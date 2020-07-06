package com.qifenqian.bms.basemanager.trade.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.trade.bean.AllTradeBill;
import com.qifenqian.bms.basemanager.trade.bean.TdTradeBillMainVO;
import com.qifenqian.bms.basemanager.trade.mapper.AllTradeBillMapper;
import com.qifenqian.bms.basemanager.trade.mapper.TdTradeBillMainMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class TradeBillDAO {
	
	@Autowired
	private TdTradeBillMainMapper tdTradeBillMainMapper;
	
	@Autowired
	private AllTradeBillMapper allTradeBillMapper;
	/**
	 * 交易汇总
	 * @param tdTradeBillMainVO
	 * @return
	 */
	@Page
	public List<TdTradeBillMainVO> selectTdradeBillMainSummary(TdTradeBillMainVO tdTradeBillMainVO){
		return tdTradeBillMainMapper.selectTdradeBillMainSummary(tdTradeBillMainVO);
	}
	
	/**
	 * 查询消费流水
	 * @param tdTradeBillMainVO
	 * @return
	 */
	@Page
	public List<TdTradeBillMainVO> selectConsume(TdTradeBillMainVO tdTradeBillMainVO){
		return tdTradeBillMainMapper.selectConsume(tdTradeBillMainVO);
	}

	@Page
	public List<AllTradeBill> getAllTradeBill(String bank,String payProd,String payChannel) {
		// TODO Auto-generated method stub
		return allTradeBillMapper.getAllTradeBill(bank,payProd,payChannel);
	}

	public List<AllTradeBill> getAllTradeBillExport(String bank,
			String payProd, String payChannel) {
		// TODO Auto-generated method stub
		return allTradeBillMapper.getAllTradeBillExport(bank,payProd,payChannel);
	}
	
}
