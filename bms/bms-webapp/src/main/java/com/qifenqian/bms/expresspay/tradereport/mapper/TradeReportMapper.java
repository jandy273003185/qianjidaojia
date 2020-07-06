package com.qifenqian.bms.expresspay.tradereport.mapper;

import java.util.List;

import com.qifenqian.bms.common.annotation.MapperScanCore;
import com.qifenqian.bms.expresspay.tradereport.bean.TradeReport;

@MapperScanCore
public interface TradeReportMapper {

	List<TradeReport> selectReportList(TradeReport tradeReport);
}
