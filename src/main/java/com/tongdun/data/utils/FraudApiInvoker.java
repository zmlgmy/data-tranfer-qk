package com.tongdun.data.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netfinworks.mns.client.INotifyClient;
import com.netfinworks.mns.client.Response;
import com.tongdun.data.entity.CjSms;
import com.tongdun.data.entity.Rule;
import com.tongdun.data.mapper.kratos.CjSmsMapper;
import com.tongdun.data.mapper.kratos.RuleMapper;
import com.tongdun.data.singleton.ProductionCodeToEventIdCache;
import com.tongdun.data.worker.HitRulesDeal;

/**
 * 发送API请求
 * 
 * @author yxw
 *
 */
@Component
public class FraudApiInvoker {

	private static Logger logger = LoggerFactory.getLogger(FraudApiInvoker.class);

	// API服务器地址，请替换为真实的服务器地址
	private static final String apiUrl = ProperitesUtil.getPropertyValue("request.api.url");

	private static PoolingHttpClientConnectionManager connMgr;
	private static RequestConfig requestConfig;
	
//	private static RuleMapper ruleMapper;
//	@Resource
//	public void setRuleMapper(RuleMapper ruleMapper) {
//		FraudApiInvoker.ruleMapper = ruleMapper;
//	}
	
//	private static CjSmsMapper cjSmsMapper;
//	@Resource
//	public void setCjSmsMapper(CjSmsMapper cjSmsMapper) {
//		FraudApiInvoker.cjSmsMapper = cjSmsMapper;
//	}
	
//	private static INotifyClient iNotifyClient;
//	@Resource
//	public void setINotifyClient(INotifyClient iNotifyClient) {
//		FraudApiInvoker.iNotifyClient = iNotifyClient;
//	}
	
	public FraudApiInvoker() {
		// 设置连接池
		connMgr = new PoolingHttpClientConnectionManager();
		// 设置连接池大小
		connMgr.setMaxTotal(200);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时
		configBuilder.setConnectTimeout(500);
		// 设置读取超时
		configBuilder.setSocketTimeout(5000);
		// 设置从连接池获取连接实例的超时
		configBuilder.setConnectionRequestTimeout(500);
		// 在提交请求之前 测试连接是否可用
		configBuilder.setStaleConnectionCheckEnabled(true);
		requestConfig = configBuilder.build();
	}

	@SuppressWarnings("unchecked")
	public static FraudApiResponse invoke(Map<String, Object> params) throws IOException {
		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
				.setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;
		HttpEntity entity = null;

		try {
			httpPost.setConfig(requestConfig);
			List<NameValuePair> pairList = new ArrayList<NameValuePair>(params.size());
			for (Map.Entry<String, Object> entry : params.entrySet()) {
				NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue().toString());
				pairList.add(pair);
			}
			httpPost.setEntity(new UrlEncodedFormEntity(pairList, Charset.forName("utf-8")));
			response = httpClient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				logger.warn("[FraudApiInvoker] invoke failed, response status: " + statusCode);
				return null;
			}
			entity = response.getEntity();
			if (entity == null) {
				logger.warn("[FraudApiInvoker] invoke failed, response output is null!");
				return null;
			}
			String result = EntityUtils.toString(entity, "utf-8");
			logger.info("交易结果：" + result);
			
			JSONObject parseObject = JSONObject.parseObject(result);
			List<Map<String, Object>> hitRules = (List) parseObject.getJSONArray("hitRules");
			if (hitRules != null) {
				HitRulesDeal hitRulesDeal = new HitRulesDeal(params, parseObject, hitRules);
				hitRulesDeal.start();
			}
			
//			if("true".equals(ProperitesUtil.getPropertyValue("sms.switch"))) {
//				List<Map<String,Object>> hitRules = (List)JSONObject.parseObject(result).getJSONArray("hitRules");
//				if(hitRules != null) {
//					String tplId = ProperitesUtil.getPropertyValue("mnsclient.tplId");
//					for (Map<String, Object> hitMap : hitRules) {
//						if(("MessageAlert").equals(hitMap.get("dealType"))) {
//							
//							List<String> phones = new ArrayList<String>();
//							Rule rule = ruleMapper.selectByPrimaryKey(Long.parseLong(hitMap.get("id").toString()));
//							List<Map<String,Object>> rulePhones = (List<Map<String, Object>>) JSON.parse(rule.getPhones());
//							if(rulePhones != null) {
//								for (Map<String, Object> phoneStr : rulePhones) {
//									if(phoneStr.get("phone1") != null && !phoneStr.get("phone1").toString().isEmpty()) {
//										phones.add(phoneStr.get("phone1").toString());
//									}
//									if(phoneStr.get("phone2") != null && !phoneStr.get("phone2").toString().isEmpty()) {
//										phones.add(phoneStr.get("phone2").toString());
//									}
//									if(phoneStr.get("phone3") != null && !phoneStr.get("phone3").toString().isEmpty()) {
//										phones.add(phoneStr.get("phone3").toString());
//									}
//								}
//							}
//							for (String phone : phones) {
//								Map<String, Object> cjSmsParams = new HashMap<String, Object>();
//								cjSmsParams.put("phone", phone);
//								cjSmsParams.put("ruleUuid", hitMap.get("uuid"));
//								cjSmsParams.put("dateTime", new Date());
//								cjSmsParams.put("type", SmsTypeEnums.SMS);
//								cjSmsParams.put("second", ProperitesUtil.getPropertyValue("mnsclient.second"));
//								Integer count = cjSmsMapper.selectCountByParams(cjSmsParams);
//								if(count == 0) {
//									try {
//										Map<String, Object> sendParam = new HashMap<String, Object>();
//										sendParam.put("seqId", JSONObject.parseObject(result).get("seqId"));
//										sendParam.put("ruleName", hitMap.get("name"));
//										CjSms cjSms = new CjSms();
//										cjSms.setCreatedTime(new Date());
//										cjSms.setPhone(phone);
//										cjSms.setRuleId(Integer.parseInt(hitMap.get("id").toString()));
//										cjSms.setRuleUuid(hitMap.get("uuid").toString());
//										cjSms.setTepId(tplId);
//										cjSms.setParams(JSONObject.toJSONString(sendParam));
//										cjSms.setType(SmsTypeEnums.SMS);
//										cjSmsMapper.insert(cjSms);
//										Response res = iNotifyClient.sendMobileMsg(phone, tplId, sendParam);
//										logger.info(String.format("电话号码%s--模板%s---响应%s", phone, tplId, JSONObject.toJSON(res)));
//										if (res.isSuccess()) {
//											cjSms.setSendRes(JSONObject.toJSONString(res));
//											cjSmsMapper.updateByPrimaryKey(cjSms);
//										}
//									} catch (Exception e) {
//										logger.error("调用短信接口异常-----" + e.getMessage());
//									}
//								}
//							}
//						}
//					}
//				}
//			}
//			
//			if("true".equals(ProperitesUtil.getPropertyValue("yonyoucloud.switch"))) {
//				List<Map<String,Object>> hitRules = (List)JSONObject.parseObject(result).getJSONArray("hitRules");
//				if(hitRules != null) {
//					for (Map<String, Object> hitMap : hitRules) {
//						if(("YonYouCloudAlert").equals(hitMap.get("dealType"))) {
//							List<String> phones = new ArrayList<String>();
//							Rule rule = ruleMapper.selectByPrimaryKey(Long.parseLong(hitMap.get("id").toString()));
//							List<Map<String,Object>> rulePhones = (List<Map<String, Object>>) JSON.parse(rule.getPhones());
//							if(rulePhones!= null) {
//								for (Map<String, Object> phoneStr : rulePhones) {
//									if(phoneStr.get("phone1") != null && !phoneStr.get("phone1").toString().isEmpty()) {
//										phones.add(phoneStr.get("phone1").toString());
//									}
//									if(phoneStr.get("phone2") != null && !phoneStr.get("phone2").toString().isEmpty()) {
//										phones.add(phoneStr.get("phone2").toString());
//									}
//									if(phoneStr.get("phone3") != null && !phoneStr.get("phone3").toString().isEmpty()) {
//										phones.add(phoneStr.get("phone3").toString());
//									}
//								}
//							}
//							Map<String, Object> tokenParams = new HashMap<String, Object>();
//							tokenParams.put("appid", ProperitesUtil.getPropertyValue("yonyoucloud.appid"));
//							tokenParams.put("secret", ProperitesUtil.getPropertyValue("yonyoucloud.secret"));
//							String resTokenStr = HttpClientUtil.invokeGet(ProperitesUtil.getPropertyValue("yonyoucloud.tokenUrl"), tokenParams , 5000);
//							Map resToken = (Map)JSON.parseObject(resTokenStr);
//							logger.info("token------>>" + resTokenStr);
//							String content = "交易seqId:"+JSONObject.parseObject(result).get("seqId")+"，商户编号："+params.get("merNo") +"，触发了规则："+hitMap.get("name")+"，请及时查看交易详情！【触发规则友空间预警】";
//							List merIdList = new ArrayList();
//							Map<String, Object> sendParams = new HashMap<String, Object>();
//							sendParams.put("spaceId", ProperitesUtil.getPropertyValue("yonyoucloud.spaceId"));
//							sendParams.put("appId", ProperitesUtil.getPropertyValue("yonyoucloud.appId"));
//							sendParams.put("sendThrough", "appNotify");
//							sendParams.put("sendScope", "list");
//							sendParams.put("to", merIdList);
//							sendParams.put("title", "友空间规则预警");
//							sendParams.put("desc", content);
//							sendParams.put("attributes", JSON.parse("{systemDefaultBrowser: true}"));
//							for (String phone : phones) {
//								Map<String, Object> cjSmsParams = new HashMap<String, Object>();
//								cjSmsParams.put("phone", phone);
//								cjSmsParams.put("ruleUuid", hitMap.get("uuid"));
//								cjSmsParams.put("dateTime", new Date());
//								cjSmsParams.put("type", SmsTypeEnums.YONYOUCLOUD);
//								cjSmsParams.put("second",rule.getMsgInterval());
//								Integer count = cjSmsMapper.selectCountByParams(cjSmsParams);
//								if(count == 0) {
//									CjSms cjSms = new CjSms();
//									cjSms.setCreatedTime(new Date());
//									cjSms.setPhone(phone);
//									cjSms.setRuleId(Integer.parseInt(hitMap.get("id").toString()));
//									cjSms.setRuleUuid(hitMap.get("uuid").toString());
//									cjSms.setType(SmsTypeEnums.YONYOUCLOUD);
//									cjSms.setContent(content);
//									cjSmsMapper.insert(cjSms);
//									if(resToken != null && "0".equals(resToken.get("code").toString())) {
//										Map<String, Object> merParams = new HashMap<String, Object>();
//										//18001202404
//										merParams.put("field", phone);
//										merParams.put("type", "1");
//										
//										String resMerInfoStr = HttpClientUtil.connectPostHttps(ProperitesUtil.getPropertyValue("yonyoucloud.merUrl")+((Map)resToken.get("data")).get("access_token"), merParams);
//										logger.info("resMerInfoStr------>>>" + resMerInfoStr);
//										Map resMerInfo = (Map)JSON.parseObject(resMerInfoStr);
//										if(resMerInfo != null && "0".equals(resMerInfo.get("code").toString())) {
//											merIdList.add(((Map)resMerInfo.get("data")).get("id"));
//											
//											String sendResStr = HttpClientUtil.postJsonBody(ProperitesUtil.getPropertyValue("yonyoucloud.msgUrl")+((Map)resToken.get("data")).get("access_token"), 5000, sendParams, "UTF-8");
//											logger.info("sendRes------>>>>" + sendResStr);
//											Map sendRes = JSON.parseObject(sendResStr);
//											if(sendRes!= null && sendRes.get("flag") != null && "0".equals(sendRes.get("flag").toString())) {
//												cjSms.setSendRes("成功");
//												cjSmsMapper.updateByPrimaryKey(cjSms);
//											} else {
//												cjSms.setSendRes("失败！"+sendRes.get("msg"));
//												cjSmsMapper.updateByPrimaryKey(cjSms);
//											}
//										} else {
//											cjSms.setSendRes(resMerInfo.get("msg").toString());
//											cjSmsMapper.updateByPrimaryKey(cjSms);
//										}
//									} else {
//										cjSms.setSendRes(resToken.get("msg").toString());
//										cjSmsMapper.updateByPrimaryKey(cjSms);
//									}
//								} else {
//									logger.info("受时间间隔限制------" + rule.getMsgInterval());
//								}
//							}
//						}
//					}
//				}
//				
//				
//			}

			return JSON.parseObject(result, FraudApiResponse.class);
		} catch (Exception e) {
			logger.error("[FraudApiInvoker] invoke throw exception, details: ", e);
		} finally {
			if (response != null) {
				EntityUtils.consume(response.getEntity());
			}
			if (entity != null) {
				entity.getContent().close();
			}
			httpPost.releaseConnection();
		}
		return null;
	}

	private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
		SSLConnectionSocketFactory sslsf = null;
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			sslsf = new SSLConnectionSocketFactory(sslContext, new X509HostnameVerifier() {

				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}

				@Override
				public void verify(String host, SSLSocket ssl) throws IOException {
				}

				@Override
				public void verify(String host, X509Certificate cert) throws SSLException {
				}

				@Override
				public void verify(String host, String[] cns, String[] subjectAlts) throws SSLException {
				}
			});
		} catch (GeneralSecurityException e) {
			logger.error(e.getMessage(), e);
		}
		return sslsf;
	}

	/**
	 * 请求API
	 * 
	 * @param dataDO
	 * @return
	 */
	public static boolean sentRequestToApi(Map<String, Object> dataDO) {
		boolean result = true;
		Map<String, Object> params = constructRequestParams(dataDO);
		if (params != null && params.size() != 0) {
			FraudApiResponse apiResp = null;
			logger.info("发送的数据:{}", params.toString());
			try {
				apiResp = FraudApiInvoker.invoke(params);
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (apiResp == null) {
				logger.warn("[FraudApiInvoker] the api response is null.");
				return false;
			}
			if (!apiResp.isSuccess()) {
				result = false;
				logger.warn("[FraudApiInvoker] invoke faild. the reason: {}", apiResp.getReasonCode());
			}
		}

		return result;
	}

	/**
	 * 反射组装参数
	 * 
	 * @param dataDO
	 * @return
	 */
	private static Map<String, Object> constructRequestParams(Map<String, Object> dataDO) {
		DateFormat formatStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		DateFormat formatStrNo_ = new SimpleDateFormat("yyyyMMdd");
		Map<String, Object> params = new HashMap<String, Object>();
		ProductionCodeToEventIdCache singleton = ProductionCodeToEventIdCache.getSingleton();
		Map<String, Object> productionCodeToEventIdCache = singleton.getProductionCodeToEventIdCache();
		APIConfig apiConfig = (APIConfig) productionCodeToEventIdCache.get(dataDO.get("appName"));
		if (apiConfig != null) {
			params.put("partnerCode", "kratos"); // 此处填写产品标识(kratos)
			params.put("appName", apiConfig.getAppName()); // 应用标识
			params.put("secretKey", apiConfig.getSecretKey());// 旗舰版需要填写app密钥，专业版和基础版不需要app密钥
			params.put("eventId", apiConfig.getEventId()); // 此处填写策略集上的事件标识
			
			dataDO.put("appName", apiConfig.getAppName());

//			Map<String, String> fieldsMapper = FieldsMapper.getSingleton().getFieldsMapper();
//			Set<String> keys = dataDO.keySet();
			Map<String, Object> originParams = new HashMap<>();
//			if (fieldsMapper != null && fieldsMapper.size() != 0) {
//				for (String key : keys) {
//					String s = fieldsMapper.get(key);
//					if (!StringUtils.isBlank(s)) {
//						originParams.put(s, dataDO.get(s));
//					} else {
//						originParams.put(key, dataDO.get(key));
//					}
//				}
//			} else {
				originParams.putAll(dataDO);
//			}
			params.putAll(originParams);

			// 生成事件发生时间
			String eventOccurTimeStr = ProperitesUtil.getPropertyValue("eventOccurTime");
			String eventOccurTime = "";
			if (params.get(eventOccurTimeStr) != null) {
				eventOccurTime = params.get(eventOccurTimeStr).toString();
			}
			if (StringUtils.isEmpty(eventOccurTime)) {
				Object eventOccurTime1 = params.get("eventOccurTime");
				if (eventOccurTime1 != null) {
					eventOccurTime = eventOccurTime1.toString();
				} else {
					eventOccurTime = formatStr.format(new Date());
				}
			}

			String seqId = generateSeqId(eventOccurTime);
			long times = 0;
			try {
				times = formatStr.parse(eventOccurTime).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}

			eventOccurTime = formatStr.format(new Date(times));

			String tid = DataUtil.getTid(params);

			params.put("tid", tid);
			params.put("seqId", seqId);
			params.put("eventOccurTime", eventOccurTime);
		}
		return params;
	}

	/**
	 * 反射对象属性
	 * 
	 * @param params
	 * @param object
	 * @return
	 */
	public static Map<String, Object> reflectObjectParams(Map<String, Object> params, Object object) {

		if (object == null) {
			logger.warn("[FraudApiInvoker] reflect object is null");
			return params;
		}
		Class<?> classes = (Class<?>) object.getClass();
		Field[] fs = classes.getDeclaredFields();
		for (int i = 0; i < fs.length; i++) {
			Field f = fs[i];
			f.setAccessible(true); // 设置些属性是可以访问的
			Object value = null;
			String key = f.getName();
			try {
				value = f.get(object);
				String type = f.getType().toString();// 得到此属性的类型
				if (type.endsWith("int") || type.endsWith("Integer")) {
					if (value == null) {
						value = 0;
					}
				}
				params.put(key, value);
			} catch (IllegalArgumentException e) {
				logger.error("[FraudApiInvoker]反射object属性参数不合法：" + e.getMessage(), e);
			} catch (IllegalAccessException e) {
				logger.error("[FraudApiInvoker]反射object属性不可访问：" + e.getMessage(), e);
			}

		}
		return params;
	}

	/**
	 * 生成seqId
	 * 
	 * @param eventOccurTime
	 * @return
	 */
	public static String generateSeqId(String eventOccurTime) {
//		logger.info(eventOccurTime);
		if (StringUtils.isEmpty(eventOccurTime)) {
			logger.warn("[FraudApiInvoker]create seqId warn , eventOccurTime is null");
			return null;
		}
		long times = 0;
		DateFormat formatStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		DateFormat formatStrNo_ = new SimpleDateFormat("yyyyMMdd");
		try {
			Date date = null;
			if (eventOccurTime.indexOf("-") != -1) {
				date = formatStr.parse(eventOccurTime);
			} else {
				date = formatStrNo_.parse(eventOccurTime);
			}

			times = date.getTime();
		} catch (ParseException e) {
			logger.error("[FraudApiInvoker]create seqId error , " + e.getMessage(), e);
			return null;
		}

		StringBuilder sb = new StringBuilder();
		sb.append(times);
		sb.append("-");

		Random r = new Random();
		String ri = r.nextInt(100000000) + "";
		if (ri.length() < 8) {
			for (int i = 0; i < 8 - ri.length(); i++) {
				sb.append("0");
			}
		}
		sb.append(ri);
		String seqId = sb.toString();
		return seqId;
	}

}
