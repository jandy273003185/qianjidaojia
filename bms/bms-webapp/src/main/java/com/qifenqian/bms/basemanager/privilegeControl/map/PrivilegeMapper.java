package com.qifenqian.bms.basemanager.privilegeControl.map;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.privilegeControl.bean.Privilege;

@MapperScan
public interface PrivilegeMapper {
	public void insertPrivilege(Privilege privilege);

	public void deletePrivilege(Privilege privilege);
}
