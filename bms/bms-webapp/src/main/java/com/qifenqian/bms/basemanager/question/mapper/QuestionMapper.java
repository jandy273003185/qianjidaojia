package com.qifenqian.bms.basemanager.question.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.question.bean.Question;

@MapperScan
public interface QuestionMapper {
	/**
	 * 问题列表
	 * 
	 * @param question
	 * @return
	 */
	List<Question> selectQuestions(Question question);

	/**
	 * 新增问题
	 * 
	 * @param question
	 */
	public void insertQuestion(Question question);

	/**
	 * 更新问题
	 * 
	 * @param question
	 */
	public void updateQuestion(Question question);

	/**
	 * 删除问题
	 * 
	 * @param question
	 */
	public void deleteQuestion(@Param("questNo") String questNo);
	
	/**
	 * 根据编号查询问题
	 * 
	 * @param question
	 */
	public String selectQuestionMaxId();

}
