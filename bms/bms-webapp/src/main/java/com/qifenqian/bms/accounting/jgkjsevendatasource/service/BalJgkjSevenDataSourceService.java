package com.qifenqian.bms.accounting.jgkjsevendatasource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.jgkjsevendatasource.bean.BalJgkjSevenDataSource;
import com.qifenqian.bms.accounting.jgkjsevendatasource.dao.BalJgkjSevenDataSourceDao;

@Service
public class BalJgkjSevenDataSourceService {
	@Autowired
	private BalJgkjSevenDataSourceDao dao;

	public List<BalJgkjSevenDataSource> selectSevenDataSourceList(BalJgkjSevenDataSource sevenDataSource) {
		return dao.selectSevenDataSourceList(sevenDataSource);
	}

}
