package com.qifenqian.bms.expresspay.tradeResult.controller;

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
import com.qifenqian.bms.expresspay.mpper.JgkjTradeMapper;
import com.qifenqian.bms.expresspay.tradeResult.bean.JgkjTrade;
import com.qifenqian.bms.expresspay.tradeResult.bean.TradeResult;
import com.qifenqian.bms.expresspay.tradeResult.service.TradeResultService;

@Controller
@RequestMapping(TradeResultPath.BASE)
public class TradeResultController {
	private static Logger logger = LoggerFactory.getLogger(TradeResultController.class);
	@Autowired
	private TradeResultService tradeResultService;
	
	@Autowired
	private JgkjTradeMapper jgkjTradeMapper;
	
	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 交广科技交易结果列表
	 * 
	 * @param cardNo
	 * @return
	 */
	@RequestMapping(TradeResultPath.TRADERESULTMAIN)
	public ModelAndView tradeResultmain(JgkjTrade queryBean) {
		ModelAndView mv = new ModelAndView(TradeResultPath.BASE + TradeResultPath.TRADERESULTMAIN);
		List<JgkjTrade> jgkjTradeList = tradeResultService.queryJgkjTradeList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("jgkjTradeList", jgkjTradeList);
		return mv;
	}
	/***
	 * 查询交广科技
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(TradeResultPath.TRADERESULT)
	@ResponseBody
	public String tradeResult(JgkjTrade queryBean) {

		JSONObject jsonObj = new JSONObject();
		if(StringUtils.isBlank(queryBean.getTransId())){
			jsonObj.put("result", "fail");
			jsonObj.put("message", "七分钱请求编号为空");
			return jsonObj.toJSONString();
		}
		if(StringUtils.isBlank(queryBean.getCardNo())){
			jsonObj.put("result", "fail");
			jsonObj.put("message", "交广科技卡号为空");
			return jsonObj.toJSONString();
		}
		try {
			TradeResult tradeResult	= tradeResultService.getTradeDetail(queryBean);
			if (null!=tradeResult&&!StringUtils.isBlank(tradeResult.getAmount())) {
				List<TradeResult> tradeResults =new ArrayList<TradeResult>();
				tradeResults.add(tradeResult);
				jsonObj.put("message", JSONObject.toJSON(tradeResults));
				jsonObj.put("result", "success");
			} else {
				jsonObj.put("result", "fail");
				jsonObj.put("message", "无交广科技交易信息");
			}
		} catch (Exception e) {
			logger.error("查询交广科技交易信息未成功", e);
			jsonObj.put("result", "fail");
			jsonObj.put("message", e.getMessage());
		}
		return jsonObj.toJSONString();
	}
	
	/***
	 * 导出交广科技交易结果
	 * @param jgkjTrade
	 * @param request
	 * @param response
	 */
	@RequestMapping(TradeResultPath.TRADERESULTEXPORT)
	public void tradeResultExport(JgkjTrade jgkjTrade, HttpServletRequest request, HttpServletResponse response) {

		logger.info("导出交广科技交易结果信息");

		try {
			String[] headers = {"七分钱请求流水号","交易编号","交易类型","交广科技卡号","交易金额","订单编号","交易请求时间","七分钱会计日期","状态","交广科技返回时间","交广科技平台流水号","交广科技返回码"};
			List<JgkjTrade> jgkjTradeExcel = jgkjTradeMapper.queryJgkjTradeList(jgkjTrade);
			String fileName = DatetimeUtils.dateSecond() + "_交广科技交易结果.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(jgkjTradeExcel, headers, fileName, "交广科技交易结果", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel交广科技交易结果信息成功");
		} catch (Exception e) {
			logger.error("导出excel银联交易结果信息异常", e);
		}

	}
}
