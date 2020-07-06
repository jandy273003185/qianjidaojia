package com.qifenqian.bms.bal.accountResult.bean;

import java.io.Serializable;

public class ResultStatisticBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1051550834195945852L;
	
	private String workDateMin;
	
	private String workDateMax;

	public String getWorkDateMin() {
		return workDateMin;
	}

	public void setWorkDateMin(String workDateMin) {
		this.workDateMin = workDateMin;
	}

	public String getWorkDateMax() {
		return workDateMax;
	}

	public void setWorkDateMax(String workDateMax) {
		this.workDateMax = workDateMax;
	}

	
	
	
}
