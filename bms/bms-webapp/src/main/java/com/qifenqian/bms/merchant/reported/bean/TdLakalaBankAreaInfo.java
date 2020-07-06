package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

/**
 * @author LvFeng
 * @Description: 拉卡拉银行地区Bean
 * @date 2020/5/21 15:08
 */
public class TdLakalaBankAreaInfo implements Serializable {

    private static final long serialVersionUID = -9055762054479735839L;
    /**
     * 地区id
     */
    private String districtId;
    /**
     * 地区名称
     */
    private String districtName;
    /**
     * 上级地区id
     */
    private String districtParent;

    public String getDistrictId() {
        return districtId;
    }

    public void setDistrictId(String districtId) {
        this.districtId = districtId;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public String getDistrictParent() {
        return districtParent;
    }

    public void setDistrictParent(String districtParent) {
        this.districtParent = districtParent;
    }
}
