package com.qifenqian.bms.accounting.financequery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.financequery.bean.QueryWaterVo;
import com.qifenqian.bms.accounting.financequery.dao.QueryWaterDao;

@Service
public class QueryWaterService {
	@Autowired
	private QueryWaterDao dao;

	public List<QueryWaterVo> selectQueryWaterList(QueryWaterVo waterVo) {
		return dao.selectQueryWaterList(waterVo);
	}

}
