package com.qifenqian.bms.basemanager.transferRevoke.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.transferRevoke.bean.TransferRevoke;
import com.qifenqian.bms.basemanager.transferRevoke.mapper.TransferRevokeMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class TransferRevokeDao {
	
	@Autowired
	private TransferRevokeMapper transferRevokeMapper;

	@Page
	public List<TransferRevoke> selectTransferRevokeList(TransferRevoke queryBean) {
		return transferRevokeMapper.selectTransferRevokeList(queryBean);
	}
}
