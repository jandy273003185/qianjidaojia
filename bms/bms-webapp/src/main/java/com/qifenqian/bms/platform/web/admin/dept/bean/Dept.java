package com.qifenqian.bms.platform.web.admin.dept.bean;

import java.io.Serializable;

import com.qifenqian.bms.platform.web.admin.dept.type.DeptStatus;

/**
 * 部门
 * 
 * @project gyzb-platform-web-admin
 * @fileName Dept.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
public class Dept implements Serializable {

	private static final long serialVersionUID = 6807934484007604510L;

	/** 部门编号 */
	private int deptId;
	/** 部门代码 */
	private String deptCode;
	/** 部门级别 */
	private int deptLevel;
	/** 部门名称 */
	private String deptName;
	/** 部门地址 */
	private String deptAddress;
	/** 上级部门 */
	private int upDeptId;
	/** 备注 */
	private String memo;
	/** 初始写入人 */
	private int instUser;
	/** 最后更改人 */
	private int lupdUser;
	/** 状态：VALID有效/DISABLE失效 */
	private DeptStatus status;
	/** 初始时间：YYYY-MM-DD HH:MI:SS */
	private String instDatetime;
	/** 最后更改时间：YYYY-MM-DD HH:MI:SS */
	private String lupdDatetime;
	
	private String instUserName;
	
	private String lupdUserName;

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public int getDeptLevel() {
		return deptLevel;
	}

	public void setDeptLevel(int deptLevel) {
		this.deptLevel = deptLevel;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptAddress() {
		return deptAddress;
	}

	public void setDeptAddress(String deptAddress) {
		this.deptAddress = deptAddress;
	}

	public int getUpDeptId() {
		return upDeptId;
	}

	public void setUpDeptId(int upDeptId) {
		this.upDeptId = upDeptId;
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

	public DeptStatus getStatus() {
		return status;
	}

	public void setStatus(DeptStatus status) {
		this.status = status;
	}

	public String getInstDatetime() {
		return instDatetime;
	}

	public void setInstDatetime(String instDatetime) {
		this.instDatetime = instDatetime;
	}

	public String getLupdDatetime() {
		return lupdDatetime;
	}

	public void setLupdDatetime(String lupdDatetime) {
		this.lupdDatetime = lupdDatetime;
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
