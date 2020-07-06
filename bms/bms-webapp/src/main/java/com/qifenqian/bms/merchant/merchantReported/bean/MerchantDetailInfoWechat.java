package com.qifenqian.bms.merchant.merchantReported.bean;

import java.util.Date;

/**
 * @author 微信报备详情类 zgc 
 *
 */
public class MerchantDetailInfoWechat  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**报备批次号*/
	private String patchNo;
	/**商户编号*/
	private String merchantCode;
	/**商户名称*/
	private String custName;
	/**  门店名称   */
	private String storeName;
	/**  商户行业   */
	private String industryCode;
	/**客服电话   */
	private String contactPhone;
	/**商户地址省   */
	private String merchantProvince;
	/** 商户地址市   */
	private String merchantCity;
	/**  商户地址区 */
	private String merchantArea;
	/** 商户详细地址 */
	private String cprRegAddr;
	/** 门头照路径 '*/
	private String doorPhotoPath;
	/** 店内照路径'*/
	private String shopInteriorPath;
	/** 法人真实姓名 */
	private String representativeName;
	/**手机号*/
	private String mobileNo;
	/**  邮箱*/
	private String email;
	/**  法人身份证号码 */
	private String representativeCertNo;
	/** 身份证有效起始期 */
	private String identityEffDate;
	/**身份证有效截止期*/
	private String identityValDate;
	/**  身份证人像照  */
	private String legalCertAttribute1Path;
	/**  身份证国徽照 */
	private String legalCertAttribute2Path;
	/** 结算账号名称 */
	private String accountNm;
	/** 结算账号 */
	private String accountNo;
	/** 银行开户省  */
	private String bankProvince;
	/** 银行开户市 */
	private String bankCity;
	/**开户银行 */
	private String bank;
	/**开户支行*/
	private String interBankName;
	/**结算费率*/
	private String rate;
	/**微信小微商户号*/
	private String outMerchantCode;
	/**	商户简称 */
	private String shortName;
	/**主体类型 */
	private String merchantProperty;
	/**	经营场景 */
	private String businessScene;
	/**	营业执照号 */
	private String businessLicense;
	/**	营业执照有效期起始值 */
	private String businessEffectiveTerm;
	/**	营业执照有效期结束值 */
	private String businessTerm;
	/**	营业执照图路径 */
	private String businessPhotoPath;
	/**	特殊行业资质照路径 */
	private String qualificationPath;
	/**	公众号APPID */
	private String mpAppid;
	/**	公众号页面截图 */
	private String mpAppScreenShotsPath;
	/**	小程序APPID */
	private String miniprogramAppid;
	/**	小程序页面截图 */
	private String miniprogramAppidPath;
	/**	应用APPID */
	private String appAppid;
	/**	APP下载链接 */
	private String appDownloadUrl;
	/**	APP截图*/
	private String appAppidPath;
	/**	PC网站域名*/
	private String webUrl;
	/**	PC网站对应的公众号APPID */
	private String webAppid;
	/**	网站授权函图片路径 */
	private String webUrlPath;
	/**	费率结算规则ID*/
	private String rateId;
	/**	报备明细状态 */
	private String reportStatus;
	/**	返回信息 */
	private String resultMsg;
	/**	备注 */
	private String remark;
	/**	微信支付分配申请单号 */
	private String applymentId;
	/**	微信返回签约链接 */
	private String signUrl;
	/**	签约二维码 */
	private String signQrcode;
	/**	创建时间 */
	private Date createTime;
	/**	修改时间 */
	private Date modifyTime;
	
	
	public String getSignUrl() {
		return signUrl;
	}
	public void setSignUrl(String signUrl) {
		this.signUrl = signUrl;
	}
	public String getSignQrcode() {
		return signQrcode;
	}
	public void setSignQrcode(String signQrcode) {
		this.signQrcode = signQrcode;
	}
	public String getPatchNo() {
		return patchNo;
	}
	public void setPatchNo(String patchNo) {
		this.patchNo = patchNo;
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
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getIndustryCode() {
		return industryCode;
	}
	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
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
	public String getCprRegAddr() {
		return cprRegAddr;
	}
	public void setCprRegAddr(String cprRegAddr) {
		this.cprRegAddr = cprRegAddr;
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
	public String getRepresentativeName() {
		return representativeName;
	}
	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRepresentativeCertNo() {
		return representativeCertNo;
	}
	public void setRepresentativeCertNo(String representativeCertNo) {
		this.representativeCertNo = representativeCertNo;
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
	public String getBankProvince() {
		return bankProvince;
	}
	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getInterBankName() {
		return interBankName;
	}
	public void setInterBankName(String interBankName) {
		this.interBankName = interBankName;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getOutMerchantCode() {
		return outMerchantCode;
	}
	public void setOutMerchantCode(String outMerchantCode) {
		this.outMerchantCode = outMerchantCode;
	}
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getMerchantProperty() {
		return merchantProperty;
	}
	public void setMerchantProperty(String merchantProperty) {
		this.merchantProperty = merchantProperty;
	}
	public String getBusinessScene() {
		return businessScene;
	}
	public void setBusinessScene(String businessScene) {
		this.businessScene = businessScene;
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
	public String getBusinessPhotoPath() {
		return businessPhotoPath;
	}
	public void setBusinessPhotoPath(String businessPhotoPath) {
		this.businessPhotoPath = businessPhotoPath;
	}
	public String getQualificationPath() {
		return qualificationPath;
	}
	public void setQualificationPath(String qualificationPath) {
		this.qualificationPath = qualificationPath;
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
	public String getRateId() {
		return rateId;
	}
	public void setRateId(String rateId) {
		this.rateId = rateId;
	}
	public String getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getApplymentId() {
		return applymentId;
	}
	public void setApplymentId(String applymentId) {
		this.applymentId = applymentId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
}
