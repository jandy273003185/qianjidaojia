package com.qifenqian.bms.accounting.adjust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustDetail;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustDetailExample;
import com.qifenqian.bms.accounting.adjust.bean.AccountingAdjustDetailKey;


@MapperScan
public interface AccountingAdjustDetailMapper {
    int countByExample(AccountingAdjustDetailExample example);

    int deleteByExample(AccountingAdjustDetailExample example);

    int deleteByPrimaryKey(AccountingAdjustDetailKey key);

    int insert(AccountingAdjustDetail record);

    int insertSelective(AccountingAdjustDetail record);

    List<AccountingAdjustDetail> selectByExample(AccountingAdjustDetailExample example);

    AccountingAdjustDetail selectByPrimaryKey(AccountingAdjustDetailKey key);

    int updateByExampleSelective(@Param("record") AccountingAdjustDetail record, @Param("example") AccountingAdjustDetailExample example);

    int updateByExample(@Param("record") AccountingAdjustDetail record, @Param("example") AccountingAdjustDetailExample example);

    int updateByPrimaryKeySelective(AccountingAdjustDetail record);

    int updateByPrimaryKey(AccountingAdjustDetail record);
}
