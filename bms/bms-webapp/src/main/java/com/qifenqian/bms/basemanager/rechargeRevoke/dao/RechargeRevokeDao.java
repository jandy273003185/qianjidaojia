package com.qifenqian.bms.basemanager.rechargeRevoke.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.rechargeRevoke.bean.RechargeRevoke;
import com.qifenqian.bms.basemanager.rechargeRevoke.mapper.RechargeRevokeMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class RechargeRevokeDao {
	
	@Autowired
	private RechargeRevokeMapper rechargeRevokeMapper;
	
	@Page
	public List<RechargeRevoke> selectTransRevokeList(RechargeRevoke queryBean) {
		return rechargeRevokeMapper.selectRechargeRevokeList(queryBean);
	};
	
	
}
