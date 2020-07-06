package com.qifenqian.bms.platform.web.admin.role.service;

import com.qifenqian.bms.platform.web.admin.function.mapper.FunctionMapper;
import com.qifenqian.bms.platform.web.admin.role.bean.Role;
import com.qifenqian.bms.platform.web.admin.role.dao.RoleDAO;
import com.qifenqian.bms.platform.web.admin.role.mapper.RoleMapper;
import com.qifenqian.bms.platform.web.admin.role.type.RoleIsValid;
import com.qifenqian.bms.platform.web.admin.rolefunction.bean.RoleFunction;
import com.qifenqian.bms.platform.web.admin.rolefunction.service.RoleFunctionService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 角色服务层
 * 
 * @project gyzb-platform-web-admin
 * @fileName RoleService.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
@Service
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private FunctionMapper functionMapper;

	@Autowired
	private RoleDAO roleDAO;

	@Autowired
	private RoleFunctionService roleFunctionService;

	/**
	 * 查询所有角色信息
	 * 
	 * @return
	 */
	public List<Role> selectRoles() {
		return roleMapper.selectRoleList(null);
	}

	/**
	 * 根据条件查询
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> selectRole(Role role) {
		return roleDAO.selectRole(role);
	}

	/**
	 * 根据用户编号查询角色列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<Role> selectRoleListByUserId(int userId, boolean isSkipInvalid) {
		if (userId < 1) {
			throw new IllegalArgumentException("用户编号为空");
		}
		List<Role> resultList = new ArrayList<Role>();
		List<Role> selectList = roleMapper.selectRoleListByUserId(userId);
		if (null != selectList && !selectList.isEmpty()) {
			for (Role role : selectList) {
				if (isSkipInvalid && RoleIsValid.N == role.getIsValid()) {
					continue;
				}
				role.setFunctionList(functionMapper.selectFunctionListByRoleId(role.getRoleId()));
				resultList.add(role);
			}
		}
		return resultList.size() == 0 ? null : resultList;
	}

	/**
	 * 根据用户ID查询角色ID列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> selectRoleIdListByUserId(int userId) {
		return roleMapper.selectRoleIdListByUserId(userId);
	}

	public List<Role> selectRoles(Role requestRole) {
		return roleMapper.selectRoleList(requestRole);
	}

	/**
	 * 角色新增
	 * 
	 * @param role
	 * @return
	 */
	public void saveAddRole(Role role, String checkValue) {
		if (null == role) {
			throw new IllegalArgumentException("新增角色对象为空");
		}
		if (StringUtils.isEmpty(role.getRoleCode())) {
			throw new IllegalArgumentException("角色代码为空");
		}
		if (StringUtils.isEmpty(role.getRoleName())) {
			throw new IllegalArgumentException("角色名称为空");
		}
		// 个别栏位填充
		role.setInstUser(WebUtils.getUserInfo().getUserId());
		role.setIsValid(RoleIsValid.Y);

//		User user = WebUtils.getUserInfo();
//		role.setInstUser(user.getUserId());

		// 保存
		roleMapper.insertRole(role);

		// 增加菜单
		if (!StringUtils.isEmpty(checkValue)) {
			String[] check = checkValue.split(",");
			List<String> list = Arrays.asList(check);
			List<RoleFunction> roleFunctions = roleFunctionService.roleFunctionList(role.getRoleId(), list,
					role.getInstUser());
			roleFunctionService.insertList(roleFunctions);
		}
	}

	/**
	 * 角色修改
	 * 
	 * @param role
	 * @return
	 */
	public void updateRoleById(Role role) {
		if (null == role) {
			throw new IllegalArgumentException("角色对象为空");
		}
		if (role.getRoleId() < 1) {
			throw new IllegalArgumentException("角色编号为空");
		}
		if (StringUtils.isEmpty(role.getRoleName())) {
			throw new IllegalArgumentException("角色名称为空");
		}
		// 个别栏位填充
//		role.setLupdUser(WebUtils.getUserInfo().getUserId());
		// 保存
		roleMapper.updateRole(role);
	}

	/**
	 * 角色删除
	 * 
	 * @param role
	 * @return
	 */
	public void deleteRoleById(int roleId) {

		if (roleId < 1) {
			throw new IllegalArgumentException("角色编号为空");
		}
		Role updateRole = new Role();
		updateRole.setRoleId(roleId);
		updateRole.setLupdUser(WebUtils.getUserInfo().getUserId());
		updateRole.setIsValid(RoleIsValid.N);

		roleMapper.updateRole(updateRole);
	}

	/**
	 * 增加角色
	 * 
	 * @param role
	 */
	public void addRole(Role role) {
		roleMapper.insertRole(role);
	}

	/**
	 * 更改角色
	 * 
	 * @param role
	 */
	public void editRole(Role role) {
		roleMapper.updateRole(role);
	}

	/**
	 * 事务删除角色
	 * 
	 * @param roleId
	 */

	public void deleteRoleByRoleId(int roleId) {
		if (roleId < 1) {
			throw new IllegalArgumentException("角色ID为空");
		}

		roleFunctionService.deleteRoleFunction(roleId);
		this.deleteRoleById(roleId);
	}

	/**
	 * 
	 * @param roleCode
	 * @return
	 */
	public boolean roleCodeIsExists(String roleCode) {
		boolean flag = false;
		String roleCodeCount = roleMapper.roleCodeIsExists(roleCode);
		if (!roleCodeCount.equals("0")) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * @param requestRole
	 * @return
	 */
	public boolean roleCodeAndIdIsExists(Role requestRole) {
		boolean flag = false;
		String roleCodeCount = roleMapper.roleCodeAndIdIsExists(requestRole);
		if (!roleCodeCount.equals("0")) {
			flag = true;
		}
		return flag;
	}

}
