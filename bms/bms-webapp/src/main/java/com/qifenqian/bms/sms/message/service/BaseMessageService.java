package com.qifenqian.bms.sms.message.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.accounting.utils.DictionaryUtils;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.qifenqian.bms.sms.message.bean.BaseMessage;
import com.qifenqian.bms.sms.message.mapper.BaseMessageMapper;
import com.qifenqian.bms.sms.thread.BaseMessageThreadPool;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageBean;
import com.sevenpay.plugin.message.bean.MessageColumnValues;

@Service
public class BaseMessageService {

  // 支持的文件类型
  String[] errorType = {".xls"};

  public static final String MESSAGE_FILENAME = "message-{yyyyMMddHHmmssSSS}.xls";

  private static Logger logger = LoggerFactory.getLogger(BaseMessageService.class);

  private static final String SUCCESS = "SUCCESS";

  private static final String FAILURE = "FAILURE";

  private static final String EXCEPTION = "EXCEPTION";

  private static final String ALL_SHEET = "ALL_SHEET";

  private static final String FIRST_SHEET = "FIRST_SHEET";

  @Autowired private BaseMessageMapper baseMessageMapper;

  @Autowired private CommonService commonService;
  @Value("${AGRICULTURAL_BANK_SMS_PATH}")
  private String AGRICULTURAL_BANK_SMS_PATH;

  /**
   * * 上传并保存数据
   *
   * @param request
   * @param response
   * @throws FileNotFoundException
   * @throws IOException
   */
  public JSONObject fileUpload(HttpServletRequest request, HttpServletResponse response)
      throws FileNotFoundException, IOException {
    JSONObject json = new JSONObject();
    FileOutputStream out = null;
    InputStream in = null;
    String createDate = DateFormatUtils.format(new Date(), "yyyyMMdd");
    String nowShortDatetime = DateFormatUtils.format(new Date(), "yyyyMMddHHmmss");
    try {
      request.setCharacterEncoding("UTF-8");
      response.setCharacterEncoding("UTF-8");
      DiskFileItemFactory factory = new DiskFileItemFactory();
      ServletFileUpload upload = new ServletFileUpload(factory);
      upload.setSizeMax(40 * 1024 * 1024);

      List<FileItem> list = upload.parseRequest(request);

      FileItem item = list.get(0);
      String regExp = ".+\\\\(.+)$";
      Pattern p = Pattern.compile(regExp);
      String name = item.getName();
      Matcher m = p.matcher(name);
      if (m.find()) {
        boolean flag = false;
        for (int temp = 0; temp < errorType.length; temp++) {
          if (m.group(1).endsWith(errorType[temp])) {
            flag = true;
          }
        }
        if (!flag) {
          json.put("result", "FALIE");
          json.put("message", "文件格式不正确");
        }
      }

      String sheetIndex = item.getFieldName();

      String fileName = MESSAGE_FILENAME.replace("{yyyyMMddHHmmssSSS}", nowShortDatetime);

      String filePath =  AGRICULTURAL_BANK_SMS_PATH + File.separator + createDate;

      String filePathName = filePath + File.separator + fileName;
      String dictpath = request.getParameter("dictpath");

      logger.info("========上传文件名称 {}", filePathName);

      File saveFile = new File(filePath);
      if (!saveFile.exists()) {
        saveFile.mkdirs();
      }
      out = new FileOutputStream(filePathName);

      in = item.getInputStream();
      byte buffer[] = new byte[1024];
      int len = 0;
      while ((len = in.read(buffer)) > 0) {
        out.write(buffer, 0, len);
      }
      item.delete();
      out.flush();
      readXls(filePathName, sheetIndex, dictpath);
    } catch (FileUploadException e1) {
      json.put("result", "FALIE");
      json.put("message", e1.getMessage());

    } finally {
      try {
        if (null != in) {
          in.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
      try {
        if (null != out) {
          out.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    json.put("result", "SUCCESS");
    return json;
  }

  /**
   * * 保存数据
   *
   * @param filePath
   * @throws IOException
   */
  public void readXls(String filePath, String sheetIndex, String dictpath) throws IOException {

    InputStream is = new FileInputStream(filePath);
    HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);

    String instUser = String.valueOf(WebUtils.getUserInfo().getUserId());
    /** 发送短信通知 * */
    String sendMessageContent =
        SpringUtils.getBean(DictionaryUtils.class).getDataValueByPath(dictpath);

    int i = hssfWorkbook.getNumberOfSheets();
    logger.info("=========工作表长度：========{}", i);
    if (i > 0) {
      switch (sheetIndex) {
        case ALL_SHEET:
          logger.info("======保存所有工作表数据");
          /** 循环工作表Sheet * */
          for (int j = 0; j < hssfWorkbook.getNumberOfSheets(); j++) {

            HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(j);
            String sheetName = hssfSheet.getSheetName();
            /** 循环行Row * */
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
              HSSFRow hssfRow = hssfSheet.getRow(rowNum);
              if (hssfRow != null) {
                BatchSaveMessageThread batchSaveMessageThread =
                    (BatchSaveMessageThread) SpringUtils.getBean("batchSaveMessageThread");
                batchSaveMessageThread.setHssfRow(hssfRow);
                batchSaveMessageThread.setSendMessageContent(sendMessageContent);
                batchSaveMessageThread.setInstUser(instUser);
                batchSaveMessageThread.setSheetName(sheetName);
                batchSaveMessageThread.setDictpath(dictpath);
                BaseMessageThreadPool.getInstance().put(batchSaveMessageThread);
              }
            }
          }
          break;

        case FIRST_SHEET:
          HSSFSheet firstHssfSheet = hssfWorkbook.getSheetAt(0);
          String firstSheetName = firstHssfSheet.getSheetName();
          logger.info("======保存第一个工作表数据:{}", firstSheetName);
          /** 循环行Row * */
          for (int rowNum = 1; rowNum <= firstHssfSheet.getLastRowNum(); rowNum++) {
            HSSFRow hssfRow = firstHssfSheet.getRow(rowNum);
            if (hssfRow != null) {
              BatchSaveMessageThread batchSaveMessageThread =
                  (BatchSaveMessageThread) SpringUtils.getBean("batchSaveMessageThread");
              batchSaveMessageThread.setHssfRow(hssfRow);
              batchSaveMessageThread.setSendMessageContent(sendMessageContent);
              batchSaveMessageThread.setInstUser(instUser);
              batchSaveMessageThread.setSheetName(firstSheetName);
              batchSaveMessageThread.setDictpath(dictpath);
              BaseMessageThreadPool.getInstance().put(batchSaveMessageThread);
            }
          }
          break;

        default:
          HSSFSheet lastHssfSheet = hssfWorkbook.getSheetAt(i - 1);
          String lastSheetName = lastHssfSheet.getSheetName();
          logger.info("======保存最后一个工作表数据:{}", lastSheetName);
          /** 循环行Row * */
          for (int rowNum = 1; rowNum <= lastHssfSheet.getLastRowNum(); rowNum++) {
            HSSFRow hssfRow = lastHssfSheet.getRow(rowNum);
            if (hssfRow != null) {
              BatchSaveMessageThread batchSaveMessageThread =
                  (BatchSaveMessageThread) SpringUtils.getBean("batchSaveMessageThread");
              batchSaveMessageThread.setHssfRow(hssfRow);
              batchSaveMessageThread.setSendMessageContent(sendMessageContent);
              batchSaveMessageThread.setInstUser(instUser);
              batchSaveMessageThread.setSheetName(lastSheetName);
              batchSaveMessageThread.setDictpath(dictpath);
              BaseMessageThreadPool.getInstance().put(batchSaveMessageThread);
            }
          }
          break;
      }
    }
  }

  /**
   * 批量发送短信
   *
   * @param baseMessageBean
   * @return
   */
  public String sendBatchMessage(BaseMessage baseMessageBean) {
    String result = EXCEPTION;
    try {
      logger.info(
          "=========待发送数据客户号========{},手机号码====={}",
          baseMessageBean.getId(),
          baseMessageBean.getMobile());
      MessageBean messageBean = new MessageBean();
      messageBean.setContent(baseMessageBean.getContent());
      messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
      messageBean.setBusType(MessageColumnValues.busType.MANUAL_HANDLING);
      String[] tos = new String[] {baseMessageBean.getMobile()};
      messageBean.setTos(tos);
      IPlugin plugin = commonService.getIPlugin();
      boolean falg = plugin.sendMessage(MessageColumnValues.MsgType.SMS, messageBean);

      if (falg) {
        logger.info("发送信息成功===== {}", baseMessageBean.getId());
        baseMessageBean.setStatus(SUCCESS);
        result = SUCCESS;
      } else {
        logger.error("发送信息失败");
        baseMessageBean.setStatus(FAILURE);
        result = FAILURE;
      }
    } catch (Exception e) {
      logger.error("审核异常", e);
      baseMessageBean.setStatus(EXCEPTION);
      result = EXCEPTION;
    }
    baseMessageMapper.updateBaseMessage(baseMessageBean);
    return result;
  }

  /**
   * 单个发送短信
   *
   * @param baseMessageBean
   * @return
   */
  public String sendMessage(BaseMessage baseMessageBean) {
    String result = EXCEPTION;
    try {
      logger.info(
          "=========待发送数据客户号========{},手机号码====={}",
          baseMessageBean.getId(),
          baseMessageBean.getMobile());
      MessageBean messageBean = new MessageBean();
      messageBean.setContent(baseMessageBean.getContent());
      messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
      messageBean.setBusType(MessageColumnValues.busType.MANUAL_HANDLING);
      String[] tos = new String[] {baseMessageBean.getMobile()};
      messageBean.setTos(tos);
      IPlugin plugin = commonService.getIPlugin();
      boolean falg = plugin.sendMessage(MessageColumnValues.MsgType.SMS, messageBean);

      if (falg) {
        logger.info("发送信息成功===== {}", baseMessageBean.getId());
        baseMessageBean.setStatus(SUCCESS);
        result = SUCCESS;
      } else {
        logger.error("发送信息失败");
        baseMessageBean.setStatus(FAILURE);
        result = FAILURE;
      }
    } catch (Exception e) {
      logger.error("审核异常", e);
      baseMessageBean.setStatus(EXCEPTION);
      result = EXCEPTION;
    }
    baseMessageMapper.updateSingleMessage(baseMessageBean);
    return result;
  }
}
