package com.qifenqian.bms.platform.common.message;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import com.qifenqian.bms.platform.common.message.BankCncbDataSpecific;
import com.qifenqian.bms.platform.common.message.MessageDetailBean;
import com.qifenqian.bms.platform.common.message.interceptor.MessageInterceptorInterface;
import com.qifenqian.bms.platform.common.message.translate.FieldAnalysisSeparatorSplit;
import com.qifenqian.bms.platform.common.message.type.AnalysisObtainType;
import com.qifenqian.bms.platform.common.message.type.InterceptorAction;
import com.qifenqian.bms.platform.common.message.type.InterceptorTime;
import com.qifenqian.bms.platform.common.utils.ConvertUtils;
import com.qifenqian.bms.platform.common.utils.SpringUtils;

import com.qifenqian.bms.platform.common.message.annotation.MessageEnhance;
import com.qifenqian.bms.platform.common.message.annotation.MessageFieldSeparatorSplit;

/**
 * 符号分割
 * 
 * @project gyzb-platform-common
 * @fileName MessageBeanSeparatorSplit.java
 * @author huiquan.ma
 * @date 2016年2月25日
 * @memo
 */
public abstract class MessageBeanSeparatorSplit implements Serializable {

  private static final long serialVersionUID = -3683618401509759213L;

  private static Logger logger = Logger.getLogger(MessageBeanSeparatorSplit.class.getName());

  /**
   * 获取解析map
   * 
   * @return
   */
  public abstract Map<Field, FieldAnalysisSeparatorSplit> getFieldAnalysisMap();

  /**
   * 获取解析规则 json->bean
   * 
   * @return
   */
  public abstract FieldAnalysisSeparatorSplit getFieldAnalysisByDB(String fieldCode);

  /**
   * 获取解析来源方式
   * 
   * @return
   */
  public abstract AnalysisObtainType getAnalysisObtainType();

  /**
   * 获取配置bean
   * 
   * @param field
   * @return
   */
  private FieldAnalysisSeparatorSplit getFieldAnalysis(Field field) {
    if (null == field) {
      throw new IllegalArgumentException("字段为空");
    }

    if (!this.getFieldAnalysisMap().containsKey(field)) {
      FieldAnalysisSeparatorSplit fieldAnalysis = null;

      // 设置可访问
      field.setAccessible(true);

      MessageFieldSeparatorSplit messageFieldAnnotation =
          field.getAnnotation(MessageFieldSeparatorSplit.class);

      if (null != messageFieldAnnotation) {
        if (AnalysisObtainType.ANNOTATION == this.getAnalysisObtainType()) {
          Annotation[] annotationArray = field.getAnnotations();

          if (null != annotationArray && annotationArray.length > 0) {
            fieldAnalysis = new FieldAnalysisSeparatorSplit();
            for (Annotation annotation : annotationArray) {
              // 处理messageField注解
              if (annotation instanceof MessageFieldSeparatorSplit) {
                MessageFieldSeparatorSplit messageField = (MessageFieldSeparatorSplit) annotation;

                fieldAnalysis.setName(messageField.name());
                fieldAnalysis.setSeparator(messageField.separator());
                fieldAnalysis.setIndex(messageField.index());
                fieldAnalysis.setFieldCode(messageField.fieldCode());
                fieldAnalysis.setFieldClass(messageField.fieldClass());
                fieldAnalysis.setPadType(messageField.padType());
                fieldAnalysis.setMaterial(messageField.material());
                fieldAnalysis.setRegex(messageField.regex());
                fieldAnalysis.setColumnId(messageField.columnId());
              }
              // 处理MessageEnhance增强注解
              if (annotation instanceof MessageEnhance) {
                MessageEnhance messageEnhance = (MessageEnhance) annotation;
                Class<? extends MessageInterceptorInterface> interceptor =
                    messageEnhance.interceptor();
                // 判断
                if (InterceptorTime.BEFORE == messageEnhance.time()
                    && InterceptorAction.SET == messageEnhance.action()) {
                  fieldAnalysis.addBeforeSetInterceptor(interceptor);
                } else if (InterceptorTime.BEFORE == messageEnhance.time()
                    && InterceptorAction.GET == messageEnhance.action()) {
                  fieldAnalysis.addBeforeGetInterceptor(interceptor);
                } else if (InterceptorTime.AFTER == messageEnhance.time()
                    && InterceptorAction.SET == messageEnhance.action()) {
                  fieldAnalysis.addAfterSetInterceptor(interceptor);
                } else if (InterceptorTime.AFTER == messageEnhance.time()
                    && InterceptorAction.GET == messageEnhance.action()) {
                  fieldAnalysis.addAfterGetInterceptor(interceptor);
                }
              }
            }
          }
        } else if (AnalysisObtainType.DB == this.getAnalysisObtainType()) {
          fieldAnalysis = this.getFieldAnalysisByDB(messageFieldAnnotation.fieldCode());
        } else {
          throw new UnsupportedOperationException("暂不支持的规则解析来源|" + this.getAnalysisObtainType());
        }
      }

      this.getFieldAnalysisMap().put(field, fieldAnalysis);
    }
    return this.getFieldAnalysisMap().get(field);
  }

  /**
   * 赋值
   * 
   * @param field
   * @param readLine
   */
  private void setFieldValue(Field field, String readLine) {
    if (null == field) {
      throw new IllegalArgumentException("字段为空");
    }
    if (StringUtils.isEmpty(readLine)) {
      throw new IllegalArgumentException("读取行为空");
    }
    FieldAnalysisSeparatorSplit fieldAnalysis = this.getFieldAnalysis(field);
    if (null == fieldAnalysis) {
      return;
    }
    String[] fieldStrArray = readLine.split(fieldAnalysis.getSeparator());
    if (fieldStrArray.length < fieldAnalysis.getIndex() + 1) {
      throw new IllegalArgumentException(fieldAnalysis.getName() + "|栏位索引超过数组长度" + "|" + readLine);
    }
    String fieldStr = fieldStrArray[fieldAnalysis.getIndex()];
    // 处理填充物
    fieldStr = fieldStr.replaceAll(String.valueOf(fieldAnalysis.getMaterial()), "");
    // 处理校验
    String regex = fieldAnalysis.getRegex();
    if (StringUtils.isNotEmpty(regex) && !fieldStr.matches(regex)) {
      throw new IllegalArgumentException(fieldAnalysis.getName() + "|格式校验不匹配|" + regex);
    }
    // 处理数据类型
    Class<?> fieldClass = fieldAnalysis.getFieldClass();
    Object value = ConvertUtils.convert(fieldStr, fieldClass);
    // 赋值前增强处理
    if (null != fieldAnalysis.getBeforeSetInterceptors()) {
      for (Class<? extends MessageInterceptorInterface> interceptor : fieldAnalysis
          .getBeforeSetInterceptors()) {
        value = SpringUtils.getBean(interceptor).deal(value);
      }
    }
    try {
      field.set(this, value);
    } catch (Exception e) {
      logger.log(Level.WARNING, fieldAnalysis.getName() + "|" + readLine + "|赋值异常", e);
      throw new RuntimeException(
          fieldAnalysis.getName() + "|" + readLine + "|赋值异常|" + e.getMessage());
    }
    // 赋值后增强处理
    if (null != fieldAnalysis.getAfterSetInterceptors()) {
      for (Class<? extends MessageInterceptorInterface> interceptor : fieldAnalysis
          .getAfterSetInterceptors()) {
        SpringUtils.getBean(interceptor).deal(value);
      }
    }
  }

  /**
   * 赋值
   * 
   * @param readLine
   */
  public void fillFieldsValues(String readLine) {
    Field[] fields = this.getClass().getDeclaredFields();
    if (null == fields) {
      return;
    }
    for (Field field : fields) {
      field.setAccessible(true);
      this.setFieldValue(field, readLine);
    }
  }

  /***
   * 账单明细赋值
   * 
   * @param messageDetail
   * @param readLine
   * @return
   */
  public List<MessageDetailBean> fillDetailValues(MessageDetailBean messageDetail, String readLine,
      String separator) {
    List<MessageDetailBean> datailList = new ArrayList<MessageDetailBean>();
    Field[] fields = this.getClass().getDeclaredFields();
    if (null == fields) {
      return null;
    }
    for (Field field : fields) {
      field.setAccessible(true);

      MessageDetailBean messageDetailBean = this.setDetailValue(field, readLine, separator);
      if (null != messageDetailBean) {
        messageDetailBean.setChannelId(messageDetail.getChannelId());
        messageDetailBean.setFileId(messageDetail.getFileId());
        messageDetailBean.setSeq(messageDetail.getSeq());
        messageDetailBean.setWorkDate(messageDetail.getWorkDate());
        messageDetailBean.setColumnId(messageDetailBean.getColumnId());
        datailList.add(messageDetailBean);
      }
    }
    return datailList;
  }

  /**
   * 中信账单明细赋值
   * 
   * @param DataSpecific
   * @param readLine
   * @param separator
   * @return
   */
  public BankCncbDataSpecific DetailValues(BankCncbDataSpecific dataSpecific, String readLine,
      String separator) {

    BankCncbDataSpecific bankCncbDataSpecific = new BankCncbDataSpecific();
    Field[] fields = this.getClass().getDeclaredFields();
    if (null == fields) {
      return null;
    }
    for (Field field : fields) {
      field.setAccessible(true);
      if (null != dataSpecific) {
        bankCncbDataSpecific.setChannelId(dataSpecific.getChannelId());
        bankCncbDataSpecific.setFileId(dataSpecific.getFileId());
        bankCncbDataSpecific.setWorkDate(dataSpecific.getWorkDate());


      }
    }



    String[] separ = readLine.split(separator);
    try {
      // for(int i=0;i<separ.length;i++){
      // 字符串类型转换成日期类型
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      bankCncbDataSpecific.setTransDatetime(sdf.parse(separ[0]));
      bankCncbDataSpecific.setAppId(separ[1]);
      bankCncbDataSpecific.setChannelSub(separ[2]);
      bankCncbDataSpecific.setMerchantId(separ[3]);
      bankCncbDataSpecific.setMerchantIdSub(separ[4]);
      bankCncbDataSpecific.setDeviceId(separ[5]);
      bankCncbDataSpecific.setChannelOrderId(separ[6]);
      bankCncbDataSpecific.setChannelOrderIdSub(separ[7]);
      bankCncbDataSpecific.setOrderId(separ[8]);
      bankCncbDataSpecific.setUserMark(separ[9]);
      bankCncbDataSpecific.setTransType(separ[10]);
      bankCncbDataSpecific.setTransStatus(separ[11]);
      bankCncbDataSpecific.setPayBank(separ[12]);
      bankCncbDataSpecific.setCurrencyCode(separ[13]);
      bankCncbDataSpecific.setTotalAmt((separ[14]));
      bankCncbDataSpecific.setEnterpriseRedAmount(separ[15]);
      bankCncbDataSpecific.setChannelRefundId(separ[16]);
      bankCncbDataSpecific.setMerchantRefundId(separ[17]);
      bankCncbDataSpecific.setRefundAmt(separ[18]);
      bankCncbDataSpecific.setMerchantRefundAmt(separ[19]);
      bankCncbDataSpecific.setRefundType(separ[20]);
      bankCncbDataSpecific.setRefundStatus(separ[21]);
      bankCncbDataSpecific.setCommodityName(separ[22]);
      bankCncbDataSpecific.setMerchantAttach(separ[23]);
      bankCncbDataSpecific.setFeeAmt(separ[24]);
      bankCncbDataSpecific.setFeeRate(separ[25]);
      bankCncbDataSpecific.setTerminalType(separ[26]);
      bankCncbDataSpecific.setBalanceFlag(separ[27]);
      bankCncbDataSpecific.setStoreId(separ[28]);
      bankCncbDataSpecific.setMerchantName(separ[29]);
      bankCncbDataSpecific.setCashier(separ[30]);
      bankCncbDataSpecific.setMerchantSubId(separ[31]);
      bankCncbDataSpecific.setRechargeTicketAmt(separ[32]);
      bankCncbDataSpecific.setReceiveAmt(separ[33]);
      switch (separ.length) {
        case 35:
          bankCncbDataSpecific.setReservedField1(separ[34]);
          break;
        case 36:
          bankCncbDataSpecific.setReservedField1(separ[34]);
          bankCncbDataSpecific.setReservedField2(separ[35]);
          break;
        case 37:
          bankCncbDataSpecific.setReservedField1(separ[34]);
          bankCncbDataSpecific.setReservedField2(separ[35]);
          bankCncbDataSpecific.setReservedField3(separ[36]);
          break;
        case 38:
          bankCncbDataSpecific.setReservedField1(separ[34]);
          bankCncbDataSpecific.setReservedField2(separ[35]);
          bankCncbDataSpecific.setReservedField3(separ[36]);
          bankCncbDataSpecific.setReservedField4(separ[37]);
          break;
      }


      // bankCncbDataSpecific.setReservedField5(readLine.split(separator)[38]);
      // bankCncbDataSpecific.setInternalOrderId(readLine.split(separator)[39]);
      // bankCncbDataSpecific.setChannel(readLine.split(separator)[40]);
      // bankCncbDataSpecific.setInternalChannelSub(readLine.split(separator)[41]);
      // bankCncbDataSpecific.setZxOrderId(readLine.split(separator)[42]);
      // bankCncbDataSpecific.setInternalChannelOrderId(readLine.split(separator)[43]);
      // bankCncbDataSpecific.setOrderAmt(readLine.split(separator)[44]);
      // bankCncbDataSpecific.setTradeAmt(readLine.split(separator)[45]);
      // bankCncbDataSpecific.setOrderTimestamp(sdf.parse(readLine.split(separator)[46]));
      // bankCncbDataSpecific.setOrderState(readLine.split(separator)[47]);
      // bankCncbDataSpecific.setFinishTime(sdf.parse(readLine.split(separator)[48]));


      /*
       * Class<?> cls = Class.forName("BankCncbDataSpecific");//加载Bean类到内存中！获取一个Class对象
       * 
       * Object obj = cls.newInstance();//通过class类反射一个对象实体 bankCncbDataSpecific
       * =(BankCncbDataSpecific)obj;
       * 
       * for(Field field : fields) { field.setAccessible(true); MessageDetailBean messageDetailBean
       * =this.setDetailValue(field, readLine,separator);
       * 
       * //取messageDetailBean中的value值，然后set进bankCncbDataSpecific中 String fieldName =
       * field.getName();//取出字段名称 String Value = messageDetailBean.getFieldValue();
       * 
       * //假如字段的set方法并赋值 规则：加set,首字母大写！ Method method = cls.getDeclaredMethod("set" +
       * fieldName.substring(0,1).toUpperCase()+ fieldName.substring(1)); method.invoke(obj, Value);
       * }
       */
    } catch (Exception e) {
      e.printStackTrace();
      logger.log(Level.WARNING, "数组取值异常" + e.getMessage(), e);
      throw new RuntimeException("遍历读取异常", e);
    }

    return bankCncbDataSpecific;

  }



  private MessageDetailBean setDetailValue(Field field, String readLine, String separator) {
    MessageDetailBean messageDetailBean = null;
    if (null == field) {
      throw new IllegalArgumentException("字段为空");
    }
    if (StringUtils.isEmpty(readLine)) {
      throw new IllegalArgumentException("读取行为空");
    }
    FieldAnalysisSeparatorSplit fieldAnalysis = this.getFieldAnalysis(field);
    if (null == fieldAnalysis) {
      return null;
    }
    fieldAnalysis.setSeparator(separator);
    /** 不忽略任何一个分隔符 **/
    String[] fieldStrArray = readLine.split(fieldAnalysis.getSeparator(), -1);
    if (fieldStrArray.length < fieldAnalysis.getIndex() + 1) {
      throw new IllegalArgumentException(fieldAnalysis.getName() + "|栏位索引超过数组长度" + "|" + readLine);
    }
    String fieldStr = fieldStrArray[fieldAnalysis.getIndex()];
    // 处理填充物
    fieldStr = fieldStr.replaceAll(String.valueOf(fieldAnalysis.getMaterial()), "");
    // 处理校验
    String regex = fieldAnalysis.getRegex();
    if (StringUtils.isNotEmpty(regex) && !fieldStr.matches(regex)) {
      throw new IllegalArgumentException(fieldAnalysis.getName() + "|格式校验不匹配|" + regex);
    }
    // 处理数据类型
    Class<?> fieldClass = fieldAnalysis.getFieldClass();
    Object value = ConvertUtils.convert(fieldStr, fieldClass);
    // 赋值前增强处理
    if (null != fieldAnalysis.getBeforeSetInterceptors()) {
      for (Class<? extends MessageInterceptorInterface> interceptor : fieldAnalysis
          .getBeforeSetInterceptors()) {
        value = SpringUtils.getBean(interceptor).deal(value);
      }
    }
    try {
      field.set(this, value);
      messageDetailBean = new MessageDetailBean();
      messageDetailBean.setFieldValue(value.toString());
      messageDetailBean.setFieldCode(fieldAnalysis.getFieldCode());
      messageDetailBean.setColumnId(fieldAnalysis.getColumnId());
    } catch (Exception e) {
      logger.log(Level.WARNING, fieldAnalysis.getName() + "|" + readLine + "|赋值异常", e);
      throw new RuntimeException(
          fieldAnalysis.getName() + "|" + readLine + "|赋值异常|" + e.getMessage());
    }
    // 赋值后增强处理
    if (null != fieldAnalysis.getAfterSetInterceptors()) {
      for (Class<? extends MessageInterceptorInterface> interceptor : fieldAnalysis
          .getAfterSetInterceptors()) {
        SpringUtils.getBean(interceptor).deal(value);
      }
    }
    return messageDetailBean;
  }
}


