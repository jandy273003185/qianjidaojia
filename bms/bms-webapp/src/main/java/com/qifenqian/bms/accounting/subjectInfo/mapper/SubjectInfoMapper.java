package com.qifenqian.bms.accounting.subjectInfo.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.subjectInfo.bean.SubjectInfo;
import com.qifenqian.bms.common.annotation.MapperScanCore;
/***
 * 会计科目信息
 * @author pc
 *
 */
@MapperScanCore
public interface SubjectInfoMapper {

 	void createSubjectInfo(SubjectInfo subjectInfo);

	List<SubjectInfo> selectSubjectInfo(SubjectInfo subject);

	void updateSubjectInfo(SubjectInfo subjectInfo);
	
	List<SubjectInfo> list();
	
	String selectSubjectCode(String subjectCode);

	void deleteSubjectInfo(SubjectInfo subjectInfo);
}
