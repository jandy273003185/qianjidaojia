package com.qifenqian.bms.merchant.reported.dao;

import com.qifenqian.bms.merchant.reported.bean.TdMerchantReportInfo;
import com.qifenqian.bms.merchant.reported.mapper.TdMerchantReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TdMerchantReportDao {
    @Autowired
    private TdMerchantReportMapper tdMerchantReportMapper;


    public int updateTdMerchantReport(TdMerchantReportInfo tdInfo) {
        return this.tdMerchantReportMapper.updateTdMerchantReport(tdInfo);
    }

    public int insertTdMerchantReport(TdMerchantReportInfo info) {
        return this.tdMerchantReportMapper.insertTdMerchantReport(info);
    }
    public TdMerchantReportInfo selectByMerchantCode(String merchantCode){
        return this.tdMerchantReportMapper.selectByMerchantCode(merchantCode);
    }

    public TdMerchantReportInfo selectByMerchantInfo(TdMerchantReportInfo tdMerchantReportInfo){
        return this.tdMerchantReportMapper.selectByMerchantInfo(tdMerchantReportInfo);
    }
}
