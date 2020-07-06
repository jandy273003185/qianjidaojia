package com.qifenqian.bms.paymentmanager.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoBean;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.paymentmanager.PaymentManagerController;
import com.qifenqian.bms.paymentmanager.bean.AcctSevenBussAgentpay;
import com.qifenqian.bms.paymentmanager.bean.TdAgentPaymentChildRecord;
import com.qifenqian.bms.paymentmanager.bean.TdAuditReportExport;
import com.qifenqian.bms.paymentmanager.bean.TdBankCardBin;
import com.qifenqian.bms.paymentmanager.bean.TdChannelInfo;
import com.qifenqian.bms.paymentmanager.bean.TdPayCreditAuditInfo;
import com.qifenqian.bms.paymentmanager.bean.TdPayCreditInfo;
import com.qifenqian.bms.paymentmanager.bean.TdPayInInfo;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentAccountAuditInfo;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentAuditRecode;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatDetail;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatInfo;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentFeeInfo;
import com.qifenqian.bms.paymentmanager.bean.TdRecodeExport;
import com.qifenqian.bms.paymentmanager.bean.TdReportExport;
import com.qifenqian.bms.paymentmanager.dao.PaymentManagerDao;
import com.qifenqian.bms.paymentmanager.mapper.PaymentManagerMapper;
import com.qifenqian.bms.platform.utils.GenSN;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.seven.micropay.base.domain.ChannelResp;
import com.seven.micropay.base.domain.ChannelResult;
import com.seven.micropay.base.enums.ReStatus;
import com.seven.micropay.channel.domain.api.b2e.B2eQueryReq;
import com.seven.micropay.channel.domain.api.b2e.BatchTransferApiReq;
import com.seven.micropay.channel.domain.api.b2e.TransferApiReq;
import com.seven.micropay.channel.enums.ChannelCode;
import com.seven.micropay.channel.enums.PaychannelType;
import com.seven.micropay.channel.service.api.IB2eApiService;
import com.seven.micropay.order.enums.NoticeStatus;
import com.seven.micropay.order.enums.RechargeOrderStatus;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.common.type.RequestColumnValues.MsgType;
import com.sevenpay.invoke.common.type.RequestColumnValues.RtnCode;
import com.sevenpay.invoke.transaction.bussagentpay.BussAgentpayApplyRequest;
import com.sevenpay.invoke.transaction.bussagentpay.BussAgentpayApplyResponse;
import com.sevenpay.invoke.transaction.bussagentpay.BussAgentpayRechargeRequest;
import com.sevenpay.invoke.transaction.bussagentpay.BussAgentpayRechargeResponse;
import com.sevenpay.invoke.transaction.bussagentpay.BussAgentpayVerifyRequest;
import com.sevenpay.invoke.transaction.bussagentpay.BussAgentpayVerifyResponse;
import com.sevenpay.invoke.transaction.querybuss.bean.AcctBuss;
import com.sevenpay.invoke.transaction.querybussagentpay.QueryAcctSevenBussAgentpayRequest;
import com.sevenpay.invoke.transaction.querybussagentpay.QueryAcctSevenBussAgentpayResponse;

@Service
public class PaymentService {
  private static Logger logger = LoggerFactory.getLogger(PaymentManagerController.class);

  @Autowired private PaymentManagerDao dao;

  @Autowired private PaymentManagerMapper mapper;

  @Autowired private IB2eApiService iB2eService;
  @Autowired private SevenpayCoreServiceInterface coreServiceInterface;
  
  @Value("${EXCEL_FILE_SAVE_PATH}")
  private String EXCEL_FILE_SAVE_PATH;

  /** 文件上传 * */
  public Map<String, String> tinyMerchantFileUpload(HttpServletRequest request, String batchNo) {
    logger.info("*********微商户文件上传开始*********");
    Map<String, String> result = new HashMap<String, String>();
    try {
      DiskFileItemFactory factory = new DiskFileItemFactory(); // 创建一个DiskFileItemFactory工厂
      ServletFileUpload upload = new ServletFileUpload(factory); // 创建一个文件上传解析器
      upload.setHeaderEncoding("UTF-8"); // 解决上传文件名的中文乱码
      List<FileItem> list = upload.parseRequest(request);
      InputStream inputStream = null;
      for (FileItem item : list) {
        String filename = null; //
        if (!item.isFormField()) { // 是否是表单项
          filename = item.getName();
          // String filedName = item.getFieldName();
          if (StringUtils.isEmpty(filename)) {
            continue;
          }
          /** 验证后缀名 * */
          String type = filename.substring(filename.lastIndexOf("."));
          String[] photoTypes = {".xls", ".xlsx"};
          boolean isType = false;
          for (int i = 0; i < photoTypes.length; i++) {
            if (photoTypes[i].equalsIgnoreCase(type)) {
              isType = true;
              break;
            }
          }
          if (!isType) {
            result.put("result", "FAIL");
            result.put("message", "文件类型不匹配");
            return result;
          }
          inputStream = item.getInputStream();
          String fileUploadPath = EXCEL_FILE_SAVE_PATH; //properties.getProperty("d:/data/payment"); // 服务器上传路径
          /** 根据文件名生成路径规则 * */
          filename = new SimpleDateFormat("yyyy-MM-dd") + batchNo + type;
          fileUploadPath = fileUploadPath + File.separator + batchNo;

          File saveFile = new File(fileUploadPath);
          if (!saveFile.exists()) {
            saveFile.mkdirs();
          }
          // renameFile( filename,filename+".bak"); // 如果已经存在重命名
          FileOutputStream fileOutputStream =
              new FileOutputStream(fileUploadPath + File.separator + filename);
          byte buffer[] = new byte[1024];
          int len = 0;
          while ((len = inputStream.read(buffer)) > 0) {
            fileOutputStream.write(buffer, 0, len);
          }
          /** 刷新此输出流并强制写出所有缓冲的输出字节 * */
          fileOutputStream.flush();
          inputStream.close();
          fileOutputStream.close();
          item.delete();
          result.put("filePath", fileUploadPath + File.separator + filename);
        }
      }
      result.put("result", "SUCCESS");
      result.put("message", "上传成功");

      logger.info("文件上传成功");
    } catch (Exception e) {
      logger.error("上传失败", e.getMessage());
      result.put("result", "FAIL");
      result.put("message", e.getMessage());
    }
    return result;
  }

  public Map<String, Object> fileUpload(
      @RequestParam(value = "filename") MultipartFile mFile,
      HttpServletRequest request,
      String batchNo)
      throws IOException {
    Map<String, Object> result = new HashMap<String, Object>();
    CommonsMultipartFile cf = (CommonsMultipartFile) mFile;

    // 获取文件名
    String filename = mFile.getOriginalFilename();
    /** 验证后缀名 * */
    String type = filename.substring(filename.lastIndexOf("."));
    String[] photoTypes = {".xls", ".xlsx"};
    boolean isType = false;
    boolean isExcel2003 = true;
    if (photoTypes[0].equalsIgnoreCase(type)) {
      isType = true;
    } else if (photoTypes[1].equalsIgnoreCase(type)) {
      isType = true;
      isExcel2003 = false;
    }

    if (!isType) {
      result.put("result", "FAIL");
      result.put("message", "文件类型不匹配");
      return result;
    }
    // 绝对路径
    try {
      String fileUploadPath = EXCEL_FILE_SAVE_PATH ;// properties.getProperty("EXCEL_FILE_SAVE_PATH"); // 服务器上传路径
      File file = new File(fileUploadPath);

      if (!file.exists()) {
        file.mkdirs();
      }
      filename = new SimpleDateFormat("yyyy-MM-dd").format(new Date()) + "_" + batchNo + type;
      File file1 = new File(fileUploadPath + File.separator + filename);
      cf.getFileItem().write(file1);

      result.put("filePath", fileUploadPath + File.separator + filename);
      result.put("result", "SUCCESS");
      result.put("isExcel2003", isExcel2003);
      result.put("message", "上传成功");

    } catch (Exception e) {
      e.printStackTrace();
      logger.error("上传失败", e.getMessage());
      result.put("result", "FAIL");
      result.put("message", e.getMessage());
    }
    return result;
  }

  /**
   * 根据excel里面的内容读取客户信息
   *
   * @param is 输入流
   * @param isExcel2003 excel是2003还是2007版本
   * @return
   * @throws IOException
   */
  public List<TdPaymentBatDetail> ReadExcel(String filPath, String batchNo, boolean type)
      throws IOException {
    int totalRows = 0;
    int totalCells = 0;
    List<TdPaymentBatDetail> paymentCustomerList = null;
    InputStream is = null;
    try {
      is = new FileInputStream(filPath);
      Workbook wb = null;
      // 当excel是2003时
      if (type) {
        wb = new HSSFWorkbook(is);
      } else { // 当excel是2007时
        wb = new XSSFWorkbook(is);
      }
      // 得到第一个shell
      Sheet sheet = wb.getSheetAt(0);

      // 得到Excel的行数
      totalRows = sheet.getPhysicalNumberOfRows();

      // 得到Excel的列数(前提是有行数)
      if (totalRows >= 1 && sheet.getRow(0) != null) {
        totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
      }
      paymentCustomerList = new ArrayList<TdPaymentBatDetail>();
      TdPaymentBatDetail tdPaymentBatDetail = null;
      // 循环Excel行数,从第二行开始。标题不入库
      for (int r = 1; r < totalRows; r++) {
        Row row = sheet.getRow(r);
        if (row == null) continue;
        tdPaymentBatDetail = new TdPaymentBatDetail();

        // 循环Excel的列
        for (int c = 0; c < totalCells; c++) {
          Cell cell = row.getCell(c);
          cell.setCellType(Cell.CELL_TYPE_STRING);
          if (null != cell) {

            if (c == 0) {
              tdPaymentBatDetail.setRecAccountName(cell.getStringCellValue()); // 客户名称
            } else if (c == 1) {
              tdPaymentBatDetail.setRecAccountNo(cell.getStringCellValue()); // 客户卡号
            } else if (c == 2) {
              tdPaymentBatDetail.setTransAmt(
                  new BigDecimal(cell.getStringCellValue())
                      .setScale(2, BigDecimal.ROUND_HALF_UP)
                      .toString()); // 金额
            }
          }
        }
        /** 批次号，序列号 */
        tdPaymentBatDetail.setBatNo(batchNo);
        tdPaymentBatDetail.setRowNo(batchNo + String.format("%05d", r));
        tdPaymentBatDetail.setStatus("01");
        tdPaymentBatDetail.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId()));
        paymentCustomerList.add(tdPaymentBatDetail);

        // 数据入库

      }
    } catch (Exception e) {
      logger.error("解析Excel异常：" + e.getMessage(), e);

    } finally {
      is.close();
    }
    return paymentCustomerList;
  }

  /**
   * 添加银行号 入库
   *
   * @param list
   * @return
   */
  @SuppressWarnings("unused")
  @Transactional
  public List<TdPaymentBatDetail> addPaymentList(List<TdPaymentBatDetail> list) {
    List<TdPaymentBatDetail> result = null;
    int transAmt = 0;
    List<TdBankCardBin> binList = dao.selectCardBinList();
    BigDecimal transAmtCount = BigDecimal.ZERO;
    for (int i = 0; i < list.size(); i++) {
      for (int j = 0; j < binList.size(); j++) {
        String no = null;
        try {
          no = list.get(i).getRecAccountNo().substring(0, binList.get(j).getCardBin().length());
        } catch (Exception e) {
          logger.error(">>>>>卡号错误，继续执行");
        }
        if (binList.get(j).getCardBin().equals(no)) {
          list.get(i).setRecBankCode(binList.get(j).getBankCode());
          list.get(i).setPayeeAcctBankName(binList.get(j).getBankName());
          logger.info(">>>>>银行号" + JSONObject.toJSONString(list.get(i).getRecBankCode()));
          break;
        }
      }
      // transAmt +=Integer.valueOf(list.get(i).getTransAmt());
      transAmtCount = transAmtCount.add(new BigDecimal(list.get(i).getTransAmt()));
    }
    logger.info(">>>>>>>>>>>>>>>>>>总金额：" + transAmtCount.toString());
    list.get(0).setTransAmtCount(transAmtCount);
    return list;
  }

  public Map<String, String> paymentManage(List<TdPaymentBatDetail> list, String paerAcctNo) {
    Map<String, String> result = new HashMap<String, String>();

    BatchTransferApiReq req = new BatchTransferApiReq();
    req.setPaychannelType(PaychannelType.CR);
    req.setBatchNo(list.get(0).getBatNo());
    req.setCount(list.size());

    List<TransferApiReq> reqs = new ArrayList<TransferApiReq>();
    BigDecimal transAmt = BigDecimal.ZERO;
    for (int i = 0; i < list.size(); i++) {
      TransferApiReq req1 = new TransferApiReq();
      req1.setPaychannelType(PaychannelType.CR);
      req1.setOrderId(list.get(i).getRowNo());
      req1.setPayeeAcct(list.get(i).getRecAccountNo());
      req1.setPayeeName(list.get(i).getRecAccountName());
      req1.setPayeeAcctBankName(list.get(i).getPayeeAcctBankName());
      req1.setChannelCode(ChannelCode.CCB);
      // req1.setPayeeAcctBank(list.get(i).getRecBankCode());
      req1.setAmount(new BigDecimal(list.get(i).getTransAmt()));
      transAmt = transAmt.add(new BigDecimal(list.get(i).getTransAmt()));

      list.get(i).setStatus("02"); // 审核通过
      list.get(i).setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
      reqs.add(req1);
    }
    req.setTotalAmount(transAmt);
    req.setReqs(reqs);
    try {
      ChannelResult succ = iB2eService.batchTransfer(req);
      logger.info(">>>>>>>>>>>>>批量交易返回响应状态：[" + succ.getReCode() + "]" + succ.getReMsg());
      if (ReStatus.PROCESSING == succ.getStatus() && "00".equals(succ.getReCode())) {
        dao.insertPaymentList(list);
        TdPaymentBatInfo bean = new TdPaymentBatInfo();
        bean.setBatNo(list.get(0).getBatNo());

        bean.setCreateId(String.valueOf(WebUtils.getUserInfo().getUserId()));
        bean.setBatStatus("02");
        bean.setSumAmt(String.valueOf(transAmt));
        bean.setSumCount(String.valueOf(list.size()));
        bean.setPaerAcctNo(paerAcctNo);
        bean.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
        mapper.insertBatInfo(bean);
        result.put("result", "SUCCESS");
        result.put("message", "提交银行处理成功");
      } else {
        result.put("result", "FAIL");
        result.put("message", "提交银行处理失败");
      }
    } catch (Exception e) {
      logger.error(">>>>>>>>>入库失败：" + e.getMessage(), e);
      throw new RuntimeException(e);
    }

    return result;
  }

  @Transactional
  public Map<String, String> toPay(
      List<TdPaymentBatDetail> list,
      String paerAcctNo,
      String custId,
      String batNo,
      String channelType,
      List<TdCustInfo> cust,
      String channelMerchantid) {
    Map<String, String> result = new HashMap<String, String>();

    // 修改批量代付信息表为异常
    this.updateTdPaymentBatDetail(list.get(0).getBatNo(), "90"); // 90异常

    this.updateTdPaymentBatInfo(list.get(0).getBatNo(), "90"); // 90异常

    BatchTransferApiReq req = new BatchTransferApiReq();

    if ("CR".equals(channelType)) {
      req.setPaychannelType(PaychannelType.CR);
    } else if ("ECITIC".equals(channelType)) {
      req.setPaychannelType(PaychannelType.ECITIC);
    } else if ("Z_BANK".equals(channelType)) {
      req.setPaychannelType(PaychannelType.Z_BANK);
    }

    req.setBatchNo(list.get(0).getBatNo());
    req.setCount(list.size());

    List<TransferApiReq> reqs = new ArrayList<TransferApiReq>();
    BigDecimal transAmt = BigDecimal.ZERO;
    for (int i = 0; i < list.size(); i++) {
      TransferApiReq req1 = new TransferApiReq();
      if ("CR".equals(channelType)) {
        req1.setPaychannelType(PaychannelType.CR);
      } else if ("ECITIC".equals(channelType)) {
        req1.setPaychannelType(PaychannelType.ECITIC);
      } else if ("Z_BANK".equals(channelType)) {
        req1.setPaychannelType(PaychannelType.Z_BANK);
      }
      req1.setOrderId(list.get(i).getRowNo());
      req1.setPayeeAcct(list.get(i).getRecAccountNo());
      req1.setPayeeName(list.get(i).getRecAccountName());
      req1.setPayeeAcctBankName(list.get(i).getRecCardName());
      /*
       * req1.setPayeeAcctBank("103100000026"); req1.setProvince("370000");
       * req1.setCity("004610");
       */
      req1.setPayeeAcctBank(list.get(i).getRecBankCnaps());
      req1.setProvince(list.get(i).getProvinceCode());
      req1.setCity(list.get(i).getCityCode());
      req1.setChannelCode(ChannelCode.CCB);
      // req1.setPayeeAcctBank(list.get(i).getRecBankCode());
      req1.setAmount(new BigDecimal(list.get(i).getTransAmt()));
      transAmt = transAmt.add(new BigDecimal(list.get(i).getTransAmt()));
      list.get(i).setTradeStatus("08"); // 审核通过
      list.get(i).setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
      reqs.add(req1);
    }

    // 校验可用金额
    if (null != custId && "" != custId) {
      Map<String, Object> map = checkOnwayAmt(custId, transAmt);
      String re = (String) map.get("result");
      if (re.equals("FAIL")) {
        throw new RuntimeException("交易金额不能大于代付账户可用金额");
      }
    }
    req.setTotalAmount(transAmt);
    req.setReqs(reqs);
    req.setChannelMerchantid(channelMerchantid);
    if (cust != null && cust.get(0) != null) {
      req.setInnerMerchantid(cust.get(0).getMerchantCode()); // 正确内部商户号
    }

    try {
      logger.info(">>>>>>>>>>>>>批量交易响应报文：[" + JSON.toJSONString(req, true) + "]");
      ChannelResult succ = iB2eService.batchTransfer(req);
      logger.info(">>>>>>>>>>>>>批量交易返回响应状态：[" + succ.getReCode() + "]" + succ.getReMsg());
      if (ReStatus.PROCESSING == succ.getStatus() && "00".equals(succ.getReCode())) {
        // 修改批量代付汇总详情表

        this.updateTdPaymentBatDetail(list.get(0).getBatNo(), "06"); // 06发送银行成功

        this.updateTdPaymentBatInfo(list.get(0).getBatNo(), "08"); // 08 发送银行成功
        // 代付审核记录表 插入
        this.InsertTdPaymentAuditRecode(batNo, "审核通过", "00"); // 财务审核通过

        result.put("result", "SUCCESS");
        result.put("message", "提交银行处理成功");
      } else {

        // this.InsertTdPaymentAuditRecode(batNo,"提交银行处理失败","04");//财务审核通过
        this.InsertTdPaymentAuditRecode(batNo, succ.getReMsg(), "04"); // 财务审核通过
        result.put("result", "FAIL");
        result.put("message", "提交银行处理失败,原因:" + succ.getReMsg());
      }
    } catch (Exception e) {
      logger.error(">>>>>>>>>入库失败：" + e.getMessage(), e);
      throw new RuntimeException(e);
    }

    return result;
  }

  @Transactional
  public Map<String, String> singletoPay(
      TdPaymentBatInfo td, TdCustInfo cust, String paerAcctNo, String channelMerchantid) {
    Map<String, String> result = new HashMap<String, String>();
    // 修改单笔代付信息表为异常
    this.updateTdPaymentSingleInfo(td.getBatNo(), "90"); // 90异常;

    TransferApiReq req1 = new TransferApiReq();
    if ("CR".equals(td.getChannelType())) {
      req1.setPaychannelType(PaychannelType.CR);
    } else if ("ECITIC".equals(td.getChannelType())) {
      req1.setPaychannelType(PaychannelType.ECITIC);
    } else if ("Z_BANK".equals(td.getChannelType())) {
      req1.setPaychannelType(PaychannelType.Z_BANK);
    }

    req1.setOrderId(td.getBatNo());
    req1.setPayeeAcct(td.getRecAccountNo());
    req1.setPayeeName(td.getRecAccountName());
    req1.setPayeeAcctBankName(td.getPayeeAcctBankName());
    req1.setChannelCode(ChannelCode.CCB);
    req1.setPayeeAcctBank(td.getLineNumber());
    req1.setRemark("众邦代付");
    req1.setMobileno(td.getPhone());
    req1.setProvince(td.getProvinceCode());
    req1.setCity(td.getCityCode());
    req1.setChannelMerchantid(channelMerchantid);
    /*
     * req1.setProvince("370000"); req1.setCity("004610");
     */
    // req1.setPayeeAcctBank(list.get(i).getRecBankCode());
    req1.setAmount(new BigDecimal(td.getSumAmt()));
    req1.setInnerMerchantid(cust.getMerchantCode());

    logger.info("单笔代付发从数据：{}", JSON.toJSONString(req1, true));
    try {
      ChannelResult succ = iB2eService.transfer(req1);
      logger.info(">>>>>>>>>>>>>单笔交易返回响应状态：[" + succ.getReCode() + "]" + succ.getReMsg());
      if (ReStatus.PROCESSING == succ.getStatus() && "00".equals(succ.getReCode())) {
        // 更新单笔表为发送银行成功（08交易进行中）
        this.updateTdPaymentSingleInfo(td.getBatNo(), "08"); // 08提交银行成功;
        // 插入审核表信息
        this.InsertTdPaymentAuditRecode(td.getBatNo(), "财务审核通过", "00");
        result.put("result", "SUCCESS");
        result.put("message", "提交银行处理成功");
      } else {
        // 插入审核表信息
        this.InsertTdPaymentAuditRecode(td.getBatNo(), succ.getReMsg(), "04");
        result.put("result", "FAIL");
        result.put("message", "提交银行处理失败,原因:" + succ.getReMsg());
      }

    } catch (Exception e) {
      logger.error(">>>>>>>>>入库失败：" + e.getMessage(), e);
      throw new RuntimeException(e);
    }

    return result;
  }

  @Transactional
  public String updateDetail(TdPaymentBatDetail bean) {
    String result = "FAIL";
    int tatol = dao.upPaymentBatDetail(bean);
    if (tatol > 0) {
      result = "SUCCESS";
    }
    return result;
  }

  /**
   * 查询批次商户信息
   *
   * @param batNo
   * @return
   */
  public List<TdPaymentBatDetail> selecPaymentList(String batNo) {
    return dao.selecPaymentList(batNo);
  }

  /**
   * 查询批次商户信息
   *
   * @param batNo
   * @return
   */
  @Transactional
  public Map<String, String> selectPaymentTrans(TdPaymentBatDetail bean) {
    Map<String, String> result = new HashMap<String, String>();
    B2eQueryReq req = new B2eQueryReq();
    req.setBatchNo(bean.getBatNo());
    ChannelResult resMsg = null;
    List<TdPaymentBatDetail> detail = null;
    List<TransferApiReq> apiRes = null;
    try {
      if (StringUtils.isNotBlank(bean.getRowNo())) {
        req.setOrderId(bean.getRowNo());
        resMsg = iB2eService.query(req);

        bean.setStatus(String.valueOf(resMsg.getData().get("status")));

        String reStatus = resMsg.getStatus().name();
        if ("SUCCESS".equals(reStatus)) {
          bean.setStatus("00");
        } else if ("PROCESSING".equals(reStatus)) {
          bean.setStatus("03");
        } else {
          bean.setStatus("04");
        }

        bean.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
        dao.upPaymentBatDetail(bean);
      } else {
        resMsg = iB2eService.query(req);
        detail = dao.selecPaymentList(bean.getBatNo());
        TdPaymentBatInfo batInfo = new TdPaymentBatInfo();
        batInfo.setBatNo(bean.getBatNo());
        batInfo.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));

        batInfo.setSuccAmt(String.valueOf(resMsg.getData().get("succAmt")));
        batInfo.setSuccCount(String.valueOf(resMsg.getData().get("succCount")));
        batInfo.setBatStatus(String.valueOf(resMsg.getData().get("status")));
        batInfo.setFailAmt(String.valueOf(resMsg.getData().get("failAmt")));
        batInfo.setFailCount(String.valueOf(resMsg.getData().get("failCount")));
        batInfo.setPaerAcctNo(String.valueOf(resMsg.getData().get("paerAcctNo")));
        // apiRes = (List<TransferApiReq>)resMsg.getData().get("datas");

        batInfo.setSuccAmt((String) resMsg.getData().get("succAmt"));
        batInfo.setSuccCount((String) resMsg.getData().get("succCount"));
        // 成功、部分成功、失败
        int succCount = Integer.valueOf((String) resMsg.getData().get("succCount"));
        batInfo.setBatStatus(succCount == detail.size() ? "00" : succCount == 0 ? "04" : "03");
        batInfo.setFailAmt((String) resMsg.getData().get("failAmt"));
        batInfo.setFailCount((String) resMsg.getData().get("failCount"));
        batInfo.setPaerAcctNo((String) resMsg.getData().get("paerAcctNo"));
        List<ChannelResp> respList = (List<ChannelResp>) resMsg.getData().get("respList");

        for (int i = 0; i < detail.size(); i++) {
          for (int j = 0; j < respList.size(); j++) {
            ChannelResp resp = respList.get(i);
            if (detail.get(i).getRowNo().equals(resp.getOrderId())) {

              detail.get(i).setStatus(resp.getCode());
              detail.get(i).setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
              break;
            }
          }
        }
        mapper.updateDetailBatch(detail);
        logger.info(">>>>>>>>>查询交易>>>>>>>>>>>>>>>>>>");
        mapper.upPaymentBatInfo(batInfo);
      }
      logger.info(">>>>>>>>>查询交易响应状态:[" + resMsg.getStatus().name() + "],响应信息" + resMsg.getReMsg());
    } catch (Exception e) {
      /*
       * result.put("result", "SUCCESS"); result.put("message", "查询成功");
       */
      throw new RuntimeException(e);
    }

    if (!"00".equals(resMsg.getReCode())) {
      result.put("result", "FAIL");
      result.put("message", "查询失败");
    } else {
      result.put("result", "SUCCESS");
      result.put("message", "查询成功");
    }
    return result;
  }

  public List<AcctSevenBussAgentpay> queryAcctSevenToPayBuss(AcctSevenBussAgentpay queryBean) {

    return dao.queryAcctSevenToPayBuss(queryBean);
  }

  public List<TdPaymentBatInfo> getAudingPaymentBatList(TdPaymentBatInfo queryBean) {

    return dao.getAudingPaymentBatList(queryBean);
  }

  /**
   * * 导出报表数据查询
   *
   * @param queryBean
   * @return
   */
  public List<TdAuditReportExport> exportPayment(TdPaymentBatInfo queryBean) {
    return dao.exportPayment(queryBean);
  }

  public List<TdPayCreditInfo> rechargePaymentList(TdPayCreditInfo queryBean) {

    return dao.rechargePaymentList(queryBean);
  }

  public List<TdPayCreditInfo> selRechargeInfo(String id) {

    return dao.selRechargeInfo(id);
  }

  public List<TdPaymentBatInfo> getPaymentRecodeQuery(TdPaymentBatInfo queryBean) {

    return dao.getPaymentRecodeQuery(queryBean);
  }

  public List<TdPaymentBatInfo> getPaymentReportList(TdPaymentBatInfo queryBean) {
    return dao.getPaymentReportList(queryBean);
  }

  public TdPaymentBatInfo getBatchAuditPayment(String batNo) {

    return dao.getBatchAuditPayment(batNo);
  }

  public List<TdPaymentBatDetail> getBatchAuditPaymentList(String batNo, String tradeStatus) {
    TdPaymentBatDetail batDetail = new TdPaymentBatDetail();
    batDetail.setBatNo(batNo);
    batDetail.setTradeStatus(tradeStatus);
    List<TdPaymentBatDetail> list = dao.getBatchAuditPaymentList(batDetail);
    return list;
  }

  public List<TdPaymentBatDetail> getBatchReportPaymentList(
      String batNo, String tradeStatus, String toPayType) {
    TdPaymentBatDetail batDetail = new TdPaymentBatDetail();
    batDetail.setBatNo(batNo);
    batDetail.setTradeStatus(tradeStatus);
    List<TdPaymentBatDetail> list = dao.getBatchReportPaymentList(batDetail);
    return list;
  }

  /** 单笔记录 */
  public TdPaymentBatInfo getSingleInfo(String batNo, String toPayType) {
    TdPaymentBatInfo batDetail = new TdPaymentBatInfo();
    batDetail.setBatNo(batNo);
    List<TdPaymentBatInfo> list = dao.getSingleInfo(batDetail);
    if (toPayType.equals("00")) { // 银行卡
      List<TdBankCardBin> selectCardBinList = dao.selectCardBinList();
      for (int i = 0; i < list.size(); i++) {
        for (int j = 0; j < selectCardBinList.size(); j++) {
          String recAccountNo =
              list.get(i)
                  .getRecAccountNo()
                  .substring(0, selectCardBinList.get(j).getCardBin().length());
          if (selectCardBinList.get(j).getCardBin().equals(recAccountNo)) {
            // 去查询支行信息
            TdBankCardBin bankCardBin = new TdBankCardBin();
            bankCardBin.setCardBin(recAccountNo);
            TdBankCardBin tdBankCardBin = dao.selectCardName(bankCardBin);
            String bankName = tdBankCardBin.getBankName();
            list.get(i).setPayeeAcctBankName(bankName);
            break;
          }
        }
      }
    }
    return list.get(0);
  }

  @Transactional
  public void payMentFristPass(String batNo) {

    // 代付审核记录表 插入
    this.InsertTdPaymentAuditRecode(batNo, "审核通过", "06"); // 06清洁算审核通过;
    // 修改批量代付汇总表
    this.updateTdPaymentBatInfo(batNo, "06"); // 06清洁算审核通过;
    // 修改批量代付汇总详情表
    this.updateTdPaymentBatDetail(batNo, "05"); // 08 清洁算审核未通过
  }

  @Transactional
  public void paySingleFristPass(String batNo) {
    // 代付审核记录表 插入
    this.InsertTdPaymentAuditRecode(batNo, "清洁算审核通过", "06"); // 06清洁算审核通过;
    // 修改单笔代付信息表
    this.updateTdPaymentSingleInfo(batNo, "06"); // 06清洁算审核通过;
  }

  @Transactional
  public String payNotPassAudit(TdPaymentBatInfo td, String status) throws Exception {
    /** * 掉撤销接口，解冻资金 ** */
    // 查询核心账户ID
    TdCustInfo cust = new TdCustInfo();
    cust.setEmail(td.getEmail());
    List<TdCustInfo> list = dao.selToPayCustList(cust);

    if (list != null && list.size() > 0) {
      cust = list.get(0);
    }

    // 查询代付核心流水号
    TdAgentPaymentChildRecord child = dao.getTdAgentPaymentChildRecord(td.getBatNo());

    // 查询单笔代付记录
    TdPaymentBatInfo singleTd = dao.getSingleToPayInfo(td);
    // 核心流水号
    String coreSn = "";

    if (child != null) {
      coreSn = child.getCoreSn();
    } else {
      logger.info("撤销步骤异常:核心流水号不存在");
      throw new Exception("撤销步骤异常:核心流水号不存在");
    }

    try {
      // 发核心前先修改表彰状态为撤销异常
      this.updateTdPaymentBatInfo(td.getBatNo(), "97");
      /** 调核心撤回接口 */
      // undoNucleus(cust,td.getToPayType(),td.getSumAmt(),td.getFeeAmt(),coreSn);
      ResponseMessage<BussAgentpayApplyResponse> responseMessage =
          undoNucleus(
              cust, singleTd.getToPayType(), singleTd.getSumAmt(), singleTd.getFeeAmt(), coreSn);
      TdAgentPaymentChildRecord td_child = null;
      if (responseMessage != null) {
        td_child = new TdAgentPaymentChildRecord();
        String orderId =
            new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())
                + RandomStringUtils.randomNumeric(8);
        td_child.setRecordId(orderId);
        td_child.setBatNo(td.getBatNo());
        td_child.setCoreSn(responseMessage.getMsgId());
        td_child.setCoreReturnCode(String.valueOf(responseMessage.getRtnCode()));
        td_child.setCoreReturnInfo(String.valueOf(responseMessage.getRtnResult()));
        td_child.setOperType("pay_revoke");
        if ("SUCCESS".equals(String.valueOf(responseMessage.getRtnCode()))) {
          // 代付审核记录表 插入
          this.InsertTdPaymentAuditRecode(td.getBatNo(), td.getMessage(), status);
          // 修改单笔代付信息表
          this.updateTdPaymentSingleInfo(td.getBatNo(), td.getTradeStatus());
          // 添加代付字表 -- 核心返回成功
          td_child.setStatus("00");
          dao.addTdAgentPaymentChildRecord(td_child);
        } else if ("FAILURE".equals(String.valueOf(responseMessage.getRtnCode()))) {
          // 设置单笔代付表为撤销失败
          this.updateTdPaymentSingleInfo(td.getBatNo(), "94");
          // 代付审核记录表 插入
          this.InsertTdPaymentAuditRecode(td.getBatNo(), "核心撤销失败", "94");
          // 添加代付字表 -- 核心返回失败
          td_child.setStatus("03");
          dao.addTdAgentPaymentChildRecord(td_child);
          logger.info(td.getBatNo() + "撤销核心返回数据异常" + responseMessage.getRtnInfo());
          return "FAILE";
        } else {
          // 设置单笔代付表为撤销失败
          this.updateTdPaymentSingleInfo(td.getBatNo(), "94");
          // 代付审核记录表 插入
          this.InsertTdPaymentAuditRecode(td.getBatNo(), "核心撤销失败", "94");
          // 添加代付字表 -- 异常
          td_child.setStatus("01");
          return "FAILE";
        }
      }

    } catch (Exception e) {
      logger.error("商户代付申请-撤销异常", e);
      throw e;
    }
    return "SUCCESS";
  }

  /** 撤销核心代付审核数据 */
  private ResponseMessage<BussAgentpayApplyResponse> undoNucleus(
      TdCustInfo cust, String type, String fkMoney, String serviceFee, String coreSn) {
    /** 调核心撤销代付 */
    logger.info("商户代付申请-撤销");
    RequestMessage<BussAgentpayApplyRequest> requestMessage =
        new RequestMessage<BussAgentpayApplyRequest>();
    ResponseMessage<BussAgentpayApplyResponse> responseMessage = null;

    try {
      requestMessage.setMsgType(RequestColumnValues.MsgType.BUSS_AGENTPAY_APPLY); // 代付申请
      requestMessage.setReqDatetime(new Date());
      requestMessage.setReqMsgNum(1);
      requestMessage.setReqSysId(RequestColumnValues.ReqSysId.OPER); // 来源系统的代码
      requestMessage.setReqSerialId(DatetimeUtils.datetime());

      BussAgentpayApplyRequest request_ = new BussAgentpayApplyRequest();
      {
        request_.setAcctId(cust.getDfAccId());
        request_.setBrief("商户代付申请-撤销");
        request_.setCurrCode(RequestColumnValues.CurrCode.CNY);
        request_.setCustId(cust.getCustId());
        request_.setOperateType(RequestColumnValues.BussAgentPayOperate.REVOKE);
        request_.setPayType(type); // 付款方式 pay_type ，0：付到银行卡（默认）；1：付到其代付账户
        request_.setAgentpayAmt(new BigDecimal(fkMoney)); // 代付金额
        request_.setAgentpayFeeAmt(new BigDecimal(serviceFee)); // 代付手续费
        request_.setOriginMsgId(coreSn);
      }

      requestMessage.setRequest(request_);

      logger.info("向核心发送报文" + JSONObject.toJSONString(requestMessage, true));

      responseMessage = coreServiceInterface.bussAgentpayApply(requestMessage);

      logger.info("核心返回报文" + JSONObject.toJSONString(responseMessage, true));

    } catch (Exception e) {
      logger.error("商户代付申请-撤销异常", e);
      throw e;
    }

    return responseMessage;
  }

  public Map undoTopay(TdPaymentBatInfo td) throws Exception {
    // 查询核心账户ID
    TdCustInfo cust = new TdCustInfo();
    cust.setEmail(td.getPaerAcctNo());
    List<TdCustInfo> list = dao.selToPayCustList(cust);

    if (list != null && list.size() > 0) {
      cust = list.get(0);
    }
    // 查询代付核心流水号
    TdAgentPaymentChildRecord child = dao.getTdAgentPaymentChildRecord(td.getBatNo());

    // 核心流水号
    String coreSn = "";

    Map map = null;
    try {
      // 查询核心记录流水号
      if (child != null) {
        coreSn = child.getCoreSn();
        if ("0".equals(td.getType())) { // 批量
          map = batUndo(cust, td, coreSn);

        } else { // 单笔
          map = singleUndo(cust, td, coreSn);
        }

      } else {
        logger.info("撤销步骤异常:核心流水号不存在");
        throw new Exception("撤销步骤异常:核心流水号不存在");
      }

    } catch (Exception e) {
      logger.info("撤销步骤异常:" + e.getMessage(), e);
      throw new Exception("撤销步骤异常");
    }
    return map;
  }

  public Map batUndo(TdCustInfo cust, TdPaymentBatInfo td, String coreSn) {
    Map<String, String> map = new HashMap<String, String>();
    /** 调核心撤回接口之前，修改批量表代付状态-修改为撤销异常 */
    // 查询批量汇总信息
    TdPaymentBatInfo td_ = dao.selectBatRecode(td);
    // 修改批量表代付状态-修改为撤销异常
    this.updateTdPaymentBatInfo(td.getBatNo(), "97");

    try {
      /** 调核心撤销接口 */
      ResponseMessage<BussAgentpayApplyResponse> responseMessage =
          undoNucleus(cust, td_.getToPayType(), td_.getSumAmt(), td_.getFeeAmt(), coreSn);

      TdAgentPaymentChildRecord td_child = null;
      if (responseMessage != null) {
        td_child = new TdAgentPaymentChildRecord();
        String orderId =
            new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())
                + RandomStringUtils.randomNumeric(8);
        td_child.setRecordId(orderId);
        td_child.setBatNo(td.getBatNo());
        td_child.setCoreSn(responseMessage.getMsgId());
        td_child.setCoreReturnCode(String.valueOf(responseMessage.getRtnCode()));
        td_child.setCoreReturnInfo(String.valueOf(responseMessage.getRtnResult()));
        td_child.setOperType("pay_revoke");
        if ("SUCCESS".equals(String.valueOf(responseMessage.getRtnCode()))) {
          // 设置批量代付汇总表为撤销成功
          this.updateTdPaymentBatInfo(td.getBatNo(), "07");
          // 设置批量代付明细表为撤销成功
          this.updateTdPaymentBatDetail(td.getBatNo(), "07");
          // 设置代付审核表为撤销成功
          this.InsertTdPaymentAuditRecode(td.getBatNo(), "已撤销", "07");
          // 添加代付字表 -- 核心返回成功
          td_child.setStatus("00");
          dao.addTdAgentPaymentChildRecord(td_child);
          map.put("result", "SUCCESS");
          map.put("message", "撤销成功");
        } else if ("FAILURE".equals(String.valueOf(responseMessage.getRtnCode()))) {
          // 设置批量代付汇总表为撤销失败
          this.updateTdPaymentBatInfo(td.getBatNo(), "94");
          // 代付审核记录表 插入
          this.InsertTdPaymentAuditRecode(td.getBatNo(), "核心撤销失败", "94");
          // 添加代付字表 -- 核心返回失败
          td_child.setStatus("03");
          dao.addTdAgentPaymentChildRecord(td_child);
          map.put("result", "FAILE");
          map.put("message", responseMessage.getRtnInfo());
          logger.info(td.getBatNo() + "撤销核心返回数据异常" + responseMessage.getRtnInfo());
        } else {
          // 设置批量代付汇总表为撤销失败
          this.updateTdPaymentBatInfo(td.getBatNo(), "94");
          // 代付审核记录表 插入
          this.InsertTdPaymentAuditRecode(td.getBatNo(), "核心撤销失败", "94");
          // 添加代付字表 -- 异常
          td_child.setStatus("01");
          dao.addTdAgentPaymentChildRecord(td_child);
          map.put("result", "FAILE");
          map.put("message", "核心撤销异常");
        }

      } else {
        logger.info("核心返回数据异常");
        map.put("result", "FAILE");
        map.put("message", "核心返回数据异常");
      }
    } catch (Exception e) {
      logger.info("调用核心失败");
      map.put("result", "FAILE");
      map.put("message", "调用核心失败");
    }
    return map;
  }

  public Map singleUndo(TdCustInfo cust, TdPaymentBatInfo td, String coreSn) {
    Map<String, String> map = new HashMap<String, String>();
    // 查询代付记录
    TdPaymentBatInfo singleTd = dao.getSingleToPayInfo(td);
    /** 修改核心之前，修改单笔记录（td_agent_payment_single_record）为撤销异常 */
    this.updateTdPaymentSingleInfo(td.getBatNo(), "97");

    try {
      /** 调核心撤回接口 */
      ResponseMessage<BussAgentpayApplyResponse> responseMessage =
          undoNucleus(
              cust, singleTd.getToPayType(), singleTd.getSumAmt(), singleTd.getFeeAmt(), coreSn);

      TdAgentPaymentChildRecord td_child = null;
      if (responseMessage != null) {
        td_child = new TdAgentPaymentChildRecord();
        String orderId =
            new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())
                + RandomStringUtils.randomNumeric(8);
        td_child.setRecordId(orderId);
        td_child.setBatNo(td.getBatNo());
        td_child.setCoreSn(responseMessage.getMsgId());
        td_child.setCoreReturnCode(String.valueOf(responseMessage.getRtnCode()));
        td_child.setCoreReturnInfo(String.valueOf(responseMessage.getRtnResult()));
        td_child.setOperType("pay_revoke");
        if ("SUCCESS".equals(String.valueOf(responseMessage.getRtnCode()))) {
          // 设置单笔代付表为撤销成功
          this.updateTdPaymentSingleInfo(td.getBatNo(), "07");

          this.InsertTdPaymentAuditRecode(td.getBatNo(), "撤销成功", "07");
          // 添加代付字表 -- 核心返回成功
          td_child.setStatus("00");
          dao.addTdAgentPaymentChildRecord(td_child);
          map.put("result", "SUCCESS");
          map.put("message", "撤销成功");
        } else if ("FAILURE".equals(String.valueOf(responseMessage.getRtnCode()))) {
          // 设置单笔代付表为撤销失败
          this.updateTdPaymentSingleInfo(td.getBatNo(), "94");
          // 代付审核记录表 插入
          this.InsertTdPaymentAuditRecode(td.getBatNo(), "核心撤销失败", "94");
          // 添加代付字表 -- 核心返回失败
          td_child.setStatus("03");
          dao.addTdAgentPaymentChildRecord(td_child);
          map.put("result", "FAILE");
          map.put("message", responseMessage.getRtnInfo());
          logger.info(td.getBatNo() + "撤销核心返回数据异常" + responseMessage.getRtnInfo());
        } else {
          // 设置单笔代付表为撤销失败
          this.updateTdPaymentSingleInfo(td.getBatNo(), "94");
          // 代付审核记录表 插入
          this.InsertTdPaymentAuditRecode(td.getBatNo(), "核心撤销失败", "94");
          // 添加代付字表 -- 异常
          td_child.setStatus("01");
          dao.addTdAgentPaymentChildRecord(td_child);
          map.put("result", "FAILE");
          map.put("message", "核心撤销异常");
        }

      } else {
        logger.info("核心返回数据异常");
        map.put("result", "FAILE");
        map.put("message", "核心返回数据异常");
      }
    } catch (Exception e) {
      logger.info("调用核心失败");
      map.put("result", "FAILE");
      map.put("message", "调用核心失败");
    }
    return map;
  }

  /*
   * public void singleUndo(TdCustInfo cust,TdPaymentBatInfo td,String coreSn){
   * //查询代付记录 tdPaymentSingleRecord singleTd =
   * enterpriseDAO.getSingleRecord(td.getBatNo());
   */
  /** 修改核心之前，修改单笔记录（td_agent_payment_single_record）为撤销异常 */
  /*
   * tdPaymentSingleRecord td_ = new tdPaymentSingleRecord();
   * td_.setBatNo(singleTd.getBatNo()); td_.setTradeStatus("97");
   * enterpriseDAO.updatePaymentSingleRecord(td_);
   *
   * try {
   */
  /** 调核心撤回接口 */
  /*
   * ResponseMessage<BussAgentpayApplyResponse> responseMessage =
   * undoNucleus(cust,singleTd.getToPayType(),singleTd.getSumAmt(),singleTd.
   * getServiceFee(),coreSn);
   *
   * TdAgentPaymentChildRecord td_child = null; if(responseMessage!=null){
   * td_child = new TdAgentPaymentChildRecord(); String orderId = new
   * SimpleDateFormat("yyyyMMddhhmmssSSS").format(new
   * Date())+RandomStringUtils.randomNumeric(8); td_child.setRecordId(orderId);
   * td_child.setBatNo(td.getBatNo());
   * td_child.setCoreSn(responseMessage.getMsgId());
   * td_child.setCoreReturnCode(String.valueOf(responseMessage.getRtnCode()));
   * td_child.setCoreReturnInfo(String.valueOf(responseMessage.getRtnResult()));
   * td_child.setOperType("pay_revoke");
   * if("SUCCESS".equals(String.valueOf(responseMessage.getRtnCode()))){
   * //设置单笔代付表为撤销 td_.setTradeStatus("07"); //添加代付字表 -- 核心返回成功
   * td_child.setStatus("00");
   * enterpriseDAO.insertTdAgentPaymentChildRecord(td_child); }else
   * if("FAILURE".equals(String.valueOf(responseMessage.getRtnCode()))){
   * //设置单笔代付表为撤销异常 td_.setTradeStatus("94"); //添加代付字表 -- 核心返回失败
   * td_child.setStatus("03");
   * enterpriseDAO.insertTdAgentPaymentChildRecord(td_child);
   *
   * }else{ //设置单笔代付表为撤销异常 td_.setTradeStatus("94"); //添加代付字表 -- 异常
   * td_child.setStatus("01");
   * enterpriseDAO.insertTdAgentPaymentChildRecord(td_child); } //更新单笔代付表
   * enterpriseDAO.updatePaymentSingleRecord(td_); //修改代付审核信息表状态-修改为已撤销
   * PaymentAuditRecode par = new PaymentAuditRecode();
   *
   * par.setId(new SimpleDateFormat("yyyyMMdd").format(new
   * Date())+RandomStringUtils.randomNumeric(8)); par.setBatNo(td.getBatNo());
   * par.setAuditStatus("07"); par.setAuditTime(new Date()); par.setMemo("撤销成功");
   * enterpriseDAO.insertTopayAuditRecord(par); }else{ log.info("核心返回数据异常"); } }
   * catch (Exception e) { log.info("调用核心失败"); }
   *
   * }
   *
   *
   *
   * public void batUndo(TdCustInfo cust,TdPaymentBatInfo td,String coreSn){
   */
  /** 调核心撤回接口之前，修改批量表代付状态-修改为撤销异常 */

  /*
   * //查询批量汇总信息 TdPaymentBatInfo td_ = enterpriseDAO.getBatInfoByBatNo(td);
   * //修改批量表代付状态-修改为撤销异常 TdPaymentBatDetail bat_del = new TdPaymentBatDetail();
   * bat_del.setBatNo(td_.getBatNo()); bat_del.setTradeStatus("97");
   * enterpriseDAO.updatePaymentBatInfo(bat_del);
   *
   *
   * try {
   */
  /**
   * 调核心撤销接口
   *
   * @throws Exception
   */
  /*
   * ResponseMessage<BussAgentpayApplyResponse> responseMessage =
   * undoNucleus(cust,td_.getToPayType(),td_.getSumAmt(),td_.getServiceFee(),
   * coreSn);
   *
   * TdAgentPaymentChildRecord td_child = null; if(responseMessage!=null){
   * td_child = new TdAgentPaymentChildRecord(); String orderId = new
   * SimpleDateFormat("yyyyMMddhhmmssSSS").format(new
   * Date())+RandomStringUtils.randomNumeric(8); td_child.setRecordId(orderId);
   * td_child.setBatNo(td.getBatNo());
   * td_child.setCoreSn(responseMessage.getMsgId());
   * td_child.setCoreReturnCode(String.valueOf(responseMessage.getRtnCode()));
   * td_child.setCoreReturnInfo(String.valueOf(responseMessage.getRtnResult()));
   * td_child.setOperType("pay_revoke");
   * if("SUCCESS".equals(String.valueOf(responseMessage.getRtnCode()))){
   * //设置单笔代付表为撤销 bat_del.setTradeStatus("07"); //添加代付字表 -- 核心返回成功
   * td_child.setStatus("00");
   * enterpriseDAO.insertTdAgentPaymentChildRecord(td_child);
   *
   * //查询代付明细记录 TdPaymentBatInfo bat = new TdPaymentBatInfo();
   * bat.setBatNo(td.getBatNo()); List<TdPaymentBatDetail> list =
   * enterpriseDAO.getBatDetailByBatNo(bat); for (int i = 0; i < list.size(); i++)
   * { TdPaymentBatDetail bat_ = list.get(i); TdPaymentBatDetail del = new
   * TdPaymentBatDetail(); del.setBatNo(bat_.getBatNo());
   * del.setTradeStatus("07"); //修改代付明细记录为已撤销
   * enterpriseDAO.updatePaymentBatRecord(del); } }else
   * if("FAILURE".equals(String.valueOf(responseMessage.getRtnCode()))){
   * //设置批量代付汇总表表为撤销失败 bat_del.setTradeStatus("94"); //添加代付字表 -- 核心返回失败
   * td_child.setStatus("03");
   * enterpriseDAO.insertTdAgentPaymentChildRecord(td_child); }else{
   * //设置批量代付汇总表表为撤销失败 bat_del.setTradeStatus("94"); //添加代付字表 -- 异常
   * td_child.setStatus("01");
   * enterpriseDAO.insertTdAgentPaymentChildRecord(td_child); } //更新单笔代付表
   * enterpriseDAO.updatePaymentBatInfo(bat_del); }else{ log.info("核心返回数据异常"); } }
   * catch (Exception e) { log.info("调用核心失败"); }
   *
   * }
   */

  public String payMentAudit(String id, String message, String auditFlag) throws Exception {
    String status = null;
    String result = null;
    String detailStatus = null;
    if (null != auditFlag && auditFlag.equals("Clste")) {
      auditFlag = "05";
      status = "92"; // 清结算审核未通过
      detailStatus = "08"; // 08 清洁算审核未通过
    }
    if (null != auditFlag && auditFlag.equals("Finance")) {
      auditFlag = "04"; // 04财务审核未通过
      status = "93";
      detailStatus = "09"; // 09清洁算审核未通过
    }
    if (null != id && !id.equals("")) {
      TdPaymentBatInfo info = new TdPaymentBatInfo();
      info.setBatNo(id);
      TdPaymentBatInfo batchToPayInfo = dao.getBatchToPayInfo(info);
      try {
        Map<String, Object> map =
            paymentRevoke(batchToPayInfo, message, auditFlag, status, detailStatus);
        String str = (String) map.get("result");
        if (null != str && str.equals("SUCCESS")) {
          result = "SUCCESS";
        } else {
          result = "FAILURE";
        }
      } catch (Exception e) {
        logger.error("商户代付申请-撤销异常", e);
        throw e;
      }
    }
    return result;
  }

  public void AuditRecharge(String id, String message, String auditFlag) {
    if (auditFlag.equals("Clste")) { // 清结算
      // 代付审核记录表 插入
      this.InsertTdPayCreditAuditInfo(id, message, "01", null); // 01清洁算审核未通过;
      // 修改银行凭证表
      this.updateTdPayCreditInfo(id, "02", "80"); // 02 清洁算审核未通过 充值失败
    }
    if (auditFlag.equals("Finance")) {
      // 代付审核记录表更新
      this.InsertTdPayCreditAuditInfo(id, message, "03", null); // 03财务审核未通过;
      // 修改银行凭证表
      this.updateTdPayCreditInfo(id, "04", "80"); // 04 财务审核未通过
      // 根据id查询出order信息 EXT_DATA2
      // 根据id查询出batNo,用batNo(orderId)查询是否有值，如果有则改变状态
      OrderInfoBean order = new OrderInfoBean();

      order = dao.getOrderInfoByCreditId(id);
      if (order != null) {
        order.setNotifyCount("0");
        order.setNotifyResult(NoticeStatus.FAIL.getCode());
        order.setOrderState(RechargeOrderStatus.FAIL.getCode());
        if (message == null) {
          order.setExtData2(RechargeOrderStatus.FAIL.getText());
        } else {
          order.setExtData2(message);
        }

        dao.updateOrderInfo(order);
      }
    }
  }

  public void RechargeFristPass(String id, String sumMoney) {
    // 代付审核记录表 插入
    this.InsertTdPayCreditAuditInfo(id, "清洁算审核通过", "02", sumMoney); // 02清洁算审核通过;
    // 修改单笔代付信息表
    this.updateTdPayCreditInfo(id, "03", null); // 03清洁算审核通过;
  }

  public void InsertTdPayCreditAuditInfo(
      String creditId, String message, String auditStatus, String sumMoney) {
    String id =
        new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date()) + GenSN.getRandomNum(8);
    TdPayCreditAuditInfo auditInfo = new TdPayCreditAuditInfo();
    auditInfo.setId(id);
    auditInfo.setCreditId(creditId);
    auditInfo.setAuditUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
    auditInfo.setMemo(message);
    auditInfo.setStatus(auditStatus);
    // auditInfo.setFristAuditTime(DatetimeUtils.dateFormat(new Date(), "yyyy-MM-dd
    // HH:mm:ss"));
    // auditInfo.setSecondAuditTime(DatetimeUtils.dateFormat(new Date(), "yyyy-MM-dd
    // HH:mm:ss"));
    auditInfo.setAuditAmt(sumMoney);
    dao.InsertTdPayCreditAuditInfo(auditInfo);
  }

  public void updateTdPayCreditAuditInfo(String id) {
    TdPayCreditAuditInfo auditInfo = new TdPayCreditAuditInfo();
    auditInfo.setCreditId(id);
    auditInfo.setSecondAuditTime(DatetimeUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
    dao.updateTdPayCreditAuditInfo(auditInfo);
  }

  public void updateTdPayCreditInfo(String id, String auditStatus, String payStatus) {
    TdPayCreditInfo info = new TdPayCreditInfo();
    info.setId(id);
    info.setAuditStatus(auditStatus);
    info.setPayStatus(payStatus);
    dao.updateTdPayCreditInfo(info);
    dao.updateToTdPayCreditAuditInfo(info);
  }

  public void InsertTdPaymentAuditRecode(String batNo, String message, String auditStatus) {
    TdPaymentAuditRecode recode = new TdPaymentAuditRecode();
    String id =
        new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date()) + GenSN.getRandomNum(8);
    recode.setId(id);
    recode.setBatNO(batNo);
    recode.setAuditStatus(auditStatus);
    recode.setAuditTime(DatetimeUtils.dateFormat(new Date(), "yyyy-MM-dd HH:mm:ss"));
    recode.setMemo(message);
    recode.setAuditUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
    dao.InsertTdPaymentAuditRecode(recode);
  }

  /**
   * 查询批次商户信息
   *
   * @param batNo
   * @return
   */
  public List<TdCustInfo> selToPayCustList(TdCustInfo td) {
    return dao.selToPayCustList(td);
  }

  /**
   * 查询商户信息
   *
   * @param batNo
   * @return
   */
  public TdCustInfo selToPayCust(String custId) {
    return dao.selToPayCust(custId);
  }

  /**
   * 更新代付账户信息
   *
   * @param batNo
   * @return
   */
  public void updateToPayCust(TdCustInfo cust) {
    dao.updateToPayCust(cust);
  }

  /**
   * 添加代付账户费用信息
   *
   * @param batNo
   * @return
   */
  public void insertTdPaymentFeeInfo(TdPaymentFeeInfo fee) {
    dao.insertTdPaymentFeeInfo(fee);
  }

  /**
   * 查询商户代付账户费用信息
   *
   * @param batNo
   * @return
   */
  public TdPaymentFeeInfo selTdPaymentFeeInfo(TdPaymentFeeInfo fee) {
    return dao.selTdPaymentFeeInfo(fee);
  }

  /**
   * 查询默认代付账户费用信息
   *
   * @param batNo
   * @return
   */
  public TdPayInInfo selDefaultFeeInfo(TdPayInInfo fee) {
    return dao.selDefaultFeeInfo(fee);
  }

  /**
   * 查询代付账户审核信息
   *
   * @param batNo
   * @return
   */
  public List<TdPaymentAccountAuditInfo> selToPayHistory(TdPaymentAccountAuditInfo acctinfo) {
    return dao.getToPayHistory(acctinfo);
  }

  /**
   * 添加代付账户开通审核信息
   *
   * @param batNo
   * @return
   */
  public void insertPaymentAccountAuditInfo(TdPaymentAccountAuditInfo info) {
    dao.insertPaymentAccountAuditInfo(info);
  }

  /**
   * 查询单笔代付账户信息
   *
   * @param batNo
   * @return
   */
  public TdPaymentBatInfo selSingleToPayInfo(TdPaymentBatInfo info) {
    TdPaymentBatInfo td = dao.getSingleToPayInfo(info);
    /*
     * List<TdBankCardBin> binList = dao.selectCardBinList(); for(int j = 0; j <
     * binList.size(); j++){ String recAccountNo = td.getRecAccountNo().substring(0,
     * binList.get(j).getCardBin().length());
     * if(binList.get(j).getCardBin().equals(recAccountNo)){ //去查询支行信息 TdBankCardBin
     * bankCardBin=new TdBankCardBin(); bankCardBin.setCardBin(recAccountNo);
     * TdBankCardBin tdBankCardBin = dao.selectCardName(bankCardBin); String
     * bankName = tdBankCardBin.getBankName(); td.setPayeeAcctBankName(bankName);
     * break; } }
     */

    return td;
  }

  /**
   * 查询单笔代付账户信息
   *
   * @param batNo
   * @return
   */
  public TdPaymentBatInfo getSingleToPayOfRecode(TdPaymentBatInfo info) {
    String toPayType = info.getToPayType();
    TdPaymentBatInfo td = dao.getSingleToPayInfo(info);
    /*
     * if(null!=toPayType && toPayType.equals("00")){//银行卡类型的才去查询支行信息
     * List<TdBankCardBin> binList = dao.selectCardBinList(); for(int j = 0; j <
     * binList.size(); j++){ String recAccountNo = td.getRecAccountNo().substring(0,
     * binList.get(j).getCardBin().length());
     * if(binList.get(j).getCardBin().equals(recAccountNo)){ //去查询支行信息 TdBankCardBin
     * bankCardBin=new TdBankCardBin(); bankCardBin.setCardBin(recAccountNo);
     * TdBankCardBin tdBankCardBin = dao.selectCardName(bankCardBin); String
     * bankName = tdBankCardBin.getBankName(); td.setPayeeAcctBankName(bankName);
     * break; } } }
     */
    return td;
  }

  public void updateTdPaymentBatInfo(String batNo, String tradeStatus) {
    TdPaymentBatInfo batInfo = new TdPaymentBatInfo();
    batInfo.setBatNo(batNo);
    batInfo.setTradeStatus(tradeStatus);
    batInfo.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
    dao.updateTdPaymentBatInfo(batInfo);
  }

  /** * 更新单笔信息表 * */
  public void updateTdPaymentSingleInfo(String batNo, String tradeStatus) {
    TdPaymentBatInfo batInfo = new TdPaymentBatInfo();
    batInfo.setBatNo(batNo);
    batInfo.setTradeStatus(tradeStatus);
    batInfo.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
    dao.updateTdPaymentSingleInfo(batInfo);
  }

  public void updateTdPaymentBatDetail(String batNo, String tradeStatus) {
    TdPaymentBatDetail batDetail = new TdPaymentBatDetail();
    batDetail.setBatNo(batNo);
    batDetail.setTradeStatus(tradeStatus);
    batDetail.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
    dao.updateTdPaymentBatDetail(batDetail);
  }

  public List<TdPaymentAuditRecode> getPaymentCheckHistory(String batNo) {
    return dao.getPaymentCheckHistory(batNo);
  }

  public List<TdPaymentAuditRecode> getSinglePaymentCheckHistory(String batNo) {
    return dao.getSinglePaymentCheckHistory(batNo);
  }

  /**
   * ** 批量核销
   *
   * @param batNo
   */
  public Map batVerification(String batNo) {
    Map<String, String> map = new HashMap<String, String>();
    // 查询批量代付记录
    TdPaymentBatInfo td = new TdPaymentBatInfo();
    td.setBatNo(batNo);
    td = dao.selectBatRecode(td);
    // 查询商户信息
    TdCustInfo cust = new TdCustInfo();
    cust.setEmail(td.getPaerAcctNo());
    List<TdCustInfo> list = dao.selToPayCustList(cust);
    if (list != null && list.size() > 0) {
      cust = list.get(0);
    } else {
      map.put("result", "FAIL");
      map.put("message", "商户信息异常");
      return map;
    }

    // 查询代付申请流水号
    TdAgentPaymentChildRecord child = dao.getTdAgentPaymentChildRecord(batNo);
    // 核心流水号
    String MsgId = "";
    if (child != null) {
      MsgId = child.getCoreSn();
    } else {
      map.put("result", "FAIL");
      map.put("message", "核心流水号异常");
      return map;
    }

    if ("60".equals(td.getTradeStatus()) || "80".equals(td.getTradeStatus())) {
      /** 修改核心之前，修改批量汇总记录表（td_agent_payment_bat_record）为核销销异常 */
      this.updateTdPaymentBatInfo(batNo, "96");

      /** ** 调用核心核销接口 ** */
      logger.info("商户代付核销");
      String sumAtm = "0.00";
      String fee = "0.00";

      if (td != null) {
        if (null != td.getSuccAmt() && !("".equals(td.getSuccAmt()))) {
          sumAtm = td.getSuccAmt();
        }
        // 计算批量成功手续费
        if (null != td.getFeeAmt() && !("".equals(td.getFeeAmt()))) {
          fee =
              String.valueOf(
                  new BigDecimal(td.getFeeAmt())
                      .divide(new BigDecimal(td.getSumCount()))
                      .multiply(new BigDecimal(td.getSuccCount())));
        }
      }

      RequestMessage<BussAgentpayVerifyRequest> requestMessage =
          new RequestMessage<BussAgentpayVerifyRequest>();
      ResponseMessage<BussAgentpayVerifyResponse> responseMessage = null;

      try {
        requestMessage.setMsgType(RequestColumnValues.MsgType.BUSS_AGENTPAY_VERIFY); // 代付申请
        requestMessage.setReqDatetime(new Date());
        requestMessage.setReqMsgNum(Integer.parseInt(td.getSuccCount()));
        requestMessage.setReqSysId(RequestColumnValues.ReqSysId.OPER); // 来源系统的代码
        requestMessage.setReqSerialId(DatetimeUtils.datetime());

        BussAgentpayVerifyRequest request = new BussAgentpayVerifyRequest();
        {
          request.setAcctId(cust.getDfAccId());
          request.setBrief("商户代付核销");
          request.setCurrCode(RequestColumnValues.CurrCode.CNY);
          request.setCustId(cust.getCustId());
          request.setOperateType(RequestColumnValues.BussAgentPayOperate.VERIFY);
          request.setVerifyAgentpayAmt(new BigDecimal(sumAtm)); // 核销代付金额
          request.setVerifyAgentpayFeeAmt(new BigDecimal(fee)); // 核销代付手续费
          request.setOriginMsgId(MsgId);
        }

        requestMessage.setRequest(request);

        logger.info("向核心发送报文" + JSONObject.toJSONString(requestMessage, true));

        responseMessage = coreServiceInterface.bussAgentpayVerify(requestMessage);

        logger.info("核心返回报文" + JSONObject.toJSONString(responseMessage, true));

        // 根据核心返回修改数据
        TdAgentPaymentChildRecord td_child = null;
        TdPaymentBatInfo td_ = new TdPaymentBatInfo();
        td_.setBatNo(batNo);
        if (responseMessage != null) {
          td_child = new TdAgentPaymentChildRecord();
          String orderId =
              new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())
                  + RandomStringUtils.randomNumeric(8);
          td_child.setRecordId(orderId);
          td_child.setBatNo(batNo);
          td_child.setCoreSn(responseMessage.getMsgId());
          td_child.setCoreReturnCode(String.valueOf(responseMessage.getRtnCode()));
          td_child.setCoreReturnInfo(String.valueOf(responseMessage.getRtnResult()));
          td_child.setOperType("pay_verified");
          if ("SUCCESS".equals(String.valueOf(responseMessage.getRtnCode()))) {
            // 设置批量汇总信息表为交易成功
            this.updateTdPaymentBatInfo(batNo, "00");
            // 设置批量汇总明细表为交易成功
            this.updateTdPaymentBatDetail(batNo, "00");

            // 修改代付审核信息表状态-修改为已核销
            this.InsertTdPaymentAuditRecode(batNo, "已核销", "03");
            // 添加代付字表 -- 核心返回成功
            td_child.setStatus("00");
            map.put("result", "SUCCESS");
            map.put("message", "核销成功");

          } else if ("FAILURE".equals(String.valueOf(responseMessage.getRtnCode()))) {
            // 设置批量汇总表为核销失败
            this.updateTdPaymentBatInfo(batNo, "80");
            // 设置批量汇总明细表为交易失败
            this.updateTdPaymentBatDetail(batNo, "80");
            // 添加代付字表 -- 核心返回失败
            td_child.setStatus("03");
            map.put("result", "FAIL");
            map.put("message", "核心核销失败");
          } else {
            // 设置批量汇总表为撤销失败
            this.updateTdPaymentBatInfo(batNo, "80");
            // 设置批量汇总明细表为交易失败
            this.updateTdPaymentBatDetail(batNo, "80");
            // 添加代付字表 -- 异常
            td_child.setStatus("01");
            map.put("result", "FAIL");
            map.put("message", "核心核销异常");
          }
          // 添加代付字表
          dao.addTdAgentPaymentChildRecord(td_child);
        } else {
          logger.info("核心返回数据异常");
        }

      } catch (Exception e) {
        logger.error("商户代付核销异常", e);
        throw e;
      }
    }
    return map;
  }

  /**
   * 单笔核销
   *
   * @param batNo
   */
  public Map singleVerification(String batNo) {
    Map<String, String> map = new HashMap<String, String>();
    // 查询单笔代付记录
    TdPaymentBatInfo td = new TdPaymentBatInfo();
    td.setBatNo(batNo);
    td = dao.getSingleToPayInfo(td);

    // 查询商户信息
    TdCustInfo cust = new TdCustInfo();
    cust.setEmail(td.getPaerAcctNo());
    List<TdCustInfo> list = dao.selToPayCustList(cust);
    if (list != null && list.size() > 0) {
      cust = list.get(0);
    } else {
      map.put("result", "FAIL");
      map.put("message", "商户信息异常");
      return map;
    }

    // 查询代付申请流水号
    TdAgentPaymentChildRecord child = dao.getTdAgentPaymentChildRecord(batNo);
    // 核心流水号
    String MsgId = "";
    if (child != null) {
      MsgId = child.getCoreSn();
    } else {
      map.put("result", "FAIL");
      map.put("message", "核心流水号异常");
      return map;
    }

    if ("60".equals(td.getTradeStatus()) || "80".equals(td.getTradeStatus())) {
      /** 修改核心之前，修改单笔记录（td_agent_payment_single_record）为核销销异常 */
      this.updateTdPaymentSingleInfo(batNo, "96");

      /** ** 调用核心核销接口 ** */
      logger.info("商户代付核销");
      String sumAtm = "0.00";
      String fee = "0.00";

      if (td != null) {
        if (null != td.getSumAmt() && !("".equals(td.getSumAmt()))) {
          sumAtm = td.getSumAmt();
        }
        if (null != td.getFeeAmt() && !("".equals(td.getFeeAmt()))) {
          fee = td.getFeeAmt();
        }
      }

      RequestMessage<BussAgentpayVerifyRequest> requestMessage =
          new RequestMessage<BussAgentpayVerifyRequest>();
      ResponseMessage<BussAgentpayVerifyResponse> responseMessage = null;

      try {
        requestMessage.setMsgType(RequestColumnValues.MsgType.BUSS_AGENTPAY_VERIFY); // 代付申请
        requestMessage.setReqDatetime(new Date());
        requestMessage.setReqMsgNum(1);
        requestMessage.setReqSysId(RequestColumnValues.ReqSysId.OPER); // 来源系统的代码
        requestMessage.setReqSerialId(DatetimeUtils.datetime());

        BussAgentpayVerifyRequest request = new BussAgentpayVerifyRequest();
        {
          request.setAcctId(cust.getDfAccId());
          request.setBrief("商户代付核销");
          request.setCurrCode(RequestColumnValues.CurrCode.CNY);
          request.setCustId(cust.getCustId());
          request.setOperateType(RequestColumnValues.BussAgentPayOperate.VERIFY);
          request.setVerifyAgentpayAmt(new BigDecimal(sumAtm)); // 核销代付金额
          request.setVerifyAgentpayFeeAmt(new BigDecimal(fee)); // 核销代付手续费
          request.setOriginMsgId(MsgId);
        }

        requestMessage.setRequest(request);

        logger.info("向核心发送报文" + JSONObject.toJSONString(requestMessage, true));

        responseMessage = coreServiceInterface.bussAgentpayVerify(requestMessage);

        logger.info("核心返回报文" + JSONObject.toJSONString(responseMessage, true));

        // 根据核心返回修改数据
        TdAgentPaymentChildRecord td_child = null;
        TdPaymentBatInfo td_ = new TdPaymentBatInfo();
        td_.setBatNo(batNo);
        if (responseMessage != null) {
          td_child = new TdAgentPaymentChildRecord();
          String orderId =
              new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())
                  + RandomStringUtils.randomNumeric(8);
          td_child.setRecordId(orderId);
          td_child.setBatNo(batNo);
          td_child.setCoreSn(responseMessage.getMsgId());
          td_child.setCoreReturnCode(String.valueOf(responseMessage.getRtnCode()));
          td_child.setCoreReturnInfo(String.valueOf(responseMessage.getRtnResult()));
          td_child.setOperType("pay_verified");
          if ("SUCCESS".equals(String.valueOf(responseMessage.getRtnCode()))) {
            // 设置单笔代付表为交易成功
            this.updateTdPaymentSingleInfo(batNo, "00");
            // 修改代付审核信息表状态-修改为已核销
            this.InsertTdPaymentAuditRecode(batNo, "已核销", "03");
            // 添加代付字表 -- 核心返回成功
            td_child.setStatus("00");
            map.put("result", "SUCCESS");
            map.put("message", "核销成功");

          } else if ("FAILURE".equals(String.valueOf(responseMessage.getRtnCode()))) {
            // 设置单笔代付表为核销失败
            this.updateTdPaymentSingleInfo(batNo, "80");
            // 添加代付字表 -- 核心返回失败
            td_child.setStatus("03");
            map.put("result", "FAIL");
            map.put("message", "核心核销失败");
          } else {
            // 设置单笔代付表为撤销失败
            this.updateTdPaymentSingleInfo(batNo, "80");
            // 添加代付字表 -- 异常
            td_child.setStatus("01");
            map.put("result", "FAIL");
            map.put("message", "核心核销异常");
          }
          // 添加代付字表
          dao.addTdAgentPaymentChildRecord(td_child);
        } else {
          logger.info("核心返回数据异常");
        }

      } catch (Exception e) {
        logger.error("商户代付核销异常", e);
        throw e;
      }
    }

    return map;
  }

  /** 代付充值财务审核通过 */
  public void rechargeAuditSecodePass(HttpServletRequest request, TdPayCreditInfo creditInfo) {

    ResponseMessage<BussAgentpayRechargeResponse> resultMassage = null;
    String accountNo = creditInfo.getRecAccountNo();
    String id = creditInfo.getId();
    // 发往核心之前修改为异常
    this.updateTdPayCreditInfo(id, "05", "60"); // 交易状态为异常

    // 根据id查询出batNo,用batNo(orderId)查询是否有值，如果有则改变状态
    OrderInfoBean order = new OrderInfoBean();
    order = dao.getOrderInfoByCreditId(id);
    if (order != null) {
      order.setNotifyCount("0");
      order.setNotifyResult(NoticeStatus.FAIL.getCode());
      order.setOrderState(RechargeOrderStatus.SUCCESS.getCode());
      order.setExtData2(RechargeOrderStatus.SUCCESS.getText());
      dao.updateOrderInfo(order);
    }

    this.updateTdPayCreditAuditInfo(id);
    // 查询商户信息
    TdCustInfo cust = new TdCustInfo();
    cust.setEmail(accountNo);
    cust.setMerchantCode(creditInfo.getRecMerchantNo());
    List<TdCustInfo> list = dao.selRechargeCustList(cust);
    if (list != null && list.size() > 0) {
      cust = list.get(0);
    }

    resultMassage = rechargeNucleus(creditInfo, cust);
    // resultMassage = submitRecharge(creditInfo,cust);//发往核心
    if (null != resultMassage) {
      TdAgentPaymentChildRecord childRecord = new TdAgentPaymentChildRecord();
      String orderId =
          new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())
              + RandomStringUtils.randomNumeric(8);
      childRecord.setRecordId(orderId);
      childRecord.setBatNo(creditInfo.getBatNo());
      if ("SUCCESS".equals(String.valueOf(resultMassage.getRtnCode()))) {
        childRecord.setCoreSn(resultMassage.getMsgId());
        childRecord.setCoreReturnCode(String.valueOf(resultMassage.getRtnCode()));
        childRecord.setCoreReturnInfo(String.valueOf(resultMassage.getRtnResult()));
        childRecord.setOperType("charge_apply"); // 充值申请
        childRecord.setStatus("00"); // 成功
        dao.addTdAgentPaymentChildRecord(childRecord); // 插入记录核心表
        // 修改银行凭证表
        this.updateTdPayCreditInfo(id, "05", "00"); // 充值成功

        // 调用充值接口
        //			ResponseMessage<BussAgentpayRechargeResponse> responseMessage =
        // rechargeNucleus(creditInfo,cust,tdPaymentBatInfo);
        /*
         * if(null != responseMessage){ logger.info("调用充值核心成功"); }else{
         * logger.info("调用充值核心失败"); }
         */

      } else {
        // 失败
        childRecord.setCoreSn(resultMassage.getMsgId());
        childRecord.setCoreReturnCode(String.valueOf(resultMassage.getRtnCode()));
        childRecord.setCoreReturnInfo(String.valueOf(resultMassage.getRtnResult()));
        childRecord.setOperType("charge_apply");
        childRecord.setStatus("03"); // 失败
        dao.addTdAgentPaymentChildRecord(childRecord); // 插入记录核心子表
        // 修改银行凭证表
        this.updateTdPayCreditInfo(id, "05", "80"); // 充值失败
      }
    } else {
      logger.info("申请充值核心返回数据异常");
    }
  }

  // 代付充值撤销
  public void rechargeRevoke(HttpServletRequest request, TdPayCreditInfo creditInfo) {

    ResponseMessage<BussAgentpayRechargeResponse> resultMassage = null;
    String accountNo = creditInfo.getRecAccountNo();
    String id = creditInfo.getId();
    String MsgId = null;
    // 发往核心之前修改为撤销异常
    this.updateTdPayCreditInfo(creditInfo.getId(), null, "91"); // 91:撤销异常

    // 查询商户信息
    TdCustInfo cust = new TdCustInfo();
    cust.setEmail(accountNo);
    List<TdCustInfo> list = dao.selRechargeCustList(cust);
    if (list != null && list.size() > 0) {
      cust = list.get(0);
    }
    // 查询核心流水号
    TdAgentPaymentChildRecord record = dao.selTdAgentPaymentChildRecord(creditInfo.getBatNo());
    if (record != null) {
      MsgId = record.getCoreSn();
    }

    resultMassage = submitRevoke(creditInfo, cust, MsgId); // 发往核心
    if (null != resultMassage) {
      TdAgentPaymentChildRecord childRecord = new TdAgentPaymentChildRecord();
      String orderId =
          new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())
              + RandomStringUtils.randomNumeric(8);
      childRecord.setRecordId(orderId);
      childRecord.setBatNo(creditInfo.getBatNo());
      childRecord.setCoreSn(resultMassage.getMsgId());
      childRecord.setCoreReturnCode(String.valueOf(resultMassage.getRtnCode()));
      childRecord.setCoreReturnInfo(String.valueOf(resultMassage.getRtnResult()));
      childRecord.setOperType("charge_revoke"); // 充值撤销
      if ("SUCCESS".equals(String.valueOf(resultMassage.getRtnCode()))) {
        childRecord.setStatus("00"); // 已撤销
        dao.addTdAgentPaymentChildRecord(childRecord); // 插入记录核心表
        // 修改银行凭证表
        this.updateTdPayCreditInfo(id, null, "90"); // 已撤销

      } else if ("FAILURE".equals(String.valueOf(resultMassage.getRtnCode()))) {
        // 失败
        childRecord.setStatus("03"); // 充值撤销失败
        dao.addTdAgentPaymentChildRecord(childRecord); // 插入记录核心子表
        // 修改银行凭证表
        this.updateTdPayCreditInfo(id, null, "92"); // 撤销失败
      } else {
        // 异常
        childRecord.setStatus("01"); // 充值撤销失败
        dao.addTdAgentPaymentChildRecord(childRecord); // 插入记录核心子表
        // 修改银行凭证表
        this.updateTdPayCreditInfo(id, null, "92"); // 撤销失败
      }
    } else {

      logger.info("申请充值核心返回数据异常");
    }
  }

  // 批量代付撤销
  public Map<String, Object> paymentRevoke(
      TdPaymentBatInfo batchToPayInfo,
      String message,
      String auditFlag,
      String status,
      String detailStatus)
      throws Exception {
    Map<String, Object> map = new HashMap<>();

    ResponseMessage<BussAgentpayApplyResponse> resultMassage = null;
    String accountNo = batchToPayInfo.getPaerAcctNo();
    String batNo = batchToPayInfo.getBatNo();
    String MsgId = null;
    // 发往核心之前修改为撤销异常
    if (null != batNo && !batNo.equals("")) {
      this.updateTdPaymentBatInfo(batNo, "97"); // 97 撤销异常；
    }

    // 查询商户信息
    TdCustInfo cust = new TdCustInfo();
    cust.setEmail(accountNo);
    List<TdCustInfo> list = dao.selRechargeCustList(cust);
    if (list != null && list.size() > 0) {
      cust = list.get(0);
    }
    // 查询核心流水号
    TdAgentPaymentChildRecord record = dao.getTdAgentPaymentChildRecord(batchToPayInfo.getBatNo());
    if (record != null) {
      MsgId = record.getCoreSn();
    } else {
      logger.info("撤销步骤异常:核心流水号不存在");
      throw new Exception("撤销步骤异常:核心流水号不存在");
    }

    try {
      resultMassage =
          undoNucleus(cust, "0", batchToPayInfo.getSumAmt(), batchToPayInfo.getFeeAmt(), MsgId);

      if (null != resultMassage) {
        TdAgentPaymentChildRecord childRecord = new TdAgentPaymentChildRecord();
        String orderId =
            new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())
                + RandomStringUtils.randomNumeric(8);
        childRecord.setRecordId(orderId);
        childRecord.setBatNo(batchToPayInfo.getBatNo());
        childRecord.setCoreSn(resultMassage.getMsgId());
        childRecord.setCoreReturnCode(String.valueOf(resultMassage.getRtnCode()));
        childRecord.setCoreReturnInfo(String.valueOf(resultMassage.getRtnResult()));
        childRecord.setOperType("pay_revoke"); // 代付撤销
        if ("SUCCESS".equals(String.valueOf(resultMassage.getRtnCode()))) {
          childRecord.setStatus("00"); // 已撤销
          dao.addTdAgentPaymentChildRecord(childRecord); // 插入记录核心表
          // 批量汇总表
          this.updateTdPaymentBatInfo(batchToPayInfo.getBatNo(), status);
          this.InsertTdPaymentAuditRecode(batchToPayInfo.getBatNo(), message, auditFlag);
          // 批量详情
          this.updateTdPaymentBatDetail(batchToPayInfo.getBatNo(), detailStatus);
          map.put("result", "SUCCESS");

        } else if ("FAILURE".equals(String.valueOf(resultMassage.getRtnCode()))) {
          // 失败
          childRecord.setStatus("03"); // 充值撤销失败
          dao.addTdAgentPaymentChildRecord(childRecord); // 插入记录核心子表
          // 批量汇总表
          this.updateTdPaymentBatInfo(batchToPayInfo.getBatNo(), "94"); // 撤销失败
          this.InsertTdPaymentAuditRecode(batchToPayInfo.getBatNo(), "核心撤销失败", "94");

          this.updateTdPaymentBatDetail(batchToPayInfo.getBatNo(), "94"); // 撤销失败
          map.put("result", "FAILURE");
          logger.info(batchToPayInfo.getBatNo() + "撤销核心返回数据异常" + resultMassage.getRtnInfo());
        } else {
          // 异常
          childRecord.setStatus("01"); // 代付撤销失败
          dao.addTdAgentPaymentChildRecord(childRecord); // 插入记录核心子表
          // 批量汇总表
          this.InsertTdPaymentAuditRecode(batchToPayInfo.getBatNo(), "核心撤销异常", "94");

          this.updateTdPaymentBatInfo(batchToPayInfo.getBatNo(), "97"); // 撤销异常

          this.updateTdPaymentBatDetail(batchToPayInfo.getBatNo(), "94"); // 撤销异常
          map.put("result", "EXCEPTION");
        }
      } else {
        logger.info("申请代付撤销核心返回数据异常");
      }
    } catch (Exception e) {
      logger.error("商户代付申请-撤销异常", e);
      throw e;
    }
    return map;
  }

  private ResponseMessage<BussAgentpayRechargeResponse> submitRevoke(
      TdPayCreditInfo creditInfo, TdCustInfo cust, String MsgId) {
    RequestMessage<BussAgentpayRechargeRequest> requestMessage =
        new RequestMessage<BussAgentpayRechargeRequest>();
    ResponseMessage<BussAgentpayRechargeResponse> responseMessage = null;

    try {
      requestMessage.setMsgType(RequestColumnValues.MsgType.BUSS_AGENTPAY_RECHARGE); // 代付申请
      requestMessage.setReqDatetime(new Date());
      requestMessage.setReqMsgNum(1);
      requestMessage.setReqSysId(RequestColumnValues.ReqSysId.OPER); // 来源系统的代码
      requestMessage.setReqSerialId(DatetimeUtils.datetime());

      BussAgentpayRechargeRequest request = new BussAgentpayRechargeRequest();
      {
        request.setCustId(cust.getCustId());
        request.setAcctId(cust.getDfAccId()); // 代付账号
        request.setOperateType(
            RequestColumnValues.BussAgentPayRechargeOperate.RECHARGE_REVOKE); // 撤销
        request.setCurrCode(RequestColumnValues.CurrCode.CNY);
        request.setBrief("商户代付账户充值-撤销");
        request.setChargeAmt(new BigDecimal(creditInfo.getAuditAmt())); // 充值金额
        request.setRechargeMode(
            RequestColumnValues.RechargeMode.SUBSTITUTED); // SUBSTITUTED 人工充值,DIRECT 直充
        request.setChannel(RequestColumnValues.RechargeChannel.ABC); // 充值渠道(银行代码)
        request.setChannelMerchant(""); //
        request.setChannelSerialSeq(creditInfo.getId()); // 银行凭证号，不可为空，且只能使用一次
        request.setFee(new BigDecimal("0"));
        request.setBrief("代付账户充值-撤销");
        request.setOriginMsgId(MsgId); // 撤销充值交易时填
        request.setReserve(""); // 预留字段
      }

      requestMessage.setRequest(request);
      logger.info("向核心发送报文" + JSONObject.toJSONString(requestMessage, true));
      responseMessage = coreServiceInterface.bussAgentpayRecharge(requestMessage);
      logger.info("核心返回报文" + JSONObject.toJSONString(responseMessage, true));
    } catch (Exception e) {
      logger.error("核心代付账户充值-撤销异常", e);
      throw e;
    }
    return responseMessage;
  }

  private ResponseMessage<BussAgentpayRechargeResponse> submitRecharge(
      TdPayCreditInfo creditInfo, TdCustInfo cust) {
    RequestMessage<BussAgentpayRechargeRequest> requestMessage =
        new RequestMessage<BussAgentpayRechargeRequest>();
    ResponseMessage<BussAgentpayRechargeResponse> responseMessage = null;

    try {
      requestMessage.setMsgType(RequestColumnValues.MsgType.BUSS_AGENTPAY_RECHARGE); // 代付申请
      requestMessage.setReqDatetime(new Date());
      requestMessage.setReqMsgNum(1);
      requestMessage.setReqSysId(RequestColumnValues.ReqSysId.OPER); // 来源系统的代码
      requestMessage.setReqSerialId(DatetimeUtils.datetime());

      BussAgentpayRechargeRequest request = new BussAgentpayRechargeRequest();
      {
        request.setCustId(cust.getCustId());
        request.setAcctId(cust.getDfAccId()); // 代付账号
        request.setOperateType(RequestColumnValues.BussAgentPayRechargeOperate.RECHARGE); // 充值
        request.setCurrCode(RequestColumnValues.CurrCode.CNY);
        request.setBrief("商户代付账户充值");
        request.setChargeAmt(new BigDecimal(creditInfo.getAuditAmt())); // 充值金额
        request.setRechargeMode(
            RequestColumnValues.RechargeMode.SUBSTITUTED); // SUBSTITUTED 人工充值,DIRECT 直充
        request.setChannel(RequestColumnValues.RechargeChannel.ABC); // 充值渠道(银行代码)
        request.setChannelMerchant(""); //
        request.setChannelSerialSeq(creditInfo.getId()); // 银行凭证号，不可为空，且只能使用一次
        request.setFee(new BigDecimal("0"));
        request.setBrief("代付账户充值");
        request.setOriginMsgId(""); // 撤销充值交易时填
        request.setReserve(""); // 预留字段
      }

      requestMessage.setRequest(request);
      logger.info("向核心发送报文" + JSONObject.toJSONString(requestMessage, true));
      responseMessage = coreServiceInterface.bussAgentpayRecharge(requestMessage);
      logger.info("核心返回报文" + JSONObject.toJSONString(responseMessage, true));

    } catch (Exception e) {
      logger.error("核心代付账户充值-撤销异常", e);
      throw e;
    }
    return responseMessage;
  }

  public List<TdRecodeExport> getPaymentRecodeQueryExcel(TdPaymentBatInfo batInfo) {
    return dao.getPaymentRecodeQueryExcel(batInfo);
  }

  public List<TdReportExport> getReportExport(TdPaymentBatInfo batInfo) {

    List<TdReportExport> list = dao.getReportExport(batInfo);
    for (int i = 0; i < list.size(); i++) {
      TdReportExport export = list.get(i);
      if (null != export.getSuccCount()) {
        if (export.getSuccCount().equals("000")) {
          if (export.getTradeStatus().equals("00")) {
            export.setSuccCount("1");
          } else if (export.getTradeStatus().equals("99")) {
            export.setSuccCount("0");
          } else {
            export.setSuccCount("0");
          }
        }
      }
      if (null != export.getFailCount()) {
        if (export.getFailCount().equals("failCount")) {
          if (export.getTradeStatus().equals("00")) {
            export.setFailCount("0");
          } else if (export.getTradeStatus().equals("99")) {
            export.setFailCount("1");
          } else {
            export.setFailCount("0");
          }
        }
      }
      if (null != export.getSuccAmt()) {
        if (export.getSuccAmt().equals("succAmt")) {
          if (export.getTradeStatus().equals("00")) {
            export.setSuccAmt(export.getSuccAmt());
          } else if (export.getTradeStatus().equals("99")) {
            export.setSuccAmt("0");
          } else {
            export.setSuccAmt("0");
          }
        }
      }
      if (null != export.getFailAmt()) {
        if (export.getFailAmt().equals("failAmt")) {
          if (export.getTradeStatus().equals("00")) {
            export.setFailAmt("0.00");
          } else if (export.getTradeStatus().equals("99")) {
            export.setFailAmt(export.getSumAmt());
          } else {
            export.setSuccAmt(export.getSumAmt());
          }
        }
      }
      if (null != export.getTradeStatus()) {
        if (export.getTradeStatus().equals("00")) {
          export.setTradeStatus("付款成功");
        } else if (export.getTradeStatus().equals("99")) {
          export.setTradeStatus("付款失败");
        }
      }
    }
    return list;
  }

  public String findBankScanPath(String custId, String certifyType) {
    TdPayCreditInfo creditScan = new TdPayCreditInfo();
    creditScan.setId(custId);
    creditScan.setPayStatus(certifyType);
    return dao.findBankScanPath(creditScan);
  }

  public String getBankScanPathById(String custId, String certifyType) {
    TdPayCreditInfo creditScan = new TdPayCreditInfo();
    creditScan.setBatNo(custId);
    creditScan.setPayStatus(certifyType);
    return dao.findBankScanPath(creditScan);
  }

  public String selBankScanPathById(String id) {
    TdPayCreditInfo creditScan = new TdPayCreditInfo();
    creditScan.setId(id);
    return dao.findBankScanPath(creditScan);
  }

  public Map<String, Object> checkOnwayAmt(String custId, BigDecimal transAmt) {
    Map<String, Object> map = new HashMap<>();
    BigDecimal onwayAmt = BigDecimal.ZERO;

    // 调用核心，开通代付账户
    try {
      RequestMessage<QueryAcctSevenBussAgentpayRequest> requestMessage =
          new RequestMessage<QueryAcctSevenBussAgentpayRequest>();
      {
        requestMessage.setMsgType(MsgType.ACCT_BUSS_AGENTPAY_QUERY);
        requestMessage.setReqSysId(RequestColumnValues.ReqSysId.OPER);
        requestMessage.setReqSerialId(DatetimeUtils.datetime());
        requestMessage.setReqMsgNum(1);
        requestMessage.setReqDatetime(new Date());

        QueryAcctSevenBussAgentpayRequest request_ = new QueryAcctSevenBussAgentpayRequest();
        {
          request_.setCustId(custId);
        }

        requestMessage.setRequest(request_);
      }
      ResponseMessage<QueryAcctSevenBussAgentpayResponse> responseMessage =
          coreServiceInterface.queryAcctSevenBussAgentpay(requestMessage);
      logger.info("==========>>response:" + JSONObject.toJSONString(responseMessage, true));

      if ("SUCCESS".equals(String.valueOf(responseMessage.getRtnCode()))) {
        AcctBuss query = responseMessage.getResponse().getAcctBuss();
        onwayAmt = query.getOnwayAmt();
        if (transAmt.compareTo(onwayAmt) == 1) {
          map.put("result", "FAIL");
          map.put("message", "交易金额不能大于代付账户可用金额");
        } else {
          map.put("result", "SUCCESS");
        }
      } else {
        map.put("message", "核心返回余额异常");
      }

    } catch (Exception e) {
      logger.info("查询代付账户余额异常" + e.getMessage(), e);
    }
    return map;
  }

  public List<TdPayCreditAuditInfo> getRechargeCheckHistory(String creditId) {
    return dao.getRechargeCheckHistory(creditId);
  }

  /** 提交核心代付充值金额数据 */
  private ResponseMessage<BussAgentpayRechargeResponse> rechargeNucleus(
      TdPayCreditInfo creditInfo, TdCustInfo cust) {
    RequestMessage<BussAgentpayRechargeRequest> requestMessage =
        new RequestMessage<BussAgentpayRechargeRequest>();
    ResponseMessage<BussAgentpayRechargeResponse> responseMessage = null;
    TdChannelInfo channelInfo = dao.getChannelInfoByMerchantCode(cust.getMerchantCode());
    String accId = queryAccId(cust.getCustId(), channelInfo.getChannelName());
    try {
      // 调充值接口,充值金额
      {
        requestMessage.setMsgType(MsgType.BUSS_AGENTPAY_RECHARGE);
        requestMessage.setReqSysId(RequestColumnValues.ReqSysId.OPER);
        requestMessage.setReqSerialId(DatetimeUtils.datetime());
        requestMessage.setReqMsgNum(1);
        requestMessage.setReqDatetime(new Date());

        //    			String acctId = queryBalance(cust);
        // String fee = dao.selectRechargeRate(cust.getMerchantCode());
        // DecimalFormat format = new DecimalFormat("0.00");
        // 收取商户的手续费
        // String feeAmt = format.format(creditInfo.getRechargeFeeamt());

        Map<String, Object> merProMap = dao.selectMerProduct(cust.getMerchantCode(), "PROXY_PAY");
        if (null == merProMap) {
          logger.info("商户未开通产品");
        }
        /**
         * BigDecimal channelRechargeRate = merProMap.get("channel_recharge_rate")==null?new
         * BigDecimal(0):(BigDecimal) merProMap.get("channel_recharge_rate"); DecimalFormat format1
         * = new DecimalFormat("0.00"); String channelRechargeRateAmt = null;
         * if("fixed".equalsIgnoreCase(merProMap.get("channel_recharge_type").toString().trim())) {
         * channelRechargeRateAmt = format1.format(channelRechargeRate);
         *
         * <p>}else
         * if("rate".equalsIgnoreCase(merProMap.get("channel_recharge_type").toString().trim())) {
         * channelRechargeRateAmt = format1.format(channelRechargeRate.multiply(new
         * BigDecimal(creditInfo.getAuditAmt())).setScale(2,BigDecimal.ROUND_UP)); }
         *
         * <p>BigDecimal rechargeRate = merProMap.get("recharge_rate")==null?new
         * BigDecimal(0):(BigDecimal) merProMap.get("recharge_rate");
         */
        BussAgentpayRechargeRequest request = new BussAgentpayRechargeRequest();
        {
          //
          request.setCustId(cust.getCustId());
          request.setAcctId(accId); // 代付账号 //DAIFUGOTO
          request.setOperateType(RequestColumnValues.BussAgentPayRechargeOperate.RECHARGE); // 充值
          request.setCurrCode(RequestColumnValues.CurrCode.CNY);
          request.setBrief("商户代付账户充值");
          request.setChargeAmt(new BigDecimal(creditInfo.getAuditAmt())); // 充值金额
          request.setFee(creditInfo.getRechargeFeeamt());
          request.setChannelFee(creditInfo.getChannelRechargeFeeamt());
          request.setRechargeMode(
              RequestColumnValues.RechargeMode.SUBSTITUTED); // SUBSTITUTED 人工充值,DIRECT 直充
          request.setChannel(RequestColumnValues.RechargeChannel.ABC); // 充值渠道(银行代码)
          request.setChannelMerchant(""); //
          request.setChannelSerialSeq(creditInfo.getId()); // 银行凭证号，不可为空，且只能使用一次
          request.setBrief("代付账户充值");
          request.setReserve(""); // 预留字段
          request.setBatchNo("");
          if (null == creditInfo.getBatNo()) {
            request.setBizOrderNo("");
          } else {
            request.setBizOrderNo(creditInfo.getBatNo());
          }

          request.setChannelCode(channelInfo.getChannelName().toUpperCase());
        }
        requestMessage.setRequest(request);
      }

      logger.info("向核心发送报文" + JSONObject.toJSONString(requestMessage, true));

      responseMessage = coreServiceInterface.bussAgentDFRecharge(requestMessage);

      logger.info("核心返回报文" + JSONObject.toJSONString(responseMessage, true));

    } catch (Exception e) {
      logger.error("核心结算申请异常", e);
      throw e;
    }

    return responseMessage;
  }

  public TdCustInfo selCustInfoByEmail(String email) {
    // TODO Auto-generated method stub
    return dao.selCustInfoByEmail(email);
  }

  /** 通过custId和渠道名字获取商户accId * */
  public String queryAccId(String custId, String channelName) {

    try {
      RequestMessage<QueryAcctSevenBussAgentpayRequest> requestMessage =
          new RequestMessage<QueryAcctSevenBussAgentpayRequest>();

      {
        requestMessage.setMsgType(MsgType.ACCT_BUSS_AGENTPAY_QUERY);
        requestMessage.setReqSysId(RequestColumnValues.ReqSysId.OPER);
        requestMessage.setReqSerialId(DatetimeUtils.datetime());
        requestMessage.setReqMsgNum(1);
        requestMessage.setReqDatetime(new Date());

        QueryAcctSevenBussAgentpayRequest request_ = new QueryAcctSevenBussAgentpayRequest();
        {
          request_.setCustId(custId);
          request_.setChannelCode(channelName.toUpperCase());
        }

        requestMessage.setRequest(request_);
      }

      ResponseMessage<QueryAcctSevenBussAgentpayResponse> responseMessage =
          coreServiceInterface.queryAcctSevenBussAgentpay(requestMessage);
      if (RtnCode.SUCCESS.equals(responseMessage.getRtnCode())
          && responseMessage.getResponse() != null) {
        return responseMessage.getResponse().getAcctBuss().getAcctId();
      }
      logger.info("==========>>response:" + JSONObject.toJSONString(responseMessage, true));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  public List<TdChannelInfo> getMerchantChannelList(TdChannelInfo queryBean) {
    // TODO Auto-generated method stub
    return dao.selMerchantChannelList(queryBean);
  }
}
