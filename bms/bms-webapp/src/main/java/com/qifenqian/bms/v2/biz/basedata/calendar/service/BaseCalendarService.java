package com.qifenqian.bms.v2.biz.basedata.calendar.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.accounting.calendar.bean.CalendarVo;
import com.qifenqian.bms.accounting.calendar.mapper.CalendarVoMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/27 14:23
 */
@Service
public class BaseCalendarService extends BaseService {

    @Autowired
    private CalendarVoMapper calendarVoMapper;

    public PageInfo<CalendarVo> findCalendarList(CalendarVo queryBean) {
        List<CalendarVo> calendarVos = calendarVoMapper.selectCalendarList(queryBean);
        return new PageInfo<>(calendarVos);
    }

    public ResultData update(CalendarVo calendar) {
        int result = calendarVoMapper.updateCalendar(calendar);
        if (result < 1) {
            return ResultData.error("修改更新信息失败!");
        }
        return ResultData.success();
    }
}
