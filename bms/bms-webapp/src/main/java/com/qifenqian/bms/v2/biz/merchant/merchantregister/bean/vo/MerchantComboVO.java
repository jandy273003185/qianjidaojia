package com.qifenqian.bms.v2.biz.merchant.merchantregister.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @author LiBin
 * @Description: 商户下拉对象
 * @date 2020/4/22 11:48
 */
@ApiModel(value = "商户下拉对象")
public class MerchantComboVO implements Serializable {
    /**
     * 商户编号
     */
    @ApiModelProperty(value = "商户编号")
    private String merchantCode;

    /**
     * 商户简称
     */
    @ApiModelProperty(value = "商户简称")
    private String value;

    public String getMerchantCode() {
        return merchantCode;
    }

    public void setMerchantCode(String merchantCode) {
        this.merchantCode = merchantCode;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
