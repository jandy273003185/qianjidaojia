package com.qifenqian.bms.basemanager.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.merchant.bean.BmsProtocolContent;
import com.qifenqian.bms.basemanager.merchant.bean.BmsProtocolContentExample;

@MapperScan
public interface BmsProtocolContentMapper {
    int countByExample(BmsProtocolContentExample example);

    int deleteByExample(BmsProtocolContentExample example);

    int deleteByPrimaryKey(String id);

    int insert(BmsProtocolContent record);

    int insertSelective(BmsProtocolContent record);

    List<BmsProtocolContent> selectByExample(BmsProtocolContentExample example);

    BmsProtocolContent selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") BmsProtocolContent record, @Param("example") BmsProtocolContentExample example);

    int updateByExample(@Param("record") BmsProtocolContent record, @Param("example") BmsProtocolContentExample example);

    int updateByPrimaryKeySelective(BmsProtocolContent record);

    int updateByPrimaryKey(BmsProtocolContent record);
    
    List<BmsProtocolContent> selectContentByCustId(@Param("custId") String custId);
}