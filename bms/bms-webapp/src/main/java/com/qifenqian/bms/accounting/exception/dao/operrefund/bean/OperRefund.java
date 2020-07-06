package com.qifenqian.bms.accounting.exception.dao.operrefund.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.qifenqian.bms.accounting.exception.base.bean.Operation;
import com.sevenpay.invoke.common.type.RequestColumnValues;

/**
 * @project sevenpay-bms-web
 * @fileName OperRefund.java
 * @author chonggan_shen
 * @date 2016年2月24日
 * @memo
 */
public class OperRefund extends Operation {
	/**
	 * 
	 */
	private static final long serialVersionUID = -254526575403496367L;

	/** 退款订单号 */
	private String orderId;
	/** 原七分钱订单号 */
	private String originOrderId;
	/** 原商户订单号 */
	private String originMerOrderId;
	/** 退款方客户号 */
	private String refundCustId;
	/** 商户客户号 */
	private String merchantCustId;
	/** 原交易报文编号 */
	private String originCoreSn;
	/** 币别 */
	private RequestColumnValues.CurrCode currCode;
	/** 原始交易金额 */
	private BigDecimal originTransAmt;
	/** 金额 */
	private BigDecimal refundAmt;
	/** 退款理由 */
	private String refundMemo;
	/** 退款申请时间 */
	private Date submitTime;
	/** 退款状态：01 待审核；02 提交核心；03核心已受理；04 退款失败；00 退款成功 */
	private String refundState;
	/** 完成退款时间 */
	private Date refundTime;
	/** 手续费：事前收的手续费 */
	private BigDecimal fee;
	/** 付手续费方 */
	private String feeCustId;
	/** 手续费状态：00事前未统计RN 01事前已统计RN */
	private String feeState;
	/** 核心流水号 */
	private String coreSn;
	/** 核心返回码 */
	private String coreReturnCode;
	/** 核心返回信息 */
	private String coreReturnMsg;
	/** 核心返回时间 */
	private Date coreReturnTime;
	/** 审核人 */
	private String modifyId;
	/** 审核时间 */
	private Date modifyTime;
	/** 审核状态（01:待审核；02:审核通过；04：审核不通过） */
	private String auditState;
	/** 核销时间 */
	private Date verificationTime;
	/** 核销状态（01:未核销；02:已核销） */
	private String verificationState;
	/** 原始交易时间 */
	private Date originTransTime;
	/** 核销人 */
	private String verificationUser;

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

	public BigDecimal getOriginTransAmt() {
		return originTransAmt;
	}

	public void setOriginTransAmt(BigDecimal originTransAmt) {
		this.originTransAmt = originTransAmt;
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

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public String getRefundState() {
		return refundState;
	}

	public void setRefundState(String refundState) {
		this.refundState = refundState;
	}

	public Date getRefundTime() {
		return refundTime;
	}

	public void setRefundTime(Date refundTime) {
		this.refundTime = refundTime;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
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

	public Date getCoreReturnTime() {
		return coreReturnTime;
	}

	public void setCoreReturnTime(Date coreReturnTime) {
		this.coreReturnTime = coreReturnTime;
	}

	public String getModifyId() {
		return modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public Date getVerificationTime() {
		return verificationTime;
	}

	public void setVerificationTime(Date verificationTime) {
		this.verificationTime = verificationTime;
	}

	public String getVerificationState() {
		return verificationState;
	}

	public void setVerificationState(String verificationState) {
		this.verificationState = verificationState;
	}

	public Date getOriginTransTime() {
		return originTransTime;
	}

	public void setOriginTransTime(Date originTransTime) {
		this.originTransTime = originTransTime;
	}

	public String getVerificationUser() {
		return verificationUser;
	}

	public void setVerificationUser(String verificationUser) {
		this.verificationUser = verificationUser;
	}

}
