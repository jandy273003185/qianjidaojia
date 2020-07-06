package com.qifenqian.bms.merchant.reports.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoWeChat;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantReportInfo;
import com.qifenqian.bms.merchant.reported.dao.TdMerchantDetailInfoWechatDao;
import com.qifenqian.bms.merchant.reported.dao.TdMerchantReportDao;
import com.qifenqian.bms.merchant.reports.bean.TdMerchantReportDetail;
import com.qifenqian.bms.merchant.reports.constants.ChannelTypeConstants;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.jellyfish.api.merregist.WxpayAgentMerRegistService;
import com.qifenqian.jellyfish.bean.enums.BusinessStatus;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgentMerRegistReq;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgentMerRegistResp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @description:
 * @author: LiBin
 * @date: 2019/12/25 0025 17:08
 */
@Service("tdMerchantReportDetailService" + ChannelTypeConstants.WX)
public class TdMerchantDetailInfoWeChatService implements TdMerchantReportDetailService {
    private static final Logger logger = LoggerFactory.getLogger(TdMerchantDetailInfoWeChatService.class);
    @Reference(version="${dubbo.provider.jellyfish.version}")
    private WxpayAgentMerRegistService wxpayAgentMerRegistService;
    @Autowired
    private TdMerchantDetailInfoWechatDao tdMerchantDetailInfoWechatDao;
    @Autowired
    private TdMerchantReportDao tdMerchantReportDao;

    @Override
    public TdMerchantReportDetail getDetailParams(String jsonString) {
        return JSONObject.parseObject(jsonString, TdMerchantDetailInfoWeChat.class);
    }

    @Override
    public ResultData addMerchantReportDetail(TdMerchantReportDetail tdMerchantReportDetail) {
        TdMerchantDetailInfoWeChat tdMerchantDetailInfoWeChat = (TdMerchantDetailInfoWeChat) tdMerchantReportDetail;
        /**
         * 判断银行账号是否在微信支持范围内
         */
        List<String> accountList6 = Arrays.asList("623501", "621468", "620522", "625191", "622384", "623078",
                "622150", "622151", "622181", "622188", "955100", "621095", "620062", "621285", "621798", "621799",
                "621797", "622199", "621096", "621622", "623219", "621674", "623218", "621599", "623698", "623699",
                "620529", "622180", "622182", "622187", "622189", "621582", "623676", "623677", "622812", "622810",
                "623686", "621098", "622811", "628310", "625919", "625368", "625367", "518905", "622835", "625603",
                "940034", "625605", "518905");
        String accountNoPrefix6 = tdMerchantDetailInfoWeChat.getAccountNo().substring(0, 6);
        if (accountList6.contains(accountNoPrefix6)) {
            return ResultData.error("微信小微商户开户目前不支持此前缀的银行账号");
        }
        int reuslt = this.tdMerchantDetailInfoWechatDao.insert(tdMerchantDetailInfoWeChat);
        if (reuslt < 1) {
            return ResultData.error();
        }
        try {
            String reportStatus = "N";
            String reportDetailStatus = "99";
            WeiXinAgentMerRegistResp weiXinAgentMerRegistResp = this.weChatMerchantRPC(tdMerchantDetailInfoWeChat);
            if (BusinessStatus.SUCCESS.equals(weiXinAgentMerRegistResp.getSubCode())) {
                reportStatus = "Y";
                reportDetailStatus = "00";
            } else {
                reportStatus = "F";
                reportDetailStatus = "99";
            }
            tdMerchantDetailInfoWeChat.setReportStatus(reportDetailStatus);
            tdMerchantDetailInfoWeChat.setResultMsg(weiXinAgentMerRegistResp.getSubMsg());
            reuslt = this.tdMerchantDetailInfoWechatDao.updateDynamic(tdMerchantDetailInfoWeChat);
            if (reuslt < 1) {
                return ResultData.error();
            }
            TdMerchantReportInfo tdMerchantReportInfo = new TdMerchantReportInfo();
            tdMerchantReportInfo.setReportStatus(reportStatus);
            tdMerchantReportInfo.setDetailStatus(reportDetailStatus);
            tdMerchantReportInfo.setOutMerchantCode(tdMerchantDetailInfoWeChat.getOutMerchantCode());
            tdMerchantReportInfo.setResultMsg(weiXinAgentMerRegistResp.getSubMsg());
            reuslt = this.tdMerchantReportDao.updateTdMerchantReport(tdMerchantReportInfo);
            if (reuslt < 1) {
                return ResultData.error();
            }
            return ResultData.success(weiXinAgentMerRegistResp);
        } catch (Exception e) {
            return ResultData.error(e.getMessage());
        }
    }

    private WeiXinAgentMerRegistResp weChatMerchantRPC(TdMerchantDetailInfoWeChat tdMerchantDetailInfoWeChat) throws Exception {
        //微信报备
        WeiXinAgentMerRegistReq req = new WeiXinAgentMerRegistReq();
        //业务申请编号
        req.setBusinessCode(tdMerchantDetailInfoWeChat.getPatchNo());
        // 身份证人像面照片
        req.setIdCardCopy(tdMerchantDetailInfoWeChat.getLegalCertAttribute1Path());
        // 身份证国徽面
        req.setIdCardNational(tdMerchantDetailInfoWeChat.getLegalCertAttribute2Path());
        // 门店门口照片
        req.setStoreEntrancePic(tdMerchantDetailInfoWeChat.getDoorPhotoPath());
        // 店内环境照片
        req.setIndoorPic(tdMerchantDetailInfoWeChat.getShopInteriorPath());
        // 身份证姓名
        req.setIdCardName(tdMerchantDetailInfoWeChat.getRepresentativeName());
        // 身份证号码
        req.setIdCardNumber(tdMerchantDetailInfoWeChat.getRepresentativeCertNo());
        // 身份证有效期限
        String idCardValidTime = "[\"" + tdMerchantDetailInfoWeChat.getIdentityEffDate() + "\",\"" + ("2099-12-31".equals(tdMerchantDetailInfoWeChat.getIdentityValDate()) ? "长期" : tdMerchantDetailInfoWeChat.getIdentityValDate()) + "\"]";
        req.setIdCardValidTime(idCardValidTime);
        // 开户名称
        req.setAccountName(tdMerchantDetailInfoWeChat.getAccountNm());
        // 开户银行
        req.setAccountBank(tdMerchantDetailInfoWeChat.getBank());
        // 开户银行省市编码
        req.setBankAddressCode(tdMerchantDetailInfoWeChat.getBankCity());
        // 银行账号
        req.setAccountNumber(tdMerchantDetailInfoWeChat.getAccountNo());
        // 门店名称
        req.setStoreName(tdMerchantDetailInfoWeChat.getStoreName());
        // 门店省市编码
        req.setStoreAddressCode(tdMerchantDetailInfoWeChat.getMerchantArea());
        // 门店街道名称
        req.setStoreStreet(tdMerchantDetailInfoWeChat.getCprRegAddr());
        // 商户简称
        req.setMerchantShortname(tdMerchantDetailInfoWeChat.getCustName());
        // 客服电话
        req.setServicePhone(tdMerchantDetailInfoWeChat.getContactPhone());
        // 售卖商品/提供服务描述
        req.setProductDesc(tdMerchantDetailInfoWeChat.getIndustryCode());
        // 费率
        req.setRate(tdMerchantDetailInfoWeChat.getRate());
        // 超级管理员姓名
        req.setContact(tdMerchantDetailInfoWeChat.getRepresentativeName());
        // 手机号码
        req.setContactPhone(tdMerchantDetailInfoWeChat.getMobileNo());
        // 管理员邮箱
        req.setContactEmail(tdMerchantDetailInfoWeChat.getEmail());

        logger.info("-----------------微信进件请求报文：" + JSONObject.toJSONString(req));
        WeiXinAgentMerRegistResp wxregResp = wxpayAgentMerRegistService.microMerRegist(req);
        logger.info("-----------------微信进件响应报文：" + JSONObject.toJSONString(wxregResp));
        return wxregResp;
    }

    /**
     * TODO 转换查询条件,查询列表
     *
     * @param tdMerchantReportDetail
     * @return
     */
    @Override
    public ResultData queryMerchantReportDetailByChannel(TdMerchantReportDetail tdMerchantReportDetail) {
        return ResultData.success();
    }
}
