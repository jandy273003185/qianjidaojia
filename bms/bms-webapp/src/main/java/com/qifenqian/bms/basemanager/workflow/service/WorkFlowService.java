package com.qifenqian.bms.basemanager.workflow.service;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.Execution;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;


/**
 * 工作流服务类
 * @author pc
 *
 */
@Service
public class WorkFlowService {

	@Resource
	private RepositoryService repositoryService;
	
	@Resource
	private RuntimeService runtimeService;
	
	@Resource 
	private TaskService taskService;
	/**
	 * 申请请假开始
	 */
	
	public void startLeave(String userId){
//		ProcessEngine processEngine =ProcessEngines.getDefaultProcessEngine();
		User user = WebUtils.getUserInfo();
		Map<String, Object>  var = new LinkedHashMap<String, Object>();
		var.put("userId", user.getUserId());
		//启动流程实例并指定任务人
//		 ProcessInstance processInstance = processEngine.getRuntimeService()
//				 .startProcessInstanceByKey("IwillLeave",var);
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("IwillLeave",var);
		 
		 Execution ex = runtimeService.createExecutionQuery()
		 				.processInstanceId(processInstance.getId())
		 				.singleResult();
		 
		//完成申请
		Task task = taskService
					.createTaskQuery().executionId(ex.getId()).singleResult();
		
		Map<String, Object>  var2 = new LinkedHashMap<String, Object>();
		var2.put("userId", "U000003");
		taskService.complete(task.getId(),var2);
		
	}
}
