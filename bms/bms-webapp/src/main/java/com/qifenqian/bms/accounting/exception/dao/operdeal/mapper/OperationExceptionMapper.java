package com.qifenqian.bms.accounting.exception.dao.operdeal.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.exception.base.bean.Operation;
import com.qifenqian.bms.accounting.exception.base.bean.OperationExcel;
import com.qifenqian.bms.accounting.exception.dao.operdeal.bean.OperationException;
import com.qifenqian.bms.accounting.exception.dao.operdealflow.bean.OperationExceptionFlow;

/**
 * 异常处理主表：bms_operation_exception
 * 
 * @project sevenpay-bms-web
 * @fileName OperationExceptionMapper.java
 * @author huiquan.ma
 * @date 2015年10月20日
 * @memo 
 */
@MapperScan
public interface OperationExceptionMapper {
	
	/**
	 * 查询各场景异常列表
	 * @param selectBean
	 * @return
	 */
	List<Operation> selectExceptionList(Operation selectBean);
	
	/**
	 * 更新异常处理主表
	 * @param operation
	 * @return
	 */
	int updateOperationException(OperationException operationException);
	
	/**
	 * 新增操作记录
	 * @param operation
	 * @return
	 */
	int insertOperationException(OperationException operationException);
	
	/**
	 * 查询业务流水操作记录
	 * @param operationId
	 * @return
	 */
	String selectOperationExceptionByOperationId(@Param("operationId") String operationId);
	
	/**
	 * 查看操作记录
	 * @param operationId
	 * @return
	 */
	List<OperationExceptionFlow> queryOperationRecord(@Param("operationId") String operationId);
	
	/**
	 * 导出报表
	 * @param requestBean
	 * @return
	 */
	List<OperationExcel> exportOperationListExcel(Operation requestBean);
}


