package com.qifenqian.bms.accounting.unnamedProcessing.clearjgkjtrade;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.unnamedProcessing.clearjgkjtrade.bean.ClearJgkjTrade;
import com.qifenqian.bms.accounting.unnamedProcessing.clearjgkjtrade.service.ClearJgkjTradeService;

/****
 * 交广科技交易未明列表
 * 
 * @author shen
 * 
 */
@Controller
@RequestMapping(ClearJgkjTradePath.BASE)
public class ClearJgkjTradeController {

	private static Logger logger = LoggerFactory.getLogger(ClearJgkjTradeController.class);

	@Autowired
	private ClearJgkjTradeService service;

	@RequestMapping(ClearJgkjTradePath.LIST)
	public ModelAndView list(ClearJgkjTrade clearJgkjTrade) {
		logger.info("交广科技交易未明列表查询对象：{}", JSONObject.toJSONString(clearJgkjTrade, true));
		ModelAndView mv = new ModelAndView(ClearJgkjTradePath.BASE + ClearJgkjTradePath.LIST);
		List<ClearJgkjTrade> list = service.selectClearJgkjTradeList(clearJgkjTrade);
		mv.addObject("clearJgkjTradeList", JSONObject.toJSON(list));
		mv.addObject("queryBean", clearJgkjTrade);
		return mv;
	}
}
