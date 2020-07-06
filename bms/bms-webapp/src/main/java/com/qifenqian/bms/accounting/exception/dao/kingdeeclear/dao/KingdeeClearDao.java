package com.qifenqian.bms.accounting.exception.dao.kingdeeclear.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.exception.dao.kingdeeclear.bean.KingdeePayEntry;
import com.qifenqian.bms.accounting.exception.dao.kingdeeclear.mapper.KingdeeClearMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class KingdeeClearDao {

	@Autowired
	private KingdeeClearMapper kingdeeClearMapper;

	@Page
	public List<KingdeePayEntry> queryKingdeeEntryList(String clearId) {
		return kingdeeClearMapper.queryKingdeeEntryList(clearId);
	}

}
