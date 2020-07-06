package com.qifenqian.bms.v2.biz.financial.settlement.bean.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/5/9 14:23
 */
public class BatchSettleAO implements Serializable {
    private List<MerchantSettleAO> merchantSettleAOS;

    public List<MerchantSettleAO> getMerchantSettleAOS() {
        return merchantSettleAOS;
    }

    public void setMerchantSettleAOS(List<MerchantSettleAO> merchantSettleAOS) {
        this.merchantSettleAOS = merchantSettleAOS;
    }
}
