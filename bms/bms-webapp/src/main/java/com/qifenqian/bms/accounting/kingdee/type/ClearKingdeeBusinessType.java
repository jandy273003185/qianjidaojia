package com.qifenqian.bms.accounting.kingdee.type;

import com.qifenqian.bms.platform.common.annotation.Description;


public enum ClearKingdeeBusinessType {

  @Description("个人提现")
  PERSONAL_WITHDRAW("个人提现"), @Description("商户结算")
  MERCHANT_SETTLE("商户结算");

  private String desc;

  private ClearKingdeeBusinessType(String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }

}


