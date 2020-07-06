package com.qifenqian.bms.workflow.group.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.workflow.group.bean.ActIdGroup;

@MapperScan
public interface ActIdGroupMapper {


    int deleteByPrimaryKey(String id);

    int insert(ActIdGroup record);

    int insertSelective(ActIdGroup record);

    int updateByPrimaryKeySelective(ActIdGroup record);

    int updateByPrimaryKey(ActIdGroup record);
    
    List<ActIdGroup> selectListActIdGroup(ActIdGroup record);
}