package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class BestPayCoBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3865790527298028494L;

	//
	private String channelNo;
	//商户号
	private String merchantCode;
	//商户名称
	private String custName;
	//商户申请类型
	private String merchantApplyType;
	//证件类型
	private String certificateType;
	//工商注册号
	private String registrationNumber;
	//翼支付商户信息MccCode
	private String industryCode;
	//经营范围
	private String businessScope;
	//经营起始时间
	//经营结束时间
	
	//联系人手机号
	private String mobileNo;
	//地址
	private String province;
	
	private String city;
	
	private String country;
	//详细地址
	private String cprRegAddr;
	//创建人
	private String createdBy;
	//银行卡号
	private String bankCardNo;
	//开户行
	private String bankCode;
	//开户银行名称
	private String interBankName;
	//银行账户名
	private String bankAcctName;
	//对公对私标识
	private String perEntFlag;
	//银行账户类型
	private String bankCardType;
	//联系人姓名
	private String interName;
	//法人证件类型
	private String representativeCertType;
	//证件号
	private String certifyNo;
	//证件有效期
	private String validDate;
	//户口所在地
	private String residentCity;
	
	/** 身份证正面图片 */
	private String identityCardFrontPic;
	
	/** 身份证反面图片 */
	private String identityCardReversePic;
	
	/** 营业许可证图片 */
	private String licensePic;
	
	/** 开户许可证图片 */
	private String openPic;

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getMerchantApplyType() {
		return merchantApplyType;
	}

	public void setMerchantApplyType(String merchantApplyType) {
		this.merchantApplyType = merchantApplyType;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	
	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCprRegAddr() {
		return cprRegAddr;
	}

	public void setCprRegAddr(String cprRegAddr) {
		this.cprRegAddr = cprRegAddr;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	
	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getInterBankName() {
		return interBankName;
	}

	public void setInterBankName(String interBankName) {
		this.interBankName = interBankName;
	}

	public String getBankAcctName() {
		return bankAcctName;
	}

	public void setBankAcctName(String bankAcctName) {
		this.bankAcctName = bankAcctName;
	}

	public String getPerEntFlag() {
		return perEntFlag;
	}

	public void setPerEntFlag(String perEntFlag) {
		this.perEntFlag = perEntFlag;
	}

	public String getBankCardType() {
		return bankCardType;
	}

	public void setBankCardType(String bankCardType) {
		this.bankCardType = bankCardType;
	}

	public String getInterName() {
		return interName;
	}

	public void setInterName(String interName) {
		this.interName = interName;
	}

	public String getRepresentativeCertType() {
		return representativeCertType;
	}

	public void setRepresentativeCertType(String representativeCertType) {
		this.representativeCertType = representativeCertType;
	}

	public String getCertifyNo() {
		return certifyNo;
	}

	public void setCertifyNo(String certifyNo) {
		this.certifyNo = certifyNo;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getResidentCity() {
		return residentCity;
	}

	public void setResidentCity(String residentCity) {
		this.residentCity = residentCity;
	}

	public String getIdentityCardFrontPic() {
		return identityCardFrontPic;
	}

	public void setIdentityCardFrontPic(String identityCardFrontPic) {
		this.identityCardFrontPic = identityCardFrontPic;
	}

	public String getIdentityCardReversePic() {
		return identityCardReversePic;
	}

	public void setIdentityCardReversePic(String identityCardReversePic) {
		this.identityCardReversePic = identityCardReversePic;
	}

	public String getLicensePic() {
		return licensePic;
	}

	public void setLicensePic(String licensePic) {
		this.licensePic = licensePic;
	}


	public String getOpenPic() {
		return openPic;
	}

	public void setOpenPic(String openPic) {
		this.openPic = openPic;
	}
	
	
	
	
}
