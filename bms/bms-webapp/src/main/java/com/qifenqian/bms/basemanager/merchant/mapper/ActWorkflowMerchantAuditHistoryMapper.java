package com.qifenqian.bms.basemanager.merchant.mapper;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.merchant.bean.ActWorkflowMerchantAudit;

@MapperScan
public interface ActWorkflowMerchantAuditHistoryMapper {
	 int insert(ActWorkflowMerchantAudit record);
}
