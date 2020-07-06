package com.qifenqian.bms.accounting.exception.base.type;

/**
 * 处理结果
 * 
 * @project sevenpay-bms-web
 * @fileName DealResult.java
 * @author huiquan.ma
 * @date 2015年10月28日
 * @memo 
 */
public enum DealResult {
	
	/**
	 * 重启
	 */
	RESTART,
	
	/**
	 * 回退
	 */
	ROLLBACK,
	
	/**
	 * 终止
	 */
	END,
	
	/**
	 * 确认成功
	 */
	CONFIRM_SUCCESS,
	
	/**
	 * 确认失败
	 */
	CONFIRM_FAILURE,
	
	/**
	 * 撤销
	 */
	REVOKE,
	
	/**
	 * 重执行
	 */
	RE_EXECUTE;
}


