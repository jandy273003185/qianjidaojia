package com.qifenqian.bms.myworkspace.allTask.bean;

public class AllTaskBean extends ActHiTaskinst{

	/**
	 * 
	 */
	private static final long serialVersionUID = -585089690060262974L;

	
	private String proName;
	
	private String realName;
	
	private String businessKey;
	
	private String startUserId;
	

	private String taskBeginTime;
	
	private String taskEndTime;
	
	private String auditStatu;
	
	
	
	public String getAuditStatu() {
		return auditStatu;
	}


	public void setAuditStatu(String auditStatu) {
		this.auditStatu = auditStatu;
	}


	public String getBusinessKey() {
		return businessKey;
	}


	public void setBusinessKey(String businessKey) {
		this.businessKey = businessKey;
	}


	public String getStartUserId() {
		return startUserId;
	}


	public void setStartUserId(String startUserId) {
		this.startUserId = startUserId;
	}


	public String getTaskBeginTime() {
		return taskBeginTime;
	}


	public void setTaskBeginTime(String taskBeginTime) {
		this.taskBeginTime = taskBeginTime;
	}


	public String getTaskEndTime() {
		return taskEndTime;
	}


	public void setTaskEndTime(String taskEndTime) {
		this.taskEndTime = taskEndTime;
	}

	
	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public String getProName() {
		return proName;
	}


	public void setProName(String proName) {
		this.proName = proName;
	}
	
	
}
