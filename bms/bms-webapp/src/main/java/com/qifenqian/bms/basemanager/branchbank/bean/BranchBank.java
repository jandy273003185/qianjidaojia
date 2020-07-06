package com.qifenqian.bms.basemanager.branchbank.bean;

public class BranchBank {

	/** 银行联行号 */
	private String bankCnaps;
	/** 银行代码 */
	private String bankCode;
	/** 区域id */
	private int areaId;
	/** 银行名称 */
	private String bankName;
	/** 银行地址 */
	private String bankAddress;

	public String getBankCnaps() {
		return bankCnaps;
	}

	public void setBankCnaps(String bankCnaps) {
		this.bankCnaps = bankCnaps;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public int getAreaId() {
		return areaId;
	}

	public void setAreaId(int areaId) {
		this.areaId = areaId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

}
