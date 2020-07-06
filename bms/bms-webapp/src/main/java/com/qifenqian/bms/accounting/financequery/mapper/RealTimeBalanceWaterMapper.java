package com.qifenqian.bms.accounting.financequery.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.financequery.bean.RealTimeBussBalanceWater;
import com.qifenqian.bms.accounting.financequery.bean.RealTimeCustBalanceWater;
import com.qifenqian.bms.common.annotation.MapperScanCore;

@MapperScanCore
public interface RealTimeBalanceWaterMapper {
	List<RealTimeCustBalanceWater> selectCustBalanceWaterList(RealTimeCustBalanceWater queryBean);

	List<RealTimeBussBalanceWater> selectBussBalanceWaterList(RealTimeBussBalanceWater queryBean);

}
