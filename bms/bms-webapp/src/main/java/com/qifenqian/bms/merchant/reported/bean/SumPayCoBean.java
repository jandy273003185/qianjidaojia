package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class SumPayCoBean implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2976964389401139756L;
	//
	private String channelNo;
	//商户号
	private String merchantCode;
	//商户名称
	private String custName;
	//营业执照号 
	private String businessLicense;
	//商户详细地址
	private String cprRegAddr;
	//商户地址省
	private String province;
	//商户地址市
	private String city;
	//商户地址区
	private String country;
	//法人姓名
	private String interName;
	//法人证件号
	private String certifyNo;
	//对公对私标识
	private String perEntFlag;
	//开户总行联行号
	private String bankCode;
	//开户支行号
	private String interBankCode;
	//开户支行名称
	private String interBankName;
	//预留电话
	private String mobile;
	//联系人名称
	private String attentionName;
	//联系人电话
	private String attentionMobile;
	//联系人邮箱
	private String attentionEmail;
	//商户登录邮箱号
	private String merchantLoginEmail;
	//商户登录手机号
	private String merchantLoginMobile;
	//客服号码
	private String customerPhone;
	//商户级别
	private String merchantLev;
	//企业代码类型
	private String merchantCodeType;
	//法人手机号
	private String representativePhone;
	//联系地址
	private String representativeAddr;
	//法人证件类型
	private String sumpayIdCardLegalType;
	//商户类型
	private String sumpayProfessionType;
	//结算周期
	private String sumpayCycleDays;
	//结算账户开户所在城市
	private String residentCity;
	//业务类型
	private String sumpayMerSumType;
	//结算账号名称
	private String accountNm;
	//结算账号
	private String accountNo;
	//开户行号
	private String sumpayBankNmType;
	
	//商户地址省
	private String provinceAdd;
	//商户地址市
	private String cityAdd;
	//商户地址区
	private String countryAdd;
	//渠道返回商户号
	private String outMerchantCode;
	
	//营业执照路径
	private String licensePath;
	//身份证正面照
	private String identityImagePath0;
	//身份证反面照
	private String identityImagePath1;
	//开户许可证
	private String openPath;
	//银行卡照
	private String bankCardPath;
	//门头照
	private String doorPhotoPath;
	//店内照
	private String shopInteriorPath;
	
	
	
	
	public String getLicensePath() {
		return licensePath;
	}
	public void setLicensePath(String licensePath) {
		this.licensePath = licensePath;
	}
	public String getIdentityImagePath0() {
		return identityImagePath0;
	}
	public void setIdentityImagePath0(String identityImagePath0) {
		this.identityImagePath0 = identityImagePath0;
	}
	public String getIdentityImagePath1() {
		return identityImagePath1;
	}
	public void setIdentityImagePath1(String identityImagePath1) {
		this.identityImagePath1 = identityImagePath1;
	}
	public String getOpenPath() {
		return openPath;
	}
	public void setOpenPath(String openPath) {
		this.openPath = openPath;
	}
	public String getBankCardPath() {
		return bankCardPath;
	}
	public void setBankCardPath(String bankCardPath) {
		this.bankCardPath = bankCardPath;
	}
	public String getDoorPhotoPath() {
		return doorPhotoPath;
	}
	public void setDoorPhotoPath(String doorPhotoPath) {
		this.doorPhotoPath = doorPhotoPath;
	}
	public String getShopInteriorPath() {
		return shopInteriorPath;
	}
	public void setShopInteriorPath(String shopInteriorPath) {
		this.shopInteriorPath = shopInteriorPath;
	}
	public String getOutMerchantCode() {
		return outMerchantCode;
	}
	public void setOutMerchantCode(String outMerchantCode) {
		this.outMerchantCode = outMerchantCode;
	}
	public String getProvinceAdd() {
		return provinceAdd;
	}
	public void setProvinceAdd(String provinceAdd) {
		this.provinceAdd = provinceAdd;
	}
	public String getCityAdd() {
		return cityAdd;
	}
	public void setCityAdd(String cityAdd) {
		this.cityAdd = cityAdd;
	}
	public String getCountryAdd() {
		return countryAdd;
	}
	public void setCountryAdd(String countryAdd) {
		this.countryAdd = countryAdd;
	}
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
	public String getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	public String getCprRegAddr() {
		return cprRegAddr;
	}
	public void setCprRegAddr(String cprRegAddr) {
		this.cprRegAddr = cprRegAddr;
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
	public String getInterName() {
		return interName;
	}
	public void setInterName(String interName) {
		this.interName = interName;
	}
	public String getCertifyNo() {
		return certifyNo;
	}
	public void setCertifyNo(String certifyNo) {
		this.certifyNo = certifyNo;
	}
	public String getPerEntFlag() {
		return perEntFlag;
	}
	public void setPerEntFlag(String perEntFlag) {
		this.perEntFlag = perEntFlag;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getInterBankCode() {
		return interBankCode;
	}
	public void setInterBankCode(String interBankCode) {
		this.interBankCode = interBankCode;
	}
	public String getInterBankName() {
		return interBankName;
	}
	public void setInterBankName(String interBankName) {
		this.interBankName = interBankName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAttentionName() {
		return attentionName;
	}
	public void setAttentionName(String attentionName) {
		this.attentionName = attentionName;
	}
	public String getAttentionMobile() {
		return attentionMobile;
	}
	public void setAttentionMobile(String attentionMobile) {
		this.attentionMobile = attentionMobile;
	}
	public String getAttentionEmail() {
		return attentionEmail;
	}
	public void setAttentionEmail(String attentionEmail) {
		this.attentionEmail = attentionEmail;
	}
	public String getMerchantLoginEmail() {
		return merchantLoginEmail;
	}
	public void setMerchantLoginEmail(String merchantLoginEmail) {
		this.merchantLoginEmail = merchantLoginEmail;
	}
	public String getMerchantLoginMobile() {
		return merchantLoginMobile;
	}
	public void setMerchantLoginMobile(String merchantLoginMobile) {
		this.merchantLoginMobile = merchantLoginMobile;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getMerchantLev() {
		return merchantLev;
	}
	public void setMerchantLev(String merchantLev) {
		this.merchantLev = merchantLev;
	}
	public String getMerchantCodeType() {
		return merchantCodeType;
	}
	public void setMerchantCodeType(String merchantCodeType) {
		this.merchantCodeType = merchantCodeType;
	}
	public String getRepresentativePhone() {
		return representativePhone;
	}
	public void setRepresentativePhone(String representativePhone) {
		this.representativePhone = representativePhone;
	}
	public String getRepresentativeAddr() {
		return representativeAddr;
	}
	public void setRepresentativeAddr(String representativeAddr) {
		this.representativeAddr = representativeAddr;
	}
	public String getSumpayIdCardLegalType() {
		return sumpayIdCardLegalType;
	}
	public void setSumpayIdCardLegalType(String sumpayIdCardLegalType) {
		this.sumpayIdCardLegalType = sumpayIdCardLegalType;
	}
	public String getSumpayProfessionType() {
		return sumpayProfessionType;
	}
	public void setSumpayProfessionType(String sumpayProfessionType) {
		this.sumpayProfessionType = sumpayProfessionType;
	}
	public String getSumpayCycleDays() {
		return sumpayCycleDays;
	}
	public void setSumpayCycleDays(String sumpayCycleDays) {
		this.sumpayCycleDays = sumpayCycleDays;
	}
	public String getResidentCity() {
		return residentCity;
	}
	public void setResidentCity(String residentCity) {
		this.residentCity = residentCity;
	}
	public String getSumpayMerSumType() {
		return sumpayMerSumType;
	}
	public void setSumpayMerSumType(String sumpayMerSumType) {
		this.sumpayMerSumType = sumpayMerSumType;
	}
	public String getAccountNm() {
		return accountNm;
	}
	public void setAccountNm(String accountNm) {
		this.accountNm = accountNm;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getSumpayBankNmType() {
		return sumpayBankNmType;
	}
	public void setSumpayBankNmType(String sumpayBankNmType) {
		this.sumpayBankNmType = sumpayBankNmType;
	}
	
	
	
	
	
	
	
	
}