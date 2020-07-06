package com.qifenqian.bms.basemanager.market.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.market.bean.MarketRole;
import com.qifenqian.bms.basemanager.market.mapper.MarketAuthoMapper;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.user.mapper.UserMapper;
import com.qifenqian.bms.platform.web.admin.userRole.bean.UserRole;
import com.qifenqian.bms.platform.web.admin.userRole.mapper.UserRoleMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class MarketAuthoDao {

	@Autowired
	private MarketAuthoMapper marketAuthoMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	/**
	 * 根据查询条件获取用户详情列表
	 */
	@Page
	public List<MarketRole> listInfo(MarketRole queryBean){
		return marketAuthoMapper.listInfo(queryBean);
	}
	/**
	 * 保存用户信息
	 */
	public void saveInfo(MarketRole marketRole) {
		marketAuthoMapper.saveInfo(marketRole);
	}
	/**
	 * 更新用户信息
	 */
	public void updateInfo(MarketRole marketRole) {
		marketAuthoMapper.updateInfo(marketRole);
	}
	/**
	 * 更新用户名字
	 */
	public void updateUserName(User user) {
		userMapper.updateUserById(user);
	}
	/**
	 * 删除用户信息
	 */
	public void deleteInfo(MarketRole marketRole) {
		marketAuthoMapper.deleteInfo(marketRole);
		
	}
	/**
	 * 根据用户名查询用户信息
	 */
	public User selectUserByCode(String userCode) {
		return userMapper.selectUserSingleByCode(userCode);
	}
	
	/**
	 * 根据用户id查询用户角色Id集合
	 */
	public List<Integer> listRoleIds(Integer userId){
		return marketAuthoMapper.listRoleId(userId);
	}
	/**
	 * 添加用户角色
	 * @param userRole
	 */
	public void insertUserRole(UserRole userRole){
		if(null == userRole){
			throw new IllegalArgumentException("用户角色对象为空");
		}
		
		userRoleMapper.insertUserRole(userRole);
	}
	/**
	 * 根据id删除用户角色
	 * @param userId
	 */
	public void deleteUserRole(int userId) {
		userRoleMapper.deleteUserRole(userId);
	}
	/**
	 * 根据角色名获取角色id
	 */
	public Integer getRoleIdByName(String roleName) {
		return marketAuthoMapper.getRoleIdByName(roleName);
	}
	
	/**
	 * 根据角色id和用户id删除相应的角色信息
	 */
	public void deleteRoleId(Integer roleId,Integer userId) {
		marketAuthoMapper.deleteRoleId(roleId, userId);
	}
}
