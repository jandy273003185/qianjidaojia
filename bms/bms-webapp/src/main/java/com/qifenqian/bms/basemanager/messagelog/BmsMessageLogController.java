package com.qifenqian.bms.basemanager.messagelog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qifenqian.bms.basemanager.messagelog.bean.BmsMessageLog;
import com.qifenqian.bms.basemanager.messagelog.service.BmsMessageLogService;

@Controller
@RequestMapping(BmsMessageLogPath.BASE)
public class BmsMessageLogController {
	@Autowired
	private BmsMessageLogService servie;

	@RequestMapping(BmsMessageLogPath.LIST)
	public ModelAndView messageList(BmsMessageLog bmsMessageLog) {
		ModelAndView mv = new ModelAndView(BmsMessageLogPath.BASE + BmsMessageLogPath.LIST);
		List<BmsMessageLog> messageList = servie.selectMessageLogList(bmsMessageLog);
		mv.addObject("queryBean", bmsMessageLog);
		mv.addObject("messageList", messageList);
		return mv;
	}
}
