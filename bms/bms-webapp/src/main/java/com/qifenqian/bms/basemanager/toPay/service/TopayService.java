package com.qifenqian.bms.basemanager.toPay.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.city.bean.CityNew;
import com.qifenqian.bms.basemanager.city.bean.ProvinceBean;
import com.qifenqian.bms.basemanager.toPay.bean.Creater;
import com.qifenqian.bms.basemanager.toPay.bean.DateBean;
import com.qifenqian.bms.basemanager.toPay.bean.ProfitBean;
import com.qifenqian.bms.basemanager.toPay.bean.TbBankCardBin;
import com.qifenqian.bms.basemanager.toPay.bean.TdPaymentBatDetailBean;
import com.qifenqian.bms.basemanager.toPay.bean.TdPaymentBatInfoBean;
import com.qifenqian.bms.basemanager.toPay.bean.ToPayChannelInfo;
import com.qifenqian.bms.basemanager.toPay.bean.TopayBatDetail;
import com.qifenqian.bms.basemanager.toPay.bean.TopaySingleDetail;
import com.qifenqian.bms.basemanager.toPay.bean.TyyBankInfo;
import com.qifenqian.bms.basemanager.toPay.dao.TopayDao;
import com.qifenqian.bms.paymentmanager.service.PaymentService;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.seven.micropay.base.domain.ChannelResult;
import com.seven.micropay.base.enums.ReStatus;
import com.seven.micropay.channel.domain.api.b2e.TransferApiReq;
import com.seven.micropay.channel.enums.ChannelCode;
import com.seven.micropay.channel.enums.PaychannelType;
import com.seven.micropay.channel.service.api.IB2eApiService;

/**
 * 代付Service
 *
 * @author hongjiagui
 */
@Service
public class TopayService {

  private static Logger logger = LoggerFactory.getLogger(TopayService.class);

  @Autowired private TopayDao topayDao;

  @Autowired private IB2eApiService iB2eService;

  @Autowired private PaymentService paymentService;

  @Value("${CHANNEL_NAME}")
  private String CHANNEL_NAME;

  @Value("${MERCHANT_CODE}")
  private String MERCHANT_CODE;

  /**
   * 获取Tyy渠道对应的银行编号信息
   *
   * @return
   */
  public List<TyyBankInfo> getTyyBankCode() {
    return topayDao.listTyyBankCode();
  }

  /** 批量插入单条代付信息 */
  public void insertSingleTopay(TopaySingleDetail list) {
    topayDao.saveSingleTopay(list);
  }

  /** 插入代付批次信息 */
  public void insertBatTopay(TopayBatDetail bean) {
    topayDao.saveBatTopay(bean);
  }

  /** 获取省份列表 */
  public List<ProvinceBean> listProvince() {
    return topayDao.listProvince();
  }
  /** 根据省份code获取城市列表 */
  public List<CityNew> listCityByProvinceCode(String provinceCode) {
    return topayDao.listCityByProvinceCode(provinceCode);
  }

  /** 批量插入单条代付信息 */
  public void addSingleTopay(TopaySingleDetail singleDetail) {
    topayDao.saveSingleTopay(singleDetail);
  }

  /** 插入代付批次信息 */
  public void addBatTopay(TopayBatDetail bean) {
    topayDao.saveBatTopay(bean);
  }

  /** 查询代付汇总信息 */
  public List<TopayBatDetail> listBatRecord(TopayBatDetail bean) {
    return topayDao.listBatRecord(bean);
  }

  /** 根据orderNo查询单笔代付信息 */
  public List<TopaySingleDetail> listSingleRecordByOrderNo(String orderNo) {
    return topayDao.listSingleRecordByOrderNo(orderNo);
  }

  /** 代付审核通过 */
  public JSONObject toPay(String orderNo) {
    // 单笔代付发送银行成功笔数
    int successCount = 0;
    // 单笔代付发送银行失败笔数
    int defeatedCount = 0;
    BigDecimal failAmt = new BigDecimal(0);
    JSONObject json = new JSONObject();
    List<TopaySingleDetail> listSingle = this.listSingleRecordByOrderNo(orderNo);

    for (TopaySingleDetail topaySingleDetail : listSingle) {
      this.updateSinglePayInfo(topaySingleDetail, "90"); // 设置此笔交易为交易异常 	
      TransferApiReq req = new TransferApiReq();
      // 批次号
      req.setOrderId(topaySingleDetail.getBatNo());
      // 收款卡号
      req.setPayeeAcct(topaySingleDetail.getRecAccountNo());
      // 收款人姓名
      req.setPayeeName(topaySingleDetail.getRecAccountName());
      // 收款银行名字
      req.setPayeeAcctBankName(topaySingleDetail.getRecCardName());
      // 收款银行编码
      req.setBankNumber(topaySingleDetail.getRecBankCode());
      // 收款银行开户省份编码
      req.setProvince(topaySingleDetail.getProvinceCode());
      // 收款银行开户城市编码
      req.setCity(topaySingleDetail.getCityCode());
      // 收款金额
      req.setAmount(topaySingleDetail.getTransAmt());
      // 渠道编码
      req.setChannelCode(ChannelCode.CCB);
      req.setPaychannelType(PaychannelType.TYY);

      // 付款商户号
      req.setInnerMerchantid(topaySingleDetail.getPaerMerchantCode());
      logger.info("单笔代付发从数据：{}", JSON.toJSONString(req, true));
      try {
        ChannelResult succ = iB2eService.transfer(req);
        logger.info(
            ">>>>批次号为"
                + topaySingleDetail.getBatNo()
                + "的单笔交易返回响应状态：["
                + succ.getReCode()
                + "]"
                + succ.getReMsg());

        if (ReStatus.PROCESSING == succ.getStatus() && "00".equals(succ.getReCode())) {
          successCount++;
          // 更新单笔表为发送银行成功（06发送银行成功）
          this.updateSinglePayInfo(topaySingleDetail, "06");
          paymentService.InsertTdPaymentAuditRecode(topaySingleDetail.getBatNo(), "财务审核通过", "00");
        } else {
          // 失败  90代表交易异常。
          defeatedCount++;
          failAmt = failAmt.add(topaySingleDetail.getTransAmt());
          paymentService.InsertTdPaymentAuditRecode(
              topaySingleDetail.getBatNo(), succ.getReMsg(), "90");
        }
      } catch (Exception e) {
        logger.error(">>>>>>>>>单笔代付异常 ：" + e.getMessage(), e);
        json.put("result", "fail");
        json.put("message", e.getMessage());
      }
    }
    json.put("result", "success");
    if (defeatedCount == listSingle.size()) {
      // 此次交易下所有订单均提交银行失败
      this.updateBatchPayInfo(orderNo, "99", failAmt, defeatedCount);
    } else {
      // 将此交易状态改为发送银行等待处理
      this.updateBatchPayInfo(orderNo, "08", failAmt, defeatedCount);
    }
    json.put("successCount", successCount + "");
    json.put("defeatedCount", defeatedCount + "");
    return json;
  }

  /** 修改单笔支付状态信息 */
  public void updateSinglePayInfo(TopaySingleDetail bean, String status) {
    bean.setTradeStatus(status);
    bean.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
    topayDao.updateSinglePayInfo(bean);
  }

  /** 修改交易汇总状态信息 */
  public void updateBatchPayInfo(
      String batNo, String status, BigDecimal failAmt, int defeatedCount) {
    TopayBatDetail bean = new TopayBatDetail();
    bean.setFailAmt(failAmt);
    bean.setFailCount(defeatedCount);
    bean.setBatNo(batNo);
    bean.setTradeStatus(status);
    bean.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
    topayDao.updateBatchPayInfo(bean);
  }
  /** 审核不通过 */
  public JSONObject auditNopass(String orderNo, String reason) {
    logger.info("<<<<<<<操作订单号为" + orderNo + "代付审核不通过,原因为:" + reason);
    JSONObject json = new JSONObject();
    List<TopaySingleDetail> listSingle = this.listSingleRecordByOrderNo(orderNo);
    try {
      for (TopaySingleDetail topaySingleDetail : listSingle) {
        this.updateSinglePayInfo(topaySingleDetail, "03"); // 审核不通过
        paymentService.InsertTdPaymentAuditRecode(topaySingleDetail.getBatNo(), reason, "04");
      }
      paymentService.InsertTdPaymentAuditRecode(orderNo, reason, "04");
      this.updateBatchPayInfo(orderNo, "03", new BigDecimal(0), 0);
      json.put("result", "success");
    } catch (Exception e) {
      logger.error("<<<<<代付审核出现异常 ，异常原因 :" + e.toString());
      json.put("result", "fail");
      json.put("message", e.toString());
    }
    return json;
  }

  public List<Creater> listCreater() {
    return topayDao.listCreater();
  }

  /** 获取省份code */
  public ProvinceBean selProvinceByName(String provinceName) {
    return topayDao.selProvinceByName(provinceName);
  }

  /** 获取城市code */
  public CityNew selCityByName(String cityName) {
    return topayDao.selCityByName(cityName);
  }

  /** 获取银行Code */
  public TyyBankInfo selBankCodeByName(String bankName) {
    return topayDao.selBankCodeByName(bankName);
  }

  /**
   * 查询商户号是否存在
   *
   * @param paerMerchantCode
   * @return
   */
  public String selInfoByMerchantCode(String paerMerchantCode) {
    return topayDao.selInfoByMerchantCode(paerMerchantCode);
  }

  public BigDecimal sumTransAmtInToday(String recAccountNo) {
    return topayDao.sumTransAmtInToday(recAccountNo);
  }

  /** 根据日期查询充值利润 */
  public ProfitBean getProfitOfRecharge(DateBean dateBean) {
    ProfitBean bean = topayDao.getProfitOfRecharge(dateBean);
    return bean;
  }

  /** 根据日期查询手续费利润 */
  public ProfitBean getProfitOfPoundage(DateBean dateBean) {
    ProfitBean bean = topayDao.getProfitOfPoundage(dateBean);
    return bean;
  }

  /** 提现 */
  public TdPaymentBatDetailBean cashWithdrawal(
      BigDecimal money, String custId, String accountNumber, String accountName) {
    logger.info(">>>>>>>开始代付收益提现Service");
    // Properties properties = PropertiesUtil.getProperties("/topayProfit.properties");
    // 从配置文件中读取银行卡号
    // String accountNumber = properties.getProperty("ACCOUNT_NUMBER");
    // 从配置文件中获取渠道名字
    String channelName = CHANNEL_NAME; // properties.getProperty("CHANNEL_NAME");
    // 从配置文件中获取银行卡号户主名字
    //		String accounName = null;
    //		try {
    //			accounName = new
    // String(properties.getProperty("ACCOUNT_NAME").getBytes("iso8859-1"),"utf-8");
    //		} catch (UnsupportedEncodingException e1) {
    //			// TODO Auto-generated catch block
    //			e1.printStackTrace();
    //		}
    // 从配置文件中获取内部户MerchantCode(MerchantCode用以区分为内部户或是其他普通商户)
    String merchantCode = MERCHANT_CODE; // properties.getProperty("MERCHANT_CODE");

    // 根据银行卡号获取其他银行信息
    TbBankCardBin tbBankCardBin = topayDao.getBankNameByBankNum(accountNumber);
    TdPaymentBatDetailBean detailBean = new TdPaymentBatDetailBean();
    // 根据渠道名字获取渠道信息
    ToPayChannelInfo channelInfo = topayDao.getChnanelInfoByChannelName(channelName);

    detailBean.setCustId(custId); // 设置custId
    detailBean.setRecAccountNo(accountNumber); // 设置收款卡号
    detailBean.setRecAccountName(accountName); // 设置收款卡号开户姓名
    detailBean.setRecBankCode(tbBankCardBin.getTyyCode()); // 设置银行代码
    detailBean.setRecCardName(tbBankCardBin.getBankName()); // 设置银行名字
    detailBean.setChannelFeeamt(
        channelInfo.getChannelTopayRate().setScale(2, BigDecimal.ROUND_HALF_UP)); // 设置付给渠道手续费
    detailBean.setSingleFeeAmt(
        channelInfo
            .getChannelTopayRate()
            .setScale(2, BigDecimal.ROUND_HALF_UP)
            .toString()
            .trim()); // 设置收取手续费
    detailBean.setTransAmt(
        (money.subtract(detailBean.getChannelFeeamt()))
            .setScale(2, BigDecimal.ROUND_HALF_UP)
            .toString()
            .trim()); // 设置金额
    detailBean.setTransAmtCount(
        (money.subtract(detailBean.getChannelFeeamt())).setScale(2, BigDecimal.ROUND_HALF_UP));
    detailBean.setMerchantCode(merchantCode);
    detailBean.setBatNo(
        new SimpleDateFormat("yyyyMMdd").format(new Date())
            + RandomStringUtils.randomNumeric(5)); // 设置单笔详细记录批次号
    detailBean.setOrderNo(
        new SimpleDateFormat("yyyyMMdd").format(new Date())
            + RandomStringUtils.randomNumeric(5)); // 批量订单表的批次号
    detailBean.setChannelOrderId(
        new SimpleDateFormat("yyyyMMdd").format(new Date())
            + RandomStringUtils.randomNumeric(8)); // 设置通道订单号
    detailBean.setRowNo(RandomStringUtils.randomNumeric(8)); // 设置序号
    detailBean.setToPayType("00"); // 交易类型为:银行卡
    detailBean.setTradeStatus("02"); // 交易类型为已确认

    try {
      logger.info(">>>>>>开始插入代付收益信息{}", detailBean);
      topayDao.insertPaymentDetail(detailBean);
      this.insertBatInfo(detailBean);
    } catch (Exception e) {
      logger.error(">>>>>>>>代付收益提现数据入库失败{}", e);
      throw e;
    }
    // 批量汇总信息插入
    return detailBean;
  }

  public void insertBatInfo(TdPaymentBatDetailBean detail) {
    TdPaymentBatInfoBean bean = new TdPaymentBatInfoBean();
    bean.setBatNo(detail.getOrderNo()); // 批次号
    bean.setPaerAcctNo(detail.getCustId()); //
    bean.setTradeStatus("02"); // 凭证待传
    bean.setSumAmt(String.valueOf(detail.getTransAmtCount()));
    bean.setSumCount(String.valueOf(1));
    bean.setToPayType(detail.getToPayType()); // 银行卡
    bean.setServiceFee(detail.getSingleFeeAmt()); // 服务费
    bean.setPaerMerchantCode(detail.getMerchantCode());
    //		String merchantCode = enterpriseDAO.getMerchantCodeByCustId(custId);
    //		TdMerchantProductInfo productInfo = enterpriseDAO.getRechargeRate(merchantCode);
    //		bean.setMerchantProductId(productInfo.getId());
    topayDao.insertBatInfo(bean);
  }

  /** 根据BatNo插入CORESN */
  public void updateCoreSn(TdPaymentBatDetailBean bean) {
    topayDao.updateCoreSn(bean);
  }

  /** 根据文件名，属性 从配置文件查出对应的字段 */
  /*
  public Object searchProperty(String path, String property) {
    	Properties properties = PropertiesUtil.getProperties(path);
    Object value = properties.getProperty(property);
    return value;
  }*/
}
