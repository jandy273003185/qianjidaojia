package com.qifenqian.bms.basemanager.withdrawControl.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.withdrawControl.bean.WithdrawControl;
import com.qifenqian.bms.basemanager.withdrawControl.mapper.WithdrawControlMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class WithdrawControlDAO {
	
	@Autowired
	private WithdrawControlMapper withdrawControlMapper;
	
	@Page
	public List<WithdrawControl> selectAll(WithdrawControl withdrawControl){
		return withdrawControlMapper.selectAll(withdrawControl);
	}
}
