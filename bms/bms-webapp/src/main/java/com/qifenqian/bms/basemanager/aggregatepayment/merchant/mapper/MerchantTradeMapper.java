package com.qifenqian.bms.basemanager.aggregatepayment.merchant.mapper;

import java.util.List;

import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.MerchantTradeQueryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.OrderSummaryBean;
import com.qifenqian.bms.common.annotation.MapperScanPayment;

/**
 * 交易信息
 * 
 * @project sevenpay-bms-web
 * @fileName TdTradeBillMainMapper
 * @author Dayet
 * @date 2015年6月26日
 * @memo 
 */

@MapperScanPayment
public interface MerchantTradeMapper {

	public List<OrderSummaryBean> getMerchantTradeList(
			MerchantTradeQueryBean queryBean);

	public List<OrderSummaryBean> getMerchantRefundList(
			MerchantTradeQueryBean queryBean);
	
}
