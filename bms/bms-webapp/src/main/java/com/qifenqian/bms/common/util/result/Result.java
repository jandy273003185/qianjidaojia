 package com.qifenqian.bms.common.util.result;

import java.io.Serializable;


/**
 * @author zhangguangchao  
 *   返回结果类
 */
public final class Result implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 7167899422279472093L;

	private String resultDesc;

	private int statusCode;

	public Result(String resultDesc, int statusCode) {
		super();
		this.resultDesc = resultDesc;
		this.statusCode = statusCode;
	}

	public String getResultCode() {
		return resultDesc;
	}

	public void setResultCode(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public static Result getResult(String resultDesc, int statusCode) {
		return new Result(resultDesc, statusCode);
	}
}
