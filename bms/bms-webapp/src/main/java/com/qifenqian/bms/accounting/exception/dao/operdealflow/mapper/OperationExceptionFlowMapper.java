package com.qifenqian.bms.accounting.exception.dao.operdealflow.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.exception.dao.operdealflow.bean.OperationExceptionFlow;

/**
 * 异常处理主表：bms_operation_exception_flow
 * 
 * @project sevenpay-bms-web
 * @fileName OperationExceptionMapper.java
 * @author huiquan.ma
 * @date 2015年10月20日
 * @memo 
 */
@MapperScan
public interface OperationExceptionFlowMapper {

	/**
	 * 插入流水操作日志
	 * @param exceptionFlow
	 * @return
	 */
	int insertExceptionFlow(OperationExceptionFlow exceptionFlow);
	
	/**
	 * 查询操作日志
	 * @param operationId
	 * @return
	 */
	int selectExceptionFlowByOperationId(@Param("operationId") String operationId);
	
}


