package com.qifenqian.bms.basemanager.merchant.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class MerchantExport implements Serializable{
	
	
	/**
	 *  商户协议号
	 */
	
	private String merchantCode;
	/*
	private String userName;
	
	private String mobile;
	
	private String state;*/
	/**
	 * 客户名称
	 */
	private String custName;
	/**
	 * 交易密码
	 */
//	private String tradePwd;
	/**
	 * 附加串
	 */
//	private String attachStr;
	
	/**
	 * 错误密码次数
	 */
//	private String wrongPwdCount;
	
	/**
	 * 证件类型
	 *//*	
	private String certifyType;
	
	*//**
	 * 证件号
	 *//*
	private String certifyNo;*/
	
	/**
	 * 客户类型
	 */
	private String custType;
	/**
	 * 商户状态
	 */
	private String merchantState;
	/**
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 商户标志
	 */
	private String merchantFlag;
	
	/**
	 * 客户积分
	 */
//	private BigDecimal custScore;
	
	/**
	 * 客户等级
	 */
//	private String custLvl;
	
	/**
	 * 实名认证等级
	 */
//	private String trustCertifyLvl;
	
	/**
	 * 实名认证状态
	 */
//	private String trustCertifyAuditState;
	
	/**
	 * 客户信息完整度
	 */
//	private String custInfoLvl;
	
	/**
	 * 地址
	 */
	private String custAdd;
	
	/**
	 * 邮编
	 */
	private String custPostCode;
	
	/**
	 * 营业执照编号
	 */
	private String businessLicense;
	
	/**
	 * 税务登记证号
	 */
	private String taxRegCert;
	
	/**
	 * 法人证件类型
	 */
	private String representativeCertType;
	
	/**
	 * 法人证件号码
	 */
	private String representativeCertNo;
	
	/**
	 * 法人名称
	 */
	private String representativeName;
	
	/**
	 * 注册资本
	 */
//	private BigDecimal regCapital;
	
	/**
	 * 企业类型
	 */
	private String entType;
	
	/**
	 * 所属行业
	 */
//	private String industryBelong;
	
	/**
	 * 年营业额
	 */
//	private BigDecimal yearTurnover;
	
	/**
	 * 商户网站地址
	 */
//	private String merchantWebSite;
	
	/**
	 * 客户审核状态
	 */
	private String auditState;
	
	/**
	 * 是否证书认证
	 */
	private String isUseCertification;
	
	/**
	 * 是否短信认证
	 */
	private String isUseMsg;
	
	/**
	 * 七分钱账户ID
	 */
	private String qfqAccId;
	
	/**
	 * 创建人
	 */
	private String createId;
	
	/**
	 * 创建时间
	 */
	private String createTime;
	
	/**
	 * 修改人
	 */
	private String modifyId;
	
	/**
	 * 修改时间
	 */
	private String modifyTime;
	
	/**
	 * 营业期限
	 */
	private String businessTerm;
	
	
	/**
	 * 营业执照注册所在地
	 */
	private String businessRegAddr;
	
	/**
	 * 企业联系电话
	 */
	private String contactPhone;
	
	/**
	 * 来往单位编号
	 */
	private String fcontactunitNumber;
	
	/**
	 * 组织机构代码
	 */
	private String orgInstCode;
	
	/**
	 *  法人代表归属地
	 */
	private String representativeAddr;
	
	/**
	 *  法人手机
	 */
	private String representativeMobile;
	
	/**
	 *  代理人真实姓名
	 */
	private String agentName;
	
	/**
	 *  代理人身份证类型
	 */
	private String agentCertType;
	
	/**
	 *  代理人身份证号码
	 */
	private String agentCertCode;
	
	/**
	 *  代理人手机号码
	 */
	private String agentMobile;
	
	/**
	 *  公司对公账号
	 */
	private String compMainAcct;
	
	/**
	 *  公司对公账号所属行
	 */
	private String compAcctBank;
	
	
	/**
	 *  支付密码冻结时间
	 */
//	private Date pwdFreezeTime;
	
	
	
	/**
	 *  公司汇款校验金额
	 */
	private BigDecimal compVerifyAmt;
	
	/**
	 *  支行信息
	 */
	private String branchBank;
	
	/**
	 *  银行开户名
	 */
	private String bankAcctName;
	
	/**
	 *  备注
	 */
	private String comment;
	
	private String aduitUserName; 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3995489953800055928L;

/*	public String getCustId() {
		return custId;
	}
*/
	public String getCustName() {
		return custName;
	}

	/*
	 * public String getTradePwd() { return tradePwd; }
	 * 
	 * public String getAttachStr() { return attachStr; }
	 * 
	 * public String getWrongPwdCount() { return wrongPwdCount; }
	 */

/*	public String getCertifyType() {
		return certifyType;
	}

	public String getCertifyNo() {
		return certifyNo;
	}*/

	public String getAduitUserName() {
		return aduitUserName;
	}

	public void setAduitUserName(String aduitUserName) {
		this.aduitUserName = aduitUserName;
	}

	public String getCustType() {
		return custType;
	}

	public String getMerchantState() {
		return merchantState;
	}

	public String getEmail() {
		return email;
	}

	public String getMerchantFlag() {
		return merchantFlag;
	}

	/*
	 * public BigDecimal getCustScore() { return custScore; }
	 * 
	 * public String getCustLvl() { return custLvl; }
	 * 
	 * public String getTrustCertifyLvl() { return trustCertifyLvl; }
	 * 
	 * public String getTrustCertifyAuditState() { return trustCertifyAuditState; }
	 * 
	 * public String getCustInfoLvl() { return custInfoLvl; }
	 */

	public String getCustAdd() {
		return custAdd;
	}

	public String getCustPostCode() {
		return custPostCode;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public String getTaxRegCert() {
		return taxRegCert;
	}

	public String getRepresentativeCertType() {
		return representativeCertType;
	}

	public String getRepresentativeCertNo() {
		return representativeCertNo;
	}

	public String getRepresentativeName() {
		return representativeName;
	}

	/*
	 * public BigDecimal getRegCapital() { return regCapital; }
	 */

	public String getEntType() {
		return entType;
	}

	/*
	 * public String getIndustryBelong() { return industryBelong; }
	 * 
	 * public BigDecimal getYearTurnover() { return yearTurnover; }
	 * 
	 * public String getMerchantWebSite() { return merchantWebSite; }
	 */


	public String getIsUseCertification() {
		return isUseCertification;
	}

	public String getIsUseMsg() {
		return isUseMsg;
	}

	public String getQfqAccId() {
		return qfqAccId;
	}

	public String getCreateId() {
		return createId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public String getModifyId() {
		return modifyId;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public String getBusinessTerm() {
		return businessTerm;
	}

	public String getBusinessRegAddr() {
		return businessRegAddr;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public String getFcontactunitNumber() {
		return fcontactunitNumber;
	}

	public String getOrgInstCode() {
		return orgInstCode;
	}

	public String getRepresentativeAddr() {
		return representativeAddr;
	}

	public String getRepresentativeMobile() {
		return representativeMobile;
	}

	public String getAgentName() {
		return agentName;
	}

	public String getAgentCertType() {
		return agentCertType;
	}

	public String getAgentCertCode() {
		return agentCertCode;
	}

	public String getAgentMobile() {
		return agentMobile;
	}

	public String getCompMainAcct() {
		return compMainAcct;
	}

	public String getCompAcctBank() {
		return compAcctBank;
	}

	/*
	 * public Date getPwdFreezeTime() { return pwdFreezeTime; }
	 */

	public BigDecimal getCompVerifyAmt() {
		return compVerifyAmt;
	}

	public String getBranchBank() {
		return branchBank;
	}

	public String getBankAcctName() {
		return bankAcctName;
	}

	public String getComment() {
		return comment;
	}

	/*public void setCustId(String custId) {
		this.custId = custId;
	}*/

	public void setCustName(String custName) {
		this.custName = custName;
	}
	/*
	 * public void setTradePwd(String tradePwd) { this.tradePwd = tradePwd; }
	 * 
	 * public void setAttachStr(String attachStr) { this.attachStr = attachStr; }
	 * 
	 * public void setWrongPwdCount(String wrongPwdCount) { this.wrongPwdCount =
	 * wrongPwdCount; }
	 * 
	 */	/*public void setCertifyType(String certifyType) {
		this.certifyType = certifyType;
	}

	public void setCertifyNo(String certifyNo) {
		this.certifyNo = certifyNo;
	}*/

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public void setMerchantState(String merchantState) {
		this.merchantState = merchantState;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setMerchantFlag(String merchantFlag) {
		this.merchantFlag = merchantFlag;
	}

	/*
	 * public void setCustScore(BigDecimal custScore) { this.custScore = custScore;
	 * }
	 * 
	 * public void setCustLvl(String custLvl) { this.custLvl = custLvl; }
	 * 
	 * public void setTrustCertifyLvl(String trustCertifyLvl) { this.trustCertifyLvl
	 * = trustCertifyLvl; }
	 * 
	 * public void setTrustCertifyAuditState(String trustCertifyAuditState) {
	 * this.trustCertifyAuditState = trustCertifyAuditState; }
	 * 
	 * public void setCustInfoLvl(String custInfoLvl) { this.custInfoLvl =
	 * custInfoLvl; }
	 */
	public void setCustAdd(String custAdd) {
		this.custAdd = custAdd;
	}

	public void setCustPostCode(String custPostCode) {
		this.custPostCode = custPostCode;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public void setTaxRegCert(String taxRegCert) {
		this.taxRegCert = taxRegCert;
	}

	public void setRepresentativeCertType(String representativeCertType) {
		this.representativeCertType = representativeCertType;
	}

	public void setRepresentativeCertNo(String representativeCertNo) {
		this.representativeCertNo = representativeCertNo;
	}

	public void setRepresentativeName(String representativeName) {
		this.representativeName = representativeName;
	}

	/*
	 * public void setRegCapital(BigDecimal regCapital) { this.regCapital =
	 * regCapital; }
	 */
	public void setEntType(String entType) {
		this.entType = entType;
	}

	/*
	 * public void setIndustryBelong(String industryBelong) { this.industryBelong =
	 * industryBelong; }
	 * 
	 * public void setYearTurnover(BigDecimal yearTurnover) { this.yearTurnover =
	 * yearTurnover; }
	 * 
	 * public void setMerchantWebSite(String merchantWebSite) { this.merchantWebSite
	 * = merchantWebSite; }
	 */

	
	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public void setIsUseCertification(String isUseCertification) {
		this.isUseCertification = isUseCertification;
	}

	public void setIsUseMsg(String isUseMsg) {
		this.isUseMsg = isUseMsg;
	}

	public void setQfqAccId(String qfqAccId) {
		this.qfqAccId = qfqAccId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public void setBusinessTerm(String businessTerm) {
		this.businessTerm = businessTerm;
	}

	public void setBusinessRegAddr(String businessRegAddr) {
		this.businessRegAddr = businessRegAddr;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public void setFcontactunitNumber(String fcontactunitNumber) {
		this.fcontactunitNumber = fcontactunitNumber;
	}

	public void setOrgInstCode(String orgInstCode) {
		this.orgInstCode = orgInstCode;
	}

	public void setRepresentativeAddr(String representativeAddr) {
		this.representativeAddr = representativeAddr;
	}

	public void setRepresentativeMobile(String representativeMobile) {
		this.representativeMobile = representativeMobile;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public void setAgentCertType(String agentCertType) {
		this.agentCertType = agentCertType;
	}

	public void setAgentCertCode(String agentCertCode) {
		this.agentCertCode = agentCertCode;
	}

	public void setAgentMobile(String agentMobile) {
		this.agentMobile = agentMobile;
	}

	public void setCompMainAcct(String compMainAcct) {
		this.compMainAcct = compMainAcct;
	}

	public void setCompAcctBank(String compAcctBank) {
		this.compAcctBank = compAcctBank;
	}

	/*
	 * public void setPwdFreezeTime(Date pwdFreezeTime) { this.pwdFreezeTime =
	 * pwdFreezeTime; }
	 */


	public void setCompVerifyAmt(BigDecimal compVerifyAmt) {
		this.compVerifyAmt = compVerifyAmt;
	}

	public void setBranchBank(String branchBank) {
		this.branchBank = branchBank;
	}

	public void setBankAcctName(String bankAcctName) {
		this.bankAcctName = bankAcctName;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
/*
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

		*/
}
