package com.qifenqian.bms.basemanager.sysuser.bean;

/**
 * 员工信息
 * @author wulingtong
 *
 */
public class SysUser {

	/** 用户编号*/          	private String userId;
	/** 员工号*/           	private String userCode;
	/** 用户名*/           	private String userName;
	/** 密码*/            	private String password;
	/** 真实姓名*/          	private String realName;
	/** 用户昵称*/          	private String nickName;
	/** 部门编号*/          	private String deptId;
	/** 性别：MEN男/WOMEN女*/	private String sex;
	/** 工作号码*/          	private String workPhone;
	/** 个人号码*/          	private String selfPhone;
	/** 工作邮箱*/          	private String workEmail;
	/** 个人邮箱*/          	private String selfEmail;
	/** 状态：生效VALID/冻结FRE*/	private String status;
	/** 备注*/            	private String memo;
	/** 初始写入人*/         	private String instUser;
	/** 初始写入时间*/        	private String instDatetime;
	/** 最后更改人*/         	private String lupdUser;
	/** 最后更改时间*/        	private String lupdDatetime;
	/** 用户部门*/        	private String deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getWorkPhone() {
		return workPhone;
	}
	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}
	public String getSelfPhone() {
		return selfPhone;
	}
	public void setSelfPhone(String selfPhone) {
		this.selfPhone = selfPhone;
	}
	public String getWorkEmail() {
		return workEmail;
	}
	public void setWorkEmail(String workEmail) {
		this.workEmail = workEmail;
	}
	public String getSelfEmail() {
		return selfEmail;
	}
	public void setSelfEmail(String selfEmail) {
		this.selfEmail = selfEmail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getInstUser() {
		return instUser;
	}
	public void setInstUser(String instUser) {
		this.instUser = instUser;
	}
	public String getInstDatetime() {
		return instDatetime;
	}
	public void setInstDatetime(String instDatetime) {
		this.instDatetime = instDatetime;
	}
	public String getLupdUser() {
		return lupdUser;
	}
	public void setLupdUser(String lupdUser) {
		this.lupdUser = lupdUser;
	}
	public String getLupdDatetime() {
		return lupdDatetime;
	}
	public void setLupdDatetime(String lupdDatetime) {
		this.lupdDatetime = lupdDatetime;
	}
	
	
	
}
