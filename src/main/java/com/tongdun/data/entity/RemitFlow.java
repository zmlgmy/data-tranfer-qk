package com.tongdun.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class RemitFlow implements Serializable {
    private Long id;

    private Object remitFlowUuid;

    private Object walletFlowUuid;

    private Long remitChannelId;

    private String remitChannelNo;

    private Long remitBatchId;

    private String remitBatchNo;

    private String remitFlowNo;

    private String remitChannelBatchNo;

    private String remitChannelFlowNo;

    private BigDecimal remitAmount;

    private Integer remitStatus;

    private Integer remitType;

    private Integer sourceType;

    private Date createTime;

    private Object createUserUuid;

    private String createUserName;

    private String remark;

    private Long accountId;

    private Object accountUuid;

    private Object accountWalletInfo;

    private Integer accountType;

    private String accountNo;

    private String accountName;

    private String bankCode;

    private String bankName;

    private String bankSubName;

    private String bankChannelNo;

    private String bankProvince;

    private String bankCity;

    private String bankArea;

    private String resCode;

    private String resCodeMemo;

    private Date resHandleTime;

    private Integer deleteStatus;

    private Integer refundType;

    private Integer recStatus;

    private String recCode;

    private String recCodeMemo;

    private Date recHandleTime;

    private String transStatus;

    private String bankResCode;

    private String bankResMessage;

    private Integer handleStatus;

    private BigDecimal withdrawDepositRate;

    private BigDecimal withdrawDepositSingleFee;

    private BigDecimal withdrawDepositFee;

    private BigDecimal receiveAmount;

    private Integer riskDay;

    private Integer isDayConsume;

    private Integer businessType;

    private Integer walletType;

    private String origBatchNo;

    private String origFlowNo;

    private String accountNoCipher;

    private String accountNoMask;

    private Integer isCipher;

    private Long keyId;

    private Integer isSendReexchangeEmail;

    private Integer merchantType;

    private String idCardNo;

    private String traceNo;

    private String traceTime;

    private String clientNo;

    private String recReason;

    private Date reqHandleTime;

    private Integer bankCardType;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getRemitFlowUuid() {
        return remitFlowUuid;
    }

    public void setRemitFlowUuid(Object remitFlowUuid) {
        this.remitFlowUuid = remitFlowUuid;
    }

    public Object getWalletFlowUuid() {
        return walletFlowUuid;
    }

    public void setWalletFlowUuid(Object walletFlowUuid) {
        this.walletFlowUuid = walletFlowUuid;
    }

    public Long getRemitChannelId() {
        return remitChannelId;
    }

    public void setRemitChannelId(Long remitChannelId) {
        this.remitChannelId = remitChannelId;
    }

    public String getRemitChannelNo() {
        return remitChannelNo;
    }

    public void setRemitChannelNo(String remitChannelNo) {
        this.remitChannelNo = remitChannelNo == null ? null : remitChannelNo.trim();
    }

    public Long getRemitBatchId() {
        return remitBatchId;
    }

    public void setRemitBatchId(Long remitBatchId) {
        this.remitBatchId = remitBatchId;
    }

    public String getRemitBatchNo() {
        return remitBatchNo;
    }

    public void setRemitBatchNo(String remitBatchNo) {
        this.remitBatchNo = remitBatchNo == null ? null : remitBatchNo.trim();
    }

    public String getRemitFlowNo() {
        return remitFlowNo;
    }

    public void setRemitFlowNo(String remitFlowNo) {
        this.remitFlowNo = remitFlowNo == null ? null : remitFlowNo.trim();
    }

    public String getRemitChannelBatchNo() {
        return remitChannelBatchNo;
    }

    public void setRemitChannelBatchNo(String remitChannelBatchNo) {
        this.remitChannelBatchNo = remitChannelBatchNo == null ? null : remitChannelBatchNo.trim();
    }

    public String getRemitChannelFlowNo() {
        return remitChannelFlowNo;
    }

    public void setRemitChannelFlowNo(String remitChannelFlowNo) {
        this.remitChannelFlowNo = remitChannelFlowNo == null ? null : remitChannelFlowNo.trim();
    }

    public BigDecimal getRemitAmount() {
        return remitAmount;
    }

    public void setRemitAmount(BigDecimal remitAmount) {
        this.remitAmount = remitAmount;
    }

    public Integer getRemitStatus() {
        return remitStatus;
    }

    public void setRemitStatus(Integer remitStatus) {
        this.remitStatus = remitStatus;
    }

    public Integer getRemitType() {
        return remitType;
    }

    public void setRemitType(Integer remitType) {
        this.remitType = remitType;
    }

    public Integer getSourceType() {
        return sourceType;
    }

    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Object getCreateUserUuid() {
        return createUserUuid;
    }

    public void setCreateUserUuid(Object createUserUuid) {
        this.createUserUuid = createUserUuid;
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Object getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(Object accountUuid) {
        this.accountUuid = accountUuid;
    }

    public Object getAccountWalletInfo() {
        return accountWalletInfo;
    }

    public void setAccountWalletInfo(Object accountWalletInfo) {
        this.accountWalletInfo = accountWalletInfo;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo == null ? null : accountNo.trim();
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
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

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince == null ? null : bankProvince.trim();
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity == null ? null : bankCity.trim();
    }

    public String getBankArea() {
        return bankArea;
    }

    public void setBankArea(String bankArea) {
        this.bankArea = bankArea == null ? null : bankArea.trim();
    }

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode == null ? null : resCode.trim();
    }

    public String getResCodeMemo() {
        return resCodeMemo;
    }

    public void setResCodeMemo(String resCodeMemo) {
        this.resCodeMemo = resCodeMemo == null ? null : resCodeMemo.trim();
    }

    public Date getResHandleTime() {
        return resHandleTime;
    }

    public void setResHandleTime(Date resHandleTime) {
        this.resHandleTime = resHandleTime;
    }

    public Integer getDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(Integer deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    public Integer getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(Integer recStatus) {
        this.recStatus = recStatus;
    }

    public String getRecCode() {
        return recCode;
    }

    public void setRecCode(String recCode) {
        this.recCode = recCode == null ? null : recCode.trim();
    }

    public String getRecCodeMemo() {
        return recCodeMemo;
    }

    public void setRecCodeMemo(String recCodeMemo) {
        this.recCodeMemo = recCodeMemo == null ? null : recCodeMemo.trim();
    }

    public Date getRecHandleTime() {
        return recHandleTime;
    }

    public void setRecHandleTime(Date recHandleTime) {
        this.recHandleTime = recHandleTime;
    }

    public String getTransStatus() {
        return transStatus;
    }

    public void setTransStatus(String transStatus) {
        this.transStatus = transStatus == null ? null : transStatus.trim();
    }

    public String getBankResCode() {
        return bankResCode;
    }

    public void setBankResCode(String bankResCode) {
        this.bankResCode = bankResCode == null ? null : bankResCode.trim();
    }

    public String getBankResMessage() {
        return bankResMessage;
    }

    public void setBankResMessage(String bankResMessage) {
        this.bankResMessage = bankResMessage == null ? null : bankResMessage.trim();
    }

    public Integer getHandleStatus() {
        return handleStatus;
    }

    public void setHandleStatus(Integer handleStatus) {
        this.handleStatus = handleStatus;
    }

    public BigDecimal getWithdrawDepositRate() {
        return withdrawDepositRate;
    }

    public void setWithdrawDepositRate(BigDecimal withdrawDepositRate) {
        this.withdrawDepositRate = withdrawDepositRate;
    }

    public BigDecimal getWithdrawDepositSingleFee() {
        return withdrawDepositSingleFee;
    }

    public void setWithdrawDepositSingleFee(BigDecimal withdrawDepositSingleFee) {
        this.withdrawDepositSingleFee = withdrawDepositSingleFee;
    }

    public BigDecimal getWithdrawDepositFee() {
        return withdrawDepositFee;
    }

    public void setWithdrawDepositFee(BigDecimal withdrawDepositFee) {
        this.withdrawDepositFee = withdrawDepositFee;
    }

    public BigDecimal getReceiveAmount() {
        return receiveAmount;
    }

    public void setReceiveAmount(BigDecimal receiveAmount) {
        this.receiveAmount = receiveAmount;
    }

    public Integer getRiskDay() {
        return riskDay;
    }

    public void setRiskDay(Integer riskDay) {
        this.riskDay = riskDay;
    }

    public Integer getIsDayConsume() {
        return isDayConsume;
    }

    public void setIsDayConsume(Integer isDayConsume) {
        this.isDayConsume = isDayConsume;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getWalletType() {
        return walletType;
    }

    public void setWalletType(Integer walletType) {
        this.walletType = walletType;
    }

    public String getOrigBatchNo() {
        return origBatchNo;
    }

    public void setOrigBatchNo(String origBatchNo) {
        this.origBatchNo = origBatchNo == null ? null : origBatchNo.trim();
    }

    public String getOrigFlowNo() {
        return origFlowNo;
    }

    public void setOrigFlowNo(String origFlowNo) {
        this.origFlowNo = origFlowNo == null ? null : origFlowNo.trim();
    }

    public String getAccountNoCipher() {
        return accountNoCipher;
    }

    public void setAccountNoCipher(String accountNoCipher) {
        this.accountNoCipher = accountNoCipher == null ? null : accountNoCipher.trim();
    }

    public String getAccountNoMask() {
        return accountNoMask;
    }

    public void setAccountNoMask(String accountNoMask) {
        this.accountNoMask = accountNoMask == null ? null : accountNoMask.trim();
    }

    public Integer getIsCipher() {
        return isCipher;
    }

    public void setIsCipher(Integer isCipher) {
        this.isCipher = isCipher;
    }

    public Long getKeyId() {
        return keyId;
    }

    public void setKeyId(Long keyId) {
        this.keyId = keyId;
    }

    public Integer getIsSendReexchangeEmail() {
        return isSendReexchangeEmail;
    }

    public void setIsSendReexchangeEmail(Integer isSendReexchangeEmail) {
        this.isSendReexchangeEmail = isSendReexchangeEmail;
    }

    public Integer getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo == null ? null : idCardNo.trim();
    }

    public String getTraceNo() {
        return traceNo;
    }

    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo == null ? null : traceNo.trim();
    }

    public String getTraceTime() {
        return traceTime;
    }

    public void setTraceTime(String traceTime) {
        this.traceTime = traceTime == null ? null : traceTime.trim();
    }

    public String getClientNo() {
        return clientNo;
    }

    public void setClientNo(String clientNo) {
        this.clientNo = clientNo == null ? null : clientNo.trim();
    }

    public String getRecReason() {
        return recReason;
    }

    public void setRecReason(String recReason) {
        this.recReason = recReason == null ? null : recReason.trim();
    }

    public Date getReqHandleTime() {
        return reqHandleTime;
    }

    public void setReqHandleTime(Date reqHandleTime) {
        this.reqHandleTime = reqHandleTime;
    }

    public Integer getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(Integer bankCardType) {
        this.bankCardType = bankCardType;
    }
}