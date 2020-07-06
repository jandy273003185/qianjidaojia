package com.qifenqian.bms.bal.accountResult.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.bal.accountResult.bean.BalExternalResultException;
import com.qifenqian.bms.bal.accountResult.mapper.BalExternalResultExceptionMapper;
import com.qifenqian.bms.platform.web.page.Page;


@Repository
public class BalExternalResultExceptionDAO {
	
	
	@Autowired
	private BalExternalResultExceptionMapper balExternalResultExceptionMapper;
	
	@Page
	public  List<BalExternalResultException>  selectList(BalExternalResultException balExternalResultException){
		return balExternalResultExceptionMapper.selectList(balExternalResultException);
	}
	
}
