package com.qifenqian.bms.basemanager.market.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.market.bean.Manager;
import com.qifenqian.bms.basemanager.market.mapper.ManagerMapper;
import com.qifenqian.bms.basemanager.market.service.ManagerService;
import com.qifenqian.bms.basemanager.market.service.MarketAuthoService;
import com.qifenqian.bms.basemanager.market.utils.JSONUtil;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

import net.sf.json.JSONArray;


@Controller
@RequestMapping(ManagerPath.BASE)
public class ManagetController {
	
	
	private Logger logger = LoggerFactory.getLogger(ManagetController.class);
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private MarketAuthoService marketAuthoService;
	
	@Autowired
	private ManagerMapper managerMapper;
	
	/**
	 * 市场部分组列表
	 * @param manager
	 * @return
	 */
	@RequestMapping(ManagerPath.LIST)
	public ModelAndView list(Manager manager){
		ModelAndView mv = new ModelAndView(ManagerPath.BASE + ManagerPath.LIST);
		List<Manager> managerList = managerService.selectManagerList(manager);
		List<User> marketRoleList = managerService.listInfo();
		Integer userId = WebUtils.getUserInfo().getUserId();
		mv.addObject("marketRoleList", JSONObject.toJSON(marketRoleList));
		mv.addObject("managerList", JSONObject.toJSON(managerList));
		mv.addObject("manager", manager);	
		return mv;
		
	}
	
	@RequestMapping(ManagerPath.EDIT)
	@ResponseBody
	public String edit(Manager updateManager){
		logger.info("修改市场部分组负责人",JSONObject.toJSONString(updateManager,true));
		JSONObject jsonObject = new JSONObject();
		Manager manager = managerService.getManager(updateManager.getUserCode());
		try {
			int a = WebUtils.getUserInfo().getUserId();
			manager.setUpdateId(String.valueOf(String.valueOf(WebUtils.getUserInfo().getUserId())));
			//无团队负责人时
			if("".equals(manager.getTeamLeaderId()) || null ==manager.getTeamLeaderId()){
				manager.setTeamLeaderId(updateManager.getTeamLeaderId());
				managerService.updateManagerList(manager);
				jsonObject.put("result", "SUCCESS");
			}else if(manager.getTeamLeaderId().equals(manager.getUpdateId())){
				manager.setTeamLeaderId(updateManager.getTeamLeaderId());
				managerService.updateManagerList(manager);
				jsonObject.put("result", "SUCCESS");
			}else {
				jsonObject.put("result", "fail");
				jsonObject.put("message", "您无权限修改！");
			}
			
		} catch (Exception e) {
			logger.error("修改负责人异常", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		
		return jsonObject.toJSONString();
		
		
	}
	

	@RequestMapping(ManagerPath.ADD)
	public ModelAndView add(Manager manager){
		ModelAndView mv = new ModelAndView(ManagerPath.BASE + ManagerPath.ADD);
		
			manager.setUpdateId(String.valueOf(String.valueOf(WebUtils.getUserInfo().getUserId())));
			//查询市场经理
			List<Manager> addManagerList = managerService.selectNewManagerList(manager);
			
			mv.addObject("addManagerList", JSONObject.toJSON(addManagerList));
			mv.addObject("manager", manager);
		return mv;
	}
	
	@RequestMapping(ManagerPath.ADDBATCH)
	@ResponseBody
	public String addbatch(String marketManages){
		System.out.println(marketManages.toString());
		logger.info("添加市场经理",JSONObject.toJSONString(marketManages,true));
		JSONObject jsonObject = new JSONObject();
		List<Manager> managerList = JSONUtil.jsonArrayToList(JSONArray.fromObject(marketManages),new Manager(), "yyyy-MM-dd hh:mm:ss");	
		try {
			for (Manager manager : managerList) {
				List<Manager> managers =managerMapper.selectManagerList(manager);
				if(managers.size() == 0){
					managerMapper.insertNewManagerList(manager);
				}
			}
			jsonObject.put("result", "SUCCESS");
		}catch(Exception e) {
			logger.error("添加客户经理异常:"+e.toString());
			jsonObject.put("result", e.toString());
		}
		return jsonObject.toJSONString();
		
		
	}
	
//	@RequestMapping(ManagerPath.ADDBATCH)
//	@ResponseBody
//	public String addbatch(Manager manager){
//		logger.info("添加市场经理",JSONObject.toJSONString(manager,true));
//		JSONObject jsonObject = new JSONObject();
//		try {
//			MarketRole role = new MarketRole();
//			//获取当前用户角色
//			role.setUserCode(String.valueOf(String.valueOf(WebUtils.getUserInfo().getUserCode())));
//			List<MarketRole> market =  managerMapper.selectManangerJurisdiction(role);
//			//判断是否有权限添加市场经理
//			if(market.get(0).getUserRole().equals("104") && market.get(0).getUserRole().equals("105")){
//				//判断是否存在此表，如果不存在就添加，存在则不添加
//				List<Manager> managers =managerMapper.selectManagerList(manager);
//				if(managers.size() == 0){
//					managerMapper.insertNewManagerList(manager);
//					jsonObject.put("result", "SUCCESS");
//				}else{
//					jsonObject.put("result", "fail");
//					jsonObject.put("message", "此市场经理已经添加");
//				}
//			}else {
//				jsonObject.put("result", "fail");
//				jsonObject.put("message", "暂无权限添加");
//			}
//		} catch (Exception e) {
//			logger.error("添加市场经理异常", e);
//			jsonObject.put("result", "fail");
//			jsonObject.put("message", e.getMessage());
//		}
//		
//		return jsonObject.toJSONString();
//		
//		
//	}
//	

}
