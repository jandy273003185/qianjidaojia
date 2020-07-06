package com.qifenqian.bms.accounting.refund.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/***
 * 
 * @author shen
 * 
 */
@SuppressWarnings("serial")
public class RefundExcel implements Serializable {
	/**
	 * 
	 */
	/** 退款订单号 **/
	private String orderId;

	/** 原始订单号 **/
	private String originOrderId;

	private String rtnSeq;

	private String msgType;
	/** 原商户订单号 **/
	private String originMerOrderId;

	/** 客户账号 **/
	private String mobile;

	private BigDecimal originTransAmt;

	/** 原始交易时间 **/
	private String originTransTime;

	/** 商户编号 **/
	private String merchantCustId;

	/** 商户名称 **/
	private String custName;

	/** 退款金额 **/
	private BigDecimal refundAmt;

	/** 退款申请时间 **/
	private String submitTime;

	private String workDate;

	/** 手续费 **/
	private String fee;

	/** 付手续费方 **/
	private String feeCustId;

	/** 退款状态 **/
	private String refundState;

	private String auditState;

	private String modifyId;

	private String modifyTime;

	private String verificationState;

	private String verificationUser;

	private String verificationTime;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOriginOrderId() {
		return originOrderId;
	}

	public void setOriginOrderId(String originOrderId) {
		this.originOrderId = originOrderId;
	}

	public String getRtnSeq() {
		return rtnSeq;
	}

	public void setRtnSeq(String rtnSeq) {
		this.rtnSeq = rtnSeq;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getOriginMerOrderId() {
		return originMerOrderId;
	}

	public void setOriginMerOrderId(String originMerOrderId) {
		this.originMerOrderId = originMerOrderId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public BigDecimal getOriginTransAmt() {
		return originTransAmt;
	}

	public void setOriginTransAmt(BigDecimal originTransAmt) {
		this.originTransAmt = originTransAmt;
	}

	public String getOriginTransTime() {
		return originTransTime;
	}

	public void setOriginTransTime(String originTransTime) {
		this.originTransTime = originTransTime;
	}

	public String getMerchantCustId() {
		return merchantCustId;
	}

	public void setMerchantCustId(String merchantCustId) {
		this.merchantCustId = merchantCustId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public BigDecimal getRefundAmt() {
		return refundAmt;
	}

	public void setRefundAmt(BigDecimal refundAmt) {
		this.refundAmt = refundAmt;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getFeeCustId() {
		return feeCustId;
	}

	public void setFeeCustId(String feeCustId) {
		this.feeCustId = feeCustId;
	}

	public String getRefundState() {
		return refundState;
	}

	public void setRefundState(String refundState) {
		this.refundState = refundState;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
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

	public String getVerificationState() {
		return verificationState;
	}

	public void setVerificationState(String verificationState) {
		this.verificationState = verificationState;
	}

	public String getVerificationUser() {
		return verificationUser;
	}

	public void setVerificationUser(String verificationUser) {
		this.verificationUser = verificationUser;
	}

	public String getVerificationTime() {
		return verificationTime;
	}

	public void setVerificationTime(String verificationTime) {
		this.verificationTime = verificationTime;
	}

}
