package com.qifenqian.bms.platform.web.admin.user.service;

import com.qifenqian.bms.platform.common.utils.CipherUtils;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.user.dao.UserDAO;
import com.qifenqian.bms.platform.web.admin.user.mapper.UserMapper;
import com.qifenqian.bms.platform.web.admin.user.type.UserStatus;
import com.qifenqian.bms.platform.web.admin.userRole.bean.UserRole;
import com.qifenqian.bms.platform.web.admin.userRole.service.UserRoleService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务层
 * 
 * @project gyzb-platform-web-admin
 * @fileName UserService.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
@Service
public class UserService {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserDAO userDao;
	@Autowired
	private UserRoleService userRoleService;

	public List<User> getUserList(User user) {
		return userMapper.selectUserList(user);
	}

	/**
	 * 新增单个用户
	 * 
	 * @param user
	 * @return
	 */
	public int insertUserSingle(User user) {
		if (null == user) {
			throw new IllegalArgumentException("用户对象为空");
		}
		if (StringUtils.isEmpty(user.getUserName())) {
			throw new IllegalArgumentException("用户名为空");
		}
		return userMapper.insertUserSingle(user);
	}

	/**
	 * 根据用户编号查询单个用户
	 * 
	 * @param userId
	 * @return
	 */
	public User selectUserSingleById(int userId) {
		return userMapper.selectUserSingleById(userId);
	}

	/**
	 * 根据用户编号查询单个用户
	 * 
	 * @param userCode
	 * @return
	 */
	public User selectUserSingleByCode(String userCode) {
		return userMapper.selectUserSingleByCode(userCode);
	}

	/**
	 * 更加用户编号更改状态
	 * 
	 * @param userId
	 * @param status
	 * @return
	 */
	public int updateStatusById(int userId, UserStatus status) {
		if (null == status) {
			throw new IllegalArgumentException("要更改的用户状态为空");
		}
		User updateUser = new User();
		updateUser.setUserId(userId);
		updateUser.setStatus(status);
		updateUser.setLupdUser(userId);

		return userMapper.updateUserById(updateUser);
	}

	/**
	 * 查询用户列表-分页
	 * 
	 * @return
	 */
	public List<User> selectUserListByPage(User user) {
		return userDao.selectUserListByPage(user);
	}

	/**
	 * 页面新增单个用户
	 * 
	 * @param user
	 * @return
	 */
	public void saveAddUser(User user, String role) {
		// 检查
		if (null == user) {
			throw new IllegalArgumentException("新增用户对象为空");
		}
		if (StringUtils.isEmpty(user.getUserName())) {
			throw new IllegalArgumentException("用户名为空");
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			throw new IllegalArgumentException("密码为空");
		}
		if (StringUtils.isEmpty(user.getRealName())) {
			throw new IllegalArgumentException("真实名称为空");
		}
		if (user.getDeptId() < 1) {
			throw new IllegalArgumentException("所属部门为空");
		}
		if (null == user.getSex()) {
			throw new IllegalArgumentException("性别为空");
		}
		if (null == user.getStatus()) {
			throw new IllegalArgumentException("状态为空");
		}
		// 个别栏位填充
		user.setPassword(CipherUtils.encryptPassword(user.getUserCode(), user.getPassword()));
//		user.setInstUser(WebUtils.getUserInfo().getUserId());
		// 保存
		userMapper.insertUserSingle(user);

		String[] roleList = role.split(",");
		for (String roleId : roleList) {
			UserRole userRole = new UserRole();
			userRole.setUserId(user.getUserId());
			userRole.setRoleId(Integer.parseInt(roleId));
			userRole.setInstUser(user.getInstUser());
			userRoleService.insertUserRole(userRole);
		}
	}

	/**
	 * 根据用户编号删除单个用户
	 * 
	 * @param userId
	 * @return
	 */
	public int deleteUserById(int userId) {
		if (userId < 1) {
			throw new IllegalArgumentException("用户编号为空");
		}
		User updateUser = new User();
		updateUser.setUserId(userId);
		updateUser.setStatus(UserStatus.LEAVE);
		updateUser.setLupdUser(WebUtils.getUserInfo().getUserId());

		return userMapper.updateUserById(updateUser);
	}

	/**
	 * 页面修改单个用户
	 * 
	 * @param user
	 * @return
	 */
	public void updateUserById(User user, String role) {
		// 检查
		if (null == user) {
			throw new IllegalArgumentException("用户对象为空");
		}
		if (user.getUserId() < 1) {
			throw new IllegalArgumentException("用户Id为空");
		}
		if (StringUtils.isEmpty(user.getUserCode())) {
			throw new IllegalArgumentException("员工号为空");
		}
		if (StringUtils.isEmpty(user.getRealName())) {
			throw new IllegalArgumentException("真实名称为空");
		}
		if (user.getDeptId() < 1) {
			throw new IllegalArgumentException("所属部门为空");
		}
		if (null == user.getSex()) {
			throw new IllegalArgumentException("性别为空");
		}
		if (null == user.getStatus()) {
			throw new IllegalArgumentException("状态为空");
		}
		// 查询用户
		User selectUser = userMapper.selectUserSingleById(user.getUserId());
		if (!StringUtils.isEmpty(user.getPassword()) && !user.getPassword().equals(selectUser.getPassword())) {
			user.setPassword(CipherUtils.encryptPassword(user.getUserCode(), user.getPassword()));
		} else {
			user.setPassword(null);
		}

//		user.setLupdUser(WebUtils.getUserInfo().getUserId());
		// 保存
		userMapper.updateUserById(user);

		// 删除后重新添加角色
		userRoleService.deleteUserRole(user.getUserId());

		String[] roleList = role.split(",");

		for (String roleId : roleList) {
			UserRole userRole = new UserRole();
			userRole.setUserId(user.getUserId());
			userRole.setRoleId(Integer.parseInt(roleId));
			userRole.setInstUser(user.getInstUser());
//			userRole.setInstUser(WebUtils.getUserInfo().getUserId());
			userRoleService.insertUserRole(userRole);
		}

	}

	/**
	 * 修改用户密码
	 * 
	 * @param user
	 * @return
	 */
	public int changePassword(User user) {
		// 检查
		if (null == user) {
			throw new IllegalArgumentException("用户对象为空");
		}
		if (user.getUserId() < 1) {
			throw new IllegalArgumentException("用户编号为空");
		}
		if (StringUtils.isEmpty(user.getUserCode())) {
			throw new IllegalArgumentException("员工号为空");
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			throw new IllegalArgumentException("密码为空");
		}
		user.setPassword(CipherUtils.encryptPassword(user.getUserCode(), user.getPassword()));
		user.setLupdUser(WebUtils.getUserInfo().getUserId());
		return userMapper.updateUserById(user);
	}

	/**
	 * 查询用户密码
	 * 
	 * @param userId
	 * @return
	 */
	public String getUserPwd(int userId) {
		User user = userMapper.selectUserSingleById(userId);
		if (null == user) {
			return null;
		}
		return user.getPassword();
	}

	/**
	 * 登出
	 * 
	 * @return
	 */
	public int loginOutAllUsers() {
		return userMapper.loginOutAllUsers();
	}

	/***
	 * cas 新增用户
	 * 
	 * @param user
	 * @return
	 */
	public int insertUserByCas(User user) {

		if (null == user) {
			throw new IllegalArgumentException("新增用户对象为空");
		}
		if (user.getUserId()<1) {
			throw new IllegalArgumentException("新增用户ID为空");
		}
		if (StringUtils.isEmpty(user.getUserName())) {
			throw new IllegalArgumentException("用户名为空");
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			throw new IllegalArgumentException("密码为空");
		}
		if (StringUtils.isEmpty(user.getRealName())) {
			throw new IllegalArgumentException("真实名称为空");
		}
		if (user.getDeptId() < 1) {
			throw new IllegalArgumentException("部门编号为空");
		}
		if (null == user.getSex()) {
			throw new IllegalArgumentException("性别为空");
		}
		if (null == user.getStatus()) {
			throw new IllegalArgumentException("状态为空");
		}
		return userMapper.insertUserByCas(user);
	}
	
	/***
	 * cas修改用户
	 * 
	 * @param user
	 * @return
	 */
	public int updateUserByCas(User user) {
		if (null == user) {
			throw new IllegalArgumentException("用户对象为空");
		}
		if (user.getUserId()<1) {
			throw new IllegalArgumentException("员工ID为空");
		}
		if (StringUtils.isEmpty(user.getUserCode())) {
			throw new IllegalArgumentException("员工号为空");
		}
		if (StringUtils.isEmpty(user.getRealName())) {
			throw new IllegalArgumentException("真实名称为空");
		}
		if (null == user.getSex()) {
			throw new IllegalArgumentException("性别为空");
		}
		if (null == user.getStatus()) {
			throw new IllegalArgumentException("状态为空");
		}
		return userMapper.updateUserByCas(user);
	}



	/**
	 * 
	 * @param userName
	 * @return
	 */
	public boolean userNameIsExists(String userName) {
		boolean flag = false;
		String isExists = userMapper.selectUserNameIsExists(userName);
		if (!isExists.endsWith("0")) {
			flag = true;
		}
		return flag;
	}

	/**
	 * 
	 * @param requestUser
	 * @return
	 */
	public boolean userNameAndIdIsExists(User requestUser) {
		boolean flag = false;
		String isExists = userMapper.userNameAndIdIsExists(requestUser);
		if (!isExists.endsWith("0")) {
			flag = true;
		}
		return flag;
	}
}
