package com.qifenqian.bms.basemanager.withdrawControl;

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
import com.qifenqian.bms.basemanager.withdrawControl.bean.WithdrawControl;
import com.qifenqian.bms.basemanager.withdrawControl.service.WithdrawControlService;

@Controller
@RequestMapping(WithdrawControlPath.BASE)
public class WithdrawControlController {

	private static Logger logger = LoggerFactory.getLogger(WithdrawControlController.class);
	@Autowired
	private WithdrawControlService withdrawControlService;

	/**
	 * 展示交易控制信息
	 * 
	 * @param tradeControl
	 */
	@RequestMapping(WithdrawControlPath.LIST)
	public ModelAndView list(WithdrawControl withdrawControl) {

		List<WithdrawControl> withdrawControlList = withdrawControlService.selectAll(withdrawControl);

		ModelAndView model = new ModelAndView(WithdrawControlPath.BASE + WithdrawControlPath.LIST);

		model.addObject("queryBean", withdrawControl);
		model.addObject("withdrawControlList", JSONObject.toJSON(withdrawControlList));

		return model;
	}

	/**
	 * 增加
	 * 
	 * @param rule
	 * @return
	 */
	@RequestMapping(WithdrawControlPath.ADD)
	@ResponseBody
	public String add(WithdrawControl withdrawControl) {
		JSONObject js = new JSONObject();
		try {
			logger.info("请求增加提现控制信息：[{}]", JSONObject.toJSONString(withdrawControl, true));

			WithdrawControl control = withdrawControlService.selectTradeControl(withdrawControl);
			if (control != null) {
				js.put("result", "FAIL");
				if (!StringUtils.isBlank(withdrawControl.getMobile())) {
					js.put("message", "客户 [" + withdrawControl.getMobile() + "]已经存在提现控制信息！");
				} else if (!StringUtils.isBlank(withdrawControl.getCustId())) {
					js.put("message", "客户 [" + withdrawControl.getCustId() + "]已经存在提现控制信息！");
				}
				return js.toJSONString();
			}
			if (StringUtils.isBlank(withdrawControl.getCustId()) && !StringUtils.isBlank(withdrawControl.getMobile())) {
				WithdrawControl queryBean = withdrawControlService.selectCustIdByMobile(withdrawControl.getMobile());
				if (queryBean == null || StringUtils.isBlank(queryBean.getCustId())) {
					js.put("result", "FAIL");
					js.put("message", "客户 [" + withdrawControl.getMobile() + "]在系统中不存在！");
					return js.toJSONString();
				}
				withdrawControl.setCustId(queryBean.getCustId());
			}
			withdrawControlService.addTradeControl(withdrawControl);
			js.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("请求增加提现控制信息异常", e);
			js.put("result", "FAIL");
			js.put("message", e.getMessage());
		}
		return js.toJSONString();

	}

	/**
	 * 修改
	 * 
	 * @param rule
	 * @return
	 */
	@RequestMapping(WithdrawControlPath.UPDATE)
	@ResponseBody
	public String edit(WithdrawControl withdrawControl) {
		JSONObject js = new JSONObject();
		try {
			logger.info("请求修改提现控制信息：[{}]", JSONObject.toJSONString(withdrawControl, true));

			withdrawControlService.editTradeControl(withdrawControl);
			js.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("请求修改提现控制信息异常", e);
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
	@RequestMapping(WithdrawControlPath.DELETE)
	@ResponseBody
	public String delete(WithdrawControl tradeControl) {
		JSONObject js = new JSONObject();
		try {
			
			logger.info("请求删除交易控制信息： [{}]", JSONObject.toJSONString(tradeControl, true));
			
			if("0000".equals(tradeControl.getCustId())){
				js.put("result", "FAIL");
				js.put("message", "总控不能删除");
				return js.toJSONString();
			}
			withdrawControlService.deleteTradeControl(tradeControl);
			js.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("请求删除交易控制信息异常", e);
			js.put("result", "FAIL");
			js.put("message", e.getMessage());
		}
		return js.toJSONString();

	}

}
