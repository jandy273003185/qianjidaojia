package com.qifenqian.bms.myworkspace.overAudit.bean;

import com.qifenqian.bms.myworkspace.allTask.bean.ActHiTaskinst;



public class OverAuditBean extends ActHiTaskinst{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7827623547621812570L;
	private String proName; //流程实例名称

	private String realName;
	
	private String businessKey;
	
	private String startUserId;
	
	private String auditBeginTime;
	
	private String auditEndTime;
	
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


	public String getAuditBeginTime() {
		return auditBeginTime;
	}


	public void setAuditBeginTime(String auditBeginTime) {
		this.auditBeginTime = auditBeginTime;
	}


	public String getAuditEndTime() {
		return auditEndTime;
	}


	public void setAuditEndTime(String auditEndTime) {
		this.auditEndTime = auditEndTime;
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
