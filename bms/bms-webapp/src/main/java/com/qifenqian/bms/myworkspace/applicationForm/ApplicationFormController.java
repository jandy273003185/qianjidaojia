package com.qifenqian.bms.myworkspace.applicationForm;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.mapper.BankMapper;
import com.qifenqian.bms.basemanager.city.service.CityService;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.service.AuditorService;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.rule.service.RuleService;
import com.qifenqian.bms.myworkspace.ActViewUrl;
import com.qifenqian.bms.myworkspace.applicationForm.bean.ActHiProcinst;
import com.qifenqian.bms.myworkspace.applicationForm.bean.ApplicationFormBean;
import com.qifenqian.bms.myworkspace.applicationForm.service.ApplicationFormService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@RequestMapping(ApplicationFormPath.BASE)
@Controller
public class ApplicationFormController {

	private static Logger logger = LoggerFactory.getLogger(ApplicationFormController.class);

	@Autowired 
	private ApplicationFormService applicationFormService;

	@Autowired
	private MerchantService merchantService;

	@Autowired
	private AuditorService auditorService;

	@Autowired
	private BankMapper bankMapper;

	@Autowired
	private CityService cityService;
	
	@Autowired
	private RuleService ruleService;
	/**
	 * 申请单列表
	 * 
	 * @return
	 */
	@RequestMapping(ApplicationFormPath.LIST)
	public ModelAndView formList(ApplicationFormBean bean) {
		
		bean.setUserId(String.valueOf(WebUtils.getUserInfo().getUserId()));
		ModelAndView mv = new ModelAndView(ApplicationFormPath.BASE + ApplicationFormPath.LIST);
		Bank bank = new Bank();
		List<ApplicationFormBean> list = applicationFormService.getMyApplictionForm(bean);
		mv.addObject("banklist", bankMapper.selectBanks(bank));
		mv.addObject("provincelist", cityService.selectAllProvince());
		mv.addObject("forms", list);
		
		mv.addObject("applyBeginTime", bean.getApplyBeginTime());
		mv.addObject("applyEndTime", bean.getApplyEndTime());
		mv.addObject("ids", bean.getId());
		mv.addObject("startUserId", bean.getStartUserId());
		return mv;
	}

	/**
	 * 预览申请单
	 * 
	 * @return
	 */
//	@RequestMapping(ApplicationFormPath.VIEW)
//	@ResponseBody
//	public String viewForm(HttpServletRequest request) {
//
//		logger.info("预览申请单");
//		String bussessKey = request.getParameter("bussessKey");
//		String[] var = bussessKey.split("\\.");
//
//		JSONObject jsonobject = new JSONObject();
//		try {
//			if ("Merchant".equals(var[0])) {
//
//				MerchantVo info = merchantService.findMerchantInfo(var[1]);
//
//				List<String> paths = auditorService.findByidScanPath(var[1]);
//				List<String> certiPath = new ArrayList<String>();
//				for (String path : paths) {
//					String[] scanpath = path.split(",");
//					if (scanpath.length > 1) {
//						certiPath.add(scanpath[0]);
//						certiPath.add(scanpath[1]);
//					} else {
//						certiPath.add(path);
//					}
//				}
//				jsonobject.put("info", info);
//				jsonobject.put("idcard1", certiPath.get(0));
//				jsonobject.put("idcard2", certiPath.get(1));
//				jsonobject.put("businessImage", certiPath.get(2));
//				jsonobject.put("certAttribute", certiPath.get(3));
//				jsonobject.put("result", "SUCCESS");
//			}
//		} catch (Exception e) {
//			jsonobject.put("result", "fail");
//
//			logger.error("预览申请单异常", e);
//		}
//
//		return jsonobject.toJSONString();
//	}
//	
	/**
	 * 根据URL跳转预览页面
	 * @param taskId
	 * @param attr
	 * @return
	 */
	@RequestMapping(ApplicationFormPath.URL)
	public ModelAndView goToUrl(String id,String procDefId,RedirectAttributes attr){
		
		String applicationName = null;
		if(procDefId.startsWith("process.accounting.adjust")){
			applicationName = "process.accounting.adjust";
		}
		if(procDefId.startsWith("merchantAudit")){
			
			applicationName = "merchantAudit";
		}
		ActViewUrl actViewUrl = applicationFormService.selectByApplicationName(applicationName);
		
		attr.addAttribute("processInstanceId", id);
		return new ModelAndView("redirect:"+actViewUrl.getUrl());
		
	}
	
	/**
	 * 申请单预览
	 * @param request
	 * @return
	 */
	@RequestMapping(ApplicationFormPath.VIEW)
	public ModelAndView waitView(HttpServletRequest request){
		ModelAndView mv = new ModelAndView(ApplicationFormPath.BASE+ApplicationFormPath.VIEW);
		
		logger.info("申请单预览......");
		
		try {
			String processInstanceId = request.getParameter("processInstanceId");
			
			ActHiProcinst actHiProcinst = applicationFormService.selectActHiProcinstById(processInstanceId);
			String id = actHiProcinst.getBusinessKey().split("\\.")[1];
			MerchantVo info = merchantService.findMerchantInfo(id);
			
			List<String> paths = auditorService.findByidScanPath(id);
			List<String> certiPath = new ArrayList<String>();
			for(String path: paths){
				String[] scanpath = path.split(";");
				if(scanpath.length>1){
					certiPath.add(scanpath[0]);
					certiPath.add(scanpath[1]);
				}
				else{
					certiPath.add(path);
				}
			}
			
//			String address[] = info.getBusinessRegAddr().split(",");
//			
//			ProvinceCityBean provinceCityBean =cityService.findProvineAndCity(address[0], address[1]);
//			
//			String businessRegAddr = provinceCityBean.getProvinceName()+ provinceCityBean.getCityName();
//			String feeCode = info.getFeeCode();
			Bank bank = new Bank();
//			Rule rule = ruleService.selectRuleByFeeCode(feeCode);
			mv.addObject("banklist", bankMapper.selectBanks(bank));
//			mv.addObject("ruleType", rule.getFeeType());
//			mv.addObject("businessRegAddr", businessRegAddr);
			mv.addObject("idcard1", certiPath.get(2));
			mv.addObject("idcard2", certiPath.get(3));
			mv.addObject("businessImage", certiPath.get(0));
			mv.addObject("certAttribute", certiPath.get(1));
			mv.addObject("info", info);
		} catch (Exception e) {
			logger.error("转到预览界面失败",e);
		}
		
		return mv;
	}
}
