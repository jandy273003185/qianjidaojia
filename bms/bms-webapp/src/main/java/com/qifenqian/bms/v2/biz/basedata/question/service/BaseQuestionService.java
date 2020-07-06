package com.qifenqian.bms.v2.biz.basedata.question.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.question.bean.Question;
import com.qifenqian.bms.basemanager.question.mapper.QuestionMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/27 14:59
 */
@Service
public class BaseQuestionService extends BaseService {
    @Autowired
    private QuestionMapper questionMapper;

    public PageInfo<Question> findQuestionList(Question question) {
        List<Question> questions = this.questionMapper.selectQuestions(question);
        return new PageInfo<>(questions);
    }

    public ResultData add(Question question) {
        try {
            questionMapper.insertQuestion(question);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("添加保存问题信息异常!" + e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData update(Question question) {
        try {
            questionMapper.updateQuestion(question);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("修改问题信息异常!" + e.getMessage());
        }
        return ResultData.success();
    }

    public ResultData delete(Question question) {
        try {
            questionMapper.deleteQuestion(question.getQuestNo());
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException("删除问题信息异常!" + e.getMessage());
        }
        return ResultData.success();
    }
}
