package com.qifenqian.bms.accounting.bmsexception;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.bmsexception.bean.BmsException;
import com.qifenqian.bms.accounting.bmsexception.dao.BmsExceptionDao;

@Controller
@RequestMapping(BmsExceptionPath.BASE)
public class BmsExceptionController {
	private static Logger logger =LoggerFactory.getLogger(BmsExceptionController.class);
	@Autowired
	private BmsExceptionDao bmsExceptionDao;
	
	/***
	 * 异常管理列表
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(BmsExceptionPath.LIST)
	public ModelAndView list(BmsException queryBean) {
		logger.info("异常处理查询列表请求:[{}]", JSONObject.toJSONString(queryBean, true));
		ModelAndView mv = new ModelAndView(BmsExceptionPath.BASE + BmsExceptionPath.LIST);
		List<BmsException> bmsExceptionList = bmsExceptionDao.selectBmsExceptionList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("bmsExceptionList", bmsExceptionList);
		return mv;
	}
}
