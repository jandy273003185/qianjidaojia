package com.qifenqian.bms.v2.biz.message.sms.bean.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/27 17:34
 */
@ApiModel("短信")
public class BaseMessageAO implements Serializable {

    @ApiModelProperty(value = "ids")
    private List<String> ids;
    @ApiModelProperty(value = "短信发送IDS")
    private String sendId;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }
}
