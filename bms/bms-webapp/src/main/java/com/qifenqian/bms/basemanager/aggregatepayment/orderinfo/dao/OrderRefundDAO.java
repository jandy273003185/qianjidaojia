package com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
public class OrderRefundDAO {
	private static Logger loger = LoggerFactory.getLogger(OrderRefundDAO.class);
	@Autowired
	private OrderMapperSlave orderMapperSlave;
	@Autowired
	private OrderMapperMaster orderMapperMaster;
	@Page
	public List<RefundBean> getOrderRefundList(RefundQueryBean queryBean) {
		return orderMapperSlave.getOrderRefundList(queryBean);
	}
	
	public List<RefundBean> getOrderRefundListExport(RefundQueryBean queryBean) {
		return orderMapperSlave.getOrderRefundListExport(queryBean);
				
	}
	
	/**
	 * 查询退款详细信息
	 * @param refundId
	 * @return
	 */
	public RefundBean getRefundDetail(String refundId){
		return orderMapperSlave.getRefundDetail(refundId);
	}
	/**
	 * 更改退款信息
	 * @param refundBean
	 * @return
	 */
	public void updateRefundRecode(RefundBean refundBean){
		try {
			orderMapperMaster.updateRefundRecode(refundBean);
		} catch (Exception e) {
			loger.error(e.getMessage());
			throw e;
		}
		
	}
	
	/**
	 * 导出自己权限的退款订单
	 * @param queryBean
	 * @return
	 */
	public List<RefundBean> getMyOrderRefundListExport(RefundQueryBean queryBean){
		return orderMapperSlave.getMyOrderRefundListExport(queryBean);
	}
}
