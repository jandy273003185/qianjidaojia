package com.qifenqian.bms.meeting.helper.pushrelation.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.meeting.helper.pushrelation.bean.PushRelation;

/**
 * @project sevenpay-bms-web
 * @fileName PushRelationMapper.java
 * @author huiquan.ma
 * @date 2015年12月17日
 * @memo 
 */
@MapperScan
public interface PushRelationMapper {

	PushRelation selectSingleById(String custId);

	List<PushRelation> selectAllRelation();
}


