package com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.mapper;

import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.RefundBean;
import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;


@MapperScanCombinedmaster
public interface OrderMapperMaster {
	
	public void updateOrder(OrderInfoBean infoBean);
	
	public int updateRefundRecode(RefundBean refundBean);
	
	public void updateOrderInfo(OrderInfoBean order);
}
