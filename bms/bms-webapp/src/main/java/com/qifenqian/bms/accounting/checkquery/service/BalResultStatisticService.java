package com.qifenqian.bms.accounting.checkquery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.checkquery.bean.BalResultStatistic;
import com.qifenqian.bms.accounting.checkquery.dao.BalResultStatisticDao;
/**
 * 交广科技对账结果统计报表
 * @author shen
 *
 */
@Service
public class BalResultStatisticService {

	@Autowired
	private BalResultStatisticDao balResultStatisticDao;
	
	public List<BalResultStatistic> selectListByPage(BalResultStatistic selectBean){
		return balResultStatisticDao.selectListByPage(selectBean);
	}
}
