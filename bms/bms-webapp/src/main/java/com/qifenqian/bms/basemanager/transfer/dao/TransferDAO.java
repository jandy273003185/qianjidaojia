package com.qifenqian.bms.basemanager.transfer.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.transfer.bean.TransferBean;
import com.qifenqian.bms.basemanager.transfer.mapper.TransferMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class TransferDAO {
	
	@Autowired
	private TransferMapper transferMapper;
	
	@Page
	public List<TransferBean> selectTransfer(TransferBean transferBean){
		return transferMapper.selectTransfer(transferBean);
	}
	
}
