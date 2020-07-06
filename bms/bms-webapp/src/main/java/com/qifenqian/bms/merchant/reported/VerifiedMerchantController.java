package com.qifenqian.bms.merchant.reported;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.mapper.FmIncomeMapper;
import com.qifenqian.bms.merchant.reported.service.VerifiedMerchantService;

@Controller
@RequestMapping("/merchant")
public class VerifiedMerchantController {
	
	
	private Logger logger = LoggerFactory.getLogger(VerifiedMerchantController.class);
	
	@Autowired
	private VerifiedMerchantService verifiedMerchantService;
	@Autowired
	private FmIncomeMapper fmIncomeMapper;
	/**
	 * 微信商户实名认证提交
	*/
	@RequestMapping("/verified/merchantReportSubmit")
	@ResponseBody
	public String verifiedSubmit(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo tdMerchantDetailInfo){
		JSONObject object = new JSONObject();
		Map<String, Object> verifiedResult = new HashMap<String, Object>();
		tdMerchantDetailInfo = fmIncomeMapper.selMerchantDetailInfo(tdMerchantDetailInfo);
		//调用实名认证接口
		logger.info("------------调用实名认证接口---------------------" + JSONObject.toJSONString(tdMerchantDetailInfo));
		verifiedResult = verifiedMerchantService.verifiedMerchant(tdMerchantDetailInfo);
		logger.info("------------调用实名认证接口结束---------------------" + JSONObject.toJSONString(verifiedResult));
		if("SUCCESS".equals(verifiedResult.get("result"))){
			object.put("result", "SUCCESS");
			object.put("message", "提交实名认证成功");
		}else{
			object.put("result", "FAILURE");
			object.put("message", verifiedResult.get("message"));
		}
		return object.toString();
		
	}
	
	
	/**
	 * 微信商户认证申请结果查询
	*/
	@RequestMapping("/verified/queryMerchant")
	@ResponseBody
	public String verifiedQuery(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo tdMerchantDetailInfo){
		JSONObject object = new JSONObject();
		Map<String, Object> verifiedResult = new HashMap<String, Object>();
		tdMerchantDetailInfo = fmIncomeMapper.selMerchantDetailInfo(tdMerchantDetailInfo);
		//调用微信商户认证申请结果查询
		logger.info("------------调用微信商户认证申请结果查询---------------------" + JSONObject.toJSONString(tdMerchantDetailInfo));
		verifiedResult = verifiedMerchantService.verifiedQuery(tdMerchantDetailInfo);
		logger.info("------------调用微信商户认证申请结果查询结束---------------------" + JSONObject.toJSONString(verifiedResult));
		if("SUCCESS".equals(verifiedResult.get("result"))){
			object.put("result", "SUCCESS");
			object.put("message", "申请结果查询成功");
		}else{
			object.put("result", "FAILURE");
			object.put("message", verifiedResult.get("message"));
		}
		return object.toString();
		
	}
	
	/**
	 * 微信商户认证申请撤销接口
	*/
	@RequestMapping("/verified/cancelMerchant")
	@ResponseBody
	public String verifiedCancel(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo tdMerchantDetailInfo){
		JSONObject object = new JSONObject();
		Map<String, Object> verifiedResult = new HashMap<String, Object>();
		tdMerchantDetailInfo = fmIncomeMapper.selMerchantDetailInfo(tdMerchantDetailInfo);
		//微信商户认证申请撤销接口
		logger.info("------------调用微信商户认证申请撤销接口---------------------" + JSONObject.toJSONString(tdMerchantDetailInfo));
		verifiedResult = verifiedMerchantService.verifiedCancel(tdMerchantDetailInfo);
		logger.info("------------调用微信商户认证申请撤销接口结束---------------------" + JSONObject.toJSONString(verifiedResult));
		if("SUCCESS".equals(verifiedResult.get("result"))){
			object.put("result", "SUCCESS");
			object.put("message", "申请撤销成功");
		}else{
			object.put("result", "FAILURE");
			object.put("message", verifiedResult.get("message"));
		}
		return object.toString();
		
	}
	
	/**
	 * 微信子商户授权状态查询接口
	*/
	@RequestMapping("/verified/authorizeMerchant")
	@ResponseBody
	public String verifiedAuthorize(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo tdMerchantDetailInfo){
		JSONObject object = new JSONObject();
		Map<String, Object> verifiedResult = new HashMap<String, Object>();
		tdMerchantDetailInfo = fmIncomeMapper.selMerchantDetailInfo(tdMerchantDetailInfo);
		//调用实名认证接口
		verifiedResult = verifiedMerchantService.verifiedAuthorize(tdMerchantDetailInfo);
		if("SUCCESS".equals(verifiedResult.get("result"))){
			object.put("result", "SUCCESS");
			object.put("message", "申请撤销成功");
		}else{
			object.put("result", "FAILURE");
			object.put("message", verifiedResult.get("message"));
		}
		return object.toString();
		
	}
}
