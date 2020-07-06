package com.qifenqian.bms.basemanager.trade.bean;

import java.math.BigDecimal;

public class TdTradeBillMainVO extends TdTradeBillMain {

	private String beginTime;

	private String endCreateTime;

	/**
	 * 商家交易流水号
	 */
	private String merOrderId;

	/**
	 * 商家账号
	 */
	private String custId;

	/**
	 * 商家名称
	 */
	private String custName;

	/**
	 * 交易笔数
	 */
	private int sumCount;

	/**
	 * 交易总金额
	 */
	private BigDecimal sumCountAmount;

	/**
	 * 商户结算总金额
	 */
	private BigDecimal sumSettleAmt;

	/**
	 * 用户名称
	 */
	private String userName;

	/**
	 * 用户编号
	 */
	private String userId;

	/**
	 * 支付方式
	 */
	private String payType;

	/**
	 * 渠道编号
	 */
	private String channelId;

	/**
	 * 渠道
	 */
	private String channel;

	/**
	 * 结算金额
	 */
	private BigDecimal settleAmt;

	/**
	 * 对账日期
	 */
	private String workDate;

	private String compEndCreateTime;
	private String compBeginTime;

	/**
	 * 手机号
	 */
	private String mobile;

	/**
	 * 订单类型
	 */
	private String orderType;

	/**
	 * 充值订单号
	 */
	private String chargeSn;

	/**
	 * 银联订单号
	 */
	private String transId;

	private String merchantCode;
	
	private String service;
	
	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getChargeSn() {
		return chargeSn;
	}

	public void setChargeSn(String chargeSn) {
		this.chargeSn = chargeSn;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public BigDecimal getSettleAmt() {
		return settleAmt;
	}

	public void setSettleAmt(BigDecimal settleAmt) {
		this.settleAmt = settleAmt;
	}

	private BigDecimal orderAmount;

	private String minDate;

	private String maxDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
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

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getEndCreateTime() {
		return endCreateTime;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getMerOrderId() {
		return merOrderId;
	}

	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public BigDecimal getSumSettleAmt() {
		return sumSettleAmt;
	}

	public void setSumSettleAmt(BigDecimal sumSettleAmt) {
		this.sumSettleAmt = sumSettleAmt;
	}

	public String getCompEndCreateTime() {
		return compEndCreateTime;
	}

	public void setCompEndCreateTime(String compEndCreateTime) {
		this.compEndCreateTime = compEndCreateTime;
	}

	public String getCompBeginTime() {
		return compBeginTime;
	}

	public void setCompBeginTime(String compBeginTime) {
		this.compBeginTime = compBeginTime;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

}
