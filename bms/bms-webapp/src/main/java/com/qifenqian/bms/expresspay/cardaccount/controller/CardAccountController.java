package com.qifenqian.bms.expresspay.cardaccount.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.expresspay.cardaccount.bean.CardAccount;
import com.qifenqian.bms.expresspay.cardaccount.service.CardAccountService;
import com.qifenqian.bms.expresspay.cardholderInfo.bean.Cardholder;
/**
 * 交广科技卡账户信息维护
 * @author shen
 *
 */
@Controller
@RequestMapping(CardAccountPath.BASE)
public class CardAccountController {
	
	private static Logger logger =LoggerFactory.getLogger(CardAccountController.class);

	@Autowired
	private CardAccountService cardAccountService;
	
	@Autowired
	private CommonService commonService;
	
	/**
	 * 卡账户信息主页
	 * @param cardNo
	 * @return
	 */
	@RequestMapping(CardAccountPath.CARDACCOUNTMAIN)
	public ModelAndView main(CardAccount account) {
		logger.info("卡账户信息查询,查询对象：[{}]",JSONObject.toJSONString(account,true));
		
		List<CardAccount> cardAccountList = null;
		CardAccount cardAccount = null;
		ModelAndView mv = new ModelAndView(CardAccountPath.BASE + CardAccountPath.CARDACCOUNTMAIN);
		if (!StringUtils.isBlank(account.getMobile())||!StringUtils.isBlank(account.getCardNo())) {
			Cardholder cardHolder= new Cardholder();
			cardHolder.setMobile(account.getMobile());
			cardHolder.setCardNo(account.getCardNo());
			String custId=commonService.getCustId(cardHolder);
			if(!StringUtils.isBlank(custId)){
				String cardNo = commonService.selectCardNo(custId);
				if (!StringUtils.isBlank(cardNo)) {
					cardAccount = cardAccountService.getCardAccountInfo(cardNo);
					if (cardAccount!=null) {
						cardAccount.setCustId(custId);
						cardAccountList=new ArrayList<CardAccount>();
						cardAccountList.add(cardAccount);
					}
				}
			}
		}
		mv.addObject("queryBean",account);
		mv.addObject("cardAccountList", cardAccountList);
		return mv;
	}
}
