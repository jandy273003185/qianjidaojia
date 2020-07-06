package com.qifenqian.bms.basemanager.tradesummary.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.tradesummary.bean.TransSummaryBean;
import com.qifenqian.bms.basemanager.tradesummary.mapper.TransSummaryMapper;

@Service
public class TransSummaryService { 
	@Autowired
	private TransSummaryMapper transSummaryMapper;
	
	public List<TransSummaryBean> selectTradeSummary(TransSummaryBean queryBean){
		return transSummaryMapper.selectTradeSummary(queryBean);
	}
	
	public List<TransSummaryBean> selectTradeSummaryExcel(TransSummaryBean queryBean){
		return transSummaryMapper.selectTradeSummaryExcel(queryBean);
	}
}
