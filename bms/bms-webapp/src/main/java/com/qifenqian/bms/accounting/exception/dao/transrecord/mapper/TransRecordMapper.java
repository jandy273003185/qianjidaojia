package com.qifenqian.bms.accounting.exception.dao.transrecord.mapper;

import com.qifenqian.bms.accounting.exception.dao.transrecord.bean.TransRecord;
import com.qifenqian.bms.common.annotation.MapperScanCore;

/**
 * 报文表：trans_record
 * 
 * @project sevenpay-bms-web
 * @fileName TransRecordMapper.java
 * @author huiquan.ma
 * @date 2015年10月28日
 * @memo
 */
@MapperScanCore
public interface TransRecordMapper {
	/***
	 * 根据报文id获取报文信息
	 * 
	 * @param transFlowId
	 * @return
	 */
	TransRecord selectTransRecordByMsgId(String msgId);

	/**
	 * 根据对象查询交易报文
	 * @param selectBean
	 * @return
	 */
	TransRecord selectSingleByRequest(TransRecord selectBean);
	
	/***
	 * 修改核心报文状态
	 * @param updateBean
	 * @return
	 */
	int updateTransRecordStatus(TransRecord updateBean);
}
