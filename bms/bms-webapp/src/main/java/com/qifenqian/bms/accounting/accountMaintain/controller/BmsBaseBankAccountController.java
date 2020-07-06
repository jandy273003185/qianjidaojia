package com.qifenqian.bms.accounting.accountMaintain.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.accountMaintain.bean.BmsBaseBankAccount;
import com.qifenqian.bms.accounting.accountMaintain.service.BmsBaseBankAccountService;

@Controller
@RequestMapping(BmsBaseBankAccountPath.BASE)
public class BmsBaseBankAccountController {

	public static Logger logger = LoggerFactory.getLogger(BmsBaseBankAccountController.class);
	
	@Autowired
	private BmsBaseBankAccountService bmsBaseBankAccountService;
	
	/**
	 * 我方账户信息查询
	 * @param bean
	 * @return
	 */
	@RequestMapping(BmsBaseBankAccountPath.LIST)
	public ModelAndView getList(BmsBaseBankAccount bean){
		logger.info("**********我方账户信息查询***********");
		ModelAndView mv = new ModelAndView(BmsBaseBankAccountPath.BASE+BmsBaseBankAccountPath.LIST);
		List<BmsBaseBankAccount> beanList = bmsBaseBankAccountService.selectBaseBankAccountList(bean);
		mv.addObject("beanJson", JSONObject.toJSONString(beanList));
		mv.addObject("beanList", beanList);
		mv.addObject("bean", bean);
		return mv;
	}
	
	/**
	 * 新增账户信息
	 * @param bean
	 * @return
	 */
	@RequestMapping(BmsBaseBankAccountPath.ADD)
	@ResponseBody
	public String addAccount(BmsBaseBankAccount bean){
		logger.info("**********新增账户信息**********");
		
		JSONObject jo = new JSONObject();
		try {
			bmsBaseBankAccountService.addBaseBankAccount(bean);
			jo.put("result", "SUCCESS");
			jo.put("message", "账户信息添加成功");
		} catch (Exception e) {
			jo.put("result", "fail");
			jo.put("message", e.getMessage());
		}
		return jo.toJSONString();
	}
	
	/**
	 * 修改账户信息
	 * @param bean
	 * @return
	 */
	@RequestMapping(BmsBaseBankAccountPath.UPDATE)
	@ResponseBody
	public String updateAccount(BmsBaseBankAccount bean){
		logger.info("**********修改账户信息**********");
		
		JSONObject jo = new JSONObject();
		try {
			bmsBaseBankAccountService.updateBaseBankAccount(bean);
			jo.put("result", "SUCCESS");
			jo.put("message", "账户信息修改成功");
		} catch (Exception e) {
			jo.put("result", "FAIL");
			jo.put("message", e.getMessage());
		}
		return jo.toJSONString();
	}
}
