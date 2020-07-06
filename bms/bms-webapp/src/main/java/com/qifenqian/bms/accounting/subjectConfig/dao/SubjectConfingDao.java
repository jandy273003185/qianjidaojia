package com.qifenqian.bms.accounting.subjectConfig.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.subjectConfig.bean.SubjectConfig;
import com.qifenqian.bms.accounting.subjectConfig.mapper.SubjectConfigMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class SubjectConfingDao {
	@Autowired
	private SubjectConfigMapper mapper;
	
	@Page
	public List<SubjectConfig> selectSubjectInfo(SubjectConfig subject) {
		return mapper.selectSubjectConfig(subject);
	}
}
