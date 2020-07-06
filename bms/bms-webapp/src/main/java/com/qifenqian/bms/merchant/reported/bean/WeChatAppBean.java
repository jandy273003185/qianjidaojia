package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class WeChatAppBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8898489885772662047L;
	//渠道
	private String channelNo;
	//批次号
	private String patchNo;
	//商户号
	private String merchantCode;
	//商户名称
	private String custName;
	//门店名称
	private String storeName;
	//商户简称
	private String shortName;
	//营业执照号 
	private String businessLicense;
	//营业执照有效起始日期
	private String businessEffectiveTerm;
	//营业执照有效截止日期
	private String businessTerm;
	//法人姓名
	private String representativeName;
	//法人证件号
	private String representativeCertNo;
	//费率
	private String rate;
	//费率Id
	private String rateId;
	//身份证有效起始期
	private String identityEffDate;
	//身份证有效截止期
	private String identityValDate;
	//行业编号
	private String industryCode;
	//商户地址省
	private String merchantProvince;
	//商户地址市
	private String merchantCity;
	//商户地址区
	private String merchantArea;
	//商户详细地址
	private String cprRegAddr;
	//结算账号名称
	private String accountNm;
	//结算账号
	private String accountNo;
	//开户支行号
	private String interBankCode;
	//开户支行名称
	private String interBankName;
	//开户行
	private String bank;
	//开户行名称
	private String bankName;
	//开户行联行号
	private String bankCode;
	//银行开户省
	private String bankProvince;
	//银行开户市
	private String bankCity;
	//预留电话
	private String mobile;
	//联系人名称
	private String attentionName;
	//联系人电话
	private String attentionMobile;
	//营业执照路径
	private String businessPhotoPath;
	//身份证正面路径
	private String certAttribute1Path;
	//身份证反面路径
	private String certAttribute2Path;
	//身份证人像照
	private String legalCertAttribute1Path;
	//身份证国徽照
	private String legalCertAttribute2Path;
	//门头照路径
	private String doorPhotoPath;
	//店内照路径
	private String shopInteriorPath;
	//特殊资质照路径
	private String qualificationPath;
	//电子签名照路径
	private String signaturePath;
	//开户许可证路径
	private String openPath;
	//银行卡照路径
	private String bankCardPath;
	//其他材料照路径
	private String otherMaterialPath;
	//对公对私标识
	private String perEntFlag;
	//联系人邮箱
	private String attentionEmail;
	//商户登录邮箱号
	private String merchantLoginEmail;
	//商户登录手机号
	private String merchantLoginMobile;
	//客服号码
	private String contactPhone;
	//商户级别
	private String merchantLev;
	//法人手机号
	private String representativePhone;
	//联系地址
	private String representativeAddr;
	//渠道返回商户号
	private String outMerchantCode;
	//手机号
	private String mobileNo;
	//邮箱
	private String email;
	//主体类型
	private String merchantProperty;
	//营业执照类型 1762-已三证合一    1763-未三证合一
	private String businessLicenceType;
	//经营场景
	private String businessScene;
	//联系邮箱
	private String contactEmail;
	//公众号APPID
	private String mpAppid;
	//公众号页面截图
	private String mpAppScreenShotsPath;
	//小程序APPID
	private String miniprogramAppid;
	//小程序页面截图
	private String miniprogramAppidPath;
	//应用APPID
	private String appAppid;
	//APP下载链接
	private String appDownloadUrl;
	//APP截图
	private String appAppidPath;
	//PC网站域名
	private String webUrl;
	//PC网站对应的公众号APPID
	private String webAppid;
	//网站授权函
	private String webUrlPath;
	//
	private String merchantEmail;
	//
	private String actNm;
	//
	private String bankCardNo;
	//
	private String weChatBank;
	//日期
	private String date;
	
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPatchNo() {
		return patchNo;
	}
	public void setPatchNo(String patchNo) {
		this.patchNo = patchNo;
	}
	public String getMerchantEmail() {
		return merchantEmail;
	}
	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}
	public String getActNm() {
		return actNm;
	}
	public void setActNm(String actNm) {
		this.actNm = actNm;
	}
	public String getBankCardNo() {
		return bankCardNo;
	}
	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}
	public String getWeChatBank() {
		return weChatBank;
	}
	public void setWeChatBank(String weChatBank) {
		this.weChatBank = weChatBank;
	}
	public String getRateId() {
		return rateId;
	}
	public void setRateId(String rateId) {
		this.rateId = rateId;
	}
	public String getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	public String getBusinessEffectiveTerm() {
		return businessEffectiveTerm;
	}
	public void setBusinessEffectiveTerm(String businessEffectiveTerm) {
		this.businessEffectiveTerm = businessEffectiveTerm;
	}
	public String getBusinessTerm() {
		return businessTerm;
	}
	public void setBusinessTerm(String businessTerm) {
		this.businessTerm = businessTerm;
	}
	public String getInterBankCode() {
		return interBankCode;
	}
	public void setInterBankCode(String interBankCode) {
		this.interBankCode = interBankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankProvince() {
		return bankProvince;
	}
	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
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
	public String getBusinessPhotoPath() {
		return businessPhotoPath;
	}
	public void setBusinessPhotoPath(String businessPhotoPath) {
		this.businessPhotoPath = businessPhotoPath;
	}
	public String getCertAttribute1Path() {
		return certAttribute1Path;
	}
	public void setCertAttribute1Path(String certAttribute1Path) {
		this.certAttribute1Path = certAttribute1Path;
	}
	public String getCertAttribute2Path() {
		return certAttribute2Path;
	}
	public void setCertAttribute2Path(String certAttribute2Path) {
		this.certAttribute2Path = certAttribute2Path;
	}
	public String getQualificationPath() {
		return qualificationPath;
	}
	public void setQualificationPath(String qualificationPath) {
		this.qualificationPath = qualificationPath;
	}
	public String getSignaturePath() {
		return signaturePath;
	}
	public void setSignaturePath(String signaturePath) {
		this.signaturePath = signaturePath;
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
	public String getOtherMaterialPath() {
		return otherMaterialPath;
	}
	public void setOtherMaterialPath(String otherMaterialPath) {
		this.otherMaterialPath = otherMaterialPath;
	}
	public String getPerEntFlag() {
		return perEntFlag;
	}
	public void setPerEntFlag(String perEntFlag) {
		this.perEntFlag = perEntFlag;
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
	public String getMerchantLev() {
		return merchantLev;
	}
	public void setMerchantLev(String merchantLev) {
		this.merchantLev = merchantLev;
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
	public String getOutMerchantCode() {
		return outMerchantCode;
	}
	public void setOutMerchantCode(String outMerchantCode) {
		this.outMerchantCode = outMerchantCode;
	}
	public String getBusinessLicenceType() {
		return businessLicenceType;
	}
	public void setBusinessLicenceType(String businessLicenceType) {
		this.businessLicenceType = businessLicenceType;
	}
	public String getBusinessScene() {
		return businessScene;
	}
	public void setBusinessScene(String businessScene) {
		this.businessScene = businessScene;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public void setMerchantProperty(String merchantProperty) {
		this.merchantProperty = merchantProperty;
	}
	public String getMpAppid() {
		return mpAppid;
	}
	public void setMpAppid(String mpAppid) {
		this.mpAppid = mpAppid;
	}
	public String getMpAppScreenShotsPath() {
		return mpAppScreenShotsPath;
	}
	public void setMpAppScreenShotsPath(String mpAppScreenShotsPath) {
		this.mpAppScreenShotsPath = mpAppScreenShotsPath;
	}
	public String getMiniprogramAppid() {
		return miniprogramAppid;
	}
	public void setMiniprogramAppid(String miniprogramAppid) {
		this.miniprogramAppid = miniprogramAppid;
	}
	public String getMiniprogramAppidPath() {
		return miniprogramAppidPath;
	}
	public void setMiniprogramAppidPath(String miniprogramAppidPath) {
		this.miniprogramAppidPath = miniprogramAppidPath;
	}
	public String getAppAppid() {
		return appAppid;
	}
	public void setAppAppid(String appAppid) {
		this.appAppid = appAppid;
	}
	public String getAppDownloadUrl() {
		return appDownloadUrl;
	}
	public void setAppDownloadUrl(String appDownloadUrl) {
		this.appDownloadUrl = appDownloadUrl;
	}
	public String getAppAppidPath() {
		return appAppidPath;
	}
	public void setAppAppidPath(String appAppidPath) {
		this.appAppidPath = appAppidPath;
	}
	public String getWebUrl() {
		return webUrl;
	}
	public void setWebUrl(String webUrl) {
		this.webUrl = webUrl;
	}
	public String getWebAppid() {
		return webAppid;
	}
	public void setWebAppid(String webAppid) {
		this.webAppid = webAppid;
	}
	public String getWebUrlPath() {
		return webUrlPath;
	}
	public void setWebUrlPath(String webUrlPath) {
		this.webUrlPath = webUrlPath;
	}
	public String getMerchantProperty() {
		return merchantProperty;
	}
	public String getLegalCertAttribute1Path() {
		return legalCertAttribute1Path;
	}
	public void setLegalCertAttribute1Path(String legalCertAttribute1Path) {
		this.legalCertAttribute1Path = legalCertAttribute1Path;
	}
	public String getLegalCertAttribute2Path() {
		return legalCertAttribute2Path;
	}
	public void setLegalCertAttribute2Path(String legalCertAttribute2Path) {
		this.legalCertAttribute2Path = legalCertAttribute2Path;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
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
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getIdentityEffDate() {
		return identityEffDate;
	}
	public void setIdentityEffDate(String identityEffDate) {
		this.identityEffDate = identityEffDate;
	}
	public String getIdentityValDate() {
		return identityValDate;
	}
	public void setIdentityValDate(String identityValDate) {
		this.identityValDate = identityValDate;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
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
	public String getCprRegAddr() {
		return cprRegAddr;
	}
	public void setCprRegAddr(String cprRegAddr) {
		this.cprRegAddr = cprRegAddr;
	}
	
	public String getMerchantProvince() {
		return merchantProvince;
	}
	public void setMerchantProvince(String merchantProvince) {
		this.merchantProvince = merchantProvince;
	}
	public String getMerchantCity() {
		return merchantCity;
	}
	public void setMerchantCity(String merchantCity) {
		this.merchantCity = merchantCity;
	}
	public String getMerchantArea() {
		return merchantArea;
	}
	public void setMerchantArea(String merchantArea) {
		this.merchantArea = merchantArea;
	}
	public String getRepresentativeName() {
		return representativeName;
	}
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}
	public String getRepresentativeCertNo() {
		return representativeCertNo;
	}
	public void setRepresentativeCertNo(String representativeCertNo) {
		this.representativeCertNo = representativeCertNo;
	}
	public String getInterBankName() {
		return interBankName;
	}
	public void setInterBankName(String interBankName) {
		this.interBankName = interBankName;
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
	
	
	
	
	
}