package com.qifenqian.bms.v2.biz.merchant.channel.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.merchant.channel.bean.ChannelBean;
import com.qifenqian.bms.merchant.channel.bean.ChannelDetailBean;
import com.qifenqian.bms.merchant.channel.service.ChannelService;
import com.qifenqian.bms.v2.biz.merchant.channel.bean.domain.ChannelAO;
import com.qifenqian.bms.v2.biz.merchant.channel.service.BizChannelService;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiBin
 * @Description: 商户渠道
 * @date 2020/4/21 10:54
 */
@RestController
@Api(tags = "商户渠道接口管理")
public class BizChannelController extends BaseController {
    @Autowired
    private ChannelService channelService;
    @Autowired
    private BizChannelService bizChannelService;

    @PostMapping(value = "/channel/list")
    @ApiOperation(value = "渠道列表")
    public PageInfo<ChannelBean> list(PageQuery pageQuery, @RequestBody ChannelBean queryBean) {
        return this.bizChannelService.findChannelList(queryBean);
    }

    @PostMapping(value = "/channel/detail")
    @ApiOperation(value = "渠道详情列表")
    public PageInfo<ChannelDetailBean> detail(PageQuery pageQuery, @RequestBody ChannelDetailBean queryBean) {
        return this.bizChannelService.detail(queryBean);
    }

    @PostMapping(value = "/channel/edit")
    @ApiOperation(value = "编辑渠道列表")
    public ChannelBean edit(@RequestBody ChannelBean queryBean) {
        return channelService.getChannel(queryBean.getCustId(), queryBean.getChannelName().toString(), queryBean.getMerchantChannelId());
    }

    @PostMapping(value = "/channel/add")
    @ApiOperation(value = "编辑渠道保存或者更新")
    public ResultData add(@RequestBody ChannelAO channelAO) {
        return bizChannelService.add(channelAO);
    }

    @PostMapping(value = "/channel/activate")
    @ApiOperation(value = "编辑渠道激活")
    public ResultData activate(@RequestBody ChannelBean queryBean) {
        return bizChannelService.activate(queryBean);
    }

    @PostMapping(value = "/channel/deactivate")
    @ApiOperation(value = "编辑渠道下线")
    public ResultData deactivate(@RequestBody ChannelBean queryBean) {
        return bizChannelService.deactivate(queryBean);
    }


}
