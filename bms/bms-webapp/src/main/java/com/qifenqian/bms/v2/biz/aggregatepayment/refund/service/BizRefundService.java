package com.qifenqian.bms.v2.biz.aggregatepayment.refund.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdRefund;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.mapper.RefundMapper;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/24 17:14
 */
@Service
public class BizRefundService extends BaseService {
    @Autowired
    private RefundMapper refundMapper;

    public PageInfo<TdRefund> findRefundList(TdRefund refund) {
        List<TdRefund> refunds = refundMapper.getRefundList(refund);
        return new PageInfo<>(refunds);
    }
}
