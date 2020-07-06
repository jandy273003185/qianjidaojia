package com.qifenqian.bms.platform.web.schedulerLog.serive;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.platform.web.schedulerLog.bean.SchedulerLog;
import com.qifenqian.bms.platform.web.schedulerLog.dao.SchedulerLogDao;

@Service
public class SchedulerLogService {
	@Autowired
	private SchedulerLogDao dao;
	
	public List<SchedulerLog>selectSchedulerLogList(SchedulerLog schedulerLog){
		return dao.selectSchedulerLogList(schedulerLog);
	}
}
