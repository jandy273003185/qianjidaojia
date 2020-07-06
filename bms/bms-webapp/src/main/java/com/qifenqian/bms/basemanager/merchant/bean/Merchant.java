package com.qifenqian.bms.basemanager.merchant.bean;



import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Merchant implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2131395679153030392L;

	/**
	 * 客户账号， 
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
	 * 客户名称
	 */
	private String custName;
	
	/** 
	 * 客户简称
	 */
	private String shortName;

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
	 * 开户省
	 */
	private String bankProvinceName;

	/**
	 *开户市
	 */
	private String bankCityName;
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
	 * 证件号
	 */
	private String certifyNo;

	/** 
	 * 客户类型：0 个人1 企业
	 */
	private String custType;

	/** 
	 * 商户标志：0 商户，1 非商户
	 */
	private String merchantFlag;

	/** 
	 * 客户积分
	 */
	private Long custScore;

	/** 
	 * 客户等级
	 */
	private String custLvl;

	/** 
	 * 实名认证等级：0：未认证；1：1级认证；2：2级认证；3：3级认证。
	           业务含义：
	           0：未验证任何信息材料，；
	           1：身份证验证；
	           2：身份证+银行卡验证；
	           3：身份证+银行卡+证件审核。
	           
	           具体参看支付宝。https://certify.alipay.com/certifyInfo.htm.
	 */
	private Short trustCertifyLvl;

	/**
	 * 实名认证审核状态：
		00：0级审核通过；01：0级审核中；02：0级审核不通过；
		0级审核通过后，客户实名认证等级升级到1级，1级的审核状态为10,11,12，依此类推
	 */
	private String trustCertifyAuditState;
	
	/**
	 * 客户信息完整度，分几级：0、1
	 */
	private String custInfoLvl;
	
	/** 
	 * 地址
	 */
	private String custAdd;

	/** 
	 * 邮编
	 */
	private String custPostCode;

	/** 
	 * 营业执照编号（企业专用）
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
	 * 法人证件号码（企业专用）
	 */
	private String representativeCertNo;

	/** 
	 * 法人名称（企业专用）
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
	 * 代理人真实姓名 mian9所属代理商
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
	 * 客户状态：00 有效；01 待审核；02 注销；03 冻结；04 审核不通过.
	 */
	private String state;

	/** 
	 * 是否证书认证：如开启证书认证，则只能在安装了证书的电脑上进行支付.
	 */
	private String isUseCertification;

	/** 
	 * 是否短信认证：如开启短信认证，则账户相关设置或资金变动都需要输入手机验证码.
	 */
	private String isUseMsg;

	/** 
	 * 创建人
	 */
	private String createId;

	/** 
	 * 创建时间
	 */
	private Date createTime;

	/** 
	 * 修改人
	 */
	private String modifyId;

	/** 
	 * 修改时间
	 */
	private Date modifyTime;
	
	/** 
	 * 附加串，用于生成加密密码
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
	/**
	 * 费率编号
	 */
	private String feeCode;

	/**
	 * 对公账号支行信息 
	 */
	private String branchBank;
	/**
	 * 银行开户名
	 */
	private String bankAcctName;
	
	/**
	 * 来往单位编号
	 */
	private String fcontactunitNumber;
	
	/**
	 * 开户行地址
	 */
	private String openAddressRec;
	
	private String mobile;
	private String businessTermStart;//营业期限起始时间
	private String businessTermEnd;//营业期限终止时间
	private String roleId;
	
	/**
	 * 行业类目
	 */
	private String categoryType;
	
	/**
	 * 商户角色
	 */
	private String mchRole;
	
	/**
	 * 省份 
	 */
	private String province;
	
	/**
	 * 城市 
	 */
	private String city;
	
	/**
	 * 区县
	 */
	private String country;
	
	/**
	 * 身份证有效日期
	 */
	private String certNoValidDate;
	private String filingStatus;
	private String filingAuditStatus;

	private Short wrongPwdCount;


	private String dfAccId;


	private Date pwdFreezeTime;


	private String comment;

	private Integer authId;

	private String agentFlag;

	private Date dfCreateTime;

	private String isClear;

	private String businessScope;

	private String merchantMobile;

	private String registerWay;

	/**
	 * 服务商推荐人
	 */
	private String referrer;
	/**
	 * 服务商级别
	 */
	private String serviceLevel;
	/**
	 * 法人身份有效截止期
	 */
	private String idTermEnd;
	/**
	 * 法人身份有效起始期
	 */
	private String idTermStart;
	

	private String taskId;

	private String auditName;

	private String fals;

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getAuditName() {
		return auditName;
	}

	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}

	public String getFals() {
		return fals;
	}

	public void setFals(String fals) {
		this.fals = fals;
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

	public Short getWrongPwdCount() {
		return wrongPwdCount;
	}

	public void setWrongPwdCount(Short wrongPwdCount) {
		this.wrongPwdCount = wrongPwdCount;
	}

	public String getDfAccId() {
		return dfAccId;
	}

	public void setDfAccId(String dfAccId) {
		this.dfAccId = dfAccId;
	}

	public Date getPwdFreezeTime() {
		return pwdFreezeTime;
	}

	public void setPwdFreezeTime(Date pwdFreezeTime) {
		this.pwdFreezeTime = pwdFreezeTime;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getAuthId() {
		return authId;
	}

	public void setAuthId(Integer authId) {
		this.authId = authId;
	}

	public String getAgentFlag() {
		return agentFlag;
	}

	public void setAgentFlag(String agentFlag) {
		this.agentFlag = agentFlag;
	}

	public Date getDfCreateTime() {
		return dfCreateTime;
	}

	public void setDfCreateTime(Date dfCreateTime) {
		this.dfCreateTime = dfCreateTime;
	}

	public String getIsClear() {
		return isClear;
	}

	public void setIsClear(String isClear) {
		this.isClear = isClear;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getMerchantMobile() {
		return merchantMobile;
	}

	public void setMerchantMobile(String merchantMobile) {
		this.merchantMobile = merchantMobile;
	}

	public String getRegisterWay() {
		return registerWay;
	}

	public void setRegisterWay(String registerWay) {
		this.registerWay = registerWay;
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getCertNoValidDate() {
		return certNoValidDate;
	}

	public void setCertNoValidDate(String certNoValidDate) {
		this.certNoValidDate = certNoValidDate;
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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getBusinessTermStart() {
		return businessTermStart;
	}

	public void setBusinessTermStart(String businessTermStart) {
		this.businessTermStart = businessTermStart;
	}

	public String getBusinessTermEnd() {
		return businessTermEnd;
	}

	public void setBusinessTermEnd(String businessTermEnd) {
		this.businessTermEnd = businessTermEnd;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getOpenAddressRec() {
		return openAddressRec;
	}

	public void setOpenAddressRec(String openAddressRec) {
		this.openAddressRec = openAddressRec;
	}

	public Merchant()
	{
	}

	public Merchant(String custId, Date createTime, Date modifyTime)
	{
		this.custId = custId;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
	}

    public String getMerchantAccount() {
        return merchantAccount;
    }

    public void setMerchantAccount(String merchantAccount) {
        this.merchantAccount = merchantAccount;
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


    public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getCustId()
	{
		return this.custId;
	}

	public void setCustId(String custId)
	{
		this.custId = custId;
	}

	public String getCustName()
	{
		return this.custName;
	}

	public void setCustName(String custName)
	{
		this.custName = custName;
	}

	public String getTradePwd()
	{
		return this.tradePwd;
	}

	public void setTradePwd(String tradePwd)
	{
		this.tradePwd = tradePwd;
	}

	public String getCertifyType()
	{
		return this.certifyType;
	}

	public void setCertifyType(String certifyType)
	{
		this.certifyType = certifyType;
	}

	public String getCertifyNo()
	{
		return this.certifyNo;
	}

	public void setCertifyNo(String certifyNo)
	{
		this.certifyNo = certifyNo;
	}

	public String getCustType()
	{
		return this.custType;
	}

	public void setCustType(String custType)
	{
		this.custType = custType;
	}

	public String getMerchantFlag()
	{
		return this.merchantFlag;
	}

	public void setMerchantFlag(String merchantFlag)
	{
		this.merchantFlag = merchantFlag;
	}

	public Long getCustScore()
	{
		return this.custScore;
	}

	public void setCustScore(Long custScore)
	{
		this.custScore = custScore;
	}

	public String getCustLvl()
	{
		return this.custLvl;
	}

	public void setCustLvl(String custLvl)
	{
		this.custLvl = custLvl;
	}

	public Short getTrustCertifyLvl()
	{
		return this.trustCertifyLvl;
	}

	public void setTrustCertifyLvl(Short trustCertifyLvl)
	{
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

	public String getCustAdd()
	{
		return this.custAdd;
	}

	public void setCustAdd(String custAdd)
	{
		this.custAdd = custAdd;
	}

	public String getCustPostCode()
	{
		return this.custPostCode;
	}

	public void setCustPostCode(String custPostCode)
	{
		this.custPostCode = custPostCode;
	}

	public String getBusinessLicense()
	{
		return this.businessLicense;
	}

	public void setBusinessLicense(String businessLicense)
	{
		this.businessLicense = businessLicense;
	}

	public String getTaxRegCert()
	{
		return this.taxRegCert;
	}

	public void setTaxRegCert(String taxRegCert)
	{
		this.taxRegCert = taxRegCert;
	}

	public String getRepresentativeCertType()
	{
		return this.representativeCertType;
	}

	public void setRepresentativeCertType(String representativeCertType)
	{
		this.representativeCertType = representativeCertType;
	}

	public String getRepresentativeCertNo()
	{
		return this.representativeCertNo;
	}

	public void setRepresentativeCertNo(String representativeCertNo)
	{
		this.representativeCertNo = representativeCertNo;
	}

	public String getRepresentativeName()
	{
		return this.representativeName;
	}

	public void setRepresentativeName(String representativeName)
	{
		this.representativeName = representativeName;
	}

	public BigDecimal getRegCapital()
	{
		return this.regCapital;
	}

	public void setRegCapital(BigDecimal regCapital)
	{
		this.regCapital = regCapital;
	}

	public String getEntType()
	{
		return this.entType;
	}

	public void setEntType(String entType)
	{
		this.entType = entType;
	}

	public String getIndustryBelong()
	{
		return this.industryBelong;
	}

	public void setIndustryBelong(String industryBelong)
	{
		this.industryBelong = industryBelong;
	}

	public BigDecimal getYearTurnover()
	{
		return this.yearTurnover;
	}

	public void setYearTurnover(BigDecimal yearTurnover)
	{
		this.yearTurnover = yearTurnover;
	}

	public String getMerchantWebSite()
	{
		return this.merchantWebSite;
	}

	public void setMerchantWebSite(String merchantWebSite)
	{
		this.merchantWebSite = merchantWebSite;
	}

	public String getState()
	{
		return this.state;
	}

	public void setState(String state)
	{
		this.state = state;
	}

	public String getIsUseCertification()
	{
		return this.isUseCertification;
	}

	public void setIsUseCertification(String isUseCertification)
	{
		this.isUseCertification = isUseCertification;
	}

	public String getIsUseMsg()
	{
		return this.isUseMsg;
	}

	public void setIsUseMsg(String isUseMsg)
	{
		this.isUseMsg = isUseMsg;
	}

	public String getCreateId()
	{
		return this.createId;
	}

	public void setCreateId(String createId)
	{
		this.createId = createId;
	}

	public Date getCreateTime()
	{
		return this.createTime;
	}

	public void setCreateTime(Date createTime)
	{
		this.createTime = createTime;
	}

	public String getModifyId()
	{
		return this.modifyId;
	}

	public void setModifyId(String modifyId)
	{
		this.modifyId = modifyId;
	}

	public Date getModifyTime()
	{
		return this.modifyTime;
	}

	public void setModifyTime(Date modifyTime)
	{
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
	
	public String getOrgInstCode() {
		return orgInstCode;
	}

	public void setOrgInstCode(String orgInstCode) {
		this.orgInstCode = orgInstCode;
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

	public String getFeeCode() {
		return feeCode;
	}

	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
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

	public String getFcontactunitNumber() {
		return fcontactunitNumber;
	}

	public void setFcontactunitNumber(String fcontactunitNumber) {
		this.fcontactunitNumber = fcontactunitNumber;
	}

	public Merchant(String merchantAccount, String custId, String merchantCode, String custName, String shortName, String merchantEmail, String custManager, String contactName, String contactMobile, String provinceName, String cityName, String cnaps, String compMainAcctType, String tradePwd, String certifyType, String certifyNo, String custType, String merchantFlag, Long custScore, String custLvl, Short trustCertifyLvl, String trustCertifyAuditState, String custInfoLvl, String custAdd, String custPostCode, String businessLicense, String businessRegAddr, String businessTerm, String contactPhone, String orgInstCode, String taxRegCert, String representativeCertType, String representativeCertNo, String representativeName, String representativeMobile, String representativeAddr, String agentName, String agentCertType, String agentCertCode, String agentMobile, String compMainAcct, String compAcctBank, String compVerifyAmt, BigDecimal regCapital, String entType, String industryBelong, BigDecimal yearTurnover, String merchantWebSite, String state, String isUseCertification, String isUseMsg, String createId, Date createTime, String modifyId, Date modifyTime, String attachStr, String qfqAccId, String qfbAccId, String feeCode, String branchBank, String bankAcctName, String fcontactunitNumber, String openAddressRec, String mobile, String businessTermStart, String businessTermEnd, String roleId, String categoryType, String mchRole, String province, String city, String country, String certNoValidDate, String filingStatus, String filingAuditStatus, Short wrongPwdCount, String dfAccId, Date pwdFreezeTime, String comment, Integer authId, String agentFlag, Date dfCreateTime, String isClear, String businessScope, String merchantMobile, String registerWay) {
		this.merchantAccount = merchantAccount;
		this.custId = custId;
		this.merchantCode = merchantCode;
		this.custName = custName;
		this.shortName = shortName;
		this.merchantEmail = merchantEmail;
		this.custManager = custManager;
		this.contactName = contactName;
		this.contactMobile = contactMobile;
		this.cnaps = cnaps;
		this.compMainAcctType = compMainAcctType;
		this.tradePwd = tradePwd;
		this.certifyType = certifyType;
		this.certifyNo = certifyNo;
		this.custType = custType;
		this.merchantFlag = merchantFlag;
		this.custScore = custScore;
		this.custLvl = custLvl;
		this.trustCertifyLvl = trustCertifyLvl;
		this.trustCertifyAuditState = trustCertifyAuditState;
		this.custInfoLvl = custInfoLvl;
		this.custAdd = custAdd;
		this.custPostCode = custPostCode;
		this.businessLicense = businessLicense;
		this.businessRegAddr = businessRegAddr;
		this.businessTerm = businessTerm;
		this.contactPhone = contactPhone;
		this.orgInstCode = orgInstCode;
		this.taxRegCert = taxRegCert;
		this.representativeCertType = representativeCertType;
		this.representativeCertNo = representativeCertNo;
		this.representativeName = representativeName;
		this.representativeMobile = representativeMobile;
		this.representativeAddr = representativeAddr;
		this.agentName = agentName;
		this.agentCertType = agentCertType;
		this.agentCertCode = agentCertCode;
		this.agentMobile = agentMobile;
		this.compMainAcct = compMainAcct;
		this.compAcctBank = compAcctBank;
		this.compVerifyAmt = compVerifyAmt;
		this.regCapital = regCapital;
		this.entType = entType;
		this.industryBelong = industryBelong;
		this.yearTurnover = yearTurnover;
		this.merchantWebSite = merchantWebSite;
		this.state = state;
		this.isUseCertification = isUseCertification;
		this.isUseMsg = isUseMsg;
		this.createId = createId;
		this.createTime = createTime;
		this.modifyId = modifyId;
		this.modifyTime = modifyTime;
		this.attachStr = attachStr;
		this.qfqAccId = qfqAccId;
		this.qfbAccId = qfbAccId;
		this.feeCode = feeCode;
		this.branchBank = branchBank;
		this.bankAcctName = bankAcctName;
		this.fcontactunitNumber = fcontactunitNumber;
		this.openAddressRec = openAddressRec;
		this.mobile = mobile;
		this.businessTermStart = businessTermStart;
		this.businessTermEnd = businessTermEnd;
		this.roleId = roleId;
		this.categoryType = categoryType;
		this.mchRole = mchRole;
		this.province = province;
		this.city = city;
		this.country = country;
		this.certNoValidDate = certNoValidDate;
		this.filingStatus = filingStatus;
		this.filingAuditStatus = filingAuditStatus;
		this.wrongPwdCount = wrongPwdCount;
		this.dfAccId = dfAccId;
		this.pwdFreezeTime = pwdFreezeTime;
		this.comment = comment;
		this.authId = authId;
		this.agentFlag = agentFlag;
		this.dfCreateTime = dfCreateTime;
		this.isClear = isClear;
		this.businessScope = businessScope;
		this.merchantMobile = merchantMobile;
		this.registerWay = registerWay;
	}

	@Override
	public String toString() {
		return "Merchant{" +
				"merchantAccount='" + merchantAccount + '\'' +
				", custId='" + custId + '\'' +
				", merchantCode='" + merchantCode + '\'' +
				", custName='" + custName + '\'' +
				", shortName='" + shortName + '\'' +
				", merchantEmail='" + merchantEmail + '\'' +
				", custManager='" + custManager + '\'' +
				", contactName='" + contactName + '\'' +
				", contactMobile='" + contactMobile + '\'' +
				", cnaps='" + cnaps + '\'' +
				", compMainAcctType='" + compMainAcctType + '\'' +
				", tradePwd='" + tradePwd + '\'' +
				", certifyType='" + certifyType + '\'' +
				", certifyNo='" + certifyNo + '\'' +
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
				", isUseCertification='" + isUseCertification + '\'' +
				", isUseMsg='" + isUseMsg + '\'' +
				", createId='" + createId + '\'' +
				", createTime=" + createTime +
				", modifyId='" + modifyId + '\'' +
				", modifyTime=" + modifyTime +
				", attachStr='" + attachStr + '\'' +
				", qfqAccId='" + qfqAccId + '\'' +
				", qfbAccId='" + qfbAccId + '\'' +
				", feeCode='" + feeCode + '\'' +
				", branchBank='" + branchBank + '\'' +
				", bankAcctName='" + bankAcctName + '\'' +
				", fcontactunitNumber='" + fcontactunitNumber + '\'' +
				", openAddressRec='" + openAddressRec + '\'' +
				", mobile='" + mobile + '\'' +
				", businessTermStart='" + businessTermStart + '\'' +
				", businessTermEnd='" + businessTermEnd + '\'' +
				", roleId='" + roleId + '\'' +
				", categoryType='" + categoryType + '\'' +
				", mchRole='" + mchRole + '\'' +
				", province='" + province + '\'' +
				", city='" + city + '\'' +
				", country='" + country + '\'' +
				", certNoValidDate='" + certNoValidDate + '\'' +
				", filingStatus='" + filingStatus + '\'' +
				", filingAuditStatus='" + filingAuditStatus + '\'' +
				", wrongPwdCount=" + wrongPwdCount +
				", dfAccId='" + dfAccId + '\'' +
				", pwdFreezeTime=" + pwdFreezeTime +
				", comment='" + comment + '\'' +
				", authId=" + authId +
				", agentFlag='" + agentFlag + '\'' +
				", dfCreateTime=" + dfCreateTime +
				", isClear='" + isClear + '\'' +
				", businessScope='" + businessScope + '\'' +
				", merchantMobile='" + merchantMobile + '\'' +
				", registerWay='" + registerWay + '\'' +
				'}';
	}
}
