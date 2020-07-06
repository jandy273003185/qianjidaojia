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

import com.qifenqian.bms.accounting.financequery.bean.RealTimeBussBalanceWater;
import com.qifenqian.bms.accounting.financequery.bean.RealTimeCustBalanceWater;
import com.qifenqian.bms.accounting.financequery.mapper.RealTimeBalanceWaterMapper;
import com.qifenqian.bms.accounting.financequery.service.RealTimeBalanceWaterService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

/**
 * 
 * @author shen
 *
 */
@Controller
@RequestMapping(FinanceQueryPath.BASE)
public class RealTimeBalanceController {

	private static Logger logger = LoggerFactory.getLogger(RealTimeBalanceController.class);

	@Autowired
	private RealTimeBalanceWaterService realTimeBalanceWaterService;

	@Autowired
	private RealTimeBalanceWaterMapper realTimeBalanceWaterMapper;

	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 查询客户流水
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(FinanceQueryPath.BALANCEWATERLIST)
	public ModelAndView custBalanceWater(RealTimeCustBalanceWater queryBean) {

		ModelAndView mv = new ModelAndView(FinanceQueryPath.BASE + FinanceQueryPath.BALANCEWATERLIST);
		List<RealTimeCustBalanceWater> balanceWaterList = realTimeBalanceWaterService.selectCustBalanceWaterList(queryBean);
		mv.addObject("balanceWaterList", balanceWaterList);
		mv.addObject("queryBean", queryBean);
		return mv;
	}

	/**
	 * 导出客户流水报表
	 * 
	 * @param custWater
	 * @param request
	 * @param response
	 */
	@RequestMapping(FinanceQueryPath.EXPORTBALANCEWATERLIST)
	public void exportExcelCustBalanceWater(RealTimeCustBalanceWater custWater, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出客户流水报表");
		try {
			List<RealTimeCustBalanceWater> custBalanceWater = realTimeBalanceWaterMapper.selectCustBalanceWaterList(custWater);
			String headers[] = { "账户名称", "业务类型", "余额方向", "期初余额", "交易金额", "在途金额变动", "可用余额变动","期末余额", "对账日期","交易时间" };
			String fileName = DatetimeUtils.dateSecond() + "_客户流水.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(custBalanceWater, headers, fileName, "客户流水报表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel表成功");
		} catch (Exception e) {
			logger.error("导出excel表异常", e);
			e.printStackTrace();
		}

	}

	/**
	 * 查询商户流水
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(FinanceQueryPath.REALTIMELIST)
	public ModelAndView bussBalanceWater(RealTimeBussBalanceWater queryBean) {

		ModelAndView mv = new ModelAndView(FinanceQueryPath.BASE + FinanceQueryPath.REALTIMELIST);
		List<RealTimeBussBalanceWater> balanceWaterList = realTimeBalanceWaterService.selectBussBalanceWaterList(queryBean);
		mv.addObject("balanceWaterList", balanceWaterList);
		mv.addObject("queryBean", queryBean);
		return mv;
	}

	/**
	 * 导出商户流水报表
	 * 
	 * @param custWater
	 * @param request
	 * @param response
	 */
	@RequestMapping(FinanceQueryPath.EXPORTREALTIMEBALANCE)
	public void exportExcelBussBalanceWater(RealTimeBussBalanceWater bussWater, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出商户流水报表");
		try {
			List<RealTimeBussBalanceWater> custBalanceWater = realTimeBalanceWaterMapper.selectBussBalanceWaterList(bussWater);
			String headers[] = { "账户名称", "业务类型", "余额方向", "期初余额", "交易金额", "在途金额变动", "可用余额变动", "期末余额","对账日期", "交易时间" };
			String fileName = DatetimeUtils.dateSecond() + "_商户流水.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(custBalanceWater, headers, fileName, "商户流水报表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel表成功");
		} catch (Exception e) {
			logger.error("导出excel表异常", e);
			e.printStackTrace();
		}

	}

}
