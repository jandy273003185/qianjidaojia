package com.qifenqian.bms.platform.web.admin.rolefunction.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.platform.web.admin.rolefunction.bean.RoleFunction;

/**
 * 角色菜单持久层
 * @project gyzb-platform-web-admin
 * @fileName RoleFunctionMapper.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */

@MapperScan
public interface RoleFunctionMapper {
	
	/**
	 * 批量插入
	 * @param roleFunction
	 */
	public void insertList(List<RoleFunction> roleFunction);
	
	/**
	 * 根据ID查询所有角色菜单
	 * @param roleId
	 * @return
	 */
	public List<RoleFunction> selectList(@Param("roleId")  int roleId);
	
	
	/**
	 * 根据ID删除角色菜单
	 * @param roleId
	 */
	public void deleteByRoleId(@Param("roleId") int roleId);
	
	/**
	 * 根据功能菜单ID删除角色菜单
	 * @param functionId
	 */
	public void deleteRoleFunctionbyFunctionId(int functionId);
	
}
