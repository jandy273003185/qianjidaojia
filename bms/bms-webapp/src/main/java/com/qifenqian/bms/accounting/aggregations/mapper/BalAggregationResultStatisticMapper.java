package com.qifenqian.bms.accounting.aggregations.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.aggregations.bean.BalAggregationResultStatistic;
import com.qifenqian.bms.accounting.aggregations.bean.BaseChannel;
import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;

@MapperScanCombinedmaster 
public interface BalAggregationResultStatisticMapper {
	
	List<BalAggregationResultStatistic> selectList(BalAggregationResultStatistic requestBean);

	List<BaseChannel> queryBalBaseChannel();
}