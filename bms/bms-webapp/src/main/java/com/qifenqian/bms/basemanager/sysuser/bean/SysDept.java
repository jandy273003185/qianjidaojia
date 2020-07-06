package com.qifenqian.bms.basemanager.sysuser.bean;

/**
 * 系统-部门表
 */
public class SysDept {
	private String     deptId;
	private String     deptCode;
	private String     deptLevel;
	private String     deptName;
	private String     deptAddress;
	private String     upDeptId;
	private String     memo;
	private String     status;
	private String     instUser;
	private String     instDatetime;
	private String     lupdUser;
	private String     lupdDatetime;

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptLevel() {
        return deptLevel;
    }

    public void setDeptLevel(String deptLevel) {
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

    public String getUpDeptId() {
        return upDeptId;
    }

    public void setUpDeptId(String upDeptId) {
        this.upDeptId = upDeptId;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
