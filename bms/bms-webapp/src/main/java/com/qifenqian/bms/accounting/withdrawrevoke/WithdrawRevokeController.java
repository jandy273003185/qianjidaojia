package com.qifenqian.bms.accounting.withdrawrevoke;

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

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.withdraw.bean.Withdraw;
import com.qifenqian.bms.accounting.withdraw.bean.WithdrawExcel;
import com.qifenqian.bms.accounting.withdraw.bean.WithdrawRequestBean;
import com.qifenqian.bms.accounting.withdrawrevoke.service.WithdrawRevokeService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

@Controller
@RequestMapping(WithdrawRevokePath.BASE)
public class WithdrawRevokeController {

	private Logger logger = LoggerFactory.getLogger(WithdrawRevokeController.class);

	@Autowired
	private WithdrawRevokeService withdrawRevokeService;
	
	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 提现撤销列表
	 * 
	 * @return 
	 */
	@RequestMapping(WithdrawRevokePath.WITHDRAWREVOKE)
	public ModelAndView withdrawRevoke(WithdrawRequestBean withdrawBean) {
		ModelAndView mv = new ModelAndView(WithdrawRevokePath.BASE + WithdrawRevokePath.WITHDRAWREVOKE);
		List<Withdraw> withdrawRevokeList = withdrawRevokeService.selectWithdrawRevokeList(withdrawBean);
		mv.addObject("withdrawRevokeList", withdrawRevokeList);
		mv.addObject("withdrawBean", withdrawBean);
		mv.addObject("withdraws", JSONObject.toJSONString(withdrawRevokeList));
		return mv;
	}
	
	
	/**
	 * 导出提现撤销信息
	 * 
	 * @param recharge
	 */
	@RequestMapping(WithdrawRevokePath.WITHDRAWREVOKEEXPORT)
	public void withdrawRevokeExport(WithdrawRequestBean withdrawRequestBean, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<WithdrawExcel> withdrawExcel = withdrawRevokeService.selectWithdrawRevokeExcel(withdrawRequestBean);
			String[] headers = { "七分钱提现流水号","提现撤销关联流水号","平台流水号", "客户手机", "客户名", "收款名", "提现银行卡号", "开户行", "支行信息", "提现金额", "手续费", "提现申请日期", "提现状态", "审核状态", "审核人", "最后修改时间", "核销状态", "核销人", "核销时间" };
			String fileName = DatetimeUtils.dateSecond() + "_提现撤销信息.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(withdrawExcel, headers, fileName, "提现撤销信息", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel提现撤销信息成功");
		} catch (Exception e) {
			logger.error("导出excel提现撤销信息异常", e);
		}

	}

}
