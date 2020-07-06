package com.qifenqian.bms.myworkspace.overAudit;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.mapper.BankMapper;
import com.qifenqian.bms.basemanager.city.service.CityService;
import com.qifenqian.bms.basemanager.merchant.service.AuditorService;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.myworkspace.allTask.service.AllTaskService;
import com.qifenqian.bms.myworkspace.overAudit.bean.OverAuditBean;
import com.qifenqian.bms.myworkspace.overAudit.service.OverAuditService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@RequestMapping(OverAuditPath.BASE)
@Controller
public class OverAuditController {

	private static Logger logger = LoggerFactory.getLogger(OverAuditController.class);

	@Autowired
	private OverAuditService overAuditService;

	@Autowired
	private MerchantService merchantService;

	@Autowired
	private AuditorService auditorService;

	@Autowired
	private AllTaskService allTaskService;

	@Autowired
	private BankMapper bankMapper;

	@Autowired
	private CityService cityService;

	/**
	 * 已审核列表
	 * 
	 * @return
	 */
	@RequestMapping(OverAuditPath.LIST)
	public ModelAndView overAudit(OverAuditBean bean) {
		logger.info("查询已审核对象{}", JSONObject.toJSONString(bean, true));
		bean.setUserId(String.valueOf(WebUtils.getUserInfo().getUserId()));
		ModelAndView mv = new ModelAndView(OverAuditPath.BASE + OverAuditPath.LIST);
		List<OverAuditBean> actHiProcinst = overAuditService.getOverAudit(bean);
		Bank bank = new Bank();
		mv.addObject("overAudits", actHiProcinst);
		mv.addObject("banklist", bankMapper.selectBanks(bank));
		mv.addObject("provincelist", cityService.selectAllProvince());
		mv.addObject("id", bean.getId());
		mv.addObject("startUserId", bean.getStartUserId());
		mv.addObject("auditBeginTime", bean.getAuditBeginTime());
		mv.addObject("auditEndTime", bean.getAuditEndTime());
		return mv;
	}

	/**
	 * 预览申请单
	 * 
	 * @param request
	 * @return
	 */
	// @RequestMapping(OverAuditPath.VIEW)
	// @ResponseBody
	// public String viewForm(HttpServletRequest request) {
	//
	// logger.info("预览申请单");
	// String taskId = request.getParameter("taskId");
	//
	// String bussessKey = allTaskService.getTaskByTaskId(taskId);
	// String[] var = bussessKey.split("\\.");
	//
	// JSONObject jsonobject = new JSONObject();
	// try {
	// if ("Merchant".equals(var[0])) {
	//
	// MerchantVo info = merchantService.findMerchantInfo(var[1]);
	//
	// List<String> paths = auditorService.findByidScanPath(var[1]);
	// List<String> certiPath = new ArrayList<String>();
	// for (String path : paths) {
	// String[] scanpath = path.split(",");
	// if (scanpath.length > 1) {
	// certiPath.add(scanpath[0]);
	// certiPath.add(scanpath[1]);
	// } else {
	// certiPath.add(path);
	// }
	// }
	// jsonobject.put("info", info);
	// jsonobject.put("idcard1", certiPath.get(0));
	// jsonobject.put("idcard2", certiPath.get(1));
	// jsonobject.put("businessImage", certiPath.get(2));
	// jsonobject.put("certAttribute", certiPath.get(3));
	// jsonobject.put("result", "SUCCESS");
	// }
	// } catch (Exception e) {
	// jsonobject.put("result", "fail");
	//
	// logger.error("预览申请单异常", e);
	// }
	//
	// return jsonobject.toJSONString();
	// }
}
