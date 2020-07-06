package com.qifenqian.bms.platform.type;

import com.qifenqian.bms.platform.common.annotation.Description;

public enum ProductCodeState {

  /**
   * H5支付
   */
  @Description("h5.gateway.pay")
  H5支付("h5.gateway.pay"),

  /**
   * APP支付
   */
  @Description("mobile.plugin.pay")
  APP支付("mobile.plugin.pay"),

  /**
   * 扫码支付
   */
  @Description("onecode.coll.pay")
  扫码支付("onecode.coll.pay"),

  /**
   * 网关支付
   */
  @Description("pc.gateway.pay")
  网关支付("pc.gateway.pay"),

  /**
   * 原生H5支付
   */
  @Description("h5_t.gateway.pay")
  原生H5支付("h5_t.gateway.pay");

  private String desc;

  private ProductCodeState(String desc) {
    this.desc = desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }
}
