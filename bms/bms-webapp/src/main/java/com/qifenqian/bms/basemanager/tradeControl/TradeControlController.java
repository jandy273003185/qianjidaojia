package com.qifenqian.bms.basemanager.tradeControl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.tradeControl.bean.TradeControl;
import com.qifenqian.bms.basemanager.tradeControl.service.TradeControlService;
/***
 *交易控制
 * @author shen
 *
 */
@Controller
@RequestMapping(TradeControlPath.BASE)
public class TradeControlController {
	
	private static Logger logger = LoggerFactory.getLogger(TradeControlController.class);
	@Autowired
	private TradeControlService tradeControlService;
	
	/**
	 * 展示交易控制信息
	 * @param tradeControl
	 */
	@RequestMapping(TradeControlPath.LIST)
	public ModelAndView list(TradeControl tradeControl){
		
		List<TradeControl> tradeControlList = tradeControlService.selectAll(tradeControl);
		
		ModelAndView model = new ModelAndView(TradeControlPath.BASE+TradeControlPath.LIST );
		
		model.addObject("tradeControls", tradeControlList);
		model.addObject("queryBean", tradeControl);
		model.addObject("tradeControlList", JSONObject.toJSONString(tradeControlList));
		
		return model;
	}
	
	/**
	 * 增加
	 * 
	 * @param rule
	 * @return
	 */
	@RequestMapping(TradeControlPath.ADD)
	@ResponseBody
	public String add(TradeControl tradeControl) {
		JSONObject js = new JSONObject();
		try {
			logger.info("请求保存交易控制信息：[{}]", JSONObject.toJSONString(tradeControl, true));
			TradeControl suitableTradeControl = tradeControlService.selectTradeControl(tradeControl);
			if(suitableTradeControl !=null){
				js.put("result", "FAIL");
				js.put("message", "该交易控制类型已经存在");
				return js.toJSONString();
			}
			
			tradeControlService.addTradeControl(tradeControl);
				js.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("请求增加交易控制信息异常", e);
			js.put("result", "FAIL");
			js.put("message", e.getMessage());
		}
		return js.toJSONString();

	}
	
	/**
	 * 修改
	 * 
	 * @param rule
	 * @return
	 */
	@RequestMapping(TradeControlPath.UPDATE)
	@ResponseBody
	public String edit(TradeControl tradeControl) {
		JSONObject js = new JSONObject();
		try {
			logger.info("请求修改交易控制信息：[{}]", JSONObject.toJSONString(tradeControl, true));
			
			tradeControlService.editTradeControl(tradeControl);
				js.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("请求修改交易控制信息异常", e);
			js.put("result", "FAIL");
			js.put("message", e.getMessage());
		}
		return js.toJSONString();

	}
	
	/**
	 * 修改
	 * 
	 * @param rule
	 * @return
	 */
	@RequestMapping(TradeControlPath.DELETE)
	@ResponseBody
	public String delete(TradeControl tradeControl) {
		JSONObject js = new JSONObject();
		try {
			logger.info("请求删除交易控制信息： [{}]", JSONObject.toJSONString(tradeControl, true));
			
			tradeControlService.deleteTradeControl(tradeControl);
				js.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("请求删除交易控制信息异常", e);
			js.put("result", "FAIL");
			js.put("message", e.getMessage());
		}
		return js.toJSONString();

	}
	
}
