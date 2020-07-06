package com.qifenqian.bms.accounting.cncbdatasource.bean;

import java.io.Serializable;

public class BalCncbDataSourceBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2193148905977337445L;
	
	/*会计日期*/
	private String workDate;

	
	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	
}
