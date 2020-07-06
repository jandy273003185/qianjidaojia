package com.qifenqian.bms.myworkspace;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;


@MapperScan
public interface ActViewUrlMapper {

   public ActViewUrl selectByApplicationName(@Param("applicationName")  String applicationName);

   
}
