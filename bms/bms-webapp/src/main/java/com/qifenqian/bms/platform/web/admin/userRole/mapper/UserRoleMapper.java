package com.qifenqian.bms.platform.web.admin.userRole.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.platform.web.admin.role.bean.Role;
import com.qifenqian.bms.platform.web.admin.userRole.bean.UserRole;

/**
 * 
 * @project gyzb-platform-web-admin
 * @fileName UserRoleMapper.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
@MapperScan
public interface UserRoleMapper {
	
	/**
	 * 用户添加角色
	 * @param role
	 */
	public void insertUserRole(UserRole role);
	
	/**
	 * 查找用户角色
	 * @param userId
	 * @return
	 */
	public List<Role> selectUserRoleByUserId(@Param("userId") int userId);
	
	/**
	 * 修改用户角色
	 */
	public void updateUserRole(UserRole role);
	
	/**
	 * 删除
	 * @param userId
	 */
	public void deleteUserRole(@Param("userId") int userId);
}
