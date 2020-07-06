package com.qifenqian.bms.bal.accountResult.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qifenqian.bms.bal.accountResult.bean.BalBatchResultStatistic;
import com.qifenqian.bms.bal.accountResult.bean.BalBatchResultStatisticExample;
import com.qifenqian.bms.bal.accountResult.bean.BaseChannel;
import com.qifenqian.bms.common.annotation.MapperScanCombinedmaster;

@MapperScanCombinedmaster 
public interface BalBatchResultStatisticMapper {
	
    int countByExample(BalBatchResultStatisticExample example);

    int deleteByExample(BalBatchResultStatisticExample example);

    int deleteByPrimaryKey(Integer statisticId);

    int insert(BalBatchResultStatistic record);

    int insertSelective(BalBatchResultStatistic record);

    List<BalBatchResultStatistic> selectByExample(BalBatchResultStatisticExample example);

    BalBatchResultStatistic selectByPrimaryKey(Integer statisticId);

    int updateByExampleSelective(@Param("record") BalBatchResultStatistic record, @Param("example") BalBatchResultStatisticExample example);

    int updateByExample(@Param("record") BalBatchResultStatistic record, @Param("example") BalBatchResultStatisticExample example);

    int updateByPrimaryKeySelective(BalBatchResultStatistic record);

    int updateByPrimaryKey(BalBatchResultStatistic record);

	List<BalBatchResultStatistic> selectList(BalBatchResultStatistic requestBean);

	List<BaseChannel> queryBalBaseChannel();
}