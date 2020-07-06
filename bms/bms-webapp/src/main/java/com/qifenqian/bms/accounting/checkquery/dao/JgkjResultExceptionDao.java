package com.qifenqian.bms.accounting.checkquery.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.checkquery.bean.JgkjResultException;
import com.qifenqian.bms.accounting.checkquery.mapper.JgkjResultExceptionMapper;
import com.qifenqian.bms.platform.web.page.Page;
@Repository
public class JgkjResultExceptionDao {
	@Autowired
	private JgkjResultExceptionMapper mapper;
	@Page
	public List<JgkjResultException> selectZytResultExceptionList(JgkjResultException exception){
		return mapper.selectZytResultExceptionList(exception);
	}
}
