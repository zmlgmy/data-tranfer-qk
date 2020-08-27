package com.tongdun.data.utils;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author yxw
 *
 */
public class OriginMapping {

	private static Logger logger = LoggerFactory.getLogger(OriginMapping.class);

	public final static Map<String, String> TRADE_CODE_NAME = Collections
			.unmodifiableMap(new HashMap<String, String>() {
				{
					put("10101", "充值");
					put("10240", "单笔/批量付款到卡");
					put("10310", "普通转账");
					put("10210", "提现");
					put("10212", "订单提现");
					put("10213", "单笔实时代付");
					put("10214", "同步单笔代付");
					put("10215", "异步单笔代付");
					put("10216", "批量付款");
					put("10217", "批量代付");
					put("20201", "即时到帐交易、快捷支付");
					put("20301", "合并支付");
					put("20202", "担保交易");
					put("20204", "固定码扫码交易");
					put("20205", "微信公众号扫码交易");
					put("20226", "一码付");
					put("20207", "单笔订单线下支付模式");
					put("20211", "微信APP支付");
					put("20212", "新快捷支付（商户和平台）");
					put("20213", "渠道侧支付");
					put("20214", "直付通支付");
					put("20216", "一码付（废弃）");
					put("20217", "主扫");
					put("20218", "被扫");
					put("20229", "app支付");
					put("20230", "一起付(B端收银台)");
					put("20232", "一键支付");
					put("20233", "wap支付");
					put("20234", "控件支付");
					put("20219", "公众号、服务窗支付大商户");
					put("20220", "公众号、服务窗支付一户一码");
					put("20203", "单笔实时代收");
					put("20221", "同步单笔代扣");
					put("20222", "异步单笔代扣");
					put("20223", "批量收款");
					put("20227", "批量代扣");
					put("20224", "pos主扫");
					put("20225", "pos被扫");
					put("20228", "pos刷卡");
					put("20208", "扫码枪扫码收单");
					put("20209", "投资通系统绑卡支付功能产品码");
					put("30301", "鉴权产品码");
					put("20210", "智能催收系统产品码");
					put("20206", "动态扫码交易外部结算模式");
					put("20231", "鉴权前台模式");
					put("10211", "自动结算");
					/** 新增渠道类型 **/
					put("01", "余额");
					put("02", "B2C");
					put("03", "POS刷卡");
					put("04", "现金");
					put("05", "快捷");
					put("06", "贷款");
					put("07", "积分");
					put("08", "奖金");
					put("09", "信用支付");
					put("14", "出款");
					put("22", "B2B");
					put("23", "网银借记卡");
					put("24", "网银贷记卡");
					put("25", "快捷储蓄卡");
					put("26", "快捷信用卡");
					put("27", "大额快捷");
					put("28", "扫码支付（综合）");
					put("29", "扫码支付-支付宝");
					put("32", "小网关网银借记卡");
					put("33", "小网关网银贷记卡");
					put("34", "单笔代收");
					put("35", "单笔代付");
					put("36", "批量代收");
					put("37", "批量代付");
					put("38", "新快捷鉴权");
					put("39", "新快捷支付");
					put("41", "线下打款支付");
					put("42", "B2B");
					// 老代收付
					put("100001", "批量代扣");
					put("100002", "批量代付");
					put("100011", "单笔代收");
					put("100014", "单笔代付");
				}
			});

	public final static Map<String, String> ACCOUNT_TYPE_MAP = Collections
			.unmodifiableMap(new HashMap<String, String>() {

				{
					put("1", "个人");
					put("2", "企业");
					put("3", "组织");
				}
			});

	// 1-借记卡 2- 信用卡 3-准贷记卡 4-预付费卡
	public final static Map<String, String> TRA_CARD_TYPE = Collections.unmodifiableMap(new HashMap<String, String>() {

		{
			put("1", "借记卡");
			put("2", "信用卡");
			put("3", "准贷记卡");
			put("4", "预付费卡");
		}
	});

	/**
	 * 钱客/店小友
	 * 
	 * @param map
	 * @return result
	 */
	public static Map<String, Object> AssemblyDataBy_qkDxyCode(String appName, Map<String, Object> map) {
		Map<String, Object> result = new HashMap<>();
		// 交易码tradeCode
		if (map.get("tradeCode") != null) {
			result.put("tradeCode", map.get("tradeCode"));
			if (TRADE_CODE_NAME.get(map.get("tradeCode")) != null) {
				result.put("tradeName", TRADE_CODE_NAME.get(map.get("tradeCode")));
			}
		}
		// 账户accountLogin
		if (map.get("ctac") != null) {
			result.put("accountLogin", map.get("ctac"));
		}
		// 手机号accountMobile
		if (map.get("account_mobile") != null) {
			result.put("accountMobile", map.get("account_mobile"));
		}
		// 名称accountName
		if (map.get("account_name") != null) {
			result.put("accountName", map.get("account_name"));
		}
		// 身份证号码idNumber
		if (map.get("id_number") != null) {
			result.put("idNumber", map.get("id_number"));
		}
		// 商户编号merNo
		if (map.get("smid") != null) {
			result.put("merNo", map.get("smid"));
		}
		// 交易金额traAmount
		if (map.get("crat") != null) {
//            BigDecimal amount = new BigDecimal(map.get("crat").toString().trim());
//            result.put("traAmount", amount.divide(new BigDecimal(100)));
			result.put("traAmount", new BigDecimal(map.get("crat").toString().trim()));
		}
		// 交易外部订单号tradeSrcNo
		if (map.get("order_no") != null) {
			result.put("tradeSrcNo", map.get("order_no"));
		}
		// 交易状态payStatus
		if (map.get("trans_stat") != null) {
			if ("2".equals(map.get("trans_stat").toString())) {
				result.put("payStatus", "0");
			} else if ("0".equals(map.get("trans_stat").toString())) {
				result.put("payStatus", "2");
			} else {
				result.put("payStatus", map.get("trans_stat"));
			}
		}
		// 交易结果payResult
		if (map.get("res_msg") != null) {
			result.put("payResult", map.get("res_msg"));
		}
		// 交易订单号transactionId
		if (map.get("pmtc") != null) {
			result.put("transactionId", map.get("pmtc"));
		}
		// 交易流水号traSeq
		if (map.get("bptc") != null) {
			result.put("traSeq", map.get("bptc"));
		}
		// 收款人银行卡号intoCardNumber
		if (map.get("bank_account_no_cipher") != null) {
			result.put("intoCardNumber", map.get("bank_account_no_cipher"));
		}
		// 收款人银行名称intoBankName
		if (map.get("bank_sub_name") != null) {
			result.put("intoBankName", map.get("bank_sub_name"));
		}
		// 收款人账户intoAcc
		if (map.get("bank_sub_name") != null) {
			result.put("intoAcc", map.get("bank_sub_name"));
		}
		// 收款人姓名intoName
		if (map.get("ctnm") != null) {
			result.put("intoName", map.get("ctnm"));
		}
		// 交渠道payChannel
		if (map.get("trans_channel") != null) {
			result.put("payChannel", map.get("trans_channel"));
		}
		// 交易渠道名称channelName
		if ((map.get("trans_channel") != null) && (TRADE_CODE_NAME.get(map.get("trans_channel")) != null)) {
			result.put("channelName", TRADE_CODE_NAME.get(map.get("trans_channel")));
		}
		

//		渠道返回码:channel_res_code
		if(map.get("channel_res_code") != null) {
			result.put("channelResCode", map.get("channel_res_code"));
		}
//		渠道返回描述:channel_res_msg
		if(map.get("channel_res_msg") != null) {
			result.put("channelResMsg", map.get("channel_res_msg"));
		}
		
//		渠道编号qk_channel_code
		if(map.get("qk_channel_code") != null) {
			result.put("channelCode", map.get("qk_channel_code"));
		}
		
		// 客户类型accountType
		Object accountType = result.get("account_type");
		if (accountType != null) {
			result.put("accountType", accountType);
			// 客户类型名称accountTypeName
			if (ACCOUNT_TYPE_MAP.get(accountType) != null) {
				result.put("accountTypeName", ACCOUNT_TYPE_MAP.get(accountType));
			}
		}
		// 扣款银行卡号cardNumber
		if (map.get("cbac") != null) {
			result.put("cardNumber", map.get("cbac"));
		}
		// 扣款银行卡类型traCardType
		if (map.get("card_type") != null && TRA_CARD_TYPE.get(map.get("card_type")) != null) {
			result.put("traCardType", TRA_CARD_TYPE.get(map.get("card_type")));
		}
		// 设备ID deviceId
		if (map.get("ctac") != null) {
			result.put("deviceId", map.get("ctac"));
		}

		if (appName != null) {
			result.put("appName", appName);
		}

		// 事件发生时间
        if (map.get("create_time") != null) {
            result.put("eventOccurTime", map.get("create_time"));
        }

		return result;
	}
	
	/**
	 * 钱客提现
	 * 
	 * @param map
	 * @return result
	 */
	public static Map<String, Object> AssemblyDataBy_qkWithdrawCode(String appName, Map<String, Object> map) {
		Map<String, Object> result = new HashMap<>();
		// 交易码tradeCode
		result.put("tradeName", "提现");
		if (map.get("trade_code") != null) {
			result.put("tradeCode", map.get("trade_code"));
		}
		// 账户accountLogin
		if (map.get("merchant_account_uuid") != null) {
			result.put("accountLogin", map.get("merchant_account_uuid"));
		}
		// 手机号accountMobile
		if (map.get("mobile_cipher") != null) {
			result.put("accountMobile", map.get("mobile_cipher"));
		}
		// 名称accountName
		if (map.get("real_name") != null) {
			result.put("accountName", map.get("real_name"));
		}
		// 商户名称
		if (map.get("company_name") != null) {
			result.put("merName", map.get("company_name"));
		}
//		// 身份证号码idNumber
////		if (map.get("id_number") != null) {
////			result.put("idNumber", map.get("id_number"));
////		}
		// 商户编号merNo
		if (map.get("merchant_no") != null) {
			result.put("merNo", map.get("merchant_no"));
		}
		// 交易金额traAmount
		if (map.get("withdraw_amount") != null) {
//            BigDecimal amount = new BigDecimal(map.get("crat").toString().trim());
//            result.put("traAmount", amount.divide(new BigDecimal(100)));
			result.put("traAmount", new BigDecimal(map.get("withdraw_amount").toString().trim()));
		}
		// 交易外部订单号tradeSrcNo
		if (map.get("source_req_flow_no") != null) {
			result.put("tradeSrcNo", map.get("source_req_flow_no"));
		}
		// 交易状态payStatus
		if (map.get("remit_status") != null) {
			if ("1".equals(map.get("remit_status").toString())) {
				result.put("payStatus", "0");
			} else if ("3".equals(map.get("remit_status").toString())) {
				result.put("payStatus", "2");
			} else {
				result.put("payStatus", "1");
			}
		}
		// 交易结果payResult
		if (map.get("withdraw_failed_reason") != null) {
			result.put("payResult", map.get("withdraw_failed_reason"));
		}
		// 交易订单号transactionId
		if (map.get("req_flow_no") != null) {
			result.put("transactionId", map.get("req_flow_no"));
		}
		// 交易流水号traSeq
		if (map.get("req_flow_no") != null) {
			result.put("traSeq", map.get("req_flow_no"));
		}
		// 收款人银行卡号intoCardNumber
		if (map.get("bank_card_no_cipher") != null) {
			result.put("intoCardNumber", map.get("bank_card_no_cipher"));
		}
		// 收款人银行名称intoBankName
		if (map.get("bank_name") != null) {
			result.put("intoBankName", map.get("bank_name"));
		}
//		// 收款人账户intoAcc
//		if (map.get("bank_sub_name") != null) {
//			result.put("intoAcc", map.get("bank_sub_name"));
//		}
//		// 收款人姓名intoName
//		if (map.get("ctnm") != null) {
//			result.put("intoName", map.get("ctnm"));
//		}
//		// 交渠道payChannel
//		if (map.get("trans_channel") != null) {
//			result.put("payChannel", map.get("trans_channel"));
//		}
//		// 交易渠道名称channelName
//		if ((map.get("trans_channel") != null) && (TRADE_CODE_NAME.get(map.get("trans_channel")) != null)) {
//			result.put("channelName", TRADE_CODE_NAME.get(map.get("trans_channel")));
//		}
//		
//
////		渠道返回码:channel_res_code
//		if(map.get("channel_res_code") != null) {
//			result.put("channelResCode", map.get("channel_res_code"));
//		}
////		渠道返回描述:channel_res_msg
//		if(map.get("channel_res_msg") != null) {
//			result.put("channelResMsg", map.get("channel_res_msg"));
//		}
//		
////		渠道编号qk_channel_code
//		if(map.get("qk_channel_code") != null) {
//			result.put("channelCode", map.get("qk_channel_code"));
//		}
//		
//		// 客户类型accountType
//		Object accountType = result.get("account_type");
//		if (accountType != null) {
//			result.put("accountType", accountType);
//			// 客户类型名称accountTypeName
//			if (ACCOUNT_TYPE_MAP.get(accountType) != null) {
//				result.put("accountTypeName", ACCOUNT_TYPE_MAP.get(accountType));
//			}
//		}
//		// 扣款银行卡号cardNumber
//		if (map.get("cbac") != null) {
//			result.put("cardNumber", map.get("cbac"));
//		}
//		// 扣款银行卡类型traCardType
//		if (map.get("card_type") != null && TRA_CARD_TYPE.get(map.get("card_type")) != null) {
//			result.put("traCardType", TRA_CARD_TYPE.get(map.get("card_type")));
//		}
//		// 设备ID deviceId
//		if (map.get("ctac") != null) {
//			result.put("deviceId", map.get("ctac"));
//		}
		// 身份证号码 idNumber
		if (map.get("id_card_no_cipher") != null) {
			result.put("idNumber", map.get("id_card_no_cipher"));
		}

		if (appName != null) {
			result.put("appName", appName);
		}

		// 事件发生时间
        if (map.get("create_time") != null) {
            result.put("eventOccurTime", map.get("create_time"));
        }

		return result;
	}
	
	/**
	 * 钱客提现
	 * 
	 * @param map
	 * @return result
	 */
	public static Map<String, Object> AssemblyDataBy_qkRemitCode(String appName, Map<String, Object> map) {
		Map<String, Object> result = new HashMap<>();
		// 交易码tradeCode
		result.put("tradeName", "提现");
		if (map.get("trade_code") != null) {
			result.put("tradeCode", map.get("trade_code"));
		}
		// 账户accountLogin
		if (map.get("account_uuid") != null) {
			result.put("accountLogin", map.get("account_uuid"));
		}
		// 手机号accountMobile
//		if (map.get("mobile_cipher") != null) {
//			result.put("accountMobile", map.get("mobile_cipher"));
//		}
		// 名称accountName
		if (map.get("real_name") != null) {
			result.put("accountName", map.get("real_name"));
		}
		// 商户名称
		if (map.get("company_name") != null) {
			result.put("merName", map.get("company_name"));
		}
		// 身份证号码idNumber
		if (map.get("id_card_no") != null) {
			result.put("idNumber", map.get("id_card_no"));
		}
		// 商户编号merNo
		if (map.get("merchant_no") != null) {
			result.put("merNo", map.get("merchant_no"));
		}
		// 交易金额traAmount
		if (map.get("remit_amount") != null) {
//            BigDecimal amount = new BigDecimal(map.get("crat").toString().trim());
//            result.put("traAmount", amount.divide(new BigDecimal(100)));
			result.put("traAmount", new BigDecimal(map.get("remit_amount").toString().trim()));
		}
		// 交易外部订单号tradeSrcNo
		if (map.get("req_flow_no") != null) {
			result.put("tradeSrcNo", map.get("req_flow_no"));
		}
		// 交易状态payStatus
		if (map.get("remit_status") != null) {
			if ("1".equals(map.get("remit_status").toString())) {
				result.put("payStatus", "0");
			} else if ("3".equals(map.get("remit_status").toString())){
				result.put("payStatus", "2");
			} else {
				result.put("payStatus", "1");
			}
		}
		// 交易结果payResult
		if (map.get("remit_status") != null) {
			if ("1".equals(map.get("remit_status").toString())) {
				result.put("payResult", "成功");
			} else if ("3".equals(map.get("remit_status").toString())) {
				result.put("payResult", "失败");
			}
		}
		if (map.get("remit_channel_no") != null && map.get("remit_channel_flow_no") != null) {
			String traSeq = map.get("remit_channel_no") + "-" + map.get("remit_channel_flow_no");
			// 交易订单号transactionId
			result.put("transactionId", traSeq);
			// 交易流水号traSeq
			result.put("traSeq",  traSeq);
		}
		// 收款人银行卡号intoCardNumber
		if (map.get("account_no_cipher") != null) {
			result.put("intoCardNumber", map.get("account_no_cipher"));
		}
		// 收款人银行名称intoBankName
		if (map.get("bank_name") != null) {
			result.put("intoBankName", map.get("bank_name"));
		}
//		// 收款人账户intoAcc
//		if (map.get("bank_sub_name") != null) {
//			result.put("intoAcc", map.get("bank_sub_name"));
//		}
//		// 收款人姓名intoName
//		if (map.get("ctnm") != null) {
//			result.put("intoName", map.get("ctnm"));
//		}
		// 交渠道payChannel
//		if (map.get("trans_channel") != null) {
//			result.put("payChannel", map.get("trans_channel"));
//		}
		// 交易渠道名称channelName
//		if ((map.get("trans_channel") != null) && (TRADE_CODE_NAME.get(map.get("trans_channel")) != null)) {
//			result.put("channelName", TRADE_CODE_NAME.get(map.get("trans_channel")));
//		}
//		
//
//		渠道返回码:channel_res_code
		if(map.get("bank_res_code") != null) {
			result.put("channelResCode", map.get("bank_res_code"));
		}
//		渠道返回描述:channel_res_msg
		if(map.get("bank_res_message") != null) {
			result.put("channelResMsg", map.get("bank_res_message"));
		}
//		
////		渠道编号qk_channel_code
//		if(map.get("qk_channel_code") != null) {
//			result.put("channelCode", map.get("qk_channel_code"));
//		}
//		
//		// 客户类型accountType
//		Object accountType = result.get("account_type");
//		if (accountType != null) {
//			result.put("accountType", accountType);
//			// 客户类型名称accountTypeName
//			if (ACCOUNT_TYPE_MAP.get(accountType) != null) {
//				result.put("accountTypeName", ACCOUNT_TYPE_MAP.get(accountType));
//			}
//		}
//		// 扣款银行卡号cardNumber
//		if (map.get("cbac") != null) {
//			result.put("cardNumber", map.get("cbac"));
//		}
//		// 扣款银行卡类型traCardType
//		if (map.get("card_type") != null && TRA_CARD_TYPE.get(map.get("card_type")) != null) {
//			result.put("traCardType", TRA_CARD_TYPE.get(map.get("card_type")));
//		}
//		// 设备ID deviceId
//		if (map.get("ctac") != null) {
//			result.put("deviceId", map.get("ctac"));
//		}
		// 身份证号码 idNumber
		if (map.get("id_card_no_cipher") != null) {
			result.put("idNumber", map.get("id_card_no_cipher"));
		}
		
		if (appName != null) {
			result.put("appName", appName);
		}
		
		// 事件发生时间
		if (map.get("create_time") != null) {
			result.put("eventOccurTime", map.get("create_time"));
		}
		
		return result;
	}
}
