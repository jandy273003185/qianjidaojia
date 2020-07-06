package com.qifenqian.bms.basemanager.question.dao;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.question.bean.Question;
import com.qifenqian.bms.basemanager.question.mapper.QuestionMapper;
import com.qifenqian.bms.platform.web.page.Page;

/**
 * dao层，一般分页需要
 * 
 * @project sevenpay-bms-web
 * @fileName CityDAO.java
 * @author Dayet
 * @date 2015年5月21日
 * @memo 
 */
@Repository
public class QuestionDao {

	@Autowired
	private QuestionMapper questionMapper;
	
	/**
	 * 分页查询问题列表
	 * @return
	 */
	@Page
	public List<Question> selectQuestions(Question question) {
		return questionMapper.selectQuestions(question);
	}
	
	/**
	 * 问题修改
	 * 
	 * @param question
	 * @return
	 */
	public void updateQuestionById(Question question) {
		if (null == question) {
			throw new IllegalArgumentException("问题对象为空");
		}
		if (StringUtils.isEmpty(question.getQuestNo())) {
			throw new IllegalArgumentException("问题编号为空");
		}
		if (StringUtils.isEmpty(question.getQuestContent())) {
			throw new IllegalArgumentException("问题内容为空");
		}
		questionMapper.updateQuestion(question);
	}

	/**
	 * 问题删除
	 * 
	 * @param quesNo
	 * @return
	 */
	public void deleteQuestionById(String questNo) {

		if (StringUtils.isEmpty(questNo)) {
			throw new IllegalArgumentException("问题编号为空");
		}
		questionMapper.deleteQuestion(questNo);
	}

	/**
	 * 增加问题
	 * @param city
	 */
	public void addQuestion(Question question){
		questionMapper.insertQuestion(question);
	}
	
}
