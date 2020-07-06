package com.qifenqian.bms.basemanager.branchbank;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.branchbank.bean.BranchBank;
import com.qifenqian.bms.basemanager.branchbank.service.BranchBankService;
import com.qifenqian.bms.basemanager.utils.StrCombineSplit;

/***
 * 银行支行信息
 * 
 * @author shen
 * 
 */
@Controller
@RequestMapping(BranchBankPath.BASE)
public class BranchBankController {

	private static Logger logger = LoggerFactory.getLogger(BranchBankController.class);

	@Autowired
	public BranchBankService branchBankService;

	/***
	 * 银行支行信息列表
	 * 
	 * @param queryBean
	 * @return
	 */
	@RequestMapping(BranchBankPath.LIST)
	public ModelAndView list(BranchBank queryBean) {

		logger.info("支行信息查询对象 {}", JSONObject.toJSONString(queryBean, true));

		ModelAndView mv = new ModelAndView(BranchBankPath.BASE + BranchBankPath.LIST);
		//拆分银行名称，增加匹配度。
		String bankName = queryBean.getBankName();
		if(bankName!=null && !bankName.equals("")){
			queryBean.setBankName(StrCombineSplit.splitStr(queryBean.getBankName()));
		}
		List<BranchBank> branchBankList = branchBankService.branchBankList(queryBean);
		queryBean.setBankName(bankName);
		mv.addObject("queryBean", queryBean);
		mv.addObject("branchBankList", JSONObject.toJSON(branchBankList));

		return mv;
	}

	/***
	 * 新增银行支行信息
	 * 
	 * @param insertBean
	 * @return
	 */
	@RequestMapping(BranchBankPath.ADD)
	@ResponseBody
	public String add(BranchBank insertBean) {
		logger.info("新增支行信息对象 {}", JSONObject.toJSONString(insertBean, true));
		JSONObject json = new JSONObject();
		json = branchBankService.add(insertBean);
		return json.toJSONString();
	}

	/***
	 * 修改银行支行信息
	 * 
	 * @param insertBean
	 * @return
	 */
	@RequestMapping(BranchBankPath.UPDATE)
	@ResponseBody
	public String update(BranchBank updateBean) {
		logger.info("修改支行信息对象 {}", JSONObject.toJSONString(updateBean, true));
		JSONObject json = new JSONObject();
		json = branchBankService.update(updateBean);
		return json.toJSONString();
	}

	/***
	 * 删除银行支行信息
	 * 
	 * @param insertBean
	 * @return
	 */
	@RequestMapping(BranchBankPath.DELETE)
	@ResponseBody
	public String delete(BranchBank deleteBean) {
		JSONObject json = new JSONObject();
		json = branchBankService.delete(deleteBean);
		return json.toJSONString();
	}
}
