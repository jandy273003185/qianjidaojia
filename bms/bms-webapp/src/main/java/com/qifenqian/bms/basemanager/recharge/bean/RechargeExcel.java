package com.qifenqian.bms.basemanager.recharge.bean;

import java.math.BigDecimal;

public class RechargeExcel {
	
	private String chargeSn;
	private String merOrderId;
	private String transId;
	private String channelId;
	private String mobile;
	private String compDate;
	private String chargeSubmitTime;
	private String chargeReturnTime;
	private String custName;
	private BigDecimal chargeAmt;
	private BigDecimal settleAmt;
	private String chargeType;
	private String chargeWay;
	private String chargeNetpayState;
	private String transType;

	

	public String getChargeSn() {
		return chargeSn;
	}

	public void setChargeSn(String chargeSn) {
		this.chargeSn = chargeSn;
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

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getCompDate() {
		return compDate;
	}

	public void setCompDate(String compDate) {
		this.compDate = compDate;
	}

	public String getChargeSubmitTime() {
		return chargeSubmitTime;
	}

	public void setChargeSubmitTime(String chargeSubmitTime) {
		this.chargeSubmitTime = chargeSubmitTime;
	}

	public String getChargeReturnTime() {
		return chargeReturnTime;
	}

	public void setChargeReturnTime(String chargeReturnTime) {
		this.chargeReturnTime = chargeReturnTime;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public BigDecimal getChargeAmt() {
		return chargeAmt;
	}

	public void setChargeAmt(BigDecimal chargeAmt) {
		this.chargeAmt = chargeAmt;
	}

	public BigDecimal getSettleAmt() {
		return settleAmt;
	}

	public void setSettleAmt(BigDecimal settleAmt) {
		this.settleAmt = settleAmt;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getChargeNetpayState() {
		return chargeNetpayState;
	}

	public void setChargeNetpayState(String chargeNetpayState) {
		this.chargeNetpayState = chargeNetpayState;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

}
