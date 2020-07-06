package com.qifenqian.bms.bal.accountResult.bean;

import java.io.Serializable;

public class BalInternalResultEqualBase implements Serializable{
	
	private static final long serialVersionUID = -1260227917928338651L;
	private String beginWorkDate;
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
