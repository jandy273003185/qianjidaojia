package com.qifenqian.bms.myworkspace.overAudit.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.myworkspace.overAudit.bean.OverAuditBean;

@MapperScan
public interface OverAuditMapper {

	/**
	 * 已审核任务
	 * @return
	 */
	public List<OverAuditBean> getOverAudit(OverAuditBean bean);
	
	
}
