package com.qifenqian.bms.basemanager.sysuser.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.sysuser.bean.SysUser;

/**
 * 费率持久层
 * 
 * @project sevenpay-bms-web
 * @fileName RuleMapper.java
 * @author PC
 * @date 2015年6月17日
 * @memo
 */

@MapperScan
public interface SysUserMapper {
	
	public List<SysUser> selectSysUser();

	SysUser selectUserById(String userId);

	SysUser selectUserByUserName(String custManager);

	SysUser selectDeptByAgentName(String deptName);
	
	SysUser selectDeptByUserCode(String userCode);
}
