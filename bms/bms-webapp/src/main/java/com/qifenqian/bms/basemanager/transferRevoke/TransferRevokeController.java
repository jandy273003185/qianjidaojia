package com.qifenqian.bms.basemanager.transferRevoke;

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
import com.qifenqian.bms.basemanager.transferRevoke.bean.TransferRevoke;
import com.qifenqian.bms.basemanager.transferRevoke.dao.TransferRevokeDao;
import com.qifenqian.bms.basemanager.transferRevoke.service.TransferRevokeService;

@Controller
@RequestMapping(TransferRevokePath.BASE)
public class TransferRevokeController {
	private static Logger logger = LoggerFactory.getLogger(TransferRevokeController.class);

	@Autowired
	private TransferRevokeService transferRevokeService;

	@Autowired
	private TransferRevokeDao transferRevokeDao;

	/**
	 * 转账撤销列表
	 * 
	 * @param tradeControl
	 */
	@RequestMapping(TransferRevokePath.LIST)
	public ModelAndView list(TransferRevoke queryBean) {
		logger.info("交易撤销列表查询对象：{}", JSONObject.toJSONString(queryBean, true));
		ModelAndView mv = new ModelAndView(TransferRevokePath.BASE + TransferRevokePath.LIST);
		List<TransferRevoke> transferRevokeList = transferRevokeDao.selectTransferRevokeList(queryBean);
		mv.addObject("queryBean", queryBean);
		mv.addObject("transferRevokeList", JSONObject.toJSON(transferRevokeList));
		return mv;
	}

	/**
	 * 转账撤销申请
	 * 
	 * @param queryBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(TransferRevokePath.TRANSFER_REVOKE_APPLY)
	@ResponseBody
	public String transRevokeApply(TransferRevoke applyBean) {
		logger.info("转账撤销申请对象：{}", JSONObject.toJSONString(applyBean, true));
		JSONObject jsonObject = new JSONObject();
		jsonObject = transferRevokeService.transferRevokeApply(applyBean);
		return jsonObject.toJSONString();
	}

	/**
	 * 审核
	 * 
	 * @param revokeBean
	 * @return
	 */
	@RequestMapping(TransferRevokePath.TRANSFER_REVOKE_AUDIT)
	@ResponseBody
	public String transRevokeAudit(TransferRevoke auditBean) {
		logger.info("转账撤销审核对象：{}", JSONObject.toJSONString(auditBean, true));
		JSONObject json = new JSONObject();
		try {
			json = transferRevokeService.transferRevokeAudit(auditBean);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return json.toJSONString();
	}

}
