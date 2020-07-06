package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class AllinPayBean implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4492584097257913323L;
	//渠道
	private String channelNo;
	//批次号
	private String patchNo;
	//商户号
	private String merchantCode;
	//商户名称
	private String custName;
	//商户简称
	private String shortName;
	//商户类型
	private String mecTypeFlag;
	//商户行业
	private String industry;
	//客服号码
	private String contactPhone;
	//商户地址省
	private String merchantProvince;
	//商户地址市
	private String merchantCity;
	//商户详细地址
	private String cprRegAddr;
	//营业执照名称
	private String cprRegNmCn;
	//营业执照有效起始日期
	private String businessEffectiveTerm;
	//营业执照有效截止日期
	private String businessTerm;
	//营业执照号 
	private String businessLicense;
	//拓展人
	private String expanduser;
	//线上线下业务场景
	private String offlag;
	//合同有效日期
	private String contractDate;
	//法人姓名
	private String representativeName;
	//法人证件号
	private String representativeCertNo;
	//法人证件类型
	private String representativeCertType;
	//身份证有效起始期
	private String idTermStart;
	//身份证有效截止期
	private String idTermEnd;
	//联系人名称
	private String attentionName;
	//联系人电话
	private String attentionMobile;
	//结算账号名称
	private String accountNm;
	//结算账号
	private String accountNo;
	//结算方式
	private String clearMode;
	//卡折类型
	private String accttp;
	//银行开户省
	private String bankProvince;
	//银行开户市
	private String bankCity;
	//开户银行
	private String branchBank;
	//开户支行号
	private String interBankCode;
	//开户支行名称
	private String interBankName;
	//开户行联行号
	private String bankCode;
	//对公对私标识
	private String perEntFlag;
	//账户人身份证号码
	private String certifyNo;
	//营业执照路径
	private String businessPhotoPath;
	//身份证人像照
	private String legalCertAttribute1Path;
	//身份证国徽照
	private String legalCertAttribute2Path;
	//门头照路径
	private String doorPhotoPath;
	//店内照路径
	private String shopInteriorPath;
	//手持身份证照
	private String handIdCardPath;
	//经营场所证明文件
	private String businessPlacePath;
	//其他材料照路径
	private String otherMaterialPath;
	//商户登录邮箱号
	private String merchantLoginEmail;
	//商户登录手机号
	private String merchantLoginMobile;
	//法人手机号
	private String representativePhone;
	//渠道返回商户号
	private String outMerchantCode;
	//进件平台商户号
	private String mchId;
	//是否电子协议
	private String agreeType;
	//返回信息
	private String resultMsg;
	//交易类型
	private List<Map> prodInfoList;
	
	private String reportStatus;
	//标识状态  1 待审核通过后更新
	private String flagStatus;
	
	
	
	public String getFlagStatus() {
		return flagStatus;
	}
	public void setFlagStatus(String flagStatus) {
		this.flagStatus = flagStatus;
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
	public String getAgreeType() {
		return agreeType;
	}
	public void setAgreeType(String agreeType) {
		this.agreeType = agreeType;
	}
	public String getHandIdCardPath() {
		return handIdCardPath;
	}
	public void setHandIdCardPath(String handIdCardPath) {
		this.handIdCardPath = handIdCardPath;
	}
	public List<Map> getProdInfoList() {
		return prodInfoList;
	}
	public void setProdInfoList(List<Map> prodInfoList) {
		this.prodInfoList = prodInfoList;
	}
	
	public String getBusinessPlacePath() {
		return businessPlacePath;
	}
	public void setBusinessPlacePath(String businessPlacePath) {
		this.businessPlacePath = businessPlacePath;
	}
	public String getCertifyNo() {
		return certifyNo;
	}
	public void setCertifyNo(String certifyNo) {
		this.certifyNo = certifyNo;
	}
	public String getChannelNo() {
		return channelNo;
	}
	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
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
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getMecTypeFlag() {
		return mecTypeFlag;
	}
	public void setMecTypeFlag(String mecTypeFlag) {
		this.mecTypeFlag = mecTypeFlag;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
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
	public String getCprRegAddr() {
		return cprRegAddr;
	}
	public void setCprRegAddr(String cprRegAddr) {
		this.cprRegAddr = cprRegAddr;
	}
	public String getCprRegNmCn() {
		return cprRegNmCn;
	}
	public void setCprRegNmCn(String cprRegNmCn) {
		this.cprRegNmCn = cprRegNmCn;
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
	public String getBusinessLicense() {
		return businessLicense;
	}
	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}
	public String getExpanduser() {
		return expanduser;
	}
	public void setExpanduser(String expanduser) {
		this.expanduser = expanduser;
	}
	public String getOfflag() {
		return offlag;
	}
	public void setOfflag(String offlag) {
		this.offlag = offlag;
	}
	public String getContractDate() {
		return contractDate;
	}
	public void setContractDate(String contractDate) {
		this.contractDate = contractDate;
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
	public String getRepresentativeCertType() {
		return representativeCertType;
	}
	public void setRepresentativeCertType(String representativeCertType) {
		this.representativeCertType = representativeCertType;
	}
	public String getIdTermStart() {
		return idTermStart;
	}
	public void setIdTermStart(String idTermStart) {
		this.idTermStart = idTermStart;
	}
	public String getIdTermEnd() {
		return idTermEnd;
	}
	public void setIdTermEnd(String idTermEnd) {
		this.idTermEnd = idTermEnd;
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
	public String getClearMode() {
		return clearMode;
	}
	public void setClearMode(String clearMode) {
		this.clearMode = clearMode;
	}
	public String getAccttp() {
		return accttp;
	}
	public void setAccttp(String accttp) {
		this.accttp = accttp;
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
	public String getBranchBank() {
		return branchBank;
	}
	public void setBranchBank(String branchBank) {
		this.branchBank = branchBank;
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
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBusinessPhotoPath() {
		return businessPhotoPath;
	}
	public void setBusinessPhotoPath(String businessPhotoPath) {
		this.businessPhotoPath = businessPhotoPath;
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
	public String getRepresentativePhone() {
		return representativePhone;
	}
	public void setRepresentativePhone(String representativePhone) {
		this.representativePhone = representativePhone;
	}
	public String getOutMerchantCode() {
		return outMerchantCode;
	}
	public void setOutMerchantCode(String outMerchantCode) {
		this.outMerchantCode = outMerchantCode;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	
	
	
	
	
	
	
}