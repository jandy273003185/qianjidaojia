package com.qifenqian.bms.bal.accountResult.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.bal.accountResult.bean.BalExternalResultEqual;
import com.qifenqian.bms.bal.accountResult.mapper.BalExternalResultEqualMapper;
import com.qifenqian.bms.platform.web.page.Page;


@Repository
public class BalExternalResultEqualDAO {
	
	
	@Autowired
	private BalExternalResultEqualMapper balExternalResultEqualMapper;
	
	@Page
	public List<BalExternalResultEqual> selectList(BalExternalResultEqual record){
		return balExternalResultEqualMapper.selectList(record);
		
	}
	
	

}
