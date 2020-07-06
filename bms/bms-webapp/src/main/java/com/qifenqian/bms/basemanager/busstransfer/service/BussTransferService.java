package com.qifenqian.bms.basemanager.busstransfer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.busstransfer.bean.BussTransferBean;
import com.qifenqian.bms.basemanager.busstransfer.bean.BussTransferExcel;
import com.qifenqian.bms.basemanager.busstransfer.dao.BussTransferDao;
import com.qifenqian.bms.basemanager.busstransfer.mapper.BussTransferMapper;


@Service
public class BussTransferService {

	@Autowired
	private BussTransferDao bussTransferDao;
	
	@Autowired
	private BussTransferMapper bussTransferMapper;
	
	/**
	 * 查询转账信息
	 * @param transferBean
	 * @return
	 */
	public List<BussTransferBean> selectTransfer(BussTransferBean bussTransferBean){
		return bussTransferDao.selectTransfer(bussTransferBean);
	}
	
	/**
	 * 查询转账列表信息
	 * @param transferBean
	 * @return
	 */
	public List<BussTransferExcel> selectTransferExcel(BussTransferBean bussTransferBean){
		return bussTransferMapper.selectTransferExcel(bussTransferBean);
	}
}
