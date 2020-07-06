package com.qifenqian.bms.expresspay.cardholderInfo.controller;

import java.util.ArrayList;
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
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.expresspay.cardholderInfo.bean.Cardholder;
import com.qifenqian.bms.expresspay.cardholderInfo.service.CardholderService;

/**
 * 交广科技卡信息维护
 * 
 * @author shen
 * 
 */
@Controller
@RequestMapping(CardholderPath.BASE)
public class CardholderController {

	private static Logger logger = LoggerFactory.getLogger(CardholderController.class);

	@Autowired
	private CardholderService cardholderService;

	@Autowired
	private CommonService commonService;

	/**
	 * 持卡人信息主页
	 * 
	 * @param cardNo
	 * @return
	 */
	@RequestMapping(CardholderPath.CARDHOLDERMAIN)
	public ModelAndView main(Cardholder queryBean) {
		logger.info("持卡人信息查询,查询对象：[{}]", JSONObject.toJSONString(queryBean,true));
		List<Cardholder> cardholderList = new ArrayList<Cardholder>();
		Cardholder cardholder = null;
		ModelAndView mv = new ModelAndView(CardholderPath.BASE + CardholderPath.CARDHOLDERMAIN);
		if (!StringUtils.isBlank(queryBean.getMobile()) || !StringUtils.isBlank(queryBean.getCardNo())) {
			String custId = commonService.getCustId(queryBean);
			if (!StringUtils.isBlank(custId)) {
				String cardNo = commonService.selectCardNo(custId);
				if (!StringUtils.isBlank(cardNo)) {
					cardholder = cardholderService.getCardholderInfo(cardNo);
					if (cardholder != null) {
						cardholder.setCustId(custId);
						cardholderList.add(cardholder);
					}
				}
			}
		}
		mv.addObject("queryBean", queryBean);
		mv.addObject("cardholderList", JSONObject.toJSON(cardholderList));
		return mv;
	}

	/**
	 * 持卡人信息修改
	 * 
	 * @return
	 */
	@RequestMapping(CardholderPath.EDIT)
	@ResponseBody
	public String edit(Cardholder requestUser) {
		logger.info("请求修改cardholder：[{}]", JSONObject.toJSONString(requestUser, true));
		if (StringUtils.isEmpty(requestUser.getCardNo())) {
			throw new IllegalArgumentException("交广科技卡号为空");
		}
		if (StringUtils.isEmpty(requestUser.getCustId())) {
			throw new IllegalArgumentException("客户账号为空");
		}
		JSONObject jsonObject = new JSONObject();

		try {
			// 保存修改
			Cardholder card = cardholderService.updateJgkjCard(requestUser);
			if (card != null && !StringUtils.isBlank(card.getCardNo())) {
				jsonObject.put("result", "SUCCESS");
			} else {
				jsonObject.put("result", "FAILURE");
				jsonObject.put("message", "修改失败");
			}

		} catch (Exception e) {
			logger.error("修改用户异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

}
