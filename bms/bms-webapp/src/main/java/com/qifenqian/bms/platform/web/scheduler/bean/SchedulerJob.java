package com.qifenqian.bms.platform.web.scheduler.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 任务调度配置
 * 
 * @author pc
 *
 */
public class SchedulerJob implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3849205841237292363L;

	private String id;
	private String jobName;
	private String state;
	private String isEnable;
	private String classPath;
	private String deleteFlag;
	private String parameter;
	private String creator;
	private Date createTime;
	private String lastUpdateUser;
	private String lastUpdateTime;
	private String memo;
	private String cron;
	private String hostName;
	private String executeParameter;
	private String instUserName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getClassPath() {
		return classPath;
	}

	public void setClassPath(String classPath) {
		this.classPath = classPath;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getExecuteParameter() {
		return executeParameter;
	}

	public void setExecuteParameter(String executeParameter) {
		this.executeParameter = executeParameter;
	}

	public String getInstUserName() {
		return instUserName;
	}

	public void setInstUserName(String instUserName) {
		this.instUserName = instUserName;
	}

}
