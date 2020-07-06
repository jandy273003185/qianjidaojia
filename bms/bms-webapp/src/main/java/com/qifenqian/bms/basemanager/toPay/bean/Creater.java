package com.qifenqian.bms.basemanager.toPay.bean;

import java.io.Serializable;

public class Creater implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2240617636691222759L;

	private String userId;
	
	private String userName;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
