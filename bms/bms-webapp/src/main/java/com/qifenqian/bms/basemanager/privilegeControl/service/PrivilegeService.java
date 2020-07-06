package com.qifenqian.bms.basemanager.privilegeControl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qifenqian.bms.basemanager.privilegeControl.bean.Privilege;
import com.qifenqian.bms.basemanager.privilegeControl.map.PrivilegeMapper;

@Service
public class PrivilegeService {
	@Autowired
	private PrivilegeMapper mapper;
	@Transactional
	public void insertPrivilege(Privilege privilege){
		mapper.insertPrivilege(privilege);
	}
	@Transactional
	public void deletePrivilege(Privilege privilege){
		mapper.deletePrivilege(privilege);
	}
	
	
}
