package com.qifenqian.bms.accounting.financequery.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.financequery.bean.RealTimeBussBalanceWater;
import com.qifenqian.bms.accounting.financequery.bean.RealTimeCustBalanceWater;
import com.qifenqian.bms.accounting.financequery.mapper.RealTimeBalanceWaterMapper;
import com.qifenqian.bms.platform.web.page.Page;
@Repository
public class RealTimeBalanceWaterDao {
	@Autowired
	private RealTimeBalanceWaterMapper mapper;
	
	@Page
	public List<RealTimeCustBalanceWater> selectCustBalanceWaterList(RealTimeCustBalanceWater queryBean){
		return mapper.selectCustBalanceWaterList(queryBean);
	}
	@Page
	public List<RealTimeBussBalanceWater> selectBussBalanceWaterList(RealTimeBussBalanceWater queryBean) {
		return mapper.selectBussBalanceWaterList(queryBean);
	}
	
}
