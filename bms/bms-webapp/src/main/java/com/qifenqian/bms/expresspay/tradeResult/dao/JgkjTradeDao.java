package com.qifenqian.bms.expresspay.tradeResult.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.expresspay.mpper.JgkjTradeMapper;
import com.qifenqian.bms.expresspay.tradeResult.bean.JgkjTrade;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class JgkjTradeDao {
	@Autowired
	private JgkjTradeMapper jgkjTradeMapper;
	@Page
	public List<JgkjTrade> queryJgkjTradeList(JgkjTrade queryBean) {
		return jgkjTradeMapper.queryJgkjTradeList(queryBean);
	}
}
