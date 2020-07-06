package com.qifenqian.bms.merchant.reports.service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoAllinPay;
import com.qifenqian.bms.merchant.reports.bean.TdMerchantReportDetail;
import com.qifenqian.bms.merchant.reports.constants.ChannelTypeConstants;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @description:
 * @author: LiBin
 * @date: 2019/12/25 0025 17:08
 */
@Service("tdMerchantReportDetailService" + ChannelTypeConstants.ALLIN_PAY)
public class TdMerchantDetailInfoAllinPayService implements TdMerchantReportDetailService {

    @Override
    public TdMerchantReportDetail getDetailParams(String jsonString) {
        return JSONObject.parseObject(jsonString, TdMerchantDetailInfoAllinPay.class);
    }

    @Override
    public ResultData addMerchantReportDetail(TdMerchantReportDetail tdMerchantReportDetail) {
        TdMerchantDetailInfoAllinPay tdMerchantDetailInfoAllinPay = (TdMerchantDetailInfoAllinPay) tdMerchantReportDetail;

        return ResultData.success();
    }

    /**
     * TODO 转换查询条件,查询列表
     *
     * @param tdMerchantReportDetail
     * @return
     */
    @Override
    public ResultData queryMerchantReportDetailByChannel(TdMerchantReportDetail tdMerchantReportDetail) {
        TdMerchantDetailInfoAllinPay tdMerchantDetailInfoAlipay = (TdMerchantDetailInfoAllinPay) tdMerchantReportDetail;
        List<TdMerchantDetailInfoAllinPay> tdMerchantDetailInfoAllinPays = new ArrayList<>();
        return ResultData.success(tdMerchantDetailInfoAllinPays);
    }
}
