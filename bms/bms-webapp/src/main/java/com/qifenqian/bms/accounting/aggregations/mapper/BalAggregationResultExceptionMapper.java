package com.qifenqian.bms.accounting.aggregations.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.aggregations.bean.BalAggregationResultException;
import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;

@MapperScanCombinedmaster
public interface BalAggregationResultExceptionMapper {
	
	List<BalAggregationResultException> selectZxResultExceptionList(BalAggregationResultException balAggregationResultException);
	
}
