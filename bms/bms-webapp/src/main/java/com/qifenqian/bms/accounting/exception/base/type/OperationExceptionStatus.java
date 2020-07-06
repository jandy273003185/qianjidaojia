package com.qifenqian.bms.accounting.exception.base.type;

/**
 * 异常处理状态
 * 
 * @project sevenpay-bms-web
 * @fileName OperationExceptionStatus.java
 * @author huiquan.ma
 * @date 2015年10月28日
 * @memo 
 */
public enum OperationExceptionStatus {

	/**
	 * 初始化
	 */
	INIT,
	
	/**
	 * 处理中
	 */
	DEAL_ING,
	
	/**
	 * 处理结束
	 */
	DEAL_OVER,
	
	/**
	 * 无效
	 */
	DISABLE;
}


