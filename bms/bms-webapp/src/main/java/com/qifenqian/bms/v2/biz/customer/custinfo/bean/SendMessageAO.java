package com.qifenqian.bms.v2.biz.customer.custinfo.bean;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;

/**
 * @author LvFeng
 * @Description: 发送信息
 * @date 2020/4/29 11:28
 */
@ApiOperation(value = "发送信息")
public class SendMessageAO implements Serializable {

    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "短信内容")
    private String content;
    @ApiModelProperty(value = "客户编号")
    private String custId;
    @ApiModelProperty(value = "新密码")
    private String tradeCode;
    @ApiModelProperty(value = "邮箱")
    private String email;
    @ApiModelProperty(value = "类型")
    private String type;

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
