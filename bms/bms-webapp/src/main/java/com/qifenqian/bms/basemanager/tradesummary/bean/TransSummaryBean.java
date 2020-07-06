package com.qifenqian.bms.basemanager.tradesummary.bean;

import java.math.BigDecimal;

public class TransSummaryBean {

	private String transName;

	private String transCount;

	private BigDecimal transAmt;

	private String operType;

	private String workBeginDate;

	private String workEndDate;

	public String getTransName() {
		return transName;
	}

	public void setTransName(String transName) {
		this.transName = transName;
	}

	public String getTransCount() {
		return transCount;
	}

	public void setTransCount(String transCount) {
		this.transCount = transCount;
	}

	public BigDecimal getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(BigDecimal transAmt) {
		this.transAmt = transAmt;
	}

	public String getOperType() {
		return operType;
	}

	public void setOperType(String operType) {
		this.operType = operType;
	}

	public String getWorkBeginDate() {
		return workBeginDate;
	}

	public void setWorkBeginDate(String workBeginDate) {
		this.workBeginDate = workBeginDate;
	}

	public String getWorkEndDate() {
		return workEndDate;
	}

	public void setWorkEndDate(String workEndDate) {
		this.workEndDate = workEndDate;
	}

}
