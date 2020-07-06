package com.qifenqian.bms.accounting.checkquery.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.checkquery.bean.Qfqexception;
import com.qifenqian.bms.accounting.checkquery.mapper.QfqexceptionMapper;
import com.qifenqian.bms.platform.web.page.Page;
@Repository
public class QfqexceptionDao {
	@Autowired
	private QfqexceptionMapper mapper;
	@Page
	public List<Qfqexception> selectQfqResultExceptionList(Qfqexception exception){
		return mapper.selectQfqResultExceptionList(exception);
	}
}
