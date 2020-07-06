package com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean;

import java.io.Serializable;

public class RefundQueryBean implements Serializable{
	private String refundId;
	private String mchId;
	private String orderId;
	private String createTimeB;
	private String createTimeE;
	private String refundState;
	private String channel;
	private String merchantCode;
	private String userName;
	private String userId;
	
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
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getRefundId() {
		return refundId;
	}
	public void setRefundId(String refundId) {
		this.refundId = refundId;
	}
	public String getMchId() {
		return mchId;
	}
	public void setMchId(String mchId) {
		this.mchId = mchId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
	public String getRefundState() {
		return refundState;
	}
	public void setRefundState(String refundState) {
		this.refundState = refundState;
	}
	
	
}
