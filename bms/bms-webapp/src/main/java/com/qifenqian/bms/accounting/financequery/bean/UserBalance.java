package com.qifenqian.bms.accounting.financequery.bean;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class UserBalance extends UserBalanceBase{

	private String mobile;
	private String acctName;
	private BigDecimal balance;
	private BigDecimal freezeAmt;
	private BigDecimal onwayAmt;
	private BigDecimal usableAmt;
	private String instDatetime;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getAcctName() {
		return acctName;
	}
	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	public BigDecimal getFreezeAmt() {
		return freezeAmt;
	}
	public void setFreezeAmt(BigDecimal freezeAmt) {
		this.freezeAmt = freezeAmt;
	}
	public BigDecimal getOnwayAmt() {
		return onwayAmt;
	}
	public void setOnwayAmt(BigDecimal onwayAmt) {
		this.onwayAmt = onwayAmt;
	}
	public BigDecimal getUsableAmt() {
		return usableAmt;
	}
	public void setUsableAmt(BigDecimal usableAmt) {
		this.usableAmt = usableAmt;
	}
	public String getInstDatetime() {
		return instDatetime;
	}
	public void setInstDatetime(String instDatetime) {
		this.instDatetime = instDatetime;
	}
	
}
