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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.AgentSettleBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.AgentSettleDetailBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.service.AgentSettleService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;


@Controller
@RequestMapping(AgentCollectDailyPath.BASE)
public class AgentSettleController {
	
	private static Logger logger = LoggerFactory.getLogger(AgentSettleController.class);
	
	@Autowired
	private AgentSettleService agentSettleService;
	
	/***
	 * 代理商结算列表
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(AgentSettlePath.AGENTSETTLELIST)
	public ModelAndView list(AgentSettleBean queryBean) {
		logger.info("查询代理商结算列表");
		ModelAndView mv = new ModelAndView(AgentSettlePath.BASE + AgentSettlePath.AGENTSETTLELIST);
		
		List<AgentSettleBean> list = agentSettleService.getAgentSettleList(queryBean);
		mv.addObject("agentSettleList", list);
		mv.addObject("queryBean", queryBean);
		return mv;
	}
	
	@RequestMapping(AgentSettlePath.DETAIL)
	@ResponseBody
	public String detail(String settleId) {
		logger.info("查询代理商结算详情");
		JSONObject jo= new JSONObject();
		try {
			AgentSettleDetailBean detailBean = agentSettleService.getDetailBySettleId(settleId);
			jo.put("detailBean", detailBean);
			jo.put("result", "SUCCESS");
		} catch (Exception e) {
			jo.put("result", "FAIL");
		}
			
		return jo.toJSONString();
	}
	
	@RequestMapping(AgentSettlePath.AGENTSETTLEEXPORT)
	public void exportExcel(AgentSettleBean queryBean,HttpServletRequest request, HttpServletResponse response) {

		logger.info("导出每日汇集信息");

		try {
			String[] headers = {"结算号","代理商号","结算会计日期","结算开始日期","结算结束日期","协议编号","结算金额","完成日期","状态","备注","生成人","记账日期","生成时间","复核人","复核时间","核销人","核销时间"};
			List<AgentSettleBean> list = agentSettleService.getAgentSettleListExport(queryBean);
			String fileName = DatetimeUtils.dateSecond() + "_交易信息.xls";
			Map<String, String> fileInfo = agentSettleService.exportExcel(list, headers, fileName, "代理商结算信息", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel代理商结算信息成功");
		} catch (Exception e) {
			logger.error("导出excel代理商结算信息异常", e);
		}

	}
}
