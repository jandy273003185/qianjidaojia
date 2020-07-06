package com.qifenqian.bms.basemanager.merchantwithdraw;

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
import com.qifenqian.bms.basemanager.merchantwithdraw.bean.MerchantWithdraw;
import com.qifenqian.bms.basemanager.merchantwithdraw.bean.MerchantWithdrawExcel;
import com.qifenqian.bms.basemanager.merchantwithdraw.service.MerchantWithdrawService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;

@Controller
@RequestMapping(MerchantWithdrawPath.BASE)
public class MerchantWithdrawController {

	private Logger logger = LoggerFactory.getLogger(MerchantWithdrawController.class);

	@Autowired
	private MerchantWithdrawService merchantWithdrawService;
	
	@Autowired
	private TradeBillService tradeBillService;

	/**
	 * 商户提现列表
	 * @param withdrawBean
	 * @return
	 */
	@RequestMapping(MerchantWithdrawPath.LIST)
	public ModelAndView list(MerchantWithdraw withdrawBean) {
		ModelAndView mv = new ModelAndView(MerchantWithdrawPath.BASE + MerchantWithdrawPath.LIST);
		List<MerchantWithdraw> withdrawList = merchantWithdrawService.selectMerchantWithdrawList(withdrawBean);
		mv.addObject("withdrawList", withdrawList);
		mv.addObject("withdrawBean", withdrawBean);
		mv.addObject("withdraws", JSONObject.toJSONString(withdrawList));
		return mv;
	}
	
	/**
	 * 导出商户提现信息
	 * @param withdraw
	 * @param request
	 * @param response
	 */
	@RequestMapping(MerchantWithdrawPath.MERCHANTWITHDRAWEXPORT)
	public void merchantWithdrawExport(MerchantWithdraw withdraw, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<MerchantWithdrawExcel> withdrawExcel = merchantWithdrawService.selectWithdrawExcelByMerchant(withdraw);
			String[] headers = { "七分钱提现流水号", "商户编号", "商户名", "收款名", "提现银行卡号", "开户行","提现金额", "手续费", "提现申请日期", "审核状态","提现状态","核销状态", "审核人", "审核时间", "核销人","核销时间"};
			String fileName = DatetimeUtils.dateSecond() + "_商户提现结算信息.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(withdrawExcel, headers, fileName, "提现结算信息", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel提现结算信息成功");
		} catch (Exception e) {
			logger.error("导出excel提现结算信息异常", e);
		}
	}

	/**
	 * 提现审核通过
	 * @param request
	 * @param withdraw
	 * @return
	 */
	@ResponseBody
	@RequestMapping(MerchantWithdrawPath.MERCHANTWITHDRAWAUDITPASS)
	public String merchantWithdrawAuditPass(HttpServletRequest request, MerchantWithdraw withdraw) {

		logger.info("审核通过处理...");
		JSONObject json = new JSONObject();
		try {
			json=merchantWithdrawService.merchantWithdrawAuditPass(withdraw);
			logger.info("审核通过处理成功");

		} catch (Exception e) {
			logger.error("审核通过处理异常", e);
			json.put("result", "FAIL");
			json.put("message", e.getMessage());
		}
		return json.toJSONString();

	}

	/**
	 * 提现审核不通过
	 * @param request
	 * @param withdraw
	 * @return
	 */
	@ResponseBody
	@RequestMapping(MerchantWithdrawPath.MERCHANTWITHDRAWAUDITNOPASS)
	public String merchantWithdrawAuditNoPass(HttpServletRequest request, MerchantWithdraw withdraw) {

		logger.info("审核不通过处理...");
		JSONObject json = new JSONObject();
		try {
			json = merchantWithdrawService.merchantWithdrawAuditNoPass(withdraw);
			logger.info("审核不通过处理成功");

		} catch (Exception e) {
			logger.error("审核不通过处理异常", e);
			json.put("result", "FAIL");
			json.put("message", e.getMessage());
		}
		return json.toJSONString();

	}

	/**
	 * 提现核销
	 * @param merchantWithdraw
	 * @return
	 */
	@RequestMapping(MerchantWithdrawPath.MERCHANTWITHDRAWVERIFICATION)
	@ResponseBody
	public String merchantWithdrawVerification(MerchantWithdraw merchantWithdraw) {
		logger.info("商户提现核销");
		JSONObject json = new JSONObject();
		
		if(null==merchantWithdraw||merchantWithdraw.getWithdrawSn().equals("")){
			json.put("result", "FAIL");
			json.put("message", "提现核销对象不存在");
			return json.toJSONString();
		}
		try {
			merchantWithdrawService.merchantWithdrawVerification(merchantWithdraw);
			json.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.info("提现核销异常：" + e.getMessage());
			json.put("message", e.getMessage());
		}
		return json.toJSONString();
	}
}
