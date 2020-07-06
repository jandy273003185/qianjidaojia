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

import com.qifenqian.bms.accounting.financequery.bean.UserBalance;
import com.qifenqian.bms.accounting.financequery.mapper.UserBalanceMapper;
import com.qifenqian.bms.accounting.financequery.service.UserBalanceService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

/**
 * 账务管理 - 用户余额
 * 
 * @author shen
 *
 */
@Controller
@RequestMapping(FinanceQueryPath.BASE)
public class UserBalanceController {

	private static Logger logger = LoggerFactory.getLogger(UserBalanceController.class);
	@Autowired
	private UserBalanceService service;

	@Autowired
	private TradeBillService tradeBillService;
	@Autowired
	private UserBalanceMapper userBalanceMapper;

	/**
	 * 用户余额列表
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(FinanceQueryPath.USERBALANCELIST)
	public ModelAndView userBalanceWater(UserBalance queryBean) {
		ModelAndView mv = new ModelAndView(FinanceQueryPath.BASE + FinanceQueryPath.USERBALANCELIST);
		List<UserBalance> userBalanceLists = service.selectUserBalanceList(queryBean);
		UserBalance balanceCount = userBalanceMapper.selectSumBalance();
		mv.addObject("queryBean", queryBean);
		mv.addObject("balanceCount", balanceCount);
		mv.addObject("userBalanceLists", userBalanceLists);
		return mv;
	}

	/**
	 * 导出用户余额
	 * 
	 * @param userBalance
	 * @param request
	 * @param response
	 */
	@RequestMapping(FinanceQueryPath.EXPORTUSERBALANCE)
	public void exportExcelCommerciaBalance(UserBalance userBalance, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出用户报表");

		try {
			List<UserBalance> userBalanceList = service.exportUserBalanceList(userBalance);

			String[] headers = { "客户手机", "用户名称", "余额", "冻结金额", "在途金额", "可用金额", "创建时间" };
			String fileName = DatetimeUtils.dateSecond() + "_用户余额.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(userBalanceList, headers, fileName, "用户余额查询", request);

			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel表成功");
		} catch (Exception e) {
			logger.error("导出excel表异常", e);
			e.printStackTrace();
		}

	}
}
