package com.qifenqian.bms.v2.biz.aggregatepayment.productchannel.controller;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.aggregatepayment.agent.bean.PayChannelBean;
import com.qifenqian.bms.v2.biz.aggregatepayment.productchannel.service.BizPayChannelService;
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

@RestController
@Api(tags = "支付渠道管理")
public class BizPayChannelController extends BaseController {
    @Autowired
    private BizPayChannelService bizPayChannelService;

    @PostMapping("/aggregatepayment/productchannel/paychannel/list")
    @ApiOperation(value = "支付渠道列表")
    public PageInfo<PayChannelBean> list(PageQuery query, @RequestBody PayChannelBean queryBean) {
        return bizPayChannelService.list(queryBean);
    }

    @PostMapping("/aggregatepayment/productchannel/paychannel/add")
    @ApiOperation(value = "支付渠道新增")
    public ResultData add(@RequestBody PayChannelBean payChannelBean) {
        paramValidation(payChannelBean);
        return bizPayChannelService.add(payChannelBean);
    }

    @PostMapping("/aggregatepayment/productchannel/paychannel/update")
    @ApiOperation(value = "支付渠道修改")
    public ResultData update(@RequestBody PayChannelBean payChannelBean) {
        paramValidation(payChannelBean);
        return bizPayChannelService.update(payChannelBean);
    }

    @PostMapping("/aggregatepayment/productchannel/paychannel/delete")
    @ApiOperation(value = "支付渠道删除")
    public ResultData delete(@RequestBody PayChannelBean payChannelBean) {
        if (StringUtils.isBlank(payChannelBean.getPayChannelCode()))
            throw new BizException("支付渠道编码不能为空！");
        return bizPayChannelService.delete(payChannelBean);
    }

    private void paramValidation(PayChannelBean payChannelBean) {
        if (StringUtils.isBlank(payChannelBean.getPayChannelCode()))
            throw new BizException("支付渠道编号不能为空！");
        if (StringUtils.isBlank(payChannelBean.getPayChannelName()))
            throw new BizException("支付渠道名称不能为空！");
        if (StringUtils.isBlank(payChannelBean.getPayChannelMemo()))
            throw new BizException("支付渠道说明不能为空！");
        if (null == payChannelBean.getPayChannelRate())
            throw new BizException("支付渠道成本费率不能为空！");
        if (StringUtils.isBlank(payChannelBean.getSupllyOrg()))
            throw new BizException("渠道提供机构不能为空！");
        if (StringUtils.isBlank(payChannelBean.getOurBankAcctNo()))
            throw new BizException("我方结算账户号不能为空！");
        if (StringUtils.isBlank(payChannelBean.getOurBankAcctName()))
            throw new BizException("我方结算账户名称不能为空！");
    }

}
