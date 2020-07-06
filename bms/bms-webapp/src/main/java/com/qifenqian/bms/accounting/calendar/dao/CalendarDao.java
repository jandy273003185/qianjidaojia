package com.qifenqian.bms.accounting.calendar.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.calendar.bean.CalendarVo;
import com.qifenqian.bms.accounting.calendar.mapper.CalendarVoMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class CalendarDao {
	
	@Autowired
	private CalendarVoMapper calendarVoMapper;

	@Page
	public List<CalendarVo> selectCalendarList(CalendarVo queryBean) {
		return calendarVoMapper.selectCalendarList(queryBean);
	}
}
