package com.qifenqian.bms.accounting.subjectConfig.bean;

import java.io.Serializable;

public class SubjectConfig implements Serializable {
	
	private static final long serialVersionUID = -9131354286646167005L;
	
	private String id;
	private String subjectId;
	private String subjectC;
	private String subjectD;
	private String payAccType;
	private String rcvAccType;
	private String tradType;
	private String state;
	private String creator;
	private String createTime;
	private String lastupdateUser;
	private String lastupdateTime;
	private String memo;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(String subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectC() {
		return subjectC;
	}
	public void setSubjectC(String subjectC) {
		this.subjectC = subjectC;
	}
	public String getSubjectD() {
		return subjectD;
	}
	public void setSubjectD(String subjectD) {
		this.subjectD = subjectD;
	}
	public String getPayAccType() {
		return payAccType;
	}
	public void setPayAccType(String payAccType) {
		this.payAccType = payAccType;
	}
	public String getRcvAccType() {
		return rcvAccType;
	}
	public void setRcvAccType(String rcvAccType) {
		this.rcvAccType = rcvAccType;
	}
	public String getTradType() {
		return tradType;
	}
	public void setTradType(String tradType) {
		this.tradType = tradType;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCreator() {
		return creator;
	}
	public void setCreator(String creator) {
		this.creator = creator;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLastupdateUser() {
		return lastupdateUser;
	}
	public void setLastupdateUser(String lastupdateUser) {
		this.lastupdateUser = lastupdateUser;
	}
	public String getLastupdateTime() {
		return lastupdateTime;
	}
	public void setLastupdateTime(String lastupdateTime) {
		this.lastupdateTime = lastupdateTime;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	
}
