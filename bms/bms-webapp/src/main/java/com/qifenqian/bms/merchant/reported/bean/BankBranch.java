package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class BankBranch implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -914933947713731951L;

	private String bankBranchId;//银行网点编码
	
	private String bankBranchName;//银行网点名称
	
	private String bankId;//银行编号

	public String getBankBranchId() {
		return bankBranchId;
	}

	public void setBankBranchId(String bankBranchId) {
		this.bankBranchId = bankBranchId;
	}

	public String getBankBranchName() {
		return bankBranchName;
	}

	public void setBankBranchName(String bankBranchName) {
		this.bankBranchName = bankBranchName;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	
	
}
