package com.qifenqian.bms.basemanager.aggregatepayment.agent.controller;

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

import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.AgentCollectDailyBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.service.AgentCollectDailyService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;


@Controller
@RequestMapping(AgentCollectDailyPath.BASE)
public class AgentCollectDailyController {
	
	private static Logger logger = LoggerFactory.getLogger(AgentCollectDailyController.class);
	
	@Autowired
	private AgentCollectDailyService agentCollectDailyService;
	
	/***
	 * 代理每日汇集信息列表
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(AgentCollectDailyPath.COLLECTDAILYLIST)
	public ModelAndView list(AgentCollectDailyBean queryBean) {
		logger.info("查询代理每日汇集信息");
		ModelAndView mv = new ModelAndView(AgentCollectDailyPath.BASE + AgentCollectDailyPath.COLLECTDAILYLIST);
		
		List<AgentCollectDailyBean> list = agentCollectDailyService.getAgentCollectDailyList(queryBean);
		mv.addObject("agentCollectDailyList", list);
		mv.addObject("queryBean", queryBean);
		return mv;
	}
	

	@RequestMapping(AgentCollectDailyPath.COLLECTDAILYEXPORT)
	public void exportExcel(AgentCollectDailyBean queryBean,HttpServletRequest request, HttpServletResponse response) {

		logger.info("导出每日汇集信息");

		try {
			String[] headers = {"明细号","代理商号","结算会计日期","商户号","渠道号","应收金额","状态","记账日期","生成日期"};
			List<AgentCollectDailyBean> list = agentCollectDailyService.getAgentCollectDailyListExport(queryBean);
			String fileName = DatetimeUtils.dateSecond() + "_交易信息.xls";
			Map<String, String> fileInfo = agentCollectDailyService.exportExcel(list, headers, fileName, "交易信息", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel交易信息成功");
		} catch (Exception e) {
			logger.error("导出excel交易信息异常", e);
		}

	}
}
