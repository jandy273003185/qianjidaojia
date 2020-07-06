package com.qifenqian.bms.basemanager.BlackAndWhite.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.BlackAndWhite.bean.PrivilegeInfo;
import com.qifenqian.bms.basemanager.BlackAndWhite.mapper.PrivilegeInfoMapper;

@Repository
public class PrivilegeInfoDao {
	@Autowired
	private PrivilegeInfoMapper mapper;
	
	public List<PrivilegeInfo> list(){
		return mapper.list();
	}
}
