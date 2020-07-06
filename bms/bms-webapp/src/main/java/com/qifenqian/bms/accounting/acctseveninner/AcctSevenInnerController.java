package com.qifenqian.bms.accounting.acctseveninner;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.acctseveninner.bean.AcctSevenInner;
import com.qifenqian.bms.accounting.acctseveninner.dao.AcctSevenInnerDao;

@Controller
@RequestMapping(AcctSevenInnerPath.BASE)
public class AcctSevenInnerController {

	private static Logger logger = LoggerFactory.getLogger(AcctSevenInnerController.class);

	@Autowired
	private AcctSevenInnerDao acctSevenInnerDao;

	@RequestMapping(AcctSevenInnerPath.LIST)
	public ModelAndView list(AcctSevenInner queryBean) {
		logger.info("查询七分钱内部账户对象 {}", JSONObject.toJSONString(queryBean, true));

		ModelAndView mv = new ModelAndView(AcctSevenInnerPath.BASE + AcctSevenInnerPath.LIST);

		List<AcctSevenInner> acctSevenInnerList = acctSevenInnerDao.queryAcctSevenInnerList(queryBean);

		logger.info("七分钱内部账户数量 {} 条", acctSevenInnerList.size());
		mv.addObject("queryBean", queryBean);
		mv.addObject("acctSevenInnerList", acctSevenInnerList);
		return mv;
	}
}
