package com.qifenqian.bms.accounting.financequery;

import java.math.BigDecimal;
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
import com.qifenqian.bms.accounting.financequery.bean.CommerciaBalance;
import com.qifenqian.bms.accounting.financequery.mapper.CommerciaBalanceMapper;
import com.qifenqian.bms.accounting.financequery.service.CommerciaBalanceService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.busswithdrawbill.service.BussWithdrawBillService;

/**
 * 
 * @author shen
 *
 */
@Controller
@RequestMapping(FinanceQueryPath.BASE)
public class CommerciaBalanceController {

	private static Logger logger = LoggerFactory.getLogger(CommerciaBalanceController.class);
	@Autowired
	private CommerciaBalanceService service;

	@Autowired
	private BussWithdrawBillService billServie;
	
	@Autowired
	private TradeBillService tradeBillService;

	@Autowired
	private CommerciaBalanceMapper commerciaBalanceMapper;

	/**
	 * 商户批量提现申请
	 * 
	 * @param commerciaBalance
	 * @return
	 */
	@RequestMapping(FinanceQueryPath.MERCHANTSWITHDRAWALBATCH)
	@ResponseBody
	public String merchantswithdrawalbatch(CommerciaBalance commerciaBalance) {
		JSONObject object = new JSONObject();
		String withdrawArrs = commerciaBalance.getDepositall();
		if (withdrawArrs.equals("") || null == withdrawArrs) {
			object.put("result", "fail");
			object.put("message", "提现对象为空");
			return object.toJSONString();
		}
		try {
			String arr[] = withdrawArrs.split("☺");
			for (int i = 0; i < arr.length; i++) {
				String withdrawInfo[] = arr[i].split("#");
				List<CommerciaBalance> commercia = commerciaBalanceMapper.selectCommerciaBalance(withdrawInfo[0]);
				CommerciaBalance commer = commercia.get(0);
				BigDecimal balance = new BigDecimal(withdrawInfo[2]);
				commer.setPartbalance2(balance);
				billServie.merchantsWithdrawal(commer);
			}
			object.put("result", "success");
			object.put("message", "提现申请成功");

		} catch (Exception e) {
			logger.error("商户批量提现申请异常" + e.getMessage());
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}

		return object.toJSONString();
	}

	/**
	 * 商户申请提现
	 * 
	 * @param commerciaBalance
	 * @return
	 */
	@RequestMapping(FinanceQueryPath.MERCHANTSWITHDRAWAL)
	@ResponseBody
	public String merchantsWithdrawal(CommerciaBalance commerciaBalance) {
		JSONObject object = new JSONObject();
		// 提交申请提现

		if (null == commerciaBalance) {
			object.put("result", "fail");
			object.put("message", "提现对象为空");
		}
		try {
			billServie.merchantsWithdrawal(commerciaBalance);
			object.put("result", "success");
		} catch (Exception e) {
			logger.error("商户提现申请异常:" + e.getMessage());
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}

	/**
	 * 商户余额列表
	 * 
	 * @param commerciaBalance
	 * @param request
	 * @return
	 */
	@RequestMapping(FinanceQueryPath.COMMERCIABALANCELIST)
	public ModelAndView balanceWater(CommerciaBalance queryBean) {
		ModelAndView mv = new ModelAndView(FinanceQueryPath.BASE + FinanceQueryPath.COMMERCIABALANCELIST);
		List<CommerciaBalance> commerciaBalanceLists = service.selectCommerciaBalanceList(queryBean);
		CommerciaBalance commerciaBalanceCount = commerciaBalanceMapper.selectCommerciaBalanceCount();
		mv.addObject("queryBean", queryBean);
		mv.addObject("balanceCount", commerciaBalanceCount);
		mv.addObject("commerciaBalanceLists", JSONObject.toJSON(commerciaBalanceLists));
		return mv;
	}

	/**
	 * 商户余额导出
	 * 
	 * @param commerciaBalance
	 * @param request
	 * @param response
	 */
	@RequestMapping(FinanceQueryPath.EXPORTCOMMERCIABALANCE)
	public void exportExcelCommerciaBalance(CommerciaBalance commerciaBalance, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出商户余额报表");
		try {
			List<CommerciaBalance> excelContent = commerciaBalanceMapper.selectCommerciaBalanceList(commerciaBalance);
			String headers []={"商户编号","商户名称"," 收款账号名", "提现银行卡号","开户银行","支行信息","余额","可用余额","在途金额","可用结算金额","在途结算金额","冻结金额","账户状态"};
			String fileName = DatetimeUtils.dateSecond() + "_商户余额列表.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excelContent, headers, fileName, "商户余额列表", request);

			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel表成功");
		} catch (Exception e) {
			logger.error("导出excel表异常", e);
			e.printStackTrace();
		}

	}
}
