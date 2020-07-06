package com.qifenqian.bms.platform.web.myWorkSpace.bean;

public class WaitTaskBean extends ActRuTask {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1630224911467432921L;
	
	private String proName;
	
	private String startUser;
	
	private String taskBeginTime;
	
	private String taskEndTime;
	
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

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getStartUser() {
		return startUser;
	}

	public void setStartUser(String startUser) {
		this.startUser = startUser;
	}
	
	

}
