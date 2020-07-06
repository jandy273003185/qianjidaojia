package com.qifenqian.bms.v2.biz.merchant.channel.bean.domain;

import com.qifenqian.bms.merchant.channel.bean.ChannelBean;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/21 11:42
 */
@ApiOperation(value = "渠道编辑")
public class ChannelAO implements Serializable {
    @ApiModelProperty(value = "待修改体")
    private ChannelBean channelBean;
    @ApiModelProperty(value = "旧修改体")
    private ChannelBean oldChannelBean;

    public ChannelBean getChannelBean() {
        return channelBean;
    }

    public void setChannelBean(ChannelBean channelBean) {
        this.channelBean = channelBean;
    }

    public ChannelBean getOldChannelBean() {
        return oldChannelBean;
    }

    public void setOldChannelBean(ChannelBean oldChannelBean) {
        this.oldChannelBean = oldChannelBean;
    }
}
