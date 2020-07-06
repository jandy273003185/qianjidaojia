package com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.ExportOrderInfoBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoQueryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.RefundBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.RefundQueryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.mapper.OrderMapperMaster;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.mapper.OrderMapperSlave;
import com.qifenqian.bms.platform.web.page.Page;

/**
 * @author Chen Gao
 *
 * 
 */
@Repository
public class OrderInfoDAO {
	
	private static Logger logger = LoggerFactory.getLogger(OrderInfoDAO.class);
	@Autowired
	private OrderMapperSlave orderMapperSlave;
	
	@Autowired
	private OrderMapperMaster orderMapperMaster;
	@Page
	public List<OrderInfoBean> getOrderInfoList(OrderInfoQueryBean queryBean) {
		return orderMapperSlave.getOrderInfoList(queryBean);
	}
	
	public List<ExportOrderInfoBean> getOrderInfoListExport(OrderInfoQueryBean queryBean) {
		return orderMapperSlave.getOrderInfoListExport(queryBean);
				
	}
	
	public List<ExportOrderInfoBean> getMyOrderInfoListExport(OrderInfoQueryBean queryBean){
		return orderMapperSlave.getMyOrderInfoListExport(queryBean);
	}
	@Page
	public List<OrderInfoBean> getOrderExceptionList(OrderInfoQueryBean queryBean) {
		return orderMapperSlave.getOrderExceptionList(queryBean);
	}
	
	/**
	 * 查询订单详细信息
	 * @param orderId
	 * @return
	 */
	public OrderInfoBean getOrderInfoDetail(String orderId){
		return orderMapperSlave.getOrderInfoDetail(orderId);
	}
	
	public void updateOrder(OrderInfoBean infoBean){
		try {
			 orderMapperMaster.updateOrder(infoBean);
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw e;
		}
		
	}
	
	/**
	 * 查询自己权限的所属订单
	 * @param queryBean
	 * @return
	 */
	@Page
	public List<OrderInfoBean> queryMylist(OrderInfoQueryBean queryBean){
		return orderMapperSlave.queryMylist(queryBean);
	}
	
	/**
	 * 查询自己权限的所属退款订单
	 * @param queryBean
	 * @return
	 */
	@Page
	public List<RefundBean> queryMyRefundList(RefundQueryBean queryBean){
		return orderMapperSlave.queryMyRefundList(queryBean);
	}
}
