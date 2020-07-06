package com.qifenqian.bms.basemanager.custInfo.bean;

import java.io.Serializable;
import java.util.Date;

public class TdLoginUserInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4680339492420446439L;

	private String custId;

    private String roleId;

    private String loginPwd;

    private String mobile;

    private String email;

    private String state;

    private Short wrongPwdCount;

    private String isBandMobileForLogin;

    private Short onlineState;

    private Date lastLoginTime;

    private String attachStr;

    private String createId;

    private Date createTime;

    private String modifyId;

    private Date modifyTime;

    private Date freezetime;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Short getWrongPwdCount() {
        return wrongPwdCount;
    }

    public void setWrongPwdCount(Short wrongPwdCount) {
        this.wrongPwdCount = wrongPwdCount;
    }

    public String getIsBandMobileForLogin() {
        return isBandMobileForLogin;
    }

    public void setIsBandMobileForLogin(String isBandMobileForLogin) {
        this.isBandMobileForLogin = isBandMobileForLogin;
    }

    public Short getOnlineState() {
        return onlineState;
    }

    public void setOnlineState(Short onlineState) {
        this.onlineState = onlineState;
    }

    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getAttachStr() {
        return attachStr;
    }

    public void setAttachStr(String attachStr) {
        this.attachStr = attachStr;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyId() {
        return modifyId;
    }

    public void setModifyId(String modifyId) {
        this.modifyId = modifyId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getFreezetime() {
        return freezetime;
    }

    public void setFreezetime(Date freezetime) {
        this.freezetime = freezetime;
    }
}