package com.qifenqian.bms.accounting.bmsexception.bean;

import com.qifenqian.bms.accounting.bmsexception.type.BusType;


public class BmsException {

	private String exceId;
	private String exceType;
	private String level;
	private String model;
	private BusType busType;
	private String exCode;
	private String exDesc;
	private String currentState;
	private String description;
	private String createDate;
	private String updateDate;
	private String updateby;
	private String orderId;
	private String beginCreateTime;
	private String endCreateTime;
	private String beginUpdateTime;
	private String endUpdateTime;
	
	public String getExceId() {
		return exceId;
	}
	public void setExceId(String exceId) {
		this.exceId = exceId;
	}
	public String getExceType() {
		return exceType;
	}
	public void setExceType(String exceType) {
		this.exceType = exceType;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	public BusType getBusType() {
		return busType;
	}
	public void setBusType(BusType busType) {
		this.busType = busType;
	}
	public String getExCode() {
		return exCode;
	}
	public void setExCode(String exCode) {
		this.exCode = exCode;
	}
	public String getExDesc() {
		return exDesc;
	}
	public void setExDesc(String exDesc) {
		this.exDesc = exDesc;
	}
	public String getCurrentState() {
		return currentState;
	}
	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	public String getUpdateby() {
		return updateby;
	}
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getBeginCreateTime() {
		return beginCreateTime;
	}
	public void setBeginCreateTime(String beginCreateTime) {
		this.beginCreateTime = beginCreateTime;
	}
	public String getEndCreateTime() {
		return endCreateTime;
	}
	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
	public String getBeginUpdateTime() {
		return beginUpdateTime;
	}
	public void setBeginUpdateTime(String beginUpdateTime) {
		this.beginUpdateTime = beginUpdateTime;
	}
	public String getEndUpdateTime() {
		return endUpdateTime;
	}
	public void setEndUpdateTime(String endUpdateTime) {
		this.endUpdateTime = endUpdateTime;
	}

}
