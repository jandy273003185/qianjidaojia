package com.qifenqian.bms.basemanager.merchant.auding.bean;
/**
 * 
 * @author 代理商审核路径
 * 2017年6月23日 上午11:30:44
 * 
 */
public final class AgencyAudingPath {
	//代理商审核礼拜
	public final static String BASE = "/basemanager/agency";
	
	public final static String LIST = "/agentAuditList";
	
	public final static String AGENCY = "/edit";
	
	//代理商审核不通过
	public final static String SECONDNOTPASS="/secondNotPass";
	//代理商审核通过
	public final static String PASS="/secondPass";
	//代理商审核列表导出报表
	public final static String AGENTEXPORTMERCHANTINFO="/agentExportMerchantinfo";
	
	public final static String GETIMG="/showImage";
	
	//查看审核历史记录
	public final static String GETHISTORY="/getHistory";
	
	//查询已经审核完毕的代理商对象
    public final static String SHOW = "/show";
    
	
	
}
