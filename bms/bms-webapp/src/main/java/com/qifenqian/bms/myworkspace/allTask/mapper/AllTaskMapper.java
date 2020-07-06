package com.qifenqian.bms.myworkspace.allTask.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.myworkspace.allTask.bean.AllTaskBean;

@MapperScan
public interface AllTaskMapper {
	
	
	public List<AllTaskBean> getAllTask(@Param("userId") String userId);
	
	public List<AllTaskBean> getAllTaskbyRole(Map<String,Object> params);
	
	public AllTaskBean getTaskById(@Param("taskId") String taskId);
	
	public List<AllTaskBean> getTasks(AllTaskBean bean);
}
