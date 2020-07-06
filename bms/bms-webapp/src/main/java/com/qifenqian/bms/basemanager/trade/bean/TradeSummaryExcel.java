package com.qifenqian.bms.basemanager.trade.bean;

import java.math.BigDecimal;

public class TradeSummaryExcel {

	private String beginTime;
	
	private String endCreateTime;
	
	private String merchantCode;
	
	private String custName;
	
	private int sumCount;
	
	private BigDecimal sumCountAmount;
	
	private BigDecimal sumSettleAmt;
	
	private String tradeType;
	
	private String minDate;
	
	private String maxDate;
	

	
	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getMinDate() {
		return minDate;
	}

	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}

	public String getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}


	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public int getSumCount() {
		return sumCount;
	}

	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}

	public BigDecimal getSumCountAmount() {
		return sumCountAmount;
	}

	public void setSumCountAmount(BigDecimal sumCountAmount) {
		this.sumCountAmount = sumCountAmount;
	}

	public BigDecimal getSumSettleAmt() {
		return sumSettleAmt;
	}

	public void setSumSettleAmt(BigDecimal sumSettleAmt) {
		this.sumSettleAmt = sumSettleAmt;
	}
	
	
}
