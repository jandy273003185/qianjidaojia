package com.qifenqian.bms.platform.web.admin.role.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.platform.web.admin.role.bean.Role;
import com.qifenqian.bms.platform.web.admin.role.mapper.RoleMapper;
import com.qifenqian.bms.platform.web.page.Page;

/**
 * 
 * @project gyzb-platform-web-admin
 * @fileName RoleDAO.java
 * @author huiquan.ma
 * @date 2015年11月25日
 * @memo
 */
@Repository
public class RoleDAO {
	
	@Autowired
	private RoleMapper roleMapper;
	
	/**
	 * 角色查询
	 * @param role
	 * @return
	 */
	@Page
	public List<Role> selectRole(Role role){
		return roleMapper.selectRoleList(role);
	}
}
