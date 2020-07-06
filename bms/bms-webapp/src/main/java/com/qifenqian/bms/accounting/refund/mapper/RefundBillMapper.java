package com.qifenqian.bms.accounting.refund.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.refund.bean.RefundBill;
import com.qifenqian.bms.accounting.refund.bean.RefundExcel;

@MapperScan
public interface RefundBillMapper {

	/***
	 * 退款列表
	 * 
	 * @param queryBean
	 * @return
	 */
	List<RefundBill> select(RefundBill queryBean);

	/**
	 * 退款审核通过
	 * 
	 * @param queryBean
	 * @return
	 */
	int update(RefundBill queryBean);

	/**
	 * 导出退款列表
	 * 
	 * @param queryBean
	 * @return
	 */
	List<RefundExcel> selectRefundExcel(RefundBill queryBean);

	/**
	 * 退款核销
	 * 
	 * @param refundBill
	 * @return
	 */
	int refundVerification(RefundBill refundBill);

	/**
	 * 审核不通过
	 * 
	 * @param queryBean
	 * @return
	 */
	int auditRefund(RefundBill queryBean);
	
	/**
	 * 
	 * @param instBean
	 * @return
	 */
	int insertRefundBill(RefundBill instBean);
	
	/**
	 * 根据退款原始订单号查找商户订单号
	 * @return
	 */
	String findOutOrderId(@Param("orderId")String orderId);
	

}
