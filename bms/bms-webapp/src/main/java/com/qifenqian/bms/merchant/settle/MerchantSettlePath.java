package com.qifenqian.bms.merchant.settle;

/**
 * @project sevenpay-bms-web
 * @fileName MerchantSettlePath.java
 * @author huiquan.ma
 * @date 2015年10月10日
 * @memo 
 */
public class MerchantSettlePath {

	public static final String BASE = "/merchant";
	
	/** 列表*/
	public static final String LIST = "/settle/list";
	
	/**
	 * 代理商
	 */
	public static final String AGENCYLIST = "/settle/agencyList";
	
	/** 复核*/
	public static final String AUDIT = "/settle/audit";
	
	/** 复核撤销*/
	public static final String CANCEL = "/settle/cancel";
	
	/** 核销*/
	public static final String VERIFIED = "/settle/verified";
	
	/** 明细*/
	public static final String DETAIL = "/settle/detail";
	
	/** 合体 用于出现结算金额为负数情况*/
	public static final String TOGETHER ="/settle/together";
	
	/** 分离 用于出现结算金额为负数情况*/
	public static final String SEPARATE ="/settle/separate";
	
	/**
	 * 批量结算
	 */
	public static final String BATCHSETTLE = "/settle/batchSettle";
	
	/**
	 * 批量核销
	 */
	public static final String BATCHVERIFIED = "/settle/batchVerified";
	
	/**
	 * 导出
	 */
	public static final String EXPORT = "/settle/export";  
	
	/**
	 * 导出代理商结算
	 */
	public static final String AGENCYEXPORT = "/settle/agencyExport"; 
	
	public static final String SAVE_TOKEN =  "/savaToken";
}


