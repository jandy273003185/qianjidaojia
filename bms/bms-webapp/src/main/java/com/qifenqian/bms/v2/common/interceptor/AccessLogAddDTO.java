package com.qifenqian.bms.v2.common.interceptor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/** 访问日志添加 DTO */
public class AccessLogAddDTO implements Serializable {

	private static final long serialVersionUID = -8500252523100041396L;

	/** 用户编号 - 空 */
	public static final Integer USER_ID_NULL = 0;

	@NotNull(message = "链路追踪编号不能为空")
	private String traceId;

	@NotNull(message = "用户编号不能为空")
	private Integer userId;

	@NotNull(message = "用户类型不能为空")
	private Integer userType;

	@NotNull(message = "应用名不能为空")
	private String applicationName;

	@NotNull(message = "访问地址不能为空")
	private String uri;

	@NotNull(message = "请求参数不能为空")
	private String queryString;

	@NotNull(message = "http 请求方法不能为空")
	private String method;

	@NotNull(message = "User-Agent 不能为空")
	private String userAgent;

	@NotNull(message = "ip 不能为空")
	private String ip;

	@NotNull(message = "请求时间不能为空")
	private Date startTime;

	@NotNull(message = "响应时长不能为空")
	private Integer responseTime;

	@NotNull(message = "错误码不能为空")
	private Integer errorCode;
	/** 错误提示 */
	private String errorMessage;

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Integer getResponseTime() {
		return responseTime;
	}

	public void setResponseTime(Integer responseTime) {
		this.responseTime = responseTime;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public static Integer getUserIdNull() {
		return USER_ID_NULL;
	}

	@Override
	public String toString() {
		return "AccessLogAddDTO [traceId=" + traceId + ", userId=" + userId + ", userType=" + userType
				+ ", applicationName=" + applicationName + ", uri=" + uri + ", queryString=" + queryString + ", method="
				+ method + ", userAgent=" + userAgent + ", ip=" + ip + ", startTime=" + startTime + ", responseTime="
				+ responseTime + ", errorCode=" + errorCode + ", errorMessage=" + errorMessage + "]";
	}

}
