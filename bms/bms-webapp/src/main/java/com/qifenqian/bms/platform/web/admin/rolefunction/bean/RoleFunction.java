package com.qifenqian.bms.platform.web.admin.rolefunction.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @project gyzb-platform-web-admin
 * @fileName RoleFunction.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
public class RoleFunction implements Serializable{

	private static final long serialVersionUID = 1L;
	
	/*******************************************************************/
	
	/** 角色编号     */      	private int roleId;
	/** 功能编号     */		private int functionId;
	/** 初始写入人 */		private int instUser;
	/** 初始时间     */		private Date instDatetime;
	
	/*******************************************************************/
	
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	public int getFunctionId() {
		return functionId;
	}
	public void setFunctionId(int functionId) {
		this.functionId = functionId;
	}
	public int getInstUser() {
		return instUser;
	}
	public void setInstUser(int instUser) {
		this.instUser = instUser;
	}
	public Date getInstDatetime() {
		return instDatetime;
	}
	public void setInstDatetime(Date instDatetime) {
		this.instDatetime = instDatetime;
	}
	

}
