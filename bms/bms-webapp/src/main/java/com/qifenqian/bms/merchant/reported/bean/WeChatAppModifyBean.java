package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

public class WeChatAppModifyBean implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4345281605758882707L;
	/**
	 * 批次号
	 */
	private String patchNo;
	/**
	 * 微信 小微商户号
	 */
	private String subMchId;
	/**
	 * 商户号
	 */
	private String merchantCode;
	/**
	 * 商户名
	 */
	private String merchantName;
	/**
	 * 银行卡号
	 */
	private String accountNo;
	/**
	 * 银行
	 */
	private String bank;
	/**
	 * 银行全称
	 */
	private String interBankName;
	/**
	 * 开户省份
	 */
	private String bankProvince;
	/**
	 * 开户城市
	 */
	private String bankCity;
	/**
	 * 商户简称
	 */
	private String shortName;
	/**
	 * 手机号码
	 */
	private String mobileNo;
	/**
	 * 邮箱
	 */
	private String email;
	
	
	
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPatchNo() {
		return patchNo;
	}
	public void setPatchNo(String patchNo) {
		this.patchNo = patchNo;
	}
	public String getSubMchId() {
		return subMchId;
	}
	public void setSubMchId(String subMchId) {
		this.subMchId = subMchId;
	}
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getMerchantName() {
		return merchantName;
	}
	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getInterBankName() {
		return interBankName;
	}
	public void setInterBankName(String interBankName) {
		this.interBankName = interBankName;
	}
	public String getBankProvince() {
		return bankProvince;
	}
	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}
	public String getBankCity() {
		return bankCity;
	}
	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
	
	
}
