package com.qifenqian.bms.bal.accountResult.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qifenqian.bms.bal.accountResult.bean.BalExternalResultException;
import com.qifenqian.bms.bal.accountResult.bean.BalExternalResultExceptionExample;
import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;

@MapperScanCombinedmaster
public interface BalExternalResultExceptionMapper {
    int countByExample(BalExternalResultExceptionExample example);

    int deleteByExample(BalExternalResultExceptionExample example);

    int deleteByPrimaryKey(Integer exceptionId);

    int insert(BalExternalResultException record);

    int insertSelective(BalExternalResultException record);

    List<BalExternalResultException> selectByExample(BalExternalResultExceptionExample example);

    BalExternalResultException selectByPrimaryKey(Integer exceptionId);

    int updateByExampleSelective(@Param("record") BalExternalResultException record, @Param("example") BalExternalResultExceptionExample example);

    int updateByExample(@Param("record") BalExternalResultException record, @Param("example") BalExternalResultExceptionExample example);

    int updateByPrimaryKeySelective(BalExternalResultException record);

    int updateByPrimaryKey(BalExternalResultException record);

	List<BalExternalResultException> selectList(
			BalExternalResultException requestBean);
}