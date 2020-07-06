package com.qifenqian.bms.merchant.reports.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoAlipay;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantReportInfo;
import com.qifenqian.bms.merchant.reported.dao.TdMerchantDetailInfoAlipayDao;
import com.qifenqian.bms.merchant.reported.dao.TdMerchantReportDao;
import com.qifenqian.bms.merchant.reports.bean.TdMerchantReportDetail;
import com.qifenqian.bms.merchant.reports.constants.ChannelTypeConstants;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.jellyfish.api.merregist.IAlipayAgentMerRegistService;
import com.qifenqian.jellyfish.bean.enums.GetwayStatus;
import com.qifenqian.jellyfish.bean.merregist.ContactInfo;
import com.qifenqian.jellyfish.bean.merregist.alipay.AlipayOpenAgentConfirmReq;
import com.qifenqian.jellyfish.bean.merregist.alipay.AlipayOpenAgentConfirmRes;
import com.qifenqian.jellyfish.bean.merregist.alipay.AlipayOpenAgentCreateReq;
import com.qifenqian.jellyfish.bean.merregist.alipay.AlipayOpenAgentCreateRes;
import com.qifenqian.jellyfish.bean.merregist.alipay.AlipayOpenAgentFacetofaceSignReq;
import com.qifenqian.jellyfish.bean.merregist.alipay.AlipayOpenAgentFacetofaceSignRes;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @description:
 * @author: LiBin
 * @date: 2019/12/25 0025 17:08
 */
@Service("tdMerchantReportDetailService" + ChannelTypeConstants.ALIPAY)
public class TdMerchantDetailInfoAlipayService implements TdMerchantReportDetailService {

    private static final Logger logger = LoggerFactory.getLogger(TdMerchantDetailInfoAlipayService.class);

    @Reference(version="${dubbo.provider.jellyfish.version}")
    private IAlipayAgentMerRegistService iAgentMerRegistService;

    @Autowired
    private TdMerchantDetailInfoAlipayDao tdMerchantDetailInfoAlipayDao;
    @Autowired
    private TdMerchantReportDao tdMerchantReportDao;

    @Override
    public TdMerchantReportDetail getDetailParams(String jsonString) {
        return JSONObject.parseObject(jsonString, TdMerchantDetailInfoAlipay.class);
    }

    @Override
    public ResultData addMerchantReportDetail(TdMerchantReportDetail tdMerchantReportDetail) {
        ResultData resultData;
        /**
         * 入库信息
         */
        TdMerchantDetailInfoAlipay tdMerchantDetailInfoAlipay = (TdMerchantDetailInfoAlipay) tdMerchantReportDetail;
        tdMerchantDetailInfoAlipayDao.insert(tdMerchantDetailInfoAlipay);

        String reportStatus = "N";
        String reportDetailStatus = "99";


        /** * 调用DUBBO服务,报备支付宝 */
        AlipayOpenAgentConfirmRes confirmRes = AlipayMerchantRPC(tdMerchantDetailInfoAlipay);
        if (!GetwayStatus.SUCCESS.equals(confirmRes.getCode())) {
            reportStatus = "F";
            reportDetailStatus = "99";
            //异常信息
            tdMerchantDetailInfoAlipay.setResultMsg(confirmRes.getSubMsg());
            resultData = ResultData.error("报备失败：" + confirmRes.getSubMsg());
        } else {
            tdMerchantDetailInfoAlipay.setZfbUserId(confirmRes.getUserId());
            tdMerchantDetailInfoAlipay.setZfbAuthAppId(confirmRes.getAuthAppId());
            tdMerchantDetailInfoAlipay.setZfbAppAuthToken(confirmRes.getAppAuthToken());
            tdMerchantDetailInfoAlipay.setZfbAppRefreshToken(confirmRes.getAppRefreshToken());
            tdMerchantDetailInfoAlipay.setZfbExpiresIn(confirmRes.getExpiresIn());
            tdMerchantDetailInfoAlipay.setZfbReExpiresIn(confirmRes.getReExpiresIn());
            reportStatus = "Y";
            reportDetailStatus = "00";
            resultData = ResultData.success("报备成功", confirmRes);
        }
        /**
         * 修改report
         */
        TdMerchantReportInfo tdMerchantReportInfo = new TdMerchantReportInfo();
        tdMerchantReportInfo.setReportStatus(reportStatus);
        tdMerchantReportInfo.setDetailStatus(reportDetailStatus);
        tdMerchantReportInfo.setResultMsg(tdMerchantDetailInfoAlipay.getResultMsg());
        tdMerchantReportInfo.setOutMerchantCode(tdMerchantDetailInfoAlipay.getAccount());
        tdMerchantReportInfo.setDetailStatus(tdMerchantDetailInfoAlipay.getReportStatus());
        int result = this.tdMerchantReportDao.updateTdMerchantReport(tdMerchantReportInfo);
        if (result < 1) {
            return ResultData.error();
        }
        /**
         * 修改支付宝渠道
         */
        tdMerchantDetailInfoAlipay.setReportStatus(reportDetailStatus);
        result = this.tdMerchantDetailInfoAlipayDao.updateDynamic(tdMerchantDetailInfoAlipay);
        if (result < 1) {
            return ResultData.error();
        }
        return resultData;
    }


    /**
     * @param tdMerchantDetailInfoAlipay
     * @return
     */

    private AlipayOpenAgentConfirmRes AlipayMerchantRPC(TdMerchantDetailInfoAlipay tdMerchantDetailInfoAlipay) {
        AlipayOpenAgentCreateReq aliReqBean = new AlipayOpenAgentCreateReq();
        aliReqBean.setAccount(tdMerchantDetailInfoAlipay.getAccount());
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setContactEmail(tdMerchantDetailInfoAlipay.getContactEmail());
        contactInfo.setContactMobile(tdMerchantDetailInfoAlipay.getContactMobile());
        contactInfo.setContactName(tdMerchantDetailInfoAlipay.getContactName());
        aliReqBean.setContactInfo(contactInfo);
        logger.debug("开启支付宝报备事务：{}", JSONObject.toJSONString(aliReqBean));
        AlipayOpenAgentCreateRes res = iAgentMerRegistService.alipayOpenAgentCreate(aliReqBean);
        logger.debug("支付宝报备事务返回值：{}", JSONObject.toJSONString(res));
        //返回批次号和状态
        tdMerchantDetailInfoAlipay.setZfbBatchNo(res.getBatchNo());
        tdMerchantDetailInfoAlipay.setZfbBatchStatus(res.getBatchStatus());
        //待签约当面付产品
        AlipayOpenAgentFacetofaceSignReq aliSignReqBean = new AlipayOpenAgentFacetofaceSignReq();
        //批次号
        aliSignReqBean.setBatchNo(res.getBatchNo());
        //经营类目
        aliSignReqBean.setMccCode(tdMerchantDetailInfoAlipay.getMccCode());
        //特殊资质
        if (StringUtils.isNotBlank(tdMerchantDetailInfoAlipay.getQualificationPath())) {
            aliSignReqBean.setSpecialLicensePic(tdMerchantDetailInfoAlipay.getQualificationPath());
        }
        //营业执照号码
        aliSignReqBean.setBusinessLicenseNo(tdMerchantDetailInfoAlipay.getBusinessLicense());
        //营业执照
        aliSignReqBean.setBusinessLicensePic(tdMerchantDetailInfoAlipay.getBusinessPhotoPath());
        //营业执照授权函
        if (StringUtils.isNotBlank(tdMerchantDetailInfoAlipay.getBusinessAuthPhotoPath())) {
            aliSignReqBean.setBusinessLicenseAuthPic(tdMerchantDetailInfoAlipay.getBusinessAuthPhotoPath());
        }
        //营业执照是否长期有效
        aliSignReqBean.setLongTerm("2099-12-31".equals(tdMerchantDetailInfoAlipay.getBusinessTerm()));
        //营业期限
        if (StringUtils.isNotBlank(tdMerchantDetailInfoAlipay.getBusinessTerm()) && !aliSignReqBean.getLongTerm()) {
            aliSignReqBean.setDateLimitation(tdMerchantDetailInfoAlipay.getBusinessTerm());
        }
        if (StringUtils.isNotBlank(tdMerchantDetailInfoAlipay.getShopInteriorPath())) {
            //店铺内景照
            aliSignReqBean.setShopScenePic(tdMerchantDetailInfoAlipay.getShopInteriorPath());
        }
        //店铺门头照
        aliSignReqBean.setShopSignBoardPic(tdMerchantDetailInfoAlipay.getDoorPhotoPath());
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

    /**
     * TODO 转换查询条件,查询列表
     *
     * @param tdMerchantReportDetail
     * @return
     */
    @Override
    public ResultData queryMerchantReportDetailByChannel(TdMerchantReportDetail tdMerchantReportDetail) {
        TdMerchantDetailInfoAlipay tdMerchantDetailInfoAlipay = (TdMerchantDetailInfoAlipay) tdMerchantReportDetail;
        List<TdMerchantDetailInfoAlipay> tdMerchantDetailInfoAlipays = new ArrayList<>();
        return ResultData.success(tdMerchantDetailInfoAlipays);
    }
}
