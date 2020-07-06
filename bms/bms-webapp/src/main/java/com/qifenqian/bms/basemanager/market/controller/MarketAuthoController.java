package com.qifenqian.bms.basemanager.market.controller;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
/**
 * 市场部权限Controller
 * @author hongjiagui
 */
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.market.bean.MarketRole;
import com.qifenqian.bms.basemanager.market.bean.UpdateMarketRole;
import com.qifenqian.bms.basemanager.market.service.MarketAuthoService;

@Controller
@RequestMapping(MarketAuthoControllerPath.BASE)
public class MarketAuthoController {
	
	@Autowired
	private MarketAuthoService marketAuthoService;
	
	private Logger logger = LoggerFactory.getLogger(MarketAuthoController.class);
	
	@RequestMapping(MarketAuthoControllerPath.LIST)
	public ModelAndView List(MarketRole queryBean) {
		ModelAndView mv = new ModelAndView(MarketAuthoControllerPath.BASE + MarketAuthoControllerPath.LIST);
		
		//获取市场部总裁与副总裁的信息列表
		List<MarketRole> marketRoleList = marketAuthoService.listInfo(queryBean);
		//获取市场部总裁的roleId
		Integer presidentId = marketAuthoService.getRoleIdByName("市场部总裁");
		//获取市场部副总裁的roleId
		Integer vicePresidentId = marketAuthoService.getRoleIdByName("市场部副总裁");
		
		mv.addObject("marketRoleList", JSONObject.toJSON(marketRoleList));
		mv.addObject("presidentId",presidentId);
		mv.addObject("vicePresidentId",vicePresidentId);
		mv.addObject("queryBean", queryBean);
		
		return mv;
	}

	/**
	 * 保存用户信息
	 */
	@ResponseBody
	public String saveChannelDetail(MarketRole marketRole) {
		String result = marketAuthoService.saveInfo(marketRole);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json.toString();
	}
	
	/**
	 * 更新用户信息
	 */
	@ResponseBody
	public String deleteChannelDetail(UpdateMarketRole marketRole) {
		logger.info("市场部权限更新用户信息");
		String result;
		try {
			result = marketAuthoService.updateInfo(marketRole);
		}catch(Exception e) {
			result = e.toString();
			logger.error("市场部权限更新用户信息异常:"+e.toString());
		}
	
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json.toString();
	}
	/**
	 * 删除用户信息
	 */
	@ResponseBody
	public String deleteChannelDetail(MarketRole marketRole) {
		String result = marketAuthoService.deleteInfo(marketRole);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json.toString();
	}
}
