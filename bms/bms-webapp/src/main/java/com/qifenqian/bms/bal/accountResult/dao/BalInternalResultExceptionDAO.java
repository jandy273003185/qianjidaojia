package com.qifenqian.bms.bal.accountResult.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.bal.accountResult.bean.BalInternalResultException;
import com.qifenqian.bms.bal.accountResult.mapper.BalInternalResultExceptionMapper;
import com.qifenqian.bms.platform.web.page.Page;


@Repository
public class BalInternalResultExceptionDAO {
	
	@Autowired
	private BalInternalResultExceptionMapper balInternalResultExceptionMapper;
	
	@Page
	public  List<BalInternalResultException>  selectList(BalInternalResultException balInternalResultException){
		return balInternalResultExceptionMapper.selectList(balInternalResultException);
	}
}
