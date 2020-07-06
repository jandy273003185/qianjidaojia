package com.qifenqian.bms.basemanager.trade;

import java.math.BigDecimal;
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

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.trade.bean.AllTradeBill;
import com.qifenqian.bms.basemanager.trade.bean.TdTradeBillMainVO;
import com.qifenqian.bms.basemanager.trade.bean.TradeExcel;
import com.qifenqian.bms.basemanager.trade.bean.TradeSummaryExcel;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

@Controller
@RequestMapping(TradeBillMainPath.BASE)
public class TradeBillMainController {

	private static Logger logger = LoggerFactory.getLogger(TradeBillMainController.class);

	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 查询交易记录
	 * 
	 * @param city
	 * @return
	 */
	@RequestMapping(TradeBillMainPath.LIST)
	public ModelAndView list(TdTradeBillMainVO queryBean, HttpServletRequest request) {

		ModelAndView model = new ModelAndView(TradeBillMainPath.BASE + TradeBillMainPath.LIST);

		String isFirst = request.getParameter("isFirst");
		if (StringUtils.isEmpty(isFirst)) {
			queryBean.setBeginTime(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));
			queryBean.setEndCreateTime(DateUtils.formatDate(new Date(), "yyyy-MM-dd"));

		}

		List<TdTradeBillMainVO> tradeBillList = tradeBillService.selectConsume(queryBean);
		
		String isShow = request.getParameter("isShow");
		if (isShow == null) {
			isShow = "NO";
		}

		model.addObject("isFirst", "No");
		model.addObject("isShow", isShow);
		model.addObject("queryBean", queryBean);
		model.addObject("tradeBillList", JSONObject.toJSON(tradeBillList));
		return model;
	}

	/**
	 * 导出交易列表
	 * 
	 * @param tdTradeBillMainVO
	 * @param request
	 * @param response
	 */
	@RequestMapping(TradeBillMainPath.TRADEEXPORT)
	public void exportTradeExcel(TdTradeBillMainVO tdTradeBillMainVO, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("导出excel交易查询表");
		try {

			List<TradeExcel> excels = tradeBillService.selectTradeConsumeExcel(tdTradeBillMainVO);

			String[] headers = { "七分钱交易流水号", "订单名称", "订单描述", "商户订单号", "银联订单号", "交广科技订单号", "商户编号", "商户名称", "客户名称",
					"手机号码", "账期", "订单开始时间", "订单结束时间", "订单状态", "订单类型", "订单金额", "商户结算金额","产品" };
			String fileName = DatetimeUtils.dateSecond() + "_消费查询.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "消费表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel交易查询表成功");
		} catch (Exception e) {
			logger.error("导出excel交易查询表异常", e);
			e.printStackTrace();
		}
	}
	
	/**
	 * 交易汇总
	 * 
	 * @param tdTradeBillMainVO
	 * @return
	 */
	@RequestMapping(TradeBillMainPath.SUMMARY)
	public ModelAndView listSummary(TdTradeBillMainVO tdTradeBillMainVO) {
		String beginTime = tdTradeBillMainVO.getBeginTime();
		String endCreateTime = tdTradeBillMainVO.getEndCreateTime();
		String custName = tdTradeBillMainVO.getCustName();
		String tradeType = tdTradeBillMainVO.getTradeType();

		List<TdTradeBillMainVO> tradeBills = tradeBillService.selectTdradeBillMainSummary(tdTradeBillMainVO);
		BigDecimal sum = new BigDecimal("0.00");
		BigDecimal sumSettAmt = new BigDecimal("0.00");
		int sumTrade = 0;

		for (TdTradeBillMainVO vo : tradeBills) {
			sum = sum.add(vo.getSumCountAmount());
			sumTrade += vo.getSumCount();
			sumSettAmt = sumSettAmt.add(vo.getSumSettleAmt());
		}

		ModelAndView model = new ModelAndView(TradeBillMainPath.BASE + TradeBillMainPath.SUMMARY);

		if (StringUtils.isEmpty(tdTradeBillMainVO.getBeginTime())) {
			for (TdTradeBillMainVO vo : tradeBills) {
				vo.setBeginTime(vo.getMinDate().substring(0, 10));
			}
		} else {
			for (TdTradeBillMainVO vo : tradeBills) {
				vo.setBeginTime(tdTradeBillMainVO.getBeginTime());
			}
		}
		if (StringUtils.isEmpty(tdTradeBillMainVO.getEndCreateTime())) {
			for (TdTradeBillMainVO vo : tradeBills) {
				vo.setEndCreateTime(vo.getMaxDate().substring(0, 10));
			}
		} else {
			for (TdTradeBillMainVO vo : tradeBills) {
				vo.setEndCreateTime(tdTradeBillMainVO.getEndCreateTime());
			}
		}
		model.addObject("sum", sum);
		model.addObject("sumSettAmt", sumSettAmt);
		model.addObject("sumTrade", sumTrade);
		model.addObject("tradeBillList", JSONObject.toJSONString(tradeBills));
		model.addObject("tradeBills", tradeBills);
		model.addObject("beginTime", beginTime);
		model.addObject("endCreateTime", endCreateTime);
		model.addObject("custName", custName);
		model.addObject("tradeType", tradeType);
		return model;
	}

	/**
	 * 导出交易汇总列表
	 * 
	 * @param tdTradeBillMainVO
	 * @return
	 */
	@RequestMapping(TradeBillMainPath.SUMMARYEXPORT)
	public void exportExcel(TdTradeBillMainVO tdTradeBillMainVO, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("导出excel交易汇总表");

		try {
			List<TradeSummaryExcel> excels = tradeBillService.selectTradeSummaryExcel(tdTradeBillMainVO);
			String[] headers = { "开始账期", "结束账期", "商户编号", "商户名称", "交易笔数", "交易金额", "商户结算金额", "交易类型" };
			String fileName = DatetimeUtils.dateSecond() + "_交易汇总.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "交易汇总表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel交易汇总表成功");
		} catch (Exception e) {
			logger.error("导出excel交易汇总表异常", e);
			e.printStackTrace();
		}

	}
	
	@RequestMapping(TradeBillMainPath.ALLTRADEBILL)
	public ModelAndView allTradeBill(String bank,String payProd,String payChannel,HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("查询所有交易表（wx  ali sp）");
		ModelAndView model = new ModelAndView(TradeBillMainPath.BASE + TradeBillMainPath.ALLTRADEBILL);
		List<AllTradeBill> list = tradeBillService.getAllTradeBill(bank,payProd,payChannel);
		model.addObject("allTradeBill", list);
		model.addObject("bank",bank);
		model.addObject("payProd",payProd);
		model.addObject("payChannel",payChannel);
		return model;
	}
	
	@RequestMapping(TradeBillMainPath.ALLTRADEEXPORT)
	public void exportRechargeExcel(String bank,String payProd,String payChannel,HttpServletRequest request, HttpServletResponse response) {

		logger.info("导出交易信息");

		try {
			String[] headers = { "订单号","商户号","商户名", "服务号", "渠道","下级渠道", "中信订单号", "渠道订单号", "返回商家页面地址", "通知商家后台调用地址", "网关版本", "订单金额","付款金额" ,"代金券" ,"商品描述","付款方式","订单时间","订单超时时间","订单失效时间","备用1","备用2","订单状态","通知时间","通知次数","通知结果(0成功500失败)","订单创建时间","订单支付成功时间","订单退款时间","修改时间","修改人"};
			List<AllTradeBill> list = tradeBillService.getAllTradeBillExport(bank, payProd, payChannel);
			String fileName = DatetimeUtils.dateSecond() + "_交易信息.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(list, headers, fileName, "交易信息", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel交易信息成功");
		} catch (Exception e) {
			logger.error("导出excel交易信息异常", e);
		}

	}

}
