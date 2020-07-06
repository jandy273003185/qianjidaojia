package com.qifenqian.bms.basemanager.merchant.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.merchant.bean.ActWorkflowMerchantAudit;


@MapperScan
public interface ActWorkflowMerchantAuditMapper {

    int deleteByPrimaryKey(String id);

    int insert(ActWorkflowMerchantAudit record);

    int insertSelective(ActWorkflowMerchantAudit record);


    ActWorkflowMerchantAudit selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActWorkflowMerchantAudit record);

    int updateByPrimaryKey(ActWorkflowMerchantAudit record);
    
    ActWorkflowMerchantAudit selectListByMerchantId(@Param("merchantId")String  id);
}
