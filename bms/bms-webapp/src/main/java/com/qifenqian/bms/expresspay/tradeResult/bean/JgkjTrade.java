package com.qifenqian.bms.expresspay.tradeResult.bean;

import java.math.BigDecimal;

public class JgkjTrade {

	private String transId;
	private String transFlowId;
	private String businessType;
	private String cardNo;
	private BigDecimal transAmt;
	private String orderNo;
	private String sendDatetime;
	private String workDate;
	private String status;
	private String rtnTime;
	private String rtnSeq;
	private String rtnCode;
	public String getTransId() {
		return transId;
	}
	public void setTransId(String transId) {
		this.transId = transId;
	}
	public String getTransFlowId() {
		return transFlowId;
	}
	public void setTransFlowId(String transFlowId) {
		this.transFlowId = transFlowId;
	}
	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public BigDecimal getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getSendDatetime() {
		return sendDatetime;
	}
	public void setSendDatetime(String sendDatetime) {
		this.sendDatetime = sendDatetime;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRtnTime() {
		return rtnTime;
	}
	public void setRtnTime(String rtnTime) {
		this.rtnTime = rtnTime;
	}
	public String getRtnSeq() {
		return rtnSeq;
	}
	public void setRtnSeq(String rtnSeq) {
		this.rtnSeq = rtnSeq;
	}
	public String getRtnCode() {
		return rtnCode;
	}
	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}



}
