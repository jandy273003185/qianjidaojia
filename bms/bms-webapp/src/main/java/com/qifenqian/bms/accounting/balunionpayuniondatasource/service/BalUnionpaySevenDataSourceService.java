package com.qifenqian.bms.accounting.balunionpayuniondatasource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.balunionpayuniondatasource.bean.BalUnionpaySevenDataSource;
import com.qifenqian.bms.accounting.balunionpayuniondatasource.dao.BalUnionpaySevenDataSourceDao;

@Service
public class BalUnionpaySevenDataSourceService {
	@Autowired
	private BalUnionpaySevenDataSourceDao dao;

	public List<BalUnionpaySevenDataSource> selectUnionpaySevenDataSourceList(BalUnionpaySevenDataSource source) {
		return dao.selectUnionpaySevenDataSourceList(source);
	}

}
