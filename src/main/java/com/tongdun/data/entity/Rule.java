package com.tongdun.data.entity;

import java.io.Serializable;
import java.util.Date;

public class Rule implements Serializable {
    private Long id;

    private String uuid;

    private String name;

    private String valid;

    private String causeCode;

    private String operateCode;

    private Date gmtBegin;

    private Date gmtEnd;

    private Integer priority;

    private Float riskWeight;

    private Date gmtCreate;

    private Date gmtModified;

    private Integer displayOrder;

    private String fkPolicyUuid;

    private String template;

    private String parentUuid;

    private String ifClause;

    private Float weightRatio;

    private String weightIndex;

    private String testRun;

    private String applyStatus;

    private String applyType;

    private String fkDealTypeUuid;

    private String customId;

    private String phones;

    private String triggers;
    
    private Integer msgInterval;

    private static final long serialVersionUID = 1L;

	public Integer getMsgInterval() {
		return msgInterval;
	}
	
	public void setMsgInterval(Integer msgInterval) {
		this.msgInterval = msgInterval;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid == null ? null : valid.trim();
    }

    public String getCauseCode() {
        return causeCode;
    }

    public void setCauseCode(String causeCode) {
        this.causeCode = causeCode == null ? null : causeCode.trim();
    }

    public String getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(String operateCode) {
        this.operateCode = operateCode == null ? null : operateCode.trim();
    }

    public Date getGmtBegin() {
        return gmtBegin;
    }

    public void setGmtBegin(Date gmtBegin) {
        this.gmtBegin = gmtBegin;
    }

    public Date getGmtEnd() {
        return gmtEnd;
    }

    public void setGmtEnd(Date gmtEnd) {
        this.gmtEnd = gmtEnd;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Float getRiskWeight() {
        return riskWeight;
    }

    public void setRiskWeight(Float riskWeight) {
        this.riskWeight = riskWeight;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public String getFkPolicyUuid() {
        return fkPolicyUuid;
    }

    public void setFkPolicyUuid(String fkPolicyUuid) {
        this.fkPolicyUuid = fkPolicyUuid == null ? null : fkPolicyUuid.trim();
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template == null ? null : template.trim();
    }

    public String getParentUuid() {
        return parentUuid;
    }

    public void setParentUuid(String parentUuid) {
        this.parentUuid = parentUuid == null ? null : parentUuid.trim();
    }

    public String getIfClause() {
        return ifClause;
    }

    public void setIfClause(String ifClause) {
        this.ifClause = ifClause == null ? null : ifClause.trim();
    }

    public Float getWeightRatio() {
        return weightRatio;
    }

    public void setWeightRatio(Float weightRatio) {
        this.weightRatio = weightRatio;
    }

    public String getWeightIndex() {
        return weightIndex;
    }

    public void setWeightIndex(String weightIndex) {
        this.weightIndex = weightIndex == null ? null : weightIndex.trim();
    }

    public String getTestRun() {
        return testRun;
    }

    public void setTestRun(String testRun) {
        this.testRun = testRun == null ? null : testRun.trim();
    }

    public String getApplyStatus() {
        return applyStatus;
    }

    public void setApplyStatus(String applyStatus) {
        this.applyStatus = applyStatus == null ? null : applyStatus.trim();
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType == null ? null : applyType.trim();
    }

    public String getFkDealTypeUuid() {
        return fkDealTypeUuid;
    }

    public void setFkDealTypeUuid(String fkDealTypeUuid) {
        this.fkDealTypeUuid = fkDealTypeUuid == null ? null : fkDealTypeUuid.trim();
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId == null ? null : customId.trim();
    }

    public String getPhones() {
        return phones;
    }

    public void setPhones(String phones) {
        this.phones = phones == null ? null : phones.trim();
    }

    public String getTriggers() {
        return triggers;
    }

    public void setTriggers(String triggers) {
        this.triggers = triggers == null ? null : triggers.trim();
    }
}