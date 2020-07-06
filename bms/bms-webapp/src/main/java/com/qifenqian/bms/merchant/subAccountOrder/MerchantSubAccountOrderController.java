package com.qifenqian.bms.merchant.subAccountOrder;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qifenqian.bms.merchant.subAccountOrder.bean.MerchantSubAccouontOrderBean;
import com.qifenqian.bms.merchant.subAccountOrder.service.MerchantSubAccouontOrderService;

/**
 * 商户分账关系
 * @author Lx
 *
 */
@Controller
@RequestMapping("/merchant/subAccountOrder")
public class MerchantSubAccountOrderController {
	
	private Logger logger = LoggerFactory.getLogger(MerchantSubAccountOrderController.class);

	@Autowired
	private MerchantSubAccouontOrderService merchantSubAccountOrderService;

	/**
	 * 查询商户分账列表
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(MerchantSubAccouontOrderBean merchantSubAccouontOrderBean) {
		// 返回视图
		logger.debug("查询商户分账列表td_merchant_settle_trade");
		ModelAndView mv = new ModelAndView("/merchant/subAccountOrder/list");
		List<MerchantSubAccouontOrderBean> merchantSubAccouontOrderList = merchantSubAccountOrderService.selectSubAccountOrderList(merchantSubAccouontOrderBean);
		mv.addObject("merchantSubAccouontOrderList", merchantSubAccouontOrderList);
		mv.addObject("MerchantSubAccouontOrderBean", merchantSubAccouontOrderBean);
		
		return mv;
	}
	
	
}
