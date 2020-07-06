package com.qifenqian.bms.upgrade.merchant.bean;

import java.io.Serializable;
import java.util.Date;

public class MerchantRegisterInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String custId; //客户编号
	private String agentId;//代理商编号
	private String custName;//客户名称
	private String certifyType;//证件类型
	private String certifyNo;//证件号
	private String custAdd;//地址
	private String businessLicense;//营业执照编号
	private String representativeCertType;//法人证件类型
	private String representativeCertNo;//法人证件号
	private String representativeName;//法人名称
	private String state;//客户状态
	private String loginState;//登录状态
	private String createTime;//注册时间
	private String representativeMobile;//法人手机
	private String branchBank;//支行信息
	private String bankAcctName;//银行开户名
	private String merchantCode;//商户编号
	private String fcontactunitNumber;//往来编号
	private String cnaps;//联行号
	private String openAddressRec;//开户行地址
	private String merchantFlag;//商户标志：0 商户，1 非商户,2 微商户, 3 代理商
	private String compMainAcct;//对公账户
	private String period;//结算周期
	private String isSkipHoliday;//是否跳过节假日Y/N
	private String compAcctBank;//账户所属行
	private String agentName;   //代理人真实姓名
	
	private String h5Gateway; //h5
	private String h5tGateway; //原生h5
	private String mobilePlugin; //app
	private String onecodeColl; //扫码
	private String pcGateway;   //网关
    private String merchantRate;
    private String smallProgramPay;//小程序支持

	private  Date  addTime;
	private String agentRate;
	private String shortName;
	private String pwd;
	

	

	//接收页面 传过来的值
	private String name;//名称
	private String custManager;//客户经理
	private String mobile;//电话
	private String idCardNo;//证件号
	private String email;//邮件
	//private List<String> agentProd;//代理产品
	private String custType;//客户类型
	
	private String cashierName;
	private String cashierMobile;
	private String refundAuth;
	private String cashierOnlyId;
	private String merchantState;
	
	private String certificateState;
	private String authResultDesc;//审核不通过  备注
	
	private String businessTermStart;
	private String businessTermEnd;
	private String bankCardName;
	
	private String bankName;
	private String bankCardNo;
	private String templateId;
	private String roleId;

	private String totalNum;
	private String startCreateTime;
	private String endCreateTime;
	private String idCardFPath;
	private String idCardOPath;
	private String openAccountPath;
	
	private String bankCardPhotoPath;
	private String idCardScPhotoPath;
	
	private String businessLicenseFPath;
	private String businessLicenseOPath;
	private String trustCertifyAuditState;
	private String message;
	private String submitTime;
	private String instUser;//录入人
	
	private String mchRole;//商户角色 0 线上商户,1 线下商户,2 门店
	private String province;//省份代码
	private String city;//城市代码
	private String country;//区县码
	private String categoryType;//行业类目
	
	private String applyCode;
	
	private String businessScope;//经营范围
	
	private String certifyBeginTime;//身份证开始时间
	
	private String certifyEndTime;//身份证结束时间
	
	private String compMainAcctType;//对公帐号类型  01 对公 02 对私
	
	private String productCode;
	
	private String signRate;
	
	
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSignRate() {
		return signRate;
	}

	public void setSignRate(String signRate) {
		this.signRate = signRate;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
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

	public String getCustAdd() {
		return custAdd;
	}

	public void setCustAdd(String custAdd) {
		this.custAdd = custAdd;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
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

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getLoginState() {
		return loginState;
	}

	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getRepresentativeMobile() {
		return representativeMobile;
	}

	public void setRepresentativeMobile(String representativeMobile) {
		this.representativeMobile = representativeMobile;
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

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getFcontactunitNumber() {
		return fcontactunitNumber;
	}

	public void setFcontactunitNumber(String fcontactunitNumber) {
		this.fcontactunitNumber = fcontactunitNumber;
	}

	public String getCnaps() {
		return cnaps;
	}

	public void setCnaps(String cnaps) {
		this.cnaps = cnaps;
	}

	public String getOpenAddressRec() {
		return openAddressRec;
	}

	public void setOpenAddressRec(String openAddressRec) {
		this.openAddressRec = openAddressRec;
	}

	public String getMerchantFlag() {
		return merchantFlag;
	}

	public void setMerchantFlag(String merchantFlag) {
		this.merchantFlag = merchantFlag;
	}

	public String getCompMainAcct() {
		return compMainAcct;
	}

	public void setCompMainAcct(String compMainAcct) {
		this.compMainAcct = compMainAcct;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getIsSkipHoliday() {
		return isSkipHoliday;
	}

	public void setIsSkipHoliday(String isSkipHoliday) {
		this.isSkipHoliday = isSkipHoliday;
	}

	public String getCompAcctBank() {
		return compAcctBank;
	}

	public void setCompAcctBank(String compAcctBank) {
		this.compAcctBank = compAcctBank;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getH5Gateway() {
		return h5Gateway;
	}

	public void setH5Gateway(String h5Gateway) {
		this.h5Gateway = h5Gateway;
	}

	public String getH5tGateway() {
		return h5tGateway;
	}

	public void setH5tGateway(String h5tGateway) {
		this.h5tGateway = h5tGateway;
	}

	public String getMobilePlugin() {
		return mobilePlugin;
	}

	public void setMobilePlugin(String mobilePlugin) {
		this.mobilePlugin = mobilePlugin;
	}

	public String getOnecodeColl() {
		return onecodeColl;
	}

	public void setOnecodeColl(String onecodeColl) {
		this.onecodeColl = onecodeColl;
	}

	public String getPcGateway() {
		return pcGateway;
	}

	public void setPcGateway(String pcGateway) {
		this.pcGateway = pcGateway;
	}

	public String getMerchantRate() {
		return merchantRate;
	}

	public void setMerchantRate(String merchantRate) {
		this.merchantRate = merchantRate;
	}

	public String getSmallProgramPay() {
		return smallProgramPay;
	}

	public void setSmallProgramPay(String smallProgramPay) {
		this.smallProgramPay = smallProgramPay;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getAgentRate() {
		return agentRate;
	}

	public void setAgentRate(String agentRate) {
		this.agentRate = agentRate;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustManager() {
		return custManager;
	}

	public void setCustManager(String custManager) {
		this.custManager = custManager;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCashierName() {
		return cashierName;
	}

	public void setCashierName(String cashierName) {
		this.cashierName = cashierName;
	}

	public String getCashierMobile() {
		return cashierMobile;
	}

	public void setCashierMobile(String cashierMobile) {
		this.cashierMobile = cashierMobile;
	}

	public String getRefundAuth() {
		return refundAuth;
	}

	public void setRefundAuth(String refundAuth) {
		this.refundAuth = refundAuth;
	}

	public String getCashierOnlyId() {
		return cashierOnlyId;
	}

	public void setCashierOnlyId(String cashierOnlyId) {
		this.cashierOnlyId = cashierOnlyId;
	}

	public String getMerchantState() {
		return merchantState;
	}

	public void setMerchantState(String merchantState) {
		this.merchantState = merchantState;
	}

	public String getCertificateState() {
		return certificateState;
	}

	public void setCertificateState(String certificateState) {
		this.certificateState = certificateState;
	}

	public String getAuthResultDesc() {
		return authResultDesc;
	}

	public void setAuthResultDesc(String authResultDesc) {
		this.authResultDesc = authResultDesc;
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

	public String getBankCardName() {
		return bankCardName;
	}

	public void setBankCardName(String bankCardName) {
		this.bankCardName = bankCardName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
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

	public String getIdCardFPath() {
		return idCardFPath;
	}

	public void setIdCardFPath(String idCardFPath) {
		this.idCardFPath = idCardFPath;
	}

	public String getIdCardOPath() {
		return idCardOPath;
	}

	public void setIdCardOPath(String idCardOPath) {
		this.idCardOPath = idCardOPath;
	}

	public String getOpenAccountPath() {
		return openAccountPath;
	}

	public void setOpenAccountPath(String openAccountPath) {
		this.openAccountPath = openAccountPath;
	}

	public String getBankCardPhotoPath() {
		return bankCardPhotoPath;
	}

	public void setBankCardPhotoPath(String bankCardPhotoPath) {
		this.bankCardPhotoPath = bankCardPhotoPath;
	}

	public String getIdCardScPhotoPath() {
		return idCardScPhotoPath;
	}

	public void setIdCardScPhotoPath(String idCardScPhotoPath) {
		this.idCardScPhotoPath = idCardScPhotoPath;
	}

	public String getBusinessLicenseFPath() {
		return businessLicenseFPath;
	}

	public void setBusinessLicenseFPath(String businessLicenseFPath) {
		this.businessLicenseFPath = businessLicenseFPath;
	}

	public String getBusinessLicenseOPath() {
		return businessLicenseOPath;
	}

	public void setBusinessLicenseOPath(String businessLicenseOPath) {
		this.businessLicenseOPath = businessLicenseOPath;
	}

	public String getTrustCertifyAuditState() {
		return trustCertifyAuditState;
	}

	public void setTrustCertifyAuditState(String trustCertifyAuditState) {
		this.trustCertifyAuditState = trustCertifyAuditState;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getInstUser() {
		return instUser;
	}

	public void setInstUser(String instUser) {
		this.instUser = instUser;
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

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getApplyCode() {
		return applyCode;
	}

	public void setApplyCode(String applyCode) {
		this.applyCode = applyCode;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}

	public String getCertifyBeginTime() {
		return certifyBeginTime;
	}

	public void setCertifyBeginTime(String certifyBeginTime) {
		this.certifyBeginTime = certifyBeginTime;
	}

	public String getCertifyEndTime() {
		return certifyEndTime;
	}

	public void setCertifyEndTime(String certifyEndTime) {
		this.certifyEndTime = certifyEndTime;
	}

	public String getCompMainAcctType() {
		return compMainAcctType;
	}

	public void setCompMainAcctType(String compMainAcctType) {
		this.compMainAcctType = compMainAcctType;
	}
	
	
}
