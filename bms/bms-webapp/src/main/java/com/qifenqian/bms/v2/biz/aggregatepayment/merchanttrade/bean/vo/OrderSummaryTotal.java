package com.qifenqian.bms.v2.biz.aggregatepayment.merchanttrade.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/24 10:08
 */
@ApiOperation("交易汇总")
public class OrderSummaryTotal implements Serializable {

    @ApiModelProperty(value = "数量")
    private BigDecimal count;
    @ApiModelProperty(value = "和")
    private BigDecimal sum;
    @ApiModelProperty(value = "结算")
    private BigDecimal settle;

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    public BigDecimal getSettle() {
        return settle;
    }

    public void setSettle(BigDecimal settle) {
        this.settle = settle;
    }
}
