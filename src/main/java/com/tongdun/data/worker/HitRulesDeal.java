package com.tongdun.data.worker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

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
import com.tongdun.data.utils.HttpClientUtil;
import com.tongdun.data.utils.ProperitesUtil;
import com.tongdun.data.utils.SmsTypeEnums;


/**
 * 
 * @author yxw
 *
 */
@Component
public class HitRulesDeal extends MyThread {

	private Logger logger = LoggerFactory.getLogger(HitRulesDeal.class);

	private static RuleMapper ruleMapper;

	private static CjSmsMapper cjSmsMapper;

	private static INotifyClient iNotifyClient;

	private Map<String, Object> reqParams;

	private JSONObject resParams;

	List<Map<String, Object>> hitRules;

	@Resource
	public void setRuleMapper(RuleMapper ruleMapper) {
		HitRulesDeal.ruleMapper = ruleMapper;
	}

	@Resource
	public void setCjSmsMapper(CjSmsMapper cjSmsMapper) {
		HitRulesDeal.cjSmsMapper = cjSmsMapper;
	}

	@Resource
	public void setINotifyClient(INotifyClient iNotifyClient) {
		HitRulesDeal.iNotifyClient = iNotifyClient;
	}

	public HitRulesDeal() {
	}

	public HitRulesDeal(Map<String, Object> reqParams, JSONObject resParams, List<Map<String, Object>> hitRules) {
		this.resParams = resParams;
		this.reqParams = reqParams;
		this.hitRules = hitRules;
	}

	@Override
	public void run() {
//		List<Map<String, Object>> hitRules = (List) resParams.getJSONArray("hitRules");
//		if (hitRules != null) {
		if ("true".equals(ProperitesUtil.getPropertyValue("sms.switch"))) {
			String tplId = ProperitesUtil.getPropertyValue("mnsclient.tplId");
			for (Map<String, Object> hitMap : hitRules) {
				if (("MessageAlert").equals(hitMap.get("dealType"))) {

					List<String> phones = new ArrayList<String>();
					Rule rule = ruleMapper.selectByPrimaryKey(Long.parseLong(hitMap.get("id").toString()));
					List<Map<String, Object>> rulePhones = (List<Map<String, Object>>) JSON.parse(rule.getPhones());
					if (rulePhones != null) {
						for (Map<String, Object> phoneStr : rulePhones) {
							if (phoneStr.get("phone1") != null && !phoneStr.get("phone1").toString().isEmpty()) {
								phones.add(phoneStr.get("phone1").toString());
							}
							if (phoneStr.get("phone2") != null && !phoneStr.get("phone2").toString().isEmpty()) {
								phones.add(phoneStr.get("phone2").toString());
							}
							if (phoneStr.get("phone3") != null && !phoneStr.get("phone3").toString().isEmpty()) {
								phones.add(phoneStr.get("phone3").toString());
							}

						}
						for (String phone : phones) {
							Map<String, Object> cjSmsParams = new HashMap<String, Object>();
							cjSmsParams.put("phone", phone);
							cjSmsParams.put("ruleUuid", hitMap.get("uuid"));
							cjSmsParams.put("dateTime", new Date());
							cjSmsParams.put("type", SmsTypeEnums.SMS);
							cjSmsParams.put("second", ProperitesUtil.getPropertyValue("mnsclient.second"));
							Integer count = cjSmsMapper.selectCountByParams(cjSmsParams);
							if (count == 0) {
								try {
									Map<String, Object> sendParam = new HashMap<String, Object>();
									sendParam.put("seqId", resParams.get("seqId"));
									sendParam.put("ruleName", hitMap.get("name"));
									CjSms cjSms = new CjSms();
									cjSms.setCreatedTime(new Date());
									cjSms.setPhone(phone);
									cjSms.setRuleId(Integer.parseInt(hitMap.get("id").toString()));
									cjSms.setRuleUuid(hitMap.get("uuid").toString());
									cjSms.setTepId(tplId);
									cjSms.setParams(JSONObject.toJSONString(sendParam));
									cjSms.setType(SmsTypeEnums.SMS);
									cjSmsMapper.insert(cjSms);
									Response res = iNotifyClient.sendMobileMsg(phone, tplId, sendParam);
									logger.info(String.format("电话号码%s--模板%s---响应%s", phone, tplId, JSONObject.toJSON(res)));
									if (res.isSuccess()) {
										cjSms.setSendRes(JSONObject.toJSONString(res));
										cjSmsMapper.updateByPrimaryKey(cjSms);
									}
								} catch (Exception e) {
									logger.error("调用短信接口异常-----" + e.getMessage());
								}
							}
						}
					}
				}
			}
		}

		if ("true".equals(ProperitesUtil.getPropertyValue("yonyoucloud.switch"))) {
			for (Map<String, Object> hitMap : hitRules) {
				if (("YonYouCloudAlert").equals(hitMap.get("dealType"))) {
					List<String> phones = new ArrayList<String>();
					Rule rule = ruleMapper.selectByPrimaryKey(Long.parseLong(hitMap.get("id").toString()));
					List<Map<String, Object>> rulePhones = (List<Map<String, Object>>) JSON.parse(rule.getPhones());
					if (rulePhones != null) {
						for (Map<String, Object> phoneStr : rulePhones) {
							if (phoneStr.get("phone1") != null && !phoneStr.get("phone1").toString().isEmpty()) {
								phones.add(phoneStr.get("phone1").toString());
							}
							if (phoneStr.get("phone2") != null && !phoneStr.get("phone2").toString().isEmpty()) {
								phones.add(phoneStr.get("phone2").toString());
							}
							if (phoneStr.get("phone3") != null && !phoneStr.get("phone3").toString().isEmpty()) {
								phones.add(phoneStr.get("phone3").toString());
							}
						}
						Map<String, Object> tokenParams = new HashMap<String, Object>();
						tokenParams.put("appid", ProperitesUtil.getPropertyValue("yonyoucloud.appid"));
						tokenParams.put("secret", ProperitesUtil.getPropertyValue("yonyoucloud.secret"));
						String resTokenStr = HttpClientUtil
								.invokeGet(ProperitesUtil.getPropertyValue("yonyoucloud.tokenUrl"), tokenParams, 5000);
						Map resToken = (Map) JSON.parseObject(resTokenStr);
						logger.info("token------>>" + resTokenStr);
						String content = "交易seqId:" + resParams.get("seqId") + "，商户编号：" + reqParams.get("merNo") + "，触发了规则："
								+ hitMap.get("name") + "，请及时查看交易详情！【触发规则友空间预警】";
						List merIdList = new ArrayList();
						Map<String, Object> sendParams = new HashMap<String, Object>();
						sendParams.put("spaceId", ProperitesUtil.getPropertyValue("yonyoucloud.spaceId"));
						sendParams.put("appId", ProperitesUtil.getPropertyValue("yonyoucloud.appId"));
						sendParams.put("sendThrough", "appNotify");
						sendParams.put("sendScope", "list");
						sendParams.put("to", merIdList);
						sendParams.put("title", "友空间规则预警");
						sendParams.put("desc", content);
						sendParams.put("attributes", JSON.parse("{systemDefaultBrowser: true}"));
						for (String phone : phones) {
							Map<String, Object> cjSmsParams = new HashMap<String, Object>();
							cjSmsParams.put("phone", phone);
							cjSmsParams.put("ruleUuid", hitMap.get("uuid"));
							cjSmsParams.put("dateTime", new Date());
							cjSmsParams.put("type", SmsTypeEnums.YONYOUCLOUD);
							cjSmsParams.put("second", rule.getMsgInterval());
							Integer count = cjSmsMapper.selectCountByParams(cjSmsParams);
							if (count == 0) {
								CjSms cjSms = new CjSms();
								cjSms.setCreatedTime(new Date());
								cjSms.setPhone(phone);
								cjSms.setRuleId(Integer.parseInt(hitMap.get("id").toString()));
								cjSms.setRuleUuid(hitMap.get("uuid").toString());
								cjSms.setType(SmsTypeEnums.YONYOUCLOUD);
								cjSms.setContent(content);
								cjSmsMapper.insert(cjSms);
								if (resToken != null && "0".equals(resToken.get("code").toString())) {
									Map<String, Object> merParams = new HashMap<String, Object>();
									// 18001202404
									merParams.put("field", phone);
									merParams.put("type", "1");
									
									String resMerInfoStr = HttpClientUtil
											.connectPostHttps(ProperitesUtil.getPropertyValue("yonyoucloud.merUrl")
													+ ((Map) resToken.get("data")).get("access_token"), merParams);
									logger.info("resMerInfoStr------>>>" + resMerInfoStr);
									Map resMerInfo = (Map) JSON.parseObject(resMerInfoStr);
									if (resMerInfo != null && "0".equals(resMerInfo.get("code").toString())) {
										merIdList.add(((Map) resMerInfo.get("data")).get("id"));
										
										String sendResStr = HttpClientUtil.postJsonBody(
												ProperitesUtil.getPropertyValue("yonyoucloud.msgUrl")
												+ ((Map) resToken.get("data")).get("access_token"),
												5000, sendParams, "UTF-8");
										logger.info("sendRes------>>>>" + sendResStr);
										Map sendRes = JSON.parseObject(sendResStr);
										if (sendRes != null && sendRes.get("flag") != null
												&& "0".equals(sendRes.get("flag").toString())) {
											cjSms.setSendRes("成功");
											cjSmsMapper.updateByPrimaryKey(cjSms);
										} else {
											cjSms.setSendRes("失败！" + sendRes.get("msg"));
											cjSmsMapper.updateByPrimaryKey(cjSms);
										}
									} else {
										cjSms.setSendRes(resMerInfo.get("msg").toString());
										cjSmsMapper.updateByPrimaryKey(cjSms);
									}
								} else {
									cjSms.setSendRes(resToken.get("msg").toString());
									cjSmsMapper.updateByPrimaryKey(cjSms);
								}
							} else {
								logger.info("受时间间隔限制------" + rule.getMsgInterval());
							}
						}
					}
				}
			}
		}
//		}

//		try {
//			sleep(10);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
	}

}
