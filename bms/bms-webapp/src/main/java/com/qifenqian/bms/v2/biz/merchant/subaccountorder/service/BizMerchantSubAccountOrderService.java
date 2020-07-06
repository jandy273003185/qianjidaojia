package com.qifenqian.bms.v2.biz.merchant.subaccountorder.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.merchant.subAccountOrder.bean.MerchantSubAccouontOrderBean;
import com.qifenqian.bms.merchant.subAccountOrder.mapper.MerchantSubAccountOrderMapper;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description: 分账管理
 * @date 2020/4/21 18:07
 */
@Service
public class BizMerchantSubAccountOrderService extends BaseService {

    @Autowired
    private MerchantSubAccountOrderMapper merchantSubAccountOrderMapper;

    public PageInfo<MerchantSubAccouontOrderBean> findMerchantSubAccountOrderList(MerchantSubAccouontOrderBean merchantSubAccouontOrderBean) {
        List<MerchantSubAccouontOrderBean> merchantSubAccountOrderBeans = merchantSubAccountOrderMapper.selectSubAccountOrderList(merchantSubAccouontOrderBean);
        return new PageInfo<>(merchantSubAccountOrderBeans);
    }
}
