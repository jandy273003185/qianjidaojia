package com.qifenqian.bms.merchant.reports.service;

import com.qifenqian.bms.merchant.reports.bean.TdMerchantReportDetail;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;

/**
 * @description:
 * @author: LiBin
 * @date: 2019/12/25 0025 17:08
 */
public interface TdMerchantReportDetailService {

    TdMerchantReportDetail getDetailParams(String jsonString);

    ResultData addMerchantReportDetail(TdMerchantReportDetail tdMerchantReportDetail);

    ResultData queryMerchantReportDetailByChannel(TdMerchantReportDetail tdMerchantReportDetail);
}
