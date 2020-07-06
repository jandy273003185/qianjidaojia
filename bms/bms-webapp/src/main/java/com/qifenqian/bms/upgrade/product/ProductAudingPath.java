package com.qifenqian.bms.upgrade.product;
/**
 * 
 * @author 产品审核路径
 * 2018年3月26日 上午11:30:44
 * 
 */
public final class ProductAudingPath {
	/**产品审核路径*/
	public final static String BASE = "/upgrade/productAudit";
	/** 产品审核列表*/             
	public final static String PRODUCTAUDITLIST ="/productAuditList";
	/** 门头照路径*/             
	public final static String SELDOORSCAN = "/selDoorScan";
	/** 门头照图片*/             
	public final static String IMAGE = "/image";
	/**产品审核列表导出报表*/
	public final static String  EXPORTMERCHANPRODUCT="/exportMerchantProduct";
	
	/**审核不通过*/
	public final static String  AUDITNOTPASS="/auditNotPass";
	/**审核通过*/
	public final static String  AUDITPASS="/auditPass";
	
}
