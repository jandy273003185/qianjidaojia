package com.qifenqian.bms.basemanager.admanage.bean;

import java.io.Serializable;
import java.util.List;


public class ShopAdDO implements Serializable {

    private static final long serialVersionUID = -1894594625694313673L;

    private List<AdDO> adDOList;
    private List<MchShopDO> mchShopDOList;

    public List<AdDO> getAdDOList() {
        return adDOList;
    }

    public void setAdDOList(List<AdDO> adDOList) {
        this.adDOList = adDOList;
    }

    public List<MchShopDO> getMchShopDOList() {
        return mchShopDOList;
    }

    public void setMchShopDOList(List<MchShopDO> mchShopDOList) {
        this.mchShopDOList = mchShopDOList;
    }
}
