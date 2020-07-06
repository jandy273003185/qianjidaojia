package com.qifenqian.bms.platform.web.admin.login.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.platform.common.utils.CipherUtils;
import com.qifenqian.bms.platform.web.admin.function.bean.Function;
import com.qifenqian.bms.platform.web.admin.function.mapper.FunctionMapper;
import com.qifenqian.bms.platform.web.admin.function.type.IsMenu;
import com.qifenqian.bms.platform.web.admin.function.type.IsValid;
import com.qifenqian.bms.platform.web.admin.role.service.RoleService;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.user.service.UserService;
import com.qifenqian.bms.platform.web.admin.user.type.UserStatus;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

/**
 * 登陆服务层
 * 
 * @project gyzb-platform-web-admin
 * @fileName LoginService.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
@Service
public class LoginService {
	
	@Autowired
	private UserService userService;
	@Autowired
	private FunctionMapper functionMapper;
	@Autowired
	private RoleService roleService;

	/**
	 * 登陆检查
	 * @param userCode
	 * @param password
	 */
	public User checkUser(String userCode, String password) {
		// 校验
		if(StringUtils.isEmpty(userCode)) {
			throw new IllegalArgumentException("员工号为空");
		}
		if(StringUtils.isEmpty(password)) {
			throw new IllegalArgumentException("登陆密码为空");
		}
		// 忽略大小写
		String queryUserCode = userCode.toUpperCase();
		// 查询用户
		User loginUser = userService.selectUserSingleByCode(queryUserCode);
		if(null == loginUser) {
			throw new IllegalArgumentException("员工号[" + userCode + "]不存在");
		}
		// 先校验密码，防止 别人根据提示套信息
		if (!loginUser.getPassword().equals(CipherUtils.encryptPassword(queryUserCode, password))) {
			throw new IllegalArgumentException("密码错误");
		}
		if(null == loginUser.getStatus()) {
			throw new IllegalArgumentException("用户[" + userCode + "]状态异常");
		}
		if(UserStatus.FREEZE == loginUser.getStatus()) {
			throw new IllegalArgumentException("用户[" + userCode + "]已冻结");
		}
		if(UserStatus.LEAVE == loginUser.getStatus()) {
			throw new IllegalArgumentException("用户[" + userCode + "]已离职");
		}
/*		if(UserStatus.LOGIN == loginUser.getStatus()) {
			throw new IllegalArgumentException("用户[" + userCode + "]已登陆");
		}*/
		return loginUser;
	}
	
	/**
	 * 根据用户编号查询菜单功能树(有效)
	 * @param userId
	 * @return
	 */
	public List<Function> selectMenuTreeByUserId(int userId) {
		if(userId < 1) {
			throw new IllegalArgumentException("用户编号为空");
		}
		// 先填充一级菜单
		List<Function> returnList = new ArrayList<Function>();
		
		List<Function> userFunctionList = functionMapper.selectFunctionListByLogin(userId);
		if(null != userFunctionList && !userFunctionList.isEmpty()) {
			for(Function function : userFunctionList) {
				// 有效并且为菜单的才加入
				if(function.getFunctionLevel() == 1 && function.getIsMenu() == IsMenu.Y && function.getIsValid() == IsValid.Y) {
					returnList.add(function);
				}
			}
		}
		// 递归填充子菜单
		for(Function function : returnList) {
			function.setSubFunctionList(this.selectFunctionSubListById(userId, function.getFunctionId(), IsMenu.Y, IsValid.Y));
		}
		// 返回
		return returnList;
	}
	
	/**
	 * 根据功能编号递归查询子功能菜单树
	 * 
	 * @param userId 是否根据用户过滤
	 * @param functionId
	 * @param isMenu
	 * @param isValid
	 * @return
	 */
	private List<Function> selectFunctionSubListById(int userId, int functionId, IsMenu isMenu, IsValid isValid) {
		if(functionId < 1) {
			throw new IllegalArgumentException("功能编号为空");
		}
		List<Function> returnList = new ArrayList<Function>();
		List<Function> subFunctionList = functionMapper.selectFunctionSubListByLogin(userId, functionId);
		if(null != subFunctionList && !subFunctionList.isEmpty()) {
			for(Function function : subFunctionList) {
				if(null != isMenu && function.getIsMenu() != isMenu) {
					continue;
				}
				if(null != isValid && function.getIsValid() != isValid) {
					continue;
				}
				function.setSubFunctionList(this.selectFunctionSubListById(userId, function.getFunctionId(), isMenu, isValid));
				returnList.add(function);
			}
		}
		return returnList;
	}
	
	/**
	 * 根据用户编号查询功能列表
	 * @param userId
	 * @return
	 */
	public List<Function> selectFunctionListByUserId(int userId) {
		if(userId < 1) {
			throw new IllegalArgumentException("用户编号为空");
		}
		List<Function> userFunctionList = functionMapper.selectFunctionListByLogin(userId);
		if(null != userFunctionList && !userFunctionList.isEmpty()) {
			for(Function function : userFunctionList) {
				function.setSubFunctionList(this.selectFunctionSubListById(userId, function.getFunctionId(), null, IsValid.Y));
				function.setParentFunctionList(this.selectFunctionParentListById(function.getFunctionId()));
			}
		}
		return userFunctionList;
	}
	
	/**
	 * 根据功能编号递归查询子功能菜单树
	 * @param functionId
	 * @return
	 */
	private List<Function> selectFunctionParentListById(int functionId) {
		if(functionId < 1) {
			throw new IllegalArgumentException("功能编号为空");
		}
		List<Function> returnList = new ArrayList<Function>();
		Function parentFunction = functionMapper.selectFunctionParentById(functionId);
		while(null != parentFunction) {
			returnList.add(parentFunction);
			parentFunction = functionMapper.selectFunctionParentById(parentFunction.getFunctionId());
		}
		return returnList;
	}
	
	public void login(User user) {
		// 判断
		if(null == user) {
			throw new IllegalArgumentException("用户对象为空");
		}
		// 更改用户状态
		userService.updateStatusById(user.getUserId(), UserStatus.LOGIN);
		// 角色信息
		user.setRoleList(roleService.selectRoleListByUserId(user.getUserId(), true));
		// 菜单信息
		user.setMenuList(this.selectMenuTreeByUserId(user.getUserId()));
		// 功能信息
		user.setFunctionList(this.selectFunctionListByUserId(user.getUserId()));

		// 注册用户信息
		WebUtils.registerUserInfo(user);
	}
	
	/**
	 * 退出
	 * @param userId
	 */
	public void logout(User user) {
		// 判断
		if(null == user) {
			return;
		}
		// 更改状态
		userService.updateStatusById(user.getUserId(), UserStatus.VALID);
		// 用户登陆表更新
	}
}


