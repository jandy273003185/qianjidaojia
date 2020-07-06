package com.qifenqian.bms.platform.web.admin.user;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.platform.web.admin.role.bean.Role;
import com.qifenqian.bms.platform.web.admin.role.mapper.RoleMapper;
import com.qifenqian.bms.platform.web.admin.role.type.RoleIsValid;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.user.service.UserService;
import com.qifenqian.bms.platform.web.admin.user.type.UserStatus;
import com.qifenqian.bms.platform.web.admin.userRole.service.UserRoleService;

/**
 * 用户管理控制层
 * 
 * @project gyzb-platform-web-admin
 * @fileName UserController.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
@Controller
@RequestMapping(UserPath.BASE)
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private UserRoleService userRoleService;

	/**
	 * 进入用户列表页面
	 * 
	 * @return
	 */
	@RequestMapping(UserPath.LIST)
	public ModelAndView list(User requestUser) {
		// 返回视图
		ModelAndView mv = new ModelAndView(UserPath.BASE + UserPath.LIST);
		Role role = new Role();
		role.setIsValid(RoleIsValid.Y);
		mv.addObject("userList", JSONObject.toJSON(userService.selectUserListByPage(requestUser)));
		mv.addObject("roleList", roleMapper.selectRoleList(role));
		mv.addObject("userBean", requestUser);
		// 返回
		return mv;
	}

	/**
	 * 用户新增
	 * 
	 * @param requestUser
	 * @return
	 */
	@RequestMapping(UserPath.ADD)
	@ResponseBody
	public String add(User requestUser, String role) {
		// 请求bean 打印
		logger.info("请求保存user：[{}]", JSONObject.toJSONString(requestUser, true));

		JSONObject jsonObject = new JSONObject();
		try {

			if (StringUtils.isEmpty(role)) {
				jsonObject.put("result", "FAILURE");
				jsonObject.put("message", "角色为空");
			}
			if (userService.userNameIsExists(requestUser.getUserName())) {
				jsonObject.put("result", "FAILURE");
				jsonObject.put("message", "用户名已存在");
			} else {
				// 保存新增
				requestUser.setStatus(UserStatus.VALID);
				userService.saveAddUser(requestUser, role);
				jsonObject.put("result", "SUCCESS");
			}
		} catch (Exception e) {
			logger.error("新增用户异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 用户删除
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(UserPath.DELETE)
	@ResponseBody
	public String delete(int userId) {
		// 请求bean 打印
		logger.info("请求用户编号：[{}]", userId);

		JSONObject jsonObject = new JSONObject();
		try {
			// 删除
			userService.deleteUserById(userId);
			jsonObject.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("删除用户异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 用户修改
	 * 
	 * @param requestUser
	 * @return
	 */
	@RequestMapping(UserPath.EDIT)
	@ResponseBody
	public String edit(User requestUser, String role) {
		// 请求bean 打印
		logger.info("请求修改user：[{}]", JSONObject.toJSONString(requestUser, true));

		JSONObject jsonObject = new JSONObject();

		if (StringUtils.isEmpty(requestUser.getPassword())) {
			throw new IllegalArgumentException("密码为空");
		}
		try {
			if (userService.userNameAndIdIsExists(requestUser)) {
				jsonObject.put("result", "FAILURE");
				jsonObject.put("message", "用户名已被使用");
			} else {
				String userPwd = userService.getUserPwd(requestUser.getUserId());

				if (requestUser.getPassword().equals(userPwd)) {
					logger.info("密码相同时不做操作 userPwd equels getUserPwd " + userPwd);
					requestUser.setPassword("");
				}
				userService.updateUserById(requestUser, role);
				jsonObject.put("result", "SUCCESS");
			}
		} catch (Exception e) {
			logger.error("修改用户异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 查找用户角色
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping(UserPath.SEARCH)
	@ResponseBody
	public String selectRole(int userId) {

		logger.info("请求查找用户角色");

		JSONObject jsonObject = new JSONObject();
		try {
			List<Role> userRole = userRoleService.selectUserRole(userId);
			String roleId = "";
			String roleName = "";
			if (null != userRole && !userRole.isEmpty()) {
				for (Role role : userRole) {
					roleId = roleId + role.getRoleId() + ",";
					roleName = roleName + role.getRoleName() + ",";
				}
				roleId = roleId.substring(0, roleId.length() - 1);
				roleName = roleName.substring(0, roleName.length() - 1);
			}
			jsonObject.put("result", "SUCCESS");
			jsonObject.put("roleId", roleId);
			jsonObject.put("roleName", roleName);
		} catch (Exception e) {
			logger.error("查找异常", e);
			jsonObject.put("result", "FAILURE");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
}
