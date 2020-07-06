package com.qifenqian.bms.accounting.checkquery.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.checkquery.bean.JgkjResultException;
import com.qifenqian.bms.accounting.checkquery.dao.JgkjResultExceptionDao;

@Service
public class JgkjResultExceptionService {
	@Autowired
	private JgkjResultExceptionDao dao;
/**
 * 交广科技对账结果交广科技存疑表报表
 * @param exception
 * @return
 */
	public List<JgkjResultException> selectZytResultExceptionList(JgkjResultException exception) {
		return dao.selectZytResultExceptionList(exception);
	}

}
