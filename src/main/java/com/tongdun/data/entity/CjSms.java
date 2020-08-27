package com.tongdun.data.entity;

import java.io.Serializable;
import java.util.Date;

import com.tongdun.data.utils.SmsTypeEnums;

public class CjSms implements Serializable {
    private Integer id;

    private String phone;

    private String content;

    private String tepId;

    private String params;

    private Integer ruleId;

    private String ruleUuid;

    private String sendRes;

    private Date createdTime;
    
    private SmsTypeEnums type;

	private static final long serialVersionUID = 1L;
	
	public SmsTypeEnums getType() {
		return type;
	}
	
	public void setType(SmsTypeEnums type) {
		this.type = type;
	}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getTepId() {
        return tepId;
    }

    public void setTepId(String tepId) {
        this.tepId = tepId == null ? null : tepId.trim();
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params == null ? null : params.trim();
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleUuid() {
        return ruleUuid;
    }

    public void setRuleUuid(String ruleUuid) {
        this.ruleUuid = ruleUuid == null ? null : ruleUuid.trim();
    }

    public String getSendRes() {
        return sendRes;
    }

    public void setSendRes(String sendRes) {
        this.sendRes = sendRes == null ? null : sendRes.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}