package com.qifenqian.bms.platform.type;

import com.qifenqian.bms.platform.common.annotation.Description;

public enum ProductNameState {

  /** 微信 */
  @Description("微信")
  wx("微信"),
  /** 支付宝 */
  @Description("支付宝")
  alipay("支付宝");

  private String desc;

  private ProductNameState(String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }
}
