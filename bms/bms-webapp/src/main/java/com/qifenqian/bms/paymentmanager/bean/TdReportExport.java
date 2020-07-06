package com.qifenqian.bms.paymentmanager.bean;

import java.io.Serializable;







@SuppressWarnings("serial")
public class TdReportExport implements Serializable{
	/**
	 * 付款账号
	 */
	private String batNo=null;
	private String paerAcctNo = null;
	private String custName;
	private String toPayType=null;
	private String  type;
	private String sumCount = null;
	private String sumAmt = null;
	private String feeAmt;
	private String succCount = null;
	private String failCount = null;
	private String succAmt = null;
	private String failAmt = null;
	private String createTime = null;
	
	private String tradeStatus;

	public String getBatNo() {
		return batNo;
	}
	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}
	public String getPaerAcctNo() {
		return paerAcctNo;
	}
	public void setPaerAcctNo(String paerAcctNo) {
		this.paerAcctNo = paerAcctNo;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getToPayType() {
		return toPayType;
	}
	public void setToPayType(String toPayType) {
		this.toPayType = toPayType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSumCount() {
		return sumCount;
	}
	public void setSumCount(String sumCount) {
		this.sumCount = sumCount;
	}
	public String getSumAmt() {
		return sumAmt;
	}
	public void setSumAmt(String sumAmt) {
		this.sumAmt = sumAmt;
	}
	public String getFeeAmt() {
		return feeAmt;
	}
	public void setFeeAmt(String feeAmt) {
		this.feeAmt = feeAmt;
	}
	public String getSuccCount() {
		return succCount;
	}
	public void setSuccCount(String succCount) {
		this.succCount = succCount;
	}
	public String getFailCount() {
		return failCount;
	}
	public void setFailCount(String failCount) {
		this.failCount = failCount;
	}
	public String getSuccAmt() {
		return succAmt;
	}
	public void setSuccAmt(String succAmt) {
		this.succAmt = succAmt;
	}
	public String getFailAmt() {
		return failAmt;
	}
	public void setFailAmt(String failAmt) {
		this.failAmt = failAmt;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	
}
