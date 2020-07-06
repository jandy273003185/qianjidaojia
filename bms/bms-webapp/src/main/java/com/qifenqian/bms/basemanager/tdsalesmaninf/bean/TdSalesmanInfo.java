package com.qifenqian.bms.basemanager.tdsalesmaninf.bean;

import java.util.Date;

/**
 * 员工信息
 * @author wulingtong
 *
 */
public class TdSalesmanInfo {

	/** 业务员Id*/          	private String salesmanId;
	/** 业务员名称*/           	private String userName;
	/** 密码*/            	private String password;
	/** 业务员电话*/          	private String userPhone;
	/** 服务商客户号 */          	private String custId;
	
	/** 状态：生效VALID/冻结FRE*/	private String status;

	/** 用户部门*/        	private Date createTime;

	public String getSalesmanId() {
		return salesmanId;
	}

	public void setSalesmanId(String salesmanId) {
		this.salesmanId = salesmanId;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
}
