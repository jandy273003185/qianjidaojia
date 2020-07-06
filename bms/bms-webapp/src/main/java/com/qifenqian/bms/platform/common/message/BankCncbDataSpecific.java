package com.qifenqian.bms.platform.common.message;

import java.math.BigDecimal;
import java.util.Date;

public class BankCncbDataSpecific{
	/** 交易时间*/
	private Date transDatetime;
	/** 微信公众号*/
	private String appId;
	/** 子渠道*/
	private String channelSub;
	/** 商户号*/
	private String merchantId;
	/** 子商户号*/
	private String merchantIdSub;
	/** 设备编号*/
	private String deviceId;
	/** 威富通订单号*/
	private String channelOrderId;
	/** 威富通第三方订单号*/
	private String channelOrderIdSub;
	/** 商户订单号*/
	private String orderId;
	/** 用户标识*/
	private String userMark;
	/** 交易类型*/
	private String transType;
	/** 交易状态*/
	private String transStatus;
	/** 付款银行*/
	private String payBank;
	/** 货币种类*/
	private String currencyCode;
	/** 总金额BigDecimal*/
	private String totalAmt;	
	/** 企业红包金额*/
	private String enterpriseRedAmount;
	
	/** 威富通退款单号*/
	private String channelRefundId;
	/** 商户退款单号*/
	private String merchantRefundId;
	/** 退款金额*/
	private String refundAmt;		
	/** 企业红包退款金额*/
	private String merchantRefundAmt;
	/** 退款类型*/
	private String refundType;
	/** 退款状态*/
	private String refundStatus;
	/** 商品名称*/
	private String commodityName;
	/** 商品数据包*/
	private String merchantAttach;
	/** 手续费*/
	private String feeAmt;
	/** 费率*/
	private String feeRate;
	/** 终端类型*/
	private String terminalType;
	/** 对账标识*/
	private String balanceFlag;
	/** 门店编号*/
	private String storeId;
	/** 商户名称*/
	private String merchantName;
	/** 收银员*/
	private String cashier;
	/** 子商户ID*/
	private String merchantSubId;
	/** 免充值券金额*/
	private String rechargeTicketAmt;
	/** 实收金额*/
	private String receiveAmt;
	/** 扩展字段1*/
	private String reservedField1;
	/** 扩展字段2*/
	private String reservedField2;
	/** 扩展字段3*/
	private String reservedField3;
	/** 扩展字段4*/
	private String reservedField4;
	/** 扩展字段5*/
	private String reservedField5;
	/** 内部订单编号*/
	private String InternalOrderId;
	/** 渠道*/
	private String channel;
	/** 内部下级渠道*/
	private String internalChannelSub;
	/** 中信订单号*/
	private String zxOrderId;
	/** 内部渠道订单号*/
	private String internalChannelOrderId;
	/** 订单金额*/
	private BigDecimal orderAmt;
	/** 付款金额*/
	private BigDecimal tradeAmt;
	/** 订单生成时间*/
	private Date orderTimestamp;
	/** 订单状态*/
	private String orderState;
	/** 完成时间*/
	private Date finishTime;
	
	
	/** 文件编号*/
	private String fileId;
	/** 渠道编号*/
	private String channelId;
	/** 数据日期*/
	private String workDate;
	/** 文件名*/
	private String fileName;
	/** 栏位索引编号*/
	private String columnId;
	/**栏位名称*/
	private String fieldCode;
	/**栏位值*/
	private String fieldValue;
	
	public String getColumnId() {
		return columnId;
	}
	public void setColumnId(String columnId) {
		this.columnId = columnId;
	}
	public String getFieldCode() {
		return fieldCode;
	}
	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}
	public String getFieldValue() {
		return fieldValue;
	}
	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
	public Date getTransDatetime() {
		return transDatetime;
	}
	public void setTransDatetime(Date transDatetime) {
		this.transDatetime = transDatetime;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getChannelSub() {
		return channelSub;
	}
	public void setChannelSub(String channelSub) {
		this.channelSub = channelSub;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantIdSub() {
		return merchantIdSub;
	}
	public void setMerchantIdSub(String merchantIdSub) {
		this.merchantIdSub = merchantIdSub;
	}
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getChannelOrderId() {
		return channelOrderId;
	}
	public void setChannelOrderId(String channelOrderId) {
		this.channelOrderId = channelOrderId;
	}
	public String getChannelOrderIdSub() {
		return channelOrderIdSub;
	}
	public void setChannelOrderIdSub(String channelOrderIdSub) {
		this.channelOrderIdSub = channelOrderIdSub;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getUserMark() {
		return userMark;
	}
	public void setUserMark(String userMark) {
		this.userMark = userMark;
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
	public String getPayBank() {
		return payBank;
	}
	public void setPayBank(String payBank) {
		this.payBank = payBank;
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
	public String getEnterpriseRedAmount() {
		return enterpriseRedAmount;
	}
	public void setEnterpriseRedAmount(String enterpriseRedAmount) {
		this.enterpriseRedAmount = enterpriseRedAmount;
	}
	public String getChannelRefundId() {
		return channelRefundId;
	}
	public void setChannelRefundId(String channelRefundId) {
		this.channelRefundId = channelRefundId;
	}
	public String getMerchantRefundId() {
		return merchantRefundId;
	}
	public void setMerchantRefundId(String merchantRefundId) {
		this.merchantRefundId = merchantRefundId;
	}
	public String getRefundAmt() {
		return refundAmt;
	}
	public void setRefundAmt(String refundAmt) {
		this.refundAmt = refundAmt;
	}
	public String getMerchantRefundAmt() {
		return merchantRefundAmt;
	}
	public void setMerchantRefundAmt(String merchantRefundAmt) {
		this.merchantRefundAmt = merchantRefundAmt;
	}
	public String getRefundType() {
		return refundType;
	}
	public void setRefundType(String refundType) {
		this.refundType = refundType;
	}
	public String getRefundStatus() {
		return refundStatus;
	}
	public void setRefundStatus(String refundStatus) {
		this.refundStatus = refundStatus;
	}
	public String getCommodityName() {
		return commodityName;
	}
	public void setCommodityName(String commodityName) {
		this.commodityName = commodityName;
	}
	public String getMerchantAttach() {
		return merchantAttach;
	}
	public void setMerchantAttach(String merchantAttach) {
		this.merchantAttach = merchantAttach;
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
	public String getTerminalType() {
		return terminalType;
	}
	public void setTerminalType(String terminalType) {
		this.terminalType = terminalType;
	}
	public String getBalanceFlag() {
		return balanceFlag;
	}
	public void setBalanceFlag(String balanceFlag) {
		this.balanceFlag = balanceFlag;
	}
	public String getStoreId() {
		return storeId;
	}
	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getCashier() {
		return cashier;
	}
	public void setCashier(String cashier) {
		this.cashier = cashier;
	}
	public String getMerchantSubId() {
		return merchantSubId;
	}
	public void setMerchantSubId(String merchantSubId) {
		this.merchantSubId = merchantSubId;
	}
	public String getRechargeTicketAmt() {
		return rechargeTicketAmt;
	}
	public void setRechargeTicketAmt(String rechargeTicketAmt) {
		this.rechargeTicketAmt = rechargeTicketAmt;
	}
	public String getReceiveAmt() {
		return receiveAmt;
	}
	public void setReceiveAmt(String receiveAmt) {
		this.receiveAmt = receiveAmt;
	}
	public String getReservedField1() {
		return reservedField1;
	}
	public void setReservedField1(String reservedField1) {
		this.reservedField1 = reservedField1;
	}
	public String getReservedField2() {
		return reservedField2;
	}
	public void setReservedField2(String reservedField2) {
		this.reservedField2 = reservedField2;
	}
	public String getReservedField3() {
		return reservedField3;
	}
	public void setReservedField3(String reservedField3) {
		this.reservedField3 = reservedField3;
	}
	public String getReservedField4() {
		return reservedField4;
	}
	public void setReservedField4(String reservedField4) {
		this.reservedField4 = reservedField4;
	}
	public String getReservedField5() {
		return reservedField5;
	}
	public void setReservedField5(String reservedField5) {
		this.reservedField5 = reservedField5;
	}
	public String getInternalOrderId() {
		return InternalOrderId;
	}
	public void setInternalOrderId(String internalOrderId) {
		InternalOrderId = internalOrderId;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getInternalChannelSub() {
		return internalChannelSub;
	}
	public void setInternalChannelSub(String internalChannelSub) {
		this.internalChannelSub = internalChannelSub;
	}
	public String getZxOrderId() {
		return zxOrderId;
	}
	public void setZxOrderId(String zxOrderId) {
		this.zxOrderId = zxOrderId;
	}
	public String getInternalChannelOrderId() {
		return internalChannelOrderId;
	}
	public void setInternalChannelOrderId(String internalChannelOrderId) {
		this.internalChannelOrderId = internalChannelOrderId;
	}
	public BigDecimal getOrderAmt() {
		return orderAmt;
	}
	public void setOrderAmt(BigDecimal orderAmt) {
		this.orderAmt = orderAmt;
	}
	public BigDecimal getTradeAmt() {
		return tradeAmt;
	}
	public void setTradeAmt(BigDecimal tradeAmt) {
		this.tradeAmt = tradeAmt;
	}
	public Date getOrderTimestamp() {
		return orderTimestamp;
	}
	public void setOrderTimestamp(Date orderTimestamp) {
		this.orderTimestamp = orderTimestamp;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public Date getFinishTime() {
		return finishTime;
	}
	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	
	

}
