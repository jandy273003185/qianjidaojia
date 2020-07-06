package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class CrInComeBean implements Serializable{

	private static final long serialVersionUID = -2144259700479599225L;
	
	private String id;
	
	private String custId;
	//商户编号
	private String merchantCode;
	//商户名称
	private String mchName;
	
	private String mchShortName;
	//行业类目
	private String categoryType;
	
	private String address;
	//客服电话
	private String customerPhone;
	
	private String linkman;
	
	private String phone;
	
	private String email;
	
	private String operator;
	//商户角色 0 线上商户,1 线下商户'
	private String mchRole;
	
	private String operatorIdno;
	
	private String linenceNo;
	
	private String certifcateType;
	
	private String contractsType;
	
	private String type;
	
	private String name;
	
	private String bankCardNo;
	
	private String subBranchName;
	
	private String cardType;
	
	private String province;
	
	private String city;
	
	private String country;
	
	private String bankCode;
	
	private String idCardPath;
	
	private String businessPath;
	//报备批次号
	private String patchNo;
	//报备渠道
	private String channelNo;
	//报备状态 01 未提交报备，00已提交报备'
	private String filingStatus;
	//外部商户号
	private String outMerchantCode;
	
	private String interBank;//开户支行
	
	private String interBankName;//开户支行名称
	
	private String jylb;//经营类别
	
	private String firstType;//一级类目
	
	private String secondType;//二级类目
	
	private String thirdType;//三级类目
	
	private String powerId;//支付功能ID
	
	private String appId;//appId
	
	private String mobileNo;//银行卡预留手机号
	
	private String qq;//联系人QQ
	
	/** 身份证正面图片 */
	private String identityCardFrontPic;
	
	/** 身份证反面图片 */
	private String identityCardReversePic;
	
	/** 营业许可证图片 */
	private String licensePic;
	
	/** 店铺内景照片 */
	private String storeInteriorPic;
	
	/** 店铺招牌照片 */
	private String storeSignBoardPic;
	
	/** 费率*/
	private String rate;
	
	/** 翼支付商户行业信息*/
	private String industryCode;
	
	/**翼支付商户是否有证*/
	private String bestMerchantType;
	
	/**开户人姓名*/
	private String interName;
	
	/**身份证号*/
	private String certifyNo;
	
	private String reportStatus;
	
	
	



	public String getReportStatus() {
		return reportStatus;
	}



	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}



	public String getCertifyNo() {
		return certifyNo;
	}



	public void setCertifyNo(String certifyNo) {
		this.certifyNo = certifyNo;
	}



	public String getInterName() {
		return interName;
	}



	public void setInterName(String interName) {
		this.interName = interName;
	}



	public String getBestMerchantType() {
		return bestMerchantType;
	}



	public void setBestMerchantType(String bestMerchantType) {
		this.bestMerchantType = bestMerchantType;
	}



	public String getIndustryCode() {
		return industryCode;
	}



	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}



	public String getRate() {
		return rate;
	}



	public void setRate(String rate) {
		this.rate = rate;
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



	public String getStoreInteriorPic() {
		return storeInteriorPic;
	}



	public void setStoreInteriorPic(String storeInteriorPic) {
		this.storeInteriorPic = storeInteriorPic;
	}



	public String getStoreSignBoardPic() {
		return storeSignBoardPic;
	}



	public void setStoreSignBoardPic(String storeSignBoardPic) {
		this.storeSignBoardPic = storeSignBoardPic;
	}



	public String getMobileNo() {
		return mobileNo;
	}



	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}



	public String getQq() {
		return qq;
	}



	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * 结算卡号
	 */
	private String accountNo;
	
	/**
	 * 结算户名
	 */
	private String accountName;
	
	/**
	 * 联行号
	 */
	private String CNAPS;
	
	
	
	
	public String getInterBank() {
		return interBank;
	}



	public void setInterBank(String interBank) {
		this.interBank = interBank;
	}



	public String getInterBankName() {
		return interBankName;
	}



	public void setInterBankName(String interBankName) {
		this.interBankName = interBankName;
	}



	public String getJylb() {
		return jylb;
	}



	public void setJylb(String jylb) {
		this.jylb = jylb;
	}



	public String getFirstType() {
		return firstType;
	}



	public void setFirstType(String firstType) {
		this.firstType = firstType;
	}



	public String getSecondType() {
		return secondType;
	}



	public void setSecondType(String secondType) {
		this.secondType = secondType;
	}



	public String getThirdType() {
		return thirdType;
	}



	public void setThirdType(String thirdType) {
		this.thirdType = thirdType;
	}



	public String getPowerId() {
		return powerId;
	}



	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}



	public String getAppId() {
		return appId;
	}



	public void setAppId(String appId) {
		this.appId = appId;
	}



	public String getAccountNo() {
		return accountNo;
	}
	
	

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCNAPS() {
		return CNAPS;
	}

	public void setCNAPS(String cNAPS) {
		CNAPS = cNAPS;
	}

	public String getOutMerchantCode() {
		return outMerchantCode;
	}

	public void setOutMerchantCode(String outMerchantCode) {
		this.outMerchantCode = outMerchantCode;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFilingStatus() {
		return filingStatus;
	}

	public void setFilingStatus(String filingStatus) {
		this.filingStatus = filingStatus;
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

	public String getIdCardPath() {
		return idCardPath;
	}

	public void setIdCardPath(String idCardPath) {
		this.idCardPath = idCardPath;
	}

	public String getBusinessPath() {
		return businessPath;
	}

	public void setBusinessPath(String businessPath) {
		this.businessPath = businessPath;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getMchName() {
		return mchName;
	}

	public void setMchName(String mchName) {
		this.mchName = mchName;
	}

	public String getMchShortName() {
		return mchShortName;
	}

	public void setMchShortName(String mchShortName) {
		this.mchShortName = mchShortName;
	}

	public String getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getLinkman() {
		return linkman;
	}

	public void setLinkman(String linkman) {
		this.linkman = linkman;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getMchRole() {
		return mchRole;
	}

	public void setMchRole(String mchRole) {
		this.mchRole = mchRole;
	}

	public String getOperatorIdno() {
		return operatorIdno;
	}

	public void setOperatorIdno(String operatorIdno) {
		this.operatorIdno = operatorIdno;
	}

	public String getLinenceNo() {
		return linenceNo;
	}

	public void setLinenceNo(String linenceNo) {
		this.linenceNo = linenceNo;
	}

	public String getCertifcateType() {
		return certifcateType;
	}

	public void setCertifcateType(String certifcateType) {
		this.certifcateType = certifcateType;
	}

	public String getContractsType() {
		return contractsType;
	}

	public void setContractsType(String contractsType) {
		this.contractsType = contractsType;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getSubBranchName() {
		return subBranchName;
	}

	public void setSubBranchName(String subBranchName) {
		this.subBranchName = subBranchName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
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
	
}
