package com.sevenpay.agentmanager.common.utils;

import java.util.Random;
import java.util.UUID;

public class GenSN {
  private static long CYC_NUM_4 = 1;

  /** 按长度要求生成一串随机数字 */
  public static String getRandomNum(int length) {

    String chose = "0123456789";
    char temp;
    StringBuffer random = new StringBuffer();
    Random rand = new Random();

    for (int i = 0; i < length; i++) {
      temp = chose.charAt(rand.nextInt(chose.length()));

      random.append(temp);
    }

    return random.toString();
  }

  /** 按长度要求生成一串随机字符 */
  public static String getRandomChar(int length) {

    String stra = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    char temp;
    StringBuffer random = new StringBuffer();
    Random rand = new Random();

    for (int i = 0; i < length; i++) {
      temp = stra.charAt(rand.nextInt(stra.length()));

      random.append(temp);
    }

    return random.toString();
  }

  /** 生成32位的UUID，不含横杠 */
  public static String getSN() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  /** 用户注册时生成注册用户ID */
  public static String getLoginID() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  /** 登录日志流水ID */
  public static String getLoginLogID() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  /** 操作日志流水ID */
  public static String getOperateID() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  /** 生成form_token_id(防止form重复提交使用) */
  public static String getFormTokenId() {
    return UUID.randomUUID().toString().replaceAll("-", "");
  }

  public static void main(String args[]) {
    System.out.println(UUID.randomUUID().toString().replaceAll("-", ""));
    int i = 0;
    while (i < 1000) {
      System.out.println(GenSN.genRandomPwd());
      i++;
    }
  }

  /**
   * 生成随机密码
   *
   * @param pwdlen
   * @return
   */
  public static String genRandomPwd() {
    final int maxNum = 67;
    final int maxNums = 7;
    final int maxNumn = 10;
    int i;
    int count = 0;
    char[] stra = {
      'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
      't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B',
      'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
      'V', 'W', 'X', 'Y', 'Z', '~', '&', '!', '@', '^', '$', '_'
    };
    char[] specStr = {'~', '&', '!', '@', '^', '$', '_'}; // |*|%|;|--|+|,|//|#
    char[] num = {
      '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
    };

    StringBuffer pwd = new StringBuffer("");
    Random r = new Random();
    while (count < 8) {
      i = Math.abs(r.nextInt(maxNum));

      if (i >= 0 && i < stra.length) {
        pwd.append(stra[i]);
        count++;
      }
    }

    count = 0;
    while (count < 1) {
      i = Math.abs(r.nextInt(maxNumn));

      if (i >= 0 && i < num.length) {
        pwd.append(num[i]);
        count++;
      }
    }

    count = 0;
    while (count < 1) {
      i = Math.abs(r.nextInt(maxNums));

      if (i >= 0 && i < specStr.length) {
        pwd.append(specStr[i]);
        count++;
      }
    }

    return pwd.toString();
  }

  public static String getMerchantPictureNo() {
    String result = null;
    try {
      String dateString = DateUtils.getDateStrNO();
      String resultNo = fillLeftWith0(String.valueOf(getCycNum4()), 5);
      result = dateString + resultNo;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return result;
  }

  /**
   * 获取自增长为 1，循环周期为 10000 的数值
   *
   * @return
   */
  private static synchronized long getCycNum4() {
    CYC_NUM_4 %= 10000;
    return CYC_NUM_4++;
  }

  /**
   * 使用“0”左填充至指定长度
   *
   * @param currSeq
   * @param length
   * @return
   */
  private static String fillLeftWith0(String suffix, int length) {
    suffix = "00000000000000000000" + suffix;

    // 长度超长则截取
    if (suffix.length() > length) {
      suffix = suffix.substring(suffix.length() - length);
    }

    return suffix;
  }

  // 获取系统时间
  public static String getSysTime() {
    return String.valueOf(System.currentTimeMillis());
  }
}
