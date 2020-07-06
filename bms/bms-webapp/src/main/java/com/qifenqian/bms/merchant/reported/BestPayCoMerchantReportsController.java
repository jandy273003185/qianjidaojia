package com.qifenqian.bms.merchant.reported;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.merchant.reported.bean.Bank;
import com.qifenqian.bms.merchant.reported.bean.BestPayCoBean;
import com.qifenqian.bms.merchant.reported.bean.ChannlInfo;
import com.qifenqian.bms.merchant.reported.bean.CrInComeBean;
import com.qifenqian.bms.merchant.reported.bean.Industry;
import com.qifenqian.bms.merchant.reported.bean.Province;
import com.qifenqian.bms.merchant.reported.bean.TbFmTradeInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.service.CrIncomeService;
import com.qifenqian.bms.merchant.reported.service.FmIncomeService;
import com.seven.micropay.base.domain.ChannelResult;
import com.seven.micropay.channel.domain.merchant.BestCoMerOpenProdct;
import com.seven.micropay.channel.domain.merchant.BestPayBankInfo;
import com.seven.micropay.channel.domain.merchant.BestCoProdAuthInfo.RateList;
import com.seven.micropay.channel.enums.BestBankCode;
import com.seven.micropay.channel.enums.ChannelMerRegist;
import com.seven.micropay.channel.service.IMerChantIntoService;
import com.seven.micropay.commons.util.DateUtil;

@Controller
@RequestMapping(MerchantReportedPath.BASE)
public class BestPayCoMerchantReportsController {

  private Logger logger = LoggerFactory.getLogger(BestPayCoMerchantReportsController.class);

  @Autowired private FmIncomeService fmIncomeService;

  @Autowired private CrIncomeService crIncomeService;

  @Autowired private MerchantService merchantService;

  @Autowired private FmIncomeMapperDao fmIncomeMapperDao;

  @Autowired private IMerChantIntoService iMerChantIntoServic;

  /** 翼支付企业报备入口 */
  @RequestMapping(MerchantReportedPath.BESTPAYCOMERCHANTREPORTS)
  public ModelAndView viewMerchantReported(
      HttpServletRequest request,
      HttpServletResponse response,
      TdMerchantDetailInfo detail,
      String merchantCode,
      String merchantType,
      String status) {
    ModelAndView mv = new ModelAndView();
    String channlCode = "BEST_PAY";
    if (null == detail || null == detail.getMerchantCode()) {
      detail.setMerchantCode(merchantCode);
    }
    if (null == detail || null == detail.getChannelNo()) {
      detail.setChannelNo(channlCode);
    }
    /** *查询渠道** */
    List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
    /** *查询报备信息** */
    List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
    /** *查询省份信息** */
    List<Province> proviceList = fmIncomeService.getprovinceList();
    /** *查询银行信息** */
    List<Bank> bankList = fmIncomeService.getBankList();
    /** *查询支付功能Id** */
    List<TbFmTradeInfo> powerIdList = fmIncomeService.getPowerIdList();
    /** *查询翼支付商户行业信息** */
    List<Industry> industryList = fmIncomeService.getIndustryList();
    merchantCode = detail.getMerchantCode();
    TdCustInfo custInfo = new TdCustInfo();
    if (null != merchantCode) {
      custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
    }
    if (null != reportedList && reportedList.size() > 0) {
      mv.addObject("reportedList", reportedList);
    }
    if (null != channlInfoList && channlInfoList.size() > 0) {
      mv.addObject("infoList", channlInfoList);
    }
    if (null != proviceList && proviceList.size() > 0) {
      mv.addObject("provinceList", proviceList);
    }
    if (null != bankList && bankList.size() > 0) {
      mv.addObject("bankList", bankList);
    }
    if (null != powerIdList && powerIdList.size() > 0) {
      mv.addObject("powerIdList", powerIdList);
    }
    if (null != industryList && industryList.size() > 0) {
      mv.addObject("industryList", industryList);
    }
    if (null != custInfo) {
      mv.addObject("custInfo", custInfo);
    }
    mv.addObject("status", status);
    return mv;
  }

  /**
   * 提交报备
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(MerchantReportedPath.BESTPAYCOSUBMITREPORT)
  @ResponseBody
  public String list(HttpServletRequest request, HttpServletResponse response, BestPayCoBean cr) {
    JSONObject object = new JSONObject();
    JSONObject bestResult = new JSONObject();
    request.setAttribute("merchantCode", cr.getMerchantCode().trim());
    if ("BEST_PAY".equals(cr.getChannelNo().trim())) { // 翼支付
      // 查询该商户是否已报备
      CrInComeBean comeBean = new CrInComeBean();
      comeBean.setMerchantCode(cr.getMerchantCode());
      comeBean.setChannelNo(cr.getChannelNo());
      TdMerchantDetailInfo td = fmIncomeService.getTdMerchantReport(comeBean);

      if (td != null) {
        // 该商户已报备
        if ("Y".equals(td.getReportStatus()) || "O".equals(td.getReportStatus())) {
          object.put("result", "FAILURE");
          object.put("message", "商户已经报备，请勿重新提交");
          return object.toString();
        } else { // 商户报备
          TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
          tdInfo.setMerchantCode(td.getMerchantCode());
          tdInfo.setChannelNo(td.getChannelNo());
          tdInfo.setReportStatus("E");
          tdInfo.setBestMerchantType("02");
          fmIncomeService.UpdateMerReportAndMerDetailInfo(tdInfo, "99");

          // 获取图片路径
          TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(cr.getMerchantCode());

          // 获取开户许可证路径
          String openPath =
              merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "03");
          cr.setOpenPic(openPath);

          // 获取身份证正反面
          String identityImagePath =
              merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "04");
          String[] paths = null;
          paths = identityImagePath.split(";");
          // 正面
          String identityImagePath0 = paths[0];
          cr.setIdentityCardFrontPic(identityImagePath0);
          // 反面
          String identityImagePath1 = paths[1];
          cr.setIdentityCardReversePic(identityImagePath1);

          // 获取营业执照
          String imagePath =
              merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "02");
          cr.setLicensePic(imagePath);

          // 调用翼支付报备接口
          bestResult = fmIncomeService.bestCoReported(cr);

          if ("SUCCESS".equals(bestResult.get("result"))) {
            object.put("result", "SUCCESS");
            object.put("message", "报备成功");
          } else {
            object.put("result", "FAILURE");
            if (bestResult.get("message") == "" && bestResult.get("message") == null) {
              object.put("message", "翼支付进件明确失败");
            } else {
              object.put("message", bestResult.get("message"));
            }
          }
        }

      } else { // 查询为空，商户未报备
        // 添加商户报备详情表（td_merchant_detail_info）和商户报备表（td_merchant_report）
        TdMerchantDetailInfo info = new TdMerchantDetailInfo();
        info.setId(GenSN.getSN());
        info.setPatchNo(GenSN.getSN());
        info.setMerchantCode(cr.getMerchantCode().trim());
        info.setChannelNo(cr.getChannelNo());
        info.setReportStatus("E");
        info.setProvCode(cr.getProvince());
        info.setCityCode(cr.getCity());
        info.setContryCode(cr.getCountry());
        info.setBankCode(cr.getBankCode());
        //				info.setBranchBankCode(cr.getInterBank());
        info.setBranchBankName(cr.getInterBankName());
        info.setMobileNo(cr.getMobileNo());
        info.setBestMerchantType("02");
        fmIncomeService.insertTdMerchantReport(info);
        info.setReportStatus("99");
        fmIncomeService.inserTdMerchantDetailInfo(info);
        // 判断支行是否正确
        BestPayBankInfo bankInfo = new BestPayBankInfo();
        bankInfo.setRequestSeqId("" + System.currentTimeMillis());
        bankInfo.setBankName(cr.getInterBankName());
        bankInfo.setBankCode(BestBankCode.getBestBankCodeByBankCode(cr.getBankCode()));
        Map<String, Object> req = new HashMap<String, Object>();
        req.put("merList", bankInfo);
        req.put("channelType", ChannelMerRegist.BEST_PAY);
        ChannelResult channelResult = iMerChantIntoServic.queryBankInfo(req);
        if ("SUCCESS".equals(channelResult.getStatus())
            && "00".equals(channelResult.getChannelCode())) {
          // 获取图片路径
          TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(cr.getMerchantCode());

          // 获取开户许可证路径
          String openPath =
              merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "03");
          cr.setOpenPic(openPath);

          // 获取身份证正反面
          String identityImagePath =
              merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "04");
          String[] paths = null;
          paths = identityImagePath.split(";");
          // 正面
          String identityImagePath0 = paths[0];
          cr.setIdentityCardFrontPic(identityImagePath0);
          // 反面
          String identityImagePath1 = paths[1];
          cr.setIdentityCardReversePic(identityImagePath1);

          // 获取营业执照
          String licensePath =
              merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "02");
          cr.setLicensePic(licensePath);

          // 调用翼支付报备接口
          bestResult = fmIncomeService.bestCoReported(cr);

          if ("SUCCESS".equals(bestResult.get("result"))) {
            object.put("result", "SUCCESS");
            object.put("message", "报备成功");
          } else {
            object.put("result", "FAILURE");
            if (bestResult.get("message") == "" && bestResult.get("message") == null) {
              object.put("message", "翼支付进件明确失败");
            } else {
              object.put("message", bestResult.get("message"));
            }
          }
        } else {
          object.put("result", "FAILURE");
          if (null != channelResult.getReMsg()) {
            object.put("message", channelResult.getReMsg());
          } else {
            object.put("message", "银行信息错误");
          }
        }
      }
    }
    return object.toString();
  }

  /**
   * 签约产品
   *
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(MerchantReportedPath.CONTRACTPRODUCT)
  @ResponseBody
  public String contractProduct(
      HttpServletRequest request,
      HttpServletResponse response,
      RateList rl,
      String productCode,
      String offlineAgreementPath) {
    JSONObject object = new JSONObject();
    //			String offlineAgreementPath = "E:/";//若为标准商户（标准商户费率0.35）则不用填；签约文件本地路径 文件命名为:商户编号.pdf
    BestCoMerOpenProdct op = new BestCoMerOpenProdct();
    op.setTraceLogId("OPT" + DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
    op.setMerchantCode(rl.getMerchantCode());
    op.setProdCode(productCode);
    if (!StringUtils.isEmpty(offlineAgreementPath)) {
      op.setOfflineAgreementPath(offlineAgreementPath + "/" + rl.getMerchantCode() + ".pdf");
    }
    // 费率数据
    List<RateList> rateList = new ArrayList<RateList>();
    RateList rate = new RateList();
    rate.setFeeFlag(rl.getFeeFlag()); // TRUE 收费; FALSE 不收费
    rate.setFeeMode(rl.getFeeMode()); // SINGLE 单笔计费;ACCUMUL 累积计费;PACKET 包量计费（金额）
    rate.setBillingMode(rl.getBillingMode()); // FIXED 固定 RAT 比率
    rate.setBillingValue(rl.getBillingValue()); // 费率值
    rate.setFeeBeginAmt(rl.getFeeBeginAmt()); // 起费金额
    rate.setBottomAmt(rl.getBottomAmt()); // 封底金额
    rate.setCeilingAmt(rl.getCeilingAmt()); // 封顶金额
    rate.setFeeStatus(rl.getFeeStatus()); // 收费状态
    // rate.setSegmentFlag("FALSE");  //分档标识,暂时不支持分档，默认填 FALSE
    rate.setFeeCycle(rl.getFeeCycle()); // AGREEMENT 协议周期;YEAR 按自然年;MONTH 按自然月;WEEK 按自然周;DAY 按自然日
    // rate.setCalAcc("FIVEIN"); //FIVEIN 四舍五入 ;TRUNCATION 舍位 ;FIVEINONE 四舍五一入
    rate.setRefundFeeFlag(rl.getRefundFeeFlag()); // FALSE 不收手续费;TRUE 收手续费
    String[] productCodes = {
      rate.PRODUCTCODE1,
      rate.PRODUCTCODE2,
      rate.PRODUCTCODE3,
      rate.PRODUCTCODE4,
      rate.PRODUCTCODE5,
      rate.PRODUCTCODE6
    };
    rate.setProductCodes(productCodes);
    rateList.add(rate);
    op.setRateList(rateList);
    Map<String, Object> req = new HashMap<String, Object>();
    req.put("openProduct", op);
    req.put("channelType", ChannelMerRegist.BEST_PAY);
    req.put("isCompany", true);
    ChannelResult result = iMerChantIntoServic.openProduct(req);
    logger.info(JSON.toJSONString(result));
    if ("00".equals(result.getReCode())) {
      object.put("result", "SUCCESS");
      object.put("message", "签约成功");

    } else {
      object.put("result", "FAIL");
      object.put("message", result.getReMsg());
    }

    return object.toString();
  }
}
