package com.qifenqian.bms.basemanager.merchant.auding.bean;

import java.io.Serializable;

/**
 * 银行代码
 * @author Roy.Li
 *
 */
public class TbBankCode implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2389195480823686882L;

	/** 
	 * 支付系统行号.
	 */
	private String bankCode;

	/** 
	 * 支付系统行别.
	 */
	private String bankType;

	/** 
	 * 行名.
	 */
	private String bankName;

	/** 
	 * 7分钱自定义行别.
	 */
	private String myBankType;

	/** 
	 * 城市代码；全国定义为0000.
	 */
	private String cityCode;

	/** 
	 * 所属CCPC代码.
	 */
	private String ccpc;
	
	private int orderBy;
	
	

	public TbBankCode() {
	}

	public TbBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public TbBankCode(String bankCode, String bankType, String bankName,
			String myBankType, String cityCode, String ccpc,int orderBy) {
		this.bankCode = bankCode;
		this.bankType = bankType;
		this.bankName = bankName;
		this.myBankType = myBankType;
		this.cityCode = cityCode;
		this.ccpc = ccpc;
		this.orderBy = orderBy;
	}

	public int getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(int orderBy) {
		this.orderBy = orderBy;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankType() {
		return this.bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getBankName() {
		return this.bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getMyBankType() {
		return this.myBankType;
	}

	public void setMyBankType(String myBankType) {
		this.myBankType = myBankType;
	}

	public String getCityCode() {
		return this.cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCcpc() {
		return this.ccpc;
	}

	public void setCcpc(String ccpc) {
		this.ccpc = ccpc;
	}

}
