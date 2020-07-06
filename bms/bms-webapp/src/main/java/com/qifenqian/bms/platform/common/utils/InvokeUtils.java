package com.qifenqian.bms.platform.common.utils;

import java.util.logging.Logger;

import com.qifenqian.bms.platform.common.bean.InvokeInfo;


/**
 * 异常工具类
 * 
 * @project gyzb-platform-common
 * @fileName InvokeUtils.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
public class InvokeUtils {

  public final static Logger logger = Logger.getLogger(InvokeUtils.class.getName());

  /**
   * 查找当前方法的父级调用
   * 
   * @return
   */
  public static InvokeInfo findInvokeInfo() {

    try {

      // 抛出一个试探性异常
      throw new Exception();

    } catch (Exception e) {

      StackTraceElement[] stackTraces = e.getStackTrace();

      if (stackTraces.length < 2) {
        logger.warning("堆栈深度小于2");
        return null;
      }

      StackTraceElement parentStackTrace = stackTraces[2];

      // 获取调用信息
      String className = parentStackTrace.getClassName();
      String fileName = parentStackTrace.getFileName();
      int lineNumber = parentStackTrace.getLineNumber();
      String methodName = parentStackTrace.getMethodName();

      // 调用信息
      InvokeInfo invokeInfo = new InvokeInfo();
      {
        invokeInfo.setClassName(className);
        invokeInfo.setFileName(fileName);
        invokeInfo.setLineNumber(lineNumber);
        invokeInfo.setMethodName(methodName);
      }

      return invokeInfo;
    }

  }

}
