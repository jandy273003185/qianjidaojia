package com.qifenqian.bms.v2.biz.common.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/23 15:19
 */

@ApiOperation("区/县")
public class AreaVO implements Serializable {

    @ApiModelProperty(value = "编码")
    private String areaId;
    @ApiModelProperty(value = "名称")
    private String areaName;

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
