package com.qifenqian.bms.platform.web.myWorkSpace;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.mapper.BankMapper;
import com.qifenqian.bms.basemanager.city.bean.ProvinceCityBean;
import com.qifenqian.bms.basemanager.city.service.CityService;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.service.AuditorService;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.rule.bean.Rule;
import com.qifenqian.bms.basemanager.rule.service.RuleService;
import com.qifenqian.bms.myworkspace.WorkFlowHelper;
import com.qifenqian.bms.platform.web.myWorkSpace.bean.WaitTaskBean;
import com.qifenqian.bms.platform.web.myWorkSpace.service.WorkSpaceService;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.user.service.UserService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Controller
@RequestMapping(WorkSpacePath.BASE)
public class WorkSpaceController {
	private Logger logger = LoggerFactory.getLogger(WorkSpaceController.class);
	
	@Autowired
	private WorkSpaceService workSpaceService;
	
	@Autowired
	private WorkFlowHelper workFlowHelper;
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private AuditorService auditorService;
	
	@Autowired
	private BankMapper bankMapper;
	@Autowired
	private RuleService ruleService;
	@Autowired
	private CityService cityService;
	@Autowired
	private UserService userService;

	
	@RequestMapping(WorkSpacePath.TASKS)
	public ModelAndView taskList(WaitTaskBean bean){
		
		bean.setUserId(String.valueOf(WebUtils.getUserInfo().getUserId()));
		ModelAndView mv = new ModelAndView(WorkSpacePath.BASE+WorkSpacePath.TASKS);
		
		List<WaitTaskBean> tasks = workSpaceService.getTasks(bean);
		mv.addObject("id", bean.getId());
		mv.addObject("name", bean.getName());
		mv.addObject("startUser", bean.getStartUser());
		mv.addObject("taskBeginTime", bean.getTaskBeginTime());
		mv.addObject("taskEndTime", bean.getTaskEndTime());
		return mv.addObject("tasks", tasks);
	}
	
	/**
	 * 跳转到相应的页面
	 */
	@RequestMapping(WorkSpacePath.URL)
	public ModelAndView goToUrl(String taskId,RedirectAttributes attr){
		String url = workSpaceService.getUrl(taskId);
	
		attr.addAttribute("taskId", taskId);
		return new ModelAndView("redirect:"+url);
		
	}
	
	/**
	 * 商户审核
	 * @return
	 */
	@RequestMapping(WorkSpacePath.AUDIT)
	public ModelAndView auditRegist(HttpServletRequest request){
		ModelAndView mv = new ModelAndView(WorkSpacePath.BASE+WorkSpacePath.AUDIT);
		
		logger.info("转到商户审核界面......");
		
		try {
			String taskId = request.getParameter("taskId");
			
			String id = this.getId(taskId);
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
//			String feeCode = info.getFeeCode();
			Bank bank = new Bank();
//			Rule rule = ruleService.selectRuleByFeeCode(feeCode);
			mv.addObject("banklist", bankMapper.selectBanks(bank));
//			mv.addObject("ruleType", rule.getFeeType());
			mv.addObject("taskId", taskId);
			mv.addObject("idcard1", certiPath.get(2));
			mv.addObject("idcard2", certiPath.get(3));
			mv.addObject("businessImage", certiPath.get(0));
			mv.addObject("certAttribute", certiPath.get(1));
			mv.addObject("info", info);
		} catch (Exception e) {
			logger.error("转到商户审核界面失败",e);
		}
		
		return mv;
	}
	
	/**
	 * 读取图片
	 * @param request
	 * @param response
	 * @param path
	 */
	@RequestMapping(WorkSpacePath.SHOW)
	public void showPicture(HttpServletRequest request,HttpServletResponse response,String path){
		
		logger.info("开始读取图片[{}]",path);
		
		ServletOutputStream out = null;
		FileInputStream ips = null; 
		try {
			ips = new FileInputStream(new File(path));
			response.setContentType("image/*"); 
			out=response.getOutputStream();
			int i = ips.available();//文件大小
			
			byte[] date = new byte[i];
			
			ips.read(date);
			ips.close();
			
			out.write(date);
			out.close();
			logger.info("图片[{}]读取成功",path);
		} catch (FileNotFoundException e) {  
			logger.error("读取图片[{}]失败",path);
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
		
	}
	
	/**
	 * 注册修改页面
	 * @return
	 */
	@RequestMapping(WorkSpacePath.REGIST)
	public ModelAndView turnToRegist(HttpServletRequest request){
		logger.info("跳转到注册修改页面......");
		
		ModelAndView mv =new ModelAndView("/merchant/regist/registEdit");
		try {
			String taskId = request.getParameter("taskId");
			
			Map<String,Object> values= workSpaceService.findPathAndInfo(taskId);
			List<String> paths = (List<String>)values.get("path");
			MerchantVo info = (MerchantVo)values.get("MerchantVo");
			
			Rule rule = ruleService.selectRuleByFeeCode(info.getFeeCode());
			
			mv.addObject("taskId", taskId);
			mv.addObject("idcard1", paths.get(2));
			mv.addObject("idcard2", paths.get(3));
			mv.addObject("businessImage", paths.get(0));
			mv.addObject("certAttribute", paths.get(1));
			//文件名称
			mv.addObject("businessImageName",paths.get(0).substring(paths.get(0).lastIndexOf(File.separator)+1));
			mv.addObject("certAttributeName",paths.get(1).substring(paths.get(1).lastIndexOf(File.separator)+1));
			mv.addObject("idcard1Name", paths.get(2).substring(paths.get(2).lastIndexOf(File.separator)+1));
			mv.addObject("idcard2Name", paths.get(3).substring(paths.get(3).lastIndexOf(File.separator)+1));
			
			mv.addObject("info", info);
			User user = new User();
			mv.addObject("userlist", userService.getUserList(user));
			
			Bank bank=new Bank();
			mv.addObject("banklist",bankMapper.selectBanks(bank));
			mv.addObject("feeType", rule.getFeeType());
			mv.addObject("provincelist", cityService.selectAllProvince());
			logger.info("跳转到注册修改页面成功");
		} catch (Exception e) {
			logger.error("跳转到注册修改页面失败",e);
		}
		
		return mv;
	}
	
	
	/**
	 * 流程注册修改
	 * @param request
	 * @param merchantVo
	 * @param paths
	 * @param taskId
	 */
	@RequestMapping(WorkSpacePath.UPDATEREGIST)
	@ResponseBody
	public String updateRegist(HttpServletRequest request,MerchantVo merchantVo,String taskId,String custId){
		logger.info("修改商户信息");
		JSONObject jsonObject = new JSONObject();
		
		
		
		try {
			
			String businessType = request.getParameter("businessType");
			String certAttributeType1 = request.getParameter("certAttributeType1");
			String idCardType_1 = request.getParameter("idCardType_1");
			String idCardType_2 = request.getParameter("idCardType_2");
			
			Map<String,String> filePath = new HashMap<String, String>();
			filePath.put("businessType", businessType);
			filePath.put("certAttributeType1", certAttributeType1);
			filePath.put("idCardType_1", idCardType_1);
			filePath.put("idCardType_2", idCardType_2);
			workSpaceService.updateRegist(custId,  merchantVo,filePath);
			
			logger.info("修改商户[{}]信息成功",custId);
			String auditName = request.getParameter("auditName");
			//流程到下一步
			Map<String ,Object> var = new LinkedHashMap<String ,Object>();
			var.put("auditId", auditName);
			workFlowHelper.endTask(taskId, var);
			jsonObject.put("result", "SUCCESS");
			
		} catch (Exception e) {
			logger.error("修改商户[{}]信息失败",custId,e);
			jsonObject.put("result", "FAIL");
			jsonObject.put("message", e.getMessage());
		}
		
		return  jsonObject.toJSONString();
	}
	
	/**
	 * 代办任务预览
	 * @param request
	 * @return
	 */
	@RequestMapping(WorkSpacePath.VIEW)
	public ModelAndView waitView(HttpServletRequest request){
		ModelAndView mv = new ModelAndView(WorkSpacePath.BASE+WorkSpacePath.VIEW);
		
		logger.info("代办任务预览......");
		
		try {
			String taskId = request.getParameter("taskId");
			
			String id = this.getId(taskId);
			
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
			
			String address[] = info.getBusinessRegAddr().split(";");
			
			ProvinceCityBean provinceCityBean =cityService.findProvineAndCity(address[0], address[1]);
			
			String businessRegAddr = provinceCityBean.getProvinceName()+ provinceCityBean.getCityName();
//			String feeCode = info.getFeeCode();
//			Rule rule = ruleService.selectRuleByFeeCode(feeCode);
			
//			mv.addObject("ruleType", rule.getFeeType());
			mv.addObject("taskId", taskId);
			mv.addObject("businessRegAddr", businessRegAddr);
			mv.addObject("idcard1", certiPath.get(0));
			mv.addObject("idcard2", certiPath.get(1));
			mv.addObject("businessImage", certiPath.get(2));
			mv.addObject("certAttribute", certiPath.get(3));
			mv.addObject("info", info);
		} catch (Exception e) {
			logger.error("转到预览界面失败",e);
		}
		
		return mv;
	}
	
	
	public String getId(String taskId){
		
		Task task = workFlowHelper.getTask(taskId);
		ProcessInstance pi=	workFlowHelper.getProcessInstance(task.getProcessInstanceId());
		String bussessKey = pi.getBusinessKey();
		String id= "";
		if(bussessKey!=null && bussessKey.length()>0){
			String key[]= bussessKey.split("\\.");
			id = key[1];
		}
		return id;
	}
}
