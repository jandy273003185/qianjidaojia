package com.qifenqian.bms.basemanager.toPay.bean;

import java.io.Serializable;
/**
 * Tyy渠道银行编号信息
 * @author dengtongbai
 *
 */
public class TyyBankInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8250697637945183288L;

	private String id;
	/**
	 * 银行名字
	 */
	private String bankName;
	/**
	 * 银行编号
	 */
	private String bankCode;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	
	
}
