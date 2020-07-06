package com.qifenqian.bms.process;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qifenqian.bms.platform.web.admin.user.mapper.UserMapper;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.city.service.CityService;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.qifenqian.bms.process.bean.ProcessAudit;
import com.qifenqian.bms.process.bean.ProcessManagement;

@Controller
@RequestMapping("/process")
public class ProcessController {
	
	private Logger logger = LoggerFactory.getLogger(ProcessController.class);
	
	@Autowired
	private ProcessService processService;
	
	@Autowired
	private MerchantMapper merchantMapper;
	
	@Autowired
	private CityService cityService;

	@Autowired
	private UserMapper userMapper;

	
	/**
	 * 流程申请页面
	 * @return
	 */
	@RequestMapping("/apply")
	public ModelAndView view(HttpServletRequest request,HttpServletResponse response) {
		// 返回视图
		ModelAndView mv = new ModelAndView("/process/apply");
		User user = WebUtils.getUserInfo();
		mv.addObject("provincelist_", cityService.selAllProvince());
		mv.addObject("agentList", merchantMapper.selectAgent());
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		mv.addObject("queryBean", user);
		mv.addObject("nowTime", dateString);
		
		return mv;
	}
	
	
	/**
	 * 提交流程
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String application(HttpServletRequest request,ProcessManagement processManagement) {
		logger.info("----------提交流程开始----------");
		JSONObject object = new JSONObject();
		User user = WebUtils.getUserInfo();
		processManagement.setDeptId(String.valueOf(user.getDeptId()));
		processManagement.setUserName(user.getRealName());
		processManagement.setUserId(String.valueOf(user.getUserId()));
		try {
			if(processManagement.getModelInfoList() != null) {
				for(int i=0;i<processManagement.getModelInfoList().size();i++){
					
					DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
					Calendar calendar = Calendar.getInstance();
					processManagement.setProcessId(df.format(calendar.getTime()) + GenSN.getRandomNum(5));
					processManagement.setModel((String)processManagement.getModelInfoList().get(i).get("model"));
					processManagement.setDemandNum((String)processManagement.getModelInfoList().get(i).get("demandNum"));
					
					processService.insertApplicationAndAudit(processManagement);
				}	
			}else {
				DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
				Calendar calendar = Calendar.getInstance();
				processManagement.setProcessId(df.format(calendar.getTime()) + GenSN.getRandomNum(5));
				processService.insertApplicationAndAudit(processManagement);
			}
			
			object.put("result", "SUCCESS");
			object.put("message", "审核完成");
		} catch (Exception e) {
			object.put("status", "FAIL");
			object.put("message", e);
			logger.info("" + e);
		}
		return object.toString();
	}
	
	
	/**
	 * 我的发起页面
	 * @return
	 */
	@RequestMapping("/initiate")
	public ModelAndView initiateView(HttpServletRequest request,HttpServletResponse response) {
		// 返回视图
		ModelAndView mv = new ModelAndView("/process/initiate");
		User user = WebUtils.getUserInfo();
		ProcessAudit processAudit = new ProcessAudit();
		processAudit.setDeptId(String.valueOf(WebUtils.getUserInfo().getDeptId()));
		processAudit.setUserId(String.valueOf(WebUtils.getUserInfo().getUserId()));;
		//发起数据
		List<ProcessAudit> myInitiateList = processService.getMyInitiateList(processAudit);
		mv.addObject("num", myInitiateList.size());
		mv.addObject("myInitiateList", myInitiateList);
		//未归档
		List<ProcessAudit> myUnInitiateList =processService.getUnMyInitiateList(processAudit);
		mv.addObject("num2", myUnInitiateList.size());
		mv.addObject("myUnInitiateList", myUnInitiateList);
		//归档数据
		mv.addObject("num1", (myInitiateList.size()-myUnInitiateList.size()));
		mv.addObject("user", user);
		return mv;
	}
	
	/**
	 * 我的发起页面未归档
	 * @return
	 */
	@RequestMapping("/unInitiate")
	public ModelAndView unInitiateView(HttpServletRequest request,HttpServletResponse response) {
		// 返回视图
		ModelAndView mv = new ModelAndView("/process/unInitiate");
		User user = WebUtils.getUserInfo();
		ProcessAudit processAudit = new ProcessAudit();
		processAudit.setDeptId(String.valueOf(WebUtils.getUserInfo().getDeptId()));
		processAudit.setUserId(String.valueOf(WebUtils.getUserInfo().getUserId()));;
		//发起数据
		List<ProcessAudit> myInitiateList = processService.getMyInitiateList(processAudit);
		mv.addObject("num", myInitiateList.size());
		//未归档
		List<ProcessAudit> myUnInitiateList =processService.getUnMyInitiateList(processAudit);
		mv.addObject("num2", myUnInitiateList.size());
		mv.addObject("myUnInitiateList", myUnInitiateList);
		//归档数据
		mv.addObject("num1", (myInitiateList.size()-myUnInitiateList.size()));
		mv.addObject("user", user);
		return mv;
	}
	
	/**
	 * 我的发起页面归档
	 * @return
	 */
	@RequestMapping("/archivedInitiate")
	public ModelAndView archivedInitiateView(HttpServletRequest request,HttpServletResponse response) {
		// 返回视图
		ModelAndView mv = new ModelAndView("/process/archivedInitiate");
		User user = WebUtils.getUserInfo();
		ProcessAudit processAudit = new ProcessAudit();
		processAudit.setDeptId(String.valueOf(WebUtils.getUserInfo().getDeptId()));
		processAudit.setUserId(String.valueOf(WebUtils.getUserInfo().getUserId()));;
		//发起数据
		List<ProcessAudit> myInitiateList = processService.getMyInitiateList(processAudit);
		mv.addObject("num", myInitiateList.size());
		//归档数据
		processAudit.setCurrentNode("0");
		List<ProcessAudit> myArchivedInitiateList = processService.getMyInitiateList(processAudit);
		processAudit.setCurrentNode("1");
		List<ProcessAudit> myArchivedInitiateList1 = processService.getMyInitiateList(processAudit);
		processAudit.setCurrentNode("4");
		List<ProcessAudit> myArchivedInitiateList2 = processService.getMyInitiateList(processAudit);
		myArchivedInitiateList.addAll(myArchivedInitiateList1);
		myArchivedInitiateList.addAll(myArchivedInitiateList2);
		mv.addObject("num1", myArchivedInitiateList.size());
		mv.addObject("myArchivedInitiateList", myArchivedInitiateList);
		//未归档
		mv.addObject("num2", (myInitiateList.size() - myArchivedInitiateList.size()));
		mv.addObject("user", user);
		return mv;
	}
	
	/**
	 * 申请详细(弹框详细)
	 * @return
	 */
	@RequestMapping("/apply/detail")
	@ResponseBody
	public String selectApplyDetail(HttpServletRequest request,ProcessAudit processAudit) {
		logger.info("----------查询申请详细开始----------");
		JSONObject object = new JSONObject();
		processAudit = processService.selectApplyDetail(processAudit);
		processAudit.setAttachment("/pic/" +processAudit.getAttachment());
		object.put("processAudit", processAudit);
		return object.toString();
	}
	
	/**
	 * 流程详细
	 * @return
	 */
	@RequestMapping("/applyDetail")
	@ResponseBody
	public  ModelAndView applyDetailView(HttpServletRequest request,ProcessAudit processAudit) {
		// 返回视图
		ModelAndView mv = new ModelAndView("/process/applyDetail");
		logger.info("----------查询申请详细开始----------");
		User user = WebUtils.getUserInfo();
		processAudit = processService.selectApplyDetail(processAudit);
		processAudit.setAttachment("/pic/" +processAudit.getAttachment());
		User processUser = userMapper.selectUserSingleById(Integer.parseInt(processAudit.getUserId()));
		if(StringUtils.isNotBlank(processAudit.getFirstAuditId())){
			User firstUser = userMapper.selectUserSingleById(Integer.parseInt(processAudit.getFirstAuditId()));
			processAudit.setFirstAuditName(firstUser.getUserName());
		}
		if(StringUtils.isNotBlank(processAudit.getSecondAuditId())){
			User secondUser = userMapper.selectUserSingleById(Integer.parseInt(processAudit.getFirstAuditId()));
			processAudit.setSecondAuditName(secondUser.getUserName());
		}
		mv.addObject("processAudit", processAudit);
		mv.addObject("processUser", processUser);
		return mv;
	}
	
	
	/**
	 * 流程审核页面
	 * @return
	 */
	@RequestMapping("/applyAudit")
	@ResponseBody
	public  ModelAndView applyAuditView(HttpServletRequest request,ProcessAudit processAudit) {
		// 返回视图
		ModelAndView mv = new ModelAndView("/process/applyAudit");
		logger.info("----------查询申请详细开始----------");
		User user = WebUtils.getUserInfo();
		processAudit = processService.selectApplyDetail(processAudit);
		processAudit.setAttachment("/pic/" +processAudit.getAttachment());
		mv.addObject("processAudit", processAudit);
		return mv;
	}
	
	/**
	 * 待办事宜页面
	 * @return
	 */
	@RequestMapping("/backlog")
	public ModelAndView backlogView(HttpServletRequest request,HttpServletResponse response) {
		// 返回视图
		ModelAndView mv = new ModelAndView("/process/backlog");
		User user = WebUtils.getUserInfo();
		ProcessAudit processAudit = new ProcessAudit();
		//查询出自己权限下的当前节点
		if(5 == user.getDeptId()) {
			processAudit.setCurrentNode("2");
		}else if(4 == user.getDeptId()) {
			processAudit.setCurrentNode("3");
		}else {
			return mv;
		}
		List<ProcessAudit> myInitiateList = processService.getMyInitiateList(processAudit);
		List<ProcessAudit> myInitiateTimeOutList = new ArrayList<ProcessAudit>();
		for(int i=0;i<myInitiateList.size();i++) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date firstDate = null;  
		    Date secondDate = null; 
		    try {
		    	if(5 == user.getDeptId()) {
		    		firstDate = df.parse(myInitiateList.get(i).getProcessTime());
				}else if(4 == user.getDeptId()) {
					firstDate = df.parse(myInitiateList.get(i).getFirstAuditTime());
				}
				Date currentTime = new Date();
				String dateString = df.format(currentTime);
				secondDate = df.parse(dateString); 
				int a =(int)(secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000);
				if((a-3)>0) {
					myInitiateTimeOutList.add(myInitiateList.get(i));
				}
			} catch (ParseException e) {
				e.printStackTrace();
				mv.addObject("result", "查询超时流程错误");
				mv.addObject("message", e);
				return mv;
			} 
		}
		mv.addObject("numBack", myInitiateList.size());
		mv.addObject("numTimeOut", myInitiateTimeOutList.size());
		mv.addObject("myInitiateList", myInitiateList);
		return mv;
	}
	
	/**
	 * 待办事宜页面
	 * @return
	 */
	@RequestMapping("/backlogTout")
	public ModelAndView backlogTimeoutView(HttpServletRequest request,HttpServletResponse response) {
		// 返回视图
		ModelAndView mv = new ModelAndView("/process/backlogTout");
		User user = WebUtils.getUserInfo();
		ProcessAudit processAudit = new ProcessAudit();
		//查询出自己权限下的当前节点
		if(5 == user.getDeptId()) {
			processAudit.setCurrentNode("2");
		}else if(4 == user.getDeptId()) {
			processAudit.setCurrentNode("3");
		}else {
			return mv;
		}
		List<ProcessAudit> myInitiateList = processService.getMyInitiateList(processAudit);
		List<ProcessAudit> myInitiateTimeOutList = new ArrayList<ProcessAudit>();
		for(int i=0;i<myInitiateList.size();i++) {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date firstDate = null;  
		    Date secondDate = null; 
		    try {
		    	if(5 == user.getDeptId()) {
		    		firstDate = df.parse(myInitiateList.get(i).getProcessTime());
				}else if(4 == user.getDeptId()) {
					firstDate = df.parse(myInitiateList.get(i).getFirstAuditTime());
				}
				Date currentTime = new Date();
				String dateString = df.format(currentTime);
				secondDate = df.parse(dateString); 
				int a =(int)(secondDate.getTime() - firstDate.getTime()) / (24 * 60 * 60 * 1000);
				if((a-3)>0) {
					myInitiateTimeOutList.add(myInitiateList.get(i));
				}
			} catch (ParseException e) {
				e.printStackTrace();
				mv.addObject("result", "查询超时流程错误");
				mv.addObject("message", e);
				return mv;
			} 
		}
		mv.addObject("numBack", myInitiateList.size());
		mv.addObject("numTimeOut", myInitiateTimeOutList.size());
		mv.addObject("myInitiateTimeOutList", myInitiateTimeOutList);
		return mv;
	}
	
	
	
	
	/**
	 * 流程审核
	 * @return
	 */
	@RequestMapping("/audit")
	@ResponseBody
	public String auditApply(HttpServletRequest request,ProcessAudit processAudit,String status) {
		logger.info("----------审核开始----------");
		JSONObject object = new JSONObject();
		User user = WebUtils.getUserInfo();
		//设置日期格式
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTime = df.format(new Date());
		//是否有权限且在自己审核节点
		if(5 == user.getDeptId() && "2".equals(processAudit.getCurrentNode())) {
			if("Y".equals(status)){
				processAudit.setCurrentNode("3");
			}else if("N".equals(status)){
				processAudit.setCurrentNode("4");
			}
			processAudit.setFirstAuditId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			processAudit.setFirstAuditTime(dateTime);
			if(!StringUtils.isBlank(processAudit.getFirstAuditRecord())) {
				processAudit.setFirstAuditRecord(processAudit.getFirstAuditRecord());
			}
			
		}else if(4 == user.getDeptId() && "3".equals(processAudit.getCurrentNode())) {
			if("Y".equals(status)){
				processAudit.setCurrentNode("1");
			}else if("N".equals(status)){
				processAudit.setCurrentNode("0");
			}
			processAudit.setSecondAuditId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			processAudit.setSecondAuditTime(dateTime);
			if(!StringUtils.isBlank(processAudit.getSecondAuditRecord())) {
				processAudit.setSecondAuditRecord(processAudit.getSecondAuditRecord());
			}
			}
		
		processService.updateProcessAudit(processAudit);
		object.put("result", "SUCCESS");
		object.put("message", "审核完成");
		return object.toString();
	}
	
	
	/**
	 * 已办事宜页面
	 * @return
	 */
	@RequestMapping("/finish")
	public ModelAndView finishView(HttpServletRequest request,HttpServletResponse response) {
		// 返回视图
		ModelAndView mv = new ModelAndView("/process/finish");
		User user = WebUtils.getUserInfo();
		ProcessAudit processAudit = new ProcessAudit();
		
		//查询出自己权限下的当前节点
		if(5 == user.getDeptId()) {
			processAudit.setFirstAuditId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			//已办事宜全部
			processAudit.setCurrentNode("2");
			List<ProcessAudit> myFinishList = processService.getMyFinishList(processAudit);
			mv.addObject("num", myFinishList.size());
			mv.addObject("myFinishList", myFinishList);
			//未归档
			processAudit.setCurrentNode("3");
			List<ProcessAudit> myFinishNList = processService.getMyInitiateList(processAudit);
			mv.addObject("numN", myFinishNList.size());
			//归档数据
			processAudit.setCurrentNode("0");
			List<ProcessAudit> myFinishFList = processService.getMyInitiateList(processAudit);
			processAudit.setCurrentNode("1");
			List<ProcessAudit> myArchivedInitiateList1 = processService.getMyInitiateList(processAudit);
			processAudit.setCurrentNode("4");
			List<ProcessAudit> myArchivedInitiateList2 = processService.getMyInitiateList(processAudit);
			myFinishFList.addAll(myArchivedInitiateList1);
			myFinishFList.addAll(myArchivedInitiateList2);
			mv.addObject("numF", myFinishFList.size());
		}else if(4 == user.getDeptId()) {
			processAudit.setSecondAuditId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			processAudit.setCurrentNode("0");
			List<ProcessAudit> myFinishList = processService.getMyInitiateList(processAudit);
			processAudit.setCurrentNode("1");
			List<ProcessAudit> myFinishList1 = processService.getMyInitiateList(processAudit);
			myFinishList.addAll(myFinishList1);
			mv.addObject("num", myFinishList.size());
			mv.addObject("numF", myFinishList.size());
			mv.addObject("numN", (myFinishList.size()  - myFinishList.size()));
			mv.addObject("myFinishList", myFinishList);
		}else{
			mv.addObject("message", "暂无处理权限");
		}
		
		return mv;
	}
	
	/**
	 * 已办事宜页面未归档
	 * @return
	 */
	@RequestMapping("/finishN")
	public ModelAndView finishNView(HttpServletRequest request,HttpServletResponse response) {
		// 返回视图
		ModelAndView mv = new ModelAndView("/process/finishN");
		User user = WebUtils.getUserInfo();
		ProcessAudit processAudit = new ProcessAudit();
		//查询出自己权限下的当前节点
		if(5 == user.getDeptId()) {
			processAudit.setFirstAuditId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			//已办事宜全部
			processAudit.setCurrentNode("2");
			List<ProcessAudit> myFinishList = processService.getMyFinishList(processAudit);
			mv.addObject("num", myFinishList.size());
			//未归档
			processAudit.setCurrentNode("3");
			List<ProcessAudit> myFinishNList = processService.getMyInitiateList(processAudit);
			mv.addObject("numN", myFinishNList.size());
			mv.addObject("myFinishNList", myFinishNList);
			//归档数据
			processAudit.setCurrentNode("0");
			List<ProcessAudit> myFinishFList = processService.getMyInitiateList(processAudit);
			processAudit.setCurrentNode("1");
			List<ProcessAudit> myArchivedInitiateList1 = processService.getMyInitiateList(processAudit);
			processAudit.setCurrentNode("4");
			List<ProcessAudit> myArchivedInitiateList2 = processService.getMyInitiateList(processAudit);
			myFinishFList.addAll(myArchivedInitiateList1);
			myFinishFList.addAll(myArchivedInitiateList2);
			mv.addObject("numF", myFinishFList.size());
		}else if(4 == user.getDeptId()) {
			processAudit.setSecondAuditId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			processAudit.setCurrentNode("0");
			List<ProcessAudit> myFinishList = processService.getMyInitiateList(processAudit);
			processAudit.setCurrentNode("1");
			List<ProcessAudit> myFinishList1 = processService.getMyInitiateList(processAudit);
			myFinishList.addAll(myFinishList1);
			mv.addObject("num", myFinishList.size());
			mv.addObject("numF", myFinishList.size());
			mv.addObject("numN", (myFinishList.size() - myFinishList.size()));
			mv.addObject("myFinishList", myFinishList);
		}else{
			mv.addObject("message", "暂无处理权限");
		}
		
		return mv;
	}
	
	/**
	 * 已办事宜页面已归档
	 * @return
	 */
	@RequestMapping("/finishF")
	public ModelAndView finishFView(HttpServletRequest request,HttpServletResponse response) {
		// 返回视图
		ModelAndView mv = new ModelAndView("/process/finishF");
		User user = WebUtils.getUserInfo();
		ProcessAudit processAudit = new ProcessAudit();
		//查询出自己权限下的当前节点
		if(5 == user.getDeptId()) {
			processAudit.setFirstAuditId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			//已办事宜全部
			processAudit.setCurrentNode("2");
			List<ProcessAudit> myFinishList = processService.getMyFinishList(processAudit);
			mv.addObject("num", myFinishList.size());
			//未归档
			processAudit.setCurrentNode("3");
			List<ProcessAudit> myFinishNList = processService.getMyInitiateList(processAudit);
			mv.addObject("numN", myFinishNList.size());
			//归档数据
			processAudit.setCurrentNode("0");
			List<ProcessAudit> myFinishFList = processService.getMyInitiateList(processAudit);
			processAudit.setCurrentNode("1");
			List<ProcessAudit> myArchivedInitiateList1 = processService.getMyInitiateList(processAudit);
			processAudit.setCurrentNode("4");
			List<ProcessAudit> myArchivedInitiateList2 = processService.getMyInitiateList(processAudit);
			myFinishFList.addAll(myArchivedInitiateList1);
			myFinishFList.addAll(myArchivedInitiateList2);
			mv.addObject("numF", myFinishFList.size());
			mv.addObject("myFinishFList", myFinishFList);
		}else if(4 == user.getDeptId()) {
			processAudit.setSecondAuditId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			processAudit.setCurrentNode("0");
			List<ProcessAudit> myFinishFList = processService.getMyInitiateList(processAudit);
			processAudit.setCurrentNode("1");
			List<ProcessAudit> myFinishList1 = processService.getMyInitiateList(processAudit);
			myFinishFList.addAll(myFinishList1);
			mv.addObject("num", myFinishFList.size());
			mv.addObject("numF", myFinishFList.size());
			mv.addObject("numN", (myFinishFList.size() - myFinishFList.size()));
			mv.addObject("myFinishFList", myFinishFList);
		}else{
			mv.addObject("message", "暂无处理权限");
		}
		
		return mv;
	}
	
}
