package com.qifenqian.bms.accounting.exception.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.exception.base.bean.Operation;
import com.qifenqian.bms.accounting.exception.dao.operdeal.mapper.OperationExceptionMapper;
import com.qifenqian.bms.accounting.exception.dao.operdealflow.bean.OperationExceptionFlow;
import com.qifenqian.bms.platform.web.page.Page;

/**
 * @project sevenpay-bms-web
 * @fileName OperationExceptionDao.java
 * @author huiquan.ma
 * @date 2015年10月29日
 * @memo 
 */
@Repository
public class OperationExceptionDao {

	@Autowired
	private OperationExceptionMapper operationExceptionMapper;
	
	@Page
	public List<Operation> selectOperationExceptionListByPage(Operation selectBean) {
		return operationExceptionMapper.selectExceptionList(selectBean);
	}
	
	
	@Page
	public List<OperationExceptionFlow> queryOperationRecord(String operId) {
		return operationExceptionMapper.queryOperationRecord(operId);
	}
}


