package com.qifenqian.bms.basemanager.calendar;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qifenqian.bms.basemanager.calendar.service.CalendarService;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.qifenqian.bms.platform.third.scheduler.ISchedulerServlet;
import com.qifenqian.bms.task.TaskPath;
import com.sevenpay.plugin.IPlugin;

@WebServlet(name = "CalendarServlet", urlPatterns = { TaskPath.BASE + TaskPath.CALENDARSERVLET })
public class CalendarServlet extends ISchedulerServlet {

	/**
	 * 根据参数传入的年份生成日历，默认生成明年的日历
	 */
	private static final long serialVersionUID = 7620945991100369184L;
	private Logger logger = LoggerFactory.getLogger(CalendarService.class);
	private CalendarService CalendarService = SpringUtils.getBean(CalendarService.class);
	private CommonService commonService = SpringUtils.getBean(CommonService.class);

	@Override
	public String Subject(HttpServletRequest request, HttpServletResponse response) {
		try {
			logger.info("CalendarServlet.Subject() *****  start");
			Calendar c = Calendar.getInstance();
			c.setTime(new Date());
			int year = c.get(Calendar.YEAR) + 1;// 默认生成明年的日历
			if (StringUtils.isNotBlank(request.getParameter("year"))) {
				year = Integer.parseInt(request.getParameter("year"));
			}
			IPlugin plugin = commonService.getIPlugin();
			CalendarService.bmsCalendar(year, plugin.findDictByPath("scheduler.instUser"));
			request.setAttribute(EXECUTE_DESC, "CalendarServlet.java Subject() SUCCESS");
			logger.info("CalendarServlet.Subject() *****  end");
		} catch (Exception e) {
			logger.error("生成日历出错：", e);
			request.setAttribute(EXECUTE_DESC, "CalendarServlet.java Subject() ERROR：" + e);
			return EXECUTE_FAILURE;
		}
		return EXECUTE_SUCCESS;
	}

}
