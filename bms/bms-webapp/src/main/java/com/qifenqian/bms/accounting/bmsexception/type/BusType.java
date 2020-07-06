package com.qifenqian.bms.accounting.bmsexception.type;

import com.qifenqian.bms.platform.common.annotation.Comment;

public enum BusType {

	@Comment(desc = "注册")
	REGISTER,

	@Comment(desc = "充值")
	RECHARGE,

	@Comment(desc = "支付")
	PAYMENT,

	@Comment(desc = "支付撤销")
	PAYMENT_REVOKE,

	@Comment(desc = "支付退款")
	PAYMENT_REFUND,

	@Comment(desc = "提现")
	WITHDRAW,

	@Comment(desc = "结算")
	SETTLE,

	@Comment(desc = "提现申请")
	WITHDRAW_APPLY,

	@Comment(desc = "提现申请撤销")
	WITHDRAW_REVOKE,

	@Comment(desc = "结算申请")
	SETTLE_APPLY,

	@Comment(desc = "结算申请撤销")
	SETTLE_REVOKE,

	@Comment(desc = "退款")
	REFUND,

	@Comment(desc = "充值撤销")
	RECHARGE_REVOKE,

	@Comment(desc = "充值退款")
	RECHARGE_REFUND,

	@Comment(desc = "收款")
	RECEIVE,

	@Comment(desc = "收款撤销")
	RECEIVE_REVOKE,

	@Comment(desc = "收款退款")
	RECEIVE_REFUND,

	@Comment(desc = "转账")
	TRANSFER,

	@Comment(desc = "冻结")
	FREEZE,

	@Comment(desc = "解冻")
	UNFREEZE,

	@Comment(desc = "调账")
	ADJUST,

	@Comment(desc = "其他业务")
	OTHER;
}
