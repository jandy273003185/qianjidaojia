package com.qifenqian.bms.v2.biz.merchant.reported.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.merchant.channel.bean.ChannelBean;
import com.qifenqian.bms.merchant.channel.bean.ChannelDetailBean;
import com.qifenqian.bms.merchant.channel.bean.ChannelMerRegist;
import com.qifenqian.bms.merchant.channel.service.ChannelService;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoSuixingPay;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.service.FmIncomeService;
import com.qifenqian.bms.merchant.reports.service.TdMerchantReportService;
import com.qifenqian.bms.v2.biz.merchant.reported.bean.MerchantReportedAO;
import com.qifenqian.bms.v2.biz.merchant.reported.bean.TdMerchantReportInfoAO;
import com.qifenqian.bms.v2.biz.merchant.reported.bean.TdMerchantReportInfoVO;
import com.qifenqian.bms.v2.biz.merchant.reported.service.BizMerchantReportedService;
import com.qifenqian.bms.v2.common.mvc.bean.PageQuery;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.qifenqian.bms.v2.common.mvc.controller.BaseController;
import com.seven.micropay.base.domain.ChannelResult;
import com.seven.micropay.base.enums.ReStatus;
import com.seven.micropay.channel.domain.merchant.suixinpayInfo.SxPayRequestInfo;
import com.seven.micropay.channel.enums.ChannelCode;
import com.seven.micropay.channel.enums.PayType;
import com.seven.micropay.channel.service.IMerChantIntoService;
import com.seven.micropay.commons.util.DateUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @param
 * @author ShiLi
 * @description
 * @return
 * @date 2020/4/28 18:18
 */
@RestController
@Api(tags = "商户报备管理")
public class BizMerchantReportedController extends BaseController {

    @Autowired
    private BizMerchantReportedService bizMerchantReportedService;

    @Autowired
    private TdMerchantReportService tdMerchantReportService;

    @Autowired
    private FmIncomeMapperDao fmIncomeMapperDao;

    @Autowired
    private FmIncomeService fmIncomeService;

    @Autowired
    private IMerChantIntoService iMerChantIntoServic;

    @Autowired
    private MerchantMapper merchantMapper;

    @Autowired
    private ChannelService channelService;

    @PostMapping(value = "/reported/list")
    @ApiOperation(value = "商户报备列表")
    public PageInfo<TdMerchantReportInfoVO> list(PageQuery pageQuery, @RequestBody TdMerchantReportInfoAO requestBean) {
        return this.bizMerchantReportedService.merchantReportedList(requestBean);
    }

    @PostMapping(value = "/reported/detail")
    @ApiOperation(value = "商户报备资料详细")
    public ResultData detail(String channel, String params) {
        return this.tdMerchantReportService.findMerchantDetailList(channel, params);
    }

    @PostMapping(value = "/reported/add")
    @ApiOperation(value = "商户报备")
    public ResultData add(@RequestBody MerchantReportedAO merchantReportedAO) {
        return this.tdMerchantReportService.addReport(merchantReportedAO.getTdMerchantReport(), merchantReportedAO.getReportDetailInfo());
    }

    @PostMapping(value = "/reported/fileUpload")
    @ApiOperation(value = "随行付文件上传")
    public ResultData fileUpload(HttpServletRequest request, HttpServletResponse response, @RequestBody TdMerchantReportInfoAO merchantReportInfoAO) {
        if (StringUtils.isBlank(merchantReportInfoAO.getMerchantCode()))
            throw new BizException("商户号不能为空!");
        if (StringUtils.isBlank(merchantReportInfoAO.getPatchNo()))
            throw new BizException("批次号不能为空!");
        return bizMerchantReportedService.fileUpload(request, response, merchantReportInfoAO);
    }

    @PostMapping(value = "/reported/status")
    @ApiOperation(value = "报备状态查询")
    public ResultData status(@RequestBody TdMerchantDetailInfo detail) {
        JSONObject object = new JSONObject();
        Map<String, Object> req = new HashMap<String, Object>();

        String merchantCode = detail.getMerchantCode();
        TdCustInfo custInfo = new TdCustInfo();
        if (null != merchantCode) {
            custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
        }

        if ("BEST_PAY".equals(detail.getChannelNo())) {
            // 翼支付企业进件查询
            detail = fmIncomeMapperDao.selMerchantDetailInfo(detail);
            if ("02".equals(detail.getBestMerchantType())) {
                req.put("traceLogId", "QT" + DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
                req.put("loginNo", detail.getLoginNo());
                req.put("channelType", ChannelMerRegist.BEST_PAY);
                req.put("isCompany", true);
            } else {
                req.put("merchantNo", detail.getMerchantCode());
                req.put("requestSeqId", System.currentTimeMillis() + "BE");
                req.put("channelType", ChannelMerRegist.BEST_PAY);
            }

        } else if ("SUIXING_PAY".equals(detail.getChannelNo())) {

            List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
            String taskCode = reportedList.get(0).getRemark();
            SxPayRequestInfo requestInfo = new SxPayRequestInfo();
            requestInfo.setReqId(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
            requestInfo.setReqData(taskCode);
            requestInfo.setTimestamp(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
            req.put("merList", requestInfo);
            req.put("channelType", ChannelMerRegist.SUIXING_PAY);

        } else if ("SUM_PAY".equals(detail.getChannelNo())) {
            detail = fmIncomeMapperDao.selMerchantDetailInfo(detail);
            if (null != detail.getOutMerchantCode()) {
                req.put("mchntId", detail.getOutMerchantCode());
                req.put("channelType", ChannelMerRegist.SUM_PAY);
            } else {
                return ResultData.error("99", "外部商户号为空");
            }

        } else if ("YQB".equals(detail.getChannelNo())) {

            req.put("paltformMerNo", detail.getMerchantCode());
            req.put("channelType", ChannelMerRegist.YQB);

        } else if ("LKL".equals(detail.getChannelNo())) {
            req.put("contractId", detail.getOutMerchantCode());
            req.put("channelType", ChannelMerRegist.LKL);
        }

        logger.info("商户进件状态查询参数" + req);
        ChannelResult channelResult = iMerChantIntoServic.merQuery(req);
        logger.info(channelResult.getStatus() + "商户进件状态查询返回接口." + channelResult.getData());

        Map<String, Object> rtnResultMap = channelResult.getData();
        String channelMerNo = "";
        String wxMerNo = "";
        String zfbMerNo = "";
        if ((ReStatus.SUCCESS).equals(channelResult.getStatus())) {
            //审核通过,开通产品
            ChannelBean bean = new ChannelBean();
            if ("BEST_PAY".equals(detail.getChannelNo())) {
                if ("02".equals(detail.getBestMerchantType())) {
                    //翼支付企业进件成功后仍需签约
                    object.put("result", "SUCCESS");
                    object.put("message", detail.getBestMerchantType());
                } else {
                    bean.setChannelName(ChannelMerRegist.BEST_PAY);
                    channelMerNo = rtnResultMap.get("bestpayMctNo") == null ? "" : (String) rtnResultMap.get("bestpayMctNo");
                    detail.setBestpayMctNo(channelMerNo);
                    object.put("result", "SUCCESS");
                    object.put("message", channelResult.getData().get("ReMsg"));
                }
            } else if ("SUIXING_PAY".equals(detail.getChannelNo())) {
                bean.setChannelName(ChannelMerRegist.SUIXING_PAY);
                channelMerNo = rtnResultMap.get("mno") == null ? "" : (String) rtnResultMap.get("mno");
                wxMerNo = rtnResultMap.get("wxChildNo") == null ? "" : (String) rtnResultMap.get("wxChildNo");
                zfbMerNo = rtnResultMap.get("zfbChildNo") == null ? "" : (String) rtnResultMap.get("zfbChildNo");

            } else if ("SUM_PAY".equals(detail.getChannelNo())) {
                bean.setChannelName(ChannelMerRegist.SUM_PAY);
                channelMerNo = detail.getOutMerchantCode();
            } else if ("YQB".equals(detail.getChannelNo())) {
                if (rtnResultMap == null) {
                    return ResultData.error("返回参数为空,请重新进件");
                }
                bean.setChannelName(ChannelMerRegist.YQB);
                channelMerNo = rtnResultMap.get("merchantId") == null ? "" : (String) rtnResultMap.get("merchantId");
            } else if ("LKL".equals(detail.getChannelNo())) {
                bean.setChannelName(ChannelMerRegist.LKL);
                channelMerNo = rtnResultMap.get("mercId") == null ? "" : (String) rtnResultMap.get("mercId");
                detail.setOutMerchantCode(channelMerNo);
                object.put("result", "SUCCESS");
                object.put("message", channelResult.getData().get("ReMsg"));
            }
            //报备表中状态改变
            detail.setReportStatus("O");
            detail.setDetailStatus("1");
            //报备成功商户报备信息表中状态改变
            detail.setFileStatus("Y");
            detail.setOutMerchantCode(channelMerNo);
            detail.setWxChildNo(wxMerNo);
            detail.setZfbChildNo(zfbMerNo);
            fmIncomeService.UpdateMerReportAndMerDetailInfo(detail, "1");
            //报备成功修改商户状态
            MerchantVo merchantVo = new MerchantVo();
            merchantVo.setCustId(custInfo.getCustId());
            merchantVo.setFilingStatus("01");
            merchantVo.setFilingAuditStatus("00");
            merchantMapper.updateMerchant(merchantVo);

            //商户号
            bean.setCustId(custInfo.getCustId());
            //渠道商户号
            bean.setMerchantChannelId(channelMerNo);
            bean.setMerchantChannelKey(channelMerNo);
            //微信子商户号
            bean.setWxChildNo(wxMerNo);
            //支付宝子商户号
            bean.setZfbChildNo(zfbMerNo);

            List<ChannelDetailBean> details = new ArrayList<ChannelDetailBean>();
            //添加微信产品
            List<ChannelDetailBean> wxDetails = getWxChannel(details);
            bean.setDetails(wxDetails);
            //添加支付宝产品
            List<ChannelDetailBean> zfbDetails = getZfbChannel(details);
            bean.setDetails(zfbDetails);
            //有营业执照开通云闪付产品
            if (StringUtils.isNotBlank(custInfo.getBusinessLicense())) {
                List<ChannelDetailBean> unPayDetails = getUnionPayChannel(details, custInfo);
                bean.setDetails(unPayDetails);
            }
            //产品列表
            boolean saveChannel = channelService.saveOrupdateChannel(bean, null);
            logger.info("开通渠道产品状态：{}", saveChannel ? "成功" : "失败");
        } else if ((ReStatus.FAIL).equals(channelResult.getStatus())) {
            //查询报备失败改变表中状态
            if ("BEST_PAY".equals(detail.getChannelNo())) {
                if ("02".equals(detail.getBestMerchantType())) {
                    //翼支付企业进件成功后仍需签约
                } else {
                    channelMerNo = rtnResultMap == null ? "" : (String) rtnResultMap.get("bestpayMctNo");
                    detail.setBestpayMctNo(channelMerNo);
                }
            }//有外部商户号未成功则调更新接口   无外部商户号未成功则重新进件
            else if ("SUIXING_PAY".equals(detail.getChannelNo())) {
                channelMerNo = rtnResultMap == null ? "" : (String) rtnResultMap.get("mno");
                wxMerNo = rtnResultMap.get("wxChildNo") == null ? "" : (String) rtnResultMap.get("wxChildNo");
                zfbMerNo = rtnResultMap.get("zfbChildNo") == null ? "" : (String) rtnResultMap.get("zfbChildNo");
            } else if ("SUM_PAY".equals(detail.getChannelNo())) {
                channelMerNo = detail.getOutMerchantCode();
            } else if ("YQB".equals(detail.getChannelNo())) {
                if (rtnResultMap == null) {
                    return ResultData.error("返回参数为空,请重新进件");
                }
                channelMerNo = rtnResultMap.get("merchantId") == null ? "" : (String) rtnResultMap.get("merchantId");
            } else if ("LKL".equals(detail.getChannelNo())) {
                channelMerNo = detail.getOutMerchantCode();
            }
            detail.setReportStatus("F");
            detail.setOutMerchantCode(channelMerNo);
            detail.setWxChildNo(wxMerNo);
            detail.setZfbChildNo(zfbMerNo);
            detail.setResultMsg(channelResult.getReMsg());
            detail.setDetailStatus("2");
            fmIncomeService.UpdateMerReportAndMerDetailInfo(detail, "2");
            //审核未通过
            return ResultData.error("99", StringUtils.isBlank(channelResult.getReMsg()) ? "审核失败" : channelResult.getReMsg());
        } else {
            object.put("result", "SUCCESS");
            //通讯失败或者系统异常
            if ("04".equals(channelResult.getReCode())) {
                return ResultData.error("99", channelResult.getReMsg());
            } else {
                return ResultData.success("商户审核中");
            }
        }
        return ResultData.success("商户审核通过");
    }


    //添加微信产品
    private List<ChannelDetailBean> getWxChannel(List<ChannelDetailBean> details) {
        //微信扫码
        ChannelDetailBean weChatSM = new ChannelDetailBean();
        weChatSM.setChannelCode(ChannelCode.WEIXIN);
        weChatSM.setSubCode(PayType.SM);
        weChatSM.setWxAppId("wx1fc84beff3d0eeb8");
        weChatSM.setWxAppsecret("055e6b98ac3b4b6d7b704a6c3e884d64");
        details.add(weChatSM);
        //微信刷卡
        ChannelDetailBean weChatSK = new ChannelDetailBean();
        BeanUtils.copyProperties(weChatSM, weChatSK);
        weChatSK.setSubCode(PayType.SK);
        details.add(weChatSK);
        //微信公众号
        ChannelDetailBean weChatGZH = new ChannelDetailBean();
        BeanUtils.copyProperties(weChatSM, weChatGZH);
        weChatGZH.setSubCode(PayType.GZH);
        details.add(weChatGZH);

        return details;
    }

    //添加云闪付产品
    private List<ChannelDetailBean> getUnionPayChannel(List<ChannelDetailBean> details, TdCustInfo custInfo) {
        //有营业执照开通云闪付
        if (StringUtils.isNotBlank(custInfo.getBusinessLicense())) {
            ChannelDetailBean unionPay = new ChannelDetailBean();
            unionPay.setChannelCode(ChannelCode.UNIONPAY);
            unionPay.setSubCode(PayType.SK);
            details.add(unionPay);
        }

        return details;
    }

    //添加支付宝产品
    private List<ChannelDetailBean> getZfbChannel(List<ChannelDetailBean> details) {
        //支付宝扫码
        ChannelDetailBean aliPaySM = new ChannelDetailBean();
        aliPaySM.setChannelCode(ChannelCode.ALIPAY);
        aliPaySM.setSubCode(PayType.SM);
        details.add(aliPaySM);
        //支付宝刷卡
        ChannelDetailBean aliPaySK = new ChannelDetailBean();
        aliPaySK.setChannelCode(ChannelCode.ALIPAY);
        aliPaySK.setSubCode(PayType.SK);
        details.add(aliPaySK);

        return details;
    }


    //进件失败修改状态
    private JSONObject getFailStatus(String channelMerNo, String wxMerNo, String zfbMerNo, ChannelResult channelResult) {
        JSONObject object = new JSONObject();
        TdMerchantDetailInfo detail = new TdMerchantDetailInfo();
        detail.setReportStatus("F");
        detail.setOutMerchantCode(channelMerNo);
        detail.setWxChildNo(wxMerNo);
        detail.setZfbChildNo(zfbMerNo);
        detail.setResultMsg(channelResult.getReMsg());
        detail.setDetailStatus("2");
        fmIncomeService.UpdateMerReportAndMerDetailInfo(detail, "2");
        object.put("result", "FAIL");
        object.put("message", StringUtils.isBlank(channelResult.getReMsg()) ? "审核失败" : channelResult.getReMsg());
        return object;


    }

    @PostMapping(value = "/reported/suiXing/detail")
    @ApiOperation(value = "商户报备随行付资料详细")
    public TdMerchantDetailInfoSuixingPay detail(@RequestBody TdMerchantReportInfoAO requestBean) {
        TdMerchantDetailInfoSuixingPay tdMerchantDetailInfoSuixingPay = bizMerchantReportedService.suiXingMerchantDetailList(requestBean);
        return tdMerchantDetailInfoSuixingPay;
    }

}
