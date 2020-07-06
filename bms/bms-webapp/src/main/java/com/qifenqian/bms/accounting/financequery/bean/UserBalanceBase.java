package com.qifenqian.bms.accounting.financequery.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class UserBalanceBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -429600032931429405L;
	
	private String custId;
	
	private String acctId;
	
	private BigDecimal balanceCount;
	
	private BigDecimal usableAmtCount;
	
	private BigDecimal freezeAmtCount;
	
	private BigDecimal onwayAmtCount;
	

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public BigDecimal getBalanceCount() {
		return balanceCount;
	}

	public void setBalanceCount(BigDecimal balanceCount) {
		this.balanceCount = balanceCount;
	}

	public BigDecimal getUsableAmtCount() {
		return usableAmtCount;
	}

	public void setUsableAmtCount(BigDecimal usableAmtCount) {
		this.usableAmtCount = usableAmtCount;
	}

	public BigDecimal getFreezeAmtCount() {
		return freezeAmtCount;
	}

	public void setFreezeAmtCount(BigDecimal freezeAmtCount) {
		this.freezeAmtCount = freezeAmtCount;
	}

	public BigDecimal getOnwayAmtCount() {
		return onwayAmtCount;
	}

	public void setOnwayAmtCount(BigDecimal onwayAmtCount) {
		this.onwayAmtCount = onwayAmtCount;
	}
	
}
