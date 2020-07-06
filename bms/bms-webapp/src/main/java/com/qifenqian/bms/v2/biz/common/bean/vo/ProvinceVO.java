package com.qifenqian.bms.v2.biz.common.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

import java.io.Serializable;
import java.util.List;

/**
 * @author LiBin
 * @Description: e
 * @date 2020/4/23 14:39
 */
@ApiOperation("省")
public class ProvinceVO implements Serializable {

    @ApiModelProperty(value = "编码")
    private String provinceId;
    @ApiModelProperty(value = "名称")
    private String provinceName;
    @ApiModelProperty(value = "城市列表")
    private List<CityVO> cityVOS;

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public List<CityVO> getCityVOS() {
        return cityVOS;
    }

    public void setCityVOS(List<CityVO> cityVOS) {
        this.cityVOS = cityVOS;
    }
}
