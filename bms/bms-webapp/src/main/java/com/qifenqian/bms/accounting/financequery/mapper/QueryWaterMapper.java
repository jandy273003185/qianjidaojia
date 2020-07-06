package com.qifenqian.bms.accounting.financequery.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.financequery.bean.QueryWaterVo;
import com.qifenqian.bms.common.annotation.MapperScanCore;

@MapperScanCore
public interface QueryWaterMapper {
	List<QueryWaterVo>selectQueryWaterList(QueryWaterVo waterVo);
}
