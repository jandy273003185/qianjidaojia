package com.qifenqian.bms.accounting.cncbdatasource.bean;

public class BalCncbDataSource extends BalCncbDataSourceBase{
	
	/*订单号*/
	private String ChannelOrderId;
	
	/*交易类型*/
	private String transType;
	
	/*交易状态*/
	private String transStatus;
	
	/*货币种类*/
	private String currencyCode;
	
	/*金额*/
	private String totalAmt;
	
	/*用户标识*/
	private String userMark;
	
	/*商户号*/
	private String merchantId;
	
	/*交易日期*/
	private String transDatetime;
	
	/*商品名称*/
	private String commodityName;
	
	/*收银员*/
	private String cashier;
	
	/*手续费*/
	private String feeAmt;
	
	/*费率*/
	private String feeRate;
	
	/*门店编号*/
	private String storeId;
	
	/*实收金额*/
	private String receiveAmt;
	
	/*子渠道*/
	private String channelSub;
	
	/*商户订单号*/
	private String orderId;
	
	/*付款银行*/
	private String payBank;
	
	
	public String getChannelSub() {
		return channelSub;
	}

	public void setChannelSub(String channelSub) {
		this.channelSub = channelSub;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getPayBank() {
		return payBank;
	}

	public void setPayBank(String payBank) {
		this.payBank = payBank;
	}

	public String getChannelOrderId() {
		return ChannelOrderId;
	}

	public void setChannelOrderId(String channelOrderId) {
		ChannelOrderId = channelOrderId;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(String totalAmt) {
		this.totalAmt = totalAmt;
	}

	public String getUserMark() {
		return userMark;
	}

	public void setUserMark(String userMark) {
		this.userMark = userMark;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getTransDatetime() {
		return transDatetime;
	}

	public void setTransDatetime(String transDatetime) {
		this.transDatetime = transDatetime;
	}

	public String getCommodityName() {
		return commodityName;
	}

	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}

	public String getCashier() {
		return cashier;
	}

	public void setCashier(String cashier) {
		this.cashier = cashier;
	}

	public String getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(String feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(String feeRate) {
		this.feeRate = feeRate;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getReceiveAmt() {
		return receiveAmt;
	}

	public void setReceiveAmt(String receiveAmt) {
		this.receiveAmt = receiveAmt;
	}
	





}
