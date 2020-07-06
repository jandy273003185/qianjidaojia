package com.qifenqian.bms.basemanager.aggregatepayment.agent.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class AgreementBean implements Serializable{

	private static final long serialVersionUID = -1974101805090576902L;

	private String agentAgreementCode;
	private String agentCode;
	
	private String merCode;
	private String prodCode;
	private BigDecimal agentAgreeRate;
	public String getAgentAgreementCode() {
		return agentAgreementCode;
	}
	public void setAgentAgreementCode(String agentAgreementCode) {
		this.agentAgreementCode = agentAgreementCode;
	}
	public String getAgentCode() {
		return agentCode;
	}
	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	
	public String getMerCode() {
		return merCode;
	}
	public void setMerCode(String merCode) {
		this.merCode = merCode;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public BigDecimal getAgentAgreeRate() {
		return agentAgreeRate;
	}
	public void setAgentAgreeRate(BigDecimal agentAgreeRate) {
		this.agentAgreeRate = agentAgreeRate;
	}
	
	
}
