package com.qifenqian.bms.bal.accountResult.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.bal.accountResult.bean.BalExternalData;
import com.qifenqian.bms.bal.accountResult.mapper.BalExternalDataMapper;
import com.qifenqian.bms.platform.web.page.Page;


@Repository
public class BalExternalDataDAO {

	
	@Autowired
	private BalExternalDataMapper balExternalDataMapper;
	
	@Page
	public List<BalExternalData> selectList(BalExternalData balExternalData) {
		
		return balExternalDataMapper.selectList(balExternalData);
	}

}
