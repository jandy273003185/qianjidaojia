package com.qifenqian.bms.workflow.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.workflow.user.bean.ActIdUser;
import com.qifenqian.bms.workflow.user.mapper.ActIdUserMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class ActIdUserDao {

	@Autowired
	private ActIdUserMapper actIdUserMapper;
	
	@Page
	public List<ActIdUser> selectListActUser(ActIdUser record){
		
		return actIdUserMapper.selectListActUser(record);
		
	}
}
