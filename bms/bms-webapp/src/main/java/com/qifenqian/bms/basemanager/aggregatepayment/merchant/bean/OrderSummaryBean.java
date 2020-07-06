package com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean;


public class OrderSummaryBean {
	
	private String createTimeB;
	private String createTimeE;
	private String merchantCode;
	private String merchantName;
	private String channel;
	private String countTrade;
	private String sumTrade;
	private String custId;
	private String tradeType;
	private String settleAmt;
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getCreateTimeE() {
		return createTimeE;
	}
	public void setCreateTimeE(String createTimeE) {
		this.createTimeE = createTimeE;
	}
	public String getCreateTimeB() {
		return createTimeB;
	}
	public void setCreateTimeB(String createTimeB) {
		this.createTimeB = createTimeB;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getCountTrade() {
		return countTrade;
	}
	public void setCountTrade(String countTrade) {
		this.countTrade = countTrade;
	}
	public String getSumTrade() {
		return sumTrade;
	}
	public void setSumTrade(String sumTrade) {
		this.sumTrade = sumTrade;
	}
	public String getTradeType() {
		return tradeType;
	}
	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}
	public String getSettleAmt() {
		return settleAmt;
	}
	public void setSettleAmt(String settleAmt) {
		this.settleAmt = settleAmt;
	}
	
	
}
