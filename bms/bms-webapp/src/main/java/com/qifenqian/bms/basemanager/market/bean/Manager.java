package com.qifenqian.bms.basemanager.market.bean;

import java.io.Serializable;


/*
 * 客户经理
 */
public class Manager implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1157196802226776699L;
	
	//客户经理账号
	private String userCode;
	
	//客户经理名称
	private String userName;

	//团队负责人id
	private String teamLeaderId;

	//创建时间
	private String createTime;
	
	//更新时间
	private String updateTime;
	
	//更新人
	private String updateId;
	
	//查询起始时间
	private String createStartTime;
	
	//查询终止时间
	private String createEndTime;
	
	//团队负责人名称
	private String teamLeaderName;
	
	
	

	public String getTeamLeaderName() {
		return teamLeaderName;
	}

	public void setTeamLeaderName(String teamLeaderName) {
		this.teamLeaderName = teamLeaderName;
	}

	public String getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}

	public String getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
	}

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

	public String getTeamLeaderId() {
		return teamLeaderId;
	}

	public void setTeamLeaderId(String teamLeaderId) {
		this.teamLeaderId = teamLeaderId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateId() {
		return updateId;
	}

	public void setUpdateId(String updateId) {
		this.updateId = updateId;
	}
	
	
	
}
