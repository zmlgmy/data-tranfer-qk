package com.tongdun.data.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class PgQkUser implements Serializable {
    private Long id;

    private String userUuid;

    private String accountUuid;

    private String merchantNo;

    private String realName;

    private String companyName;

    private String companyAbbr;

    private Integer userClass;

    private String userGrade;

    private Integer isPost;

    private Date postTime;

    private String reqFlowNo;

    private Long qkServerProviderId;

    private String idCardNoCipher;

    private String idCardNoMask;

    private String bankAccountNoMask;

    private String bankAccountNoCipher;

    private String bankAccountName;

    private Integer bankAccountType;

    private String bankCode;

    private String bankProvince;

    private String bankProvinceCode;

    private String bankCity;

    private String bankCityCode;

    private String bankName;

    private String bankSubName;

    private String bankChannelNo;

    private String email;

    private Integer settType;

    private Integer settDay;

    private Integer isAgent;

    private Integer isSelfSettMode;

    private Date createTime;

    private Integer deleteStatus;

    private String mobileCipher;

    private String mobileMask;

    private Long qkSeller1Id;

    private Long qkSeller2Id;

    private Long qkSeller3Id;

    private String qkSeller1UserCode;

    private String qkSeller2UserCode;

    private String qkSeller3UserCode;

    private String qkServerProviderUserCode;

    private String sourceReqFlowNo;

    private Integer userLevel;

    private Long relativeSpId;

    private String relativeSpUserCode;

    private Object parentSpInfo;

    private Integer merchantType;

    private Long refMerchantId;

    private String refMerchantCode;

    private Integer checkStatus;

    private BigDecimal similarityDegree;

    private Integer activateStatus;

    private Integer memberLevel;

    private Date synRateTime;

    private Date synLimitTime;

    private Date synAccTime;

    private String wxChannelMerchantNo;

    private String wxInsertResStatus;

    private String wxInsertResCode;

    private String wxInsertResMessage;

    private String alipayChannelMerchantNo;

    private String alipayInsertResStatus;

    private String alipayInsertResCode;

    private String alipayInsertResMessage;

    private Date activateTime;

    private String unionpayQrcodeReportResCode;

    private String unionpayQrcodeReportResMsg;

    private String unionpayQrcodeChnMerNo;

    private String address;

    private String legalPersonName;

    private String legalPersonIdCardNoMask;

    private String legalPersonIdCardNoCipher;

    private String detailAddress;

    private String managementRange;

    private String businessNo;

    private String mccCode;

    private String comSiteweb;

    private String recordLicense;

    private String companyCity;

    private String companyCityCode;

    private String companyProvince;

    private String companyProvinceCode;

    private Integer bankCardType;

    private String unionpayQuickChnMerNo;

    private String unionpayMerReqFlowNo;

    private String idCardNoValidTime;

    private String idCardNoAddress;

    private Integer gender;

    private String cn;

    private String career;

    private Date wxInsertTime;

    private Date alipayInsertTime;

    private String alipayCategory;

    private String wxCategory;

    private Date idCardNoStartTime;

    private Date idCardNoEndTime;

    private String sendAddress;

    private String qrcMerChnMerNo;

    private String qrcMerReportResCode;

    private String qrcMerReportResMsg;

    private Integer qrcSynStatus;

    private Date cupMerInsertTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Object getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    public Object getAccountUuid() {
        return accountUuid;
    }

    public void setAccountUuid(String accountUuid) {
        this.accountUuid = accountUuid;
    }

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo == null ? null : merchantNo.trim();
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getCompanyAbbr() {
        return companyAbbr;
    }

    public void setCompanyAbbr(String companyAbbr) {
        this.companyAbbr = companyAbbr == null ? null : companyAbbr.trim();
    }

    public Integer getUserClass() {
        return userClass;
    }

    public void setUserClass(Integer userClass) {
        this.userClass = userClass;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade == null ? null : userGrade.trim();
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

    public String getReqFlowNo() {
        return reqFlowNo;
    }

    public void setReqFlowNo(String reqFlowNo) {
        this.reqFlowNo = reqFlowNo == null ? null : reqFlowNo.trim();
    }

    public Long getQkServerProviderId() {
        return qkServerProviderId;
    }

    public void setQkServerProviderId(Long qkServerProviderId) {
        this.qkServerProviderId = qkServerProviderId;
    }

    public String getIdCardNoCipher() {
        return idCardNoCipher;
    }

    public void setIdCardNoCipher(String idCardNoCipher) {
        this.idCardNoCipher = idCardNoCipher == null ? null : idCardNoCipher.trim();
    }

    public String getIdCardNoMask() {
        return idCardNoMask;
    }

    public void setIdCardNoMask(String idCardNoMask) {
        this.idCardNoMask = idCardNoMask == null ? null : idCardNoMask.trim();
    }

    public String getBankAccountNoMask() {
        return bankAccountNoMask;
    }

    public void setBankAccountNoMask(String bankAccountNoMask) {
        this.bankAccountNoMask = bankAccountNoMask == null ? null : bankAccountNoMask.trim();
    }

    public String getBankAccountNoCipher() {
        return bankAccountNoCipher;
    }

    public void setBankAccountNoCipher(String bankAccountNoCipher) {
        this.bankAccountNoCipher = bankAccountNoCipher == null ? null : bankAccountNoCipher.trim();
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName == null ? null : bankAccountName.trim();
    }

    public Integer getBankAccountType() {
        return bankAccountType;
    }

    public void setBankAccountType(Integer bankAccountType) {
        this.bankAccountType = bankAccountType;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince == null ? null : bankProvince.trim();
    }

    public String getBankProvinceCode() {
        return bankProvinceCode;
    }

    public void setBankProvinceCode(String bankProvinceCode) {
        this.bankProvinceCode = bankProvinceCode == null ? null : bankProvinceCode.trim();
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity == null ? null : bankCity.trim();
    }

    public String getBankCityCode() {
        return bankCityCode;
    }

    public void setBankCityCode(String bankCityCode) {
        this.bankCityCode = bankCityCode == null ? null : bankCityCode.trim();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
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

    public String getMobileCipher() {
        return mobileCipher;
    }

    public void setMobileCipher(String mobileCipher) {
        this.mobileCipher = mobileCipher == null ? null : mobileCipher.trim();
    }

    public String getMobileMask() {
        return mobileMask;
    }

    public void setMobileMask(String mobileMask) {
        this.mobileMask = mobileMask == null ? null : mobileMask.trim();
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

    public String getQkServerProviderUserCode() {
        return qkServerProviderUserCode;
    }

    public void setQkServerProviderUserCode(String qkServerProviderUserCode) {
        this.qkServerProviderUserCode = qkServerProviderUserCode == null ? null : qkServerProviderUserCode.trim();
    }

    public String getSourceReqFlowNo() {
        return sourceReqFlowNo;
    }

    public void setSourceReqFlowNo(String sourceReqFlowNo) {
        this.sourceReqFlowNo = sourceReqFlowNo == null ? null : sourceReqFlowNo.trim();
    }

    public Integer getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Integer userLevel) {
        this.userLevel = userLevel;
    }

    public Long getRelativeSpId() {
        return relativeSpId;
    }

    public void setRelativeSpId(Long relativeSpId) {
        this.relativeSpId = relativeSpId;
    }

    public String getRelativeSpUserCode() {
        return relativeSpUserCode;
    }

    public void setRelativeSpUserCode(String relativeSpUserCode) {
        this.relativeSpUserCode = relativeSpUserCode == null ? null : relativeSpUserCode.trim();
    }

    public Object getParentSpInfo() {
        return parentSpInfo;
    }

    public void setParentSpInfo(Object parentSpInfo) {
        this.parentSpInfo = parentSpInfo;
    }

    public Integer getMerchantType() {
        return merchantType;
    }

    public void setMerchantType(Integer merchantType) {
        this.merchantType = merchantType;
    }

    public Long getRefMerchantId() {
        return refMerchantId;
    }

    public void setRefMerchantId(Long refMerchantId) {
        this.refMerchantId = refMerchantId;
    }

    public String getRefMerchantCode() {
        return refMerchantCode;
    }

    public void setRefMerchantCode(String refMerchantCode) {
        this.refMerchantCode = refMerchantCode == null ? null : refMerchantCode.trim();
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

    public String getWxChannelMerchantNo() {
        return wxChannelMerchantNo;
    }

    public void setWxChannelMerchantNo(String wxChannelMerchantNo) {
        this.wxChannelMerchantNo = wxChannelMerchantNo == null ? null : wxChannelMerchantNo.trim();
    }

    public String getWxInsertResStatus() {
        return wxInsertResStatus;
    }

    public void setWxInsertResStatus(String wxInsertResStatus) {
        this.wxInsertResStatus = wxInsertResStatus == null ? null : wxInsertResStatus.trim();
    }

    public String getWxInsertResCode() {
        return wxInsertResCode;
    }

    public void setWxInsertResCode(String wxInsertResCode) {
        this.wxInsertResCode = wxInsertResCode == null ? null : wxInsertResCode.trim();
    }

    public String getWxInsertResMessage() {
        return wxInsertResMessage;
    }

    public void setWxInsertResMessage(String wxInsertResMessage) {
        this.wxInsertResMessage = wxInsertResMessage == null ? null : wxInsertResMessage.trim();
    }

    public String getAlipayChannelMerchantNo() {
        return alipayChannelMerchantNo;
    }

    public void setAlipayChannelMerchantNo(String alipayChannelMerchantNo) {
        this.alipayChannelMerchantNo = alipayChannelMerchantNo == null ? null : alipayChannelMerchantNo.trim();
    }

    public String getAlipayInsertResStatus() {
        return alipayInsertResStatus;
    }

    public void setAlipayInsertResStatus(String alipayInsertResStatus) {
        this.alipayInsertResStatus = alipayInsertResStatus == null ? null : alipayInsertResStatus.trim();
    }

    public String getAlipayInsertResCode() {
        return alipayInsertResCode;
    }

    public void setAlipayInsertResCode(String alipayInsertResCode) {
        this.alipayInsertResCode = alipayInsertResCode == null ? null : alipayInsertResCode.trim();
    }

    public String getAlipayInsertResMessage() {
        return alipayInsertResMessage;
    }

    public void setAlipayInsertResMessage(String alipayInsertResMessage) {
        this.alipayInsertResMessage = alipayInsertResMessage == null ? null : alipayInsertResMessage.trim();
    }

    public Date getActivateTime() {
        return activateTime;
    }

    public void setActivateTime(Date activateTime) {
        this.activateTime = activateTime;
    }

    public String getUnionpayQrcodeReportResCode() {
        return unionpayQrcodeReportResCode;
    }

    public void setUnionpayQrcodeReportResCode(String unionpayQrcodeReportResCode) {
        this.unionpayQrcodeReportResCode = unionpayQrcodeReportResCode == null ? null : unionpayQrcodeReportResCode.trim();
    }

    public String getUnionpayQrcodeReportResMsg() {
        return unionpayQrcodeReportResMsg;
    }

    public void setUnionpayQrcodeReportResMsg(String unionpayQrcodeReportResMsg) {
        this.unionpayQrcodeReportResMsg = unionpayQrcodeReportResMsg == null ? null : unionpayQrcodeReportResMsg.trim();
    }

    public String getUnionpayQrcodeChnMerNo() {
        return unionpayQrcodeChnMerNo;
    }

    public void setUnionpayQrcodeChnMerNo(String unionpayQrcodeChnMerNo) {
        this.unionpayQrcodeChnMerNo = unionpayQrcodeChnMerNo == null ? null : unionpayQrcodeChnMerNo.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getLegalPersonName() {
        return legalPersonName;
    }

    public void setLegalPersonName(String legalPersonName) {
        this.legalPersonName = legalPersonName == null ? null : legalPersonName.trim();
    }

    public String getLegalPersonIdCardNoMask() {
        return legalPersonIdCardNoMask;
    }

    public void setLegalPersonIdCardNoMask(String legalPersonIdCardNoMask) {
        this.legalPersonIdCardNoMask = legalPersonIdCardNoMask == null ? null : legalPersonIdCardNoMask.trim();
    }

    public String getLegalPersonIdCardNoCipher() {
        return legalPersonIdCardNoCipher;
    }

    public void setLegalPersonIdCardNoCipher(String legalPersonIdCardNoCipher) {
        this.legalPersonIdCardNoCipher = legalPersonIdCardNoCipher == null ? null : legalPersonIdCardNoCipher.trim();
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress == null ? null : detailAddress.trim();
    }

    public String getManagementRange() {
        return managementRange;
    }

    public void setManagementRange(String managementRange) {
        this.managementRange = managementRange == null ? null : managementRange.trim();
    }

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo == null ? null : businessNo.trim();
    }

    public String getMccCode() {
        return mccCode;
    }

    public void setMccCode(String mccCode) {
        this.mccCode = mccCode == null ? null : mccCode.trim();
    }

    public String getComSiteweb() {
        return comSiteweb;
    }

    public void setComSiteweb(String comSiteweb) {
        this.comSiteweb = comSiteweb == null ? null : comSiteweb.trim();
    }

    public String getRecordLicense() {
        return recordLicense;
    }

    public void setRecordLicense(String recordLicense) {
        this.recordLicense = recordLicense == null ? null : recordLicense.trim();
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity == null ? null : companyCity.trim();
    }

    public String getCompanyCityCode() {
        return companyCityCode;
    }

    public void setCompanyCityCode(String companyCityCode) {
        this.companyCityCode = companyCityCode == null ? null : companyCityCode.trim();
    }

    public String getCompanyProvince() {
        return companyProvince;
    }

    public void setCompanyProvince(String companyProvince) {
        this.companyProvince = companyProvince == null ? null : companyProvince.trim();
    }

    public String getCompanyProvinceCode() {
        return companyProvinceCode;
    }

    public void setCompanyProvinceCode(String companyProvinceCode) {
        this.companyProvinceCode = companyProvinceCode == null ? null : companyProvinceCode.trim();
    }

    public Integer getBankCardType() {
        return bankCardType;
    }

    public void setBankCardType(Integer bankCardType) {
        this.bankCardType = bankCardType;
    }

    public String getUnionpayQuickChnMerNo() {
        return unionpayQuickChnMerNo;
    }

    public void setUnionpayQuickChnMerNo(String unionpayQuickChnMerNo) {
        this.unionpayQuickChnMerNo = unionpayQuickChnMerNo == null ? null : unionpayQuickChnMerNo.trim();
    }

    public String getUnionpayMerReqFlowNo() {
        return unionpayMerReqFlowNo;
    }

    public void setUnionpayMerReqFlowNo(String unionpayMerReqFlowNo) {
        this.unionpayMerReqFlowNo = unionpayMerReqFlowNo == null ? null : unionpayMerReqFlowNo.trim();
    }

    public String getIdCardNoValidTime() {
        return idCardNoValidTime;
    }

    public void setIdCardNoValidTime(String idCardNoValidTime) {
        this.idCardNoValidTime = idCardNoValidTime == null ? null : idCardNoValidTime.trim();
    }

    public String getIdCardNoAddress() {
        return idCardNoAddress;
    }

    public void setIdCardNoAddress(String idCardNoAddress) {
        this.idCardNoAddress = idCardNoAddress == null ? null : idCardNoAddress.trim();
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn == null ? null : cn.trim();
    }

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
        this.career = career == null ? null : career.trim();
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

    public String getAlipayCategory() {
        return alipayCategory;
    }

    public void setAlipayCategory(String alipayCategory) {
        this.alipayCategory = alipayCategory == null ? null : alipayCategory.trim();
    }

    public String getWxCategory() {
        return wxCategory;
    }

    public void setWxCategory(String wxCategory) {
        this.wxCategory = wxCategory == null ? null : wxCategory.trim();
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

    public String getSendAddress() {
        return sendAddress;
    }

    public void setSendAddress(String sendAddress) {
        this.sendAddress = sendAddress == null ? null : sendAddress.trim();
    }

    public String getQrcMerChnMerNo() {
        return qrcMerChnMerNo;
    }

    public void setQrcMerChnMerNo(String qrcMerChnMerNo) {
        this.qrcMerChnMerNo = qrcMerChnMerNo == null ? null : qrcMerChnMerNo.trim();
    }

    public String getQrcMerReportResCode() {
        return qrcMerReportResCode;
    }

    public void setQrcMerReportResCode(String qrcMerReportResCode) {
        this.qrcMerReportResCode = qrcMerReportResCode == null ? null : qrcMerReportResCode.trim();
    }

    public String getQrcMerReportResMsg() {
        return qrcMerReportResMsg;
    }

    public void setQrcMerReportResMsg(String qrcMerReportResMsg) {
        this.qrcMerReportResMsg = qrcMerReportResMsg == null ? null : qrcMerReportResMsg.trim();
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