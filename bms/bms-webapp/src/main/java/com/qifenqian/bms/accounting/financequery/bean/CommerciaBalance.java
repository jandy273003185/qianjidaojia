package com.qifenqian.bms.accounting.financequery.bean;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class CommerciaBalance extends CommerciaBalanceBase {

	private String merchantCode;
	
	private String acctName;
	
	private String bankCardName;
	
	private String bankCardNo;

	private String bankName;

	private String branchBank;

	private BigDecimal balance;

	private BigDecimal usableAmt;

	private BigDecimal onwayAmt;
	
	private BigDecimal availableSettleAmt;
	
	private BigDecimal onwaySettleAmt;
	
	private BigDecimal freezeAmt;
	
	
	private String status;
	
	
	public BigDecimal getAvailableSettleAmt() {
		return availableSettleAmt;
	}

	public void setAvailableSettleAmt(BigDecimal availableSettleAmt) {
		this.availableSettleAmt = availableSettleAmt;
	}

	public BigDecimal getOnwaySettleAmt() {
		return onwaySettleAmt;
	}

	public void setOnwaySettleAmt(BigDecimal onwaySettleAmt) {
		this.onwaySettleAmt = onwaySettleAmt;
	}

	public String getBranchBank() {
		return branchBank;
	}

	public void setBranchBank(String branchBank) {
		this.branchBank = branchBank;
	}

	public String getBankCardName() {
		return bankCardName;
	}

	public void setBankCardName(String bankCardName) {
		this.bankCardName = bankCardName;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public BigDecimal getUsableAmt() {
		return usableAmt;
	}

	public void setUsableAmt(BigDecimal usableAmt) {
		this.usableAmt = usableAmt;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getOnwayAmt() {
		return onwayAmt;
	}

	public void setOnwayAmt(BigDecimal onwayAmt) {
		this.onwayAmt = onwayAmt;
	}

	public BigDecimal getFreezeAmt() {
		return freezeAmt;
	}

	public void setFreezeAmt(BigDecimal freezeAmt) {
		this.freezeAmt = freezeAmt;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
