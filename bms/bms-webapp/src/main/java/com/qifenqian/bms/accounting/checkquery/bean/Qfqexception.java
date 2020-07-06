package com.qifenqian.bms.accounting.checkquery.bean;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class Qfqexception extends QfqexceptionBase{
	private String balDate;
	private String clearId;
	private String batchId;
	private String transFlowId;
	private String businessType;
	private String transCode;
	private String cardNo;
	private String currCode;
	private BigDecimal transAmt;
	private String workDate;
	private String sendDate;
	private String sevenTime;
	private String transStatus;
	private String rtnDate;
	private String rtnTime;
	private String rtnSeq;
	private String rtnCode;
	private String rtnInfo;
	private String instDate;
	private String instDatetime;
	private String balResult;
	private String balDatetime;
	private String balMemo;
	private String dealFlag;
	private String dealUser;
	private String dealDatetime;
	private String dealMemo;
	
	
	public String getInstDatetime() {
		return instDatetime;
	}
	public String getTransFlowId() {
		return transFlowId;
	}
	public String getBusinessType() {
		return businessType;
	}
	public String getTransCode() {
		return transCode;
	}
	public String getCardNo() {
		return cardNo;
	}
	public String getCurrCode() {
		return currCode;
	}
	public String getWorkDate() {
		return workDate;
	}
	public String getSendDate() {
		return sendDate;
	}
	public String getTransStatus() {
		return transStatus;
	}
	public String getRtnDate() {
		return rtnDate;
	}
	public String getRtnTime() {
		return rtnTime;
	}
	public String getRtnSeq() {
		return rtnSeq;
	}
	public String getRtnCode() {
		return rtnCode;
	}
	public String getRtnInfo() {
		return rtnInfo;
	}
	public String getInstDate() {
		return instDate;
	}
	
	public String getBalResult() {
		return balResult;
	}
	public String getBalDatetime() {
		return balDatetime;
	}
	public String getDealFlag() {
		return dealFlag;
	}
	public String getDealUser() {
		return dealUser;
	}
	public String getDealDatetime() {
		return dealDatetime;
	}
	public String getDealMemo() {
		return dealMemo;
	}
	public void setTransFlowId(String transFlowId) {
		this.transFlowId = transFlowId;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public void setCurrCode(String currCode) {
		this.currCode = currCode;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}
	public void setRtnDate(String rtnDate) {
		this.rtnDate = rtnDate;
	}
	public void setRtnTime(String rtnTime) {
		this.rtnTime = rtnTime;
	}
	public void setRtnSeq(String rtnSeq) {
		this.rtnSeq = rtnSeq;
	}
	public void setRtnCode(String rtnCode) {
		this.rtnCode = rtnCode;
	}
	public void setRtnInfo(String rtnInfo) {
		this.rtnInfo = rtnInfo;
	}
	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}
	public void setInstDatetime(String instDatetime) {
		this.instDatetime = instDatetime;
	}
	public void setBalResult(String balResult) {
		this.balResult = balResult;
	}
	public void setBalDatetime(String balDatetime) {
		this.balDatetime = balDatetime;
	}
	public void setDealFlag(String dealFlag) {
		this.dealFlag = dealFlag;
	}
	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}
	public void setDealDatetime(String dealDatetime) {
		this.dealDatetime = dealDatetime;
	}
	public void setDealMemo(String dealMemo) {
		this.dealMemo = dealMemo;
	}
	public String getBalDate() {
		return balDate;
	}
	public void setBalDate(String balDate) {
		this.balDate = balDate;
	}
	public String getSevenTime() {
		return sevenTime;
	}
	public void setSevenTime(String sevenTime) {
		this.sevenTime = sevenTime;
	}
	public String getBatchId() {
		return batchId;
	}
	public void setBatchId(String batchId) {
		this.batchId = batchId;
	}
	public String getClearId() {
		return clearId;
	}
	public void setClearId(String clearId) {
		this.clearId = clearId;
	}
	public BigDecimal getTransAmt() {
		return transAmt;
	}
	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}
	public String getBalMemo() {
		return balMemo;
	}
	public void setBalMemo(String balMemo) {
		this.balMemo = balMemo;
	}
}
