package com.qifenqian.bms.accounting.exception.dao.transyl.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.qifenqian.bms.accounting.exception.dao.transyl.bean.TransYl;
import com.qifenqian.bms.accounting.exception.dao.transyl.mapper.TransYlMapper;

@Repository
public class TransYlDao {

	@Autowired
	private TransYlMapper transYlMapper;

	@Transactional
	public void insertTransYl(TransYl transBean) {
		transYlMapper.insertTransYl(transBean);

	}

	@Transactional
	public void updateTransYl(TransYl updateBean) {
		transYlMapper.updateTransYl(updateBean);

	}
	
	public TransYl selectTransYlByTransId(String transFlowId) {
		return transYlMapper.selectTransYlByTransId(transFlowId);
	}
	
	public TransYl selectTransYlByTransSn(String transSn) {
		return transYlMapper.selectTransYlByTransSn(transSn);
	}
}
