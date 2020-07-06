package com.qifenqian.bms.task;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qifenqian.bms.platform.common.utils.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qifenqian.bms.accounting.exception.dao.opercharge.mapper.OperChargeMapper;
import com.qifenqian.bms.accounting.exception.dao.opertrade.mapper.OperTradeMapper;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.platform.third.scheduler.ISchedulerServlet;

@WebServlet(name = "OrderTimeOutServlet", urlPatterns = { TaskPath.BASE
		+ TaskPath.ORDERTIMEOUTSERVLET })
public class OrderTimeOutServlet extends ISchedulerServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5648621385029292111L;
	private Logger logger = LoggerFactory.getLogger(OrderTimeOutServlet.class);
	
	private OperChargeMapper operChargeMapper = SpringUtils.getBean(OperChargeMapper.class);
	private OperTradeMapper operTradeMapper =SpringUtils.getBean(OperTradeMapper.class);
	private CommonService commonService = SpringUtils.getBean(CommonService.class);
	@Override
	public String Subject(HttpServletRequest request, HttpServletResponse response) {
		try {
				String timeOutMinute = commonService.getIPlugin().findDictByPath("timeOutMinute");
				
				operTradeMapper.updateBillMainTimeOut(Integer.parseInt(timeOutMinute));
				operChargeMapper.updateChargeBillTimeOut(Integer.parseInt(timeOutMinute));		
				request.setAttribute(EXECUTE_DESC, "OrderTimeOutServlet.java Subject() SUCCESS");
		} catch (Exception e) {
			logger.error("处理超时未支付的订单结果异常:", e);
			request.setAttribute(EXECUTE_DESC, "OrderTimeOutServlet.java Subject() ERROR：" + e);
			return EXECUTE_FAILURE;
		}
		return EXECUTE_SUCCESS;
	}

}
