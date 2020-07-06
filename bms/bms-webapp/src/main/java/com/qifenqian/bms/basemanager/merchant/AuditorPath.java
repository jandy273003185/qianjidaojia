package com.qifenqian.bms.basemanager.merchant;

public final class AuditorPath {
//  审核商户信息
	public final static String BASE= "/merchant/auditor";
	
	public final static String LIST = "/list";

	public final static String IMAGE = "/image";
	
	//public final static String GETDOORIMAGE = "/image";
	
	// 确认审核
	public final static String SUREAUDITOR="/sureAuditor";
	
	// 审核不通过
	public final static String NOPASSAUDITOR="/noPassAuditor";
	
	// 根据id查找审核商户信息
	public final static String SELECTIDAUDITORINFO="/selectIdAuditorInfo";
}
