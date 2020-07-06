package com.qifenqian.bms.myworkspace.applicationForm.bean;

public class ApplicationFormBean extends ActHiProcinst {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4462630669172037265L;

	
	private String auditName;

	private String taskName;
	
	private String proName;
	
	private String realName;
	
	private String isOver;

	private String applyBeginTime;
	
	private String applyEndTime;
	
	private String userId;
	
	private String url;
	
	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getApplyBeginTime() {
		return applyBeginTime;
	}


	public void setApplyBeginTime(String applyBeginTime) {
		this.applyBeginTime = applyBeginTime;
	}


	public String getApplyEndTime() {
		return applyEndTime;
	}


	public void setApplyEndTime(String applyEndTime) {
		this.applyEndTime = applyEndTime;
	}


	public String getTaskName() {
		return taskName;
	}


	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}


	public String getProName() {
		return proName;
	}


	public void setProName(String proName) {
		this.proName = proName;
	}


	public String getAuditName() {
		return auditName;
	}


	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}


	public String getRealName() {
		return realName;
	}


	public void setRealName(String realName) {
		this.realName = realName;
	}


	public String getIsOver() {
		return isOver;
	}


	public void setIsOver(String isOver) {
		this.isOver = isOver;
	}
	
	
}
