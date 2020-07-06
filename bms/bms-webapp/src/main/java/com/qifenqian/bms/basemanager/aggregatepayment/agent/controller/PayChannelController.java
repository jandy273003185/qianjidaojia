package com.qifenqian.bms.basemanager.aggregatepayment.agent.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayChannelBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.service.PayChannelService;


@Controller
@RequestMapping(PayChannelPath.BASE)
public class PayChannelController {
	
	private static Logger logger = LoggerFactory.getLogger(PayChannelController.class);
	
	@Autowired
	private PayChannelService payChannelService;
	
	/***
	 * 支付渠道列表
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(PayChannelPath.PAYCHANNELLIST)
	public ModelAndView list(PayChannelBean queryBean) {
		logger.info("查询支付渠道列表");
		ModelAndView mv = new ModelAndView(PayChannelPath.BASE + PayChannelPath.PAYCHANNELLIST);
		
		List<PayChannelBean> list = payChannelService.getPayChannelList(queryBean);
		mv.addObject("payChannelList", list);
		mv.addObject("queryBean", queryBean);
		return mv;
	}
	
	@RequestMapping(PayChannelPath.PAYCHANNELADD)
	@ResponseBody
	public String add(HttpServletRequest httpRequest,PayChannelBean payChannelBean) {
		logger.info("添加支付渠道");
		JSONObject jo = new JSONObject();
		if("post".equalsIgnoreCase(httpRequest.getMethod())){
			try {
				payChannelService.addPayChannelInfo(payChannelBean);
				jo.put("result", "SUCCESS");
				jo.put("message", "添加成功");
			} catch (Exception e) {
				jo.put("result", "FAIL");
				jo.put("message", "添加失败");
			}
			
		}
		return jo.toJSONString();
	}
	
	@RequestMapping(PayChannelPath.PAYCHANNELUPDATE)
	@ResponseBody
	public String update(HttpServletRequest httpRequest,PayChannelBean payChannelBean) {
		logger.info("修改渠道");
		JSONObject jo = new JSONObject();
		if("post".equalsIgnoreCase(httpRequest.getMethod())){
			try {
				payChannelService.updatePayChannelInfo(payChannelBean);
				jo.put("result", "SUCCESS");
				jo.put("message", "修改成功");
			} catch (Exception e) {
				jo.put("result", "FAIL");
				jo.put("message",e.getMessage());
			}
		}
		return jo.toJSONString();
	}
	
	@RequestMapping(PayChannelPath.PAYCHANNELDELETE)
	@ResponseBody
	public String delete(HttpServletRequest httpRequest,String payChannelCode) {
		logger.info("删除支付渠道");
		JSONObject jo = new JSONObject();
		if("post".equalsIgnoreCase(httpRequest.getMethod())){
			try {
				payChannelService.deletePayChannelInfo(payChannelCode);
				jo.put("result", "SUCCESS");
				jo.put("message", "删除成功");
			} catch (Exception e) {
				jo.put("result", "FAIL");
				jo.put("message", "删除失败");
			}
		}
		return jo.toJSONString();
	}
	
}
