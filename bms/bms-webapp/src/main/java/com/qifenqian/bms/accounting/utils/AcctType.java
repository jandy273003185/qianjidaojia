package com.qifenqian.bms.accounting.utils;

import com.qifenqian.bms.platform.common.annotation.Comment;

public enum AcctType {
  /** CHARGE--充值； CASH_O--提现； TRANSF--转账； PAYMEN--支付； RETURN--退款； CANCEL--回滚； */
  @Comment(code = "REGISTER", desc = "注册")
  REGISTER("REGISTER", "注册"),

  @Comment(code = "RECHARGE", desc = "充值")
  RECHARGE("RECHARGE", "充值"),

  @Comment(code = "PAYMENT", desc = "支付")
  PAYMENT("PAYMENT", "支付"),

  @Comment(code = "PAYMENT_REVOKE", desc = "支付撤销")
  PAYMENT_REVOKE("PAYMENT_REVOKE", "支付撤销"),

  @Comment(code = "PAYMENT_REFUND", desc = "支付退款")
  PAYMENT_REFUND("PAYMENT_REFUND", "支付退款"),

  @Comment(code = "WITHDRAW", desc = "提现")
  WITHDRAW("WITHDRAW", "提现"),

  @Comment(code = "WITHDRAW_APPLY", desc = "提现申请")
  WITHDRAW_APPLY("WITHDRAW_APPLY", "提现申请"),

  @Comment(code = "WITHDRAW_REVOKE", desc = "提现申请撤销")
  WITHDRAW_REVOKE("WITHDRAW_REVOKE", "提现申请撤销"),

  @Comment(code = "REFUND", desc = "退款")
  REFUND("REFUND", "退款"),

  @Comment(code = "RECHARGE_REVOKE", desc = "充值撤销")
  RECHARGE_REVOKE("RECHARGE_REVOKE", "充值撤销"),

  @Comment(code = "RECEIVE", desc = "收款")
  RECEIVE("RECEIVE", "收款"),

  @Comment(code = "RECEIVE_REVOKE", desc = "收款撤销")
  RECEIVE_REVOKE("RECEIVE_REVOKE", "收款撤销"),

  @Comment(code = "FREEZE", desc = "冻结")
  FREEZE("FREEZE", "冻结"),
  @Comment(code = "UNFREEZE", desc = "解冻")
  UNFREEZE("UNFREEZE", "解冻");

  private String desc;
  private String code;

  private AcctType(String code, String desc) {
    this.desc = desc;
    this.code = code;
  }

  public String getDesc() {
    return desc;
  }

  public String getCode() {
    return code;
  }
}
