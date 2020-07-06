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
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayProdBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.service.PayProdService;



@Controller
@RequestMapping(PayProdPath.BASE)
public class PayProdController {
	
	private static Logger logger = LoggerFactory.getLogger(PayProdController.class);
	
	@Autowired
	private PayProdService payProdService;
	
	/***
	 * 渠道控制信息列表
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(PayProdPath.PAYPRODPLIST)
	public ModelAndView list(PayProdBean payProdBean) {
		logger.info("查询支付产品列表");
		ModelAndView mv = new ModelAndView(PayProdPath.BASE + PayProdPath.PAYPRODPLIST);
		
		List<PayProdBean> list = payProdService.getPayProdList(payProdBean);
		mv.addObject("payProdList", list);
		mv.addObject("queryBean", payProdBean);
		return mv;
	}
	
	@RequestMapping(PayProdPath.PAYPRODADD)
	@ResponseBody
	public String add(HttpServletRequest httpRequest,PayProdBean payProdBean) {
		logger.info("添加产品");
		JSONObject jo = new JSONObject();
		if("post".equalsIgnoreCase(httpRequest.getMethod())){
			try {
				payProdService.addPayProdInfo(payProdBean);
				jo.put("result", "SUCCESS");
				jo.put("message", "添加成功");
			} catch (Exception e) {
				jo.put("result", "FAIL");
				jo.put("message", e.getMessage());
			}
			
		}
		
		return jo.toJSONString();
	}
	
	@RequestMapping(PayProdPath.PAYPRODUPDATE)
	@ResponseBody
	public String update(HttpServletRequest httpRequest,PayProdBean payProdBean) {
		logger.info("修改支付产品");
		JSONObject jo = new JSONObject();
		if("post".equalsIgnoreCase(httpRequest.getMethod())){
			try {
				payProdService.updatePayProdInfo(payProdBean);
				jo.put("result", "SUCCESS");
				jo.put("message", "修改成功");
			} catch (Exception e) {
				jo.put("result", "FAIL");
				jo.put("message", e.getMessage());
			}
		}
		return jo.toJSONString();
	}
	
	@RequestMapping(PayProdPath.PAYPRODDELETE)
	@ResponseBody
	public String delete(HttpServletRequest httpRequest,String prodCode) {
		logger.info("删除协议");
		JSONObject jo = new JSONObject();
		if("post".equalsIgnoreCase(httpRequest.getMethod())){
			try {
				payProdService.deletePayProdInfo(prodCode);
				jo.put("result", "SUCCESS");
				jo.put("message", "删除成功");
			} catch (Exception e) {
				jo.put("result", "FAIL");
				jo.put("message", e.getMessage());
			}
				
			
		}
		return jo.toJSONString();
	}
	
}
