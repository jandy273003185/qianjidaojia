package com.qifenqian.bms.accounting.exception.dao.operdealflow.bean;

import java.io.Serializable;

/**
 * @project sevenpay-bms-web
 * @fileName OperationExceptionFlow.java
 * @author huiquan.ma
 * @date 2015年10月28日
 * @memo
 */
public class OperationExceptionFlow implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8090160685926746726L;

	/** 处理编号 */
	private String dealId;
	/** 处理序号 */
	private String dealSeq;
	/** 业务关联编号 **/
	private String operationId;
	/** 处理类型 */
	private String dealType;
	/** 业务处理类型 */
	private String transStep;
	/** 处理结果 */
	private String dealResult;
	/** 处理备注 */
	private String dealMemo;
	/** 处理人 */
	private String dealUser;
	/** 用户名 */
	private String userName;
	/** 处理日期 */
	private String dealDate;

	private String executeStatus;

	private String executeMemo;

	/** 处理时间 */
	private String dealDatetime;

	public String getDealId() {
		return dealId;
	}

	public void setDealId(String dealId) {
		this.dealId = dealId;
	}

	public String getDealSeq() {
		return dealSeq;
	}

	public void setDealSeq(String dealSeq) {
		this.dealSeq = dealSeq;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getTransStep() {
		return transStep;
	}

	public void setTransStep(String transStep) {
		this.transStep = transStep;
	}

	public String getDealResult() {
		return dealResult;
	}

	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}

	public String getDealMemo() {
		return dealMemo;
	}

	public void setDealMemo(String dealMemo) {
		this.dealMemo = dealMemo;
	}

	public String getDealUser() {
		return dealUser;
	}

	public void setDealUser(String dealUser) {
		this.dealUser = dealUser;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDealDate() {
		return dealDate;
	}

	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}

	public String getExecuteStatus() {
		return executeStatus;
	}

	public void setExecuteStatus(String executeStatus) {
		this.executeStatus = executeStatus;
	}

	public String getExecuteMemo() {
		return executeMemo;
	}

	public void setExecuteMemo(String executeMemo) {
		this.executeMemo = executeMemo;
	}

	public String getDealDatetime() {
		return dealDatetime;
	}

	public void setDealDatetime(String dealDatetime) {
		this.dealDatetime = dealDatetime;
	}

}
