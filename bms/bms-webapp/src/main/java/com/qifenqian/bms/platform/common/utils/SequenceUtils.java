package com.qifenqian.bms.platform.common.utils;

import com.qifenqian.bms.platform.common.utils.DateUtils;
import com.qifenqian.bms.platform.common.utils.InetUtils;

/**
 * 序列工具类
 *
 * @project gyzb-platform-common
 * @fileName SequenceUtils.java
 * @author huiquan.ma
 * @date 2015年11月23日
 * @memo
 */
public class SequenceUtils {

  /** 循环值 用于生成银行账户、七分钱、七分宝、卡号、会计科目等账户内部编号 */
  private static long CYC_NUM1 = 1;

  /** 循环值 用于生成报文、交易、日志流水号 */
  private static long CYC_NUM2 = 1;

  /** 循环值 用于生成冻结、资金变动等流水号 */
  private static long CYC_NUM3 = 1;

  /**
   * 用于生成银行账户、七分钱、七分宝、卡号、会计科目等账户内部编号【25位】 <br>
   * 注意：如果系统采用集群，需要区分服务器<br>
   *
   * @return ip(3) + yyyyMMddHHmmssSSS(17) + SEQ(5)
   */
  public static String getAcctId() {
    String currSeq = String.valueOf(getCycNum1());

    return InetUtils.getLastSplitIp3() + DateUtils.getTimestampStrNo() + fillLeftWith0(currSeq, 5);
  }

  /**
   * 用于生成报文、交易、日志流水号 【28位】<br>
   * 注意：如果系统采用集群，需要区分服务器<br>
   *
   * @return ip(3) + yyyyMMddHHmmssSSS(17) + SEQ(8)
   */
  public static String getMsgId() {
    String currSeq = String.valueOf(getCycNum2());

    return InetUtils.getLastSplitIp3() + DateUtils.getTimestampStrNo() + fillLeftWith0(currSeq, 8);
  }

  /**
   * 用于生成冻结、资金变动等流水号【29位】 <br>
   * 注意：如果系统采用集群，需要区分服务器<br>
   *
   * @return ip(3) + yyyyMMddHHmmssSSS(17) + SEQ(9)
   */
  public static String getOnlyId() {
    String currSeq = String.valueOf(getCycNum3());

    return InetUtils.getLastSplitIp3() + DateUtils.getTimestampStrNo() + fillLeftWith0(currSeq, 9);
  }

  /**
   * 用于生成调度日志id 【24位】<br>
   *
   * @return ip(3) + yyyyMMddHHmmssSSS(17) + SEQ(4)
   */
  public static String getJobLogId() {
    String currSeq = String.valueOf(getCycNum2());

    return InetUtils.getLastSplitIp3()
        + DateUtils.getTimestampStrNo().substring(2)
        + fillLeftWith0(currSeq, 4);
  }

  /**
   * 使用“0”左填充至指定长度<br>
   *
   * @return
   */
  private static String fillLeftWith0(String currSeq, int length) {
    currSeq = "00000000000000000000" + currSeq;

    // 长度超长则截取
    if (currSeq.length() > length) {
      currSeq = currSeq.substring(currSeq.length() - length);
    }
    return currSeq;
  }

  /**
   * 获取自增长为 1，循环周期为 1000000000 的数值
   *
   * @return
   */
  private static synchronized long getCycNum1() {
    CYC_NUM1 %= 1000000000;

    return CYC_NUM1++;
  }

  /**
   * 获取自增长为 1，循环周期为 1000000000 的数值
   *
   * @return
   */
  private static synchronized long getCycNum2() {
    CYC_NUM2 %= 1000000000;

    return CYC_NUM2++;
  }

  /**
   * 获取自增长为 1，循环周期为 1000000000 的数值
   *
   * @return
   */
  private static synchronized long getCycNum3() {
    CYC_NUM3 %= 1000000000;

    return CYC_NUM3++;
  }
}
