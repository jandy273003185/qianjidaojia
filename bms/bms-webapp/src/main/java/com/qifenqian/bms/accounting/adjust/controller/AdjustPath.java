package com.qifenqian.bms.accounting.adjust.controller;

/**
 * 用户管理相关路径
 * 
 * @project sevenpay-bms-web
 * @fileName UserPath.java
 * @author huiquan.ma
 * @date 2015年5月11日
 * @memo 
 */
public final class AdjustPath {

	/** 调账模块*/
	public final static String BASE = "/accounting/adjust";
	
	/** [DATA]进入调账经办页面*/
	public final static String HANDLE = "/handle";
	
	/** [DATA]调账经办*/
	public final static String HANDLE_SUBMIT = "/handlesubmit";
	
	/** [DATA]单边账务进入调账经办页面*/
	public final static String HANDLE_SINGLE = "/handleSingle";
	
	/** [DATA]单边账务调账经办*/
	public final static String HANDLE_SINGLE_SUBMIT = "/handleSingleSubmit";
	
	/** [DATA]经办人员查询待处理调账记录*/
	public final static String EDITLIST = "/editlist";
	
	/** [AJAX]进入调账修改页面*/
	public final static String PREEDIT = "/preedit";
	
	/** [DATA]调账修改提交, 提示修改成功后, 跳转会待处理列表*/
	public final static String EDIT = "/edit";
	public final static String EDIT_SINGLE = "/editSingle";
	
	/** [DATA]删除调账记录, 提示删除成功后, 刷新当前待处理列表*/
	public final static String DELETE = "/delete";
	
	/** [AJAX]查询调账记录明细信息*/
	public final static String DETAIL = "/detail";
	public final static String DETAIL_SINGLE = "/detailSingle";
	
	/** [AJAX]查询调账记录明细信息*/
	public final static String DETAIL_BY_PID = "/detailByPid";
	
	/** [DATA]进入调账复核页面*/
	public final static String CHECKLIST = "/checklist";
	
	/** [DATA]进入调账复核页面明细页面*/
	public final static String PRECHECK = "/precheck";
	
	/** [DATA]调账复核提交*/
	public final static String CHECK = "/check";
	
	/** [DATA]调账复核提交*/
	public final static String CHECK_SINGLE = "/checkSingle";
	
	/** [DATA]进入总经理审批列表页面*/
	public final static String APPROVELIST = "/approvelist";
	
	/** [DATA]进入总经理审批列表页面*/
	public final static String PREAPPROVE = "/preapprove";
	
	/** [DATA]调账审批提交, 提示复核成功后, 刷新当前待处理列表*/
	public final static String APPROVE = "/approve";
	public final static String APPROVE_SINGLE = "/approveSingle";
	
	/** [AJAX]查询账户信息*/
	public final static String QUERY_ACCT_INFO = "/queryacctinfo";
	
	/** [DATA]调账历史查询*/
	public final static String HISTORY_LIST = "/historyList";
	
	/** [DATA]单边调账历史查询*/
	public final static String SINGLE_HISTORY_LIST = "/singleHistoryList";
	
	/** [DATA]调账历史查询*/
	public final static String HISTORY_LIST_EXCEL = "/historyListExcel";
	
	/** [DATA]单边调账历史查询*/
	public final static String SINGLE_HISTORY_LIST_EXCEL = "/singleHistoryListExcel";
}


