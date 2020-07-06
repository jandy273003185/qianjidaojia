package com.qifenqian.bms.UserStatus;

import com.qifenqian.bms.platform.common.annotation.Description;

public enum PaychannelType {
	/**
	 * 工商银行
	 */
	@Description("工商银行")
	ICBC("工商银行"),
	/**
	 * 农业银行
	 */
	@Description("农业银行")
	ABC("农业银行"),
	/**
	 * 中信-威富通
	 */
	@Description("中信-威富通")
	CNCB_SWIFT("中信-威富通"),
	/**
	 * 浦发-威富通
	 */
	@Description("浦发-前海万融")
	SPD_QHWR("浦发-前海万融");
	
	private String desc;
	private PaychannelType(String desc) {
	  this.desc = desc;
	}
  public String getDesc() {
    return desc;
  }
  public void setDesc(String desc) {
    this.desc = desc;
  }
	
	
	
}
