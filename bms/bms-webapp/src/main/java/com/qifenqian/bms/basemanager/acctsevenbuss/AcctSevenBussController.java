package com.qifenqian.bms.basemanager.acctsevenbuss;

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
import com.qifenqian.bms.basemanager.acctsevenbuss.bean.AcctSevenBuss;
import com.qifenqian.bms.basemanager.acctsevenbuss.dao.AcctSevenBussDao;
import com.qifenqian.bms.basemanager.acctsevenbuss.mapper.AcctSevenBussMapper;

/***
 * 商户账号信息查询
 * 
 * @author shen
 *
 */
@Controller
@RequestMapping(AcctSevenBussPath.BASE)
public class AcctSevenBussController {

	private static Logger logger = LoggerFactory.getLogger(AcctSevenBussController.class);

	@Autowired
	private AcctSevenBussDao acctSevenBussDao;

	@Autowired
	private AcctSevenBussMapper acctSevenBussMapper;

	/***
	 * 商户账号信息列表
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(AcctSevenBussPath.LIST)
	public ModelAndView list(AcctSevenBuss queryBean) {

		logger.info("查询商户账号信息对象: {}", JSONObject.toJSONString(queryBean, true));
		ModelAndView mv = new ModelAndView(AcctSevenBussPath.BASE + AcctSevenBussPath.LIST);
		List<AcctSevenBuss> acctSevenBussList = acctSevenBussDao.queryAcctSevenBuss(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("acctSevenBussList", JSONObject.toJSON(acctSevenBussList));
		return mv;
	}

	@RequestMapping(AcctSevenBussPath.EDIT)
	@ResponseBody
	public String edit(AcctSevenBuss updateBean) {
		JSONObject json = new JSONObject();
		logger.info("修改商户账号状态对象: {}", JSONObject.toJSONString(updateBean, true));
		if (null == updateBean) {
			throw new IllegalArgumentException("商户账号对象为空");
		}
		if (StringUtils.isBlank(updateBean.getAcctId())) {
			throw new IllegalArgumentException("商户七分钱账号为空");
		}
		if (StringUtils.isBlank(updateBean.getStatus())) {
			throw new IllegalArgumentException("商户状态为空");
		}

		try {
			int i = acctSevenBussMapper.updateAcctSevenBuss(updateBean);

			if (1 == i) {
				json.put("result", "SUCCESS");
			} else {
				json.put("result", "FAIL");
				json.put("message", "保存修改异常!");
			}

		} catch (Exception e) {
			json.put("result", "FAIL");
			json.put("message", e.getMessage());
		}

		return json.toJSONString();
	}

}
