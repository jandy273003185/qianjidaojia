package com.qifenqian.bms.basemanager.merchant.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class MerchantVo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1977088279003048575L;

	/**
	 * 客户账号
	 */
	private String merchantAccount;

	/**
	 * 客户编号，与登录表的客户编号一样.
	 */
	private String custId;
	
	/**
	 * 商户编号
	 */
	private String merchantCode;
	/**
	 * 客户名称.
	 */
	private String custName;

	/**
	 * 客户简称
	 */
	private String shortName;

	private String provinceName;

	private String cityName;
	/**
	 * 商户邮箱
	 */
	private String merchantEmail;

	/**
	 * 所属业务人员 客户经理 
	 */
	private String custManager;

	/**
	 * 联系人姓名 
	 */
	private String contactName;

	/**
	 *联系人电话 
	 */
	private String contactMobile;

	/**
	 * 网点号
	 */
	private String cnaps;


	/**
	 * 结算类型 
	 */
	private String compMainAcctType;

	/**
	 * 交易密码.
	 */
	private String tradePwd;

	/**
	 * 证件类型，00身份证.
	 */
	private String certifyType;

	/**
	 * 证件号.
	 */
	private String certifyNo;
	
	/**
	 * 审核编号
	 */
	private String authId;

	/**
	 * 客户类型：0 个人1 企业.
	 */
	private String custType;

	/**
	 * 商户标志：0 商户，1 非商户.
	 */
	private String merchantFlag;

	/**
	 * 客户积分.
	 */
	private Long custScore;

	/**
	 * 客户等级.
	 */
	private String custLvl;

	/**
	 * 实名认证等级：0：未认证；1：1级认证；2：2级认证；3：3级认证。 业务含义： 0：未验证任何信息材料，； 1：身份证验证；
	 * 2：身份证+银行卡验证； 3：身份证+银行卡+证件审核。
	 * 
	 * 具体参看支付宝。https://certify.alipay.com/certifyInfo.htm.
	 */
	private Short trustCertifyLvl;

	/**
	 * 实名认证审核状态： 00：0级审核通过；01：0级审核中；02：0级审核不通过；
	 * 0级审核通过后，客户实名认证等级升级到1级，1级的审核状态为10,11,12，依此类推
	 */
	private String trustCertifyAuditState;

	/**
	 * 客户信息完整度，分几级：0、1
	 */
	private String custInfoLvl;

	/**
	 * 地址.
	 */
	private String custAdd;

	/**
	 * 邮编.
	 */
	private String custPostCode;

	/**
	 * 营业执照编号（企业专用）.
	 */
	private String businessLicense;

	/**
	 * 营业执照所在地（企业专用）
	 */
	private String businessRegAddr;

	/**
	 * 营业期限
	 */
	private String businessTerm;

	/**
	 * 企业联系电话
	 */
	private String contactPhone;

	
	private String doorPhoto;
	/**
	 * 组织机构代码
	 */
	private String orgInstCode;

	/**
	 * 税务登记证号（企业专用）.
	 */
	private String taxRegCert;

	/**
	 * 法人证件类型（企业专用）.
	 */
	private String representativeCertType;

	/**
	 * 法人证件号码（企业专用）.
	 */
	private String representativeCertNo;

	/**
	 * 法人名称（企业专用）.
	 */
	private String representativeName;

	/**
	 * 法人手机号码
	 */
	private String representativeMobile;

	/**
	 * 法人代表归属地
	 */
	private String representativeAddr;

	/**
	 * 代理人真实姓名
	 */
	private String agentName;

	/**
	 * 代理人身份证类型
	 */
	private String agentCertType;

	/**
	 * 代理人身份证号码
	 */
	private String agentCertCode;

	/**
	 * 代理人手机号码
	 */
	private String agentMobile;

	/**
	 * 公司对公账号
	 */
	private String compMainAcct;

	/**
	 * 公司对公账号所属行
	 */
	private String compAcctBank;

	/**
	 * 公司汇款校验金额
	 */
	private String compVerifyAmt;
	/**
	 * 注册资本（企业专用）.
	 */
	private BigDecimal regCapital;

	/**
	 * 企业类型：00外资，01 国企，02 私企.
	 */
	private String entType;

	/**
	 * 所属行业（企业专用）.
	 */
	private String industryBelong;

	/**
	 * 年营业额.
	 */
	private BigDecimal yearTurnover;

	/**
	 * 商户网站地址.
	 */
	private String merchantWebSite;

	/**
	 * 审核状态：00 审核通过；01 待审核；04 审核不通过
	 */
	private String state;
	/**
	 * 商户状态
	 */
	private String merchantState;

	/**
	 * 是否证书认证：如开启证书认证，则只能在安装了证书的电脑上进行支付.
	 */
	private String isUseCertification;

	/**
	 * 是否短信认证：如开启短信认证，则账户相关设置或资金变动都需要输入手机验证码.
	 */
	private String isUseMsg;

	/**
	 * 创建人.
	 */
	private String createId;

	/**
	 * 创建时间.
	 */
	private String createTime;

	private String startCreateTime;

	private String endCreateTime;

	/**
	 * 修改人.
	 */
	private String modifyId;

	/**
	 * 修改时间.
	 */
	private Date modifyTime;

	/**
	 * 附加串，用于生成加密密码.
	 */
	private String attachStr;

	/**
	 * 七分钱账号
	 */
	private String qfqAccId;

	/**
	 * 七分宝账号
	 */
	private String qfbAccId;

	private String email;

	/**
	 * 费率编号
	 */
	private String feeCode;

	private String bankName;

	/**
	 * 对公账号支行信息
	 */
	private String branchBank;
	/**
	 * 银行开户名
	 */
	private String bankAcctName;

	/**
	 * 备注
	 */
	private String comment;

	/**
	 * 登陆状态
	 *
	 */

	private String loginState;
	
	/**
	 * 审核状态
	 */
	private String auditState;

	/**
	 * 来往单位编号
	 */
	private String fcontactunitNumber;
	
	/**
	 * 工作流审核状态
	 */
	private String workFlowStatus;
	
	/**
	 * 审核信息 
	 */
	private String aduitMessage;
	
	/**
	 * 审核人  
	 */
	private String aduitUserName;
	/**
	 * 审核时间
	 */
	private String auditTime;

	/** 
	 * 开户行地址
	 */
	private String openAddressRec;

	private String userId;
	
	private String userName;
	
	private String bankCardName;
	
	private String bankCardNo;

	
	private String agentRate;
	
	private String protocolState;
	
	private String disableDate;
	
	private String validDate;
	
	private String mobile;
	
	private String businessTermEnd;
	
	private String businessTermStart;
	
	private String categoryType;
	
	private String mchRole;
	
	private String province;
	
	private String city;
	
	private String country;
	
	private String filingStatus;
	
	private String filingAuditStatus;
	
	private String isClear;	
	
	/**
	 * //客户经理名字
	 */
	private String custManagerName;
	
	/**
	 * 商户所属服务商名字
	 */
	private String facilitatorName;
	
	/**
	 * 开户银行省份
	 */
	private String bankProvinceName;
	
	/**
	 * 开户银行城市
	 */
	private String bankCityName;

	//创建人名称
	private String createName;

	private String areaName;
	
	private String bankProName;
	
	private String bankCitName;

	private String referrer;
	/**
	 * 服务商级别',
	 */
	private String serviceLevel;
	
	private String agentCode;
	
	/**
	 * 法人身份有效截止期
	 */
	private String idTermEnd;
	/**
	 * 法人身份有效起始期
	 */
	private String idTermStart;
	
	/**
	 * 起止时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getIdTermEnd() {
		return idTermEnd;
	}

	public void setIdTermEnd(String idTermEnd) {
		this.idTermEnd = idTermEnd;
	}

	public String getIdTermStart() {
		return idTermStart;
	}

	public void setIdTermStart(String idTermStart) {
		this.idTermStart = idTermStart;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getServiceLevel() {
		return serviceLevel;
	}

	public void setServiceLevel(String serviceLevel) {
		this.serviceLevel = serviceLevel;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getBankProName() {
		return bankProName;
	}

	public void setBankProName(String bankProName) {
		this.bankProName = bankProName;
	}

	public String getBankCitName() {
		return bankCitName;
	}

	public void setBankCitName(String bankCitName) {
		this.bankCitName = bankCitName;
	}

	public String getMerchantAccount() {
		return merchantAccount;
	}

	public void setMerchantAccount(String merchantAccount) {
		this.merchantAccount = merchantAccount;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getMerchantEmail() {
		return merchantEmail;
	}

	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}

	public String getCustManager() {
		return custManager;
	}

	public void setCustManager(String custManager) {
		this.custManager = custManager;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactMobile() {
		return contactMobile;
	}

	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	public String getCnaps() {
		return cnaps;
	}

	public void setCnaps(String cnaps) {
		this.cnaps = cnaps;
	}

	public String getCompMainAcctType() {
		return compMainAcctType;
	}

	public void setCompMainAcctType(String compMainAcctType) {
		this.compMainAcctType = compMainAcctType;
	}

	public String getTradePwd() {
		return tradePwd;
	}

	public void setTradePwd(String tradePwd) {
		this.tradePwd = tradePwd;
	}

	public String getCertifyType() {
		return certifyType;
	}

	public void setCertifyType(String certifyType) {
		this.certifyType = certifyType;
	}

	public String getCertifyNo() {
		return certifyNo;
	}

	public void setCertifyNo(String certifyNo) {
		this.certifyNo = certifyNo;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getMerchantFlag() {
		return merchantFlag;
	}

	public void setMerchantFlag(String merchantFlag) {
		this.merchantFlag = merchantFlag;
	}

	public Long getCustScore() {
		return custScore;
	}

	public void setCustScore(Long custScore) {
		this.custScore = custScore;
	}

	public String getCustLvl() {
		return custLvl;
	}

	public void setCustLvl(String custLvl) {
		this.custLvl = custLvl;
	}

	public Short getTrustCertifyLvl() {
		return trustCertifyLvl;
	}

	public void setTrustCertifyLvl(Short trustCertifyLvl) {
		this.trustCertifyLvl = trustCertifyLvl;
	}

	public String getTrustCertifyAuditState() {
		return trustCertifyAuditState;
	}

	public void setTrustCertifyAuditState(String trustCertifyAuditState) {
		this.trustCertifyAuditState = trustCertifyAuditState;
	}

	public String getCustInfoLvl() {
		return custInfoLvl;
	}

	public void setCustInfoLvl(String custInfoLvl) {
		this.custInfoLvl = custInfoLvl;
	}

	public String getCustAdd() {
		return custAdd;
	}

	public void setCustAdd(String custAdd) {
		this.custAdd = custAdd;
	}

	public String getCustPostCode() {
		return custPostCode;
	}

	public void setCustPostCode(String custPostCode) {
		this.custPostCode = custPostCode;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getBusinessRegAddr() {
		return businessRegAddr;
	}

	public void setBusinessRegAddr(String businessRegAddr) {
		this.businessRegAddr = businessRegAddr;
	}

	public String getBusinessTerm() {
		return businessTerm;
	}

	public void setBusinessTerm(String businessTerm) {
		this.businessTerm = businessTerm;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getDoorPhoto() {
		return doorPhoto;
	}

	public void setDoorPhoto(String doorPhoto) {
		this.doorPhoto = doorPhoto;
	}

	public String getOrgInstCode() {
		return orgInstCode;
	}

	public void setOrgInstCode(String orgInstCode) {
		this.orgInstCode = orgInstCode;
	}

	public String getTaxRegCert() {
		return taxRegCert;
	}

	public void setTaxRegCert(String taxRegCert) {
		this.taxRegCert = taxRegCert;
	}

	public String getRepresentativeCertType() {
		return representativeCertType;
	}

	public void setRepresentativeCertType(String representativeCertType) {
		this.representativeCertType = representativeCertType;
	}

	public String getRepresentativeCertNo() {
		return representativeCertNo;
	}

	public void setRepresentativeCertNo(String representativeCertNo) {
		this.representativeCertNo = representativeCertNo;
	}

	public String getRepresentativeName() {
		return representativeName;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	public String getRepresentativeMobile() {
		return representativeMobile;
	}

	public void setRepresentativeMobile(String representativeMobile) {
		this.representativeMobile = representativeMobile;
	}

	public String getRepresentativeAddr() {
		return representativeAddr;
	}

	public void setRepresentativeAddr(String representativeAddr) {
		this.representativeAddr = representativeAddr;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getAgentCertType() {
		return agentCertType;
	}

	public void setAgentCertType(String agentCertType) {
		this.agentCertType = agentCertType;
	}

	public String getAgentCertCode() {
		return agentCertCode;
	}

	public void setAgentCertCode(String agentCertCode) {
		this.agentCertCode = agentCertCode;
	}

	public String getAgentMobile() {
		return agentMobile;
	}

	public void setAgentMobile(String agentMobile) {
		this.agentMobile = agentMobile;
	}

	public String getCompMainAcct() {
		return compMainAcct;
	}

	public void setCompMainAcct(String compMainAcct) {
		this.compMainAcct = compMainAcct;
	}

	public String getCompAcctBank() {
		return compAcctBank;
	}

	public void setCompAcctBank(String compAcctBank) {
		this.compAcctBank = compAcctBank;
	}

	public String getCompVerifyAmt() {
		return compVerifyAmt;
	}

	public void setCompVerifyAmt(String compVerifyAmt) {
		this.compVerifyAmt = compVerifyAmt;
	}

	public BigDecimal getRegCapital() {
		return regCapital;
	}

	public void setRegCapital(BigDecimal regCapital) {
		this.regCapital = regCapital;
	}

	public String getEntType() {
		return entType;
	}

	public void setEntType(String entType) {
		this.entType = entType;
	}

	public String getIndustryBelong() {
		return industryBelong;
	}

	public void setIndustryBelong(String industryBelong) {
		this.industryBelong = industryBelong;
	}

	public BigDecimal getYearTurnover() {
		return yearTurnover;
	}

	public void setYearTurnover(BigDecimal yearTurnover) {
		this.yearTurnover = yearTurnover;
	}

	public String getMerchantWebSite() {
		return merchantWebSite;
	}

	public void setMerchantWebSite(String merchantWebSite) {
		this.merchantWebSite = merchantWebSite;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getMerchantState() {
		return merchantState;
	}

	public void setMerchantState(String merchantState) {
		this.merchantState = merchantState;
	}

	public String getIsUseCertification() {
		return isUseCertification;
	}

	public void setIsUseCertification(String isUseCertification) {
		this.isUseCertification = isUseCertification;
	}

	public String getIsUseMsg() {
		return isUseMsg;
	}

	public void setIsUseMsg(String isUseMsg) {
		this.isUseMsg = isUseMsg;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(String startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	public String getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getModifyId() {
		return modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getAttachStr() {
		return attachStr;
	}

	public void setAttachStr(String attachStr) {
		this.attachStr = attachStr;
	}

	public String getQfqAccId() {
		return qfqAccId;
	}

	public void setQfqAccId(String qfqAccId) {
		this.qfqAccId = qfqAccId;
	}

	public String getQfbAccId() {
		return qfbAccId;
	}

	public void setQfbAccId(String qfbAccId) {
		this.qfbAccId = qfbAccId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFeeCode() {
		return feeCode;
	}

	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBranchBank() {
		return branchBank;
	}

	public void setBranchBank(String branchBank) {
		this.branchBank = branchBank;
	}

	public String getBankAcctName() {
		return bankAcctName;
	}

	public void setBankAcctName(String bankAcctName) {
		this.bankAcctName = bankAcctName;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getLoginState() {
		return loginState;
	}

	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getFcontactunitNumber() {
		return fcontactunitNumber;
	}

	public void setFcontactunitNumber(String fcontactunitNumber) {
		this.fcontactunitNumber = fcontactunitNumber;
	}

	public String getWorkFlowStatus() {
		return workFlowStatus;
	}

	public void setWorkFlowStatus(String workFlowStatus) {
		this.workFlowStatus = workFlowStatus;
	}

	public String getAduitMessage() {
		return aduitMessage;
	}

	public void setAduitMessage(String aduitMessage) {
		this.aduitMessage = aduitMessage;
	}

	public String getAduitUserName() {
		return aduitUserName;
	}

	public void setAduitUserName(String aduitUserName) {
		this.aduitUserName = aduitUserName;
	}

	
	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getOpenAddressRec() {
		return openAddressRec;
	}

	public void setOpenAddressRec(String openAddressRec) {
		this.openAddressRec = openAddressRec;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBankCardName() {
		return bankCardName;
	}

	public void setBankCardName(String bankCardName) {
		this.bankCardName = bankCardName;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getAgentRate() {
		return agentRate;
	}

	public void setAgentRate(String agentRate) {
		this.agentRate = agentRate;
	}

	public String getProtocolState() {
		return protocolState;
	}

	public void setProtocolState(String protocolState) {
		this.protocolState = protocolState;
	}

	public String getDisableDate() {
		return disableDate;
	}

	public void setDisableDate(String disableDate) {
		this.disableDate = disableDate;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBusinessTermEnd() {
		return businessTermEnd;
	}

	public void setBusinessTermEnd(String businessTermEnd) {
		this.businessTermEnd = businessTermEnd;
	}

	public String getBusinessTermStart() {
		return businessTermStart;
	}

	public void setBusinessTermStart(String businessTermStart) {
		this.businessTermStart = businessTermStart;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getMchRole() {
		return mchRole;
	}

	public void setMchRole(String mchRole) {
		this.mchRole = mchRole;
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

	public String getFilingStatus() {
		return filingStatus;
	}

	public void setFilingStatus(String filingStatus) {
		this.filingStatus = filingStatus;
	}

	public String getFilingAuditStatus() {
		return filingAuditStatus;
	}

	public void setFilingAuditStatus(String filingAuditStatus) {
		this.filingAuditStatus = filingAuditStatus;
	}

	public String getIsClear() {
		return isClear;
	}

	public void setIsClear(String isClear) {
		this.isClear = isClear;
	}

	public String getCustManagerName() {
		return custManagerName;
	}

	public void setCustManagerName(String custManagerName) {
		this.custManagerName = custManagerName;
	}

	public String getFacilitatorName() {
		return facilitatorName;
	}

	public void setFacilitatorName(String facilitatorName) {
		this.facilitatorName = facilitatorName;
	}

	public String getBankProvinceName() {
		return bankProvinceName;
	}

	public void setBankProvinceName(String bankProvinceName) {
		this.bankProvinceName = bankProvinceName;
	}

	public String getBankCityName() {
		return bankCityName;
	}

	public void setBankCityName(String bankCityName) {
		this.bankCityName = bankCityName;
	}

	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	@Override
	public String toString() {
		return "MerchantVo{" +
				"merchantAccount='" + merchantAccount + '\'' +
				", custId='" + custId + '\'' +
				", merchantCode='" + merchantCode + '\'' +
				", custName='" + custName + '\'' +
				", shortName='" + shortName + '\'' +
				", provinceName='" + provinceName + '\'' +
				", cityName='" + cityName + '\'' +
				", merchantEmail='" + merchantEmail + '\'' +
				", custManager='" + custManager + '\'' +
				", contactName='" + contactName + '\'' +
				", contactMobile='" + contactMobile + '\'' +
				", cnaps='" + cnaps + '\'' +
				", compMainAcctType='" + compMainAcctType + '\'' +
				", tradePwd='" + tradePwd + '\'' +
				", certifyType='" + certifyType + '\'' +
				", certifyNo='" + certifyNo + '\'' +
				", authId='" + authId + '\'' +
				", custType='" + custType + '\'' +
				", merchantFlag='" + merchantFlag + '\'' +
				", custScore=" + custScore +
				", custLvl='" + custLvl + '\'' +
				", trustCertifyLvl=" + trustCertifyLvl +
				", trustCertifyAuditState='" + trustCertifyAuditState + '\'' +
				", custInfoLvl='" + custInfoLvl + '\'' +
				", custAdd='" + custAdd + '\'' +
				", custPostCode='" + custPostCode + '\'' +
				", businessLicense='" + businessLicense + '\'' +
				", businessRegAddr='" + businessRegAddr + '\'' +
				", businessTerm='" + businessTerm + '\'' +
				", contactPhone='" + contactPhone + '\'' +
				", doorPhoto='" + doorPhoto + '\'' +
				", orgInstCode='" + orgInstCode + '\'' +
				", taxRegCert='" + taxRegCert + '\'' +
				", representativeCertType='" + representativeCertType + '\'' +
				", representativeCertNo='" + representativeCertNo + '\'' +
				", representativeName='" + representativeName + '\'' +
				", representativeMobile='" + representativeMobile + '\'' +
				", representativeAddr='" + representativeAddr + '\'' +
				", agentName='" + agentName + '\'' +
				", agentCertType='" + agentCertType + '\'' +
				", agentCertCode='" + agentCertCode + '\'' +
				", agentMobile='" + agentMobile + '\'' +
				", compMainAcct='" + compMainAcct + '\'' +
				", compAcctBank='" + compAcctBank + '\'' +
				", compVerifyAmt='" + compVerifyAmt + '\'' +
				", regCapital=" + regCapital +
				", entType='" + entType + '\'' +
				", industryBelong='" + industryBelong + '\'' +
				", yearTurnover=" + yearTurnover +
				", merchantWebSite='" + merchantWebSite + '\'' +
				", state='" + state + '\'' +
				", merchantState='" + merchantState + '\'' +
				", isUseCertification='" + isUseCertification + '\'' +
				", isUseMsg='" + isUseMsg + '\'' +
				", createId='" + createId + '\'' +
				", createTime='" + createTime + '\'' +
				", startCreateTime='" + startCreateTime + '\'' +
				", endCreateTime='" + endCreateTime + '\'' +
				", modifyId='" + modifyId + '\'' +
				", modifyTime=" + modifyTime +
				", attachStr='" + attachStr + '\'' +
				", qfqAccId='" + qfqAccId + '\'' +
				", qfbAccId='" + qfbAccId + '\'' +
				", email='" + email + '\'' +
				", feeCode='" + feeCode + '\'' +
				", bankName='" + bankName + '\'' +
				", branchBank='" + branchBank + '\'' +
				", bankAcctName='" + bankAcctName + '\'' +
				", comment='" + comment + '\'' +
				", loginState='" + loginState + '\'' +
				", auditState='" + auditState + '\'' +
				", fcontactunitNumber='" + fcontactunitNumber + '\'' +
				", workFlowStatus='" + workFlowStatus + '\'' +
				", aduitMessage='" + aduitMessage + '\'' +
				", aduitUserName='" + aduitUserName + '\'' +
				", openAddressRec='" + openAddressRec + '\'' +
				", userId='" + userId + '\'' +
				", userName='" + userName + '\'' +
				", bankCardName='" + bankCardName + '\'' +
				", bankCardNo='" + bankCardNo + '\'' +
				", agentRate='" + agentRate + '\'' +
				", protocolState='" + protocolState + '\'' +
				", disableDate='" + disableDate + '\'' +
				", validDate='" + validDate + '\'' +
				", mobile='" + mobile + '\'' +
				", businessTermEnd='" + businessTermEnd + '\'' +
				", businessTermStart='" + businessTermStart + '\'' +
				", categoryType='" + categoryType + '\'' +
				", mchRole='" + mchRole + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				", filingStatus='" + filingStatus + '\'' +
				", filingAuditStatus='" + filingAuditStatus + '\'' +
				", isClear='" + isClear + '\'' +
				", custManagerName='" + custManagerName + '\'' +
				", facilitatorName='" + facilitatorName + '\'' +
				", bankProvinceName='" + bankProvinceName + '\'' +
				", bankCityName='" + bankCityName + '\'' +
				", createName='" + createName + '\'' +
				", areaName='" + areaName + '\'' +
				'}';
	}
}
