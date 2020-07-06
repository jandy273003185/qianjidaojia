package com.qifenqian.bms.accounting.unnamedProcessing.clearjgkjtrade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.unnamedProcessing.clearjgkjtrade.bean.ClearJgkjTrade;
import com.qifenqian.bms.accounting.unnamedProcessing.clearjgkjtrade.dao.ClearJgkjTradeDao;

@Service
public class ClearJgkjTradeService {
	@Autowired
	private ClearJgkjTradeDao dao;
	
	public List<ClearJgkjTrade> selectClearJgkjTradeList(ClearJgkjTrade clearJgkjTrade){
		return dao.selectClearJgkjTradeList(clearJgkjTrade);
	}
}
