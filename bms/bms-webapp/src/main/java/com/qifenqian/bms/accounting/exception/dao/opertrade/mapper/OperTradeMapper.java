package com.qifenqian.bms.accounting.exception.dao.opertrade.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.exception.dao.opertrade.bean.OperTrade;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoBean;
import com.qifenqian.bms.basemanager.trade.bean.TdTradeBillMainVO;

/**
 * 支付订单：td_trade_bill_main
 * 
 * @project sevenpay-bms-web
 * @fileName OperTradeMapper.java
 * @author huiquan.ma
 * @date 2015年10月20日
 * @memo 
 */
@MapperScan
public interface OperTradeMapper {
	/**
	 * 查询单个
	 * @param orderId
	 * @return
	 */
	OperTrade selectTradeBillById(String orderId);
	
	/**
	 * 更新交易状态
	 * @param orderId
	 * @return
	 */
	int updateBillMainOrderState(TdTradeBillMainVO updateBean);
	
	
	/**
	 * 待支付隔日自动处理成“取消”状态
	 * @return
	 */
	int updateBillMainTimeOut(@Param("timeOutMinute")int timeOutMinute);

	int  updateTransRecordByOperTrade(TdTradeBillMainVO updateTradeBean);
	
	
	/***
	 * 通过td_trade_bill_main的ORDER_ID 查询 td_merchant_in 表是否有该记录
	 */
	
	OperTrade selectTdmerchantInByInOrderId(String orderId);
	
	/***
	 * 通过 td_merchant_in 的OUT_ORDER_ID 查询 td_order 表是否有该记录
	 */
	
	OrderInfoBean selectTdOrderByOutOrderId(String orderId);
	

	
	/**
	 * 更新td_merchant_in交易状态
	 * @param orderId
	 * @return
	 */
	int updateMerchantInOrderState(TdTradeBillMainVO updateBean);
	
	
	
	
}


