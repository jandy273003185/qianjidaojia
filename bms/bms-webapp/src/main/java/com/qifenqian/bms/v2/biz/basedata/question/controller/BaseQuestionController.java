package com.qifenqian.bms.v2.biz.basedata.question.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.question.bean.Question;
import com.qifenqian.bms.v2.biz.basedata.question.service.BaseQuestionService;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiBin
 * @Description: 问题列表管理
 * @date 2020/4/27 14:38
 */
@RestController
@Api(tags = "问题列表管理")
public class BaseQuestionController extends BaseController {

    @Autowired
    private BaseQuestionService baseQuestionService;

    @PostMapping(value = "/question/list")
    @ApiOperation("问题列表")
    public PageInfo<Question> list(PageQuery pageQuery, @RequestBody Question question) {
        return this.baseQuestionService.findQuestionList(question);
    }

    @PostMapping(value = "/question/add")
    @ApiOperation("问题信息添加")
    public ResultData add(@RequestBody Question question) {
        question.setStatus("VALID");
        return this.baseQuestionService.add(question);
    }

    @PostMapping(value = "/question/update")
    @ApiOperation("问题信息更新 ")
    public ResultData update(@RequestBody Question question) {
        return this.baseQuestionService.update(question);
    }

    @PostMapping(value = "/question/delete")
    @ApiOperation("问题信息删除")
    public ResultData delete(@RequestBody Question question) {
        if (StringUtils.isBlank(question.getQuestNo())) {
            throw new BizException("问题编号不能为空!");
        }
        return this.baseQuestionService.delete(question);
    }
}
