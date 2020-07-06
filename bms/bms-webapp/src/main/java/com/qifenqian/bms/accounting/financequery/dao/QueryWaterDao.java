package com.qifenqian.bms.accounting.financequery.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.financequery.bean.QueryWaterVo;
import com.qifenqian.bms.accounting.financequery.mapper.QueryWaterMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class QueryWaterDao {
	@Autowired
	private QueryWaterMapper mapper;
	@Page
	public List<QueryWaterVo>selectQueryWaterList(QueryWaterVo waterVo){
		return mapper.selectQueryWaterList(waterVo);
	}
}
