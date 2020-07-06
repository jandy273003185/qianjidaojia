package com.qifenqian.bms.accounting.kingdee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.kingdee.bean.BmsClearKingdeePayInfo;
import com.qifenqian.bms.accounting.kingdee.dao.BmsClearKingdeePayDAO;

@Service
public class BmsClearKingdeePayService {
	
	@Autowired
	private BmsClearKingdeePayDAO bmsClearKingdeePayDAO;
	
	public List<BmsClearKingdeePayInfo> selectList(BmsClearKingdeePayInfo bmsClearKingdeePayInfo){
		return bmsClearKingdeePayDAO.selectList(bmsClearKingdeePayInfo);
	}
}
