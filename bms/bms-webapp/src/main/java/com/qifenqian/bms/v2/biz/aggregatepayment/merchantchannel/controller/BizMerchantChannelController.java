package com.qifenqian.bms.v2.biz.aggregatepayment.merchantchannel.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdMerchantChannel;
import com.qifenqian.bms.v2.biz.aggregatepayment.merchantchannel.service.BizMerchantChannelService;
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
 * @Description: 渠道金额限制
 * @date 2020/4/24 14:15
 */
@RestController
@Api(tags = "渠道金额限制管理")
public class BizMerchantChannelController extends BaseController {

    @Autowired
    private BizMerchantChannelService bizMerchantChannelService;

    @PostMapping(value = "/merchantChannel/list")
    @ApiOperation(value = "渠道金额限制列表")
    public PageInfo<TdMerchantChannel> list(PageQuery pageQuery,@RequestBody TdMerchantChannel merchantChannel) {
        return bizMerchantChannelService.findMerchantChannelList(merchantChannel);
    }

    @PostMapping(value = "/merchantChannel/add")
    @ApiOperation(value = "渠道金额添加")
    public ResultData add(@RequestBody TdMerchantChannel merchantChannel) {
        verfiyParam(merchantChannel);
        return bizMerchantChannelService.add(merchantChannel);
    }
    @PostMapping(value = "/merchantChannel/update")
    @ApiOperation(value = "渠道金额更新")
    public ResultData update(@RequestBody TdMerchantChannel merchantChannel) {
        verfiyParam(merchantChannel);
        return bizMerchantChannelService.update(merchantChannel);
    }
    @PostMapping(value = "/merchantChannel/delete")
    @ApiOperation(value = "渠道金额删除")
    public ResultData delete(@RequestBody TdMerchantChannel merchantChannel) {
        if (StringUtils.isBlank(merchantChannel.getMchId())) {
            throw new BizException("商户编号不能为空!");
        }
        if (StringUtils.isBlank(merchantChannel.getChanel())) {
            throw new BizException("渠道不能为空!");
        }
        return bizMerchantChannelService.delete(merchantChannel);
    }

    public void verfiyParam(TdMerchantChannel bean) {
        if (StringUtils.isBlank(bean.getMchId())) {
            throw new BizException("商户编号不能为空!");
        }
        if (StringUtils.isBlank(bean.getChanel())) {
            throw new BizException("渠道不能为空!");
        }
        if (StringUtils.isBlank(bean.getChanelStatus())) {
            throw new BizException("渠道状态不能为空!");
        }
        if (bean.getLimitPerAmt() == null) {
            throw new BizException("交易限额不能为空!");
        }
        if (bean.getLimitTotAmt() == null) {
            throw new BizException("交易总限额不能为空!");
        }
    }
}
