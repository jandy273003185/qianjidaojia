package com.qifenqian.bms.v2.biz.aggregatepayment.merchantprodrate.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.bean.TdMerchantProdRate;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.mapper.TdMerchantProdRateOperationMapper;
import com.qifenqian.bms.basemanager.aggregatepayment.merchant.mapper.TdMerchantProdRateSelectMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author LiBin
 * @Description:
 * @date 2020/4/24 16:42
 */
@Service
public class BizMerchantProdRateService extends BaseService {
    @Autowired
    private TdMerchantProdRateSelectMapper tdMerchantProdRateSelectMapper;
    @Autowired
    private TdMerchantProdRateOperationMapper tdMerchantProdRateOperationMapper;

    public PageInfo<TdMerchantProdRate> findMerchantProdRateList(TdMerchantProdRate merchantProdRate) {
        List<TdMerchantProdRate> merchantProdRates = tdMerchantProdRateSelectMapper.selectMerchantProdRate(merchantProdRate);
        return new PageInfo<>(merchantProdRates);
    }

    public ResultData add(TdMerchantProdRate merchantProdRate) {
        int result = tdMerchantProdRateOperationMapper.insertMerchantProdRate(merchantProdRate);
        if (result < 1) {
            ResultData.error("添加保存异常,请联系管理员!");
        }
        return ResultData.success();
    }

    public ResultData delete(TdMerchantProdRate merchantProdRate) {
        int result = tdMerchantProdRateOperationMapper.deleteMerchantProdRate(merchantProdRate);
        if (result < 1) {
            ResultData.error("删除异常,请联系管理员!");
        }
        return ResultData.success();
    }
}
