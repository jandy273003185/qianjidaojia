package com.qifenqian.bms.v2.biz.system.user.bean.domain;

import com.qifenqian.bms.v2.common.mvc.bean.BaseAccount;
import io.swagger.annotations.ApiModelProperty;

import java.util.Set;

/**
 * @author LiBin
 * @Description: 登录类
 * @date 2020/3/30 14:42
 */
public class CurrentAccount extends BaseAccount  {
    @ApiModelProperty(value = "登录者姓名")
    private String name;

    private Set<String> functionSet;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getFunctionSet() {
        return functionSet;
    }

    public void setFunctionSet(Set<String> functionSet) {
        this.functionSet = functionSet;
    }
}
