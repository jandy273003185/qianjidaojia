package com.qifenqian.bms.accounting.adjust.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.adjust.bean.AccountingSingleAdjustDetail;
import com.qifenqian.bms.accounting.adjust.bean.AccountingSingleAdjustDetailExample;
import com.qifenqian.bms.accounting.adjust.bean.AccountingSingleAdjustDetailKey;


@MapperScan
public interface AccountingSingleAdjustDetailMapper {
    int countByExample(AccountingSingleAdjustDetailExample example);

    int deleteByExample(AccountingSingleAdjustDetailExample example);

    int deleteByPrimaryKey(AccountingSingleAdjustDetailKey key);

    int insert(AccountingSingleAdjustDetail record);

    int insertSelective(AccountingSingleAdjustDetail record);

    List<AccountingSingleAdjustDetail> selectByExample(AccountingSingleAdjustDetailExample example);

    AccountingSingleAdjustDetail selectByPrimaryKey(AccountingSingleAdjustDetailKey key);

    int updateByExampleSelective(@Param("record") AccountingSingleAdjustDetail record, @Param("example") AccountingSingleAdjustDetailExample example);

    int updateByExample(@Param("record") AccountingSingleAdjustDetail record, @Param("example") AccountingSingleAdjustDetailExample example);

    int updateByPrimaryKeySelective(AccountingSingleAdjustDetail record);

    int updateByPrimaryKey(AccountingSingleAdjustDetail record);
}
