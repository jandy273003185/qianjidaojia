package com.qifenqian.bms.platform.web.admin.userRole.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @project gyzb-platform-web-admin
 * @fileName UserRole.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
public class UserRole implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/*********************************************************************/
	
	/** 用户ID */		private int userId;
	/** 角色ID*/		private int roleId;
	/** 创建人*/		private int instUser;
	/** 创建时间*/		private Date instDatetime;
	
	/*********************************************************************/
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
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
