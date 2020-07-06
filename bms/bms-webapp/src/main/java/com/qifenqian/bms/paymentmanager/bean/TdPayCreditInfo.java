package com.qifenqian.bms.paymentmanager.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/***
 * 付款凭证信息类
 * 
 * @author houmianmian
 *
 */
public class TdPayCreditInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*** 凭证编号 **/
	private String id;
	/*** 付款账号 ***/
	private String pareAcctNo;
	/*** 收款账号 ***/
	private String recAccountNo;
	/*** 文件路径 ***/
	private String scanCopyPath;
	/*** 上传时间 ***/
	private String uploadTime;
	/*** 打款状态 00：成功； 99：未打款 ***/
	private String payStatus;
	/*** 创建时间 ***/
	private String createTime;
	/*** 修改时间 ***/
	private String modifyTime;
	/*** 批次号 ***/
	private String batNo;

	private String email;

	private String mobile;

	private String custName;

	private String auditStatus;

	private String endCreateTime;

	private String startCreateTime;

	private String sumMoney;

	private String fristAuditTime;

	private String secondAuditTime;

	private String secondEndAuditTime;

	private String auditAmt;

	private String fristEndAuditTime;

	private BigDecimal rechargeAmt;

	private String merchantCode;

	private String recMerchantNo;
	// 付给上游渠道的充值手续费
	private BigDecimal channelRechargeFeeamt;

	// 收取商户的充值手续费
	private BigDecimal rechargeFeeamt;

	public BigDecimal getChannelRechargeFeeamt() {
		return channelRechargeFeeamt;
	}

	public void setChannelRechargeFeeamt(BigDecimal channelRechargeFeeamt) {
		this.channelRechargeFeeamt = channelRechargeFeeamt;
	}

	public BigDecimal getRechargeFeeamt() {
		return rechargeFeeamt;
	}

	public void setRechargeFeeamt(BigDecimal rechargeFeeamt) {
		this.rechargeFeeamt = rechargeFeeamt;
	}

	public String getRecMerchantNo() {
		return recMerchantNo;
	}

	public void setRecMerchantNo(String recMerchantNo) {
		this.recMerchantNo = recMerchantNo;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public BigDecimal getRechargeAmt() {
		return rechargeAmt;
	}

	public void setRechargeAmt(BigDecimal rechargeAmt) {
		this.rechargeAmt = rechargeAmt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPareAcctNo() {
		return pareAcctNo;
	}

	public void setPareAcctNo(String pareAcctNo) {
		this.pareAcctNo = pareAcctNo;
	}

	public String getRecAccountNo() {
		return recAccountNo;
	}

	public void setRecAccountNo(String recAccountNo) {
		this.recAccountNo = recAccountNo;
	}

	public String getScanCopyPath() {
		return scanCopyPath;
	}

	public void setScanCopyPath(String scanCopyPath) {
		this.scanCopyPath = scanCopyPath;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(String payStatus) {
		this.payStatus = payStatus;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getBatNo() {
		return batNo;
	}

	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(String startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	public String getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(String sumMoney) {
		this.sumMoney = sumMoney;
	}

	public String getFristAuditTime() {
		return fristAuditTime;
	}

	public void setFristAuditTime(String fristAuditTime) {
		this.fristAuditTime = fristAuditTime;
	}

	public String getSecondAuditTime() {
		return secondAuditTime;
	}

	public void setSecondAuditTime(String secondAuditTime) {
		this.secondAuditTime = secondAuditTime;
	}

	public String getAuditAmt() {
		return auditAmt;
	}

	public void setAuditAmt(String auditAmt) {
		this.auditAmt = auditAmt;
	}

	public String getSecondEndAuditTime() {
		return secondEndAuditTime;
	}

	public void setSecondEndAuditTime(String secondEndAuditTime) {
		this.secondEndAuditTime = secondEndAuditTime;
	}

	public String getFristEndAuditTime() {
		return fristEndAuditTime;
	}

	public void setFristEndAuditTime(String fristEndAuditTime) {
		this.fristEndAuditTime = fristEndAuditTime;
	}

	@Override
	public String toString() {
		return "TdPayCreditInfo [id=" + id + ", pareAcctNo=" + pareAcctNo + ", recAccountNo=" + recAccountNo
				+ ", scanCopyPath=" + scanCopyPath + ", uploadTime=" + uploadTime + ", payStatus=" + payStatus
				+ ", createTime=" + createTime + ", modifyTime=" + modifyTime + ", batNo=" + batNo + ", email=" + email
				+ ", mobile=" + mobile + ", custName=" + custName + ", auditStatus=" + auditStatus + ", endCreateTime="
				+ endCreateTime + ", startCreateTime=" + startCreateTime + ", sumMoney=" + sumMoney
				+ ", fristAuditTime=" + fristAuditTime + ", secondAuditTime=" + secondAuditTime
				+ ", secondEndAuditTime=" + secondEndAuditTime + ", auditAmt=" + auditAmt + ", fristEndAuditTime="
				+ fristEndAuditTime + ", rechargeAmt=" + rechargeAmt + ", merchantCode=" + merchantCode
				+ ", recMerchantNo=" + recMerchantNo + ", channelRechargeFeeamt=" + channelRechargeFeeamt
				+ ", rechargeFeeamt=" + rechargeFeeamt + "]";
	}
}
