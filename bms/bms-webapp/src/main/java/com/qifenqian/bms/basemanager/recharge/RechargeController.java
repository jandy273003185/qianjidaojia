package com.qifenqian.bms.basemanager.recharge;

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

import com.qifenqian.bms.basemanager.recharge.bean.RechargeBean;
import com.qifenqian.bms.basemanager.recharge.bean.RechargeExcel;
import com.qifenqian.bms.basemanager.recharge.service.RechargeService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

@Controller
@RequestMapping(RechargePath.BASE)
public class RechargeController {

	private static Logger logger = LoggerFactory.getLogger(RechargeController.class);

	@Autowired
	private RechargeService rechargeService;
	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 查询充值记录
	 * 
	 * @param
	 * @return
	 */
	@RequestMapping(RechargePath.LIST)
	public ModelAndView list(RechargeBean recharge) {

		logger.info("充值记录查询");
		
		ModelAndView model = new ModelAndView(RechargePath.BASE + RechargePath.LIST);
		
		List<RechargeBean> list = rechargeService.selectRecharge(recharge);

		model.addObject("queryBean", recharge);

		model.addObject("recharges", list);

		return model;
	}

	/**
	 * 导出充值信息
	 * 
	 * @param recharge
	 */
	@RequestMapping(RechargePath.RECHARGEEXPORT)
	public void exportRechargeExcel(RechargeBean recharge, HttpServletRequest request, HttpServletResponse response) {

		logger.info("导出充值信息");

		try {
			String[] headers = { "七分钱流水号","商户订单号","银联流水号", "交广科技订单号", "手机号码","对账日期", "充值开始时间", "充值结束时间", "用户名", "充值金额", "结算金额", "充值方式","充值类型" ,"充值状态" ,"交易类型"};
			List<RechargeExcel> rechargeExcel = rechargeService.selectRechargeExcel(recharge);
			String fileName = DatetimeUtils.dateSecond() + "_充值信息.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(rechargeExcel, headers, fileName, "充值信息", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel充值信息成功");
		} catch (Exception e) {
			logger.error("导出excel充值信息异常", e);
		}

	}

}
