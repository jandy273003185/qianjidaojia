package com.qifenqian.bms.myworkspace;

import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.stereotype.Service;

@Service
public class WorkFlowHelper {

	@Resource
	private RepositoryService repositoryService;
	
	@Resource
	private RuntimeService runtimeService;
	
	@Resource 
	private TaskService taskService;
	
	/**
	 * 启动流程
	 */
	public ProcessInstance startProcess(String processKey,String businessKey,Map<String,Object> var){
		return runtimeService.startProcessInstanceByKey(processKey,businessKey,var);
	}
	
	/**
	 * 根据流程实例ID查找任务
	 * @param processInstanceId
	 * @return
	 */
	public Task findTask(String processInstanceId){
		return taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();
	}
	
	/**
	 * 完成任务
	 * @param taskId
	 * @param var
	 */
	public void endTask(String taskId,Map<String,Object> var){
		taskService.complete(taskId, var);
	}
	
	/**
	 * 根据任务ID 查询任务
	 * @param taskId
	 * @return
	 */
	public Task getTask(String taskId){
		
		return taskService.createTaskQuery().taskId(taskId).singleResult();
	}
	
	/**
	 * 根据流程实例ID查询流程实例
	 * @param processInID
	 */
	public ProcessInstance getProcessInstance(String processInID){
		return runtimeService.createProcessInstanceQuery().processInstanceId(processInID).singleResult();
	}
	
//	/**
//	 * 查询任务
//	 * @param taskBean
//	 * @return
//	 */
//	@Page
//	public List<Task> selectTask(TaskBean taskBean){
//		TaskQuery taskQuery = null;
//		
//		//无条件查询
//		if(null == taskBean){
//			taskQuery = taskService.createTaskQuery();
//		}
//		
//		//按办理人查询任务
//		if(!StringUtils.isEmpty(taskBean.getAssignee())){
//			taskQuery = taskService.createTaskQuery().taskAssignee(taskBean.getAssignee());
//		}
//		
//		//按任务ID查询
//		if(!StringUtils.isEmpty(taskBean.getId())){
//			taskQuery = taskService.createTaskQuery().taskId(taskBean.getId());
//		}
//		
//		//按任务名称查询
//		if(!StringUtils.isEmpty(taskBean.getName())){
//			taskQuery = taskService.createTaskQuery().taskName(taskBean.getName());
//		}
//		
//		//按流程定义查询
//		if(!StringUtils.isEmpty(taskBean.getProcessDefinitionId())){
//			taskQuery = taskService.createTaskQuery().processDefinitionId(taskBean.getProcessDefinitionId());
//		}
//		
//		//按流程实例查询
//		if(!StringUtils.isEmpty(taskBean.getProcessInstanceId())){
//			taskQuery = taskService.createTaskQuery().processInstanceId(taskBean.getProcessInstanceId());
//		}
//		PageInfo pageInfo = (PageInfo)ThreadUtils.get(Constants.$_PAGEINFO);
//		
//		pageInfo.setRowCnt(new Long(taskQuery.count()).intValue());
//		return taskQuery.listPage(pageInfo.getFirstIdx(), pageInfo.getPageSize());
//	}
}
