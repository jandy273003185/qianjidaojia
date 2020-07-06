package com.qifenqian.bms.unionPay.tradeylresult.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.unionPay.tradeylresult.TradeYlResultService;
import com.qifenqian.bms.unionPay.tradeylresult.bean.TradeState;
import com.qifenqian.bms.unionPay.tradeylresult.bean.TradeYlResut;
import com.qifenqian.bms.unionPay.tradeylresult.mapper.TradeYlResultMapper;

@Controller
@RequestMapping(TradeYlResultPath.BASE)
public class TradeYlResultController {

	private static Logger logger = LoggerFactory.getLogger(TradeYlResultController.class);

	@Autowired
	private TradeYlResultService tradeYlResultService;
	@Autowired
	private TradeYlResultMapper tradeYlResultMapper;
	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 账户交易结果查询
	 * 
	 * @param cardNo
	 * @return
	 */
	@RequestMapping(TradeYlResultPath.TRADEYLRESULTMAIN)
	public ModelAndView tradeYlResultMain(TradeYlResut queryBean) {

		ModelAndView mv = new ModelAndView(TradeYlResultPath.BASE + TradeYlResultPath.TRADEYLRESULTMAIN);

		List<TradeYlResut> ylResultList = tradeYlResultService.selectTransYlResut(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("ylResultList", ylResultList);
		return mv;
	}

	/**
	 * 修改
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(TradeYlResultPath.EDIT)
	public String edit(TradeYlResut tradeYlResut) {

		logger.info("请求修改tradeYlResut：[{}]", JSONObject.toJSONString(tradeYlResut, true));

		if (StringUtils.isEmpty(tradeYlResut.getTransId())) {
			throw new IllegalArgumentException("编号为空");
		}
		JSONObject jsonObject = new JSONObject();

		try {
			// 保存修改
			int i = tradeYlResultService.updateTransYlResut(tradeYlResut);
			if (1 == i) {
				jsonObject.put("result", "SUCCESS");
			} else {
				jsonObject.put("result", "FAILURE");
				jsonObject.put("message", "修改失败");
			}

		} catch (Exception e) {
			logger.error("修改异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/***
	 * 查询银联
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(TradeYlResultPath.QUERYTRADEYLRESULT)
	@ResponseBody
	public String queryTradeYlResult(TradeYlResut queryBean) {

		JSONObject jsonObj = new JSONObject();
		if (StringUtils.isBlank(queryBean.getTransId())) {
			jsonObj.put("result", "fail");
			jsonObj.put("message", "七分钱请求编号为空");
			return jsonObj.toJSONString();
		}
		if (StringUtils.isBlank(queryBean.getTransSubmitTime())) {
			jsonObj.put("result", "fail");
			jsonObj.put("message", "请求时间为空");
			return jsonObj.toJSONString();
		}

		try {
			TradeState tradeState = tradeYlResultService.selectTradeStateResult(queryBean);
			if (null != tradeState) {
				List<TradeState> tradeStates = new ArrayList<TradeState>();
				tradeStates.add(tradeState);
				jsonObj.put("message", JSONObject.toJSON(tradeStates));
				jsonObj.put("result", "success");
			} else {
				jsonObj.put("result", "fail");
				jsonObj.put("message", "无银联交易信息");
			}
		} catch (Exception e) {
			logger.error("查询银联交易信息未成功", e);
			jsonObj.put("result", "fail");
			jsonObj.put("message", e.getMessage());
		}
		return jsonObj.toJSONString();
	}

	/***
	 * 导出银联交易结果信息
	 * @param tradeYlResut
	 * @param request
	 * @param response
	 */
	@RequestMapping(TradeYlResultPath.TRADEYLRESULTEXPORT)
	public void tradeYlResultExport(TradeYlResut tradeYlResut, HttpServletRequest request, HttpServletResponse response) {

		logger.info("导出银联交易结果信息");

		try {
			String[] headers = {"七分钱请求流水号","交易流水号","银联返回流水号","交易类型","交易时间","客户编号","交易金额","交易提交时间","同步请求时间","异步请求时间","银联响应码","银联响应信息","系统跟踪号","账号","查询流水号"};
			List<TradeYlResut> tradeYlResutExcel = tradeYlResultMapper.selectTransYlResut(tradeYlResut);
			String fileName = DatetimeUtils.dateSecond() + "_银联交易结果.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(tradeYlResutExcel, headers, fileName, "银联交易结果", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel银联交易结果信息成功");
		} catch (Exception e) {
			logger.error("导出excel银联交易结果信息异常", e);
		}

	}
}
