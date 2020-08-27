package com.tongdun.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class TrMerchantQk implements Serializable {
    private Integer id;

    private String userUuid;

    private String accountUuid;

    private Integer userClass;

    private Integer isPost;

    private Date postTime;

    private Integer qkServerProviderId;

    private Integer bankAccountType;

    private Integer settType;

    private Integer settDay;

    private Integer isAgent;

    private Integer isSelfSettMode;

    private Date createTime;

    private Integer deleteStatus;

    private Integer qkSeller1Id;

    private Integer qkSeller2Id;

    private Integer qkSeller3Id;

    private Integer userLevel;

    private Integer relativeSpId;

    private Integer merchantType;

    private Integer refMerchantId;

    private Integer checkStatus;

    private BigDecimal similarityDegree;

    private Integer activateStatus;

    private Integer memberLevel;

    private Date synRateTime;

    private Date synLimitTime;

    private Date synAccTime;

    private Date activateTime;

    private Integer bankCardType;

    private Integer gender;

    private Date wxInsertTime;

    private Date alipayInsertTime;

    private Date idCardNoStartTime;

    private Date idCardNoEndTime;

    private Integer qrcSynStatus;

    private Date cupMerInsertTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    public String getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(String accountUuid) {
        this.accountUuid = accountUuid == null ? null : accountUuid.trim();
    }

    public Integer getUserClass() {
        return userClass;
    }

    public void setUserClass(Integer userClass) {
        this.userClass = userClass;
    }

    public Integer getIsPost() {
        return isPost;
    }

    public void setIsPost(Integer isPost) {
        this.isPost = isPost;
    }

    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public Integer getQkServerProviderId() {
        return qkServerProviderId;
    }

    public void setQkServerProviderId(Integer qkServerProviderId) {
        this.qkServerProviderId = qkServerProviderId;
    }

    public Integer getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(Integer bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public Integer getSettType() {
        return settType;
    }

    public void setSettType(Integer settType) {
        this.settType = settType;
    }

    public Integer getSettDay() {
        return settDay;
    }

    public void setSettDay(Integer settDay) {
        this.settDay = settDay;
    }

    public Integer getIsAgent() {
        return isAgent;
    }

    public void setIsAgent(Integer isAgent) {
        this.isAgent = isAgent;
    }

    public Integer getIsSelfSettMode() {
        return isSelfSettMode;
    }

    public void setIsSelfSettMode(Integer isSelfSettMode) {
        this.isSelfSettMode = isSelfSettMode;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getQkSeller1Id() {
        return qkSeller1Id;
    }

    public void setQkSeller1Id(Integer qkSeller1Id) {
        this.qkSeller1Id = qkSeller1Id;
    }

    public Integer getQkSeller2Id() {
        return qkSeller2Id;
    }

    public void setQkSeller2Id(Integer qkSeller2Id) {
        this.qkSeller2Id = qkSeller2Id;
    }

    public Integer getQkSeller3Id() {
        return qkSeller3Id;
    }

    public void setQkSeller3Id(Integer qkSeller3Id) {
        this.qkSeller3Id = qkSeller3Id;
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Integer getRelativeSpId() {
        return relativeSpId;
    }

    public void setRelativeSpId(Integer relativeSpId) {
        this.relativeSpId = relativeSpId;
    }

    public Integer getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }

    public Integer getRefMerchantId() {
        return refMerchantId;
    }

    public void setRefMerchantId(Integer refMerchantId) {
        this.refMerchantId = refMerchantId;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public BigDecimal getSimilarityDegree() {
        return similarityDegree;
    }

    public void setSimilarityDegree(BigDecimal similarityDegree) {
        this.similarityDegree = similarityDegree;
    }

    public Integer getActivateStatus() {
        return activateStatus;
    }

    public void setActivateStatus(Integer activateStatus) {
        this.activateStatus = activateStatus;
    }

    public Integer getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(Integer memberLevel) {
        this.memberLevel = memberLevel;
    }

    public Date getSynRateTime() {
        return synRateTime;
    }

    public void setSynRateTime(Date synRateTime) {
        this.synRateTime = synRateTime;
    }

    public Date getSynLimitTime() {
        return synLimitTime;
    }

    public void setSynLimitTime(Date synLimitTime) {
        this.synLimitTime = synLimitTime;
    }

    public Date getSynAccTime() {
        return synAccTime;
    }

    public void setSynAccTime(Date synAccTime) {
        this.synAccTime = synAccTime;
    }

    public Date getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }

    public Integer getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(Integer bankCardType) {
        this.bankCardType = bankCardType;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Date getWxInsertTime() {
        return wxInsertTime;
    }

    public void setWxInsertTime(Date wxInsertTime) {
        this.wxInsertTime = wxInsertTime;
    }

    public Date getAlipayInsertTime() {
        return alipayInsertTime;
    }

    public void setAlipayInsertTime(Date alipayInsertTime) {
        this.alipayInsertTime = alipayInsertTime;
    }

    public Date getIdCardNoStartTime() {
        return idCardNoStartTime;
    }

    public void setIdCardNoStartTime(Date idCardNoStartTime) {
        this.idCardNoStartTime = idCardNoStartTime;
    }

    public Date getIdCardNoEndTime() {
        return idCardNoEndTime;
    }

    public void setIdCardNoEndTime(Date idCardNoEndTime) {
        this.idCardNoEndTime = idCardNoEndTime;
    }

    public Integer getQrcSynStatus() {
        return qrcSynStatus;
    }

    public void setQrcSynStatus(Integer qrcSynStatus) {
        this.qrcSynStatus = qrcSynStatus;
    }

    public Date getCupMerInsertTime() {
        return cupMerInsertTime;
    }

    public void setCupMerInsertTime(Date cupMerInsertTime) {
        this.cupMerInsertTime = cupMerInsertTime;
    }
}