package com.qifenqian.bms.basemanager.calendar.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.calendar.bean.CalendarBean;
import com.qifenqian.bms.basemanager.calendar.mapper.CalendarMapper;

/**
 * 日历管理
 * 
 * @author ganzheng.ge
 *
 */
@Service
public class CalendarService {
	@Autowired
	private CalendarMapper calendarMapper;

	/**
	 * 生成某一年的日历
	 * 
	 * @param year
	 */
	public void bmsCalendar(int year, String instUser) {
		List<CalendarBean> calendarList = new ArrayList<CalendarBean>();
		
		Calendar cal = Calendar.getInstance();
		String isWork = "";// 是否工作日,Y,N
		int maxDate = cal.getActualMaximum(Calendar.DAY_OF_YEAR);// 一年的总数
		final String dayNames[] = { "Sunday", "Monday", "Tuesday", "Wednesday",
				"Thursday", "Friday", "Saturday" };
		for (int i = 0; i < maxDate; i++) {
			CalendarBean calendar = new CalendarBean();
			cal.set(year, 0, 1);
			cal.add(Calendar.DAY_OF_YEAR, i);
			calendar.setDate((new SimpleDateFormat("yyyy-MM-dd")).format(cal
					.getTime()));
			calendar.setDateType(dayNames[(cal.get(Calendar.DAY_OF_WEEK) - 1)]);
			if (1 == cal.get(Calendar.DAY_OF_WEEK)
					|| 7 == cal.get(Calendar.DAY_OF_WEEK)) {
				isWork = "N";
			} else {
				isWork = "Y";
			}
			calendar.setIsWork(isWork);
			calendar.setInstUser(instUser);
			calendarList.add(calendar);
		}
		calendarMapper.insertCalendar(calendarList);
	}
}
