package com.qifenqian.bms.accounting.aggregations.bean;

import java.io.Serializable;



public class BalAggregationResultExceptionBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -295883635100183551L;
	
	/**
	 * 会计开始日期
	 */
	private String beginBalTime;
	
	
	/**
	 * 会计结束日期
	 */
	private String endBalTime;


	public String getBeginBalTime() {
		return beginBalTime;
	}


	public void setBeginBalTime(String beginBalTime) {
		this.beginBalTime = beginBalTime;
	}


	public String getEndBalTime() {
		return endBalTime;
	}


	public void setEndBalTime(String endBalTime) {
		this.endBalTime = endBalTime;
	}


}
