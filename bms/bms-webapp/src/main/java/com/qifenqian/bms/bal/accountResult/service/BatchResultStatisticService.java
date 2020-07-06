package com.qifenqian.bms.bal.accountResult.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.bal.accountResult.bean.BalBatchResultStatistic;
import com.qifenqian.bms.bal.accountResult.bean.BaseChannel;
import com.qifenqian.bms.bal.accountResult.dao.BalBatchResultStatisticDAO;

@Service
public class BatchResultStatisticService {
	
	@Autowired
	private BalBatchResultStatisticDAO balBatchResultStatisticDAO;
	
	public List<BalBatchResultStatistic> selectList(BalBatchResultStatistic balBatchResultStatistic){
		
		return balBatchResultStatisticDAO.selectList(balBatchResultStatistic);		
	}
	
	public List<BaseChannel> queryBalBaseChannel() {
		
		return balBatchResultStatisticDAO.queryBalBaseChannel();
	}
	
	
}

