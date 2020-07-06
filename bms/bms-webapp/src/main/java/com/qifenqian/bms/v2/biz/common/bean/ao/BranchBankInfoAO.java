package com.qifenqian.bms.v2.biz.common.bean.ao;

import com.qifenqian.bms.common.bean.BranchBankInfo;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;

/**
 * @author LvFeng
 * @Description: 支行信息
 * @date 2020/5/12 14:48
 */
@ApiOperation("支行信息")
public class BranchBankInfoAO implements Serializable {
    @ApiModelProperty(value = "银行信息")
    private BranchBankInfo queryBean;
    @ApiModelProperty(value = "渠道")
    private String channelCode;

    public BranchBankInfo getQueryBean() {
        return queryBean;
    }

    public void setQueryBean(BranchBankInfo queryBean) {
        this.queryBean = queryBean;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }
}
