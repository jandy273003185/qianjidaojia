package com.qifenqian.bms.paymentmanager.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class TdPaymentFeeInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/***账号**/
	private String accountNo;
	/***手续费***/
	private BigDecimal payFee;
	/***工作日垫资费率***/
	private BigDecimal workRate;
	/***非工作日垫资费率***/
	private BigDecimal noWorkRate;
	/***待清算金额可用比例***/
	private BigDecimal amtRate;
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public BigDecimal getPayFee() {
		return payFee;
	}
	public void setPayFee(BigDecimal payFee) {
		this.payFee = payFee;
	}
	public BigDecimal getWorkRate() {
		return workRate;
	}
	public void setWorkRate(BigDecimal workRate) {
		this.workRate = workRate;
	}
	public BigDecimal getNoWorkRate() {
		return noWorkRate;
	}
	public void setNoWorkRate(BigDecimal noWorkRate) {
		this.noWorkRate = noWorkRate;
	}
	public BigDecimal getAmtRate() {
		return amtRate;
	}
	public void setAmtRate(BigDecimal amtRate) {
		this.amtRate = amtRate;
	}
	
	
	
}
