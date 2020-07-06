package com.qifenqian.bms.accounting.checkquery.bean;

import java.io.Serializable;

public class QfqexceptionBase implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6650100918156197873L;

	private String endBalTime;
	private String beginBalTime;
	private String workDateMin;
	private String workDateMax;
	private String channelId;
	private String transStatus;
	private String transType;
	private String system;
	private String status;

	public String getEndBalTime() {
		return endBalTime;
	}

	public void setEndBalTime(String endBalTime) {
		this.endBalTime = endBalTime;
	}

	public String getBeginBalTime() {
		return beginBalTime;
	}

	public void setBeginBalTime(String beginBalTime) {
		this.beginBalTime = beginBalTime;
	}

	public String getWorkDateMin() {
		return workDateMin;
	}

	public void setWorkDateMin(String workDateMin) {
		this.workDateMin = workDateMin;
	}

	public String getWorkDateMax() {
		return workDateMax;
	}

	public void setWorkDateMax(String workDateMax) {
		this.workDateMax = workDateMax;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getTransStatus() {
		return transStatus;
	}

	public void setTransStatus(String transStatus) {
		this.transStatus = transStatus;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
