package com.qifenqian.bms.accounting.kingdee.type;

import com.qifenqian.bms.platform.common.annotation.Description;

public enum ClearKingdeeBusinessBankStatusType {

  @Description("空")
  A("空"),

  @Description("银行处理中")
  B("银行处理中"),

  @Description("银行交易成功")
  C("银行交易成功"),

  @Description("银行交易失败")
  D("银行交易失败"),

  @Description("银行交易未确认")
  E("银行交易未确认");

  private String desc;

  private ClearKingdeeBusinessBankStatusType(String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }



}
