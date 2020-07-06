package com.qifenqian.bms.bal.accountResult.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qifenqian.bms.bal.accountResult.bean.BalExternalResultEqual;
import com.qifenqian.bms.bal.accountResult.bean.BalExternalResultEqualExample;
import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;

@MapperScanCombinedmaster
public interface BalExternalResultEqualMapper {
    int countByExample(BalExternalResultEqualExample example);

    int deleteByExample(BalExternalResultEqualExample example);

    int deleteByPrimaryKey(Integer equalId);

    int insert(BalExternalResultEqual record);

    int insertSelective(BalExternalResultEqual record);

    List<BalExternalResultEqual> selectByExample(BalExternalResultEqualExample example);

    BalExternalResultEqual selectByPrimaryKey(Integer equalId);

    int updateByExampleSelective(@Param("record") BalExternalResultEqual record, @Param("example") BalExternalResultEqualExample example);

    int updateByExample(@Param("record") BalExternalResultEqual record, @Param("example") BalExternalResultEqualExample example);

    int updateByPrimaryKeySelective(BalExternalResultEqual record);

    int updateByPrimaryKey(BalExternalResultEqual record);

	List<BalExternalResultEqual> selectList(BalExternalResultEqual requestBean);
}