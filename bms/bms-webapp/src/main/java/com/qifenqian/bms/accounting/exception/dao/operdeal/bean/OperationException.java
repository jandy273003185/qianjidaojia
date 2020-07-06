package com.qifenqian.bms.accounting.exception.dao.operdeal.bean;

import java.io.Serializable;

/**
 * @project sevenpay-bms-web
 * @fileName OperationException.java
 * @author huiquan.ma
 * @date 2015年10月28日
 * @memo
 */
public class OperationException implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5243623316403593441L;

	private String id;
	private String operationId;
	private String operationType;
	private String status;
	private String instUser;
	private String instDatetime;
	private String dealResult;
	private String dealMemo;
	private String dealUser;
	private String dealDate;
	private String dealDatetime;
	private String relateId;
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperationId() {
		return operationId;
	}

	public void setOperationId(String operationId) {
		this.operationId = operationId;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getInstUser() {
		return instUser;
	}

	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}

	public String getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(String instDatetime) {
		this.instDatetime = instDatetime;
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

	public String getDealDate() {
		return dealDate;
	}

	public void setDealDate(String dealDate) {
		this.dealDate = dealDate;
	}

	public String getDealDatetime() {
		return dealDatetime;
	}

	public void setDealDatetime(String dealDatetime) {
		this.dealDatetime = dealDatetime;
	}

	public String getRelateId() {
		return relateId;
	}

	public void setRelateId(String relateId) {
		this.relateId = relateId;
	}
}
