package com.qifenqian.bms.basemanager.admanage.bean;

import java.io.Serializable;
import java.util.Date;

public class ShopAd implements Serializable {

    private static final long serialVersionUID = 3753474549860123181L;

    private String shopAdId; //门店广告ID
    private String mchId; //商户ID
    private String shopId; //门店ID
    private String adId; //广告ID
    private int sortNo;  //排序
    private String createId; //创建人
    private Date createTime; //创建时间
    private String modifyId; //修改人
    private Date modifyTime; //修改时间

    public String getShopAdId() {
        return shopAdId;
    }

    public void setShopAdId(String shopAdId) {
        this.shopAdId = shopAdId;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getAdId() {
        return adId;
    }

    public void setAdId(String adId) {
        this.adId = adId;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public String getCreateId() {
        return createId;
    }

    public void setCreateId(String createId) {
        this.createId = createId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getModifyId() {
        return modifyId;
    }

    public void setModifyId(String modifyId) {
        this.modifyId = modifyId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
