package com.qifenqian.bms.accounting.jgkjdatasource.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.jgkjdatasource.bean.BalJgkjDataSource;
import com.qifenqian.bms.accounting.jgkjdatasource.mapper.BalJgkjDataSourceMapper;
import com.qifenqian.bms.platform.web.page.Page;
@Repository
public class BalJgkjDataSourceDao {
	
	@Autowired
	private BalJgkjDataSourceMapper mapper;
	
	@Page
	public List<BalJgkjDataSource> selectZytDataSourceList(BalJgkjDataSource zytDataSource){
		return mapper.selectZytDataSourceList(zytDataSource);
	}
}
