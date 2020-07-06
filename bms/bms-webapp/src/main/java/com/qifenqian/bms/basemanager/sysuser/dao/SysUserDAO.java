package com.qifenqian.bms.basemanager.sysuser.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.sysuser.bean.SysUser;
import com.qifenqian.bms.basemanager.sysuser.mapper.SysUserMapper;

@Repository
public class SysUserDAO {
	
	@Autowired
	public SysUserMapper sysUserMapper;
	
	/**
	 * 查询所有员工信息
	 * @return
	 */
	public List<SysUser> selectSysUser(){
		return sysUserMapper.selectSysUser();
	}
}
