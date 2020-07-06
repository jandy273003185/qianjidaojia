package com.qifenqian.bms.merchant.reports.service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoYqb;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantReportInfo;
import com.qifenqian.bms.merchant.reported.dao.TdMerchantDetailInfoYqbDao;
import com.qifenqian.bms.merchant.reported.dao.TdMerchantReportDao;
import com.qifenqian.bms.merchant.reports.bean.TdMerchantReportDetail;
import com.qifenqian.bms.merchant.reports.constants.ChannelTypeConstants;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.bizexception.BizException;
import com.seven.micropay.base.domain.ChannelResult;
import com.seven.micropay.channel.domain.merchant.YqbMerchantInfo;
import com.seven.micropay.channel.enums.ChannelMerRegist;
import com.seven.micropay.channel.service.IMerChantIntoService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LvFeng
 * @Description: 平安付渠道报备
 * @date 2020/5/6 16:38
 */
@Service("tdMerchantReportDetailService" + ChannelTypeConstants.YQB)
public class TdMerchantDetailInfoYqbService implements TdMerchantReportDetailService {
    private static final Logger logger = LoggerFactory.getLogger(TdMerchantDetailInfoYqbService.class);

    @Autowired
    private IMerChantIntoService iMerChantIntoService;

    @Autowired
    private TdMerchantDetailInfoYqbDao tdMerchantDetailInfoYqbDao;

    @Autowired
    private TdMerchantReportDao tdMerchantReportDao;

    @Override
    public TdMerchantReportDetail getDetailParams(String jsonString) {
        return JSONObject.parseObject(jsonString, TdMerchantDetailInfoYqb.class);
    }

    @Override
    public ResultData addMerchantReportDetail(TdMerchantReportDetail tdMerchantReportDetail) {
        TdMerchantDetailInfoYqb tdMerchantDetailInfoYqb = (TdMerchantDetailInfoYqb) tdMerchantReportDetail;
        int result = this.tdMerchantDetailInfoYqbDao.insert(tdMerchantDetailInfoYqb);
        if (result < 1) {
            return ResultData.error();
        }
        ChannelResult channelResult = yqbReportRPC(tdMerchantDetailInfoYqb);
        if (channelResult == null) {
            return ResultData.error("平安付进件明确失败");
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
        tdMerchantReportInfo.setOutMerchantCode(tdMerchantDetailInfoYqb.getOutMerchantCode());
        tdMerchantReportInfo.setResultMsg(msg);
        this.tdMerchantDetailInfoYqbDao.update(tdMerchantDetailInfoYqb);
        this.tdMerchantReportDao.updateTdMerchantReport(tdMerchantReportInfo);
        return ResultData.success();
    }

    @Override
    public ResultData queryMerchantReportDetailByChannel(TdMerchantReportDetail tdMerchantReportDetail) {
        TdMerchantDetailInfoYqb tdMerchantDetailInfoYqb = this.tdMerchantDetailInfoYqbDao.selectByPatchNo(tdMerchantReportDetail.getPatchNo());
        if (StringUtils.isNotBlank(tdMerchantDetailInfoYqb.getPatchNo())) {
            return ResultData.success(tdMerchantDetailInfoYqb);
        } else {
            return ResultData.error("平安付进件资料查询明确失败");
        }
    }

    private ChannelResult yqbReportRPC(TdMerchantDetailInfoYqb yqb) {
        YqbMerchantInfo merchantInfo = new YqbMerchantInfo();
        BeanUtils.copyProperties(yqb, merchantInfo);
        merchantInfo.setOutMerchantNo(yqb.getMerchantCode());        //服务商侧商户号			--必填
        merchantInfo.setMerchantName(yqb.getCustName());        //商户名称		--必填

        List<String> specialIndustryPaths = new ArrayList<>();
        specialIndustryPaths.add(yqb.getSpecialIndustryPaths());
        merchantInfo.setSpecialIndustryPaths(specialIndustryPaths);

        //其他材料图片			--个人商户必填
        List<String> otherMaterialPaths = new ArrayList<String>();
        otherMaterialPaths.add(yqb.getOtherMaterialPaths());
        merchantInfo.setOtherMaterialPaths(otherMaterialPaths);

        //必填参数校验
        String checkResult = merchantInfo.ckMerchantInfo();
        if (!"success".equals(checkResult)) {
            throw new BizException(checkResult);
        }
        Map<String, Object> req = new HashMap<>();
        req.put("merList", merchantInfo);
        req.put("channelType", ChannelMerRegist.YQB);

        logger.info("平安付进件请求报文：" + JSONObject.toJSONString(req));
        ChannelResult channelResult = iMerChantIntoService.merchatAdd(req);
        logger.info("平安付进件响应报文：" + JSONObject.toJSONString(channelResult));
        return channelResult;
    }
}
