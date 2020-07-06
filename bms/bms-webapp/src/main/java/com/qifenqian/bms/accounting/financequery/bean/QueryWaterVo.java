package com.qifenqian.bms.accounting.financequery.bean;

import java.math.BigDecimal;

@SuppressWarnings("serial")
public class QueryWaterVo extends CommQuery{

	private String workDate;
	
	

	private String subjectCode;
	
	private String subjectName;
	
	private String transType2;
	
	private String debtor;
	
	private BigDecimal debtorBlance;
	
	private String credit;
	
	private BigDecimal creditBlance;
	
	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getDebtor() {
		return debtor;
	}

	public void setDebtor(String debtor) {
		this.debtor = debtor;
	}

	public BigDecimal getDebtorBlance() {
		return debtorBlance;
	}

	public void setDebtorBlance(BigDecimal debtorBlance) {
		this.debtorBlance = debtorBlance;
	}

	public String getCredit() {
		return credit;
	}

	public void setCredit(String credit) {
		this.credit = credit;
	}

	public BigDecimal getCreditBlance() {
		return creditBlance;
	}

	public void setCreditBlance(BigDecimal creditBlance) {
		this.creditBlance = creditBlance;
	}

	public String getTransType2() {
		return transType2;
	}

	public void setTransType2(String transType2) {
		this.transType2 = transType2;
	}
}
