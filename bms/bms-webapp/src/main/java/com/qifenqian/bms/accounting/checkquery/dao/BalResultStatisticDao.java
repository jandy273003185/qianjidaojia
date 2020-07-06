package com.qifenqian.bms.accounting.checkquery.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.checkquery.bean.BalResultStatistic;
import com.qifenqian.bms.accounting.checkquery.mapper.BalResultStatisticMapper;
import com.qifenqian.bms.platform.web.page.Page;

/**
 * @project sevenpay-bms-web
 * @fileName BalResultStatisticDao.java
 * @author huiquan.ma
 * @date 2015年10月16日
 * @memo 
 */
@Repository
public class BalResultStatisticDao {

	@Autowired
	private BalResultStatisticMapper balResultStatisticMapper;
	
	@Page
	public List<BalResultStatistic> selectListByPage(BalResultStatistic selectBean) {
		return balResultStatisticMapper.selectList(selectBean);
	}
}


