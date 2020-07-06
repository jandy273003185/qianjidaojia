package com.qifenqian.bms.workflow.group.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.workflow.group.bean.ActIdGroup;
import com.qifenqian.bms.workflow.group.dao.ActIdGroupDao;

@Service
public class ActIdGroupService {
	
	@Autowired
	private ActIdGroupDao actIdGroupDao;
	
	public List<ActIdGroup> selectListActIdGroup(ActIdGroup record){
		return actIdGroupDao.selectListActIdGroup(record);
	}
}
