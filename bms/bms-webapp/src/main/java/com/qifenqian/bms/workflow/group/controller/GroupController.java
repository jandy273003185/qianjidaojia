package com.qifenqian.bms.workflow.group.controller;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.impl.persistence.entity.GroupEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.workflow.group.bean.ActIdGroup;
import com.qifenqian.bms.workflow.group.service.ActIdGroupService;
/**
 * 工作流用户组
 * @author wulingtong
 *
 */

@Controller
@RequestMapping(GroupPath.BASE)
public class GroupController {
	
	private static  Logger  logger = LoggerFactory.getLogger(GroupController.class);
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private ActIdGroupService actIdGroupService;
	
	/**
	 * 用户组列表
	 * @param group
	 * @return
	 */
	@RequestMapping(GroupPath.LIST)
	public ModelAndView list(ActIdGroup group){
		
		logger.info("用户列表查询");
		ModelAndView mv = new ModelAndView(GroupPath.BASE+GroupPath.LIST);
		List<ActIdGroup> groups = actIdGroupService.selectListActIdGroup(group);
		
		mv.addObject("groups", groups);
		mv.addObject("group",group);
		 return mv;
	}
	
	/**
	 * 添加用户组
	 * @param group
	 * @return
	 */
	@RequestMapping(GroupPath.ADD)
	@ResponseBody
	public String add(GroupEntity group){
		
		logger.info("添加用户组");
		JSONObject object = new JSONObject();
		
		try {
			Group groupAdd = identityService.newGroup(group.getName());
			groupAdd.setName(group.getName());
			identityService.saveGroup(groupAdd);
			logger.info("添加用户组{}成功",groupAdd.getName());
			
			object.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("添加用户组失败！",e);
			object.put("result", "FAILE");
		}
		
		 return object.toJSONString();
	}
	
	
	/**
	 * 删除用户组
	 * @param group
	 * @return
	 */
	@RequestMapping(GroupPath.DELETE)
	@ResponseBody
	public String delete(GroupEntity group){
		
		logger.info("删除用户组");
		JSONObject object = new JSONObject();
		
		try {
			identityService.deleteGroup(group.getId());
						
			object.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("删除用户组失败！",e);
			object.put("result", "FAILE");
		}
		
		 return object.toJSONString();
	}
}
