package com.qifenqian.bms.common.bean;

import java.io.Serializable;

public class BranchBankInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8622053912154342930L;
	
	/** 支行行号 */
	private String branchBankCode;
	/** 支行名称 */
	private String bankName;
	/** 银行代码 */
	private String bankCode;
	/** 区域id */
	private int cityCode;
	/** 支行地址 */
	private String bankAddress;
	
	private int areaCode;
	
	private int provinceId;
	
	
	public int getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(int provinceId) {
		this.provinceId = provinceId;
	}
	public int getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}
	public String getBranchBankCode() {
		return branchBankCode;
	}
	public void setBranchBankCode(String branchBankCode) {
		this.branchBankCode = branchBankCode;
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
	public int getCityCode() {
		return cityCode;
	}
	public void setCityCode(int cityCode) {
		this.cityCode = cityCode;
	}
	public String getBankAddress() {
		return bankAddress;
	}
	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	

}
