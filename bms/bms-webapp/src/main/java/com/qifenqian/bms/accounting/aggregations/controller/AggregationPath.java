package com.qifenqian.bms.accounting.aggregations.controller;

public class AggregationPath {
	
	public final static String BASE = "/accounting/aggregations";		
	
	/* 对账结果统计*/
	public final static String LIST = "/list";
	
	/* 中信存疑报表*/
	public final static String IMPEACH = "/impeach";
	
	/* 差错报表*/
	public final static String SLIPREPORT ="/slipReport";
	
	/* 一致报表*/
	public final static String FITREPORT ="/fitReport";
	
	/* 一致报表导出*/
	public final static String EXPORTFIT ="/exportfit";
	
	/* 差错报表导出*/
	public final static String EXPORTSLIP ="/exportslip"; 
	
	/* 中信存疑报表导出*/
	public final static String EXPORTIMPEACH ="/exportimpeach";
	
	/* 统计结果导出*/
	public final static String EXPORT = "/export";
	
	/* 七分钱存疑报表*/
	public final static String JHIMPEACH = "/jhImpeach";
	
	/* 七分钱存疑报表导出*/
	public final static String EXPORTJHIMPEACH = "/exportJhImpeach";
	
	/* 差错备注提交*/
	public final static String COMMIT = "/commit";
	
	/* 差错备注删除*/
	public final static String DELETE = "/delete";
	
}
