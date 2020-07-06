package com.qifenqian.bms.bal.accountResult.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.bal.accountResult.bean.BalExternalResultEqual;
import com.qifenqian.bms.bal.accountResult.dao.BalExternalResultEqualDAO;
import com.qifenqian.bms.platform.web.page.Page;


@Service
public class BalExternalResultEqualService {
	
	@Autowired
	private BalExternalResultEqualDAO balExternalResultEqualDAO;
	
	@Page
	public List<BalExternalResultEqual>  selectList(BalExternalResultEqual balExternalResultEqual){
		return balExternalResultEqualDAO.selectList(balExternalResultEqual);
		
	}
	
	
}
