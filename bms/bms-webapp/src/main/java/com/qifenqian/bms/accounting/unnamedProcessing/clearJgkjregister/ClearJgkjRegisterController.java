package com.qifenqian.bms.accounting.unnamedProcessing.clearJgkjregister;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.unnamedProcessing.clearJgkjregister.bean.ClearJgkjRegister;
import com.qifenqian.bms.accounting.unnamedProcessing.clearJgkjregister.service.ClearJgkjRegisterService;

/***
 * 交广科技开户未明列表
 * 
 * @author shen
 * 
 */
@Controller
@RequestMapping(ClearJgkjRegisterPath.BASE)
public class ClearJgkjRegisterController {

	private static Logger logger = LoggerFactory.getLogger(ClearJgkjRegisterController.class);

	@Autowired
	private ClearJgkjRegisterService service;

	@RequestMapping(ClearJgkjRegisterPath.LIST)
	public ModelAndView list(ClearJgkjRegister clearJgkjRegister) {
		logger.info("交广科技开户未明列表查询对象：{}", JSONObject.toJSONString(clearJgkjRegister, true));
		ModelAndView mv = new ModelAndView(ClearJgkjRegisterPath.BASE + ClearJgkjRegisterPath.LIST);
		mv.addObject("clearjgkjregisterList",
				JSONObject.toJSON(service.selectClearJgkjRegisterByList(clearJgkjRegister)));
		mv.addObject("queryBean", clearJgkjRegister);
		return mv;
	}
}
