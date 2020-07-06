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
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.AgreementBean;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.service.AgreementService;
import com.qifenqian.bms.basemanager.utils.GenSN;


@Controller
@RequestMapping(AgreementPath.BASE)
public class AgreementController {
	
	private static Logger logger = LoggerFactory.getLogger(AgreementController.class);
	
	@Autowired
	private AgreementService agreementService;
	
	/***
	 * 渠道控制信息列表
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(AgreementPath.AGREELIST)
	public ModelAndView list(AgreementBean queryBean) {
		logger.info("查询协议列表");
		ModelAndView mv = new ModelAndView(AgreementPath.BASE + AgreementPath.AGREELIST);
		
		List<AgreementBean> list = agreementService.getAgreementList(queryBean);
		mv.addObject("agreementList", list);
		mv.addObject("queryBean", queryBean);
		return mv;
	}
	
	@RequestMapping(AgreementPath.AGREEADD)
	@ResponseBody
	public String add(HttpServletRequest httpRequest,AgreementBean agreementBean) {
		logger.info("添加协议");
		JSONObject jo = new JSONObject();
		if("post".equalsIgnoreCase(httpRequest.getMethod())){
			agreementBean.setAgentAgreementCode(GenSN.getSN());
				try {
					agreementService.addAgreementInfo(agreementBean);
					jo.put("result", "SUCCESS");
					jo.put("message", "添加成功");
				} catch (Exception e) {
					jo.put("result", "FAIL");
					jo.put("message", e.getMessage());
				}
		}
		
		return jo.toJSONString();
	}
	
	@RequestMapping(AgreementPath.AGREEUPDATE)
	@ResponseBody
	public String update(HttpServletRequest httpRequest,AgreementBean agreementBean) {
		logger.info("修改协议");
		JSONObject jo = new JSONObject();
		if("post".equalsIgnoreCase(httpRequest.getMethod())){
			try {
				agreementService.updateAgreementInfo(agreementBean);
				jo.put("result", "SUCCESS");
				jo.put("message", "修改成功");
			} catch (Exception e) {
				jo.put("result", "FAIL");
				jo.put("message", e.getMessage());
			}
		}
		return jo.toJSONString();
	}
	
	@RequestMapping(AgreementPath.AGREEDELETE)
	@ResponseBody
	public String delete(HttpServletRequest httpRequest,String agentAgreementCode,String agentCode,String merCode,String prodCode) {
		logger.info("删除协议");
		JSONObject jo = new JSONObject();
		if("post".equalsIgnoreCase(httpRequest.getMethod())){
			try {
				agreementService.deleteAgreementInfo(agentAgreementCode,agentCode,merCode,prodCode);
				jo.put("result", "SUCCESS");
				jo.put("message", "删除成功");
			} catch (Exception e) {
				jo.put("result", "FAIl");
				jo.put("message",e.getMessage());
			}
		}
		return jo.toJSONString();
	}
	
}
