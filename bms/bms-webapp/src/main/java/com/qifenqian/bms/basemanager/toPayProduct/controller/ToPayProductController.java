package com.qifenqian.bms.basemanager.toPayProduct.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.toPayProduct.bean.ToPayProduct;
import com.qifenqian.bms.basemanager.toPayProduct.service.ToPayProductService;




@Controller
@RequestMapping(ToPayProductPath.BASE)
public class ToPayProductController {
	
	private static Logger logger = LoggerFactory.getLogger(ToPayProductController.class);
	
	@Autowired
	private ToPayProductService toPayProductService;
	
	@RequestMapping(ToPayProductPath.LIST)
	public ModelAndView toPayProductDetail(ToPayProduct bean) {	
		ModelAndView mv = new ModelAndView(ToPayProductPath.BASE + ToPayProductPath.LIST);
		List<ToPayProduct> productList = toPayProductService.listProduct(bean);
		mv.addObject("productList", productList);
		mv.addObject("queryBean",bean);
		return mv;
	}

	
	
	/**
	 * 修改代付费率
	 */
	@RequestMapping(ToPayProductPath.UPDATEPRODUCTRATE)
	@ResponseBody
	public String updateProductRate(ToPayProduct bean) {
		logger.info("-----修改代付费率开始");
		JSONObject json = new JSONObject();
		
		json = toPayProductService.updateProductRate(bean);
		
		
		return json.toJSONString();
	}

}
