package com.qifenqian.bms.basemanager.admanage.bean;

import java.io.Serializable;

public class AdShopInfoVO implements Serializable {
    private static final long serialVersionUID = 8545192318528806445L;

    private String shopId;
    private String shopName;

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}
