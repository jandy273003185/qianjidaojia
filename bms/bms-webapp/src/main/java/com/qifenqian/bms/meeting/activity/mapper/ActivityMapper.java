package com.qifenqian.bms.meeting.activity.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.meeting.activity.bean.ActivityBean;

@MapperScan
public interface ActivityMapper {

	int insertActivitySingle(ActivityBean insertBean);

	int updateActivityById(ActivityBean updateBean);

	List<ActivityBean> selectActivityList(ActivityBean queryBean);
	
	ActivityBean selectActivityById(int activityId);

}
