package com.qifenqian.bms.accounting.exception.dao.operfreeze.mapper;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.exception.dao.operfreeze.bean.OperFreeze;

/**
 * 支付订单：acct_seven_cust_freeze
 * 
 * @project sevenpay-bms-web
 * @fileName OperFreezeMapper.java
 * @author chonggan.shen
 * @date 2016年07月08日
 * @memo 
 */
@MapperScan
public interface OperFreezeMapper {
	
	/**
	 * 查询单个冻结/解冻
	 * @param orderId
	 * @return
	 */
	OperFreeze selectCustFreezeById(String orderId);
	
	
	/***
	 * 更新冻结/解冻状态
	 * @param updateRefundBillMain
	 * @return
	 */
	int updateCustFreezeStatus(OperFreeze updateFreeze);
	
}


