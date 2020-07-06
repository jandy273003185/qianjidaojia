package com.qifenqian.bms.accounting.exception.dao.operrefund.mapper;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.exception.dao.operrefund.bean.OperRefund;
import com.qifenqian.bms.accounting.refund.bean.RefundBill;

/**
 * 支付订单：td_trade_bill_main
 * 
 * @project sevenpay-bms-web
 * @fileName OperRefundMapper.java
 * @author huiquan.ma
 * @date 2015年10月20日
 * @memo 
 */
@MapperScan
public interface OperRefundMapper {
	/**
	 * 查询单个
	 * @param orderId
	 * @return
	 */
	OperRefund selectRefundBillById(String orderId);
	
	/***
	 * 查询退款订单
	 * @param id
	 * @return
	 */
	RefundBill selectOrderIdById(String id);
	
	
	/***
	 * 更新退款状态
	 * @param updateRefundBillMain
	 * @return
	 */
	int updateRefundOrderState(RefundBill updateRefundBillMain);

	int updateTransRecordByOperRefund(RefundBill updateCardRefundBillMain);
	
}


