package com.qifenqian.bms.platform.web.scheduler.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.platform.web.scheduler.bean.SchedulerJob;
import com.qifenqian.bms.platform.web.scheduler.mapper.SchedulerJobMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class SchedulerJobDao {
	@Autowired
	private SchedulerJobMapper schedulerJobMapper;

	/**
	 * 角色查询
	 * @param role
	 * @return
	 */
	@Page
	public List<SchedulerJob> seleteSchedulerJob(SchedulerJob job){
		return schedulerJobMapper.seleteSchedulerJob(job);
	}
}
