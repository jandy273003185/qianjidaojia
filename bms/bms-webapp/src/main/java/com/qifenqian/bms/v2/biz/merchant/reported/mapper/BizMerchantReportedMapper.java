package com.qifenqian.bms.v2.biz.merchant.reported.mapper;

import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoSuixingPay;
import com.qifenqian.bms.v2.biz.merchant.reported.bean.TdMerchantReportInfoAO;
import com.qifenqian.bms.v2.biz.merchant.reported.bean.TdMerchantReportInfoVO;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface BizMerchantReportedMapper {

    List<TdMerchantReportInfoVO> getMerchantReportedList(TdMerchantReportInfoAO requestBean);

    TdMerchantDetailInfoSuixingPay suiXingMerchantDetailList(TdMerchantReportInfoAO requestBean);

    TdMerchantDetailInfo selectSuiXingPayMerchantDetailInfo(TdMerchantDetailInfo queryBean);
}
