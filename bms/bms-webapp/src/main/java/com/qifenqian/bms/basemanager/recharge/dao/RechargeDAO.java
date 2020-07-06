package com.qifenqian.bms.basemanager.recharge.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.recharge.bean.RechargeBean;
import com.qifenqian.bms.basemanager.recharge.mapper.RechargeMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class RechargeDAO {
	
	@Autowired
	private RechargeMapper rechargeMapper;
	
	@Page
	public List<RechargeBean> selectRecharge(RechargeBean recharge){
		
		return rechargeMapper.selectRecharge(recharge);
	}

}
