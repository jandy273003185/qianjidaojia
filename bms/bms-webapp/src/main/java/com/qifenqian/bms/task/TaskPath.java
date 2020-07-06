package com.qifenqian.bms.task;

public class TaskPath {

	public static final String BASE = "/servlet";
	
	//会计日切换任务
	public static final String ACCOUNTINGWORKDATESERVLET = "/accountingWorkDateServlet";
	
	//轮询核心余额支付
	public static final String BALANCEPAYMENTSERVLET = "/balancePaymentServlet";
	
	//轮询核心卡支付
	public static final String BANKCARDPAYMENTSERVLET = "/bankCardServlet";
	
	//轮询核心开户
	public static final String POLLCREATEACCTSEVENSERVLET = "/createAcctSevenServlet";
		
	//轮询核心充值表
	public static final String SEVENRECHARGESERVLET = "/sevenRechargeServlet";
		
	//轮询交广科技充值消费撤销结果
	public static final String JGKJREBACKSERVLET = "/jgkjRebackServlet";
		
	//轮询交广科技充值消费结果
	public static final String JGKJTRADESERVLET = "/jgkjTradeServlet";
	
	//轮询业务系统充值结果
	public static final String SEVENAPPCHARGESERVLET = "/sevenAppChargeServlet";
	
	//轮询银联结果                                                                                                       
	public static final String QUERYUNIONPAYSERVLET = "/queryUnionpayServlet";
	
	//轮询业务系统支付结果
	public static final String SEVENAPPPAYMENTSERVLET = "/sevenAppPaymentServlet";
	
	// 异步通知商户结果
	public static final String MERCHANTSERVLET = "/merchantServlet";
	
	//提现核销
	public static final String VERIFICATIONSERVLET = "/verificationServlet";
	
	//APP注册未明处理
	public static final String SEVENAPPREGISTERSERVLET = "/sevenAppRegisterServlet";
	
	//生成日历
	public static final String CALENDARSERVLET = "/calendarServlet";
	//超时未付款的订单处理
	public static final String ORDERTIMEOUTSERVLET ="/orderTimeout";
	
	//红包过期自动退款
	public static final String REDPACKET_EXPRIED_REFUND ="/redPacketExpriedRefundServlet";
}
