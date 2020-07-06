package com.qifenqian.bms.basemanager.question;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.question.bean.Question;
import com.qifenqian.bms.basemanager.question.service.QuestionService;

@Controller
@RequestMapping(QuestionPath.BASE)
public class QuestionController {

	private Logger logger = LoggerFactory.getLogger(QuestionController.class);

	@Autowired
	private QuestionService questionService;

	/**
	 * 问题列表查询
	 * 
	 * @param requestRole
	 * @return
	 */
	@RequestMapping(QuestionPath.LIST)
	public ModelAndView list(Question requestQuestion) {

		ModelAndView mv = new ModelAndView(QuestionPath.BASE + QuestionPath.LIST);
		// 用户列表
		String questNo=requestQuestion.getQuestNo();
		String questContent=requestQuestion.getQuestContent();
		mv.addObject("questionList", questionService.selectQuestions(requestQuestion));
		mv.addObject("questNo", questNo);
		mv.addObject("questContent", questContent);
		return mv;
	}

	/**
	 * 增加
	 * 
	 * @param city
	 * @return
	 */
	@RequestMapping(QuestionPath.ADD)
	@ResponseBody
	public String add(Question question) {
		JSONObject js = new JSONObject();
		try {
			logger.info("请求保存question：[{}]", JSONObject.toJSONString(question, true));
			
			/*String questNo = BusinessUtils.getQuestionId(questionService.selectQuestionMaxId());
				/*Question question1=new Question();
				question.setQuestNo(questNo);
				question1.setStatus("VALID");
				question.setStatus(question1.getStatus());*/
				questionService.addQuestion(question);
				js.put("result", "SUCCESS");

			

		} catch (Exception e) {
			logger.error("请求增加问题异常", e);
			js.put("result", "FAIL");
			js.put("message", e.getMessage());
		}
		return js.toJSONString();

	}

	/**
	 * 删除
	 * 
	 * @param city
	 * @return
	 */
	@RequestMapping(QuestionPath.DELETE)
	@ResponseBody
	public String delete(String questNo) {

		JSONObject js = new JSONObject();

		try {
			logger.info("请求保存question：[{}]", questNo);
			questionService.deleteQuestion(questNo);
			js.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("请求删除问题异常", e);
			js.put("result", "FAIL");
			js.put("message", e.getMessage());
		}
		return js.toJSONString();

	}

	/**
	 * 更新
	 * 
	 * @param city
	 * @return
	 */
	@RequestMapping(QuestionPath.EDIT)
	@ResponseBody
	public String update(Question question) {
		JSONObject js = new JSONObject();
		try {
			logger.info("请求修改question：[{}]", JSONObject.toJSONString(question, true));
			questionService.updateQuestion(question);
			js.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("请求修改问题异常", e);
			js.put("result", "FAIL");
			js.put("message", e.getMessage());
		}
		return js.toJSONString();

	}

}
