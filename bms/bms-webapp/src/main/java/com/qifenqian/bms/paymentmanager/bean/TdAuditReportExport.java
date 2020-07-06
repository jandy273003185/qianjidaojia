package com.qifenqian.bms.paymentmanager.bean;

import java.io.Serializable;







@SuppressWarnings("serial")
public class TdAuditReportExport implements Serializable{
	/**
	 * 付款账号
	 */
	private String batNo;
	private String tradeStatus;
	private String custName;
	private String paerAcctNo;
	private String topayType;
	private String type;
	private String sumCount;
	private String feeAmt;
	private String sumAmt;
	private String succCount;
	private String succAmt;
	private String failCount;
	private String failAmt;
	private String createTime;
	
	
	public String getFailAmt() {
		return failAmt;
	}
	public void setFailAmt(String failAmt) {
		this.failAmt = failAmt;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getBatNo() {
		return batNo;
	}
	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}
	public String getTradeStatus() {
		return tradeStatus;
	}
	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}
	public String getTopayType() {
		return topayType;
	}
	public void setTopayType(String topayType) {
		this.topayType = topayType;
	}
	public String getPaerAcctNo() {
		return paerAcctNo;
	}
	public void setPaerAcctNo(String paerAcctNo) {
		this.paerAcctNo = paerAcctNo;
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
	public String getFeeAmt() {
		return feeAmt;
	}
	public void setFeeAmt(String feeAmt) {
		this.feeAmt = feeAmt;
	}
	public String getSumAmt() {
		return sumAmt;
	}
	public void setSumAmt(String sumAmt) {
		this.sumAmt = sumAmt;
	}
	public String getSuccCount() {
		return succCount;
	}
	public void setSuccCount(String succCount) {
		this.succCount = succCount;
	}
	public String getSuccAmt() {
		return succAmt;
	}
	public void setSuccAmt(String succAmt) {
		this.succAmt = succAmt;
	}
	public String getFailCount() {
		return failCount;
	}
	public void setFailCount(String failCount) {
		this.failCount = failCount;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
	
	
	
}
