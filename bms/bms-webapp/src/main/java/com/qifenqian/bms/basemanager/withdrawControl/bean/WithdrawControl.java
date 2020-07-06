package com.qifenqian.bms.basemanager.withdrawControl.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class WithdrawControl implements Serializable {

	private static final long serialVersionUID = 5606906624914606005L;
	
	private String custId;
	
	private String mobile;
	
	private String controlType;	
	
	private String protocolId;	
	
	private int pcWdCntPerDay;
	
	private BigDecimal pcWdAmtPerOnce;
	
	private BigDecimal pcWdAmtPerDay;
	
	private BigDecimal pcWdAmtPerMonth;
	
	private int mbWdCntPerDay;
	
	private BigDecimal mbWdAmtPerOnce;
	
	private BigDecimal mbWdAmtPerDay;
	
	private BigDecimal mbWdAmtPerMonth;
	
	private String isShare;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getControlType() {
		return controlType;
	}

	public void setControlType(String controlType) {
		this.controlType = controlType;
	}

	public String getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
	}

	public int getPcWdCntPerDay() {
		return pcWdCntPerDay;
	}

	public void setPcWdCntPerDay(int pcWdCntPerDay) {
		this.pcWdCntPerDay = pcWdCntPerDay;
	}

	public BigDecimal getPcWdAmtPerOnce() {
		return pcWdAmtPerOnce;
	}

	public void setPcWdAmtPerOnce(BigDecimal pcWdAmtPerOnce) {
		this.pcWdAmtPerOnce = pcWdAmtPerOnce;
	}

	public BigDecimal getPcWdAmtPerDay() {
		return pcWdAmtPerDay;
	}

	public void setPcWdAmtPerDay(BigDecimal pcWdAmtPerDay) {
		this.pcWdAmtPerDay = pcWdAmtPerDay;
	}

	public BigDecimal getPcWdAmtPerMonth() {
		return pcWdAmtPerMonth;
	}

	public void setPcWdAmtPerMonth(BigDecimal pcWdAmtPerMonth) {
		this.pcWdAmtPerMonth = pcWdAmtPerMonth;
	}

	public int getMbWdCntPerDay() {
		return mbWdCntPerDay;
	}

	public void setMbWdCntPerDay(int mbWdCntPerDay) {
		this.mbWdCntPerDay = mbWdCntPerDay;
	}

	public BigDecimal getMbWdAmtPerOnce() {
		return mbWdAmtPerOnce;
	}

	public void setMbWdAmtPerOnce(BigDecimal mbWdAmtPerOnce) {
		this.mbWdAmtPerOnce = mbWdAmtPerOnce;
	}

	public BigDecimal getMbWdAmtPerDay() {
		return mbWdAmtPerDay;
	}

	public void setMbWdAmtPerDay(BigDecimal mbWdAmtPerDay) {
		this.mbWdAmtPerDay = mbWdAmtPerDay;
	}

	public BigDecimal getMbWdAmtPerMonth() {
		return mbWdAmtPerMonth;
	}

	public void setMbWdAmtPerMonth(BigDecimal mbWdAmtPerMonth) {
		this.mbWdAmtPerMonth = mbWdAmtPerMonth;
	}

	public String getIsShare() {
		return isShare;
	}

	public void setIsShare(String isShare) {
		this.isShare = isShare;
	}
	
	

}
