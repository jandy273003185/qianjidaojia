package com.qifenqian.bms.accounting.refund.bean;

import com.qifenqian.bms.platform.common.annotation.Comment;

/**
 * 报文请求字段常量
 *
 * @project sevenpay-invoke
 * @fileName RequestColumnValues.java
 * @author huiquan.ma
 * @date 2015年8月17日
 * @memo
 */
public class SevenmallRequestValues {

  /** 七分钱系统返回码 */
  public enum SevenpayRtnCode {
    @Comment(desc = "成功")
    SUCCESS,
    @Comment(desc = "失败")
    FAILURE,
    @Comment(desc = "异常")
    EXCEPTION
  }

  /** 七分钱支付类型 */
  public enum SevenpayPayType {
    @Comment(code = "00", desc = "余额支付")
    BALANCE,
    @Comment(code = "10", desc = "卡支付")
    BANK_CARD
  }

  /** 运营商 */
  public enum SevenmallMobileOper {
    @Comment(desc = "移动")
    MOBILE,
    @Comment(desc = "联通")
    UNICOM,
    @Comment(desc = "电信")
    TELECOM
  }

  /** 响应返回状态 */
  public enum SevenmallResponseStatus {
    @Comment(desc = "成功")
    SUCCESS,
    @Comment(desc = "失败")
    FAILURE,
    @Comment(desc = "异常")
    EXCEPTION
  }

  /** 清算表状态 */
  public enum SevenmallClearStatus {
    @Comment(desc = "成功")
    SUCCESS,
    @Comment(desc = "失败")
    FAILURE,
    @Comment(desc = "异常")
    EXCEPTION,
    @Comment(desc = " 确认成功")
    CONFIRM_SUCCESS,
    @Comment(desc = "确认失败")
    CONFIRM_FAILURE,
    @Comment(desc = "取消")
    CANCELLED
  }

  /** 本系统币别 */
  public enum SevenmallCurrCode {
    @Comment(desc = "人民币")
    CNY,
  }

  /** 应用代码 */
  public enum SevenmallAppCode {
    @Comment(desc = "话费充值")
    PRECHARGE,
    @Comment(desc = "流量充值")
    FLUX_CHARGE,
    @Comment(desc = "购买电影票")
    BUY_FILM,
  }

  /** 终端类型 */
  public enum SevenmallTerminalType {
    @Comment(desc = "PC端")
    WEB,
    @Comment(desc = "APP端")
    MOBILE
  }

  /** 支付方式 */
  public enum SevenmallPaymentWay {
    @Comment(desc = "七分钱")
    SEVENPAY,
    @Comment(desc = "支付宝")
    ALIPAY,
    @Comment(desc = "银联在线")
    UNIONPAY
  }

  /** 订单交易类型 */
  public enum SevenmallOrderTradeType {
    @Comment(desc = "即时到帐")
    DIRECT,
    @Comment(desc = "担保交易")
    GUARANTEE
  }

  /** 外系统代码 */
  public enum SevenmallOutSystemCode {
    @Comment(desc = "万汇通")
    WANHUI,
    @Comment(desc = "中银通")
    YINTONG,
    @Comment(desc = "中影票务")
    CFT
  }

  /** 订单状态 */
  public enum SevenmallOrderStatus {
    @Comment(desc = "待付款")
    INIT,
    @Comment(desc = "已付款")
    PAID,
    @Comment(desc = "万汇通")
    DELIVERED,
    @Comment(desc = "已发货")
    RECEIVED,
    @Comment(desc = "已收货")
    FINISHED,
    @Comment(desc = "已完成")
    CANCELLED,
    // 中间状态，不会出现在表中，用于前台展示
    @Comment(desc = "处理中")
    DEAL,
    @Comment(desc = "已退款")
    REFUND
  }

  /** 退款状态 */
  public enum SevenmallRefundState {
    @Comment(desc = "无退款")
    NO,
    @Comment(desc = "部分退款")
    PART,
    @Comment(desc = "全部退款")
    ALL
  }

  /** 退货状态 */
  public enum SevenmallReturnState {
    @Comment(desc = "无退货")
    NO,
    @Comment(desc = "部分退货")
    PART,
    @Comment(desc = "全部退货")
    ALL
  }

  /**
   * 应用状态
   *
   * @author Roy.Li
   */
  public enum SevenmallAppStatus {
    @Comment(desc = "上线")
    ONLINE,
    @Comment(desc = "下线")
    OFFLINE
  }

  /**
   * 万汇通交易类型
   *
   * @author Roy.Li
   */
  public enum SevenmallClearTranType {
    @Comment(desc = "普通充值")
    GENERAL_RECHARGE,
    @Comment(desc = "广东电信")
    GUANGDONG_TELECOM,
    @Comment(desc = "广东移动")
    GUANGDONG_MOBILE
  }
}
