package com.qifenqian.bms.merchant.reports.service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoLaKaLa;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantReportInfo;
import com.qifenqian.bms.merchant.reported.dao.TdMerchantDetailInfoLaKaLaDao;
import com.qifenqian.bms.merchant.reported.dao.TdMerchantReportDao;
import com.qifenqian.bms.merchant.reports.bean.TdMerchantReportDetail;
import com.qifenqian.bms.merchant.reports.constants.ChannelTypeConstants;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.seven.micropay.base.domain.ChannelResult;
import com.seven.micropay.channel.domain.merchant.suixinpayInfo.SxPayRequestInfo;
import com.seven.micropay.channel.service.IMerChantIntoService;
import com.seven.micropay.commons.util.DateUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 拉卡拉进件
 */
@Service("tdMerchantReportDetailService" + ChannelTypeConstants.LKL)
public class TdMerchantDetailInfoLaKaLaService implements TdMerchantReportDetailService {

    private static final Logger logger = LoggerFactory.getLogger(TdMerchantDetailInfoLaKaLaService.class);

    @Autowired
    private TdMerchantReportDao tdMerchantReportDao;

    @Autowired
    private IMerChantIntoService iMerChantIntoService;

    @Autowired
    private TdMerchantDetailInfoLaKaLaDao tdMerchantDetailInfoLaKaLaDao;

    @Override
    public TdMerchantReportDetail getDetailParams(String jsonString) {
        return JSONObject.parseObject(jsonString, TdMerchantDetailInfoLaKaLa.class);
    }

    @Override
    public ResultData addMerchantReportDetail(TdMerchantReportDetail tdMerchantReportDetail) {
        TdMerchantDetailInfoLaKaLa tdMerchantDetailInfoLaKaLa = (TdMerchantDetailInfoLaKaLa) tdMerchantReportDetail;
        int result = this.tdMerchantDetailInfoLaKaLaDao.insert(tdMerchantDetailInfoLaKaLa);
        if (result < 1) {
            return ResultData.error();
        }
        ChannelResult channelResult = laKaLaMerchantRPC(tdMerchantDetailInfoLaKaLa);
        if (channelResult == null) {
            return ResultData.error("拉卡拉进件明确失败");
        }

        String reportStatus = "N";
        String reportDetailStatus = "99";
        String status = channelResult.getStatus().toString();
        String msg = channelResult.getData().get("errorMsg").toString();
        if ("SUCCESS".equalsIgnoreCase(status)) {
            reportStatus = "Y";
            reportDetailStatus = "00";
        } else {
            reportStatus = "F";
            reportDetailStatus = "99";
        }
        TdMerchantReportInfo tdMerchantReportInfo = new TdMerchantReportInfo();
        tdMerchantReportInfo.setReportStatus(reportStatus);
        tdMerchantReportInfo.setDetailStatus(reportDetailStatus);
        tdMerchantReportInfo.setOutMerchantCode(tdMerchantDetailInfoLaKaLa.getOutMerchantCode());
        tdMerchantReportInfo.setResultMsg(msg);
        this.tdMerchantDetailInfoLaKaLaDao.updateDynamic(tdMerchantDetailInfoLaKaLa);
        this.tdMerchantReportDao.updateTdMerchantReport(tdMerchantReportInfo);
        return ResultData.success();
    }

    /**
     * 调用拉卡拉报备进件
     *
     * @param tdMerchantDetailInfoSuixingPay
     * @return
     */
    private ChannelResult laKaLaMerchantRPC(TdMerchantDetailInfoLaKaLa tdMerchantDetailInfoLaKaLa) {
//        SxPayMerchantInfo merchantInfo = new SxPayMerchantInfo();
//        merchantInfo.setIndependentModel(tdMerchantDetailInfoSuixingPay.getIndependentModel());
//        merchantInfo.setParentMno(tdMerchantDetailInfoSuixingPay.getParentMno());
//        merchantInfo.setRegistCode(tdMerchantDetailInfoSuixingPay.getRegistCode());
//        merchantInfo.setCprRegNmCn(tdMerchantDetailInfoSuixingPay.getCprRegNmCn());
//        merchantInfo.setMecDisNm(tdMerchantDetailInfoSuixingPay.getCustName());
//        merchantInfo.setMblNo(tdMerchantDetailInfoSuixingPay.getMobileNo());
//        merchantInfo.setHaveLicenseNo(tdMerchantDetailInfoSuixingPay.getSuiXingMerchantType());
//        merchantInfo.setMecTypeFlag(tdMerchantDetailInfoSuixingPay.getMecTypeFlag());
//        merchantInfo.setCprRegAddr(tdMerchantDetailInfoSuixingPay.getCprRegAddr());
//        merchantInfo.setRegProvCd(tdMerchantDetailInfoSuixingPay.getMerchantProvince());
//        merchantInfo.setRegCityCd(tdMerchantDetailInfoSuixingPay.getMerchantCity());
//        merchantInfo.setRegDistCd(tdMerchantDetailInfoSuixingPay.getMerchantArea());
//        merchantInfo.setQrcodeRate(tdMerchantDetailInfoSuixingPay.getRate());
//        merchantInfo.setMccCd(tdMerchantDetailInfoSuixingPay.getIndustryCode());
//        merchantInfo.setIdentityName(tdMerchantDetailInfoSuixingPay.getRepresentativeName());
//        merchantInfo.setIdentityTyp(tdMerchantDetailInfoSuixingPay.getRepresentativeCertType());
//        merchantInfo.setIdentityNo(tdMerchantDetailInfoSuixingPay.getRepresentativeCertNo());
//        merchantInfo.setActNm(tdMerchantDetailInfoSuixingPay.getActNm());
//        merchantInfo.setActTyp(tdMerchantDetailInfoSuixingPay.getActType());
//        merchantInfo.setStmManIdNo(tdMerchantDetailInfoSuixingPay.getCertifyNo());
//        merchantInfo.setActNo(tdMerchantDetailInfoSuixingPay.getBankCardNo());
//        merchantInfo.setBnkCd(SuixinBankType.valueOf(tdMerchantDetailInfoSuixingPay.getSuiXinBank()).getCode());
        /**
         * 银行编码
         */
//        merchantInfo.setBnkNm(SuixinBankType.valueOf(tdMerchantDetailInfoSuixingPay.getSuiXinBank()).getName()); // 开户行名称
//        merchantInfo.setLbnkProv(tdMerchantDetailInfoSuixingPay.getBankProvince());
//        merchantInfo.setLbnkCity(tdMerchantDetailInfoSuixingPay.getBankCity());
//        merchantInfo.setLbnkNo(tdMerchantDetailInfoSuixingPay.getLbnkNo());
        /**
         * 联行号
         */
//        merchantInfo.setLbnkNm(tdMerchantDetailInfoSuixingPay.getInterbankName());
//        merchantInfo.setTaskCode(tdMerchantDetailInfoSuixingPay.getTaskCode());
        /**
         * 照片压缩返回码
         */
//        merchantInfo.setMerUrl("https://combinedpay.qifenqian.com/gateway.do");
        SxPayRequestInfo requestInfo = new SxPayRequestInfo();
        requestInfo.setReqId(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
//        requestInfo.setReqData(merchantInfo);
        requestInfo.setTimestamp(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));

        /**
         * 拉卡拉远程调用
         */
        Map<String, Object> req = new HashMap<>();
        req.put("merList", requestInfo);
//        req.put("channelType", ChannelMerRegist.LAKALA_PAY);
        logger.info("拉卡拉进件请求报文：" + JSONObject.toJSONString(req));
        ChannelResult channelResult = iMerChantIntoService.merchatAdd(req);
        logger.info("拉卡拉进件响应报文：" + JSONObject.toJSONString(channelResult));
        return channelResult;
    }

    /**
     * TODO 转换查询条件,查询列表
     *
     * @param tdMerchantReportDetail
     * @return
     */
    @Override
    public ResultData queryMerchantReportDetailByChannel(TdMerchantReportDetail tdMerchantReportDetail) {
        TdMerchantDetailInfoLaKaLa tdMerchantDetailInfoLaKaLa = this.tdMerchantDetailInfoLaKaLaDao.selectByPatchNo(tdMerchantReportDetail.getPatchNo());
        if(StringUtils.isNotBlank(tdMerchantDetailInfoLaKaLa.getPatchNo())){
            return ResultData.success(tdMerchantDetailInfoLaKaLa);
        }else{
            return ResultData.error("拉卡拉进件资料查询明确失败");
        }
    }
}
