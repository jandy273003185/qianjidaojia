package com.qifenqian.bms.bal.accountResult.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.bal.accountResult.bean.BalInternalResultEqual;
import com.qifenqian.bms.bal.accountResult.mapper.BalInternalResultEqualMapper;
import com.qifenqian.bms.platform.web.page.Page;



@Repository
public class BalInternalResultEqualDAO {
	
	@Autowired
	private BalInternalResultEqualMapper balInternalResultEqualMapper;
	
	@Page
	public  List<BalInternalResultEqual> selectList(BalInternalResultEqual record){
		return balInternalResultEqualMapper.selectList(record);
	}
}
