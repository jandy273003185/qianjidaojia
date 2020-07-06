package com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean;

import java.math.BigDecimal;

public class TdMerchantProdRate {

	private String merAgreementCode;
	
	private String merCode;
	
	private String prodCode;
	
	private BigDecimal merAgreeRate;

	public String getMerAgreementCode() {
		return merAgreementCode;
	}

	public void setMerAgreementCode(String merAgreementCode) {
		this.merAgreementCode = merAgreementCode;
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

	public BigDecimal getMerAgreeRate() {
		return merAgreeRate;
	}

	public void setMerAgreeRate(BigDecimal merAgreeRate) {
		this.merAgreeRate = merAgreeRate;
	}
	
}
