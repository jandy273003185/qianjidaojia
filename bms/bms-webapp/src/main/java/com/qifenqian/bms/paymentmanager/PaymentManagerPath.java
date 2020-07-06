package com.qifenqian.bms.paymentmanager;

/**
 * 代付管理路径
 * 
 * @project sevenpay-bms-web
 * @class PaymentManagerPath.class
 * @author chunhui.zeng
 * @date 2017年9月23日
 * @memo
 *
 */
public class PaymentManagerPath {

	/** 代付管理页面 */
	public final static String BASE = "/paymentmanager";
	/** 代付 */
	public final static String PAYMENT = "/payment";
	/** 批量代付 */
	public final static String PAYMENTLIST = "/paymentList";
	/** 文件上传 */
	public final static String FILEUPLOAD = "/fileUpload";
	/** 批量数据提交 */
	public final static String BATPAYMENTDATA = "/batpaymentdata";
	/** 数据修改 */
	public final static String UPDATE = "/update";

	/** 查询批量数据 */
	public final static String SELECTLIST = "/selectList";
	
	/** 查询批量数据状态 */
	public final static String STATUS = "/status";
	

	
	/******************************************代付******************************************************/
	/**开通审核代付**/
	public final static String AUDITTOPAY = "/openAudit/openToPayList";
	/**查询商户信息**/
	public final static String SELCUSTINFO = "/openAudit/selCustInfo";
	/**查询代付商户图片**/
	public final static String GETIMG="/openAudit/showImage";
	/**代付账户审核通过**/
	public final static String AUDITPASS="/openAudit/AuditPass";
	/**代付账户审核不通过**/
	public final static String AUDITNOTPASS="/openAudit/AuditNotPass";
	/**查询代付账户审核历史记录**/
	public final static String GETOPENHISTORY="/openAudit/getOpenHistory";
	
	
	/******************************商户代付审核**********************************/
	/**查询单笔代付记录明细*/
	public final static String SELSINGLETOPAYINFO = "/audit/selSingleTopayInfo";
	/**查询批量代付记录明细*/
	public final static String SELBATCHTOPAYINFO = "/audit/selBatchTopayInfo";
	
	
	
	/**单笔代付清洁算审核通过*/
	public final static String SINGLEAUDITFRISTPASS="/audit/singleAuditFristPass";
	
	/**单笔代付清洁算审核不通过*/
	public final static String SINGLEAUDITNOTPASS = "/audit/singleAuditNotPass";
	
	/**单笔代付财务审核通过*/
	public final static String SINGLEPAYMENTPASS="/audit/singleAuditSecodePass";
	
	/**核销**/
	public final static String VERIFICATIONPASS="/audit/verificationPass";
	
	/**撤销**/
	public final static String UNDOTOPAY="/audit/undoTopay";
	
	/** 导出 报表*/
	public final static String PROEXPORTMERCHANTINFO ="/audit/proExportMerchantInfo";
	


	/**代付账户记录 */
	public final static String ACCTTOPAYBUSS = "/accttopaybuss/list";
	
	/**修改代付账户 */
	public final static String EDIT = "/accttopaybuss/edit";
	/**修改代付账户 */
	public final static String AUDITPAYMENT = "/audit/auditPayment";
	
	/**批量代付审核*/
	public final static String BATCHAUDIT = "/audit/batchAudit";
	
	
	/**批量代付审核不通过*/
	public final static String AUDITFRISTNOTPASS = "/audit/auditFristNotPass";
	

	/**批量代付审核通过*/
	public final static String AUDITFRISTPASS="/audit/auditFristPass";

	/**查询代付账户审核记录*/
	public final static String GETHISTORY="/audit/getHistory";
	
	/**财务批量代付审核通过*/
	public final static String BATPAYMENTPASS="/audit/auditSecodePass";
	
	public final static String PAYMENTREPORTlIST="/settle/paymentReportList";
	
	public final static String PAYMENTREPORTINFOS="/settle/paymentReportInfos";
	
	public final static String GETSINGLEREPORT="/settle/getSingleReport";
	
	public final static String GETREPORTEXPORT="/settle/getReportExport";
	
	public final static String GETPAYMENTQUERY="/recode/getPaymentQueryList";
	
	/**查询单笔代付记录明细*/
	public final static String GETSINGLETOPAYINFO = "/recode/getSinle";
	
	public final static String GETREPORTEXPORTOFPAYMENT="/recode/getPaymentQueryListExport";
	
	public final static String GETPAYMENTBALANCE="/accttopaybuss/getPaymentBalance";
	
	public final static String GETPAYMENTSTATUS="/accttopaybuss/getPaymentStatus";
	
	public final static String UPDATEPAYMENTSTATUS="/accttopaybuss/updatePaymentStatus";
	
	public final static String AUDITPAYMENTTINFOS="/audit/auditPaymentInfos";
	
	public final static String AUDITRECHARGEPAYMENT="/recharge/auditRecharge";
	/*代付充值审核不通过*/
	public final static String AUDITRECHARGENOTPASS="/recharge/auditRechargeNotPass";
	/*代付充值审核通过*/
	public final static String AUDITRECHARGEPASS="/recharge/auditRechargePass";
	
	/*代付充值审核财务通过*/
	public final static String AUDITRECHARGESECOUNDPASS="/recharge/FinanceAuditPass";
	
	public final static String SELRECHARGEINFO="/recharge/selRechargeInfo";
	//代付充值撤销
	public final static String PAYMENTRECHARGEREVOKE="/recharge/Revoke";
	//代付充值充值审核记录
	public final static String GETRECHRAGEHISTORY="/recharge/getRechargeHistory";
	
	public final static String GETPICTURE="/recharge/getPicture";
	//批量代付撤销
	public final static String PAYMENTBATCHREVOKE="/audit/batchRevoke";
	
	
	public final static String SAVE_TOKEN_TOPAY="/savaToken";
	
	public final static String PAYMENTRECHARGEAUDITPASS="/recharge/paymentRechargeAuditPass";
	
	public final static String NEWAUDITRECHARGEPAYMENT="/recharge/newAuditRecharge";
	
	public final static String MERCHANTCHANNEL="/merchantchannel/list";
}
