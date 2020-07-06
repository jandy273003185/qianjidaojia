package com.qifenqian.bms.platform.common.message;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;
import com.qifenqian.bms.platform.common.message.interceptor.MessageInterceptorInterface;
import com.qifenqian.bms.platform.common.message.translate.FieldAnalysisFixedLength;
import com.qifenqian.bms.platform.common.message.type.AnalysisObtainType;
import com.qifenqian.bms.platform.common.message.type.InterceptorAction;
import com.qifenqian.bms.platform.common.message.type.InterceptorTime;
import com.qifenqian.bms.platform.common.utils.ConvertUtils;
import com.qifenqian.bms.platform.common.utils.SpringUtils;

import com.qifenqian.bms.platform.common.message.annotation.MessageEnhance;
import com.qifenqian.bms.platform.common.message.annotation.MessageFieldFixedLength;


/**
 * 
 * @project gyzb-platform-common
 * @fileName MessageBean.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
public abstract class MessageBeanFixedLength implements Serializable {

  private static final long serialVersionUID = -3683618401509759213L;

  private static Logger logger = Logger.getLogger(MessageBeanFixedLength.class.getName());

  /**
   * 获取解析map
   * 
   * @return
   */
  public abstract Map<Field, FieldAnalysisFixedLength> getFieldAnalysisMap();

  /**
   * 获取解析规则 json->bean
   * 
   * @return
   */
  public abstract FieldAnalysisFixedLength getFieldAnalysisByDB(String fieldCode);

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
  private FieldAnalysisFixedLength getFieldAnalysis(Field field) {
    if (null == field) {
      throw new IllegalArgumentException("字段为空");
    }

    if (!this.getFieldAnalysisMap().containsKey(field)) {
      FieldAnalysisFixedLength fieldAnalysis = null;

      // 设置可访问
      field.setAccessible(true);

      MessageFieldFixedLength messageFieldAnnotation =
          field.getAnnotation(MessageFieldFixedLength.class);

      if (null != messageFieldAnnotation) {
        if (AnalysisObtainType.ANNOTATION == this.getAnalysisObtainType()) {
          Annotation[] annotationArray = field.getAnnotations();

          if (null != annotationArray && annotationArray.length > 0) {
            fieldAnalysis = new FieldAnalysisFixedLength();
            for (Annotation annotation : annotationArray) {
              // 处理messageField注解
              if (annotation instanceof MessageFieldFixedLength) {
                MessageFieldFixedLength messageField = (MessageFieldFixedLength) annotation;

                fieldAnalysis.setName(messageField.name());
                fieldAnalysis.setFieldClass(messageField.fieldClass());
                fieldAnalysis.setStartPos(messageField.startPos());
                fieldAnalysis.setLength(messageField.length());
                fieldAnalysis.setPadType(messageField.padType());
                fieldAnalysis.setMaterial(messageField.material());
                fieldAnalysis.setRegex(messageField.regex());
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
    FieldAnalysisFixedLength fieldAnalysis = this.getFieldAnalysis(field);
    if (null == fieldAnalysis) {
      return;
    }
    int endPos = fieldAnalysis.getStartPos() + fieldAnalysis.getLength();
    // 获取字符串
    if (readLine.length() < endPos) {
      throw new IllegalArgumentException("行长度小于栏位所需长度|" + fieldAnalysis.getName() + "|" + readLine);
    }
    String fieldStr = readLine.substring(fieldAnalysis.getStartPos(), endPos);
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
}


