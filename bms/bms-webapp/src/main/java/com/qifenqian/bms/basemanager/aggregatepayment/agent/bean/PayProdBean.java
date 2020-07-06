package com.qifenqian.bms.basemanager.aggregatepayment.agent.bean;

import java.io.Serializable;

public class PayProdBean implements Serializable{

	private static final long serialVersionUID = -1974101805090576902L;
	
	private String prodCode;
	private String prodName;
	private String prodMemo;
	private String standardRate;
	private String agentBaseRate;
	
	public String getAgentBaseRate() {
		return agentBaseRate;
	}
	public void setAgentBaseRate(String agentBaseRate) {
		this.agentBaseRate = agentBaseRate;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdMemo() {
		return prodMemo;
	}
	public void setProdMemo(String prodMemo) {
		this.prodMemo = prodMemo;
	}
	public String getStandardRate() {
		return standardRate;
	}
	public void setStandardRate(String standardRate) {
		this.standardRate = standardRate;
	}
	

	
}
