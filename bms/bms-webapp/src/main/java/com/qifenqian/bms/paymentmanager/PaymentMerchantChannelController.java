package com.qifenqian.bms.paymentmanager;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.paymentmanager.bean.TdChannelInfo;
import com.qifenqian.bms.paymentmanager.service.PaymentService;




@Controller
@RequestMapping(PaymentManagerPath.BASE)
public class PaymentMerchantChannelController {
	
	private static Logger logger = LoggerFactory.getLogger(PaymentMerchantChannelController.class);
	
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(PaymentManagerPath.MERCHANTCHANNEL)
	public ModelAndView merchantChannelList(TdChannelInfo queryBean) {
		ModelAndView mv = new ModelAndView(PaymentManagerPath.BASE + PaymentManagerPath.MERCHANTCHANNEL);
		
		List<TdChannelInfo> merchantChannelList =  paymentService.getMerchantChannelList(queryBean);
		
		
		for(int i=0;i<merchantChannelList.size();i++){
			/* 费率超过1
			BigDecimal loanAmount = new BigDecimal("1.00"); 
			Boolean aBoolean = merchantChannelList.get(i).getSevenpayRechargeRate().compareTo(loanAmount) != -1;*/
			merchantChannelList.get(i).setChannelTopayRate(merchantChannelList.get(i).getChannelTopayRate().setScale(2,BigDecimal.ROUND_DOWN));
			//固定费率则保留两位小数,费率则四位小数
			if("fixed".equals(merchantChannelList.get(i).getChannelRechargeType())){
				merchantChannelList.get(i).setChannelRechargeRate(merchantChannelList.get(i).getChannelRechargeRate().setScale(2,BigDecimal.ROUND_DOWN));
			}else{
				merchantChannelList.get(i).setChannelRechargeRate(merchantChannelList.get(i).getChannelRechargeRate().setScale(4,BigDecimal.ROUND_DOWN));
			}
			
			if("fixed".equals(merchantChannelList.get(i).getSevenpayRechargeType())){
				merchantChannelList.get(i).setSevenpayRechargeRate(merchantChannelList.get(i).getSevenpayRechargeRate().setScale(2,BigDecimal.ROUND_DOWN));
			}else{
				merchantChannelList.get(i).setSevenpayRechargeRate(merchantChannelList.get(i).getSevenpayRechargeRate().setScale(4,BigDecimal.ROUND_DOWN));
			}
			
		}
		
		mv.addObject("queryBean", queryBean);
		mv.addObject("merchantChannelList", JSONObject.toJSON(merchantChannelList));
		return mv;
		
	}

}
