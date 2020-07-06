package com.qifenqian.bms.platform.web.admin.user.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.qifenqian.bms.platform.web.admin.function.bean.Function;
import com.qifenqian.bms.platform.web.admin.role.bean.Role;
import com.qifenqian.bms.platform.web.admin.user.type.Sex;
import com.qifenqian.bms.platform.web.admin.user.type.UserStatus;

/**
 * 用户bean
 * 
 * @project gyzb-platform-web-admin
 * @fileName User.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
public class User implements Serializable {

	private static final long serialVersionUID = -9010748539674202829L;

	/** 用户编号 */
	private int userId;
	/** 员工号 */
	private String userCode;
	/** 用户名称 */
	private String userName;
	/** 密码 */
	private String password;
	/** 真实姓名 */
	private String realName;
	/** 用户昵称 */
	private String nickName;
	/** 部门编号 */
	private Integer deptId;
	/** 工作号码 */
	private String workPhone;
	/** 个人号码 */
	private String selfPhone;
	/** 工作邮箱 */
	private String workEmail;
	/** 个人邮箱 */
	private String selfEmail;
	/** 备注 */
	private String memo;
	/** 初始写入人 */
	private int instUser;
	/** 最后更改人 */
	private int lupdUser;
	/** 性别：MEN男/WOMEN女 */
	private Sex sex;
	/** 状态：生效VALID/冻结FREEZE/离职LEAVE */
	private UserStatus status;
	/** 初始写入时间 */
	private Date instDatetime;
	/** 最后更改时间 */
	private Date lupdDatetime;

	private int roleId;

	private String roleName;

	private String userPassword;
	private String newPassword;
	private String confirmPassword;
	/** 角色列表 */
	private List<Role> roleList;
	/** 菜单列表 */
	private List<Function> menuList;
	/** 功能列表 */
	private List<Function> functionList;
	private String instUserName;
	private String lupdUserName;

	/** 部门名称 */
	private String deptName;

	private String role;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public int getInstUser() {
		return instUser;
	}

	public void setInstUser(int instUser) {
		this.instUser = instUser;
	}

	public int getLupdUser() {
		return lupdUser;
	}

	public void setLupdUser(int lupdUser) {
		this.lupdUser = lupdUser;
	}

	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public UserStatus getStatus() {
		return status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public Date getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(Date instDatetime) {
		this.instDatetime = instDatetime;
	}

	public Date getLupdDatetime() {
		return lupdDatetime;
	}

	public void setLupdDatetime(Date lupdDatetime) {
		this.lupdDatetime = lupdDatetime;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Function> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Function> menuList) {
		this.menuList = menuList;
	}

	public List<Function> getFunctionList() {
		return functionList;
	}

	public void setFunctionList(List<Function> functionList) {
		this.functionList = functionList;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getInstUserName() {
		return instUserName;
	}

	public void setInstUserName(String instUserName) {
		this.instUserName = instUserName;
	}

	public String getLupdUserName() {
		return lupdUserName;
	}

	public void setLupdUserName(String lupdUserName) {
		this.lupdUserName = lupdUserName;
	}
}
