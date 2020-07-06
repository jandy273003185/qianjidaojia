package com.qifenqian.bms.accounting.withdraw;

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
import com.qifenqian.bms.accounting.withdraw.bean.Withdraw;
import com.qifenqian.bms.accounting.withdraw.bean.WithdrawExcel;
import com.qifenqian.bms.accounting.withdraw.bean.WithdrawRequestBean;
import com.qifenqian.bms.accounting.withdraw.mapper.WithdrawMapper;
import com.qifenqian.bms.accounting.withdraw.service.WithdrawService;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Controller
@RequestMapping(WithdrawPath.BASE)
public class WithdrawController {

	private Logger logger = LoggerFactory.getLogger(WithdrawController.class);

	@Autowired
	private WithdrawService withdrawService;
	@Autowired
	private TradeBillService tradeBillService;
	@Autowired
	private WithdrawMapper withdrawMapper;

	/**
	 * 提现列表
	 * 
	 * @return 提现页面
	 */
	@RequestMapping(WithdrawPath.WITHDRAWLIST)
	public ModelAndView withdrawList(WithdrawRequestBean withdrawBean) {
		
		logger.info(" 提现列表查询对象 {}", JSONObject.toJSONString(withdrawBean, true));
		ModelAndView mv = new ModelAndView(WithdrawPath.BASE + WithdrawPath.WITHDRAWLIST);
		List<Withdraw> withdrawList = withdrawService.selectCustomerWithdrawList(withdrawBean);
		mv.addObject("withdrawList", withdrawList);
		mv.addObject("withdrawBean", withdrawBean);
		mv.addObject("withdraws", JSONObject.toJSONString(withdrawList));
		return mv;
	}

	/**
	 * 导出提现信息
	 * 
	 * @param recharge
	 */
	@RequestMapping(WithdrawPath.WITHDRAWEXPORT)
	public void withdrawExport(WithdrawRequestBean withdrawRequestBean, HttpServletRequest request,
			HttpServletResponse response) {
		
		try {
			List<WithdrawExcel> withdrawExcel = withdrawService.selectWithdrawExcelByUser(withdrawRequestBean);
			String[] headers = { "七分钱提现流水号", "关联流水号", "平台流水号","金蝶清算编号", "客户手机", "客户名", "收款名", "提现银行卡号", "开户行", "支行信息", "提现金额", "手续费",
					"提现申请日期", "提现状态", "审核状态", "审核人", "审核时间", "核销状态", "核销人", "核销时间" };
			String fileName = DatetimeUtils.dateSecond() + "_提现结算信息.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(withdrawExcel, headers, fileName, "提现结算信息",
					request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel提现结算信息成功");
		} catch (Exception e) {
			logger.error("导出excel提现结算信息异常", e);
		}

	}

	/**
	 * 提现审核通过
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(WithdrawPath.WITHDRAWAUDITPASS)
	public String withdrawAuditPass(HttpServletRequest request, Withdraw withdraw) {
		JSONObject json = new JSONObject();

		logger.info("审核通过处理对象 {}", JSONObject.toJSONString(withdraw, true));
		try {
			/** 审核通过-提现通过提交核心 **/
			
			withdraw.setWithdrawState(Constant.WITHDRAW_CORE_SUBMIT);
			withdraw.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			withdrawMapper.custWithdrawAudit(withdraw);

			json = withdrawService.withdrawAuditPass(withdraw);
		} catch (Exception e) {
			logger.error("审核通过处理异常", e);
			json.put("result", "FAIL");
			json.put("message", e.getMessage());
		}
		return json.toJSONString();

	}

	/**
	 * 提现审核不通过
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(WithdrawPath.WITHDRAWAUDITNOPASS)
	public String withdrawAuditNoPass(HttpServletRequest request, Withdraw withdraw) {
		JSONObject json = new JSONObject();

		logger.info("审核不通过处理对象 {}", JSONObject.toJSONString(withdraw, true));
		try {
			/** 审核不通过-撤销 **/
			
			withdraw.setWithdrawState(Constant.WITHDRAW_REVOKE_CORE_SUBMIT);
			withdraw.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			withdrawMapper.custWithdrawAudit(withdraw);

			json = withdrawService.custWithdrawAuditNoPass(withdraw);
		} catch (Exception e) {
			logger.error("审核不通过处理异常", e);
			json.put("result", "FAIL");
			json.put("message", e.getMessage());
		}
		return json.toJSONString();

	}

	/**
	 * 提现核销
	 */
	@RequestMapping(WithdrawPath.WITHDRAWVERIFICATION)
	@ResponseBody
	public String withdrawVerification(WithdrawRequestBean withdraw) {
		JSONObject json = new JSONObject();

		logger.info("提现核销处理对象 {}", JSONObject.toJSONString(withdraw, true));
		try {
			withdrawService.withdrawVerification(withdraw);
			json.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.info("提现核销异常：" + e.getMessage());
			json.put("message", e.getMessage());
		}
		return json.toJSONString();
	}
	
	
	/**提现退回**/
	@ResponseBody
	@RequestMapping(WithdrawPath.WITHDRAW_REFUND)
	public String withdrawRefund(HttpServletRequest request, Withdraw withdraw) {
		JSONObject json = new JSONObject();
		logger.info("提现退回处理对象 {}", JSONObject.toJSONString(withdraw, true));
		try {
			/** 提现退回提交核心 **/
			withdraw.setWithdrawState(Constant.WITHDRAW_REFUND_CORE_SUBMIT);
			withdraw.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			withdrawMapper.custWithdrawAudit(withdraw);

			json = withdrawService.withdrawRefund(withdraw);
		} catch (Exception e) {
			logger.error("提现退回处理异常", e);
			json.put("result", "FAIL");
			json.put("message", e.getMessage());
		}
		return json.toJSONString();

	}
}
