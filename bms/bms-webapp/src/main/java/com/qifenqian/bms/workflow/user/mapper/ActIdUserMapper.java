package com.qifenqian.bms.workflow.user.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.workflow.user.bean.ActIdUser;

@MapperScan
public interface ActIdUserMapper {


    int deleteByPrimaryKey(String id);

    int insert(ActIdUser record);

    int insertSelective(ActIdUser record);

    ActIdUser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ActIdUser record);

    int updateByPrimaryKey(ActIdUser record);
    
    List<ActIdUser> selectListActUser(ActIdUser record);
}