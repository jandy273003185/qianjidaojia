package com.qifenqian.bms.platform.type;

import com.qifenqian.bms.platform.common.annotation.Description;

public enum BusiTypeState {

  /**
   * H5
   */
  @Description("H5")
  H5("H5"),

  /**
   * PC
   */
  @Description("PC支付")
  PC("PC支付"),

  /**
   * APP支付
   */
  @Description("APP支付")
  APP("APP支付"),

  /**
   * 小额支付
   */
  @Description("小额支付")
  MICROPAY("小额支付");

  private String desc;

  private BusiTypeState(String desc) {
    this.desc = desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }
}
