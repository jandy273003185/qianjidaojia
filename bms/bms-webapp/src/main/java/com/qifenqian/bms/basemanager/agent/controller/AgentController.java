package com.qifenqian.bms.basemanager.agent.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.agent.bean.AgentApplyBean;
import com.qifenqian.bms.basemanager.agent.bean.CustVo;
import com.qifenqian.bms.basemanager.agent.service.AgentApplyService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;


@Controller
@RequestMapping(AgentPath.BASE)
public class AgentController {
	
	private static Logger logger = LoggerFactory.getLogger(AgentController.class);
	
	@Autowired
	private AgentApplyService agentApplyService;
	
	/***
	 * 商户账号信息列表
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(AgentPath.LIST)
	public ModelAndView list(AgentApplyBean applyBean) {
		logger.info("查询代理商申请信息列表");
		ModelAndView mv = new ModelAndView(AgentPath.BASE + AgentPath.LIST);
		List<AgentApplyBean> applyList = agentApplyService.getApplyList(applyBean);
		mv.addObject("applyList", applyList);
		mv.addObject("queryBean", applyBean);
		return mv;
	}
	
	
	/***
	 * 查找客户信息
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(AgentPath.FINDCUSTINFO)
	@ResponseBody
	public String findCustInfo(String custId){
		logger.info("审核代理商申请--------------------");
		JSONObject jsonObject = new JSONObject();
		CustVo custVo = agentApplyService.findCustInfo(custId);
		
		jsonObject.put("custInfo", custVo);
		return jsonObject.toJSONString();
	}
	
	
	/***
	 * 审核代理商申请不通过
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(AgentPath.AUDITNOTPASS)
	@ResponseBody
	public String auditNotPass(String custId,String memo){
		logger.info("审核代理商申请不通过--------------------");
		JSONObject jsonObject = new JSONObject();
		try {
			String auditUserId  = String.valueOf(WebUtils.getUserInfo().getUserId());
			agentApplyService.auditNotPass(custId, memo,auditUserId);
			jsonObject.put("result", "SUCCESS");
			jsonObject.put("message", "代理商申请不通过");
		} catch (Exception e) {
			jsonObject.put("result", "FAIL");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	/***
	 * 审核代理商申请通过
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(AgentPath.AUDITPASS)
	@ResponseBody
	public String auditPass(String custId){
		logger.info("审核代理商申请通过--------------------");
		JSONObject jsonObject = new JSONObject();
		try {
			String auditUserId  = String.valueOf(WebUtils.getUserInfo().getUserId());
			agentApplyService.auditPass(custId,auditUserId);
			jsonObject.put("result", "SUCCESS");
			jsonObject.put("message", "代理商申请不通过");
		} catch (Exception e) {
			jsonObject.put("result", "FAIL");
			jsonObject.put("message", e.getMessage());
		}
		
		return jsonObject.toJSONString();
	}
	
}
