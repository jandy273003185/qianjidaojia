package com.qifenqian.bms.basemanager.aggregatepayment.merchant.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdRefund;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.service.TdRefundService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;


@Controller
@RequestMapping(TdRefundPath.BASE)
public class TdRefundController {
	
	private static Logger logger = LoggerFactory.getLogger(TdRefundController.class);
	@Autowired
	private TdRefundService tdRefundService;
	
	/**
	 * 渠道金额限制列表
	 * @param bean
	 * @return
	 */
	@RequestMapping(TdRefundPath.LIST)
	public ModelAndView list(TdRefund queryBean){
		ModelAndView mv = new ModelAndView(TdRefundPath.BASE + TdRefundPath.LIST);
		List<TdRefund>  list = tdRefundService.getRefundList(queryBean);
		mv.addObject("queryBean", queryBean);
		
		mv.addObject("refundList", list);
		return mv;
	}
	
	/**
	 * 删除商户渠道金额限制
	 * @param channel
	 * @return
	 */
	@RequestMapping(TdRefundPath.EXPORT)
	
	public void exportExcel(TdRefund queryBean,HttpServletRequest request, HttpServletResponse response) {

		logger.info("导出退款信息");

		try {
			String[] headers = {"退款编号","退款流水号","原订单编号","商户编号","原订单总金额","退款金额","中信交易号","中信退款号","退款渠道","渠道返回退款错误码","渠道退款返回结果描述","退款时间","退款状态","退款发起时间","退款人编号"};
			List<TdRefund> list = tdRefundService.getRefundExport(queryBean);
			String fileName = DatetimeUtils.dateSecond() + "_退款信息.xls";
			Map<String, String> fileInfo = tdRefundService.exportExcel(list, headers, fileName, "退款信息", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel退款信息成功");
		} catch (Exception e) {
			logger.error("导出excel退款信息异常", e);
		}

	}

}
