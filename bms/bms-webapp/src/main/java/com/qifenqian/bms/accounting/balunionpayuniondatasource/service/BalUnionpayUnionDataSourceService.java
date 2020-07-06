package com.qifenqian.bms.accounting.balunionpayuniondatasource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.balunionpayuniondatasource.bean.BalUnionpayUnionDataSource;
import com.qifenqian.bms.accounting.balunionpayuniondatasource.dao.BalUnionpayUnionDataSourceDao;

@Service
public class BalUnionpayUnionDataSourceService {
	@Autowired
	private BalUnionpayUnionDataSourceDao dao;

	public List<BalUnionpayUnionDataSource> selectUnionpayUnionDataSourceList(BalUnionpayUnionDataSource source) {
		return dao.selectUnionpayUnionDataSourceList(source);
	}
}
