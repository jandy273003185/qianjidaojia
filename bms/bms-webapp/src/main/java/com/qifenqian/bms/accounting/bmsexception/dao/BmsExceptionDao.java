package com.qifenqian.bms.accounting.bmsexception.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.bmsexception.bean.BmsException;
import com.qifenqian.bms.accounting.bmsexception.mapper.BmsExceptionMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class BmsExceptionDao {
	@Autowired
	private BmsExceptionMapper bmsExceptionMapper;

	@Page
	public List<BmsException> selectBmsExceptionList(BmsException queryBean) {
		return bmsExceptionMapper.selectBmsExceptionList(queryBean);
	}
}
