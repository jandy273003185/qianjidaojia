package com.qifenqian.bms.basemanager.agency.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.agency.bean.AgenReport;
import com.qifenqian.bms.basemanager.agency.bean.AgentMerchantReport;
import com.qifenqian.bms.basemanager.agency.service.AgencyService;
import com.qifenqian.bms.basemanager.bank.mapper.BankMapper;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.platform.utils.ExportExcelUtils;

/***
 * 代理商报表
 * 
 * @param queryBean
 * @return
 */
@Controller
@RequestMapping(AgenReportPath.BASE)
public class AgencyReportController {
	
	private Logger logger = LoggerFactory.getLogger(AgencyReportController.class);
	@Autowired
	private TdCustInfoService tdCustInfoService;
	@Autowired
	private AgencyService agencyService;
	@Autowired
	private BankMapper bankMapper;
	@Autowired
	private TradeBillService tradeBillService;

	@RequestMapping(AgenReportPath.LIST)
	public ModelAndView list(AgenReport agenReport,HttpServletRequest request){
		logger.info("---------代理商报表查询---------");
		ModelAndView mv = new ModelAndView(AgenReportPath.BASE + AgenReportPath.LIST);
		String isFirst = request.getParameter("isFirst");
		if(StringUtils.isEmpty(isFirst)){
			agenReport.setBeginWorkDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
			agenReport.setEndDate(new SimpleDateFormat("yyyyMMdd").format(new Date()));
		}
		List<AgenReport> agenlist = agencyService.getAgenReportList(agenReport);
		BigDecimal tradeTotalAmt = new BigDecimal("0.00");// 总交易额
		BigDecimal refundTotalAmt = new BigDecimal("0.00");// 退款交易额
		BigDecimal commisionTotalAmt = new BigDecimal("0.00");// 总结算金额
		int tradetotalCount = 0; 
		for(AgenReport report : agenlist){
			tradeTotalAmt =	tradeTotalAmt.add(new BigDecimal(report.getReceiveTotalAmt()));
			tradetotalCount += Integer.valueOf( report.getReceiveCount());
			refundTotalAmt = refundTotalAmt.add(new BigDecimal(report.getRefundTotalAmt()));
			commisionTotalAmt = commisionTotalAmt.add(new BigDecimal(report.getCommision()));
		}
		BigDecimal vaildTotalAmt  = tradeTotalAmt.subtract(refundTotalAmt);
		
		mv.addObject("isFirst","No");
		mv.addObject("agencyReportList", JSONObject.toJSON(agenlist));
		mv.addObject("tradeTotalAmt", tradeTotalAmt);
		mv.addObject("refundTotalAmt", refundTotalAmt);
		mv.addObject("commisionTotalAmt", commisionTotalAmt);
		mv.addObject("vaildTotalAmt", vaildTotalAmt);
		mv.addObject("tradetotalCount", tradetotalCount);
		mv.addObject("queryBean", agenReport);
		return mv;
	}
	
	@RequestMapping(AgenReportPath.EXPORT)
	public void export(AgenReport agenReport,HttpServletRequest request,HttpServletResponse response){
		logger.info("---------代理商报表导出---------");
		logger.info("导出代理商列表信息");
		try {
			/** 根据条件查询需要导出的数据 **/
			List<AgenReport> agenReports = agencyService.exportAgenReportList(agenReport);
			/** 设置excel头 **/
			String[] excelHeaders = {"编号" ,"开始账期","结束账期", "代理商ID编号","代理商商户编号","代理商名称","客户经理", "执行日期" ,//
					"商户编号" , "商户收款笔数", "商户收款金额", "商户退款笔数" ,//
					"商户退款金额" , "有效金额", "代理商底价费率" , "佣金收入"};
			/** 设置文件名 **/
			String fileName = DatetimeUtils.dateSecond() + "_代理商报表信息.xls";
			/**  **/
			Map<String, String> exportExcel = ExportExcelUtils.exportExcel(agenReports, excelHeaders, fileName, "代理商报表", request);
			/**  **/
			DownLoadUtil.downLoadFile(exportExcel.get("filePath"), response, exportExcel.get("fileName"), "xls");
			logger.info("导出excel代理商报表成功");
		} catch (Exception e) {
			logger.error("导出excel代理商报表异常", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 代理商商户报表
	 * @param agenReport
	 * @param request
	 * @return
	 */
	@RequestMapping(AgenReportPath.MERCHANTLIST)
	public ModelAndView merchantlist(AgentMerchantReport agenReport,HttpServletRequest request){
		logger.info("---------代理商商户报表查询---------");
		ModelAndView mv = new ModelAndView(AgenReportPath.BASE + AgenReportPath.MERCHANTLIST);
		List<AgentMerchantReport> agenlist = agencyService.getAgentMerchantReport(agenReport);
		BigDecimal tradeTotalAmt = new BigDecimal("0.00");// 总交易额
		BigDecimal refundTotalAmt = new BigDecimal("0.00");// 退款交易额
		BigDecimal commisionTotalAmt = new BigDecimal("0.00");// 总结算金额
		int tradetotalCount = 0; 
		for(AgentMerchantReport report : agenlist){
			tradeTotalAmt =	tradeTotalAmt.add(new BigDecimal(report.getReceiveTotalAmt()));
			tradetotalCount += Integer.valueOf( report.getReceiveCount());
			refundTotalAmt = refundTotalAmt.add(new BigDecimal(report.getRefundTotalAmt()));
			commisionTotalAmt = commisionTotalAmt.add(new BigDecimal(report.getCommision()));
		}
		BigDecimal vaildTotalAmt  = tradeTotalAmt.subtract(refundTotalAmt);
		
		mv.addObject("agencyReportList", JSONObject.toJSON(agenlist));
		mv.addObject("tradeTotalAmt", tradeTotalAmt);
		mv.addObject("refundTotalAmt", refundTotalAmt);
		mv.addObject("commisionTotalAmt", commisionTotalAmt);
		mv.addObject("vaildTotalAmt", vaildTotalAmt);
		mv.addObject("tradetotalCount", tradetotalCount);
		mv.addObject("agentId", agenReport.getAgentId());
		mv.addObject("beginWorkDate", agenReport.getBeginWorkDate());
		mv.addObject("endDate", agenReport.getEndDate());
		mv.addObject("queryBean", agenReport);
		return mv;
	}
	
	/**
	 * 商户报表导出
	 * @param agenReport
	 * @param request
	 * @param response
	 */
	@RequestMapping(AgenReportPath.MERCHANTEXPORT)
	public void exportMerchant(AgentMerchantReport agenReport,HttpServletRequest request,HttpServletResponse response){
		logger.info("---------代理商报表导出---------");
		logger.info("导出代理商列表信息");
		try {
			/** 根据条件查询需要导出的数据 **/
			List<AgentMerchantReport> agenReports = agencyService.getAgentMerchantReport(agenReport);
			/** 设置excel头 **/
			String[] excelHeaders = {"开始账期","结束账期", "商户ID" ,"商户费率", "代理商ID" ,//
					"代理商名称" , "代理商费率" , "商户名称","商户收款笔数 ","商户收款金额", "商户退款笔数" ,//
					"商户退款金额" , "有效金额","佣金收入"};
			/** 设置文件名 **/
			String fileName = DatetimeUtils.dateSecond() + "_代理商商户报表信息.xls";
			/**  **/
			Map<String, String> exportExcel = ExportExcelUtils.exportExcel(agenReports, excelHeaders, fileName, "代理商商户报表", request);
			/**  **/
			DownLoadUtil.downLoadFile(exportExcel.get("filePath"), response, exportExcel.get("fileName"), "xls");
			logger.info("导出excel代理商商户报表成功");
		} catch (Exception e) {
			logger.error("导出excel代理商商户报表异常", e);
			throw new RuntimeException(e);
		}
	}
}
