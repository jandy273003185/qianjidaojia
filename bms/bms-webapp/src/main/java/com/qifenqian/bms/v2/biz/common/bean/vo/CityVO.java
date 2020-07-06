package com.qifenqian.bms.v2.biz.common.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;
import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/23 15:18
 */
@ApiOperation("市")
public class CityVO implements Serializable {
    @ApiModelProperty(value = "编码")
    private String cityId;
    @ApiModelProperty(value = "名称")
    private String cityName;
    @ApiModelProperty(value = "区域/县列表")
    private List<AreaVO> areaVOS;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<AreaVO> getAreaVOS() {
        return areaVOS;
    }

    public void setAreaVOS(List<AreaVO> areaVOS) {
        this.areaVOS = areaVOS;
    }
}
