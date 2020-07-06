package com.qifenqian.bms.myworkspace.allTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.myworkspace.allTask.bean.AllTaskBean;
import com.qifenqian.bms.myworkspace.allTask.service.AllTaskService;
import com.qifenqian.bms.myworkspace.overAudit.OverAuditController;
import com.qifenqian.bms.platform.web.myWorkSpace.service.WorkSpaceService;
import com.qifenqian.bms.platform.web.admin.role.service.RoleService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;


@RequestMapping(AllTaskPath.BASE)
@Controller
public class AllTaskController {
	
	private static Logger logger = LoggerFactory.getLogger(OverAuditController.class);
	
	@Autowired
	private AllTaskService allTaskService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private WorkSpaceService workSpaceService;
	
	/**
	 * 所有任务
	 * @return
	 */
	@RequestMapping(AllTaskPath.LIST)
	public ModelAndView allTask(AllTaskBean bean){
		
		logger.info("查询所有任务");
		ModelAndView mv = new ModelAndView(AllTaskPath.BASE+AllTaskPath.LIST);
		
		List<String> roles = roleService.selectRoleIdListByUserId(WebUtils.getUserInfo().getUserId());
		
		boolean isAdministrator = false;
		if(roles !=null ){
			for(String role:roles){
				if("1".equals(role)){
					isAdministrator = true;
				}
			}
			
		}
		List<AllTaskBean> list = null;
		//管理员所有任务
		if(isAdministrator){
			list = allTaskService.getTasks(bean);
		}else{
			Map<String,Object> params = new HashMap<String,Object>();
			params.put("userId", String.valueOf(WebUtils.getUserInfo().getUserId()));
			params.put("roleIds", roles);
			params.put("allTaskBean", bean);
			
			list = allTaskService.getAllTaskByRole(params);
		}
		mv.addObject("id", bean.getId());
		mv.addObject("name", bean.getName());
		mv.addObject("startUserId", bean.getStartUserId());
		mv.addObject("taskBeginTime", bean.getTaskBeginTime());
		mv.addObject("taskEndTime", bean.getTaskEndTime());
		mv.addObject("auditStatu", bean.getAuditStatu());
		mv.addObject("allTask", list);
		return mv;
	}
	
	/**
	 * 任务详情
	 * @return
	 */
	@ResponseBody
	@RequestMapping(AllTaskPath.VIEW)
	public String view(HttpServletRequest request){
		logger.info("任务详情");
		
		JSONObject jsonobject = new JSONObject();
		String taskId = request.getParameter("taskId");
		try {
			AllTaskBean task = allTaskService.getTaskByTaskId(taskId);
			
			jsonobject.put("result", "SUCCESS");
			jsonobject.put("task", task);
		} catch (Exception e) {
			logger.error("查询任务详情异常",e);
			jsonobject.put("result", "FIAL");
		}
		
		
		return jsonobject.toJSONString();
	}
	
	/**
	 * URL跳转
	 */
	@RequestMapping(AllTaskPath.URL)
	public ModelAndView url(HttpServletRequest request,RedirectAttributes attr){
		
		String taskId = request.getParameter("taskId");
		String url = workSpaceService.getUrl(taskId);
		
		attr.addAttribute("taskId", taskId);
		return new ModelAndView("redirect:"+url);
	}
}
