package com.qifenqian.bms.meeting.activity.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.meeting.activity.bean.ActivityBean;
import com.qifenqian.bms.meeting.activity.mapper.ActivityMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class ActivityDao {
	@Autowired
	private ActivityMapper activityMapper;

	@Page
	public List<ActivityBean> selectActivityList(ActivityBean queryBean) {
		return activityMapper.selectActivityList(queryBean);
	}

}
