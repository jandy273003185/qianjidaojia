package com.qifenqian.bms.basemanager.merchant.auding.bean;

import java.io.Serializable;

/**
 * 
 * 
 * 2017年7月12日 下午5:20:12
 */
@SuppressWarnings("serial")
public class WechatExport implements Serializable{
	

	private String merchantCode;//微商户编号
    private String custName;//微商户名称
	private String Mobile;//手机号码
	private String bankCardName;//开户名
	private String bankCardNo;//账号
	private String Email;//邮件
	
	private String createTime;//注册时间
	private String agencyState;//状态
	private String cashierName;//收银员姓名
	
	public String getMerchantCode() {
		return merchantCode;
	}
	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getMobile() {
		return Mobile;
	}
	public void setMobile(String mobile) {
		Mobile = mobile;
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
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}

	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getAgencyState() {
		return agencyState;
	}
	public void setAgencyState(String agencyState) {
		this.agencyState = agencyState;
	}
	public String getCashierName() {
		return cashierName;
	}
	public void setCashierName(String cashierName) {
		this.cashierName = cashierName;
	}
 
}
