package com.qifenqian.bms.basemanager.protocolcontent.bean;

import java.io.Serializable;
import java.util.Date;

public class ProtocolColumn implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -122675899927236122L;
	/****/
	private String id;
	/****/
	private String protocolId;
	/****/
	private String columnCode;
	/****/
	private String columnValue;
	/****/
	private String columnDesc;
	/****/
	private String status;
	/****/
	private Date instDatetime;

	private String instUser;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
	}

	public String getColumnCode() {
		return columnCode;
	}

	public void setColumnCode(String columnCode) {
		this.columnCode = columnCode;
	}

	public String getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}

	public String getColumnDesc() {
		return columnDesc;
	}

	public void setColumnDesc(String columnDesc) {
		this.columnDesc = columnDesc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(Date instDatetime) {
		this.instDatetime = instDatetime;
	}

	public String getInstUser() {
		return instUser;
	}

	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}

}
