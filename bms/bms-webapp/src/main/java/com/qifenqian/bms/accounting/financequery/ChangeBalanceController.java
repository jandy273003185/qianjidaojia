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

import com.qifenqian.bms.accounting.financequery.bean.ChangeBalance;
import com.qifenqian.bms.accounting.financequery.service.ChangeBalanceService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.platform.common.utils.DateUtils;

@Controller
@RequestMapping(FinanceQueryPath.BASE)
public class ChangeBalanceController {
	private static Logger logger = LoggerFactory.getLogger(ChangeBalanceController.class);
	@Autowired
	private ChangeBalanceService service;

	@Autowired
	private TradeBillService tradeBillService;

	@RequestMapping(FinanceQueryPath.CHANGEBALANCELIST)

	public ModelAndView changeBalance(ChangeBalance changeBalance, HttpServletRequest request) {

		if (changeBalance.getWorkDate() != null && !changeBalance.getWorkDate().equals("")) {
		} else {
			String nowDate = DateUtils.getDateStr8();
			changeBalance.setWorkDate(nowDate);
		}
		ModelAndView mv = new ModelAndView(FinanceQueryPath.BASE + FinanceQueryPath.CHANGEBALANCELIST);
		List<ChangeBalance> changeBalanceLists = service.changeBalanceList(changeBalance);
		mv.addObject("changeBalanceLists", changeBalanceLists);
		mv.addObject("workDate", changeBalance.getWorkDate());
		return mv;
	}
	/**
	 * 导出余额变动报表
	 * @param changeBalance
	 * @param request
	 * @param response
	 */
	@RequestMapping(FinanceQueryPath.EXPORTCHANGEBALANCE)
	public void exportExcelChangeBalance(ChangeBalance changeBalance, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出余额变动流水报表");
		if (changeBalance.getWorkDate() != null && !changeBalance.getWorkDate().equals("")) {
		} else {
			String nowDate = DateUtils.getDateStr8();
			changeBalance.setWorkDate(nowDate);
		}
		try {
			List<ChangeBalance> excelContent = service.changeBalanceList(changeBalance);
			String headers[] = { "会计科目", "期初余额", " 借", "贷", "期末余额", "对账日期" };
			String fileName = DatetimeUtils.dateSecond() + "_余额变动.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excelContent, headers, fileName, "余额变动流水报表", request);

			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel表成功");
		} catch (Exception e) {
			logger.error("导出excel表异常", e);
			e.printStackTrace();
		}

	}

}
