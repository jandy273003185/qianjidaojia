package com.qifenqian.bms.accounting.checkquery.bean;

import java.io.Serializable;

public class JgkjResultExceptionBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6458244005667519124L;

	private String endBalTime;

	private String beginBalTime;

	public String getEndBalTime() {
		return endBalTime;
	}

	public void setEndBalTime(String endBalTime) {
		this.endBalTime = endBalTime;
	}

	public String getBeginBalTime() {
		return beginBalTime;
	}

	public void setBeginBalTime(String beginBalTime) {
		this.beginBalTime = beginBalTime;
	}

}
