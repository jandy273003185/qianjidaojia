package com.qifenqian.bms.accounting.balunionpayuniondatasource.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.balunionpayuniondatasource.bean.BalUnionpayUnionDataSource;
import com.qifenqian.bms.accounting.balunionpayuniondatasource.mapper.BalUnionpayUnionDataSourceMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class BalUnionpayUnionDataSourceDao {
	@Autowired
	private BalUnionpayUnionDataSourceMapper mapper;
	
	@Page
	public List<BalUnionpayUnionDataSource> selectUnionpayUnionDataSourceList(BalUnionpayUnionDataSource source){
		return mapper.selectUnionpayUnionDataSourceList(source);
	}
}
