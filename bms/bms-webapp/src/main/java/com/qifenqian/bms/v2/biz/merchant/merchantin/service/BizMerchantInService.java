package com.qifenqian.bms.v2.biz.merchant.merchantin.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.merchantincontrol.bean.MerchantInControl;
import com.qifenqian.bms.basemanager.merchantincontrol.mapper.TdMerchantInControlMapper;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description: 商户网关维护
 * @date 2020/4/20 14:38
 */
@Service
public class BizMerchantInService extends BaseService {

    @Autowired
    private TdMerchantInControlMapper merchantInControlMapper;

    public PageInfo<MerchantInControl> findMerchantInList(MerchantInControl queryBean) {
        List<MerchantInControl> merchantInControls = merchantInControlMapper.selectMerchantInControlList(queryBean);
        return new PageInfo<>(merchantInControls);
    }
}
