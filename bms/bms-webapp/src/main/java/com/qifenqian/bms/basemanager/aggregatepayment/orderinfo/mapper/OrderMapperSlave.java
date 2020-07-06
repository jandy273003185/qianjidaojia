package com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.ExportOrderInfoBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoQueryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.RefundBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.RefundQueryBean;
import com.qifenqian.bms.common.annotation.MapperScanPayment;


@MapperScanPayment
public interface OrderMapperSlave {
	/** 查询所有交易**/
	
	public List<OrderInfoBean> getOrderInfoList(OrderInfoQueryBean queryBean);

	public List<ExportOrderInfoBean> getOrderInfoListExport(OrderInfoQueryBean queryBean);

	public List<RefundBean> getOrderRefundList(RefundQueryBean queryBean);

	public List<RefundBean> getOrderRefundListExport(RefundQueryBean queryBean);
	
	//查询交易成功非七分钱支付，写核心失败的交易数据
	public List<OrderInfoBean> getOrderExceptionList(OrderInfoQueryBean queryBean);
	
	public OrderInfoBean getOrderInfoDetail(@Param("orderId")String orderId);
	
	public RefundBean getRefundDetail(@Param("refundId")String refundId);
	
	public List<OrderInfoBean> queryMylist(OrderInfoQueryBean queryBean);
	
	public List<RefundBean> queryMyRefundList(RefundQueryBean queryBean);
	
	public List<ExportOrderInfoBean>  getMyOrderInfoListExport(OrderInfoQueryBean queryBean);
	
	public List<RefundBean> getMyOrderRefundListExport(RefundQueryBean queryBean);
}
