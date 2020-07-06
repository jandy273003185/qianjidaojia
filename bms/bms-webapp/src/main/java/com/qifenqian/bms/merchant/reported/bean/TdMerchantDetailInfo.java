package com.qifenqian.bms.merchant.reported.bean;

public class TdMerchantDetailInfo extends publicBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6376793115162809719L;

	private String patchNo;//报备批次号
	
	private String merchantCode;//商户编号
	
	private String reportStatus;//商户进件状态
	
	private String fileStatus;//商户文件状态
	
	private String outMerchantCode;//外部商户号
	
	private String remark;//备注
	
	private String custName;//商户名称
	
	private String channelNo;//报备渠道
	
	private String id;//报备编号
	
	private String reportTime;//报备时间
	
	private String provCode;//省份编号
	
	private String cityCode;//城市编号
	
	private String contryCode;//区县编号
	
	private String bankCode;//开户行号
	
	private String branchBankCode;//开户支行联行号
	
	private String branchBankName;//开户支行名称
	
	private String mobileNo;//银行卡预留手机号
	
	private String customerPhone;//客服电话
	
	private String qq;//联系人QQ
	
	private String detailStatus;//商户报备状态
	
	private String powerId;//支付功能id
	
	private String appId;//支付功能id
	
	private String category;//支付功能id
	
	private String loginNo; //翼支付企业LoginNo
	
	private String bestMerchantType;  //翼支付企业类型
	
	private String bestpayMctNo;//翼支付返回商户号
	
	private String channelCode;
	
	private String resultMsg; //返回信息
	
	private String shortName;
	
	private String email;
	
	private String startModifyTime;

	private String endModifyTime;
	
	private String rate;
	
	private String zfbBatchNo;
	
	private String zfbBatchStatus;
	
	private String zfbUserId;
	
	private String zfbAuthAppId;
	
	private String zfbAppAuthToken;
	
	private String zfbAppRefreshToken;
	
	private String zfbExpiresIn;
	
	private String zfbReExpiresIn;
	
	private String zfbAccount;
	
	private String zfbConfirmUrl;
	
	private String merchantPid;
	
	/**
	 * 微信支付分配申请单号
	 */
	private String applymentId;
	/**
	 * 微信返回签约链接
	 */
	private String signUrl;
	/**
	 * 结算账户
	 */
	private String accountNumber;
	
	/**
	 * 签约二维码
	 */
	private String signQrcode;
	
	/**
	 * 客户号
	 */
	private String custId;
	/**
	 * 微信申请单编号
	 */
	private String wxApplyNo;
	/**
	 * 微信实名认证状态
	 */
	private String idenStatus;
	/**
	 * 微信子商户号
	 */
	private String wxChildNo;
	/**
	 * 支付宝子商户
	 */
	private String zfbChildNo;
	
	private String status;
	
	private String mobile;
	
	private String channelMark;
	
	
	
	public String getChannelMark() {
		return channelMark;
	}

	public void setChannelMark(String channelMark) {
		this.channelMark = channelMark;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMerchantPid() {
		return merchantPid;
	}

	public void setMerchantPid(String merchantPid) {
		this.merchantPid = merchantPid;
	}

	public String getWxChildNo() {
		return wxChildNo;
	}

	public void setWxChildNo(String wxChildNo) {
		this.wxChildNo = wxChildNo;
	}

	public String getZfbChildNo() {
		return zfbChildNo;
	}

	public void setZfbChildNo(String zfbChildNo) {
		this.zfbChildNo = zfbChildNo;
	}

	public String getWxApplyNo() {
		return wxApplyNo;
	}

	public void setWxApplyNo(String wxApplyNo) {
		this.wxApplyNo = wxApplyNo;
	}

	public String getIdenStatus() {
		return idenStatus;
	}

	public void setIdenStatus(String idenStatus) {
		this.idenStatus = idenStatus;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getSignQrcode() {
		return signQrcode;
	}

	public void setSignQrcode(String signQrcode) {
		this.signQrcode = signQrcode;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getSignUrl() {
		return signUrl;
	}

	public void setSignUrl(String signUrl) {
		this.signUrl = signUrl;
	}

	public String getApplymentId() {
		return applymentId;
	}

	public void setApplymentId(String applymentId) {
		this.applymentId = applymentId;
	}

	public String getZfbAccount() {
		return zfbAccount;
	}

	public void setZfbAccount(String zfbAccount) {
		this.zfbAccount = zfbAccount;
	}

	public String getZfbConfirmUrl() {
		return zfbConfirmUrl;
	}

	public void setZfbConfirmUrl(String zfbConfirmUrl) {
		this.zfbConfirmUrl = zfbConfirmUrl;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}
	
	public String getZfbBatchNo() {
		return zfbBatchNo;
	}

	public void setZfbBatchNo(String zfbBatchNo) {
		this.zfbBatchNo = zfbBatchNo;
	}

	public String getZfbBatchStatus() {
		return zfbBatchStatus;
	}

	public void setZfbBatchStatus(String zfbBatchStatus) {
		this.zfbBatchStatus = zfbBatchStatus;
	}

	public String getZfbUserId() {
		return zfbUserId;
	}

	public void setZfbUserId(String zfbUserId) {
		this.zfbUserId = zfbUserId;
	}

	public String getZfbAuthAppId() {
		return zfbAuthAppId;
	}

	public void setZfbAuthAppId(String zfbAuthAppId) {
		this.zfbAuthAppId = zfbAuthAppId;
	}

	public String getZfbAppAuthToken() {
		return zfbAppAuthToken;
	}

	public void setZfbAppAuthToken(String zfbAppAuthToken) {
		this.zfbAppAuthToken = zfbAppAuthToken;
	}

	public String getZfbAppRefreshToken() {
		return zfbAppRefreshToken;
	}

	public void setZfbAppRefreshToken(String zfbAppRefreshToken) {
		this.zfbAppRefreshToken = zfbAppRefreshToken;
	}

	public String getZfbExpiresIn() {
		return zfbExpiresIn;
	}

	public void setZfbExpiresIn(String zfbExpiresIn) {
		this.zfbExpiresIn = zfbExpiresIn;
	}

	public String getZfbReExpiresIn() {
		return zfbReExpiresIn;
	}

	public void setZfbReExpiresIn(String zfbReExpiresIn) {
		this.zfbReExpiresIn = zfbReExpiresIn;
	}
	

	public String getStartModifyTime() {
		return startModifyTime;
	}

	public void setStartModifyTime(String startModifyTime) {
		this.startModifyTime = startModifyTime;
	}

	public String getEndModifyTime() {
		return endModifyTime;
	}

	public void setEndModifyTime(String endModifyTime) {
		this.endModifyTime = endModifyTime;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getChannelCode() {
		return channelCode;
	}

	public void setChannelCode(String channelCode) {
		this.channelCode = channelCode;
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDetailStatus() {
		return detailStatus;
	}

	public void setDetailStatus(String detailStatus) {
		this.detailStatus = detailStatus;
	}
	

	public String getProvCode() {
		return provCode;
	}

	public void setProvCode(String provCode) {
		this.provCode = provCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getContryCode() {
		return contryCode;
	}

	public void setContryCode(String contryCode) {
		this.contryCode = contryCode;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBranchBankCode() {
		return branchBankCode;
	}

	public void setBranchBankCode(String branchBankCode) {
		this.branchBankCode = branchBankCode;
	}

	public String getBranchBankName() {
		return branchBankName;
	}

	public void setBranchBankName(String branchBankName) {
		this.branchBankName = branchBankName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReportTime() {
		return reportTime;
	}

	public void setReportTime(String reportTime) {
		this.reportTime = reportTime;
	}

	public String getChannelNo() {
		return channelNo;
	}

	public void setChannelNo(String channelNo) {
		this.channelNo = channelNo;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
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

	public String getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}

	public String getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(String fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getOutMerchantCode() {
		return outMerchantCode;
	}

	public void setOutMerchantCode(String outMerchantCode) {
		this.outMerchantCode = outMerchantCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getLoginNo() {
		return loginNo;
	}

	public void setLoginNo(String loginNo) {
		this.loginNo = loginNo;
	}

	public String getBestMerchantType() {
		return bestMerchantType;
	}

	public void setBestMerchantType(String bestMerchantType) {
		this.bestMerchantType = bestMerchantType;
	}

	public String getBestpayMctNo() {
		return bestpayMctNo;
	}

	public void setBestpayMctNo(String bestpayMctNo) {
		this.bestpayMctNo = bestpayMctNo;
	}

	
	
}
