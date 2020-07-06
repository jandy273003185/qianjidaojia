package com.qifenqian.bms.platform.web.schedulerLog.bean;

import java.io.Serializable;
import java.util.Date;

public class SchedulerLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2095665916025201549L;

	private String logId;

	private String executeDate;

	private String jobCode;

	private String jobName;

	private String parametr;

	private String hostname;

	private Date startDatetime;

	private Date endDatetime;

	private String executeFlag;

	private String executeFlag2;

	private String executeType;

	private String executeType2;

	private String executeDesc;

	private String executeOper;

	public String getExecuteFlag2() {
		return executeFlag2;
	}

	public void setExecuteFlag2(String executeFlag2) {
		this.executeFlag2 = executeFlag2;
	}

	public String getExecuteType2() {
		return executeType2;
	}

	public void setExecuteType2(String executeType2) {
		this.executeType2 = executeType2;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public String getExecuteDate() {
		return executeDate;
	}

	public void setExecuteDate(String executeDate) {
		this.executeDate = executeDate;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getParametr() {
		return parametr;
	}

	public void setParametr(String parametr) {
		this.parametr = parametr;
	}

	public String getHostname() {
		return hostname;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public Date getStartDatetime() {
		return startDatetime;
	}

	public void setStartDatetime(Date startDatetime) {
		this.startDatetime = startDatetime;
	}

	public Date getEndDatetime() {
		return endDatetime;
	}

	public void setEndDatetime(Date endDatetime) {
		this.endDatetime = endDatetime;
	}

	public String getExecuteFlag() {
		return executeFlag;
	}

	public void setExecuteFlag(String executeFlag) {
		this.executeFlag = executeFlag;
	}

	public String getExecuteType() {
		return executeType;
	}

	public void setExecuteType(String executeType) {
		this.executeType = executeType;
	}

	public String getExecuteDesc() {
		return executeDesc;
	}

	public void setExecuteDesc(String executeDesc) {
		this.executeDesc = executeDesc;
	}

	public String getExecuteOper() {
		return executeOper;
	}

	public void setExecuteOper(String executeOper) {
		this.executeOper = executeOper;
	}

}