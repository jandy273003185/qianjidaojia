package com.qifenqian.bms.expresspay.tradeDetail.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.expresspay.tradeDetail.service.TradeDetailService;

@Controller
@RequestMapping(TradeDetailPath.BASE)
public class TradeDetailController {

	@Autowired
	private TradeDetailService tradeDetailService;

	@Autowired
	private CommonService commonService;

	

}
