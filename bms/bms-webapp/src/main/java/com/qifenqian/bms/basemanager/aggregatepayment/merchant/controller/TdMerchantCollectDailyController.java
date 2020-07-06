package com.qifenqian.bms.basemanager.aggregatepayment.merchant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdMerchantCollectDaily;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.service.TdMerchantCollectDailyService;

@Controller
@RequestMapping(TdMerchantTdMerchantCollectDailyPath.BASE)
public class TdMerchantCollectDailyController {

	@Autowired
	private TdMerchantCollectDailyService tdMerchantCollectDailyService;
	
	/**
	 * 查询商户每日汇集信息
	 * @param collectDaily
	 * @return
	 */
	@RequestMapping(TdMerchantTdMerchantCollectDailyPath.LIST)
	public ModelAndView list(TdMerchantCollectDaily queryBean){
		ModelAndView mv = new ModelAndView(TdMerchantTdMerchantCollectDailyPath.BASE + TdMerchantTdMerchantCollectDailyPath.LIST);
		List<TdMerchantCollectDaily> results = tdMerchantCollectDailyService.selectMerchantCollectDaily(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("querylists", JSONObject.toJSONString(results));
		mv.addObject("querylist", results);
		return mv;
	}
}
