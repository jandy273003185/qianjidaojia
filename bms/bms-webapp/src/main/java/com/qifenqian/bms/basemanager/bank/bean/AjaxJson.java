package com.qifenqian.bms.basemanager.bank.bean;

/**
 * json消息实体
 * @author dayet
 * @date 2015年5月13日
 *
 */
public class AjaxJson {

	private String message;
	
	private boolean success;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
}
