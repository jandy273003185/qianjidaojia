package com.qifenqian.bms.bal.accountResult.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qifenqian.bms.bal.accountResult.bean.BalInternalResultException;
import com.qifenqian.bms.bal.accountResult.bean.BalInternalResultExceptionExample;
import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;

@MapperScanCombinedmaster
public interface BalInternalResultExceptionMapper {
    int countByExample(BalInternalResultExceptionExample example);

    int deleteByExample(BalInternalResultExceptionExample example);

    int deleteByPrimaryKey(Integer exceptionId);

    int insert(BalInternalResultException record);

    int insertSelective(BalInternalResultException record);

    List<BalInternalResultException> selectByExample(BalInternalResultExceptionExample example);

    BalInternalResultException selectByPrimaryKey(Integer exceptionId);

    int updateByExampleSelective(@Param("record") BalInternalResultException record, @Param("example") BalInternalResultExceptionExample example);

    int updateByExample(@Param("record") BalInternalResultException record, @Param("example") BalInternalResultExceptionExample example);

    int updateByPrimaryKeySelective(BalInternalResultException record);

    int updateByPrimaryKey(BalInternalResultException record);

	List<BalInternalResultException> selectList(
			BalInternalResultException balInternalResultException);
}