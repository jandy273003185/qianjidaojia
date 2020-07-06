package com.qifenqian.bms.platform.web.admin.utils;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.qifenqian.bms.platform.web.admin.function.bean.Function;
import com.qifenqian.bms.platform.web.admin.role.bean.Role;
import com.qifenqian.bms.platform.web.admin.user.bean.User;

/**
 * 权限工具类
 * 主要用于判断当前用户权限
 * 
 * @project gyzb-platform-web-admin
 * @fileName PrivilegeUtils.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
public class PrivilegeUtils {

	/**
	 * 判断一个用户是否拥有该角色
	 */
	public static boolean hasRole(User user, Role role) {
		if (user == null) {
			throw new NullPointerException("用户不能为空");
		}
		if (role == null) {
			throw new NullPointerException("角色不能为空");
		}
		return hasRole(user, role.getRoleId());
	}

	/**
	 * 判断一个用户是否拥有该角色ID
	 */
	public static boolean hasRole(User user, int roleId) {
		if (user == null) {
			throw new NullPointerException("用户不能为空");
		}
		if (roleId < 1) {
			throw new NullPointerException("角色ID不能为空");
		}
		List<Role> roles = user.getRoleList();
		if (roles == null) {
			throw new IllegalStateException("用户的角色列表不能为空");
		}
		for (Role aRole : roles) {
			if (aRole.getRoleId() == roleId) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断一个用户是否拥有该功能
	 */
	public static boolean hasFunc(User user, Function func) {
		if (user == null) {
			throw new NullPointerException("用户不能为空");
		}
		if (func == null) {
			throw new NullPointerException("功能不能为空");
		}
		return hasFunc(user, func.getFunctionUrl());
	}

	/**
	 * 判断一个用户是否拥有该功能
	 */
	public static boolean hasFunc(User user, String funcUrl) {
		if (user == null) {
			throw new NullPointerException("用户不能为空");
		}
		if (StringUtils.isEmpty(funcUrl)) {
			throw new NullPointerException("功能Url不能为空");
		}
		List<Role> roles = user.getRoleList();
		if (roles == null) {
			throw new IllegalStateException("用户的角色列表不能为空");
		}
		for (Role aRole : roles) {
			if (hasFunc(aRole, funcUrl)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断一个角色是否拥有该功能
	 */
	public static boolean hasFunc(Role role, Function func) {
		if (role == null) {
			throw new NullPointerException("角色不能为空");
		}
		if (func == null) {
			throw new NullPointerException("功能URL不能为空");
		}
		return hasFunc(role, func.getFunctionUrl());
	}

	/**
	 * 判断一个角色是否拥有该功能
	 */
	public static boolean hasFunc(Role role, String funcUri) {
		if (role == null) {
			throw new NullPointerException("角色不能为空");
		}
		if (StringUtils.isEmpty(funcUri)) {
			throw new NullPointerException("功能URL不能为空");
		}
		List<Function> functions = role.getFunctionList();
		if (functions == null) {
			throw new IllegalStateException("角色的功能列表不能为空");
		}
		for (Function aFunction : functions) {
			if (aFunction.getFunctionUrl()!= null) {
				// 请求URI完全匹配
				if (aFunction.getFunctionUrl().replaceFirst("\\?.*$", "").equals(funcUri)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 判断当前用户是否拥有该角色
	 */
	public static boolean hasRole(String... roleIds) {
		User user = WebUtils.getUserInfo();
		for (String roleId : roleIds) {
			if (hasRole(user, Integer.parseInt(roleId))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断当前用户是否拥有该功能
	 */
	public static boolean hasFunc(String... funcUris) {
		User user = WebUtils.getUserInfo();
		for (String funcUri : funcUris) {
			if (hasFunc(user, funcUri)) {
				return true;
			}
		}
		return false;
	}

}
