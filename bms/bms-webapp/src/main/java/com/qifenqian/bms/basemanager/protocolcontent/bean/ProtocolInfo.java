package com.qifenqian.bms.basemanager.protocolcontent.bean;

import java.io.Serializable;
import java.util.Date;

public class ProtocolInfo implements Serializable {
	
	private static final long serialVersionUID = -1043500768587845825L;
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

	//客服经理
	private String custManager;
	
	//服务商
	private String serviceProvider;
	
	private String servicePhone;
	//客服经理电话
	private String selfPhone;
	
	//联系人
	private String linkMan;
	
	public String getLinkMan() {
		return linkMan;
	}

	public void setLinkMan(String linkMan) {
		this.linkMan = linkMan;
	}

	public String getServicePhone() {
		return servicePhone;
	}

	public void setServicePhone(String servicePhone) {
		this.servicePhone = servicePhone;
	}

	public String getSelfPhone() {
		return selfPhone;
	}

	public void setSelfPhone(String selfPhone) {
		this.selfPhone = selfPhone;
	}

	public String getCustManager() {
		return custManager;
	}

	public void setCustManager(String custManager) {
		this.custManager = custManager;
	}

	public String getServiceProvider() {
		return serviceProvider;
	}

	public void setServiceProvider(String serviceProvider) {
		this.serviceProvider = serviceProvider;
	}

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
