package com.qifenqian.bms.basemanager.tradeRevoke.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.tradeRevoke.bean.CustTransRevoke;
import com.qifenqian.bms.basemanager.tradeRevoke.mapper.CustTransRevokeMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class CustTransRevokeDao {

	@Autowired
	private CustTransRevokeMapper custTransRevokeMapper;
	
	@Page
	public List<CustTransRevoke> selectTransRevokeList(CustTransRevoke queryBean) {
		return custTransRevokeMapper.selectTransRevokeList(queryBean);
	};

	public void insert(CustTransRevoke insertBean) {
		custTransRevokeMapper.insert(insertBean);
	}

	public void updateByAudit(CustTransRevoke updateBean) {
		custTransRevokeMapper.updateByAudit(updateBean);
	}
	
	public void updateByRevoke(CustTransRevoke updateBean) {
		custTransRevokeMapper.updateByRevoke(updateBean);
	}

}
