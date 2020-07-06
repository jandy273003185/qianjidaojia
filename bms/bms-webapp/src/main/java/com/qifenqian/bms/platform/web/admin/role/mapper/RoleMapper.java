package com.qifenqian.bms.platform.web.admin.role.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.platform.web.admin.role.bean.Role;

/**
 * 角色持久层
 * 
 * @project gyzb-platform-web-admin
 * @fileName RoleMapper.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */

@MapperScan
public interface RoleMapper {

	/**
	 * 根据ID查询角色信息
	 * 
	 * @param roleId
	 * @return
	 */
	public Role selectRoleByRoleId(@Param("roleId") int roleId);

	/**
	 * 更新角色信息
	 * 
	 * @param role
	 */
	public void updateRole(Role role);

	/**
	 * 角色新增
	 * 
	 * @param userId
	 * @return
	 */
	public int insertRole(Role role);

	/**
	 * 根据用户编号查询角色列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<Role> selectRoleListByUserId(@Param("userId") int userId);

	/**
	 * 根据用户编号查询角色ID列表
	 * 
	 * @param userId
	 * @return
	 */
	public List<String> selectRoleIdListByUserId(@Param("userId") int userId);

	/**
	 * 根据条件查询
	 * 
	 * @param role
	 * @return
	 */
	public List<Role> selectRoleList(Role role);

	/**
	 * 
	 * @param roleCode
	 * @return
	 */
	public String roleCodeIsExists(String roleCode);

	/**
	 * 
	 * @param requestRole
	 * @return
	 */
	public String roleCodeAndIdIsExists(Role requestRole);

}
