package com.tongdun.data.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tongdun.data.mapper.pgqk.account.RemitFlowMapper;
import com.tongdun.data.mapper.pgqk.user.QkWithdrawTransDao;
import com.tongdun.data.mapper.pgqk.user.TStanStifDao;
import com.tongdun.data.queue.DataBlockingQueue;
import com.tongdun.data.singleton.ThreadStatus;

/**
 * 
 * @author yxw
 *
 */
public class WorkerUtil {

	private static Logger logger = LoggerFactory.getLogger(WorkerUtil.class);

	private static long endTime = 0L;

	static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

	/**
	 * @param dataDO       发送的数据
	 * @param threadStatus 线程状态
	 */
	public static boolean doSomething(Map<String, Object> dataDO, ThreadStatus threadStatus) {
		boolean flag = true;
		try {
			// 发送API请求
			logger.info("当前时间:" + System.currentTimeMillis());
			if (endTime != 0) {
				logger.info("上一笔结束时间:" + endTime);
				if (System.currentTimeMillis() - endTime < 500) {
					Thread.sleep(180);
					logger.info("发送速度过快，请稍后");
				}
			}
			logger.info("发送时间" + System.currentTimeMillis());
			flag = FraudApiInvoker.sentRequestToApi(dataDO);
			logger.info("结束时间" + System.currentTimeMillis());
			endTime = System.currentTimeMillis();
			long start = System.nanoTime();
			long use = System.nanoTime() - start;
			threadStatus.getWriteUseTime().addAndGet(use);
		} catch (InterruptedException e) {
			logger.error(e.getMessage(), e);
		}
		return flag;
	}

	public static void printThreadStatus(Map<String, Object> threadMap) {
		if (threadMap != null && threadMap.size() != 0) {
			Set<String> keys = threadMap.keySet();
			for (String key : keys) {
				Thread o = (Thread) threadMap.get(key);
				logger.info("线程{},状态{}", key, o.getState());
			}
		}
	}

	public static void stop(Map<String, Object> threadMap) {
		if (threadMap != null && threadMap.size() != 0) {
			Set<String> keys = threadMap.keySet();
			for (String key : keys) {
				if (key.indexOf("Ontime") != -1 || key.indexOf("Writer") != -1)
					continue;
				Thread o = (Thread) threadMap.get(key);
				o.stop();
				logger.info("停止线程{}", key, o.getState());
			}
		}
	}

	public static void doingReader(Object baseDao, DataBlockingQueue dataBlockingQueue, long now, long bigNow,
			Map<String, Object> param) throws Exception {
		ThreadStatus threadStatus = ThreadStatus.getSingleton();
		Map<String, Object> params = new HashMap<String, Object>();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		String startDate = simpleDateFormat.format(new Date(now));
		String endDate = simpleDateFormat.format(new Date(bigNow));

		// 查询数据库
		List<Map<String, Object>> list = null;
		params.put("start", startDate);
		params.put("end", endDate);
		long readStart = System.currentTimeMillis();
		if (param != null && param.size() != 0) {
			if(baseDao instanceof TStanStifDao){
				list = ((TStanStifDao) baseDao).queryByParamMaps(params);
			} else if(baseDao instanceof QkWithdrawTransDao) {
				list = ((QkWithdrawTransDao) baseDao).queryByParamMaps(params);
			} else if(baseDao instanceof RemitFlowMapper) {
				list = ((RemitFlowMapper) baseDao).queryByParamMaps(params);
			}
		}

		logger.info("开始时间:{}，结束时间:{},耗时:{}ms，查询数据{}条", startDate, endDate, System.currentTimeMillis() - readStart,
				list == null ? 0 : list.size());

		if (list != null && list.size() != 0) {
			int size = list.size();
			threadStatus.getReadLine().addAndGet(size);
			for (Map<String, Object> map : list) {
				if (map.size() == 0)
					continue;
				Map<String, Object> result = null;
//				logger.info("map------>" + map.toString());
				if (baseDao instanceof TStanStifDao) {
					map.put("tradeCode", "20212");
					//客户类型1—个人
					map.put("account_type","1");
//                	trans_source_type 1-钱客  2-店小友
					if ("1".equals(map.get("trans_source_type").toString())) {
						result = OriginMapping.AssemblyDataBy_qkDxyCode("qk", map);
					} else if ("2".equals(map.get("trans_source_type").toString())) {
						result = OriginMapping.AssemblyDataBy_qkDxyCode("dxy", map);
					}
				} else if(baseDao instanceof QkWithdrawTransDao) {
					if ("1".equals(map.get("trans_source_type").toString())) {
						result = OriginMapping.AssemblyDataBy_qkWithdrawCode("qk_withdraw", map);
					} else if ("2".equals(map.get("trans_source_type").toString())) {
						result = OriginMapping.AssemblyDataBy_qkWithdrawCode("dxy_withdraw", map);
					}
				} else if(baseDao instanceof RemitFlowMapper) {
//					if ("1".equals(map.get("business_type").toString())) {
						result = OriginMapping.AssemblyDataBy_qkRemitCode("qk_remit", map);
//					} else if ("2".equals(map.get("business_type").toString())) {
//						result = OriginMapping.AssemblyDataBy_qkRemitCode("dxy_remit", map);
//					}
				}
				if (result != null && result.size() > 0) {
					dataBlockingQueue.putQueue(result);
				}
			}
			try {
				Thread.sleep(Integer.parseInt(ProperitesUtil.getPropertyValue("read.sleep")));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

//    public static Map<String, Object> caseMethod(String code, Map<String, Object> map) {
//
//        Map<String, Object> result = new HashMap<>();
//        //从ext里面解析相关信息
//
//        String merId="";
//
//        switch (code) {
//            case "10101":
//                result.putAll(map);
//                Map parse = null;
//                if (map.get("ext") != null) {
//                    String ext = map.get("ext").toString();
//                    parse = (Map) JSONObject.parse(ext);
//                    String page_url = parse.get("PAGE_URL") == null ? "" : parse.get("PAGE_URL").toString();
//                    if (!StringUtils.isBlank(page_url)) {
//                        String[] split = page_url.split("\\?")[1].split("&");
//                        for (String paramStr : split) {
//                            if (paramStr.indexOf("memberType") != -1) {
//                                result.put("accountType", paramStr.split("=")[1]);
//                                break;
//                            }
//                        }
//                    }
//                }
//                if (parse != null && parse.size() != 0) {
//                    String account_name = parse.get("ACCOUNT_NAME") == null ? "" : parse.get("ACCOUNT_NAME").toString();
//                    String id_no = parse.get("ID_NO") == null ? "" : parse.get("ID_NO").toString();
//                    String mobile_no = parse.get("MOBILE_NO") == null ? "" : parse.get("MOBILE_NO").toString();
//                    String card_no = parse.get("CARD_NO") == null ? "" : parse.get("CARD_NO").toString();
//                    String payer_ip = parse.get("payerIp") == null ? "" : parse.get("payerIp").toString();
//
//                    if(!StringUtils.isBlank(account_name)){
//                        result.put("accountName", account_name);
//                    }
//                    if(!StringUtils.isBlank(id_no)){
//                        result.put("idNumber", id_no);
//                    }
//                    if(!StringUtils.isBlank(mobile_no)){
//                        result.put("accountMobile", mobile_no);
//                    }
//                    if(!StringUtils.isBlank(card_no)){
//                        result.put("merBankNo", card_no);
//                    }
//                    if(!StringUtils.isBlank(payer_ip)){
//                        result.put("ipAddress", payer_ip);
//                    }
//                    if(map.get("BIZ_PRODUCT_CODE")!=null){
//                        result.put("tradeCode",map.get("BIZ_PRODUCT_CODE"));
//                    }
//                    if(map.get("PAYMENT_VOUCHER_NO")!=null){
//                        result.put("traSeq",map.get("PAYMENT_VOUCHER_NO"));
//                    }
//                    if(map.get("MEMBER_ID")!=null){
//                        result.put("accountLogin",map.get("MEMBER_ID"));
//                    }
//                    if(map.get("ACCOUNT_NO")!=null){
//                        result.put("intoAcc",map.get("ACCOUNT_NO"));
//                    }
//                    if(map.get("AMOUNT")!=null){
//                        result.put("traAmount",map.get("AMOUNT"));
//                    }
//                    if(map.get("ACCOUNT_NO")!=null){
//                        result.put("intoAcc",map.get("ACCOUNT_NO"));
//                    }
//                    if(map.get("eventOccurTime")!=null){
//                        result.put("eventOccurTime",map.get("eventOccurTime"));
//                    }
//
//                    if(map.get("payStatus")!=null){
//                        result.put("payStatus",map.get("payStatus"));
//                    }
//
//                    merId=result.get("accountLogin").toString();
//                }
//                break;
//            case "10240":
//            case "10310":
//            case "10210":
//            case "10212":
//            case "10213":
//            case "10214":
//            case "10215":
//            case "10216":
//            case "10217":
//                Object extension = map.get("EXTENSION");
//                if (extension != null) {
//                    Map parse1 = (Map) JSONObject.parse(extension.toString());
//                    String bankName = parse1.get("bankName") == null ? "" : parse1.get("bankName").toString();
//                    result.put("intoBankName", bankName);
//
//                }
//                String MEMBER_ID = map.get("MEMBER_ID") == null ? "" : map.get("MEMBER_ID").toString();
////                if(MEMBER_ID!=null){
////                    result.put("accountLogin", MEMBER_ID);
////                }
//                String  account_no = map.get("ACCOUNT_NO").toString();
//                if(MEMBER_ID!=null){
//                    result.put("accountLogin", account_no);
//                }
//                if(map.get("name")!=null){
//                    result.put("accountName", map.get("name"));
//                }
//                if(map.get("ACCOUNT_NO")!=null){
//                    result.put("innerAccNo", map.get("ACCOUNT_NO"));
//                }
//                if(map.get("CARD_NO")!=null){
//                    result.put("intoAcc", map.get("CARD_NO"));
//                }
//                if(map.get("BIZ_PRODUCT_CODE")!=null){
//                    result.put("tradeCode", map.get("BIZ_PRODUCT_CODE"));
//                }
//                if(map.get("AMOUNT")!=null){
//                    result.put("traAmount", map.get("AMOUNT"));
//                }
//                if(map.get("PAYMENT_ORDER_NO")!=null){
//                    result.put("traSeq", map.get("PAYMENT_ORDER_NO"));
//                }
//                if(map.get("payStatus")!=null){
//                    result.put("payStatus", map.get("payStatus"));
//                }
//                if(map.get("FUNDOUT_ORDER_NO")!=null){
//                    result.put("transactionId", map.get("FUNDOUT_ORDER_NO"));
//                }
//                if(map.get("eventOccurTime")!=null){
//                    result.put("eventOccurTime", map.get("eventOccurTime"));
//                }
//                if(map.get("payResult")!=null){
//                    result.put("payResult",map.get("payResult"));
//                }
//                merId=MEMBER_ID;
//                break;
//            case "20201":
//            case "20301":
//            case "20202":
//            case "20204":
//            case "20205":
//            case "20226":
//            case "20207":
//            case "20211":
//            case "20212":
//            case "20213":
//            case "20214":
//            case "20216":
//            case "20217":
//            case "20218":
//            case "20229":
//            case "20230":
//            case "20232":
//            case "20233":
//            case "20234":
//            case "20219":
//            case "20220":
//                Map propsParse = null;
//                //从PROPS里面获取accountLogin，accountName，idNumber，accountMobile
//                if (map.get("PROPS") != null) {
//                    String props = map.get("PROPS").toString();
//                    propsParse = (Map) JSONObject.parse(props);
//                    if ("anonymous".equals(map.get("accountLogin"))) {
//                        if(propsParse.get("payerAccountNo")!=null){
//                            result.put("accountLogin", propsParse.get("payerAccountNo"));
//                        }else if (propsParse.get("bankCardNo") != null) {
//                            result.put("accountLogin", propsParse.get("bankCardNo"));
//                        }else{
//                            result.put("accountLogin", "anonymous");
//                            result.put("accountType", "1");
//
//                        }
//                    }
//                    if(propsParse.get("bankCode")!=null){
//                        result.put("cardName",propsParse.get("bankCode"));
//                    }
//                    if (propsParse.get("name") != null) {
//                        result.put("accountName", propsParse.get("name"));
//                    }
//                    if (propsParse.get("certNo") != null) {
//                        result.put("idNumber", propsParse.get("certNo"));
//                    }
//                    if (propsParse.get("phone") != null) {
//                        result.put("accountMobile", propsParse.get("phone"));
//                    }
//
//                }
//                if(map.get("bankCardNo")!=null){
//                    result.put("cardNumber",map.get("bankCardNo"));
//                }
////                if(map.get("SELLER_ID")!=null){
////                    result.put("intoAcc",map.get("SELLER_ID"));
////                }
//                if(map.get("SELLER_ACCOUNT_NO") != null) {
//                		result.put("intoAcc", map.get("SELLER_ACCOUNT_NO"));
//                }
//
//                if(map.get("BIZ_PRODUCT_CODE")!=null){
//                    result.put("tradeCode",map.get("BIZ_PRODUCT_CODE"));
//                }
//
//                if(map.get("PAY_MODE")!=null){
//                    result.put("payType",map.get("PAY_MODE"));
//                }
//
//                if(map.get("TRADE_AMOUNT")!=null){
//                    result.put("traAmount",map.get("TRADE_AMOUNT"));
//                }
//
//                if(map.get("PAYMENT_VOUCHER_NO")!=null){
//                    result.put("traSeq",map.get("PAYMENT_VOUCHER_NO"));
//                }
//
//                if(map.get("payStatus")!=null){
//                    result.put("payStatus",map.get("payStatus"));
//                }
//
//                if(map.get("payResult")!=null){
//                    result.put("payResult",map.get("payResult"));
//                }
//                if(map.get("PAYMENT_SOURCE_VOUCHER_NO")!=null){
//                    result.put("transactionId",map.get("PAYMENT_SOURCE_VOUCHER_NO"));
//                }
//                if(map.get("eventOccurTime")!=null){
//                    result.put("eventOccurTime",map.get("eventOccurTime"));
//                }
//                if(map.get("SELLER_ID")!=null){
//                    merId=map.get("SELLER_ID").toString();
//                    if("innerMember".equals(merId)){
//                        merId=map.get("SELLER_ACCOUNT_NO").toString();
//                    }
//                }
//                break;
//            default:
//                break;
//
//        }
//        MemberMessage memberMessage = MemberMessage.getSingleton();
//        Map<String, Map<String, Object>> pa = memberMessage.getPa();
//        //获取扣款账户的信息
//        String accountLogin = result.get("accountLogin") == null ? "" : result.get("accountLogin").toString();
//        if (!StringUtils.isBlank(accountLogin)) {
//            Map<String, Object> map1 = pa.get(accountLogin);
//            if (map1 != null && map1.size() != 0) {
//                Set<String> keys = map1.keySet();
//                for(String key : keys){
//                    Object o = map1.get(key);
//                    if(o!=null){
//                        result.put(key,o.toString());
//                    }
//                }
//            }else{
//                Map<String, Object> aNew = MemberMessage.getNew(accountLogin);
//                if(aNew!=null&&aNew.size()!=0){
//                    Set<String> keys = aNew.keySet();
//                    for(String key : keys){
//                        Object o = map1.get(key);
//                        if(o!=null){
//                            result.put(key,o.toString());
//                        }
//                    }
//                }
//            }
//        }
//        //获取收款账户的信息
//        String intoAcc = result.get("intoAcc") == null ? "" : result.get("intoAcc").toString();
//        if (!StringUtils.isBlank(intoAcc)) {
//            Map<String, Object> map1 = pa.get(intoAcc);
//            if (map1 != null && map1.size() != 0) {
//                if(map1.get("cardNumber")!=null){
//                    result.put("intoBankNo", map1.get("cardNumber"));
//                }
//                if(map1.get("merBankName")!=null){
//                    result.put("intoBankName", map1.get("merBankName"));
//                }
//            }else{
//                Map<String, Object> aNew = MemberMessage.getNew(intoAcc);
//                if(aNew!=null&&aNew.size()!=0){
//                    Set<String> keys = aNew.keySet();
//                    for(String key : keys){
//                        Object o = map1.get(key);
//                        if(o!=null){
//                            result.put(key,o.toString());
//                        }
//                    }
//                }
//            }
//
//        }
//        getMerMessage(pa,merId,result);
//        return result;
//    }
//
//    public static void getMerMessage(Map<String, Map<String, Object>> pa,String code,Map<String, Object> result){
//        if(!StringUtils.isBlank(code)){
//            Map<String, Object> map1 = pa.get(code);
//            if(map1!=null&&map1.size()!=0){
//                logger.info("key:{},value:{}",code,map1.toString());
//                if (code.startsWith("2")) {
//                    result.put("merNo", code);
//                    if(map1.get("com_field")!=null){
//                        result.put("merType", map1.get("com_field"));
//                    }
//                    if(map1.get("province")!=null){
//                        result.put("merProvince", map1.get("province"));
//                    }
//                }
//                result.putAll(map1);
//            }else{
//                logger.info("key:{},value:{}",code,"为空");
//
//                Map<String, Object> aNew = MemberMessage.getNew(code);
//                if(aNew!=null&&aNew.size()!=0){
//                    Set<String> keys = aNew.keySet();
//                    for(String key : keys){
//                        Object o = map1.get(key);
//                        if(o!=null){
//                            result.put(key,o.toString());
//                        }
//                    }
//                }
//            }
//
//        }
//    }
}

//class AnalysisThread extends Thread{
//
//    private Logger logger = LoggerFactory.getLogger(AnalysisThread.class);
//
//    public List<Map<String, Object>> list;
//
//    public DataBlockingQueue dataBlockingQueue;
//
//    public AnalysisThread(List<Map<String, Object>> list, DataBlockingQueue dataBlockingQueue) {
//        this.list = list;
//        this.dataBlockingQueue = dataBlockingQueue;
//    }
//
//    @Override
//    public void run() {
//        for (Map<String, Object> map : list) {
//            if(map.size()==0) continue;
//            String tradeCode = "";
//            if (map.get("BIZ_PRODUCT_CODE") != null) {
//                tradeCode = map.get("BIZ_PRODUCT_CODE").toString();
//            }
//            ProductionCodeToEventIdCache singleton = ProductionCodeToEventIdCache.getSingleton();
//            Map<String, Object> productionCodeToEventIdCache = singleton.getProductionCodeToEventIdCache();
//
//            APIConfig apiConfig = (APIConfig) productionCodeToEventIdCache.get(tradeCode);
//
//            //从PAGE_URL里面获取accountType
//            if (apiConfig != null) {
//
//
//                Map<String, Object> result = WorkerUtil.caseMethod(tradeCode, map);
//                if(result!=null&&result.size()!=0){
////                    logger.info("反欺诈:{}",result.toString());
//                    try {
//                        dataBlockingQueue.putQueue(result);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    long start = System.currentTimeMillis();
//                    HashMap clone = (HashMap) ((HashMap) result).clone();
//                    clone.put("tradeCode", "default");
//                    logger.info("clone time:{}", System.currentTimeMillis()-start);
//                    try {
//                        dataBlockingQueue.putQueue(clone);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }
//    }
//}
