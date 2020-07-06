package com.qifenqian.bms.bal.accountResult.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.bal.accountResult.bean.BalBatchResultStatistic;
import com.qifenqian.bms.bal.accountResult.bean.BaseChannel;
import com.qifenqian.bms.bal.accountResult.mapper.BalBatchResultStatisticMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class BalBatchResultStatisticDAO {
	
	@Autowired
	private BalBatchResultStatisticMapper BalBatchResultStatisticMapper;
	
	@Page
	public List<BalBatchResultStatistic> selectList(BalBatchResultStatistic balBatchResultStatistic) {
			
		return BalBatchResultStatisticMapper.selectList(balBatchResultStatistic);
	}

	public List<BaseChannel> queryBalBaseChannel() {
		
		return BalBatchResultStatisticMapper.queryBalBaseChannel();
	}

}
