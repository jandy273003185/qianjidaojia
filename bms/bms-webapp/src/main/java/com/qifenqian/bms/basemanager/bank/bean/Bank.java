package com.qifenqian.bms.basemanager.bank.bean;

import java.io.Serializable;

public class Bank implements Serializable{
	
	private static final long serialVersionUID = -682833961334225889L;
	
	/**********************************************************/
	
	/** 支付系统行号  */ 			private String bankCode;
	/** 支付系统行别  */			private String bankType;
	/** 行名  */				private String bankName;
	/** 7分钱自定义行别  */		private String myBankType;
	/** 城市代码  */				private String cityCode;
	/** 所属CCPC代码  */			private String ccpc;
	/** 排序号  */				private int    orderBy;
	
	/***********************************************************/
	
	
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankType() {
		return bankType;
	}
	public void setBankType(String bankType) {
		this.bankType = bankType;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getMyBankType() {
		return myBankType;
	}
	public void setMyBankType(String myBankType) {
		this.myBankType = myBankType;
	}
	public String getCityCode() {
		return cityCode;
	}
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	public String getCcpc() {
		return ccpc;
	}
	public void setCcpc(String ccpc) {
		this.ccpc = ccpc;
	}
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}
	
	
	
	
	
	
}
