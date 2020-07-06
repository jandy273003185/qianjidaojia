package com.qifenqian.bms.accounting.adjust.bean;

import java.io.Serializable;

/**
 * 通用响应消息bean
 * 
 * @project sevenpay-bms-web
 * @fileName CommonResponseBean.java
 * @author kunwang.li
 * @date 2015年7月30日
 * @memo
 */
public class CommonResponseBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Result result;

	private String message;

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
