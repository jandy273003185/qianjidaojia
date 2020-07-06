package com.qifenqian.bms.bal.accountResult.bean;

import java.io.Serializable;

public class BalInternalResultExceptionBase implements Serializable{


	private static final long serialVersionUID = 2288306530240238612L;

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

	public String getEndWorkDate() {
		return endWorkDate;
	}

	public void setBeginWorkDate(String beginWorkDate) {
		this.beginWorkDate = beginWorkDate;
	}

	public void setEndWorkDate(String endWorkDate) {
		this.endWorkDate = endWorkDate;
	}
	
	
}
