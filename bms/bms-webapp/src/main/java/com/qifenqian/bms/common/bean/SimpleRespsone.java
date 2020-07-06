package com.qifenqian.bms.common.bean;

import java.io.Serializable;

public class SimpleRespsone implements Serializable{
	private static final long serialVersionUID = -6507111659778022851L;
	private boolean success;
	private String message;
	
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
