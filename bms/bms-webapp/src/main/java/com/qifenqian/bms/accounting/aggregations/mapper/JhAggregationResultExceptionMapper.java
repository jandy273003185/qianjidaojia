package com.qifenqian.bms.accounting.aggregations.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.aggregations.bean.JhAggregationResultException;
import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;


@MapperScanCombinedmaster
public interface JhAggregationResultExceptionMapper {

	List<JhAggregationResultException> selectJhResultExceptionList(
			JhAggregationResultException jhAggregationResultException);

	

	

}
