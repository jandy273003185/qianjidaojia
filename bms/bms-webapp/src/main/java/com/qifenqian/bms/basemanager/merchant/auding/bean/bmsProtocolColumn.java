package com.qifenqian.bms.basemanager.merchant.auding.bean;

import java.io.Serializable;

/**
 * 
 * @author 后台管理系统-协议栏位表
 * 2017年6月28日 下午3:07:51
 */

public class bmsProtocolColumn implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6550298788100280697L;
	private String id;
	private String protocolId;//协议编号
	private String columnCode;
	private String columnValue;
	private String status;//'状态 生效VALID/失效DISABLE/待审核AUDIT/审核不通过AUDIT_NO',
	private String columnDesc;
	private String instDatetime;
	private String instUser;
	private String protocolName;
	private String protocolContent;
	
	
	public bmsProtocolColumn() {
		
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getColumnDesc() {
		return columnDesc;
	}
	public void setColumnDesc(String columnDesc) {
		this.columnDesc = columnDesc;
	}
	public String getInstDatetime() {
		return instDatetime;
	}
	public void setInstDatetime(String instDatetime) {
		this.instDatetime = instDatetime;
	}
	public String getInstUser() {
		return instUser;
	}
	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}
	public String getProtocolName() {
		return protocolName;
	}
	public void setProtocolName(String protocolName) {
		this.protocolName = protocolName;
	}
	public String getProtocolContent() {
		return protocolContent;
	}
	public void setProtocolContent(String protocolContent) {
		this.protocolContent = protocolContent;
	}

}
