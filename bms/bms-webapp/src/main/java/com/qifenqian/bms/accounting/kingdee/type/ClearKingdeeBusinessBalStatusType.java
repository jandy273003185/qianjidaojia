package com.qifenqian.bms.accounting.kingdee.type;

import com.qifenqian.bms.platform.common.annotation.Description;



/**
 * 金蝶对账状态
 * 
 * @author wulingtong
 *
 */
public enum ClearKingdeeBusinessBalStatusType {

  @Description("初始")
  INIT("初始");

  private String desc;

  private ClearKingdeeBusinessBalStatusType(String desc) {
    this.desc = desc;
  }

  public String getDesc() {
    return desc;
  }


}
