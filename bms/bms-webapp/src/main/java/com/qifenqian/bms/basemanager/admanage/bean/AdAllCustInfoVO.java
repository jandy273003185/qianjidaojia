package com.qifenqian.bms.basemanager.admanage.bean;

import java.io.Serializable;

public class AdAllCustInfoVO implements Serializable {
    private static final long serialVersionUID = -4751261391236595677L;
    /**
     * 客户编号
     **/
    private String custId;

    /**
     * 客户名称
     **/
    private String custName;
    private String shopId;
    private String shopName;
    private String shopNo;
    private String addr;

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

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }
}
