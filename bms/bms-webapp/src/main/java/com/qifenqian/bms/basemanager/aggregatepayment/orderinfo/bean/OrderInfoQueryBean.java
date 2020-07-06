package com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean;

import java.io.Serializable;

public class OrderInfoQueryBean implements Serializable{
	private String payProd;
	private String payChannel;
	private String createTimeB;
	private String createTimeE;
	private String orderState;
	private String channel;
	private String orderId;
	private String orderType;
	private String refundId;
	private String merchantCode;
	private String userName;
	private String zxOrderId;
	private String channelOrderId;
	private String userId;
	private String finishTimeB;
	private String finishTimeE;
	private String createTimeBv;
	private String createTimeEv;
	
	public String getCreateTimeBv() {
		return createTimeBv;
	}
	public void setCreateTimeBv(String createTimeBv) {
		this.createTimeBv = createTimeBv;
	}
	public String getCreateTimeEv() {
		return createTimeEv;
	}
	public void setCreateTimeEv(String createTimeEv) {
		this.createTimeEv = createTimeEv;
	}
	public String getFinishTimeB() {
		return finishTimeB;
	}
	public void setFinishTimeB(String finishTimeB) {
		this.finishTimeB = finishTimeB;
	}
	public String getFinishTimeE() {
		return finishTimeE;
	}
	public void setFinishTimeE(String finishTimeE) {
		this.finishTimeE = finishTimeE;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getZxOrderId() {
		return zxOrderId;
	}
	public void setZxOrderId(String zxOrderId) {
		this.zxOrderId = zxOrderId;
	}
	public String getChannelOrderId() {
		return channelOrderId;
	}
	public void setChannelOrderId(String channelOrderId) {
		this.channelOrderId = channelOrderId;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getRefundId() {
		return refundId;
	}
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getPayProd() {
		return payProd;
	}
	public void setPayProd(String payProd) {
		this.payProd = payProd;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	
	public String getCreateTimeB() {
		return createTimeB;
	}
	public void setCreateTimeB(String createTimeB) {
		this.createTimeB = createTimeB;
	}
	public String getCreateTimeE() {
		return createTimeE;
	}
	public void setCreateTimeE(String createTimeE) {
		this.createTimeE = createTimeE;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
}
