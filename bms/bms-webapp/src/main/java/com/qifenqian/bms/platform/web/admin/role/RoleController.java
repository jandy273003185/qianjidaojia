package com.qifenqian.bms.platform.web.admin.role;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.platform.web.admin.function.service.FunctionService;
import com.qifenqian.bms.platform.web.admin.role.bean.Role;
import com.qifenqian.bms.platform.web.admin.role.service.RoleService;
import com.qifenqian.bms.platform.web.admin.rolefunction.bean.RoleFunction;
import com.qifenqian.bms.platform.web.admin.rolefunction.service.RoleFunctionService;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

/**
 * 
 * @project gyzb-platform-web-admin
 * @fileName RoleController.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
@Controller
@RequestMapping(RolePath.BASE)
public class RoleController {

	private Logger logger = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private RoleService roleService;

	@Autowired
	private FunctionService functionService;

	@Autowired
	private RoleFunctionService roleFunctionService;

	/**
	 * 角色列表查询
	 * 
	 * @param requestRole
	 * @return
	 */
	@RequestMapping(RolePath.LIST)
	public ModelAndView list(Role role) {
		ModelAndView mv = new ModelAndView(RolePath.BASE + RolePath.LIST);

		List<Role> list = roleService.selectRole(role);
		mv.addObject("requestRole", role);
		mv.addObject("fun", JSONObject.toJSON(functionService.selectAllFuntion()));
		mv.addObject("roles", list);
		mv.addObject("roleList", JSONObject.toJSON(list));

		return mv;
	}

	/**
	 * 角色新增
	 * 
	 * @param requestRole
	 * @return
	 */
	@RequestMapping(RolePath.ADD)
	@ResponseBody
	public String add(Role requestRole, String checkValue) {
		// 请求bean 打印
		logger.info("请求保存role：[{}]", JSONObject.toJSONString(requestRole, true));

		JSONObject jsonObject = new JSONObject();

		try {
			if (roleService.roleCodeIsExists(requestRole.getRoleCode())) {
				jsonObject.put("result", "fail");
				jsonObject.put("message", "角色代码已存在");
			} else {
				// 保存新增
				roleService.saveAddRole(requestRole, checkValue);
				jsonObject.put("result", "success");
			}

		} catch (Exception e) {
			logger.error("新增角色异常", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 角色修改
	 * 
	 * @param requestUser
	 * @return
	 */
	@RequestMapping(RolePath.EDIT)
	@ResponseBody
	public String edit(Role requestRole, String checkValue) {
		// 请求bean 打印
		logger.info("请求修改role：[{}]", JSONObject.toJSONString(requestRole, true));

		JSONObject jsonObject = new JSONObject();
		try {
			if (roleService.roleCodeAndIdIsExists(requestRole)) {
				jsonObject.put("result", "fail");
				jsonObject.put("message", "角色代码已被使用");
			} else {
				// 修改角色
				this.updateRole(requestRole, checkValue);
				jsonObject.put("result", "success");
			}
		} catch (Exception e) {
			logger.error("修改角色异常", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 角色删除
	 * 
	 * @param requestUser
	 * @return
	 */
	@RequestMapping(RolePath.DELETE)
	@ResponseBody
	public String delete(int roleId) {
		// 请求bean 打印
		logger.info("请求修改role：[{}]", roleId);

		JSONObject jsonObject = new JSONObject();
		try {
			// 保存修改
			this.deleteRole(roleId);
			jsonObject.put("result", "success");
		} catch (Exception e) {
			logger.error("删除角色异常", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 角色已有的菜单
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping(RolePath.FUNCTION)
	@ResponseBody
	public String selectFunction(int roleId) {
		// 请求bean 打印
		logger.info("请求查询菜单：[{}]", roleId);

		JSONObject jsonObject = new JSONObject();
		try {
			List<RoleFunction> list = roleFunctionService.selectList(roleId);
			jsonObject.put("result", "success");
			jsonObject.put("data", list);
		} catch (Exception e) {
			logger.error("查询菜单异常", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**
	 * 事务删除角色
	 * 
	 * @param roleId
	 */
	public void deleteRole(int roleId) {

		roleService.deleteRoleByRoleId(roleId);
	}

	/**
	 * 角色修改
	 */
	@Transactional
	public void updateRole(Role requestRole, String checkValue) {

		User user = WebUtils.getUserInfo();
		requestRole.setLupdUser(user.getUserId());

		// 菜单删除
		roleFunctionService.deleteRoleFunction(requestRole.getRoleId());

		// 增加菜单
		if (!StringUtils.isEmpty(checkValue)) {
			List<String> list = functionService.checkedFunction(checkValue);
			List<RoleFunction> roleFunctions = roleFunctionService.roleFunctionList(requestRole.getRoleId(), list,
					user.getUserId());
			roleFunctionService.insertList(roleFunctions);
		}

		// 保存修改
		roleService.updateRoleById(requestRole);
	}

}
