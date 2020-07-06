package com.qifenqian.bms.v2.biz.message.sms.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.sms.message.bean.BaseMessage;
import com.qifenqian.bms.v2.biz.message.sms.bean.domain.BaseMessageAO;
import com.qifenqian.bms.v2.biz.message.sms.service.BizSmsService;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiBin
 * @Description: 短信管理
 * @date 2020/4/27 17:08
 */
@RestController
@Api(tags = "短信管理")
public class BizSmsController extends BaseController {

    @Autowired
    private BizSmsService bizSmsService;

    @PostMapping(value = "/message/sms/list")
    @ApiOperation("短信信息列表")
    public PageInfo<BaseMessage> list(PageQuery pageQuery, @RequestBody BaseMessage baseMessage) {
        return bizSmsService.findSmsList(baseMessage);
    }

    @PostMapping(value = "/message/sms/delete")
    @ApiOperation("短信信息删除")
    public ResultData delete(@RequestBody BaseMessage baseMessage) {
        if (StringUtils.isBlank(baseMessage.getId())) {
            throw new BizException("ID为空!");
        }
        return bizSmsService.delete(baseMessage);
    }

    @PostMapping(value = "/message/sms/batch/delete")
    @ApiOperation("短信信息批量删除")
    public ResultData batchDelete(@RequestBody BaseMessageAO baseMessageAO) {
        if (CollectionUtils.isEmpty(baseMessageAO.getIds())) {
            throw new BizException("ID为空!");
        }
        return bizSmsService.batchDelete(baseMessageAO);
    }

    @PostMapping(value = "/message/sms/single/send")
    @ApiOperation("短信信息单发")
    public ResultData singleSend(@RequestBody BaseMessage sendBean) {
        if (StringUtils.isBlank(sendBean.getMobile())) {
            throw new BizException("手机号码为空");
        }
        if (StringUtils.isBlank(sendBean.getContent())) {
            throw new BizException("发送内容为空");
        }
        if (StringUtils.isBlank(sendBean.getCustName())) {
            throw new BizException("客户姓名为空");
        }
        if (StringUtils.isBlank(sendBean.getDateData())) {
            throw new BizException("数据日期为空");
        }
        return bizSmsService.singleSend(sendBean);
    }

    @PostMapping(value = "/message/sms/batch/send")
    @ApiOperation("短信信息单发")
    public ResultData batchSend(@RequestBody BaseMessageAO baseMessageAO) {
        if (StringUtils.isBlank(baseMessageAO.getSendId())) {
            throw new BizException("发送编号为空!");
        }
        return bizSmsService.batchSend(baseMessageAO);
    }

}
