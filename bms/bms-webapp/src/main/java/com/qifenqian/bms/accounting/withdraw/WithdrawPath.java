package com.qifenqian.bms.accounting.withdraw;

public class WithdrawPath {
	
	/**
	 * 提现模块
	 */
	public final static String BASE = "/accounting/withdraw";
	
	/** [DATA]进入提现页面*/
	public final static String WITHDRAWLIST = "/withdrawlist";
	
	/** [ajax]提现审核通过*/
	public final static String WITHDRAWAUDITPASS = "/withdrawAuditPass";
	
	/** [ajax]提现审核不通过*/
	public final static String WITHDRAWAUDITNOPASS = "/withdrawAuditNoPass";
	
	/** 提现核销*/
	public final static String WITHDRAWVERIFICATION = "/withdrawVerification";
	
	public final static String WITHDRAW_REFUND = "/withdrawRefund";
	
	public final static String WITHDRAWEXPORT = "/withdrawExport";
}
