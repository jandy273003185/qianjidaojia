package com.qifenqian.bms.accounting.subjectConfig.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.subjectConfig.bean.SubjectConfig;
import com.qifenqian.bms.accounting.subjectConfig.dao.SubjectConfingDao;
import com.qifenqian.bms.accounting.subjectConfig.mapper.SubjectConfigMapper;

@Service
public class SubjectConfigSerivce {
	
	@Autowired
	private SubjectConfigMapper mapper;
	
	@Autowired
	private SubjectConfingDao dao;
	
	public void insertSubjectConfig(SubjectConfig subjectConfig){
		mapper.insertSubjectConfig(subjectConfig);
	}
	
	public void updateSubjectConfig(SubjectConfig subjectConfig){
		mapper.updateSubjectConfig(subjectConfig);
	}

	public List<SubjectConfig> selectSubjectConfig(SubjectConfig subjectConfig){
		return dao.selectSubjectInfo(subjectConfig);
	}
	
	public void delSubjectConfig(SubjectConfig subjectConfig){
		mapper.deleteSubjectConfig(subjectConfig);
	}
}
