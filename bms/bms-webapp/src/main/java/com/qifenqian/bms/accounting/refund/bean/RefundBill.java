package com.qifenqian.bms.accounting.refund.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.sevenpay.invoke.common.type.RequestColumnValues;

/***
 * 
 * @author shen
 * 
 */
public class RefundBill implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4552258549883270992L;
	/** 退款订单号 **/
	private String orderId;
	/** 原始订单号 **/
	private String originOrderId;
	/** 原商户订单号 **/
	private String originMerOrderId;

	private String msgType;
	/** 客户编号 **/
	private String refundCustId;
	/** 商户编号 **/
	private String merchantCustId;
	/** 原始核心报文编号 **/
	private String originCoreSn;
	/** 币别 **/
	private RequestColumnValues.CurrCode currCode;
	/** 退款金额 **/
	private BigDecimal refundAmt;
	/** 退款理由 **/
	private String refundMemo;
	/** 退款申请时间 **/
	private String submitTime;
	/** 退款状态 **/
	private String refundState;
	/** 退款完成时间 **/
	private String refundTime;
	/** 手续费 **/
	private String fee;
	/** 付手续费方 **/
	private String feeCustId;
	/** 手续费状态 **/
	private String feeState;
	/** 核心报文编号 **/
	private String coreSn;
	/** 原始交易时金额 **/
	private BigDecimal originTransAmt;

	/** 原始交易时间 **/
	private Date originTransTime;
	/** 核心返回码 **/
	private String coreReturnCode;
	/** 核心返回信息 **/
	private String coreReturnMsg;
	/** 核心返回时间 **/
	private String coreReturnTime;
	/** 审核人 **/
	private String modifyId;
	/** 审核时间 **/
	private String modifyTime;
	/** 审核状态 **/
	private String auditState;
	/** 核销时间 **/
	private String verificationTime;
	/** 核销状态 **/
	private String verificationState;
	/** 核销人 **/
	private String verificationUser;

	private String rtnSeq;

	private String workDate;

	private String startTime;

	private String endTime;
	/** 客户账号 **/
	private String mobile;
	/** 客户名称 **/
	private String custName;
	private String originBeginTime;
	private String originEndTime;
	private String startWorkDate;
	private String endWorkDate;

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

	public String getOriginMerOrderId() {
		return originMerOrderId;
	}

	public void setOriginMerOrderId(String originMerOrderId) {
		this.originMerOrderId = originMerOrderId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getRefundCustId() {
		return refundCustId;
	}

	public void setRefundCustId(String refundCustId) {
		this.refundCustId = refundCustId;
	}

	public String getMerchantCustId() {
		return merchantCustId;
	}

	public void setMerchantCustId(String merchantCustId) {
		this.merchantCustId = merchantCustId;
	}

	public String getOriginCoreSn() {
		return originCoreSn;
	}

	public void setOriginCoreSn(String originCoreSn) {
		this.originCoreSn = originCoreSn;
	}

	public RequestColumnValues.CurrCode getCurrCode() {
		return currCode;
	}

	public void setCurrCode(RequestColumnValues.CurrCode currCode) {
		this.currCode = currCode;
	}

	public BigDecimal getRefundAmt() {
		return refundAmt;
	}

	public void setRefundAmt(BigDecimal refundAmt) {
		this.refundAmt = refundAmt;
	}

	public String getRefundMemo() {
		return refundMemo;
	}

	public void setRefundMemo(String refundMemo) {
		this.refundMemo = refundMemo;
	}

	public String getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(String submitTime) {
		this.submitTime = submitTime;
	}

	public String getRefundState() {
		return refundState;
	}

	public void setRefundState(String refundState) {
		this.refundState = refundState;
	}

	public String getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(String refundTime) {
		this.refundTime = refundTime;
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

	public String getFeeState() {
		return feeState;
	}

	public void setFeeState(String feeState) {
		this.feeState = feeState;
	}

	public String getCoreSn() {
		return coreSn;
	}

	public void setCoreSn(String coreSn) {
		this.coreSn = coreSn;
	}

	public BigDecimal getOriginTransAmt() {
		return originTransAmt;
	}

	public void setOriginTransAmt(BigDecimal originTransAmt) {
		this.originTransAmt = originTransAmt;
	}

	public Date getOriginTransTime() {
		return originTransTime;
	}

	public void setOriginTransTime(Date originTransTime) {
		this.originTransTime = originTransTime;
	}

	public String getCoreReturnCode() {
		return coreReturnCode;
	}

	public void setCoreReturnCode(String coreReturnCode) {
		this.coreReturnCode = coreReturnCode;
	}

	public String getCoreReturnMsg() {
		return coreReturnMsg;
	}

	public void setCoreReturnMsg(String coreReturnMsg) {
		this.coreReturnMsg = coreReturnMsg;
	}

	public String getCoreReturnTime() {
		return coreReturnTime;
	}

	public void setCoreReturnTime(String coreReturnTime) {
		this.coreReturnTime = coreReturnTime;
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

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getVerificationTime() {
		return verificationTime;
	}

	public void setVerificationTime(String verificationTime) {
		this.verificationTime = verificationTime;
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

	public String getRtnSeq() {
		return rtnSeq;
	}

	public void setRtnSeq(String rtnSeq) {
		this.rtnSeq = rtnSeq;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
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

	public String getOriginBeginTime() {
		return originBeginTime;
	}

	public void setOriginBeginTime(String originBeginTime) {
		this.originBeginTime = originBeginTime;
	}

	public String getOriginEndTime() {
		return originEndTime;
	}

	public void setOriginEndTime(String originEndTime) {
		this.originEndTime = originEndTime;
	}

	public String getStartWorkDate() {
		return startWorkDate;
	}

	public void setStartWorkDate(String startWorkDate) {
		this.startWorkDate = startWorkDate;
	}

	public String getEndWorkDate() {
		return endWorkDate;
	}

	public void setEndWorkDate(String endWorkDate) {
		this.endWorkDate = endWorkDate;
	}

}
