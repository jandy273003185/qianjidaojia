package com.qifenqian.bms.v2.biz.aggregatepayment.merchantchannel.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdMerchantChannel;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.mapper.TdMerchantChannelOperationMapper;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.mapper.TdMerchantChannelSelectMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/24 14:22
 */
@Service
public class BizMerchantChannelService extends BaseService {

    @Autowired
    private TdMerchantChannelOperationMapper tdMerchantChannelOperationMapper;
    @Autowired
    private TdMerchantChannelSelectMapper merchantChannelSelectMapper;

    public PageInfo<TdMerchantChannel> findMerchantChannelList(TdMerchantChannel merchantChannel) {
        List<TdMerchantChannel> merchantChannels = merchantChannelSelectMapper.selectMerchantChannel(merchantChannel);
        return new PageInfo<>(merchantChannels);
    }

    public ResultData add(TdMerchantChannel merchantChannel) {
        int result = tdMerchantChannelOperationMapper.insertMerchantChannel(merchantChannel);
        if (result < 1) {
            ResultData.error("添加保存异常,请联系管理员!");
        }
        return ResultData.success();
    }

    public ResultData update(TdMerchantChannel merchantChannel) {
        int result = tdMerchantChannelOperationMapper.updateMerchantChannel(merchantChannel);
        if (result < 1) {
            ResultData.error("更新异常,请联系管理员!");
        }
        return ResultData.success();
    }

    public ResultData delete(TdMerchantChannel merchantChannel) {
        int result = tdMerchantChannelOperationMapper.deleteMerchantChannel(merchantChannel);
        if (result < 1) {
            ResultData.error("删除异常,请联系管理员!");
        }
        return ResultData.success();
    }
}
