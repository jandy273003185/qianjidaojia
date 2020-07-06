package com.qifenqian.bms.accounting.aggregations.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.aggregations.bean.BalAggregationResultStatistic;
import com.qifenqian.bms.accounting.aggregations.bean.BaseChannel;
import com.qifenqian.bms.accounting.aggregations.mapper.BalAggregationResultStatisticMapper;
import com.qifenqian.bms.platform.web.page.Page;



@Repository
public class BalAggregationResultStatisticDAO {
	
	@Autowired
	private BalAggregationResultStatisticMapper balAggregationResultStatisticMapper;
	
	@Page
	public List<BalAggregationResultStatistic> selectList(BalAggregationResultStatistic balAggregationResultStatistic) {
			
		return balAggregationResultStatisticMapper.selectList(balAggregationResultStatistic);
	}

	public List<BaseChannel> queryBalBaseChannel() {
		
		return balAggregationResultStatisticMapper.queryBalBaseChannel();
	}

	

}
