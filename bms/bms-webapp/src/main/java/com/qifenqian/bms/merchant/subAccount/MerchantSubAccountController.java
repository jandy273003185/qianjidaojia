package com.qifenqian.bms.merchant.subAccount;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.mapper.FmIncomeMapper;
import com.qifenqian.bms.merchant.subAccount.bean.MerchantSubAccouontBean;
import com.qifenqian.bms.merchant.subAccount.service.MerchantSubAccountService;

/**
 * 商户分账关系
 * @author Lx
 *
 */
@Controller
@RequestMapping("/merchant/subAccount")
public class MerchantSubAccountController {
	
	private Logger logger = LoggerFactory.getLogger(MerchantSubAccountController.class);

	@Autowired
	private MerchantSubAccountService merchantSubAccountService;
	@Autowired
	private FmIncomeMapper fmIncomeMapper;
	/**
	 * 查询商户分账列表
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView list(MerchantSubAccouontBean merchantSubAccouontBean) {
		// 返回视图
		ModelAndView mv = new ModelAndView("/merchant/subAccount/list");
		List<MerchantSubAccouontBean> merchantSubAccouontList = merchantSubAccountService.selectSubAccountList(merchantSubAccouontBean);
		mv.addObject("merchantSubAccouontList", merchantSubAccouontList);
		mv.addObject("merchantSubAccouontBean", merchantSubAccouontBean);
		TdMerchantDetailInfo tdMerchantDetailInfo = new TdMerchantDetailInfo();
		tdMerchantDetailInfo.setReportStatus("1");
		List<TdMerchantDetailInfo>  tdMerchantDetailInfoList= fmIncomeMapper.selMerchantDetailInfoList(tdMerchantDetailInfo);
		mv.addObject("tdMerchantDetailInfoList",tdMerchantDetailInfoList);
		return mv;
	}
	
	/**
	 * 商户分账新增
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Map<String, String> add(MerchantSubAccouontBean merchantSubAccouont) {
		// 请求bean 打印
		logger.info("请求保存MerchantSubAccouontBean：[{}]", JSONObject.toJSONString(merchantSubAccouont, true));
		Map<String, String> result = merchantSubAccountService.insterSubAccount(merchantSubAccouont);
		return result;
	}
	
	/**
	 * 商户分账方停用
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Map<String, String> delete(MerchantSubAccouontBean merchantSubAccouont) {
		// 请求bean 打印
		logger.info("请求停用MerchantSubAccouontBean：[{}]", JSONObject.toJSONString(merchantSubAccouont, true));
		Map<String, String> result = merchantSubAccountService.disableSubAccount(merchantSubAccouont);
		return result;
	}
	
}
