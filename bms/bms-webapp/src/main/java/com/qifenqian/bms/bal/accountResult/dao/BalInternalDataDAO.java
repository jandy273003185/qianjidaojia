package com.qifenqian.bms.bal.accountResult.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.bal.accountResult.bean.BalInternalData;
import com.qifenqian.bms.bal.accountResult.mapper.BalInternalDataMapper;
import com.qifenqian.bms.platform.web.page.Page;



@Repository
public class BalInternalDataDAO {
	
	@Autowired
	private BalInternalDataMapper balInternalDataMapper;
	
	@Page
	public  List<BalInternalData>  selectList(BalInternalData balInternalData){
		return balInternalDataMapper.selectList(balInternalData);
	}
}
