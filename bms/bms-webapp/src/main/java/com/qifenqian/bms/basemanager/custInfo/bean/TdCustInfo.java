package com.qifenqian.bms.basemanager.custInfo.bean;

/**
 * 客户管理
 */
import java.math.BigDecimal;

public class TdCustInfo implements java.io.Serializable {

	

	/************************************************************************************/

	

	/** 客户编号 **/
	private String custId;

	/** 客户名称 **/
	private String custName;

	/** 客户密码 **/
	private String tradePwd;
	
	/** 附加串**/
	private String attachStr;

	/** 证件类型 **/
	private String certifyType;

	private String certifyName;

	/** 证件号 **/
	private String certifyNo;

	/** 客户类型 0 个人 1 企业 **/
	private String custType;

	/** 商户标志：0 商户，1 非商户 **/
	private String merchantFlag;

	/** 客户积分 **/
	private BigDecimal custScore;

	/** 客户等级 **/
	private String custLvl;

	/** 实名认证等级：0：未认证；1：1级认证；2：2级认证；3：3级认证。 **/
	private String trustCertifyLvl;

	/** 实名认证审核状态：00：0级审核通过；01：0级审核中；02：0级审核不通过 **/
	private String trustCertifyAuditState;

	/** 客户信息完整度，分几级：0、1 **/
	private String custInfoLvl;

	/** 客户地址 **/
	private String custAdd;

	/** 邮编 **/
	private String custPostCode;

	/** 营业执照编号 **/
	private String businessLicense;

	/** 税务登记号 **/
	private String taxRegCert;

	/** 法人证件类型 **/
	private String representativeCertType;

	/** 法人证件号码 **/
	private String representativeCertNo;

	/** 法人姓名 **/
	private String representativeName;

	/** 注册资本 **/
	private BigDecimal regCapital;

	/** 企业类型 国企：01 外企：00民企:02 **/
	private String entType;

	/** 所属行业 **/
	private String industryBelong;

	/** 年营业额 **/
	private BigDecimal yearTurnover;

	/** 商户网址 **/
	private String merchantWebSite;

	/** 客户状态：00 有效；01 待审核；02 注销；03 冻结；04 审核不通过 **/
	private String state;

	/** 是否证书认证：如开启证书认证，则只能在安装了证书的电脑上进行支付 **/
	private String isUseCertification;

	/** 是否短信认证：如开启短信认证，则账户相关设置或资金变动都需要输入手机验证码 **/
	private String isUseMsg;

	/** 创建人 **/
	private String createId;

	/** 注册时间 **/
	private String createTime;

	/** 最后更新人 **/
	private String modifyId;

	/** 最后更新时间 **/
	private String modifyTime;

	/** 七分钱账号 **/
	private String qfqAccId;

	/** 描述信息 **/
	private String desc;

	/** 手机号码 **/
	private String mobile;
	
	/**
	 * 邮箱
	 */
	private String email;
	

	/** 搜索开始时间 **/
	private String startTime;
	
	/** 搜索结束时间 **/
	private String endTime;
	
	private String authId;
	
	/** 七分钱余额 **/
	private String qfqTotalAmt;

	/** 来往单位编号*/
	private String fcontactunitNumber;
	private String isClear;
	
	/**
	 * 备注
	 */
	private String comment;

	private String blackAndWhiter;
	private String createBlackAndWhiterTime;
	
	private String blackAdnWhiteCode;
	
	private String compMainAcct;
	
	private String compAcctBank;
	
	private String representativeMobile;
	
	private String merchantCode;
	
	private String agentName;
	
	private String merchantMobile;
	
	private String merchantEmail;
	
	/*代付商户账户*/
	private String dfAccId;
	
	/*代付商户费率*/
	private String PayFee;
	/*垫资费率（工作日）*/
	private String workRate;
	/*垫资费率（非工作日）*/
	private String noWorkRate;
	/*垫资费率（非工作日）*/
	private String amtRate;
	/**是否开通代付**/
	private String topayStatue;
	
	/**是否开通代付**/
	private String contactPhone;
	/*营业执照所在地*/
	private String businessRegAddr;
	/**营业执照有效期*/
	private String businessTerm;
	/**支行信息*/
	private String branchBANK;
	/**银行开户名*/
	private String bankAcctName;
	/**营业期限终止时间*/
	private String businessTermEnd;
	/**营业期限起始时间*/
	private String businessTermStart;
	/**营业范围*/
	private String businessScope;
	/**联系人名称**/
	private String contactName;
	/**联系人电话**/
	private String contactMobile;
	/**客户简称**/
	private String shortName;
	/**身份证有效期限终止时间*/
	private String idTermEnd;
	/**身份证有效期限起始时间*/
	private String idTermStart;
	
	
	
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

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
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

	public String getBranchBANK() {
		return branchBANK;
	}

	public void setBranchBANK(String branchBANK) {
		this.branchBANK = branchBANK;
	}

	public String getBankAcctName() {
		return bankAcctName;
	}

	public void setBankAcctName(String bankAcctName) {
		this.bankAcctName = bankAcctName;
	}

	public String getBusinessTerm() {
		return businessTerm;
	}

	public void setBusinessTerm(String businessTerm) {
		this.businessTerm = businessTerm;
	}

	public String getBusinessRegAddr() {
		return businessRegAddr;
	}

	public void setBusinessRegAddr(String businessRegAddr) {
		this.businessRegAddr = businessRegAddr;
	}

	public String getDfAccId() {
		return dfAccId;
	}

	public void setDfAccId(String dfAccId) {
		this.dfAccId = dfAccId;
	}

	public String getPayFee() {
		return PayFee;
	}

	public void setPayFee(String payFee) {
		PayFee = payFee;
	}

	public String getWorkRate() {
		return workRate;
	}

	public void setWorkRate(String workRate) {
		this.workRate = workRate;
	}

	public String getNoWorkRate() {
		return noWorkRate;
	}

	public void setNoWorkRate(String noWorkRate) {
		this.noWorkRate = noWorkRate;
	}

	public String getAmtRate() {
		return amtRate;
	}

	public void setAmtRate(String amtRate) {
		this.amtRate = amtRate;
	}

	public String getTopayStatue() {
		return topayStatue;
	}

	public void setTopayStatue(String topayStatue) {
		this.topayStatue = topayStatue;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	private static final long serialVersionUID = 1L;
	
	public String getMerchantMobile() {
		return merchantMobile;
	}

	public void setMerchantMobile(String merchantMobile) {
		this.merchantMobile = merchantMobile;
	}

	public String getMerchantEmail() {
		return merchantEmail;
	}

	public void setMerchantEmail(String merchantEmail) {
		this.merchantEmail = merchantEmail;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getRepresentativeMobile() {
		return representativeMobile;
	}

	public void setRepresentativeMobile(String representativeMobile) {
		this.representativeMobile = representativeMobile;
	}

	public String getCompAcctBank() {
		return compAcctBank;
	}

	public void setCompAcctBank(String compAcctBank) {
		this.compAcctBank = compAcctBank;
	}

	public String getCompMainAcct() {
		return compMainAcct;
	}

	public void setCompMainAcct(String compMainAcct) {
		this.compMainAcct = compMainAcct;
	}

	public String getAuthId() {
		return authId;
	}

	public void setAuthId(String authId) {
		this.authId = authId;
	}

	public String getFcontactunitNumber() {
		return fcontactunitNumber;
	}

	public void setFcontactunitNumber(String fcontactunitNumber) {
		this.fcontactunitNumber = fcontactunitNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	
	public String getBlackAndWhiter() {
		return blackAndWhiter;
	}

	public void setBlackAndWhiter(String blackAndWhiter) {
		this.blackAndWhiter = blackAndWhiter;
	}

	public String getCustName() {
		return custName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public void setCustName(String custName) {
		this.custName = custName;
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

	public BigDecimal getCustScore() {
		return custScore;
	}

	public void setCustScore(BigDecimal custScore) {
		this.custScore = custScore;
	}

	public String getCustLvl() {
		return custLvl;
	}

	public void setCustLvl(String custLvl) {
		this.custLvl = custLvl;
	}

	public String getTrustCertifyLvl() {
		return trustCertifyLvl;
	}

	public void setTrustCertifyLvl(String trustCertifyLvl) {
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

	public String getModifyId() {
		return modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}


	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getQfqAccId() {
		return qfqAccId;
	}

	public void setQfqAccId(String qfqAccId) {
		this.qfqAccId = qfqAccId;
	}


	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

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

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCertifyName() {
		return certifyName;
	}

	public void setCertifyName(String certifyName) {
		this.certifyName = certifyName;
	}

	public String getQfqTotalAmt() {
		return qfqTotalAmt;
	}

	public void setQfqTotalAmt(String qfqTotalAmt) {
		this.qfqTotalAmt = qfqTotalAmt;
	}


	public String getCreateBlackAndWhiterTime() {
		return createBlackAndWhiterTime;
	}

	public void setCreateBlackAndWhiterTime(String createBlackAndWhiterTime) {
		this.createBlackAndWhiterTime = createBlackAndWhiterTime;
	}

	public String getBlackAdnWhiteCode() {
		return blackAdnWhiteCode;
	}

	public void setBlackAdnWhiteCode(String blackAdnWhiteCode) {
		this.blackAdnWhiteCode = blackAdnWhiteCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getAttachStr() {
		return attachStr;
	}

	public void setAttachStr(String attachStr) {
		this.attachStr = attachStr;
	}

	public String getIsClear() {
		return isClear;
	}

	public void setIsClear(String isClear) {
		this.isClear = isClear;
	}
	
	@Override
	public String toString() {
		return "TdCustInfo [custId=" + custId + ", custName=" + custName + ", tradePwd=" + tradePwd + ", attachStr="
				+ attachStr + ", certifyType=" + certifyType + ", certifyName=" + certifyName + ", certifyNo="
				+ certifyNo + ", custType=" + custType + ", merchantFlag=" + merchantFlag + ", custScore=" + custScore
				+ ", custLvl=" + custLvl + ", trustCertifyLvl=" + trustCertifyLvl + ", trustCertifyAuditState="
				+ trustCertifyAuditState + ", custInfoLvl=" + custInfoLvl + ", custAdd=" + custAdd + ", custPostCode="
				+ custPostCode + ", businessLicense=" + businessLicense + ", taxRegCert=" + taxRegCert
				+ ", representativeCertType=" + representativeCertType + ", representativeCertNo="
				+ representativeCertNo + ", representativeName=" + representativeName + ", regCapital=" + regCapital
				+ ", entType=" + entType + ", industryBelong=" + industryBelong + ", yearTurnover=" + yearTurnover
				+ ", merchantWebSite=" + merchantWebSite + ", state=" + state + ", isUseCertification="
				+ isUseCertification + ", isUseMsg=" + isUseMsg + ", createId=" + createId + ", createTime="
				+ createTime + ", modifyId=" + modifyId + ", modifyTime=" + modifyTime + ", qfqAccId=" + qfqAccId
				+ ", desc=" + desc + ", mobile=" + mobile + ", email=" + email + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", authId=" + authId + ", qfqTotalAmt=" + qfqTotalAmt
				+ ", fcontactunitNumber=" + fcontactunitNumber + ", isClear=" + isClear + ", comment=" + comment
				+ ", blackAndWhiter=" + blackAndWhiter + ", createBlackAndWhiterTime=" + createBlackAndWhiterTime
				+ ", blackAdnWhiteCode=" + blackAdnWhiteCode + ", compMainAcct=" + compMainAcct + ", compAcctBank="
				+ compAcctBank + ", representativeMobile=" + representativeMobile + ", merchantCode=" + merchantCode
				+ ", agentName=" + agentName + ", merchantMobile=" + merchantMobile + ", merchantEmail=" + merchantEmail
				+ ", dfAccId=" + dfAccId + ", PayFee=" + PayFee + ", workRate=" + workRate + ", noWorkRate="
				+ noWorkRate + ", amtRate=" + amtRate + ", topayStatue=" + topayStatue + ", contactPhone="
				+ contactPhone + ", businessRegAddr=" + businessRegAddr + ", businessTerm=" + businessTerm
				+ ", branchBANK=" + branchBANK + ", bankAcctName=" + bankAcctName + ", businessTermEnd="
				+ businessTermEnd + ", businessTermStart=" + businessTermStart + ", businessScope=" + businessScope
				+ "]";
	}
}