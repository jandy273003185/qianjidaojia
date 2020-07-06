package com.qifenqian.bms.v2.biz.basedata.calendar.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.accounting.calendar.bean.CalendarVo;
import com.qifenqian.bms.v2.biz.basedata.calendar.service.BaseCalendarService;
import com.qifenqian.bms.v2.biz.system.user.bean.domain.CurrentAccount;
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
 * @Description: 节假日管理
 * @date 2020/4/27 13:49
 */
@RestController
@Api(tags = "节假日管理")
public class BaseCalendarController extends BaseController {

    @Autowired
    private BaseCalendarService baseCalendarService;

    @PostMapping(value = "/calendar/list")
    @ApiOperation("节假日信息列表")
    public PageInfo<CalendarVo> list(PageQuery pageQuery, @RequestBody CalendarVo queryBean) {
        return this.baseCalendarService.findCalendarList(queryBean);
    }

    @PostMapping(value = "/calendar/update")
    @ApiOperation("节假日信息更新")
    public ResultData update(CurrentAccount currentAccount, @RequestBody CalendarVo calendarVo) {
        if (StringUtils.isBlank(calendarVo.getCalendarId())) {
            throw new BizException("修改对象为空!");
        }
        calendarVo.setLastupdateUser(currentAccount.getLoginId().toString());
        return this.baseCalendarService.update(calendarVo);
    }
}
