package com.qifenqian.bms.myworkspace.applicationForm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.myworkspace.applicationForm.bean.ActHiProcinst;
import com.qifenqian.bms.myworkspace.applicationForm.bean.ApplicationFormBean;

@MapperScan
public interface ApplicationFormMapper {

	/**
	 * 我的申请单
	 * @param userId
	 * @return
	 */
	public List<ApplicationFormBean> getApplicationForm(ApplicationFormBean bean);
	
	
	public ActHiProcinst selectActHiProcinstById(@Param("id") String id);
}
