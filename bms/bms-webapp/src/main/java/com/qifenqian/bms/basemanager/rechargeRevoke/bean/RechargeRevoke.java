package com.qifenqian.bms.basemanager.rechargeRevoke.bean;

import java.math.BigDecimal;
import java.util.Date;

import com.sevenpay.invoke.common.type.RequestColumnValues;

public class RechargeRevoke {
	/** 订单号 */
	private String orderId;
	/** 原七分钱订单号 */
	private String originOrderId;

	private Date originRechargeTime;

	/** 充值客户号 */
	private String rechargeCustId;
	/** 币别 */
	private RequestColumnValues.CurrCode currCode;
	/** 撤销金额 */
	private BigDecimal revokeAmt;
	/** 撤销理由 */
	private String revokeMemo;
	/** 撤销申请人 */
	private String createId;
	/** 撤销申请时间 */
	private String createTime;
	/**
	 * 01：待处理；02：提交核心处理；03：核心返回失败；04：核心返回成功；05：提交银联处理 ；06：银联返回失败；07：银联返回成功；00
	 * 撤销成功；99：取消
	 */
	private String orderStatus;
	/** 核心流水号 */
	private String coreSn;
	/** 核心返回码 */
	private String coreReturnCode;
	/** 核心返回信息 */
	private String coreReturnMsg;
	/** 核心返回时间 */
	private Date coreReturnTime;
	/** 审核人 */
	private String auditId;
	/** 审核时间 */
	private String auditTime;
	/** 审核状态 01:待审核；02:审核通过；04：审核不通过 */
	private String auditState;

	private String revokeBeginTime;

	private String revokeEndTime;

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

	public String getRechargeCustId() {
		return rechargeCustId;
	}

	public Date getOriginRechargeTime() {
		return originRechargeTime;
	}

	public void setOriginRechargeTime(Date originRechargeTime) {
		this.originRechargeTime = originRechargeTime;
	}

	public void setRechargeCustId(String rechargeCustId) {
		this.rechargeCustId = rechargeCustId;
	}

	public RequestColumnValues.CurrCode getCurrCode() {
		return currCode;
	}

	public void setCurrCode(RequestColumnValues.CurrCode currCode) {
		this.currCode = currCode;
	}

	public BigDecimal getRevokeAmt() {
		return revokeAmt;
	}

	public void setRevokeAmt(BigDecimal revokeAmt) {
		this.revokeAmt = revokeAmt;
	}

	public String getRevokeMemo() {
		return revokeMemo;
	}

	public void setRevokeMemo(String revokeMemo) {
		this.revokeMemo = revokeMemo;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
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

	public String getAuditId() {
		return auditId;
	}

	public void setAuditId(String auditId) {
		this.auditId = auditId;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getAuditState() {
		return auditState;
	}

	public void setAuditState(String auditState) {
		this.auditState = auditState;
	}

	public String getRevokeBeginTime() {
		return revokeBeginTime;
	}

	public void setRevokeBeginTime(String revokeBeginTime) {
		this.revokeBeginTime = revokeBeginTime;
	}

	public String getRevokeEndTime() {
		return revokeEndTime;
	}

	public void setRevokeEndTime(String revokeEndTime) {
		this.revokeEndTime = revokeEndTime;
	}

}
