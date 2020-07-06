package com.qifenqian.bms.basemanager.tradeControl.Dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.tradeControl.bean.TradeControl;
import com.qifenqian.bms.basemanager.tradeControl.mapper.TradeControlMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class TradeControlDAO {
	
	@Autowired
	private TradeControlMapper tradeContrlMapper;
	
	@Page
	public List<TradeControl> selectAll(TradeControl tradeControl){
		return tradeContrlMapper.selectAll(tradeControl);
	}
}
