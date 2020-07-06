package com.qifenqian.bms.v2.biz.merchant.reported.service;

import com.github.pagehelper.PageInfo;
import com.qifenqian.bms.merchant.reported.bean.CrInComeBean;
import com.qifenqian.bms.merchant.reported.bean.SuiXingBean;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoSuixingPay;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.mapper.FmIncomeMapper;
import com.qifenqian.bms.merchant.reported.mapper.TdMerchantDetailInfoSuixingPayMapper;
import com.qifenqian.bms.merchant.reported.service.FmIncomeService;
import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.qifenqian.bms.v2.biz.merchant.reported.bean.TdMerchantReportInfoAO;
import com.qifenqian.bms.v2.biz.merchant.reported.bean.TdMerchantReportInfoVO;
import com.qifenqian.bms.v2.biz.merchant.reported.mapper.BizMerchantReportedMapper;
import com.qifenqian.bms.v2.common.mvc.bean.ResultData;
import com.qifenqian.bms.v2.common.mvc.service.BaseService;
import com.seven.micropay.base.domain.ChannelResult;
import com.seven.micropay.channel.domain.merchant.suixinpayInfo.SxPayUploadFileInfo;
import com.seven.micropay.channel.enums.ChannelMerRegist;
import com.seven.micropay.channel.service.IMerChantIntoService;
import com.seven.micropay.commons.util.DateUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author LiBin
 * @Description: 报备
 * @date 2020/4/21 13:52
 */
@Service
public class BizMerchantReportedService extends BaseService {
    @Autowired
    private BizMerchantReportedMapper bizMerchantReportedMapper;
    @Autowired
    private TdMerchantDetailInfoSuixingPayMapper merchantDetailInfoSuixingPayMapper;
    @Autowired
    private FmIncomeMapper fmIncomeMapper;
    @Autowired
    private FmIncomeService fmIncomeService;
    @Autowired
    private IMerChantIntoService iMerChantIntoService;
    @Value("${SX_FILE_SAVE_PATH}")
    private String SX_FILE_SAVE_PATH;

    public PageInfo<TdMerchantReportInfoVO> merchantReportedList(TdMerchantReportInfoAO requestBean) {

        List<TdMerchantReportInfoVO> reportList = bizMerchantReportedMapper.getMerchantReportedList(requestBean);
        return new PageInfo<>(reportList);
    }

    public TdMerchantDetailInfoSuixingPay suiXingMerchantDetailList(TdMerchantReportInfoAO requestBean) {
        TdMerchantDetailInfoSuixingPay tdMerchantDetailInfoSuixingPay = bizMerchantReportedMapper.suiXingMerchantDetailList(requestBean);
        return tdMerchantDetailInfoSuixingPay;
    }

    public ResultData fileUpload(HttpServletRequest request, HttpServletResponse response, TdMerchantReportInfoAO merchantReportInfoAO) {
        String merchantCode = merchantReportInfoAO.getMerchantCode();
        String patchNo = merchantReportInfoAO.getPatchNo();

        TdMerchantDetailInfo detail = new TdMerchantDetailInfo();
        detail.setMerchantCode(merchantCode);
        detail.setChannelNo("SUIXING_PAY");
        detail.setPatchNo(patchNo);
        TdMerchantDetailInfo detailInfo = bizMerchantReportedMapper.selectSuiXingPayMerchantDetailInfo(detail);
        SuiXingBean cr = new SuiXingBean();
        cr.setMerchantCode(merchantReportInfoAO.getMerchantCode());
        //不为空即资料已提交
        if (null != detailInfo && !"1".equals(detailInfo.getReportStatus()) && !"21".equals(detailInfo.getReportStatus())) {
            if (StringUtils.isNotBlank(detailInfo.getRemark()) && StringUtils.isNotBlank(detailInfo.getOutMerchantCode())) {
                cr.setTaskCode(detailInfo.getRemark());
            }
        }

        try {
            //文件更名压缩并上传服务器
            logger.info("文件更名压缩并上传服务器" + "------------------------------");
            Map<String, String> fileResult = fmIncomeService.download(request, response, cr);
            if (!"SUCCESS".equals(fileResult.get("result")))
                return ResultData.error(fileResult.get("message"));
            //上传成功
            //服务器地址
            String path = SX_FILE_SAVE_PATH + File.separator + cr.getMerchantCode() + ".zip";
            logger.info("+++++++++++" + path + "------------------------------");

            SxPayUploadFileInfo uploadFileInfo = new SxPayUploadFileInfo();

            uploadFileInfo.setReqId(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
            uploadFileInfo.setFilePath(path);
            Map<String, Object> req = new HashMap<>();
            ChannelResult result;
            //资料已上传过,更新
            if (null != cr.getTaskCode() && null != detailInfo && StringUtils.isNotBlank(detailInfo.getOutMerchantCode())) {
                uploadFileInfo.setTaskCode(cr.getTaskCode());
                req.put("merList", uploadFileInfo);
                req.put("channelType", ChannelMerRegist.SUIXING_PAY);
                logger.info("文件更新至随行付" + "------------------------------");
                result = iMerChantIntoService.updataMerchatAdd(req);
                logger.info("文件更新至随行付返回信息" + result.getChannelCode() + "------------------------------" + result.getReMsg());
            } else {
                req.put("merList", uploadFileInfo);
                req.put("channelType", ChannelMerRegist.SUIXING_PAY);

                logger.info("文件上传至随行付" + "------------------------------");
                result = iMerChantIntoService.fileUpload(req);
                logger.info("文件上传随行付返回信息" + result.getChannelCode() + "------------------------------");
            }
            if ("00".equals(result.getChannelCode())) {
                String taskCode = (String) result.getData().get("taskCode");
                //已提交过报备,更新本次数据
                if (null != detailInfo && !"O".equals(detailInfo.getReportStatus())) {
//                    CrInComeBean cc = new CrInComeBean();
//                    cc.setMerchantCode(cr.getMerchantCode());
//                    cc.setChannelNo("SUIXING_PAY");
//                    cc.setPatchNo(patchNo);
//                    TdMerchantDetailInfo td = fmIncomeService.getTdMerchantReport(cc);
                    TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
                    tdInfo.setRemark(taskCode);
                    tdInfo.setChannelNo("SUIXING_PAY");
                    tdInfo.setMerchantCode(merchantCode);
                    tdInfo.setPatchNo(detailInfo.getPatchNo());
                    tdInfo.setReportStatus("Y");
                    tdInfo.setFileStatus("Y");
                    tdInfo.setDetailStatus("0");
                    fmIncomeMapper.updateTdMerchantReport(tdInfo);
//						fmIncomeMapper.updateTdMerchantDetailInfo(tdInfo);
                }
                logger.info("上传文件结束-------------------" + taskCode);
            } else {
                logger.info("上传文件结束-------------------" + result.getReMsg());
                return ResultData.error(result.getReMsg());
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("随行付文件上传异常:", e);
            return ResultData.error(e.getMessage());
        }

        return ResultData.error();
    }

}
