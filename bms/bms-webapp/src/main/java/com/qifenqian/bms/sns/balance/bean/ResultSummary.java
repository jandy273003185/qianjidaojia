package com.qifenqian.bms.sns.balance.bean;

import java.math.BigDecimal;


public class ResultSummary extends ResultBase{
    /** 批次编号*/
	private String batchId;
    /** 对账日期*/
	private String balDate;
    /** 会计日期*/
	private String workDate;
    /** 红包日期*/
	private String redenvDate;
    /** 红包流水号*/
	private String redenvId;
    /** 红包个数*/
	private String redenvCount;
    /** 总金额*/
	private BigDecimal redenvAmt;
    /** 红包出账状态：00 成功；99 失败*/
	private String transStatus;
    /** 领取入账成功个数*/
	private String inSuccessCount;
    /** */
	private BigDecimal inSuccessAmt;
    /** 领取入账失败个数*/
	private String inFailureCount;
    /** 领取入账失败总金额*/
	private BigDecimal inFailureAmt;
    /** 未入账个数*/
	private String notInCount;
    /** 未入账总金额*/
	private BigDecimal notInAmt;
    /** 退款金额*/
	private BigDecimal refundAmt;
    /** 退款状态：00 成功；99 失败*/
	private String refundStatus;
	
    /** 状态:EXCEPTION 对账异常；REUAL 对账一致*/
	private String balStatus;

    /** 对账处理备注*/
	private String balMemo;
    /** 处理状态:01 待处理；00 处理完成；99 无需处理*/
	private String dealStatusDesc;
    /** 异常处理人*/
	private String dealUser;
    /** 异常处理时间*/
	private String dealDatetime;
    /** 异常处理备注*/
	private String dealMemo;
	
	private String status;
	
	private String dealStatus;

	public String getBatchId() {
		return batchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}

	public String getBalDate() {
		return balDate;
	}

	public void setBalDate(String balDate) {
		this.balDate = balDate;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getRedenvDate() {
		return redenvDate;
	}

	public void setRedenvDate(String redenvDate) {
		this.redenvDate = redenvDate;
	}

	public String getRedenvId() {
		return redenvId;
	}

	public void setRedenvId(String redenvId) {
		this.redenvId = redenvId;
	}

	public String getRedenvCount() {
		return redenvCount;
	}

	public void setRedenvCount(String redenvCount) {
		this.redenvCount = redenvCount;
	}

	public BigDecimal getRedenvAmt() {
		return redenvAmt;
	}

	public void setRedenvAmt(BigDecimal redenvAmt) {
		this.redenvAmt = redenvAmt;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getInSuccessCount() {
		return inSuccessCount;
	}

	public void setInSuccessCount(String inSuccessCount) {
		this.inSuccessCount = inSuccessCount;
	}

	public BigDecimal getInSuccessAmt() {
		return inSuccessAmt;
	}

	public void setInSuccessAmt(BigDecimal inSuccessAmt) {
		this.inSuccessAmt = inSuccessAmt;
	}

	public String getInFailureCount() {
		return inFailureCount;
	}

	public void setInFailureCount(String inFailureCount) {
		this.inFailureCount = inFailureCount;
	}

	public BigDecimal getInFailureAmt() {
		return inFailureAmt;
	}

	public void setInFailureAmt(BigDecimal inFailureAmt) {
		this.inFailureAmt = inFailureAmt;
	}

	public String getNotInCount() {
		return notInCount;
	}

	public void setNotInCount(String notInCount) {
		this.notInCount = notInCount;
	}

	public BigDecimal getNotInAmt() {
		return notInAmt;
	}

	public void setNotInAmt(BigDecimal notInAmt) {
		this.notInAmt = notInAmt;
	}

	public BigDecimal getRefundAmt() {
		return refundAmt;
	}

	public void setRefundAmt(BigDecimal refundAmt) {
		this.refundAmt = refundAmt;
	}

	public String getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}

	public String getBalStatus() {
		return balStatus;
	}

	public void setBalStatus(String balStatus) {
		this.balStatus = balStatus;
	}

	public String getBalMemo() {
		return balMemo;
	}

	public void setBalMemo(String balMemo) {
		this.balMemo = balMemo;
	}

	public String getDealStatusDesc() {
		return dealStatusDesc;
	}

	public void setDealStatusDesc(String dealStatusDesc) {
		this.dealStatusDesc = dealStatusDesc;
	}

	public String getDealUser() {
		return dealUser;
	}

	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}

	public String getDealDatetime() {
		return dealDatetime;
	}

	public void setDealDatetime(String dealDatetime) {
		this.dealDatetime = dealDatetime;
	}

	public String getDealMemo() {
		return dealMemo;
	}

	public void setDealMemo(String dealMemo) {
		this.dealMemo = dealMemo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}
	
}
