package com.qifenqian.bms.bal.accountResult.bean;

import java.io.Serializable;

public class BalExternalResultExceptionBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7655863165517749181L;
	
	/**
	 * 会计开始日期
	 */
	private String beginWorkDate;
	
	
	/**
	 * 会计结束日期
	 */
	private String endWokrDate;


	public String getBeginWorkDate() {
		return beginWorkDate;
	}


	public void setBeginWorkDate(String beginWorkDate) {
		this.beginWorkDate = beginWorkDate;
	}


	public String getEndWokrDate() {
		return endWokrDate;
	}


	public void setEndWokrDate(String endWokrDate) {
		this.endWokrDate = endWokrDate;
	}
	
	
	
}
