package com.qifenqian.bms.basemanager.tradesummary.mapper;

import java.util.List;

import com.qifenqian.bms.basemanager.tradesummary.bean.TransSummaryBean;
import com.qifenqian.bms.common.annotation.MapperScanSub;

@MapperScanSub
public interface TransSummaryMapper {

	List<TransSummaryBean> selectTradeSummary(TransSummaryBean queryBean);
	
	
	List<TransSummaryBean> selectTradeSummaryExcel(TransSummaryBean queryBean);

}
