package com.qifenqian.bms.accounting.financequery.bean;

import java.io.Serializable;

public class CommQuery implements Serializable {

	private static final long serialVersionUID = -5965395876002646632L;

	private String subjectId;
	private String endWorkDate;
	private String transType;

	public String getSubjectId() {
		return subjectId;
	}

	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}

	public String getEndWorkDate() {
		return endWorkDate;
	}

	public void setEndWorkDate(String endWorkDate) {
		this.endWorkDate = endWorkDate;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

}
