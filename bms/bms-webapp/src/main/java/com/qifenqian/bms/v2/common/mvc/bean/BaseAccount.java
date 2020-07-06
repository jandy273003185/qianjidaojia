package com.qifenqian.bms.v2.common.mvc.bean;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author LiBin
 * @Description: 登录基础类
 * @date 2020/3/30 14:42
 */
public class BaseAccount implements Serializable {
    @ApiModelProperty(value = "ID")
    private Long loginId;
    @ApiModelProperty(value = "账号")
    private String account;
    @ApiModelProperty(value = "密码")
    private String password;

    public Long getLoginId() {
        return loginId;
    }

    public void setLoginId(Long loginId) {
        this.loginId = loginId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
