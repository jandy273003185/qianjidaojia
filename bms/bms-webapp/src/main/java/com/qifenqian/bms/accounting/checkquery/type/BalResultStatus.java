package com.qifenqian.bms.accounting.checkquery.type;

/**
 * @project sevenpay-bms-web
 * @fileName BalResultStatus.java
 * @author huiquan.ma
 * @date 2015年10月16日
 * @memo
 */
public enum BalResultStatus {
  VALID("有效"),
  INVALID("无效");

  private String desc;

  private BalResultStatus(String desc) {
    this.desc = desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }
}
