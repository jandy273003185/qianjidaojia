package com.qifenqian.bms.bal.accountResult.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.bal.accountResult.bean.BalExternalData;
import com.qifenqian.bms.bal.accountResult.dao.BalExternalDataDAO;


@Service
public class BatchExternalDataService {

	@Autowired
	private BalExternalDataDAO balExternalDataDAO;
	
	public List<BalExternalData> selectList(BalExternalData balExternalData) {
		
		return balExternalDataDAO.selectList(balExternalData);
	}

}
