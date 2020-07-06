package com.qifenqian.bms.basemanager.tradeRevoke;

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
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.tradeRevoke.bean.CustTransRevoke;
import com.qifenqian.bms.basemanager.tradeRevoke.dao.CustTransRevokeDao;
import com.qifenqian.bms.basemanager.tradeRevoke.service.CustTransRevokeService;

@Controller
@RequestMapping(CustTransRevokePath.BASE)
public class CustTransRevokeController {

	private static Logger logger = LoggerFactory.getLogger(CustTransRevokeController.class);

	@Autowired
	private CustTransRevokeService custTransRevokeService;

	@Autowired
	private TradeBillService tradeBillService;

	@Autowired
	private CustTransRevokeDao custTransRevokeDao;

	/**
	 * 交易撤销列表
	 * 
	 * @param tradeControl
	 */
	@RequestMapping(CustTransRevokePath.LIST)
	public ModelAndView list(CustTransRevoke queryBean) {
		logger.info("交易撤销列表查询对象：{}", JSONObject.toJSONString(queryBean, true));
		ModelAndView mv = new ModelAndView(CustTransRevokePath.BASE + CustTransRevokePath.LIST);
		List<CustTransRevoke> transRevokeList = custTransRevokeDao.selectTransRevokeList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("transRevokeList", JSONObject.toJSON(transRevokeList));
		return mv;
	}

	/**
	 * 交易撤销申请
	 * 
	 * @param queryBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(CustTransRevokePath.TRANS_REVOKE_APPLY)
	@ResponseBody
	public String transRevokeApply(CustTransRevoke applyBean) {
		logger.info("交易撤销申请对象：{}", JSONObject.toJSONString(applyBean, true));
		JSONObject jsonObject = new JSONObject();
		jsonObject = custTransRevokeService.transRevokeApply(applyBean);
		return jsonObject.toJSONString();
	}

	/**
	 * 审核
	 * 
	 * @param revokeBean
	 * @return
	 */
	@RequestMapping(CustTransRevokePath.TRANS_REVOKE_AUDIT)
	@ResponseBody
	public String transRevokeAudit(CustTransRevoke auditBean) {
		logger.info("交易撤销审核对象：{}", JSONObject.toJSONString(auditBean, true));
		JSONObject json = new JSONObject();
		try {
			json = custTransRevokeService.transRevokeAudit(auditBean);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return json.toJSONString();
	}

}
