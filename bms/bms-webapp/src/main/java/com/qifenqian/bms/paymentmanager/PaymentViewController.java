package com.qifenqian.bms.paymentmanager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(PaymentManagerPath.BASE)
public class PaymentViewController {

	@RequestMapping(PaymentManagerPath.PAYMENTLIST)
	public ModelAndView paymentPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(PaymentManagerPath.BASE + PaymentManagerPath.PAYMENTLIST);
		return mv;
	}
}
