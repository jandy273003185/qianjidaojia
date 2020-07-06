package com.sevenpay.agentmanager.biz.agent.bean.condition;

import com.sevenpay.agentmanager.core.bean.BaseCondition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * ClassName：MerchantDO
 * Description：TODO
 * Author: LiBin
 * Date：2020/1/2 10:52 上午
 */
@ApiModel(value = "商户信息查询")
public class MerchantCondition extends BaseCondition implements Serializable {
    private static final long serialVersionUID = 2639642722366749021L;

    /**
     * 商户编号
     */
    @ApiModelProperty(value = "商户编号")
    private String mchId;
    /**
     * 查询起始时间
     */
    @ApiModelProperty(value = "查询起始时间")
    private String queryStartDate;
    /**
     * 查询终止时间
     */
    @ApiModelProperty(value = "查询终止时间")
    private String queryEndDate;

    private String lastTradeTime;

    public String getLastTradeTime() {
        return lastTradeTime;
    }

    public void setLastTradeTime(String lastTradeTime) {
        this.lastTradeTime = lastTradeTime;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getQueryStartDate() {
        return queryStartDate;
    }

    public void setQueryStartDate(String queryStartDate) {
        this.queryStartDate = queryStartDate;
    }

    public String getQueryEndDate() {
        return queryEndDate;
    }

    public void setQueryEndDate(String queryEndDate) {
        this.queryEndDate = queryEndDate;
    }
}
