package com.qifenqian.bms.merchant.reported.bean;

import java.io.Serializable;

/**
 * @author LvFeng
 * @Description: 拉卡拉行业信息
 * @date 2020/5/21 15:25
 */
public class TdLakalaMccInfo implements Serializable {
    private static final long serialVersionUID = 5491613736172758637L;
    /**
     * 行业id
     */
    private String mccId;
    /**
     * 行业名称
     */
    private String mccName;

    public String getMccId() {
        return mccId;
    }

    public void setMccId(String mccId) {
        this.mccId = mccId;
    }

    public String getMccName() {
        return mccName;
    }

    public void setMccName(String mccName) {
        this.mccName = mccName;
    }
}
