package com.qifenqian.bms.accounting.financequery.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class CommerciaBalanceBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2755923428750602549L;
	private BigDecimal  partbalance2;
	
	private String acctId;
	
	private String depositall;
	
	private String cacctId;
	
	private String custId;	
	
	private BigDecimal balanceCount;

	private BigDecimal usableAmtCount;

	private BigDecimal onwayAmtCount;
	
	private BigDecimal availableSettleAmtCount;
	
	private BigDecimal onwaySettleAmtCount;
	
	private BigDecimal freezeAmtCount;
	
	
	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public BigDecimal getPartbalance2() {
		return partbalance2;
	}

	public void setPartbalance2(BigDecimal partbalance2) {
		this.partbalance2 = partbalance2;
	}
	
	public String getAcctId() {
		return acctId;
	}

	public void setAcctId(String acctId) {
		this.acctId = acctId;
	}

	public String getDepositall() {
		return depositall;
	}

	public void setDepositall(String depositall) {
		this.depositall = depositall;
	}

	public String getCacctId() {
		return cacctId;
	}

	public void setCacctId(String cacctId) {
		this.cacctId = cacctId;
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

	public BigDecimal getOnwayAmtCount() {
		return onwayAmtCount;
	}

	public void setOnwayAmtCount(BigDecimal onwayAmtCount) {
		this.onwayAmtCount = onwayAmtCount;
	}

	public BigDecimal getAvailableSettleAmtCount() {
		return availableSettleAmtCount;
	}

	public void setAvailableSettleAmtCount(BigDecimal availableSettleAmtCount) {
		this.availableSettleAmtCount = availableSettleAmtCount;
	}

	public BigDecimal getOnwaySettleAmtCount() {
		return onwaySettleAmtCount;
	}

	public void setOnwaySettleAmtCount(BigDecimal onwaySettleAmtCount) {
		this.onwaySettleAmtCount = onwaySettleAmtCount;
	}

	public BigDecimal getFreezeAmtCount() {
		return freezeAmtCount;
	}

	public void setFreezeAmtCount(BigDecimal freezeAmtCount) {
		this.freezeAmtCount = freezeAmtCount;
	}

}
