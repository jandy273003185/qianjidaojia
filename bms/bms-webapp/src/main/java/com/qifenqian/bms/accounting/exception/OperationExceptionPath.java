package com.qifenqian.bms.accounting.exception;

/**
 * 账务异常处理路径
 * 
 * @project sevenpay-bms-web
 * @fileName OperationExceptionPath.java
 * @author huiquan.ma
 * @date 2015年10月19日
 * @memo
 */
public class OperationExceptionPath {

	public static final String BASE = "/accounting/operexception";
	/** 异常列表 */
	public static final String LIST_OPERATION = "/listOperation";
	/** 执行明细 */
	public static final String DETAIL_OPERATION = "/detailOperation";
	/** 重启流程 */
	public static final String RESTART_OPERATION = "/restartOperation";
	/** 续作下一步 */
	public static final String SEQUEL_NEXT_STEP_OPERATION = "/sequelNextStepOperation";
	/** 回退流程 */
	public static final String ROLLBACK_OPERATION = "/rollbackOperation";
	/** 关闭流程 */
	public static final String CLOSURE_OPERATION = "/closureOperation";
	/** 确认失败单步 */
	public static final String CONFIRM_FAILURE_TRANS = "/confirmFailureTrans";
	/** 确认成功单步 */
	public static final String CONFIRM_SUCCESS_TRANS = "/confirmSuccessTrans";
	/** 撤销单步 */
	public static final String REVOKE_TRANS = "/revokeTrans";
	/** 重新执行单步 */
	public static final String REXECUTE_TRANS = "/rexecuteTrans";
	/** 结果查询 */
	public static final String QUERY_RESULT_TRANS = "/queryResultTrans";
	/** 执行明细-客户充值场景 */
	public static final String DETAIL_OPERATION_CUST_RECHARGE = "/detailCustRecharge";
	/** 执行明细-红包充值场景 */
	public static final String DETAIL_OPERATION_RED_PACKET_RECHARGE = "/detailRedPacketRecharge";
	/** 执行明细-余额支付场景 */
	public static final String DETAIL_OPERATION_BALANCE_PAYMENT = "/detailBalancePayment";
	/** 执行明细-余额支付场景 */
	public static final String DETAIL_OPERATION_RED_PACKET_PAYMENT = "/detailRedPacketPayment";
	/** 执行明细-卡支付场景 */
	public static final String DETAIL_OPERATION_BANK_CARD_PAYMENT = "/detailBankCardPayment";
	/** 执行明细-支付退款 */
	public static final String DETAIL_OPERATION_PAYMENT_REFUND = "/detailPaymentRefund";
	/** 执行明细-红包支付退款 */
	public static final String DETAIL_OPERATION_RED_PACKET_PAYMENT_REFUND = "/detailRedPacketPaymentRefund";
	/** 执行明细-用户提现 */
	public static final String DETAIL_OPERATION_CUST_WITHDRAW_APPLY = "/detailCustWithdrawApply";
	/** 商户结算申请 **/
	public static final String DETAIL_OPERATION_BUSS_SETTLE_APPLY = "/detailBussSettleApply";
	/** 商户结算 **/
	public static final String DETAIL_OPERATION_BUSS_SETTLE = "/detailBussSettle";
	/** 提现/提现撤销 **/
	public static final String DETAIL_OPERATION_CUST_WITHDRAW = "/detailCustWithdraw";
	/** 客户转账 **/
	public static final String DETAIL_CUST_TRANSFER = "/detailCustTransfer";
	/** 客户转账撤销 **/
	public static final String DETAIL_CUST_TRANSFER_REVOKE = "/detailCustTransferRevoke";
	/** 客户冻结/解冻 **/
	public static final String DETAIL_CUST_FREEZE = "/detailCustFreeze";
	
	/** 交易撤销 **/
	public static final String DETAIL_OPERATION_PAYMENT_REVOKE = "/detailPaymentRevoke";
	/** 执行明细-充值撤销 */
	public static final String DETAIL_DETAIL_RECHARGE_REVOKE = "/detailRechargeRevoke";
	
	/** 查看操作日志 */
	public static final String QUERY_OPERATION_RECORD = "/queryOperationRecord";
	/** 导出异常列表 */
	public static final String EXPORTEXCEL = "/exportExcel";
	/** 金蝶交易列表 */
	public static final String QUERY_KINGDEE_ENTRY_LIST = "/queryKingdeeEntryList";

}
