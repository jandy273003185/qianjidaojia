package com.qifenqian.bms.merchant.reported.service;

import java.util.HashMap;
import java.util.Map;

import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoAlipay;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantReportInfo;
import com.qifenqian.bms.merchant.reported.dao.TdMerchantDetailInfoAlipayDao;
import com.qifenqian.bms.merchant.reported.dao.TdMerchantReportDao;
import com.qifenqian.jellyfish.bean.enums.GetwayStatus;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.merchant.reported.bean.AliPayCoBean;
import com.qifenqian.jellyfish.api.merregist.IAlipayAgentMerRegistService;
import com.qifenqian.jellyfish.bean.merregist.ContactInfo;
import com.qifenqian.jellyfish.bean.merregist.alipay.AlipayOpenAgentConfirmReq;
import com.qifenqian.jellyfish.bean.merregist.alipay.AlipayOpenAgentConfirmRes;
import com.qifenqian.jellyfish.bean.merregist.alipay.AlipayOpenAgentCreateReq;
import com.qifenqian.jellyfish.bean.merregist.alipay.AlipayOpenAgentCreateRes;
import com.qifenqian.jellyfish.bean.merregist.alipay.AlipayOpenAgentFacetofaceSignReq;
import com.qifenqian.jellyfish.bean.merregist.alipay.AlipayOpenAgentFacetofaceSignRes;
import com.qifenqian.jellyfish.bean.merregist.alipay.AlipayOpenAgentOrderQueryReq;
import com.qifenqian.jellyfish.bean.merregist.alipay.AlipayOpenAgentOrderQueryRes;


@Service
public class AliPayIncomeService {

    private static final Logger logger = LoggerFactory.getLogger(AliPayIncomeService.class);

    @Reference(version="${dubbo.provider.jellyfish.version}")
    private IAlipayAgentMerRegistService iAgentMerRegistService;

    @Autowired
    private TdMerchantReportDao tdMerchantReportDao;
    @Autowired
    private TdMerchantDetailInfoAlipayDao tdMerchantDetailInfoAlipayDao;

    /**
     * 支付宝商户报备
     *
     * @param aliPayCoBean
     * @return
     * @throws Exception
     */
    public Map<String, Object> aliPayReported(AliPayCoBean aliPayCoBean) throws Exception {
        //添加商户报备详情表（td_merchant_detail_info）和商户报备表（td_merchant_report）

        logger.info("支付宝商户报备start.");
        //报备批次号
        String patchNo = GenSN.getSN();
        /**
         * 商户报备主表
         */
        TdMerchantReportInfo tdMerchantReportInfo = new TdMerchantReportInfo();
        tdMerchantReportInfo.setChannelNo(aliPayCoBean.getChannelNo());
        tdMerchantReportInfo.setId(GenSN.getSN());
        tdMerchantReportInfo.setMerchantCode(aliPayCoBean.getMerchantCode());
        tdMerchantReportInfo.setPatchNo(patchNo);
        /**
         * TODO 默认值是多少?
         */
        tdMerchantReportInfo.setOutMerchantCode("");
        tdMerchantReportInfo.setResultMsg("");
        tdMerchantReportInfo.setDetailStatus("");
        tdMerchantReportInfo.setReportStatus("E");
        tdMerchantReportInfo.setStatus("01");
        logger.debug("插入td_merchant_report表数据：{}", JSONObject.toJSONString(tdMerchantReportInfo));
        tdMerchantReportDao.insertTdMerchantReport(tdMerchantReportInfo);

        /**
         * 支付宝报备信息
         */
        TdMerchantDetailInfoAlipay tdMerchantDetailInfoAlipay = new TdMerchantDetailInfoAlipay();
        BeanUtils.copyProperties(aliPayCoBean, tdMerchantDetailInfoAlipay);
        tdMerchantDetailInfoAlipay.setPatchNo(patchNo);
        tdMerchantDetailInfoAlipay.setReportStatus("99");
        logger.debug("插入td_merchant_detail_info表数据：{}", JSONObject.toJSONString(tdMerchantDetailInfoAlipay));
        tdMerchantDetailInfoAlipayDao.insert(tdMerchantDetailInfoAlipay);


        /**
         * 调用DUBBO服务,报备支付宝
         */
        AlipayOpenAgentConfirmRes confirmRes = AlipayMerchantRPC(aliPayCoBean, tdMerchantDetailInfoAlipay, tdMerchantReportInfo);
        /**
         * 断网测试
         */
        //AlipayOpenAgentConfirmRes confirmRes = new AlipayOpenAgentConfirmRes();
        /**
         * 封装返回Map
         */
        Map<String, Object> result = new HashMap<>();
        if (!GetwayStatus.SUCCESS.equals(confirmRes.getCode())) {
            //异常信息
            tdMerchantDetailInfoAlipay.setResultMsg(confirmRes.getSubMsg());
            tdMerchantDetailInfoAlipay.setReportStatus("99");
            result.put("message", "报备失败：" + confirmRes.getSubMsg());
            result.put("code", "FAIL");
        } else {
            tdMerchantReportInfo.setReportStatus("O");
            tdMerchantDetailInfoAlipay.setReportStatus("00");
            tdMerchantDetailInfoAlipay.setZfbUserId(confirmRes.getUserId());
            tdMerchantDetailInfoAlipay.setZfbAuthAppId(confirmRes.getAuthAppId());
            tdMerchantDetailInfoAlipay.setZfbAppAuthToken(confirmRes.getAppAuthToken());
            tdMerchantDetailInfoAlipay.setZfbAppRefreshToken(confirmRes.getAppRefreshToken());
            tdMerchantDetailInfoAlipay.setZfbExpiresIn(confirmRes.getExpiresIn());
            tdMerchantDetailInfoAlipay.setZfbReExpiresIn(confirmRes.getReExpiresIn());
            result.put("data", confirmRes);
            result.put("message", "报备成功");
            result.put("code", "SUCCESS");
        }
        //更新
        logger.debug("更新td_merchant_report和td_merchant_detail_info表数据：{}", JSONObject.toJSONString(tdMerchantDetailInfoAlipay));
        tdMerchantReportInfo.setResultMsg(tdMerchantDetailInfoAlipay.getResultMsg());
        tdMerchantReportInfo.setOutMerchantCode(tdMerchantDetailInfoAlipay.getAccount());
        tdMerchantReportInfo.setDetailStatus(tdMerchantDetailInfoAlipay.getReportStatus());
        this.tdMerchantReportDao.updateTdMerchantReport(tdMerchantReportInfo);
        this.tdMerchantDetailInfoAlipayDao.updateDynamic(tdMerchantDetailInfoAlipay);
        logger.info("支付宝商户报备end.");
        return result;
    }

    /**
     * 支付宝报备 调用DUBBO服务,返回数据
     *
     * @param aliPayCoBean
     * @param tdMerchantDetailInfoAlipay
     * @param tdMerchantReportInfo
     * @return
     */
    private AlipayOpenAgentConfirmRes AlipayMerchantRPC(AliPayCoBean aliPayCoBean, TdMerchantDetailInfoAlipay tdMerchantDetailInfoAlipay, TdMerchantReportInfo tdMerchantReportInfo) {

        AlipayOpenAgentCreateReq aliReqBean = new AlipayOpenAgentCreateReq();
        aliReqBean.setAccount(aliPayCoBean.getAccount());
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setContactEmail(aliPayCoBean.getContactEmail());
        contactInfo.setContactMobile(aliPayCoBean.getContactMobile());
        contactInfo.setContactName(aliPayCoBean.getContactName());
        aliReqBean.setContactInfo(contactInfo);
        logger.debug("开启支付宝报备事务：{}", JSONObject.toJSONString(aliReqBean));
        AlipayOpenAgentCreateRes res = alipayOpenAgentCreate(aliReqBean);
        logger.debug("支付宝报备事务返回值：{}", JSONObject.toJSONString(res));
        //返回批次号和状态
        tdMerchantDetailInfoAlipay.setZfbBatchNo(res.getBatchNo());
        tdMerchantDetailInfoAlipay.setZfbBatchStatus(res.getBatchStatus());
        //待签约当面付产品
        AlipayOpenAgentFacetofaceSignReq aliSignReqBean = new AlipayOpenAgentFacetofaceSignReq();
        //批次号
        aliSignReqBean.setBatchNo(res.getBatchNo());
        //经营类目
        aliSignReqBean.setMccCode(aliPayCoBean.getMccCode());
        //特殊资质
        if (StringUtils.isNotBlank(aliPayCoBean.getQualificationPath())) {
            aliSignReqBean.setSpecialLicensePic(aliPayCoBean.getQualificationPath());
        }
        //营业执照号码
        aliSignReqBean.setBusinessLicenseNo(aliPayCoBean.getBusinessLicense());
        //营业执照
        aliSignReqBean.setBusinessLicensePic(aliPayCoBean.getBusinessPhotoPath());
        //营业执照授权函
        if (StringUtils.isNotBlank(aliPayCoBean.getBusinessAuthPhotoPath())) {
            aliSignReqBean.setBusinessLicenseAuthPic(aliPayCoBean.getBusinessAuthPhotoPath());
        }
        //营业执照是否长期有效
        aliSignReqBean.setLongTerm("2099-12-31".equals(aliPayCoBean.getBusinessTerm()));
        //营业期限
        if (StringUtils.isNotBlank(aliPayCoBean.getBusinessTerm()) && !aliSignReqBean.getLongTerm()) {
            aliSignReqBean.setDateLimitation(aliPayCoBean.getBusinessTerm());
        }
        if (StringUtils.isNotBlank(aliPayCoBean.getShopInteriorPath())) {
            //店铺内景照
            aliSignReqBean.setShopScenePic(aliPayCoBean.getShopInteriorPath());
        }
        //店铺门头照
        aliSignReqBean.setShopSignBoardPic(aliPayCoBean.getDoorPhotoPath());
        logger.debug("支付宝商户签约：{}", JSONObject.toJSONString(aliSignReqBean));
        AlipayOpenAgentFacetofaceSignRes signRes = alipayOpenAgentFacetofaceSign(aliSignReqBean);
        logger.debug("支付宝商户签约返回值：{}", JSONObject.toJSONString(signRes));
        //提交事务
        AlipayOpenAgentConfirmReq aliConfirmReqBean = new AlipayOpenAgentConfirmReq();
        aliConfirmReqBean.setBatchNo(res.getBatchNo());
        logger.debug("支付宝商户签约提交：{}", JSONObject.toJSONString(aliConfirmReqBean));
        AlipayOpenAgentConfirmRes confirmRes = alipayOpenAgentConfirm(aliConfirmReqBean);
        logger.debug("支付宝商户签约提交返回值：{}", JSONObject.toJSONString(confirmRes));
        return confirmRes;
    }

    /**
     * 查询支付宝报备申请单状态
     *
     * @param batchNo 支付宝报备事务编号
     * @return
     */
    public AlipayOpenAgentOrderQueryRes alipayOpenAgentOrderQuery(String batchNo) {
        logger.debug("查询支付宝报备申请单状态，报备事务编号：{}", batchNo);
        AlipayOpenAgentOrderQueryReq orderQueryReq = new AlipayOpenAgentOrderQueryReq();
        orderQueryReq.setBatchNo(batchNo);
        AlipayOpenAgentOrderQueryRes orderQueryRes = iAgentMerRegistService.alipayOpenAgentOrderQuery(orderQueryReq);
        logger.debug("查询支付宝报备申请单状态，返回值：{}", JSONObject.toJSONString(orderQueryRes));
        return orderQueryRes;
    }

    /**
     * 开启支付宝报备事务
     *
     * @param aliCreateReqBean
     * @return
     */
    public AlipayOpenAgentCreateRes alipayOpenAgentCreate(AlipayOpenAgentCreateReq aliCreateReqBean) {
        AlipayOpenAgentCreateRes res = iAgentMerRegistService.alipayOpenAgentCreate(aliCreateReqBean);
        return res;
    }

    /**
     * 支付宝签约当面付
     *
     * @param aliSignReqBean
     * @return
     */
    public AlipayOpenAgentFacetofaceSignRes alipayOpenAgentFacetofaceSign(AlipayOpenAgentFacetofaceSignReq aliSignReqBean) {
        AlipayOpenAgentFacetofaceSignRes res = iAgentMerRegistService.alipayOpenAgentFacetofaceSign(aliSignReqBean);
        return res;
    }

    /**
     * 支付宝提交代商户签约
     *
     * @param aliConfirmReqBean
     * @return
     */
    public AlipayOpenAgentConfirmRes alipayOpenAgentConfirm(AlipayOpenAgentConfirmReq aliConfirmReqBean) {
        AlipayOpenAgentConfirmRes res = iAgentMerRegistService.alipayOpenAgentConfirm(aliConfirmReqBean);
        return res;
    }
}
