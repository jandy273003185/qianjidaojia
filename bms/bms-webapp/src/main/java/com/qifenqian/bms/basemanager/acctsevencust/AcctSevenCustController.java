package com.qifenqian.bms.basemanager.acctsevencust;

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
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.acctsevencust.bean.AcctSevenCust;
import com.qifenqian.bms.basemanager.acctsevencust.service.AcctSevenCustFreezeService;
import com.qifenqian.bms.basemanager.acctsevencust.service.AcctSevenCustService;

@Controller
@RequestMapping(AcctSevenCustPath.BASE)
public class AcctSevenCustController {

	private static Logger logger = LoggerFactory.getLogger(AcctSevenCustController.class);

	@Autowired
	private AcctSevenCustService acctSevenCustService;

	@Autowired
	private AcctSevenCustFreezeService acctSevenCustFreezeService;

	/**
	 * 客户账号
	 * 
	 * @param ad
	 * @return
	 */
	@RequestMapping(AcctSevenCustPath.SELECT)
	public ModelAndView list(AcctSevenCust queryBean) {

		logger.info("查询七分钱客户账号对象 {}", JSONObject.toJSONString(queryBean, true));
		ModelAndView mv = new ModelAndView(AcctSevenCustPath.BASE + AcctSevenCustPath.SELECT);
		List<AcctSevenCust> acctCustList = acctSevenCustService.getAcctSevenCust(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("acctCustList", JSONObject.toJSON(acctCustList));
		return mv;
	}

	/**
	 * 修改账号状态
	 * 
	 * @param custInfo
	 * @return
	 */
	@RequestMapping(AcctSevenCustPath.EDIT)
	@ResponseBody
	public String updateAcctSevenCust(AcctSevenCust acctSevenCust) {
		JSONObject jsonObject = new JSONObject();
		if (StringUtils.isBlank(acctSevenCust.getAcctId())) {
			throw new IllegalArgumentException("账号为空");
		}
		if (StringUtils.isBlank(acctSevenCust.getCustId())) {
			throw new IllegalArgumentException("客户号为空");
		}
		if (StringUtils.isBlank(acctSevenCust.getStatus())) {
			throw new IllegalArgumentException("状态为空");
		}
		try {
			// 冻结
			if (acctSevenCust.getStatus().equals(Constant.ACCOUNT_FREEZE)) {
				logger.info("修改账号状态-冻结" + JSONObject.toJSONString(acctSevenCust, true));
				jsonObject = acctSevenCustFreezeService.fullFreeze(acctSevenCust);
			}else if (acctSevenCust.getStatus().equals(Constant.ACCOUNT_NORMAL)) {
				logger.info("修改账号状态-解冻" + JSONObject.toJSONString(acctSevenCust, true));
				jsonObject = acctSevenCustFreezeService.fullUnfreeze(acctSevenCust);
			}else{
				throw new IllegalArgumentException("不支持的状态类型："+acctSevenCust.getStatus());
			}

		} catch (Exception e) {
			logger.error("修改账户未成功", e);
			jsonObject.put("result", "FAIL");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();

	}

}
