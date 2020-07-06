package com.qifenqian.bms.userLoginRelate.bean;

import java.io.Serializable;
import java.util.Date;

public class UserLoginRelate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	/** 用户id */
	private String userId;
	
	/** 用户类型
	 *  per 个人角色,ent 企业角色,
	 *  agent 代理商角色,cust 客户经理,
	 *  salesman 业务员,cashier 收银员,
	 *  finance 财务员,shopmanager 店长',
	 */
	private String userType;
	/** 登陆类型 1.微信、2其他 */
	private String loginType;
	
	/** 类型值编号 */
	private String openId;
	/** 是否解绑 0 解绑 1 绑定 */
	private String ifUnbind;
	
	private Date createTime;
	private Date updateTime;
	
	/** 业务员所属服务商 */
	private String custId;
	private String custName;
	private String userName;
	/**
	 * 起止时间
	 */
	private String startTime;
	/**
	 * 结束时间
	 */
	private String endTime;
	
	
	
	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}



	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getIfUnbind() {
		return ifUnbind;
	}

	public void setIfUnbind(String ifUnbind) {
		this.ifUnbind = ifUnbind;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}
	
}
