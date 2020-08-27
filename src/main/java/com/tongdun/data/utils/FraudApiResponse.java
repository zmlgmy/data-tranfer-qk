package com.tongdun.data.utils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API执行结果返回类
 * @author yxw
 *
 */
public class FraudApiResponse implements Serializable {

	private static final long serialVersionUID = 844958112006659504L;
	private boolean success; // 执行是否成功，不成功时对应reason_code
	private String reasonCode; // 错误码及原因描述，正常执行完扫描时为空
	private String seqId; // 请求序列号，每个请求进来都分配一个全局唯一的id
	private Integer finalScore; // 风险分数
	private String finalDecision; // 最终的风险决策结果
	private String ruleDealType; // 交易的风险处理方式
	private String ruleDealTypeName; // 交易的风险处理方式中文名
	private String dealType; // 根据策略映射的处理方式
	private String ruleExplanation; // 规则的解释
	private Integer spendTime; // 花费的时间，单位ms

	public String getReasonCode() {
		return reasonCode;
	}
	
	public void setReasonCode(String reasonCode) {
		this.reasonCode = reasonCode;
	}
	
	public Integer getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(Integer finalScore) {
		this.finalScore = finalScore;
	}

	public String getFinalDecision() {
		return finalDecision;
	}

	public void setFinalDecision(String finalDecision) {
		this.finalDecision = finalDecision;
	}

	public String getRuleDealType() {
		return ruleDealType;
	}

	public void setRuleDealType(String ruleDealType) {
		this.ruleDealType = ruleDealType;
	}

	public String getRuleDealTypeName() {
		return ruleDealTypeName;
	}

	public void setRuleDealTypeName(String ruleDealTypeName) {
		this.ruleDealTypeName = ruleDealTypeName;
	}

	public String getSeqId() {
		return seqId;
	}

	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}

	public Integer getSpendTime() {
		return spendTime;
	}

	public void setSpendTime(Integer spendTime) {
		this.spendTime = spendTime;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getRuleExplanation() {
		return ruleExplanation;
	}

	public void setRuleExplanation(String ruleExplanation) {
		this.ruleExplanation = ruleExplanation;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("\"success\":\"").append(this.success).append("\",");
		if (this.reasonCode != null) {
			sb.append("\"reasonCode\":\"").append(this.reasonCode).append("\",");
		}
		if (this.finalDecision != null) {
			sb.append("\"finalDecision\":\"").append(this.finalDecision).append("\",");
		}
		if (this.finalScore != null) {
			sb.append("\"finalScore\":\"").append(this.finalScore).append("\",");
		}
		if (this.ruleDealType != null) {
			sb.append("\"ruleDealType\":\"").append(this.ruleDealType).append("\",");
		}
		if (this.ruleDealTypeName != null) {
			sb.append("\"ruleDealTypeName\":\"").append(this.ruleDealTypeName).append("\',");
		}
		if (this.ruleExplanation != null) {
			sb.append("\"ruleExplanation\":\"").append(this.ruleExplanation).append("\",");
		}
		return sb.deleteCharAt(sb.length() - 1).toString();
	}

}
