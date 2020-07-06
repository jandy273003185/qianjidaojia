package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class Bank implements Serializable{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6924700961047339343L;

	private String bankId;//省份编号
	
	private String bankName;//省份名字

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	
	
}
