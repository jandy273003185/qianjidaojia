package com.qifenqian.bms.platform.web.myWorkSpace.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.platform.web.myWorkSpace.bean.WaitTaskBean;

@MapperScan
public interface MyWorkSpaceMapper {

	public List<WaitTaskBean> getMyTasks(WaitTaskBean bean);
	
	
	public String getUrl(@Param("taskId") String taskId);
}
