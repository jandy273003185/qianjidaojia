package com.qifenqian.bms.accounting.kingdee.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.kingdee.bean.BmsClearKingdeePayInfo;
import com.qifenqian.bms.accounting.kingdee.mapper.BmsClearKingdeePayMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class BmsClearKingdeePayDAO {
	
	@Autowired
	private BmsClearKingdeePayMapper bmsClearKingdeePayMapper;
	
	@Page
	public List<BmsClearKingdeePayInfo> selectList(BmsClearKingdeePayInfo bmsClearKingdeePayInfo){
		return bmsClearKingdeePayMapper.selectList(bmsClearKingdeePayInfo);
	}
}
