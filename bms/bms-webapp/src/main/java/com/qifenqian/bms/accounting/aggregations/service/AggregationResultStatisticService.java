package com.qifenqian.bms.accounting.aggregations.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.aggregations.bean.BalAggregationResultStatistic;
import com.qifenqian.bms.accounting.aggregations.bean.BaseChannel;
import com.qifenqian.bms.accounting.aggregations.dao.BalAggregationResultStatisticDAO;



@Service
public class AggregationResultStatisticService {
	
	@Autowired
	private BalAggregationResultStatisticDAO balAggregationResultStatisticDAO;
	
	public List<BalAggregationResultStatistic> selectList(BalAggregationResultStatistic balAggregationResultStatistic){
		
		return balAggregationResultStatisticDAO.selectList(balAggregationResultStatistic);		
	}
	
	public List<BaseChannel> queryBalBaseChannel() {
		
		return balAggregationResultStatisticDAO.queryBalBaseChannel();
	}
	
	
}

