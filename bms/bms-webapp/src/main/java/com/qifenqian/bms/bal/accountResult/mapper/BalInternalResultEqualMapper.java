package com.qifenqian.bms.bal.accountResult.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qifenqian.bms.bal.accountResult.bean.BalInternalResultEqual;
import com.qifenqian.bms.bal.accountResult.bean.BalInternalResultEqualExample;
import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;

@MapperScanCombinedmaster
public interface BalInternalResultEqualMapper {
    int countByExample(BalInternalResultEqualExample example);

    int deleteByExample(BalInternalResultEqualExample example);

    int deleteByPrimaryKey(Integer equalId);

    int insert(BalInternalResultEqual record);

    int insertSelective(BalInternalResultEqual record);

    List<BalInternalResultEqual> selectByExample(BalInternalResultEqualExample example);

    BalInternalResultEqual selectByPrimaryKey(Integer equalId);

    int updateByExampleSelective(@Param("record") BalInternalResultEqual record, @Param("example") BalInternalResultEqualExample example);

    int updateByExample(@Param("record") BalInternalResultEqual record, @Param("example") BalInternalResultEqualExample example);

    int updateByPrimaryKeySelective(BalInternalResultEqual record);

    int updateByPrimaryKey(BalInternalResultEqual record);

	List<BalInternalResultEqual> selectList(BalInternalResultEqual record);
}