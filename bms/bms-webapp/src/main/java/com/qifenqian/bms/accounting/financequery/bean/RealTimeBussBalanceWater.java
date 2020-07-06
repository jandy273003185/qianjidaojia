package com.qifenqian.bms.accounting.financequery.bean;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class RealTimeBussBalanceWater extends RealTimeBalanceWaterBase {

	private String acctName;
	private String businessTypeDsc;
	private String loanFlagDsc;
	private BigDecimal balance;
	private BigDecimal transAmt;
	private BigDecimal onwayAmt;
	private BigDecimal usableAmt;
	private BigDecimal finalBalance;
	private String instDate;
	private String instDatetime;

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getBusinessTypeDsc() {
		return businessTypeDsc;
	}

	public void setBusinessTypeDsc(String businessTypeDsc) {
		this.businessTypeDsc = businessTypeDsc;
	}

	public String getLoanFlagDsc() {
		return loanFlagDsc;
	}

	public void setLoanFlagDsc(String loanFlagDsc) {
		this.loanFlagDsc = loanFlagDsc;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
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

	public BigDecimal getFinalBalance() {
		return finalBalance;
	}

	public void setFinalBalance(BigDecimal finalBalance) {
		this.finalBalance = finalBalance;
	}

	public String getInstDate() {
		return instDate;
	}

	public void setInstDate(String instDate) {
		this.instDate = instDate;
	}

	public String getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(String instDatetime) {
		this.instDatetime = instDatetime;
	}

}
