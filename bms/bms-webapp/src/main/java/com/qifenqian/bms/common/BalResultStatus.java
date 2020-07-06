package com.qifenqian.bms.common;

import java.lang.reflect.Field;

import com.qifenqian.bms.platform.common.annotation.Description;

public enum BalResultStatus {
  @Description("有效")
  VALID,
  @Description("无效")
  INVALID;

  /** 获取描述性内容 */
  public String getDesc() {
    Class<?> clazz = this.getClass();
    Field filed = null;
    try {
      filed = clazz.getField(this.name());
    } catch (Exception e) {
    }
    Description description = filed.getAnnotation(Description.class);
    if (description == null) {
      return null;
    } else {
      return description.value();
    }
  }
}
