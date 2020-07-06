package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class TdMerchantBankInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 791199901595260531L;
	/**
	 * 自增长ID
	 */
	private String id;
	/**
	 * 客户号
	 */
	private String custId;
	/**
	 * 开户银行
	 */
	private String accountBank;
	/**
	 * 银行卡支行详情
	 */
	private String bankName;
	/**
	 * 银行卡号
	 */
	private String accountNumber;
	/**
	 * 开户银行省市编码
	 */
	private String bankAddressCode;
	/**
	 * 银行类型 0-个人 1-企业
	 */
	private String bankType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAccountBank() {
		return accountBank;
	}

	public void setAccountBank(String accountBank) {
		this.accountBank = accountBank;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankAddressCode() {
		return bankAddressCode;
	}

	public void setBankAddressCode(String bankAddressCode) {
		this.bankAddressCode = bankAddressCode;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	
	

}
