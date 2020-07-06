package com.qifenqian.bms.accounting.unnamedProcessing.clearjgkjtrade.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.unnamedProcessing.clearjgkjtrade.bean.ClearJgkjTrade;
import com.qifenqian.bms.accounting.unnamedProcessing.clearjgkjtrade.mapper.ClearJgkjTradeMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class ClearJgkjTradeDao {
	
	@Autowired
	private ClearJgkjTradeMapper mapper;
	@Page
	public List<ClearJgkjTrade> selectClearJgkjTradeList(ClearJgkjTrade clearJgkjTrade){
		return mapper.selectClearJgkjTradeList(clearJgkjTrade);
	}
}
