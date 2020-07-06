package com.qifenqian.bms.basemanager.admanage.bean;

import java.io.Serializable;
import java.util.List;

public class AdCustInfoVO implements Serializable {
    private static final long serialVersionUID = -5951278814564266369L;
    /** 客户编号 **/
    private String custId;

    /** 客户名称 **/
    private String custName;

    private List<AdShopInfoVO> adShopInfoVOList;


    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public List<AdShopInfoVO> getAdShopInfoVOList() {
        return adShopInfoVOList;
    }

    public void setAdShopInfoVOList(List<AdShopInfoVO> adShopInfoVOList) {
        this.adShopInfoVOList = adShopInfoVOList;
    }
}
