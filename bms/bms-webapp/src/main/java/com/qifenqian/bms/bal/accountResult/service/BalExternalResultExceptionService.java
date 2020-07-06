package com.qifenqian.bms.bal.accountResult.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.bal.accountResult.bean.BalExternalResultException;
import com.qifenqian.bms.bal.accountResult.dao.BalExternalResultExceptionDAO;


@Service
public class BalExternalResultExceptionService {
	
	@Autowired
	private BalExternalResultExceptionDAO balExternalResultExceptionDAO;
	
	public  List<BalExternalResultException> selectList(BalExternalResultException balExternalResultException){
		return balExternalResultExceptionDAO.selectList(balExternalResultException);
	}
	
}
