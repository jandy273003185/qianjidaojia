package com.qifenqian.bms.app.creditcard;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.app.creditcard.bean.CreditCardManageBean;
import com.qifenqian.bms.app.creditcard.service.CreditCardManageService;


/**
 * 信用卡申请链接管理Controller
 * @author hongjiagui
 *
 */
@Controller
@RequestMapping(CreditCardManagePath.BASE)
public class CreditCardManageController {

	@Autowired
	private CreditCardManageService creditCardManageService;
	
	/**
	 * 根据查询条件获取信用卡信息列表
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(CreditCardManagePath.MANAGE)
	public ModelAndView manage(CreditCardManageBean queryBean) {
		ModelAndView mv = new ModelAndView(CreditCardManagePath.BASE + CreditCardManagePath.MANAGE);	
		List<CreditCardManageBean> creditCardManageList = creditCardManageService.selectCreditCardList(queryBean);
		//信用卡信息列表,转成json格式方便前台js获取list对象
		mv.addObject("creditCardList", JSONObject.toJSON(creditCardManageList));
		//信用卡列表总条数
		mv.addObject("count", creditCardManageService.getCountOfCard());

		//回显查询条件
		mv.addObject("queryBean", queryBean);
		return mv;
	}
	/**
	 * 保存信用卡信息
	 * @param bean
	 * @return
	 */
	@RequestMapping(CreditCardManagePath.ADD)
	@ResponseBody
	public String add(CreditCardManageBean bean) {
		String result = creditCardManageService.saveCreditCard(bean);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json.toString();
	}
	/**
	 * 修改信用卡信息
	 * @param creditCard
	 * @return
	 */
	@RequestMapping(CreditCardManagePath.UPDATE)
	@ResponseBody
	public String updateCreditCard(CreditCardManageBean creditCard){
		String result = creditCardManageService.updateCreditCard(creditCard);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json.toString();
	}
	 
	/**
	 * 删除信用卡信息
	 * @param cardId
	 * @return
	 */
	@RequestMapping(CreditCardManagePath.DELETE)
	@ResponseBody
	public String deleteCreditCard(String cardId){
		String result = creditCardManageService.deleteCreditCardByCardId(cardId);
		JSONObject json = new JSONObject();
		json.put("result", result);
		return json.toString();
	}
}
