package com.qifenqian.bms.paymentmanager.bean;

import java.io.Serializable;

/**
 * 
 * @author 代付审核记录表
 *
 */
public class TdAgentPaymentChildRecord implements  Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String    recordId; // '流水号',
	 private String    batNo ;//'批次号',
	 private String    coreSn; // '核心流水号',
	 private String    coreReturnCode; //'核心返回码',
	 private String    coreReturnInfo;// '核心返回信息',
	 private String    coreReturnTime; //
	 private String    operType;  //'处理类型： pay_apply 代付申请, pay_revoke 代付撤销，pay_verified代付核销 ',
	 private String    createId ;
	 private String    createTime;
	 /**
	 * 处理状态: 01 核心返回异常；00核心处理成功；03 核心返回失败
	 */
	 private String Status = null;
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getBatNo() {
		return batNo;
	}
	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}
	public String getCoreSn() {
		return coreSn;
	}
	public void setCoreSn(String coreSn) {
		this.coreSn = coreSn;
	}
	public String getCoreReturnCode() {
		return coreReturnCode;
	}
	public void setCoreReturnCode(String coreReturnCode) {
		this.coreReturnCode = coreReturnCode;
	}
	public String getCoreReturnInfo() {
		return coreReturnInfo;
	}
	public void setCoreReturnInfo(String coreReturnInfo) {
		this.coreReturnInfo = coreReturnInfo;
	}
	public String getCoreReturnTime() {
		return coreReturnTime;
	}
	public void setCoreReturnTime(String coreReturnTime) {
		this.coreReturnTime = coreReturnTime;
	}
	public String getOperType() {
		return operType;
	}
	public void setOperType(String operType) {
		this.operType = operType;
	}
	public String getCreateId() {
		return createId;
	}
	public void setCreateId(String createId) {
		this.createId = createId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	 
}
