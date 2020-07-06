package com.qifenqian.bms.basemanager.rule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.rule.bean.Rule;
import com.qifenqian.bms.basemanager.rule.service.RuleService;
import com.qifenqian.bms.platform.utils.BusinessUtils;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
/**
 * 费率业务层
 * @author pc
 *
 */
@Controller
@RequestMapping(RulePath.BASE)
public class RuleController {

	private static Logger logger = LoggerFactory.getLogger(RuleController.class);
	@Autowired
	private RuleService ruleService;
	
	/**
	 * 费率列表查询
	 * 
	 * @param requestRule
	 * @return
	 */
	@RequestMapping(RulePath.LIST)
	public ModelAndView list(Rule requestRule) {
		String feeCode=requestRule.getFeeCode();
		String feeType=requestRule.getFeeType();
		ModelAndView mv = new ModelAndView(RulePath.BASE + RulePath.LIST);
		// 列表
		mv.addObject("ruleList", ruleService.selectRules(requestRule));
		mv.addObject("feeCode",feeCode );
		mv.addObject("feeType",feeType );
		return mv;
	}

	/**
	 * 增加
	 * 
	 * @param rule
	 * @return
	 */
	@RequestMapping(RulePath.ADD)
	@ResponseBody
	public String add(Rule rule) {
		JSONObject js = new JSONObject();
		try {
			logger.info("请求保存rule：[{}]", JSONObject.toJSONString(rule, true));
			String feeCode = BusinessUtils.getRuleId(ruleService.selectRuleMaxId());
			User user = WebUtils.getUserInfo();
				rule.setInstUser(String.valueOf(user.getUserId()));
				rule.setFeeCode(feeCode);
				ruleService.addRule(rule);
				js.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("请求增加费率异常", e);
			js.put("result", "FAIL");
			js.put("message", e.getMessage());
		}
		return js.toJSONString();

	}

	/**
	 * 删除
	 * 
	 * @param rule
	 * @return
	 */
	@RequestMapping(RulePath.DELETE)
	@ResponseBody
	public String delete(String feeCode) {

		JSONObject js = new JSONObject();

		try {
			logger.info("请求保存rule：[{}]", feeCode);
			
			ruleService.deleteRule(feeCode);
			js.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("请求删除费率异常", e);
			js.put("result", "FAIL");
			js.put("message", e.getMessage());
		}
		return js.toJSONString();

	}

	/**
	 * 更新
	 * 
	 * @param rule
	 * @return
	 */
	@RequestMapping(RulePath.EDIT)
	@ResponseBody
	public String update(Rule rule) {
		JSONObject js = new JSONObject();
		try {
			logger.info("请求修改rule：[{}]", JSONObject.toJSONString(rule, true));
			User user = WebUtils.getUserInfo();
			rule.setLupdUser(String.valueOf(user.getUserId()));
			ruleService.updateRule(rule);
			js.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("请求修改费率异常", e);
			js.put("result", "FAIL");
			js.put("message", e.getMessage());
		}
		return js.toJSONString();

	}

}
