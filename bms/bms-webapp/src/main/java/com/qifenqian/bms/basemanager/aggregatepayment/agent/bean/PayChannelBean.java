package com.qifenqian.bms.basemanager.aggregatepayment.agent.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class PayChannelBean implements Serializable{

	private static final long serialVersionUID = -1974101805090576902L;
	
	private String payChannelCode;
	private String payChannelName;

	private String payChannelMemo;
	private BigDecimal payChannelRate;
	private String supllyOrg;//渠道提供机构
	private String ourBankAcctNo;
	private String ourBankAcctName;
	
	private String isAvail;
	private String serviceClass;
	
	public String getIsAvail() {
		return isAvail;
	}
	public void setIsAvail(String isAvail) {
		this.isAvail = isAvail;
	}
	public String getServiceClass() {
		return serviceClass;
	}
	public void setServiceClass(String serviceClass) {
		this.serviceClass = serviceClass;
	}
	public String getSupllyOrg() {
		return supllyOrg;
	}
	public void setSupllyOrg(String supllyOrg) {
		this.supllyOrg = supllyOrg;
	}
	public String getOurBankAcctNo() {
		return ourBankAcctNo;
	}
	public void setOurBankAcctNo(String ourBankAcctNo) {
		this.ourBankAcctNo = ourBankAcctNo;
	}
	public String getOurBankAcctName() {
		return ourBankAcctName;
	}
	public void setOurBankAcctName(String ourBankAcctName) {
		this.ourBankAcctName = ourBankAcctName;
	}
	
	public String getPayChannelCode() {
		return payChannelCode;
	}
	public void setPayChannelCode(String payChannelCode) {
		this.payChannelCode = payChannelCode;
	}
	public String getPayChannelName() {
		return payChannelName;
	}
	public void setPayChannelName(String payChannelName) {
		this.payChannelName = payChannelName;
	}
	public String getPayChannelMemo() {
		return payChannelMemo;
	}
	public void setPayChannelMemo(String payChannelMemo) {
		this.payChannelMemo = payChannelMemo;
	}
	public BigDecimal getPayChannelRate() {
		return payChannelRate;
	}
	public void setPayChannelRate(BigDecimal payChannelRate) {
		this.payChannelRate = payChannelRate;
	}
	
	
	
}
