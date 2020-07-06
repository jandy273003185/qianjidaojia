package com.qifenqian.bms.basemanager.calendar.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.calendar.bean.CalendarBean;
@MapperScan
public interface CalendarMapper {
	public void insertCalendar(List<CalendarBean> calendarList);
}
