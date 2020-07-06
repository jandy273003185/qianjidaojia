package com.qifenqian.bms.basemanager.workflow;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.workflow.service.WorkFlowService;


@Controller
@RequestMapping(WorkflowPath.BASE)
public class WorkflowController {
	
	private static final Logger logger =  LoggerFactory.getLogger(WorkflowController.class);
	@Autowired
	private WorkFlowService workFlowService;
	
	/**
	 * 流程开始
	 * @return
	 */
	@RequestMapping(WorkflowPath.MAIN)
	public ModelAndView flowMain(){
		
		return new ModelAndView(WorkflowPath.BASE+WorkflowPath.MAIN);
		
	}
	
	/**
	 * 申请请假
	 * @return
	 */
	@RequestMapping(WorkflowPath.LEAVE)
	@ResponseBody
	public String leave(HttpServletRequest request){
		
		JSONObject js = new JSONObject();
		String userId = request.getParameter("userId");
		try {
			workFlowService.startLeave(userId);
			js.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("申请出现问题"+e);
			js.put("result", "FAIL");
			js.put("message", e.getMessage());
		}
		
		return js.toJSONString();
	}
	
	
	@RequestMapping(WorkflowPath.LIST)
	public void list(){
		
		
		
	}
}
