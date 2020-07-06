package com.qifenqian.bms.workflow.user.controller;

import java.util.Arrays;
import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;
import org.activiti.engine.identity.User;
import org.activiti.engine.impl.persistence.entity.UserEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.workflow.user.bean.ActIdUser;
import com.qifenqian.bms.workflow.user.service.ActIdUserService;

@Controller
@RequestMapping(UserPath.BASE)
public class UsersController {
	
	private static  Logger  logger = LoggerFactory.getLogger(UsersController.class);
	
	@Autowired
	private IdentityService identityService;
	
	@Autowired
	private ActIdUserService actIdUserService;
	
	/**
	 * 用户列表
	 * @param group
	 * @return
	 */
	@RequestMapping(UserPath.LIST)
	public ModelAndView list(ActIdUser user){
		
		logger.info("用户列表查询");
		ModelAndView mv = new ModelAndView(UserPath.BASE+UserPath.LIST);
		List<ActIdUser> users = actIdUserService.selectListActUser(user);
		
		/*if(user != null && !"".equals(user.getId()) && null != user.getId()  ){
			users =  identityService.createUserQuery().userId(user.getId()).list();
		}
		else{
			users =  identityService.createUserQuery().list();
		}*/
		
		List<Group> groups = identityService.createGroupQuery().list();
		
		mv.addObject("users", JSONObject.toJSON(users));
		mv.addObject("user",user);
		mv.addObject("groups", groups);
		 return mv;
	}
	
	@RequestMapping(UserPath.USERINGROUP)
	@ResponseBody
	public String userWithGroup(String id){
		JSONObject jo = new JSONObject();
		try {
			List<Group> userGroups = identityService.createGroupQuery().groupMember(id).list();
			String resultString="" ;
			for(Group g: userGroups){
				resultString +=g.getId()+",";
			}
			
			jo.put("resultString", resultString.substring(0,resultString.length()-1));
			jo.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("更新用户失败！",e);
			jo.put("result", "FAIL");
		}
		return jo.toJSONString();
		
	}
	
	@RequestMapping(UserPath.UPDATEUSER)
	@ResponseBody
	public String update(String id,String group){
		JSONObject jo = new JSONObject();
		try {
			List<Group> userGroups = identityService.createGroupQuery().groupMember(id).list();
			
			for(Group g: userGroups){
				identityService.deleteMembership(id, g.getId());
			}
			
			String[] newGroups = group.split(",");
			
			for(String s: newGroups){
				identityService.createMembership(id, s);
			}
			jo.put("result", "SUCCESS");
		} catch (Exception e) {
			
			jo.put("result", "FAIL");
		}
		return jo.toJSONString();
		
	}
	
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	@RequestMapping(UserPath.ADD)
	@ResponseBody
	public String add(UserEntity user,String userInGroup){
		
		logger.info("添加用户组");
		JSONObject object = new JSONObject();
		User userAdd = null;
		try {
			userAdd = identityService.newUser(user.getId());
			userAdd.setFirstName(user.getFirstName());
			userAdd.setLastName(user.getLastName());
			
			identityService.saveUser(userAdd);
			logger.info("添加用户{}成功",userAdd.getId());
			
			object.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("添加用户组失败！",e);
			object.put("result", "FAILE");
		}
		
		//用户添加用户组
		if(!"".equals(userInGroup)&& null != userInGroup){
			
			String[] groups = userInGroup.split(",");
			List<String> groupList = Arrays.asList(groups);
			
			for(String groupName: groupList){
				logger.info("用户加入用户组{}",groupName);
				identityService.createMembership(userAdd.getId(), groupName);
			}
		}
	
		 return object.toJSONString();
	}
	
	
	/**
	 * 删除用户组
	 * @param group
	 * @return
	 */
	@RequestMapping(UserPath.DELETE)
	@ResponseBody
	public String delete(UserEntity user){
		
		logger.info("删除用户");
		JSONObject object = new JSONObject();
		
		try {
			identityService.deleteUser(user.getId());
						
			object.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("删除用户失败！",e);
			object.put("result", "FAILE");
		}
		
		 return object.toJSONString();
	}
}
