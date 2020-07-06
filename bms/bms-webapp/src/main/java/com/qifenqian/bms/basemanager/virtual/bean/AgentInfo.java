package com.qifenqian.bms.basemanager.virtual.bean;

import java.io.Serializable;

public class AgentInfo implements Serializable{

	private static final long serialVersionUID = -2817266426397686725L;

	private String agentId;
	
	private String agentName;
	
	private String protocolId;
	
	private String protocolRate;
	
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

	public String getAgentId() {
		return agentId;
	}

	public void setAgentId(String agentId) {
		this.agentId = agentId;
	}

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
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
}
