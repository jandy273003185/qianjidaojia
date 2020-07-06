package com.qifenqian.bms.basemanager.question.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.question.bean.Question;
import com.qifenqian.bms.basemanager.question.dao.QuestionDao;
import com.qifenqian.bms.basemanager.question.mapper.QuestionMapper;
/**
 * 问题管理
 * @author pc
 *
 */
@Service
public class QuestionService {

	@Autowired
	private QuestionMapper questionMapper;
	
	@Autowired
	private QuestionDao questionDAO;
	
	/**
	 * 查询问题列表
	 * @return
	 */
	public List<Question> selectQuestions(Question question){
		return questionDAO.selectQuestions(question);
	}
	
	/**
	 * 增加问题
	 * @param question
	 */
	public void addQuestion(Question question){
		question.setStatus("VALID");
		questionMapper.insertQuestion(question);
	}
	
	/**
	 * 更新问题
	 * @param question
	 */
	public void updateQuestion(Question question){
		questionMapper.updateQuestion(question);
	}
	
	/**
	 * 删除问题
	 * @param questNo
	 */
	public void deleteQuestion(String questNo){
		questionMapper.deleteQuestion(questNo);
	}
	
	/**
	 * 根据编号查询问题
	 * @param questNo
	 * @return
	 */
	public String selectQuestionMaxId(){
		
		return questionMapper.selectQuestionMaxId();
		
	}

}
