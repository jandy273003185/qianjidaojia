package com.qifenqian.bms.basemanager.virtual.bean;

import java.io.Serializable;

public class MerchantInfo implements Serializable{

	private static final long serialVersionUID = -275893847000733542L;

	private String id;
	
	private String merchantName;
	
	private String protocolId;
	
	private String protocolRate;
	
	private String agentName;
	
	private String amt2014;
	
	private String amt2015;
	
	private String amt2016;
	
	private String amt2017;
	
	private String amt2018;
	
	private String tradeTime;
	
	private String address;
	
	private String contact;
	
	private String phone;
	
	private String bank;
	
	private String bankNum;
	
	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
	}

	public String getProtocolRate() {
		return protocolRate;
	}

	public void setProtocolRate(String protocolRate) {
		this.protocolRate = protocolRate;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getAmt2014() {
		return amt2014;
	}

	public void setAmt2014(String amt2014) {
		this.amt2014 = amt2014;
	}

	public String getAmt2015() {
		return amt2015;
	}

	public void setAmt2015(String amt2015) {
		this.amt2015 = amt2015;
	}

	public String getAmt2016() {
		return amt2016;
	}

	public void setAmt2016(String amt2016) {
		this.amt2016 = amt2016;
	}

	public String getAmt2017() {
		return amt2017;
	}

	public void setAmt2017(String amt2017) {
		this.amt2017 = amt2017;
	}

	public String getAmt2018() {
		return amt2018;
	}

	public void setAmt2018(String amt2018) {
		this.amt2018 = amt2018;
	}

	public String getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(String tradeTime) {
		this.tradeTime = tradeTime;
	}
	
}
