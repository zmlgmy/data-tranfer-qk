package com.tongdun.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class QkWithdrawTrans implements Serializable {
    private Long id;

    private Long qkSeller1Id;

    private Long qkSeller2Id;

    private Long qkSeller3Id;

    private Long qkSpId;

    private String qkSeller1UserCode;

    private String qkSeller2UserCode;

    private String qkSeller3UserCode;

    private String qkSpUserCode;

    private String merchantNo;

    private Object merchantUserUuid;

    private Object merchantAccountUuid;

    private Integer isSelfCardSett;

    private String bankCardNoCipher;

    private String bankCardNoMask;

    private String bankAccountName;

    private String bankName;

    private String bankSubName;

    private String bankChannelNo;

    private String clientNo;

    private String reqFlowNo;

    private String sourceReqFlowNo;

    private Integer walletType;

    private Integer riskDay;

    private BigDecimal withdrawAmount;

    private BigDecimal merchantWithdrawRate;

    private BigDecimal merchantWithdrawSingleFee;

    private BigDecimal merchantWithdrawFee;

    private BigDecimal merchantReceiveAmount;

    private BigDecimal qkSpWithdrawRate;

    private BigDecimal qkSpWithdrawSingleFee;

    private BigDecimal qkSpWithdrawFee;

    private BigDecimal qkSpWithdrawProfit;

    private BigDecimal qkSeller1WithdrawRate;

    private BigDecimal qkSeller1WithdrawSingleFee;

    private BigDecimal qkSeller1WithdrawFee;

    private BigDecimal qkSeller1WithdrawProfit;

    private BigDecimal qkSeller2WithdrawRate;

    private BigDecimal qkSeller2WithdrawSingleFee;

    private BigDecimal qkSeller2WithdrawFee;

    private BigDecimal qkSeller2WithdrawProfit;

    private BigDecimal qkSeller3WithdrawRate;

    private BigDecimal qkSeller3WithdrawSingleFee;

    private BigDecimal qkSeller3WithdrawFee;

    private BigDecimal qkSeller3WithdrawProfit;

    private Integer isQkSpPost;

    private Integer isQkSeller1Post;

    private Integer isQkSeller2Post;

    private Integer isQkSeller3Post;

    private Date qkSpPostTime;

    private Date qkSeller1PostTime;

    private Date qkSeller2PostTime;

    private Date qkSeller3PostTime;

    private Integer remitType;

    private Integer remitStatus;

    private Date remitReqTime;

    private Date remitFinishTime;

    private Date createTime;

    private Integer qkSpSettStatus;

    private Integer qkSeller1SettStatus;

    private Integer qkSeller2SettStatus;

    private Integer qkSeller3SettStatus;

    private Date qkSpSettTime;

    private Date qkSeller1SettTime;

    private Date qkSeller2SettTime;

    private Date qkSeller3SettTime;

    private Integer deleteStatus;

    private Integer transSourceType;

    private Object allSpRateInfo;

    private Integer isHsyCal;

    private Date hsyCalTime;

    private Integer transUserType;

    private String withdrawFailedReason;

    private Integer bankCardType;

    private Integer businessCheckFlag;

    private String remitChannelNo;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQkSeller1Id() {
        return qkSeller1Id;
    }

    public void setQkSeller1Id(Long qkSeller1Id) {
        this.qkSeller1Id = qkSeller1Id;
    }

    public Long getQkSeller2Id() {
        return qkSeller2Id;
    }

    public void setQkSeller2Id(Long qkSeller2Id) {
        this.qkSeller2Id = qkSeller2Id;
    }

    public Long getQkSeller3Id() {
        return qkSeller3Id;
    }

    public void setQkSeller3Id(Long qkSeller3Id) {
        this.qkSeller3Id = qkSeller3Id;
    }

    public Long getQkSpId() {
        return qkSpId;
    }

    public void setQkSpId(Long qkSpId) {
        this.qkSpId = qkSpId;
    }

    public String getQkSeller1UserCode() {
        return qkSeller1UserCode;
    }

    public void setQkSeller1UserCode(String qkSeller1UserCode) {
        this.qkSeller1UserCode = qkSeller1UserCode == null ? null : qkSeller1UserCode.trim();
    }

    public String getQkSeller2UserCode() {
        return qkSeller2UserCode;
    }

    public void setQkSeller2UserCode(String qkSeller2UserCode) {
        this.qkSeller2UserCode = qkSeller2UserCode == null ? null : qkSeller2UserCode.trim();
    }

    public String getQkSeller3UserCode() {
        return qkSeller3UserCode;
    }

    public void setQkSeller3UserCode(String qkSeller3UserCode) {
        this.qkSeller3UserCode = qkSeller3UserCode == null ? null : qkSeller3UserCode.trim();
    }

    public String getQkSpUserCode() {
        return qkSpUserCode;
    }

    public void setQkSpUserCode(String qkSpUserCode) {
        this.qkSpUserCode = qkSpUserCode == null ? null : qkSpUserCode.trim();
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    public Object getMerchantUserUuid() {
        return merchantUserUuid;
    }

    public void setMerchantUserUuid(Object merchantUserUuid) {
        this.merchantUserUuid = merchantUserUuid;
    }

    public Object getMerchantAccountUuid() {
        return merchantAccountUuid;
    }

    public void setMerchantAccountUuid(Object merchantAccountUuid) {
        this.merchantAccountUuid = merchantAccountUuid;
    }

    public Integer getIsSelfCardSett() {
        return isSelfCardSett;
    }

    public void setIsSelfCardSett(Integer isSelfCardSett) {
        this.isSelfCardSett = isSelfCardSett;
    }

    public String getBankCardNoCipher() {
        return bankCardNoCipher;
    }

    public void setBankCardNoCipher(String bankCardNoCipher) {
        this.bankCardNoCipher = bankCardNoCipher == null ? null : bankCardNoCipher.trim();
    }

    public String getBankCardNoMask() {
        return bankCardNoMask;
    }

    public void setBankCardNoMask(String bankCardNoMask) {
        this.bankCardNoMask = bankCardNoMask == null ? null : bankCardNoMask.trim();
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName == null ? null : bankAccountName.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getBankSubName() {
        return bankSubName;
    }

    public void setBankSubName(String bankSubName) {
        this.bankSubName = bankSubName == null ? null : bankSubName.trim();
    }

    public String getBankChannelNo() {
        return bankChannelNo;
    }

    public void setBankChannelNo(String bankChannelNo) {
        this.bankChannelNo = bankChannelNo == null ? null : bankChannelNo.trim();
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo == null ? null : clientNo.trim();
    }

    public String getReqFlowNo() {
        return reqFlowNo;
    }

    public void setReqFlowNo(String reqFlowNo) {
        this.reqFlowNo = reqFlowNo == null ? null : reqFlowNo.trim();
    }

    public String getSourceReqFlowNo() {
        return sourceReqFlowNo;
    }

    public void setSourceReqFlowNo(String sourceReqFlowNo) {
        this.sourceReqFlowNo = sourceReqFlowNo == null ? null : sourceReqFlowNo.trim();
    }

    public Integer getWalletType() {
        return walletType;
    }

    public void setWalletType(Integer walletType) {
        this.walletType = walletType;
    }

    public Integer getRiskDay() {
        return riskDay;
    }

    public void setRiskDay(Integer riskDay) {
        this.riskDay = riskDay;
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public BigDecimal getMerchantWithdrawRate() {
        return merchantWithdrawRate;
    }

    public void setMerchantWithdrawRate(BigDecimal merchantWithdrawRate) {
        this.merchantWithdrawRate = merchantWithdrawRate;
    }

    public BigDecimal getMerchantWithdrawSingleFee() {
        return merchantWithdrawSingleFee;
    }

    public void setMerchantWithdrawSingleFee(BigDecimal merchantWithdrawSingleFee) {
        this.merchantWithdrawSingleFee = merchantWithdrawSingleFee;
    }

    public BigDecimal getMerchantWithdrawFee() {
        return merchantWithdrawFee;
    }

    public void setMerchantWithdrawFee(BigDecimal merchantWithdrawFee) {
        this.merchantWithdrawFee = merchantWithdrawFee;
    }

    public BigDecimal getMerchantReceiveAmount() {
        return merchantReceiveAmount;
    }

    public void setMerchantReceiveAmount(BigDecimal merchantReceiveAmount) {
        this.merchantReceiveAmount = merchantReceiveAmount;
    }

    public BigDecimal getQkSpWithdrawRate() {
        return qkSpWithdrawRate;
    }

    public void setQkSpWithdrawRate(BigDecimal qkSpWithdrawRate) {
        this.qkSpWithdrawRate = qkSpWithdrawRate;
    }

    public BigDecimal getQkSpWithdrawSingleFee() {
        return qkSpWithdrawSingleFee;
    }

    public void setQkSpWithdrawSingleFee(BigDecimal qkSpWithdrawSingleFee) {
        this.qkSpWithdrawSingleFee = qkSpWithdrawSingleFee;
    }

    public BigDecimal getQkSpWithdrawFee() {
        return qkSpWithdrawFee;
    }

    public void setQkSpWithdrawFee(BigDecimal qkSpWithdrawFee) {
        this.qkSpWithdrawFee = qkSpWithdrawFee;
    }

    public BigDecimal getQkSpWithdrawProfit() {
        return qkSpWithdrawProfit;
    }

    public void setQkSpWithdrawProfit(BigDecimal qkSpWithdrawProfit) {
        this.qkSpWithdrawProfit = qkSpWithdrawProfit;
    }

    public BigDecimal getQkSeller1WithdrawRate() {
        return qkSeller1WithdrawRate;
    }

    public void setQkSeller1WithdrawRate(BigDecimal qkSeller1WithdrawRate) {
        this.qkSeller1WithdrawRate = qkSeller1WithdrawRate;
    }

    public BigDecimal getQkSeller1WithdrawSingleFee() {
        return qkSeller1WithdrawSingleFee;
    }

    public void setQkSeller1WithdrawSingleFee(BigDecimal qkSeller1WithdrawSingleFee) {
        this.qkSeller1WithdrawSingleFee = qkSeller1WithdrawSingleFee;
    }

    public BigDecimal getQkSeller1WithdrawFee() {
        return qkSeller1WithdrawFee;
    }

    public void setQkSeller1WithdrawFee(BigDecimal qkSeller1WithdrawFee) {
        this.qkSeller1WithdrawFee = qkSeller1WithdrawFee;
    }

    public BigDecimal getQkSeller1WithdrawProfit() {
        return qkSeller1WithdrawProfit;
    }

    public void setQkSeller1WithdrawProfit(BigDecimal qkSeller1WithdrawProfit) {
        this.qkSeller1WithdrawProfit = qkSeller1WithdrawProfit;
    }

    public BigDecimal getQkSeller2WithdrawRate() {
        return qkSeller2WithdrawRate;
    }

    public void setQkSeller2WithdrawRate(BigDecimal qkSeller2WithdrawRate) {
        this.qkSeller2WithdrawRate = qkSeller2WithdrawRate;
    }

    public BigDecimal getQkSeller2WithdrawSingleFee() {
        return qkSeller2WithdrawSingleFee;
    }

    public void setQkSeller2WithdrawSingleFee(BigDecimal qkSeller2WithdrawSingleFee) {
        this.qkSeller2WithdrawSingleFee = qkSeller2WithdrawSingleFee;
    }

    public BigDecimal getQkSeller2WithdrawFee() {
        return qkSeller2WithdrawFee;
    }

    public void setQkSeller2WithdrawFee(BigDecimal qkSeller2WithdrawFee) {
        this.qkSeller2WithdrawFee = qkSeller2WithdrawFee;
    }

    public BigDecimal getQkSeller2WithdrawProfit() {
        return qkSeller2WithdrawProfit;
    }

    public void setQkSeller2WithdrawProfit(BigDecimal qkSeller2WithdrawProfit) {
        this.qkSeller2WithdrawProfit = qkSeller2WithdrawProfit;
    }

    public BigDecimal getQkSeller3WithdrawRate() {
        return qkSeller3WithdrawRate;
    }

    public void setQkSeller3WithdrawRate(BigDecimal qkSeller3WithdrawRate) {
        this.qkSeller3WithdrawRate = qkSeller3WithdrawRate;
    }

    public BigDecimal getQkSeller3WithdrawSingleFee() {
        return qkSeller3WithdrawSingleFee;
    }

    public void setQkSeller3WithdrawSingleFee(BigDecimal qkSeller3WithdrawSingleFee) {
        this.qkSeller3WithdrawSingleFee = qkSeller3WithdrawSingleFee;
    }

    public BigDecimal getQkSeller3WithdrawFee() {
        return qkSeller3WithdrawFee;
    }

    public void setQkSeller3WithdrawFee(BigDecimal qkSeller3WithdrawFee) {
        this.qkSeller3WithdrawFee = qkSeller3WithdrawFee;
    }

    public BigDecimal getQkSeller3WithdrawProfit() {
        return qkSeller3WithdrawProfit;
    }

    public void setQkSeller3WithdrawProfit(BigDecimal qkSeller3WithdrawProfit) {
        this.qkSeller3WithdrawProfit = qkSeller3WithdrawProfit;
    }

    public Integer getIsQkSpPost() {
        return isQkSpPost;
    }

    public void setIsQkSpPost(Integer isQkSpPost) {
        this.isQkSpPost = isQkSpPost;
    }

    public Integer getIsQkSeller1Post() {
        return isQkSeller1Post;
    }

    public void setIsQkSeller1Post(Integer isQkSeller1Post) {
        this.isQkSeller1Post = isQkSeller1Post;
    }

    public Integer getIsQkSeller2Post() {
        return isQkSeller2Post;
    }

    public void setIsQkSeller2Post(Integer isQkSeller2Post) {
        this.isQkSeller2Post = isQkSeller2Post;
    }

    public Integer getIsQkSeller3Post() {
        return isQkSeller3Post;
    }

    public void setIsQkSeller3Post(Integer isQkSeller3Post) {
        this.isQkSeller3Post = isQkSeller3Post;
    }

    public Date getQkSpPostTime() {
        return qkSpPostTime;
    }

    public void setQkSpPostTime(Date qkSpPostTime) {
        this.qkSpPostTime = qkSpPostTime;
    }

    public Date getQkSeller1PostTime() {
        return qkSeller1PostTime;
    }

    public void setQkSeller1PostTime(Date qkSeller1PostTime) {
        this.qkSeller1PostTime = qkSeller1PostTime;
    }

    public Date getQkSeller2PostTime() {
        return qkSeller2PostTime;
    }

    public void setQkSeller2PostTime(Date qkSeller2PostTime) {
        this.qkSeller2PostTime = qkSeller2PostTime;
    }

    public Date getQkSeller3PostTime() {
        return qkSeller3PostTime;
    }

    public void setQkSeller3PostTime(Date qkSeller3PostTime) {
        this.qkSeller3PostTime = qkSeller3PostTime;
    }

    public Integer getRemitType() {
        return remitType;
    }

    public void setRemitType(Integer remitType) {
        this.remitType = remitType;
    }

    public Integer getRemitStatus() {
        return remitStatus;
    }

    public void setRemitStatus(Integer remitStatus) {
        this.remitStatus = remitStatus;
    }

    public Date getRemitReqTime() {
        return remitReqTime;
    }

    public void setRemitReqTime(Date remitReqTime) {
        this.remitReqTime = remitReqTime;
    }

    public Date getRemitFinishTime() {
        return remitFinishTime;
    }

    public void setRemitFinishTime(Date remitFinishTime) {
        this.remitFinishTime = remitFinishTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getQkSpSettStatus() {
        return qkSpSettStatus;
    }

    public void setQkSpSettStatus(Integer qkSpSettStatus) {
        this.qkSpSettStatus = qkSpSettStatus;
    }

    public Integer getQkSeller1SettStatus() {
        return qkSeller1SettStatus;
    }

    public void setQkSeller1SettStatus(Integer qkSeller1SettStatus) {
        this.qkSeller1SettStatus = qkSeller1SettStatus;
    }

    public Integer getQkSeller2SettStatus() {
        return qkSeller2SettStatus;
    }

    public void setQkSeller2SettStatus(Integer qkSeller2SettStatus) {
        this.qkSeller2SettStatus = qkSeller2SettStatus;
    }

    public Integer getQkSeller3SettStatus() {
        return qkSeller3SettStatus;
    }

    public void setQkSeller3SettStatus(Integer qkSeller3SettStatus) {
        this.qkSeller3SettStatus = qkSeller3SettStatus;
    }

    public Date getQkSpSettTime() {
        return qkSpSettTime;
    }

    public void setQkSpSettTime(Date qkSpSettTime) {
        this.qkSpSettTime = qkSpSettTime;
    }

    public Date getQkSeller1SettTime() {
        return qkSeller1SettTime;
    }

    public void setQkSeller1SettTime(Date qkSeller1SettTime) {
        this.qkSeller1SettTime = qkSeller1SettTime;
    }

    public Date getQkSeller2SettTime() {
        return qkSeller2SettTime;
    }

    public void setQkSeller2SettTime(Date qkSeller2SettTime) {
        this.qkSeller2SettTime = qkSeller2SettTime;
    }

    public Date getQkSeller3SettTime() {
        return qkSeller3SettTime;
    }

    public void setQkSeller3SettTime(Date qkSeller3SettTime) {
        this.qkSeller3SettTime = qkSeller3SettTime;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getTransSourceType() {
        return transSourceType;
    }

    public void setTransSourceType(Integer transSourceType) {
        this.transSourceType = transSourceType;
    }

    public Object getAllSpRateInfo() {
        return allSpRateInfo;
    }

    public void setAllSpRateInfo(Object allSpRateInfo) {
        this.allSpRateInfo = allSpRateInfo;
    }

    public Integer getIsHsyCal() {
        return isHsyCal;
    }

    public void setIsHsyCal(Integer isHsyCal) {
        this.isHsyCal = isHsyCal;
    }

    public Date getHsyCalTime() {
        return hsyCalTime;
    }

    public void setHsyCalTime(Date hsyCalTime) {
        this.hsyCalTime = hsyCalTime;
    }

    public Integer getTransUserType() {
        return transUserType;
    }

    public void setTransUserType(Integer transUserType) {
        this.transUserType = transUserType;
    }

    public String getWithdrawFailedReason() {
        return withdrawFailedReason;
    }

    public void setWithdrawFailedReason(String withdrawFailedReason) {
        this.withdrawFailedReason = withdrawFailedReason == null ? null : withdrawFailedReason.trim();
    }

    public Integer getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(Integer bankCardType) {
        this.bankCardType = bankCardType;
    }

    public Integer getBusinessCheckFlag() {
        return businessCheckFlag;
    }

    public void setBusinessCheckFlag(Integer businessCheckFlag) {
        this.businessCheckFlag = businessCheckFlag;
    }

    public String getRemitChannelNo() {
        return remitChannelNo;
    }

    public void setRemitChannelNo(String remitChannelNo) {
        this.remitChannelNo = remitChannelNo == null ? null : remitChannelNo.trim();
    }
}