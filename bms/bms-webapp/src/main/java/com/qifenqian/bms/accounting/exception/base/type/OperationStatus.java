package com.qifenqian.bms.accounting.exception.base.type;

import com.qifenqian.bms.platform.common.annotation.Comment;

/**
 * @project sevenpay-bms-web
 * @fileName OperationStatus.java
 * @author huiquan.ma
 * @date 2015年10月28日
 * @memo 
 */
public enum OperationStatus {

	/**
	 * 业务系统处理中
	 */
	@Comment(desc="业务系统处理中")
	OPER_DEALING,
	
	/**
	 * 核心处理中
	 */
	@Comment(desc="核心处理中")
	CORE_DEALING,
	
	/**
	 * 后台处理中
	 */
	@Comment(desc="后台处理中")
	BMS_DEALING,
	
	/**
	 * 后台处理中
	 */
	@Comment(desc="取消")
	DEAL_CANCELLED,
	/**
	 * 后台处理中
	 */
	@Comment(desc="成功")
	DEAL_SUCCESS;
}


