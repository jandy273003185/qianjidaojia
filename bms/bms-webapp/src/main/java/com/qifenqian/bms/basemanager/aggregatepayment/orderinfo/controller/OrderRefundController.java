package com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.RefundBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.RefundQueryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.service.OrderInfoService;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.service.OrderRefundService;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;


@Controller
@RequestMapping(OrderRefundPath.BASE)
public class OrderRefundController {
	
	private static Logger logger = LoggerFactory.getLogger(OrderRefundController.class);
	
	@Autowired
	private OrderRefundService orderRefundService;
	
	@Autowired
	private TdCustInfoService tdCustInfoService;
	
	@Autowired
	private OrderInfoService orderInfoService;

	/***
	 * 订单信息
	 * @param queryBean
	 * @return
	 */

	@RequestMapping(OrderRefundPath.ORDERREFUNDLIST)
	public ModelAndView allTradeBill(RefundQueryBean queryBean,HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("查询交易表");
		ModelAndView model = new ModelAndView(OrderRefundPath.BASE + OrderRefundPath.ORDERREFUNDLIST);
		String isFirst = request.getParameter("isFirst");
		if (StringUtils.isEmpty(isFirst)) {
			queryBean.setCreateTimeB(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
			queryBean.setCreateTimeE(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));

		}
		String userId  = String.valueOf(WebUtils.getUserInfo().getUserId());
		//是否有权限查看所有订单
		boolean isAllList = tdCustInfoService.isAllList(userId);
		
		List<RefundBean> list = null;
			
		if(isAllList){
			list = orderRefundService.getOrderRefundList(queryBean);
		}else{
			queryBean.setUserName(WebUtils.getUserInfo().getUserName());
			queryBean.setUserId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			list = orderInfoService.queryMyRefundList(queryBean);
		}
		model.addObject("orderRefundList", list);
		model.addObject("isFirst", "No");
		model.addObject("queryBean",queryBean);
		return model;
	}
	
	@RequestMapping(OrderRefundPath.ORDERREFUNDEXPORT)
	public void exportRechargeExcel(RefundQueryBean queryBean,HttpServletRequest request, HttpServletResponse response) {

		logger.info("导出退款信息");
		String userId  = String.valueOf(WebUtils.getUserInfo().getUserId());
		try {
			String[] headers = { "退款编号","商户退款流水号","原订单ID", "商户编号", "原订单总金额","退款金额","手续费", "中信交易号", "中信退款ID", "退款渠道","渠道返回退款错误码","渠道退款返回结果描述" ,"退款时间" ,"退款状态","退款发起时间","发起退款操作员","原订单渠道","产品名称"};
			List<RefundBean> list = null;
			//是否有权限查看所有订单
			boolean isAllList = tdCustInfoService.isAllList(userId);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			if(queryBean.getCreateTimeE() =="" && queryBean.getCreateTimeE() == null){
				queryBean.setCreateTimeE(sdf.format(cal.getTime()));
			}
			if(queryBean.getCreateTimeB() =="" && queryBean.getCreateTimeB() ==null){
				//默认开始日期为前三个月
				cal.add(Calendar.DAY_OF_YEAR, -90);
				queryBean.setCreateTimeB(sdf.format(cal.getTime()));
			}
			
			if(isAllList){
				list = orderRefundService.getOrderRefundListExport(queryBean);
			}else{
				queryBean.setUserName(WebUtils.getUserInfo().getUserName());
				queryBean.setUserId(String.valueOf(WebUtils.getUserInfo().getUserId()));
				list = orderRefundService.getMyOrderRefundListExport(queryBean);
			}
			
			String fileName = DatetimeUtils.dateSecond() + "_退款信息.xls";
			Map<String, String> fileInfo = orderRefundService.exportExcel(list, headers, fileName, "退款信息", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel退款信息成功");
		} catch (Exception e) {
			logger.error("导出excel退款信息异常", e);
		}

	}

	
}
