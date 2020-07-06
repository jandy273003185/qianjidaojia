package com.qifenqian.bms.basemanager.market.bean;

import java.io.Serializable;


/*
 * 市场部角色
 */
public class MarketRole implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5217609165490658038L;
	
	//用户账号
	private String userCode;
	//用户名称
	private String userName;
	//用户角色
	private String userRole;
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
	

	
}
