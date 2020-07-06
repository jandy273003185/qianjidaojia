package com.qifenqian.bms.accounting.financequery;

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

import com.qifenqian.bms.accounting.financequery.bean.FinanceSum;
import com.qifenqian.bms.accounting.financequery.bean.QueryWaterVo;
import com.qifenqian.bms.accounting.financequery.mapper.FinanceSumMapper;
import com.qifenqian.bms.accounting.financequery.mapper.QueryWaterMapper;
import com.qifenqian.bms.accounting.financequery.service.FinanceSumService;
import com.qifenqian.bms.accounting.financequery.service.QueryWaterService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

@Controller
@RequestMapping(FinanceQueryPath.BASE)
public class FinanceQueryController {
	private static Logger logger = LoggerFactory.getLogger(FinanceQueryController.class);
	@Autowired
	private FinanceSumService service;

	@Autowired
	private QueryWaterService waterService;
	@Autowired
	private FinanceSumMapper financeSumMapper;
	
	@Autowired
	private QueryWaterMapper waterMapper;
	
	@Autowired
	private TradeBillService tradeBillService;

	@RequestMapping(FinanceQueryPath.LIST)
	public ModelAndView slipReport(HttpServletRequest request) {
		String subjectName = request.getParameter("subjectName");
		ModelAndView mv = new ModelAndView(FinanceQueryPath.BASE + FinanceQueryPath.LIST);
		List<FinanceSum> financeList = service.selectFinanceList(subjectName);
		mv.addObject("subjectName", subjectName);
		mv.addObject("financeList", financeList);
		return mv;
	}

	/**
	 * 导出交易汇总列表
	 * 
	 * @param tdTradeBillMainVO
	 * @return
	 */
	@RequestMapping(FinanceQueryPath.EXPORTBALANCE)
	public void exportExcel(HttpServletRequest request, HttpServletResponse response, String subjectName) {
		logger.info("导出汇总余额excel表");

		try {
			List<FinanceSum> excelContent = financeSumMapper.selectFinanceList(subjectName);
			String headers[] = {"账户ID","账户编号", "账户名称", "实时余额", "对账日期"};
			String fileName = DatetimeUtils.dateSecond() + "_交易汇总余额.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excelContent, headers, fileName, "交易汇总列表", request);

			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel表成功");
		} catch (Exception e) {
			logger.error("导出excel表异常", e);
			e.printStackTrace();
		}

	}

	/**
	 * 查询汇总流水
	 * **/
	@RequestMapping(FinanceQueryPath.QUERYSUMWATER)
	public ModelAndView querysumwater(QueryWaterVo queryBean) {

		ModelAndView mv = new ModelAndView(FinanceQueryPath.BASE + FinanceQueryPath.QUERYSUMWATER);
		List<QueryWaterVo> querywaterList = waterService.selectQueryWaterList(queryBean);
		mv.addObject("querywaterList", querywaterList);
		mv.addObject("queryBean", queryBean);
		return mv;
	}

	// 导出汇总流水报表
	@RequestMapping(FinanceQueryPath.EXPORTWATERBALANCE)
	public void exportExcelA(QueryWaterVo waterVo, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出汇总流水excel表");
		try {
			List<QueryWaterVo> excelContent=waterMapper.selectQueryWaterList(waterVo);
			String headers []={"记账时间"," 对账ID", "账户名称","业务类型","借方","借方余额","贷方","贷方余额"};
			String fileName =DatetimeUtils.dateSecond()+"_汇总余额流水.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excelContent, headers, fileName, "汇总流水", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel表成功");
		} catch (Exception e) {
			logger.error("导出excel表异常", e);
			e.printStackTrace();
		}

	}
}
