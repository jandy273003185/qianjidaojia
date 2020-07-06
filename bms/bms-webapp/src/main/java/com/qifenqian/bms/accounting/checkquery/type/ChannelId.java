package com.qifenqian.bms.accounting.checkquery.type;

import com.qifenqian.bms.platform.common.annotation.Description;

/**
 * @project sevenpay-bms-web
 * @fileName ChannelId.java
 * @author huiquan.ma
 * @date 2015年10月16日
 * @memo 
 */
public enum ChannelId {
	
	@Description("中信")
	CNCB("中信"),
	@Description("银联")
	UNIONPAY("银联"),
	@Description("交广科技")
	JGKJ("交广科技");
	
	private String desc;
	private ChannelId(String desc) {
	  this.desc = desc;
	}
  public String getDesc() {
    return desc;
  }
  		

}


