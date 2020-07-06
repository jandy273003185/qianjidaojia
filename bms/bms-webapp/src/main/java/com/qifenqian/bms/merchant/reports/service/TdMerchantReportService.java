package com.qifenqian.bms.merchant.reports.service;

import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantReportInfo;
import com.qifenqian.bms.merchant.reported.dao.TdMerchantReportDao;
import com.qifenqian.bms.merchant.reports.bean.TdMerchantReportDetail;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @description:
 * @author: LiBin
 * @date: 2019/12/25 0025 17:12
 */
@Service
public class TdMerchantReportService {

    @Autowired
    private TdMerchantReportDao tdMerchantReportDao;
    @Autowired
    private Map<String, TdMerchantReportDetailService> tdMerchantReportDetailServiceMap;

    /**
     * 获取调用的service
     *
     * @param channel
     * @return
     */
    public TdMerchantReportDetailService getActiveChannel(String channel) {
        channel = "tdMerchantReportDetailService" + channel;
        if (tdMerchantReportDetailServiceMap.containsKey(channel)) {
            return tdMerchantReportDetailServiceMap.get(channel);
        }
        return null;
    }

    /**
     * 处理界面参数
     *
     * @param channel
     * @param jsonString
     * @return
     */
    public TdMerchantReportDetail getDetailParams(String channel, String jsonString) {
        TdMerchantReportDetailService tdMerchantReportDetailService = getActiveChannel(channel);
        if (tdMerchantReportDetailService == null) {
            return null;
        }
        return tdMerchantReportDetailService.getDetailParams(jsonString);
    }

    /**
     * 渠道报备入库
     *
     * @param channel
     * @param tdMerchantReportDetail
     * @return
     */
    public ResultData addMerchantReportDetailByChannel(String channel, TdMerchantReportDetail tdMerchantReportDetail) {
        TdMerchantReportDetailService tdMerchantReportDetailService = getActiveChannel(channel);
        if (tdMerchantReportDetailService == null) {
            return ResultData.error("请确认渠道信息是否正确！");
        }
        return tdMerchantReportDetailService.addMerchantReportDetail(tdMerchantReportDetail);
    }

    /**
     * 渠道报备列表查询
     *
     * @param channel
     * @param tdMerchantReportDetail
     * @return
     */
    public ResultData queryMerchantReportDetailByChannel(String channel, TdMerchantReportDetail tdMerchantReportDetail) {
        TdMerchantReportDetailService tdMerchantReportDetailService = getActiveChannel(channel);
        if (tdMerchantReportDetailService == null) {
            return ResultData.error("请确认渠道信息是否正确！");
        }
        return tdMerchantReportDetailService.queryMerchantReportDetailByChannel(tdMerchantReportDetail);
    }

    /**
     * 报备添加
     *
     * @param merchantReport
     * @param jsonReportDetailInfo
     * @return
     */
    public ResultData addReport(TdMerchantReportInfo merchantReport, String jsonReportDetailInfo) {
        /**
         * 查询数据的reportStatus判断当前数据是否已报备或者审核失败
         */

//      TdMerchantReportInfo currentTdMerchantReportInfo = tdMerchantReportDao.selectByMerchantCode(merchantReport.getMerchantCode());
        TdMerchantReportInfo currentTdMerchantReportInfo = tdMerchantReportDao.selectByMerchantInfo(merchantReport);
        if (currentTdMerchantReportInfo != null) {
            String reportStatus = currentTdMerchantReportInfo.getReportStatus();
            if ("Y".equalsIgnoreCase(reportStatus) || "O".equalsIgnoreCase(reportStatus)) {
                return ResultData.error("商户已经报备该渠道，请勿重新提交");
            }
        }
        /**
         * 如果没有
         */
        merchantReport.setId(GenSN.getSN());
        merchantReport.setPatchNo(GenSN.getSN());
        merchantReport.setReportStatus("N");
        merchantReport.setDetailStatus("99");
        merchantReport.setStatus("01");

        /**
         * 组织详情参数
         */
        String channel = merchantReport.getChannelNo();
        TdMerchantReportDetail tdMerchantReportDetail = getDetailParams(channel, jsonReportDetailInfo);
        tdMerchantReportDetail.setChannelNo(merchantReport.getChannelNo());
        tdMerchantReportDetail.setDetailStatus(merchantReport.getDetailStatus());
        tdMerchantReportDetail.setPatchNo(merchantReport.getPatchNo());

        /**
         * 调用渠道service存储
         */
        ResultData resultData = this.addMerchantReportDetailByChannel(channel, tdMerchantReportDetail);
        if (!"200".equalsIgnoreCase(resultData.get("code").toString())) {
            return resultData;
        }
        /**
         * this.dao.insert  调用dao存储
         */
        int result = this.tdMerchantReportDao.insertTdMerchantReport(merchantReport);
        if (result < 1) {
            return ResultData.error();
        }
        return resultData;
    }

    /**
     * 报备列表查询
     *
     * @param channel
     * @param params
     * @return
     */
    public ResultData findMerchantDetailList(String channel, String params) {
        TdMerchantReportDetail tdMerchantReportDetail = getDetailParams(channel, params);
        return this.queryMerchantReportDetailByChannel(channel, tdMerchantReportDetail);
    }
}
