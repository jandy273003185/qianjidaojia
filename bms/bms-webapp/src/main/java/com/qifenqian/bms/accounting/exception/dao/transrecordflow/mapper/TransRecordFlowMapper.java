package com.qifenqian.bms.accounting.exception.dao.transrecordflow.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qifenqian.bms.accounting.exception.dao.transrecordflow.bean.TransRecordFlow;
import com.qifenqian.bms.common.annotation.MapperScanCore;

/**
 * 报文表：trans_record_flow
 * 
 * @project sevenpay-bms-web
 * @fileName TransRecordFlowMapper.java
 * @author huiquan.ma
 * @date 2015年10月28日
 * @memo 
 */
@MapperScanCore
public interface TransRecordFlowMapper {

	/**
	 * 根据请求编号查询交易流水列表
	 * @param reqSerialId
	 * @return
	 */
	List<TransRecordFlow> selectRecordFlowListByReqId(String reqSerialId);
	
	/**
	 * 根据id查询流水
	 * @param transRecordFlow
	 * @return
	 */
	TransRecordFlow selectRecordFlowById(@Param("id") String id);
	
	/**
	 *根据流程编号查询
	 * @param reqSerialId
	 * @return
	 */
	TransRecordFlow selectRecordFlowByReqId(String reqSerialId);
	
	/***
	 * 更新报文表状态
	 * @param transRecordFlow
	 * @return
	 */
	int updateRecordFlow(TransRecordFlow transRecordFlow);
	
}


