package com.qifenqian.bms.meeting.activity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.meeting.activity.bean.ActivityBean;
import com.qifenqian.bms.meeting.activity.dao.ActivityDao;
import com.qifenqian.bms.meeting.activity.mapper.ActivityMapper;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Service
public class ActivityService {
	@Autowired
	private ActivityMapper activityMapper;

	@Autowired
	private ActivityDao activityDao;

	public List<ActivityBean> selectActivityList(ActivityBean queryBean) {
		return activityDao.selectActivityList(queryBean);
	}

	public int insertActivityBean(ActivityBean insertBean) {
		insertBean.setInstUser(WebUtils.getUserInfo().getUserId());
		return activityMapper.insertActivitySingle(insertBean);

	}

	public int updateActivityBean(ActivityBean updateBean) {
		updateBean.setLupdUser(WebUtils.getUserInfo().getUserId());
		return activityMapper.updateActivityById(updateBean);

	}
	
	public List<ActivityBean> selectActivityBeanList(ActivityBean queryBean) {
		return activityMapper.selectActivityList(queryBean);
	}

}
