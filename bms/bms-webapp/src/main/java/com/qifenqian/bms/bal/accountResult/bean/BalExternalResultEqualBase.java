package com.qifenqian.bms.bal.accountResult.bean;

import java.io.Serializable;

public class BalExternalResultEqualBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8882344532741523113L;


	
	/**
	 * 会计开始日期
	 */
	private String beginWorkDate;
	
	/**
	 * 会计结束日期
	 */
	private String endWorkDate;

	public String getBeginWorkDate() {
		return beginWorkDate;
	}

	public void setBeginWorkDate(String beginWorkDate) {
		this.beginWorkDate = beginWorkDate;
	}

	public String getEndWorkDate() {
		return endWorkDate;
	}

	public void setEndWorkDate(String endWorkDate) {
		this.endWorkDate = endWorkDate;
	}
	
	

}
