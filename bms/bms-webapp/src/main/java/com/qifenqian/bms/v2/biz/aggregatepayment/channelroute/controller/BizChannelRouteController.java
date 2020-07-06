package com.qifenqian.bms.v2.biz.aggregatepayment.channelroute.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.channelroute.bean.ChannelRouteBean;
import com.qifenqian.bms.v2.biz.aggregatepayment.channelroute.service.BizChannelRouteService;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "渠道路由管理")
public class BizChannelRouteController extends BaseController {
    @Autowired
    private BizChannelRouteService bizChannelRouteService;

    @PostMapping("/aggregatepayment/channelroute/list")
    @ApiOperation(value = "渠道路由列表")
    public PageInfo<ChannelRouteBean> list(PageQuery query, @RequestBody ChannelRouteBean queryBean) {
        return bizChannelRouteService.list(queryBean);
    }

    @PostMapping("/aggregatepayment/channelroute/delete")
    @ApiOperation(value = "渠道路由删除")
    public ResultData delete(@RequestBody ChannelRouteBean channelRouteBean) {
        if (null == channelRouteBean.getId())
            throw new BizException("id不能为空！");
        return bizChannelRouteService.delete(channelRouteBean);
    }

    @PostMapping("/aggregatepayment/channelroute/add")
    @ApiOperation(value = "渠道路由新增")
    public ResultData add(@RequestBody ChannelRouteBean channelRouteBean) {
        if (null == channelRouteBean)
            throw new BizException("渠道路由不能为空！");
        return bizChannelRouteService.add(channelRouteBean);
    }

    @PostMapping("/aggregatepayment/channelroute/update")
    @ApiOperation(value = "渠道路由修改")
    public ResultData update(@RequestBody ChannelRouteBean channelRouteBean) {
        return bizChannelRouteService.update(channelRouteBean);
    }

}
