package com.qifenqian.bms.basemanager.merchant.auding.AgencyCtroller;
/**
 * 
 * @author 微商户审核路径
 * 2017年6月23日 上午11:30:44
 * 
 */
public final class WechatAudingPath {
	/**代理商审核路径*/
	public final static String BASE = "/tinymerchant/regist";
	/** 微商户审核列表*/             
	public final static String WECHATAUDITLIST ="/wechatAuditList";
	/** 微商户审核*/             
	public final static String WECHATEDIT ="/wechatEdit";
	/** 微商户下载图片*/             
	public final static String IMAGE = "/image";
	/** 微商审核不通过*/
	public final static String SECONDNOTPASS="/secondNotPass";
	/** 微商审核通过*/
	public final static String PASS="/secondPass";
	/**微商审核列表导出报表*/
	public final static String  TINYMERCHANTEXPORTMERCHAN="/ExportMerchantinfo";
	/**显示已经审核过的微商户*/
	public final static String  SHOW="/show";
	/**获取历史记录*/
	public final static String GETHISTORY="/getHistory";

}
