package com.qifenqian.bms.v2.biz.system.user.bean.domain;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

/**
 * @author LiBin
 * @Description: 登录
 * @date 2020/4/7 10:06
 */
@ApiOperation(value = "登录参数")
public class LoginAO {
    @ApiModelProperty(value = "账号")
    private String account;
    @ApiModelProperty(value = "密码")
    private String password;

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
