package com.qifenqian.bms.v2.biz.aggregatepayment.merchanttrade.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.MerchantTradeQueryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.OrderSummaryBean;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.mapper.MerchantTradeMapper;
import com.qifenqian.bms.v2.biz.aggregatepayment.merchanttrade.bean.vo.OrderSummaryTotal;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/23 17:34
 */
@Service
public class BizMerchantTradeService extends BaseService {
    @Autowired
    private MerchantTradeMapper merchantTradeMapper;

    public PageInfo<OrderSummaryBean> findList(MerchantTradeQueryBean queryBean) {
        List<OrderSummaryBean> list;
        if ("refund".equalsIgnoreCase(queryBean.getTradeType())) {
            list = merchantTradeMapper.getMerchantRefundList(queryBean);
        } else {
            list = merchantTradeMapper.getMerchantTradeList(queryBean);
        }
        return new PageInfo<>(list);
    }

    public OrderSummaryTotal total(MerchantTradeQueryBean queryBean) {
        List<OrderSummaryBean> list;
        if ("refund".equalsIgnoreCase(queryBean.getTradeType())) {
            list = merchantTradeMapper.getMerchantRefundList(queryBean);
        } else {
            list = merchantTradeMapper.getMerchantTradeList(queryBean);
        }
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        BigDecimal allCount = new BigDecimal(0.00);
        BigDecimal allSum = new BigDecimal(0.00);
        BigDecimal allSettle = new BigDecimal(0.00);
        for (OrderSummaryBean orderSummaryBean : list) {
            if (orderSummaryBean.getCountTrade() != null
                    && !"".equals(orderSummaryBean.getCountTrade())) {
                allCount = allCount.add(new BigDecimal(orderSummaryBean.getCountTrade()));
            }
            if (orderSummaryBean.getSumTrade() != null && !"".equals(orderSummaryBean.getSumTrade())) {
                allSum = allSum.add(new BigDecimal(orderSummaryBean.getSumTrade()));
            }
            if (orderSummaryBean.getSettleAmt() != null && !"".equals(orderSummaryBean.getSettleAmt())) {
                allSettle = allSettle.add(new BigDecimal(orderSummaryBean.getSettleAmt()));
            }
        }
        OrderSummaryTotal orderSummaryTotal = new OrderSummaryTotal();
        orderSummaryTotal.setCount(allCount);
        orderSummaryTotal.setSettle(allSettle);
        orderSummaryTotal.setSum(allSum);
        return orderSummaryTotal;
    }
}
