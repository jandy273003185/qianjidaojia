package com.qifenqian.bms.accounting.financequery.bean;

import java.io.Serializable;

public class ChangeBalanceBase implements Serializable{
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 7287350131160001904L;
	/*private Integer id;
	
	private Integer subjectId;
	*/
	private String workDate;
}
