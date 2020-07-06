package com.qifenqian.bms.paymentmanager.bean;

import java.io.Serializable;

public class TdPaymentAccountAuditInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/***编号**/
	private String id;
	/***账号***/
	private String account;
	/***审核状态 ： 00 审核通过，99 审核不通过**/
	private String auditStatus;
	/***审核时间***/
	private String auditTime;
	/***审核描述**/
	private String memo;
	/***审核人***/
	private String auditUser;
	/***商户名称***/
	private String custName;
	
	
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(String auditUser) {
		this.auditUser = auditUser;
	}
	
	
}
