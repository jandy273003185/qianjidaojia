package com.qifenqian.bms.accounting.financequery.bean;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class ChangeBalance extends ChangeBalanceBase{

	private String subjectName;
	private BigDecimal lastAmount;
	private BigDecimal amountD;
	private BigDecimal amountC;
	private BigDecimal finalAmount;
	private String workDate;
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public BigDecimal getLastAmount() {
		return lastAmount;
	}
	public void setLastAmount(BigDecimal lastAmount) {
		this.lastAmount = lastAmount;
	}
	
	public BigDecimal getAmountD() {
		return amountD;
	}
	public void setAmountD(BigDecimal amountD) {
		this.amountD = amountD;
	}
	public BigDecimal getAmountC() {
		return amountC;
	}
	public void setAmountC(BigDecimal amountC) {
		this.amountC = amountC;
	}
	public BigDecimal getFinalAmount() {
		return finalAmount;
	}
	public void setFinalAmount(BigDecimal finalAmount) {
		this.finalAmount = finalAmount;
	}
}
