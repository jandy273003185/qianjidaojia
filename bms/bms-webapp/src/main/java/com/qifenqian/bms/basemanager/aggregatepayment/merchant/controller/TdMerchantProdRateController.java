package com.qifenqian.bms.basemanager.aggregatepayment.merchant.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdMerchantProdRate;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.service.TdMerchantProdRateService;

@Controller
@RequestMapping(TdMerchantProdRatePath.BASE)
public class TdMerchantProdRateController {
	
	private static Logger logger = LoggerFactory.getLogger(TdMerchantProdRateController.class);
	@Autowired
	private TdMerchantProdRateService tdMerchantProdRateService;
	
	/**
	 * 商户产品费率列表
	 * @param bean
	 * @return
	 */
	@RequestMapping(TdMerchantProdRatePath.LIST)
	public ModelAndView list(TdMerchantProdRate bean){
		ModelAndView mv = new ModelAndView(TdMerchantProdRatePath.BASE + TdMerchantProdRatePath.LIST);
		List<TdMerchantProdRate>  selectList = tdMerchantProdRateService.selectMerchantChannel(bean);
		mv.addObject("bean", bean);
		mv.addObject("list", JSONObject.toJSONString(selectList));
		mv.addObject("listBean", selectList);
		return mv;
	}
	
	/**
	 * 商户产品费率增加
	 * @param bean
	 * @return
	 */
	@RequestMapping(TdMerchantProdRatePath.ADD)
	@ResponseBody
	public String insert(TdMerchantProdRate bean){
		
		JSONObject json = new JSONObject();
		logger.info("商户产品费率："+JSONObject.toJSONString(bean,true));
		String result = verfiyParam(bean);
		if(result != null){
			return result;
		}
		try {
			tdMerchantProdRateService.insertMerchantProdRate(bean);
			json.put("result", "SUCCESS");
		
		} catch (Exception e) {
			json.put("messgage", "商户产品费率异常");
			json.put("result", "FAIL");
		}
		return json.toJSONString();
	}
	
	/**
	 * 删除商户产品费率
	 * @param channel
	 * @return
	 */
	@RequestMapping(TdMerchantProdRatePath.DELETE)
	@ResponseBody
	public String delete(TdMerchantProdRate bean){
		logger.info("删除商户产品费率："+JSONObject.toJSONString(bean,true));
		JSONObject json = new JSONObject();
		if(StringUtils.isEmpty(bean.getMerAgreementCode())){
			json.put("messgage", "合同编号不能为空！");
			json.put("result", "FAIL");
			return json.toJSONString();
		}
		if(StringUtils.isEmpty(bean.getMerCode())){
			json.put("messgage", "商户号不能为空！");
			json.put("result", "FAIL");
			return json.toJSONString();
		}
		
		if(StringUtils.isEmpty(bean.getProdCode())){
			json.put("messgage", "产品号不能为空！");
			json.put("result", "FAIL");
			return json.toJSONString();
		}
		
		try {
			tdMerchantProdRateService.deleteMerchantProdRate(bean);
			json.put("result", "SUCCESS");
		} catch (Exception e) {
			json.put("messgage", "删除商户产品费率异常");
			json.put("result", "FAIL");
		}
		return json.toJSONString();
	}
	
	public String verfiyParam(TdMerchantProdRate bean){
		JSONObject json = new JSONObject();
		if(StringUtils.isEmpty(bean.getMerCode())){
			json.put("messgage", "商户编号不能为空！");
			json.put("result", "FAIL");
			return json.toJSONString();
		}
		if(StringUtils.isEmpty(bean.getMerAgreementCode())){
			json.put("messgage", "合同号不能为空！");
			json.put("result", "FAIL");
			return json.toJSONString();
		}
		if(StringUtils.isEmpty(bean.getProdCode())){
			json.put("messgage", "产品编号不能为空！");
			json.put("result", "FAIL");
			return json.toJSONString();
		}
		if(bean.getMerAgreeRate()== null){
			json.put("messgage", "费率不能为空！");
			json.put("result", "FAIL");
			return json.toJSONString();
		}
		return null;
	}
}
