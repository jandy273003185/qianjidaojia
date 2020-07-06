package com.qifenqian.bms.expresspay.tradereport.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qifenqian.bms.expresspay.tradereport.bean.TradeReport;
import com.qifenqian.bms.expresspay.tradereport.mapper.TradeReportMapper;

@Controller
@RequestMapping(TradeReportPath.BASE)
public class TradeReportController {

	@Autowired
	private TradeReportMapper tradeReportMapper;

	@RequestMapping(TradeReportPath.LIST)
	public ModelAndView list(TradeReport tradeReport) {

		if (null == tradeReport.getWorkDate() || "".equals(tradeReport.getWorkDate())) {
			tradeReport.setWorkDate(shorYesterDaytDate());
		}
		ModelAndView mv = new ModelAndView(TradeReportPath.BASE + TradeReportPath.LIST);
		List<TradeReport> reportList = tradeReportMapper.selectReportList(tradeReport);
		mv.addObject("queryBean", tradeReport);
		mv.addObject("reportList", reportList);
		return mv;
	}

	/**
	 * 
	 * @return
	 */
	public String shorYesterDaytDate() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String yesterday = new SimpleDateFormat("yyyyMMdd ").format(cal.getTime());
		return yesterday;
	}
}
