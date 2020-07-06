package com.qifenqian.bms.accounting.calendar;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.calendar.bean.CalendarVo;
import com.qifenqian.bms.accounting.calendar.dao.CalendarDao;
import com.qifenqian.bms.accounting.calendar.mapper.CalendarVoMapper;
import com.qifenqian.bms.basemanager.calendar.service.CalendarService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
/**
 * 节假日管理
 * @author shen
 *
 */
@Controller
@RequestMapping(CalendarPath.BASE)
public class CalendarController {

	private static Logger logger = LoggerFactory.getLogger(CalendarController.class);

	@Autowired
	private CalendarDao calendarDao;

	@Autowired
	private CalendarVoMapper calendarVoMapper;
	
	@Autowired
	private CalendarService calendarService;

	/**
	 * 节假日列表
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(CalendarPath.LIST)
	public ModelAndView list(CalendarVo queryBean) {
		logger.info("节假日查询列表请求:[{}]", JSONObject.toJSONString(queryBean, true));
		ModelAndView mv = new ModelAndView(CalendarPath.BASE + CalendarPath.LIST);
		List<CalendarVo> list = calendarDao.selectCalendarList(queryBean);
		mv.addObject("calendarList", list);
		mv.addObject("queryBean", queryBean);
		return mv;
	}

	/**
	 * 节假日修改
	 * @param updateBean
	 * @return
	 */
	@RequestMapping(CalendarPath.EDIT)
	@ResponseBody
	public String update(CalendarVo updateBean) {
		logger.info("节假日修改请求:[{}]", JSONObject.toJSONString(updateBean, true));
		JSONObject object = new JSONObject();

		if (null == updateBean) {
			throw new IllegalArgumentException("修改对象为空");
		}
		if (StringUtils.isBlank(updateBean.getCalendarId())) {
			throw new IllegalArgumentException("修改对象为空");
		}
		updateBean.setLastupdateUser(String.valueOf(String.valueOf(WebUtils.getUserInfo().getUserId())));
		try {
			int i = calendarVoMapper.updateCalendar(updateBean);
			if (i == 1) {
				object.put("result", "success");
			} else {
				object.put("result", "fail");
			}
		} catch (Exception e) {
			logger.error("修改节假日错误：",e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}

		return object.toJSONString();
	}
}
