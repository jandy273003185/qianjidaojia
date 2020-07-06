package com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayChannelBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayProdBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.service.PayChannelService;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.service.PayProdService;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.DealOperation;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.ExportOrderInfoBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoQueryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.service.OrderInfoService;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.service.OrderRefundService;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Controller
@RequestMapping(OrderInfoPath.BASE)
public class OrderInfoController {

	private static Logger logger = LoggerFactory.getLogger(OrderInfoController.class);

	@Autowired
	private OrderInfoService orderInfoService;

	@Autowired
	private OrderRefundService OrderRefundService;

	@Autowired
	private PayProdService payProdService;

	@Autowired
	private PayChannelService payChannelService;

	@Autowired
	private TdCustInfoService tdCustInfoService;

	/***
	 * 订单信息
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(OrderInfoPath.ORDERINFOLIST)
	public ModelAndView allTradeBill(OrderInfoQueryBean queryBean, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("查询交易表");
		ModelAndView model = new ModelAndView(OrderInfoPath.BASE + OrderInfoPath.ORDERINFOLIST);
		String isFirst = request.getParameter("isFirst");
		if (StringUtils.isEmpty(isFirst)) {
			Date date = new Date();
			queryBean.setCreateTimeBv(DateUtils.formatDate(date, "yyyy-MM-dd"));
			queryBean.setCreateTimeEv(DateUtils.formatDate(date, "yyyy-MM-dd"));
			queryBean.setCreateTimeB(DateUtils.formatDate(date, "yyyy-MM-dd 00:00:00"));

			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date);
			calendar.add(Calendar.DATE, 1); // 把日期往后增加一天,整数 往后推,负数往前移动
			date = calendar.getTime();
			queryBean.setCreateTimeE(DateUtils.formatDate(date, "yyyy-MM-dd 00:00:00"));

		} else {
			String createTimeBv = queryBean.getCreateTimeB();
			if (StringUtils.isEmpty(createTimeBv)) {
				queryBean.setCreateTimeBv(createTimeBv);
				queryBean.setCreateTimeB("");
			} else {
				queryBean.setCreateTimeBv(createTimeBv);
				Date createTimeB = DatetimeUtils.stringForDate(createTimeBv, "yyyy-MM-dd");
				queryBean.setCreateTimeB(DatetimeUtils.dateFormat(createTimeB, "yyyy-MM-dd 00:00:00"));
			}

			String createTimeEv = queryBean.getCreateTimeE();
			if (StringUtils.isEmpty(createTimeEv)) {
				queryBean.setCreateTimeEv(createTimeEv);
				queryBean.setCreateTimeE("");
			} else {
				queryBean.setCreateTimeEv(createTimeEv);
				Date createTimeE = DatetimeUtils.stringForDate(createTimeEv, "yyyy-MM-dd");

				Calendar calendar = new GregorianCalendar();
				calendar.setTime(createTimeE);
				calendar.add(Calendar.DATE, 1); // 把日期往后增加一天,整数 往后推,负数往前移动
				createTimeE = calendar.getTime();
				queryBean.setCreateTimeE(DatetimeUtils.dateFormat(createTimeE, "yyyy-MM-dd 00:00:00"));
			}

		}
		String userId = String.valueOf(WebUtils.getUserInfo().getUserId());
		// 是否有权限查看所有订单
		boolean isAllList = tdCustInfoService.isAllList(userId);
		List<OrderInfoBean> list = null;
		if (isAllList) {
			list = orderInfoService.getOrderInfoList(queryBean);
		} else {
			queryBean.setUserName(WebUtils.getUserInfo().getUserName());
			queryBean.setUserId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			list = orderInfoService.queryMylist(queryBean);
		}

		List<PayProdBean> payProdList = payProdService.getPayProdList(null);
		List<PayChannelBean> payChannelList = payChannelService.getPayChannelList(null);
		model.addObject("orderInfoList", list);

		model.addObject("queryBean", queryBean);

		model.addObject("isFirst", "No");
		model.addObject("payProdList", payProdList);
		model.addObject("payChannelList", payChannelList);
		return model;
	}

	@RequestMapping(OrderInfoPath.ORDERINFOEXPORT)
	public void exportRechargeExcel(OrderInfoQueryBean queryBean, HttpServletRequest request,
			HttpServletResponse response) {

		logger.info("导出交易信息");
		String userId = String.valueOf(WebUtils.getUserInfo().getUserId());
		try {
			// 是否有权限查看所有订单
			boolean isAllList = tdCustInfoService.isAllList(userId);
			List<ExportOrderInfoBean> list = null;

			String[] headers = { "订单号", "商户编号", "商户名", "产品号", "渠道", "下级渠道", "中信订单号", "渠道订单号", "网关版本", "订单金额", "付款金额",
					"结算金额", "代金券", "商品描述", "付款方式", "订单时间", "订单超时时间", "订单失效时间", "签名类型", "签名字符串", "订单状态", "通知时间", "通知次数",
					"通知结果(0成功500失败)", "订单创建时间", "修改时间", "修改人", "买家联系方式", "买家联系人", "订单支付成功时间", "订单退款时间", "提交核心状态",
					"核心流水号", "核心返回信息", "核心返回码", "核心返回时间", "下级渠道名", "支付产品" };

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			if (queryBean.getCreateTimeE() == "" && queryBean.getCreateTimeE() == null) {
				queryBean.setCreateTimeE(sdf.format(cal.getTime()));
			}
			if (queryBean.getCreateTimeB() == "" && queryBean.getCreateTimeB() == null) {
				// 默认开始日期为前三个月
				cal.add(Calendar.DAY_OF_YEAR, -90);
				queryBean.setCreateTimeB(sdf.format(cal.getTime()));
			}

			if (isAllList) {
				list = orderInfoService.getOrderInfoListExport(queryBean);
			} else {
				queryBean.setUserName(WebUtils.getUserInfo().getUserName());
				queryBean.setUserId(String.valueOf(WebUtils.getUserInfo().getUserId()));
				list = orderInfoService.getMyOrderInfoListExport(queryBean);
			}
			String fileName = DatetimeUtils.dateSecond() + "_交易信息.xls";
			Map<String, String> fileInfo = orderInfoService.exportExcel(list, headers, fileName, "交易信息", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel交易信息成功");
		} catch (Exception e) {
			logger.error("导出excel交易信息异常", e);
		}

	}

	// 查询交易成功非七分钱支付，写核心失败的交易数据
	@RequestMapping(OrderInfoPath.ORDEREXCEPTIONLIST)
	public ModelAndView orderExceptionList(OrderInfoQueryBean queryBean, HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView model = new ModelAndView(OrderInfoPath.BASE + OrderInfoPath.ORDEREXCEPTIONLIST);
		List<OrderInfoBean> list = orderInfoService.getOrderExceptionList(queryBean);

		model.addObject("orderInfoList", list);

		model.addObject("queryBean", queryBean);
		return model;
	}

	/**
	 * 异常明细页面
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(OrderInfoPath.DETAILEXCEPTION)
	public ModelAndView detailException(OrderInfoQueryBean queryBean) {

		ModelAndView mv = new ModelAndView(OrderInfoPath.BASE + OrderInfoPath.DETAILEXCEPTION);
		// 查询订单信息
		DealOperation operation = null;
		switch (queryBean.getOrderType()) {
		case Constant.COMBINED_TYPE_PAY: // 支付订单
			operation = orderInfoService.getOrderInfoDetail(queryBean.getOrderId());
			break;
		case Constant.COMBINED_TYPE_REFUND: // 退款订单
			operation = OrderRefundService.getRefundDetail(queryBean.getRefundId());
			break;
		}
		mv.addObject("operation", operation);
		mv.addObject("orderType", queryBean.getOrderType());
		return mv;
	}

	/**
	 * 续作（核心流水记录）
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(OrderInfoPath.SEQUEL_NEXT_STEP_OPERATION)
	public String nextStep(OrderInfoQueryBean queryBean) {
		logger.info("续作核心入账");
		JSONObject json = new JSONObject();
		try {
			orderInfoService.nextStep(queryBean);
			json.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("核心入账异常" + e.getMessage(), e);
			json.put("result", "FAIL");
			json.put("message", "核心入账异常");
		}

		return json.toJSONString();
	}
}
