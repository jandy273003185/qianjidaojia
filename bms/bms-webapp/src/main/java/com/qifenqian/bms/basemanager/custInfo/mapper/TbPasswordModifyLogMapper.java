package com.qifenqian.bms.basemanager.custInfo.mapper;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.custInfo.bean.TbPasswordModifyLog;


@MapperScan
public interface TbPasswordModifyLogMapper {

    int deleteByPrimaryKey(String id);

    int insert(TbPasswordModifyLog record);

    int insertSelective(TbPasswordModifyLog record);


    TbPasswordModifyLog selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TbPasswordModifyLog record);

    int updateByPrimaryKey(TbPasswordModifyLog record);
}
