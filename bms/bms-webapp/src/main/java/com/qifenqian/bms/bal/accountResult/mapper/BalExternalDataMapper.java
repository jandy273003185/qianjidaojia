package com.qifenqian.bms.bal.accountResult.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qifenqian.bms.bal.accountResult.bean.BalExternalData;
import com.qifenqian.bms.bal.accountResult.bean.BalExternalDataExample;
import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;

@MapperScanCombinedmaster
public interface BalExternalDataMapper {
    int countByExample(BalExternalDataExample example);

    int deleteByExample(BalExternalDataExample example);

    int deleteByPrimaryKey(Integer dataId);

    int insert(BalExternalData record);

    int insertSelective(BalExternalData record);

    List<BalExternalData> selectByExample(BalExternalDataExample example);

    BalExternalData selectByPrimaryKey(Integer dataId);

    int updateByExampleSelective(@Param("record") BalExternalData record, @Param("example") BalExternalDataExample example);

    int updateByExample(@Param("record") BalExternalData record, @Param("example") BalExternalDataExample example);

    int updateByPrimaryKeySelective(BalExternalData record);

    int updateByPrimaryKey(BalExternalData record);

	List<BalExternalData> selectList(BalExternalData requestBean);
}