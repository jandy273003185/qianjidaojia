package com.qifenqian.bms.v2.biz.ad.bean;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;

/**
 * @author LvFeng
 * @Description: 商户查询
 * @date 2020/5/19 11:23
 */
@ApiOperation("商户查询")
public class CustomAO implements Serializable {
    @ApiModelProperty(value = "商户名称")
    private String customName;
    @ApiModelProperty(value = "门店名称")
    private String shopName;

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
