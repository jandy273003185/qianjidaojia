 package com.qifenqian.bms.common.util.result;

import java.util.HashMap;

/**
 * @author zhangguangchao
 *    返回结果
 */
public class ResultMap extends HashMap<String, Object> {

	private static final long serialVersionUID = -4970973511892646114L;

	/**
	 * 返回成功結果，所有成功結果都用0表示
	 */
	public static final Result STATUS_CODE_SUCCESSFUL = Result.getResult("success", 0);

	/**
	 * 系统错误
	 */
	public static final Result STATUS_ERRORCODE_SYSTEMERROR = Result.getResult("errorcode.system.error", 1);

	/**
	 * 系统繁忙
	 */
	public static final Result STATUS_ERRORCODE_SYSTEM_BUSY = Result.getResult("errorcode.system.busy", 2);

	/**
	 * 参数不能为空
	 */
	public static final Result STATUS_ERRORCODE_PARAMETER_CANNOTBENULL = Result
			.getResult("errorcode.parameter.cannotbenull", 3);

	/**
	 * 参数无效
	 */
	public static final Result STATUS_ERRORCODE_PARAMETER_INVALIDPARAM = Result
			.getResult("errorcode.parameter.invalidparam", 4);
	
	/**
	 * 超出限额
	 */
	public static final Result STATUS_ERRORCODE_OUTAMOUNT = Result
			.getResult("errorcode.parameter.outamount", 7);

	/**
	 * 谷歌验证码错误
	 */
	public static final Result STATUS_ERRORCODE_GOOGLECODE_ERROR = Result.getResult("errorcode.googlecode.error", 5);

	/**
	 * 签名异常
	 */
	public static final Result STATUS_ERRORCODE_ORDER = Result.getResult("errorcode.order.signerror",1032);
	
	
	public final static String RESULT = "result"; //返回结果

	public final static String STATUSCODE = "statusCode";//返回状态码

	public final static String RESULT_DESC = "resultDesc";//错误描述
	
	private ResultMap(int statusCode, String resultDesc, Object obj) {
		this.put(STATUSCODE, statusCode);
		this.put(RESULT, obj);
		this.put(RESULT_DESC, resultDesc);
	}

	private ResultMap(Result result, Object obj) {
		this.put(STATUSCODE, result.getStatusCode());
		this.put(RESULT, obj);
		this.put(RESULT_DESC, result.getResultCode());
	}
	//失败 状态 描述  结果
	public static ResultMap getFailureResult(int statusCode, String resultDesc, Object result) {
		return new ResultMap(statusCode, resultDesc, result);
	}
	//失败 返回  状态 描述
	public static ResultMap getFailureResult(int statusCode, String resultDesc) {
		return getFailureResult(statusCode, resultDesc, null);
	}
	//失败只返回状态码
	public static ResultMap getFailureResult(int statusCode) {
		return getFailureResult(statusCode, null, null);
	}
	///失败  系统错误
	public static ResultMap getFailureResult() {
		return  new ResultMap(STATUS_ERRORCODE_SYSTEMERROR,null);
	}

	public static ResultMap getFailureResult(Result result, String resultDesc) {
		return getFailureResult(result.getStatusCode(), resultDesc, null);
	}
	//成功返回结果  "success", 0 
	public static ResultMap getSuccessfulResult(Object result) {
		return new ResultMap(STATUS_CODE_SUCCESSFUL, result);
	}
	//成功不返回结果
	public static ResultMap getSuccessfulResult() {
		return getSuccessfulResult(null);
	}

}
