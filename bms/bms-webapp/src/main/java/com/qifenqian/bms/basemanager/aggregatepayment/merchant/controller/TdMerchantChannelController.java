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
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdMerchantChannel;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.service.TdMerchantChannelService;

@Controller
@RequestMapping(TdMerchantChannelPath.BASE)
public class TdMerchantChannelController {
	
	private static Logger logger = LoggerFactory.getLogger(TdMerchantChannelController.class);
	@Autowired
	private TdMerchantChannelService tdMerchantChannelService;
	
	/**
	 * 渠道金额限制列表
	 * @param bean
	 * @return
	 */
	@RequestMapping(TdMerchantChannelPath.LIST)
	public ModelAndView list(TdMerchantChannel bean){
		ModelAndView mv = new ModelAndView(TdMerchantChannelPath.BASE + TdMerchantChannelPath.LIST);
		List<TdMerchantChannel>  selectList = tdMerchantChannelService.selectMerchantChannel(bean);
		mv.addObject("bean", bean);
		mv.addObject("list", JSONObject.toJSONString(selectList));
		mv.addObject("listBean", selectList);
		return mv;
	}
	
	/**
	 * 增加商户渠道金额限制
	 * @param bean
	 * @return
	 */
	@RequestMapping(TdMerchantChannelPath.ADD)
	@ResponseBody
	public String insert(TdMerchantChannel bean){
		
		JSONObject json = new JSONObject();
		logger.info("增加商户渠道金额限制："+JSONObject.toJSONString(bean,true));
		String result = verfiyParam(bean);
		if(result != null){
			return result;
		}
		try {
			tdMerchantChannelService.insertMerchantChannel(bean);
			json.put("result", "SUCCESS");
		
		} catch (Exception e) {
			json.put("messgage", "增加商户渠道金额限制异常");
			json.put("result", "FAIL");
		}
		return json.toJSONString();
	}
	
	/**
	 * 修改商户渠道金额
	 * @param bean
	 * @return
	 */
	@RequestMapping(TdMerchantChannelPath.UPDATE)
	@ResponseBody
	public String update(TdMerchantChannel bean){
		logger.info("修改商户渠道金额限制："+JSONObject.toJSONString(bean,true));
		JSONObject json = new JSONObject();
		String result = verfiyParam(bean);
		if(result != null){
			return result;
		}
		
		try {
			tdMerchantChannelService.updateMerchantChannel(bean);
			json.put("result", "SUCCESS");
		} catch (Exception e) {
			json.put("messgage", "修改商户渠道金额限制异常");
			json.put("result", "FAIL");
		}
		return json.toJSONString();
	}
	
	/**
	 * 删除商户渠道金额限制
	 * @param channel
	 * @return
	 */
	@RequestMapping(TdMerchantChannelPath.DELETE)
	@ResponseBody
	public String delete(TdMerchantChannel bean){
		logger.info("删除商户渠道金额限制："+JSONObject.toJSONString(bean,true));
		JSONObject json = new JSONObject();
		if(StringUtils.isEmpty(bean.getMchId())){
			json.put("messgage", "商户编号不能为空！");
			json.put("result", "FAIL");
			return json.toJSONString();
		}
		if(StringUtils.isEmpty(bean.getChanel())){
			json.put("messgage", "渠道不能为空！");
			json.put("result", "FAIL");
			return json.toJSONString();
		}
		
		try {
			tdMerchantChannelService.deleteMerchantChannel(bean);
			json.put("result", "SUCCESS");
		} catch (Exception e) {
			json.put("messgage", "删除商户渠道金额限制异常");
			json.put("result", "FAIL");
		}
		return json.toJSONString();
	}
	
	public String verfiyParam(TdMerchantChannel bean){
		JSONObject json = new JSONObject();
		if(StringUtils.isEmpty(bean.getMchId())){
			json.put("messgage", "商户编号不能为空！");
			json.put("result", "FAIL");
			return json.toJSONString();
		}
		if(StringUtils.isEmpty(bean.getChanel())){
			json.put("messgage", "渠道不能为空！");
			json.put("result", "FAIL");
			return json.toJSONString();
		}
		if(StringUtils.isEmpty(bean.getChanelStatus())){
			json.put("messgage", "渠道状态不能为空！");
			json.put("result", "FAIL");
			return json.toJSONString();
		}
		if(bean.getLimitPerAmt() == null){
			json.put("messgage", "交易限额不能为空！");
			json.put("result", "FAIL");
			return json.toJSONString();
		}
		if(bean.getLimitTotAmt() == null){
			json.put("messgage", "交易总限额不能为空！");
			json.put("result", "FAIL");
			return json.toJSONString();
		}
		return null;
	}
}
