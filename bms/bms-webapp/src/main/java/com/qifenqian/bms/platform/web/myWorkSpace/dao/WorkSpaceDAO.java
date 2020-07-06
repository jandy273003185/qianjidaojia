	package com.qifenqian.bms.platform.web.myWorkSpace.dao;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.platform.common.utils.ThreadUtils;
import com.qifenqian.bms.platform.web.Constants;
import com.qifenqian.bms.platform.web.myWorkSpace.bean.WaitTaskBean;
import com.qifenqian.bms.platform.web.myWorkSpace.mapper.MyWorkSpaceMapper;
import com.qifenqian.bms.platform.web.page.Page;
import com.qifenqian.bms.platform.web.page.bean.PageInfo;

@Repository
public class WorkSpaceDAO {

	@Resource 
	private TaskService taskService;
	
	@Autowired
	private MyWorkSpaceMapper myWorkSpaceMapper;
	
	/**
	 * 查询我的代办任务
	 */
	@Page
	public List<Task> getTasks(String userId){
		
		PageInfo pageInfo = (PageInfo)ThreadUtils.get(Constants.Platform.$_PAGEINFO);
		
		TaskQuery taskQuery = taskService.createTaskQuery().taskAssignee(userId);
		
		pageInfo.setRowCnt(Long.valueOf( taskQuery.count()).intValue());
			
		
		return taskQuery.listPage(pageInfo.getFirstIdx()-1, pageInfo.getPageSize());
	}
	
	@Page
	public List<WaitTaskBean> getMyTasks(WaitTaskBean bean){
		return myWorkSpaceMapper.getMyTasks(bean);
		
	}
}
