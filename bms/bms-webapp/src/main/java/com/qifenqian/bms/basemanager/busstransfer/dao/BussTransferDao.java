package com.qifenqian.bms.basemanager.busstransfer.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.busstransfer.bean.BussTransferBean;
import com.qifenqian.bms.basemanager.busstransfer.mapper.BussTransferMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class BussTransferDao {
	
	@Autowired
	private BussTransferMapper bussTransferMapper;
	
	@Page
	public List<BussTransferBean> selectTransfer(BussTransferBean bussTransferBean){
		return bussTransferMapper.selectTransfer(bussTransferBean);
	}
	
}
