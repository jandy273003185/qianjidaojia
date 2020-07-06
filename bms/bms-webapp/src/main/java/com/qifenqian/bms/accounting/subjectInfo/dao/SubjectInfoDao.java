package com.qifenqian.bms.accounting.subjectInfo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.subjectInfo.bean.SubjectInfo;
import com.qifenqian.bms.accounting.subjectInfo.mapper.SubjectInfoMapper;
import com.qifenqian.bms.platform.web.page.Page;


@Repository
public class SubjectInfoDao {

	@Autowired
	private SubjectInfoMapper subjectInfoMapper;
	@Page
	public List<SubjectInfo> selectSubjectInfo(SubjectInfo subject) {
		return subjectInfoMapper.selectSubjectInfo(subject);
	}
	
	public List<SubjectInfo> list(){
		return subjectInfoMapper.list();
	}
}
