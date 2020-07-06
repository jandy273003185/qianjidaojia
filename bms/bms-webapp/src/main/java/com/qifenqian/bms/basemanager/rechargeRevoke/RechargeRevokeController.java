package com.qifenqian.bms.basemanager.rechargeRevoke;

import java.text.ParseException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.rechargeRevoke.bean.RechargeRevoke;
import com.qifenqian.bms.basemanager.rechargeRevoke.dao.RechargeRevokeDao;
import com.qifenqian.bms.basemanager.rechargeRevoke.service.RechargeRevokeService;

@Controller
@RequestMapping(RechargeRevokePath.BASE)
public class RechargeRevokeController {

	private static Logger logger = LoggerFactory.getLogger(RechargeRevokeController.class);

	@Autowired
	private RechargeRevokeService rechargeRevokeService;

	@Autowired
	private RechargeRevokeDao rechargeRevokeDao;

	/**
	 * 充值撤销列表
	 * 
	 * @param tradeControl
	 */
	@RequestMapping(RechargeRevokePath.LIST)
	public ModelAndView list(RechargeRevoke queryBean) {

		logger.info("充值撤销列表查询对象：{}", JSONObject.toJSONString(queryBean, true));
		ModelAndView mv = new ModelAndView(RechargeRevokePath.BASE + RechargeRevokePath.LIST);
		List<RechargeRevoke> rechargeRevokeList = rechargeRevokeDao.selectTransRevokeList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("rechargeRevokeList", JSONObject.toJSON(rechargeRevokeList));
		return mv;
	}

	/**
	 * 充值撤销申请
	 * 
	 * @param queryBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(RechargeRevokePath.RECHARGE_REVOKE_APPLY)
	@ResponseBody
	public String rechargeRevokeApply(RechargeRevoke applyBean) {
		logger.info("充值撤销申请对象：{}", JSONObject.toJSONString(applyBean, true));
		JSONObject jsonObject = new JSONObject();
		jsonObject = rechargeRevokeService.rechargeRevokeApply(applyBean);
		return jsonObject.toJSONString();
	}

	/**
	 * 审核
	 * 
	 * @param revokeBean
	 * @return
	 */
	@RequestMapping(RechargeRevokePath.RECHARGE_REVOKE_AUDIT)
	@ResponseBody
	public String rechargeRevokeAudit(RechargeRevoke auditBean) {
		logger.info("充值撤销审核对象：{}", JSONObject.toJSONString(auditBean, true));
		JSONObject json = new JSONObject();

		try {
			json = rechargeRevokeService.rechargeRevokeAudit(auditBean);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return json.toJSONString();
	}

}
