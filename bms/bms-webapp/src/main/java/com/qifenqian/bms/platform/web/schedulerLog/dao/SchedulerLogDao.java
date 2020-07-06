package com.qifenqian.bms.platform.web.schedulerLog.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.platform.web.schedulerLog.bean.SchedulerLog;
import com.qifenqian.bms.platform.web.schedulerLog.mapper.SchedulerLogMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class SchedulerLogDao {
	@Autowired
	private SchedulerLogMapper mapper;
	
	@Page
	public List<SchedulerLog>selectSchedulerLogList(SchedulerLog schedulerLog){
		return mapper.selectSchedulerLogList(schedulerLog);
	}
}
