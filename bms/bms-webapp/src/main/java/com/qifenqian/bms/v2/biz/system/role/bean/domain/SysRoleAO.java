package com.qifenqian.bms.v2.biz.system.role.bean.domain;

import com.qifenqian.bms.platform.web.admin.function.bean.Function;
import com.qifenqian.bms.platform.web.admin.role.type.RoleIsValid;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * ClassName：SysRoleDO
 * Description：TODO
 * Author: LiBin
 * Date：2019/12/27 4:36 下午
 */
public class SysRoleAO implements Serializable {

    private static final long serialVersionUID = 5537780202641634944L;

    private int roleId;
    private String roleCode;
    private String roleName;
    private String memo;
    private RoleIsValid isValid;
    private String indexPage;
    private int instUser;
    private Date instDatetime;
    private int lupdUser;
    private Date lupdDatetime;
    private List<Function> functionList;
    private String instUserName;
    private String lupdUserName;
    private String checkValue;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public RoleIsValid getIsValid() {
        return isValid;
    }

    public void setIsValid(RoleIsValid isValid) {
        this.isValid = isValid;
    }

    public String getIndexPage() {
        return indexPage;
    }

    public void setIndexPage(String indexPage) {
        this.indexPage = indexPage;
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

    public int getLupdUser() {
        return lupdUser;
    }

    public void setLupdUser(int lupdUser) {
        this.lupdUser = lupdUser;
    }

    public Date getLupdDatetime() {
        return lupdDatetime;
    }

    public void setLupdDatetime(Date lupdDatetime) {
        this.lupdDatetime = lupdDatetime;
    }

    public List<Function> getFunctionList() {
        return functionList;
    }

    public void setFunctionList(List<Function> functionList) {
        this.functionList = functionList;
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

    public String getCheckValue() {
        return checkValue;
    }

    public void setCheckValue(String checkValue) {
        this.checkValue = checkValue;
    }
}
