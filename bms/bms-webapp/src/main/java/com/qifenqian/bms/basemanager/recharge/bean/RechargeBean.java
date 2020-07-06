package com.qifenqian.bms.basemanager.recharge.bean;


public class RechargeBean extends Recharge{

	private static final long serialVersionUID = 2192872103525855610L;
	
	private String beginTime;
	
	private String endTime;
	private String compBeginTime;
	private String compEndTime;
	
	private String transId;
	
	private String merOrderId;
	private String orderId;
	private String mobile;
	
	private String chargeWay;
	private String transType;
	
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getChargeWay() {
		return chargeWay;
	}

	public void setChargeWay(String chargeWay) {
		this.chargeWay = chargeWay;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getTransId() {
		return transId;
	}

	public void setTransId(String transId) {
		this.transId = transId;
	}

	public String getMerOrderId() {
		return merOrderId;
	}

	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getCompBeginTime() {
		return compBeginTime;
	}

	public void setCompBeginTime(String compBeginTime) {
		this.compBeginTime = compBeginTime;
	}

	public String getCompEndTime() {
		return compEndTime;
	}

	public void setCompEndTime(String compEndTime) {
		this.compEndTime = compEndTime;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}
}
