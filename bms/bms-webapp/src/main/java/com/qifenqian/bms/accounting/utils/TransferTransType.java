package com.qifenqian.bms.accounting.utils;

import com.qifenqian.bms.platform.common.annotation.Comment;

public enum TransferTransType {
	/**
	 * 交易类型： ONL_TO_SEV--网银入账七分钱； CAD_TO_SEV--快捷支付至七分钱； SEV_TO_CAD--七分钱至银行卡；
	 * SEV_TO_SEV--七分钱至七分钱； SEV_TO_QFB--七分钱至七分宝； QFB_TO_SEV--七分宝至七分钱；
	 */
	/**
	 * 网银入账七分钱
	 */
	@Comment(code = "ONL_TO_SEV", desc = "网银入账七分钱")
	ONL_TO_SEV("ONL_TO_SEV", "网银入账七分钱"),
	/**
	 * 快捷支付至七分钱
	 */
	@Comment(code = "CAD_TO_SEV", desc = "快捷支付至七分钱")
	CAD_TO_SEV( "CAD_TO_SEV", "快捷支付至七分钱"),

	/**
	 * 七分钱至银行卡
	 */
	@Comment(code = "SEV_TO_CAD", desc = "七分钱至银行卡")
	SEV_TO_CAD("SEV_TO_CAD", "七分钱至银行卡"),
	/**
	 * 七分钱至七分钱
	 */
	@Comment(code = "SEV_TO_SEV", desc = "七分钱至七分钱")
	SEV_TO_SEV("SEV_TO_SEV",  "七分钱至七分钱"),

	/**
	 * 七分钱至七分宝
	 */
	@Comment(code = "SEV_TO_QFB", desc = "七分钱至七分宝")
	SEV_TO_QFB("SEV_TO_QFB",  "七分钱至七分宝"),
	/**
	 * 七分宝至七分钱
	 */
	@Comment(code = "QFB_TO_SEV", desc = "七分宝至七分钱")
	QFB_TO_SEV("QFB_TO_SEV","七分宝至七分钱"),
	/**
	 * 冻结
	 */
	@Comment(code = "FREEZE", desc = "冻结")
	FREEZE("FREEZE", "冻结"),
	/**
	 * 七分钱到手续费cgl
	 */
	@Comment(code = "CHARGE", desc = "七分钱到手续费cgl")
	CHARGE( "CHARGE",  "七分钱到手续费cgl"),
	/**
	 * 七分钱至七分钱担保
	 */
	@Comment(code = "SEV_TO_SEV1", desc = "七分钱至七分钱担保")
	SEV_TO_SEV1("SEV_TO_SEV1",  "七分钱至七分钱担保"),
	/**
	 * 七分钱担保至七分钱
	 */
	@Comment(code = "SEV1_TO_SEV", desc = "七分钱担保至七分钱")
	SEV1_TO_SEV( "SEV1_TO_SEV", "七分钱担保至七分钱"),
	/**
	 * 七分钱至七分钱费用
	 */
	@Comment(code = "SEV_TO_SEV2", desc = "七分钱至七分钱费用")
	SEV_TO_SEV2("SEV_TO_SEV2",  "七分钱至七分钱费用");

	private String code;
	private String desc;
	private TransferTransType(String code, String desc) {
	  this.code = code;
	  this.desc = desc;
	}
  public String getCode() {
    return code;
  }
  
  public String getDesc() {
    return desc;
  }
 
	
}
