package com.qifenqian.bms.basemanager.bank;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.service.BankService;
import com.qifenqian.bms.basemanager.utils.StrCombineSplit;
import com.qifenqian.bms.common.bean.AjaxJson;

@Controller
@RequestMapping(BankPath.BASE)
public class BankController {

	private static Logger logger = LoggerFactory.getLogger(BankController.class);

	@Autowired
	private BankService bankService;

	/**
	 * 显示银行信息列表
	 * 
	 * @param bank
	 * @return
	 */
	@RequestMapping(BankPath.LIST)
	public ModelAndView list(Bank bank) {
		ModelAndView model = new ModelAndView(BankPath.BASE + BankPath.LIST);
		//拆分银行名称，增加匹配度。
		String bankName = bank.getBankName();
		if(bankName!=null && !bankName.equals("")){
			bank.setBankName(StrCombineSplit.splitStr(bank.getBankName()));
		}
		List<Bank> banks = bankService.selectBanks(bank);
		bank.setBankName(bankName);
		model.addObject("queryBean", bank);
		model.addObject("bankList", JSONObject.toJSON(banks));
		return model;
	}

	/**
	 * 删除银行信息
	 * 
	 * @param bankCode
	 * @return
	 */
	@RequestMapping(BankPath.DELETE)
	@ResponseBody
	public String delete(String bankCode) {

		AjaxJson aj = new AjaxJson();

		try {
			logger.info("请求删除银行[{}]信息", bankCode);
			bankService.deleteBankByCode(bankCode);
			aj.setMessage("删除成功");
			aj.setSuccess(true);
		} catch (Exception e) {
			logger.error("删除异常", e);
			aj.setMessage(e.getMessage());
			aj.setSuccess(false);
		}

		return JSONObject.toJSONString(aj);

	}

	/**
	 * 增加银行
	 * 
	 * @param bank
	 */
	@RequestMapping(BankPath.ADD)
	@ResponseBody
	public String addBank(Bank bank) {

		logger.info("增加银行");

		JSONObject jsonObject = new JSONObject();

		try {
			
			Bank bankResult  = 	bankService.selectBankByBankCode(bank.getBankCode());
			if(null != bankResult ){
				jsonObject.put("result", "fail");
				jsonObject.put("message", "该支付系统行号已经占用");
			}else{
				bankService.insertBank(bank);
				jsonObject.put("result", "success");
			}
			
			
		} catch (Exception e) {
			logger.error("增加银行出错：", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}

		return jsonObject.toJSONString();
	}

	/**
	 * 更新银行
	 * 
	 * @param bank
	 * @return
	 */
	@RequestMapping(BankPath.UPDATE)
	@ResponseBody
	public String updateBank(Bank bank) {
		logger.info("更新银行信息");
		JSONObject jsonObject = new JSONObject();
		try {
			bankService.updateBank(bank);
			jsonObject.put("result", "success");
			logger.info("银行更新完成！");
		} catch (Exception e) {
			logger.error("更新银行未成功", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}

		return jsonObject.toJSONString();

	}
	
	/**
	 * 校验是否存在支付系统行号
	 * @param bank
	 * @return
	 */
	@RequestMapping(BankPath.VERIFY)
	@ResponseBody
	public String verifyBank(Bank bank) {
		logger.info("校验校验是否存在支付系统行号[{}]",bank.getBankCode());
		JSONObject jsonObject = new JSONObject();
		try {
				
			Bank bankResult  = 	bankService.selectBankByBankCode(bank.getBankCode());
			if(null != bankResult ){
				jsonObject.put("result", "fail");
				jsonObject.put("message", "该支付系统行号已经占用");
			}else{
				jsonObject.put("result", "success");
			}
			
		} catch (Exception e) {
			logger.error("校验异常", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}

		return jsonObject.toJSONString();

	}
	
	
}
