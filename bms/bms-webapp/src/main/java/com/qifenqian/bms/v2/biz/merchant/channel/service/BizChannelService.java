package com.qifenqian.bms.v2.biz.merchant.channel.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.merchant.channel.bean.ChannelBean;
import com.qifenqian.bms.merchant.channel.bean.ChannelDetailBean;
import com.qifenqian.bms.merchant.channel.bean.ChannelMerRegist;
import com.qifenqian.bms.merchant.channel.mapper.ChannelMapperReader;
import com.qifenqian.bms.merchant.channel.mapper.ChannelMapperWriter;
import com.qifenqian.bms.merchant.channel.service.ChannelService;
import com.qifenqian.bms.v2.biz.merchant.channel.bean.domain.ChannelAO;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description: 商户渠道
 * @date 2020/4/21 10:56
 */
@Service
public class BizChannelService extends BaseService {
    @Autowired
    private ChannelService channelService;
    @Autowired
    private ChannelMapperReader channelReader;
    @Autowired
    private ChannelMapperWriter channelWriter;
    @Autowired
    private TdCustInfoMapper custInfoMapper;

    public PageInfo<ChannelBean> findChannelList(ChannelBean queryBean) {
        List<ChannelBean> list = channelReader.selectChannels(queryBean);
        for (ChannelBean item : list) {
            TdCustInfo tc = custInfoMapper.selectById(item.getCustId());
            String custName = tc == null ? "无此商户,请管理处理数据" : tc.getCustName();
            String merchantCode = tc == null ? "" : tc.getMerchantCode();
            item.setCustName(custName);
            item.setMerchantCode(merchantCode);
            // 填充detail
            List<ChannelDetailBean> details = channelReader.selectChannelDetail(item.getCustId(), item.getMerchantChannelId());
            if (details != null) {
                String merchantChannelKey = details.size() > 0 ? details.get(0).getMerchantChannelKey() : "";
                item.setDetails(details);
                item.setMerchantChannelKey(merchantChannelKey);
            }
        }
        return new PageInfo<>(list);
    }

    public PageInfo<ChannelDetailBean> detail(ChannelDetailBean queryBean) {
        List<ChannelDetailBean> channelDetailBeans = channelReader.selectChannelDetails(queryBean);
        for (ChannelDetailBean item : channelDetailBeans) {
            TdCustInfo tc = custInfoMapper.selectById(item.getCustId());
            String custName = tc == null ? "无此商户,请管理处理数据" : tc.getCustName();
            item.setCustName(custName);
        }
        return new PageInfo<>(channelDetailBeans);
    }

    public ResultData add(ChannelAO channelAO) {
        //验证参数并设置custId
        paramValidation(channelAO);
        ChannelBean queryChannelBean = channelAO.getChannelBean();
        ChannelBean oldQueryChannelBean = channelAO.getOldChannelBean();
        if (null != oldQueryChannelBean && StringUtils.isNotBlank(oldQueryChannelBean.getCustId())) {
            oldQueryChannelBean = channelService.getChannel(oldQueryChannelBean.getCustId(),
                    oldQueryChannelBean.getChannelName().getCode(), oldQueryChannelBean.getMerchantChannelId());
        }
        boolean result = channelService.saveOrupdateChannel(queryChannelBean, oldQueryChannelBean);
        if (!result) {
            throw new BizException("保存或更新失败,请联系管理员!");
        }
        return ResultData.success();
    }

    public ResultData activate(ChannelBean queryChannelBean) {
        ChannelBean channelBean = channelService.getChannel(queryChannelBean.getCustId(),
                queryChannelBean.getChannelName().getCode(), queryChannelBean.getMerchantChannelId());

        boolean result = channelService.activeChannel(channelBean);
        if (!result) {
            throw new BizException("激活通道失败,请联系管理员!");
        }
        return ResultData.success();
    }

    public ResultData deactivate(ChannelBean queryChannelBean) {
        ChannelBean channelBean = channelService.getChannel(queryChannelBean.getCustId(),
                queryChannelBean.getChannelName().getCode(), queryChannelBean.getMerchantChannelId());

        boolean result = channelService.deactiveChannel(channelBean);
        if (!result) {
            throw new BizException("下线通道失败,请联系管理员!");
        }
        return ResultData.success();
    }

    private void paramValidation(ChannelAO channelAO) {
        ChannelBean channelBean = channelAO.getChannelBean();
        if (StringUtils.isBlank(channelBean.getMerchantCode()))
            throw new BizException("商户号不为能空");
        if (StringUtils.isBlank(channelBean.getChannelName().toString()))
            throw new BizException("请选择通道");
        if (StringUtils.isBlank(channelBean.getMerchantChannelId()))
            throw new BizException("商户编号(渠道)不能为空");
        /**
         * 验证时排除几个通道. 华兴没有key , 翼支付也没有key
         */
        final ChannelMerRegist channelName = channelBean.getChannelName();
        if (channelName != ChannelMerRegist.GHXB && channelName != ChannelMerRegist.BEST_PAY) {
            if (StringUtils.isBlank(channelBean.getMerchantChannelKey()))
                throw new BizException("商户KEY(渠道)不能为空");
        }

        //获取CustId
        TdCustInfo queryBean = new TdCustInfo();
        queryBean.setMerchantCode(channelBean.getMerchantCode());
        TdCustInfo tc = custInfoMapper.selectByBean(queryBean);
        if (tc == null)
            throw new BizException("无此商户号");
        channelBean.setCustId(tc.getCustId());
    }
}
