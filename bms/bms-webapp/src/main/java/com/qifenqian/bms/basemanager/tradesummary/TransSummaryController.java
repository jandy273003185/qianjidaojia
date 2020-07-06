package com.qifenqian.bms.basemanager.tradesummary;

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

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.tradesummary.bean.TransSummaryBean;
import com.qifenqian.bms.basemanager.tradesummary.service.TransSummaryService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

@Controller
@RequestMapping(TransSummaryPath.BASE)
public class TransSummaryController {
	
	private static Logger logger = LoggerFactory.getLogger(TransSummaryController.class);
	
	@Autowired
	private TransSummaryService transSummaryService;
	
	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 交易汇总信息
	 * 
	 * @param tradeControl
	 */
	@RequestMapping(TransSummaryPath.LIST)
	public ModelAndView list(TransSummaryBean queryBean) {
		logger.info("交易汇总信息查询对象：{}",JSONObject.toJSONString(queryBean,true));
		
		ModelAndView mv = new ModelAndView(TransSummaryPath.BASE + TransSummaryPath.LIST);
		List<TransSummaryBean> tradeSummaryList = transSummaryService.selectTradeSummary(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("tradeSummaryList", tradeSummaryList);
		return mv;
	}
	
	/**
	 * 导出交易汇总查询列表
	 * 
	 * @param transSummaryBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(TransSummaryPath.TRADESUMMARYEXPORT)
	public void tradeSummaryExport(TransSummaryBean transSummaryBean, HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("导出交易汇总查询列表对象：{}",JSONObject.toJSONString(transSummaryBean,true));
		try {
			List<TransSummaryBean> excels = transSummaryService.selectTradeSummaryExcel(transSummaryBean);
			String[] headers = { "业务名称", "总笔数", "总金额", "业务类型","账期开始时间","账期结束时间"};
			String fileName = DatetimeUtils.dateSecond() + "_交易汇总查询列表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "交易汇总查询列表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出交易汇总查询列表成功");
		} catch (Exception e) {
			logger.error("导出交易汇总查询列表异常", e);
			e.printStackTrace();
		}
	}

}
