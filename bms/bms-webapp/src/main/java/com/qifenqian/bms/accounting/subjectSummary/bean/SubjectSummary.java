package com.qifenqian.bms.accounting.subjectSummary.bean;

import java.io.Serializable;
import java.math.BigDecimal;

public class SubjectSummary implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4990942223233411683L;
	private Integer id;
	private String workDate;
	private Integer subjectId;
	private String subjectName;
	private BigDecimal lastAmount;
	private BigDecimal AmountD;
	private BigDecimal AmountC;
	private BigDecimal finalAmount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public Integer getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
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
		return AmountD;
	}

	public void setAmountD(BigDecimal amountD) {
		AmountD = amountD;
	}

	public BigDecimal getAmountC() {
		return AmountC;
	}

	public void setAmountC(BigDecimal amountC) {
		AmountC = amountC;
	}

	public BigDecimal getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(BigDecimal finalAmount) {
		this.finalAmount = finalAmount;
	}

}
