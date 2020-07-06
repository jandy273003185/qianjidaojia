package com.qifenqian.bms.accounting.jgkjdatasource.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.jgkjdatasource.bean.BalJgkjDataSource;
import com.qifenqian.bms.accounting.jgkjdatasource.dao.BalJgkjDataSourceDao;

@Service
public class BalJgkjDataSourceService {
	@Autowired
	private BalJgkjDataSourceDao dao;

	public List<BalJgkjDataSource> selectZytDataSourceList(BalJgkjDataSource zytDataSource) {
		return dao.selectZytDataSourceList(zytDataSource);
	}

}
