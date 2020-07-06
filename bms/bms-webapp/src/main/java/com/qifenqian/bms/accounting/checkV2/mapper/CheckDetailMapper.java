package com.qifenqian.bms.accounting.checkV2.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.checkV2.bean.CheckDetail;
import com.qifenqian.bms.common.annotation.MapperScanPayment;
@MapperScanPayment
public interface CheckDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CheckDetail record);

    int insertSelective(CheckDetail record);

    CheckDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CheckDetail record);

    int updateByPrimaryKey(CheckDetail record);
    
    List<CheckDetail> findCheckDetail(CheckDetail checkDetail);
    
}