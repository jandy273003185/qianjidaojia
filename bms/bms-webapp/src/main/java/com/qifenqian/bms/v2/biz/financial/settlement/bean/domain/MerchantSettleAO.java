package com.qifenqian.bms.v2.biz.financial.settlement.bean.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/5/9 14:23
 */
public class MerchantSettleAO implements Serializable {

    private String togetherId;
    private List<String> togetherIds;
    private String settleId;
    private String merchantId;


    public String getTogetherId() {
        return togetherId;
    }

    public void setTogetherId(String togetherId) {
        this.togetherId = togetherId;
    }

    public List<String> getTogetherIds() {
        return togetherIds;
    }

    public void setTogetherIds(List<String> togetherIds) {
        this.togetherIds = togetherIds;
    }

    public String getSettleId() {
        return settleId;
    }

    public void setSettleId(String settleId) {
        this.settleId = settleId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }
}
