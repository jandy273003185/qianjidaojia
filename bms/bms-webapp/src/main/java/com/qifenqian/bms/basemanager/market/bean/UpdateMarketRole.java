package com.qifenqian.bms.basemanager.market.bean;

import java.io.Serializable;


/*
 * 市场部角色
 */
public class UpdateMarketRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8652014093223196212L;
	
	
	//用户账号
	private String userCode;
	//用户名称
	private String userName;
	//用户角色
	private String userRole;
	//用户新名称
	private String newUserName;
	//用户新角色
	private String newUserRole;	
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getNewUserName() {
		return newUserName;
	}
	public void setNewUserName(String newUserName) {
		this.newUserName = newUserName;
	}
	public String getNewUserRole() {
		return newUserRole;
	}
	public void setNewUserRole(String newUserRole) {
		this.newUserRole = newUserRole;
	}
	

	
}
