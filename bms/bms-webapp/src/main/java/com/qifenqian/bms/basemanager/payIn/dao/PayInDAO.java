package com.qifenqian.bms.basemanager.payIn.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.payIn.bean.PayIn;
import com.qifenqian.bms.basemanager.payIn.mapper.PayInMapper;
import com.qifenqian.bms.basemanager.payIn.service.PayInService;
import com.qifenqian.bms.platform.web.page.Page;

/**
 * dao层，一般分页需要
 * 
 * @project sevenpay-bms-web
 * @fileName PayInDAO.java
 * @author Dayet
 * @date 2017年11月28日日
 * @memo 
 */
@Repository
public class PayInDAO{

	@Autowired
	private PayInMapper payInMapper;
	@Resource
	private PayInService payInService;
	
	/**
	 * 分页查询代付垫资费率信息列表
	 * @return
	 */
	@Page
	public List<PayIn> selectPayIn(PayIn payIn) {
		return payInMapper.selectPayIns(payIn);
	}
}


