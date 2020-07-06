package com.qifenqian.bms.basemanager;

import java.util.Arrays;
import java.util.List;

public class Constant {

	/**
	 * 审核状态 0：已认证 1：待审核 2：审核不通过
	 */
	public static final String AUDIT_PASS = "0";

	public static final String AUDIT_WAIT = "1";

	public static final String AUDIT_NOPASS = "2";

	/**
	 * 审核结果代码 00：通过 01：不通过
	 */
	public static final String AUDIT_RESULT_PASS = "00";

	public static final String AUDIT_RESULT_NOPASS = "01";

	/**
	 * 实名认证审核状态： 00：0级审核通过；01：0级审核中；02：0级审核不通过；
	 * 0级审核通过后，客户实名认证等级升级到1级，1级的审核状态为10,11,12，依此类推
	 */
	public static final String TRUST_AUDIT_PASSING = "01";

	public static final String TRUST_AUDIT_PASS = "10";

	public static final String TRUST_AUDIT_NOPASS = "12";
	
	/**
	 * 实名认证状态： 30 认证通过
	 */
	public static final String CERTIFY_STATUS_30 = "30";
	/**
	 * 实名认证状态： 31法人认证-企业证件信息已提交
	 */
	public static final String CERTIFY_STATUS_31 = "31";
	/**
	 * 实名认证状态： 32法人认证-企业账户信息已提交
	 */
	public static final String CERTIFY_STATUS_32 = "32";
	/**
	 * 实名认证状态： 33法人认证-企业法人信息已提交
	 */
	public static final String CERTIFY_STATUS_33 = "33";
	/**
	 * 实名认证状态： 34 审核中-等待人工审核
	 */
	public static final String CERTIFY_STATUS_34 = "34";
	/**
	 * 实名认证状态： 35 审核不通过
	 */
	public static final String CERTIFY_STATUS_35 = "35";

	/**
	 * 实名认证等级：0：未认证；1：1级认证；2：2级认证；3：3级认证。 业务含义： 0：未验证任何信息材料，； 1：身份证验证；
	 * 2：身份证+银行卡验证； 3：身份证+银行卡+证件审核。
	 */
	public static final String CERTIFY_LVL_1 = "1";

	public static final String CERTIFY_LVL_0 = "0";

	public static final String CERTIFY_LVL_3 = "3";

	public static final String CERTIFY_AUDIT_STATE_PASS = "30";

	public static final String CERTIFY_AUDIT_STATE_NOPASS = "32";

	/**
	 * 商户证件类型 扫描件类型： 00 个人身份证  01 税务登记证  02 营业执照 03 开户证件 04商户身份信息 05 银行卡扫描件 06 其他证件 18店内照  11行业资质照  12电子签名照   
	 */
	public static final String CERTIFY_TYPE_PERSON_IDCARD = "00";

	public static final String CERTIFY_TYPE_TAX = "01";

	public static final String CERTIFY_TYPE_BUSINESS = "02";

	public static final String CERTIFY_TYPE_OPEN = "03";

	public static final String CERTIFY_TYPE_MERCHANT_IDCARD = "04";
	
	public static final String CERTIFY_TYPE_MERCHANT_BANKCARD = "07";
	
	public static final String CERTIFY_TYPE_MERCHANT_ACCTID = "10";
	
	public static final String CERTIFY_TYPE_MERCHANT_DOORID = "08";
	
	public static final String CERTIFY_TYPE_MERCHANT_NETWORKID = "09";
	
	public static final String CERTIFY_TYPE_BANK_CARD = "05";
	
	public static final String CERTIFY_TYPE_OTHER_PAPERS = "06";
	
	public static final String CERTIFY_TYPE_BANK_PAPERS = "99";
	
	public static final String CERTIFY_TYPE_MERCHANT_SHOPINTERIOR = "18";
	
	public static final String CERTIFY_TYPE_MERCHANT_QUALIFICATION = "11";
	
	public static final String CERTIFY_TYPE_MERCHANT_SIGNATURE = "12";
	/**
	 * 手持身份证照
	 */
	public static final String CERTIFY_TYPE_MERCHANT_HANDIDCARD = "13";
	/**
	 * 银行卡反面照
	 */
	public static final String CERTIFY_TYPE_MERCHANT_BANKCARDBACK = "14";
	/**
	 * 合作证明函
	 */
	public static final String CERTIFY_TYPE_MERCHANT_COOPERATE = "15";
	/**
	 * 结算银行卡号
	 */
	public static final String CERTIFY_TYPE_MERCHANT_SETTLEMENT = "10";
	
	/**
	 * 商户注册认证等级 0  3
	 */
	public static final short MERCHANT_NO_CERTIFY = 0;
	
	public static final short MERCHANT_NO_TINYCERTIFY = 3;

	/**
	 * 客户类型 0 个人 1企业
	 */
	public static final String CUST_TYPE_PERSON = "0";

	public static final String CUST_TYPE_COMPANY = "1";
	
	// public static final String CUST_TYPE_TINYCOMPANY = "2";

	/**
	 * 商户标识 0 商户 1非商户 2 微商户 3代理商
	 */
	public static final String MERCHANT_FLAG_TRADE = "0";

	public static final String MERCHANT_FLAG_NOTRADE = "1";
	
	public static final String MERCHANT_FLAG_TINYTRADE = "2";

	public static final String MERCHANT_FLAG_AGENT = "3";
	/**
	 * 商户状态 00 有效；01 待审核；02 注销；03 冻结 04 审核不通过 05 审核通过待激活 06 已激活
	 */
	public static final String CUST_STATE_VALID = "00";
	public static final String CUST_STATE_WAITINGVERIFY = "01";
	public static final String CUST_STATE_LOGOUT = "02";
	public static final String CUST_STATE_FREEZE = "03";
	public static final String CUST_STATE_NOVERIFY = "04";
	public static final String CUST_STATE_WAIT_ACTIVATE = "05";
	public static final String CUST_STATE_ACTIVATE = "06";

	/****** 登陆用户信息begin *******************/

	/**
	 * 商户初始密码
	 */
	public static final String INIT_PWD = "123456";

	/**
	 * 角色ID：per 个人角色,ent 企业角色
	 */
	public static final String ROLE_PER = "per";

	public static final String ROLE_ENT = "ent";

	public static final String ROLE_AGENT = "agent";
	/**
	 * 状态：00 正常；01 停用；02 登录账户冻结；03 注册待激活 ； 04 商户审核中 ； 05 前台 商户协议待录入 06 后台商户协议待录入
	 */
	public static final String LOGIN_STATE_NORMAL = "00";

	public static final String LOGIN_STATE_STOP = "01";

	public static final String LOGIN_STATE_FREEZE = "02";

	public static final String LOGIN_STATE_WAIT_ACTIVATE = "03";

	public static final String LOGIN_STATE_AUDITORING = "04";

	public static final String LOGIN_STATE_AGREEMENTING = "05";
	
	public static final String LOGIN_STATE_MANAGER_AGREEMENTING = "06";

	/**
	 * 费率业务类型 充值RECHARGE/提现WITHDRAW/转账TRANSFER
	 */
	public static final String FEE_RATE_RECHARGE = "recharge";

	public static final String FEE_RATE_WITHDRAW = "withdraw";

	public static final String FEE_RATE_TRANSFER = "transfer";

	/**
	 * 费率状态 状态：生效VALID/失效DISABLE
	 */
	public static final String FEE_RATE_STATUS_VALID = "valid";

	public static final String FEE_RATE_STATUS_DISABLE = "disable";

	/**
	 * 商户注册审核工作流 key
	 */
	public static final String MERCHANTAUDIT_KEY = "merchantAudit";

	/**
	 * 提现状态： 00 提现成功 01 待提现 02 提现申请提交核心 03 提现申请成功 04 提现申请失败 22 提现提交核心 23
	 * 提现核心处理成功 24 提现核心处理失败 32 提现提交金蝶 33 提现金蝶处理成功 34 提现金蝶处理失败 42 提现撤销提交核心 43
	 * 提现撤销核心处理成功 44 提现撤销核心处理失败
	 */
	public static final String WITHDRAW_SUCCESS = "00";

	public static final String WITHDRAW_WAITING = "01";

	public static final String WITHDRAW_APPLY_CORE_SUBMIT = "02";

	public static final String WITHDRAW_APPLY_CORE_SUCCESS = "03";

	public static final String WITHDRAW_APPLY_CORE_FAIL = "04";

	public static final String WITHDRAW_CORE_SUBMIT = "22";

	public static final String WITHDRAW_CORE_SUCCESS = "23";

	public static final String WITHDRAW_CORE_FAIL = "24";

	public static final String WITHDRAW_KINGDEE_SUBMIT = "32";

	public static final String WITHDRAW_KINGDEE_SUCCESS = "33";

	public static final String WITHDRAW_KINGDEE_FAIL = "34";

	public static final String WITHDRAW_REVOKE_CORE_SUBMIT = "42";

	public static final String WITHDRAW_REVOKE_CORE_SUCCESS = "43";

	public static final String WITHDRAW_REVOKE_CORE_FAIL = "44";

	public static final String WITHDRAW_REFUND_CORE_SUBMIT = "52";

	public static final String WITHDRAW_REFUND_CORE_SUCCESS = "53";

	public static final String WITHDRAW_REFUND_CORE_FAIL = "54";

	/** ========== REFUND ========= **/
	public static final String REFUND_STATE_SUCCESS = "00";

	public static final String REFUND_STATE_AUDIT_REJECT = "05";

	public static final String REFUND_STATE_CROE_FAIL = "04";

	public static final String REFUND_STATE_CROE_EXCEPTION = "03";

	public static final String REFUND_STATE_CORE_HANDER = "02";

	public static final String RED_REFUND_STATE_FAIL = "02";

	public static final String RED_REFUND_STATE_CANCEL = "99";

	/**
	 * 提现审核状态：01 待审核 02 审核通过 04 审核不通过'
	 */
	public static final String WITHDRAW_AUDIT_WAIT = "01";

	public static final String WITHDRAW_AUDIT_SUCCESS = "02";

	public static final String WITHDRAW_AUDIT_FAIL = "04";

	/**
	 * 提现核销 01 未核销 02 已核销
	 */
	public static final String VERIFICATION_STATE_WAIT = "01";

	public static final String VERIFICATION_STATE_SUCCESS = "02";

	/**
	 * 提现类型 withdraw :提现 revoke：撤销
	 */
	public static final String OPER_TYPE_WITHDRAW = "withdraw";

	public static final String OPER_TYPE_REVOKE = "revoke";

	public static final String OPER_TYPE_REFUND = "refund";

	public static final String JGKJ_SUCC = "000000";

	public static final String JGKJ_NO_RESULT = "100013";

	public static final String[] JGKJ_FAILURE_CODE_ARRAY = new String[] { "100001", "100002", "100003", "100004", "100005", "100006", "100007",
			"100008", "100009", "100010", "100011", "100012", "100013", "100014", "100015", "100016", "100017", "100018", "100019", "100020",
			"100021", "100022", "100023", "100024", "100025", "100026", "100027", "100028", "100029", "100030", "100031", "100032", "100033",
			"100034", "100035", "100036", "100041", "100042", "100043", "100044", "100045", "200001", "200002", "200003", "800001", "800002",
			"G00001", "G00002", "G00003", "G00004", "G89999" };

	private static final List<String> jgkjFailureCodeList = Arrays.asList(Constant.JGKJ_FAILURE_CODE_ARRAY);

	public static boolean isJgkjFailure(String responseCode) {
		return jgkjFailureCodeList.contains(responseCode);
	}

	public static final String YINLIAN_SUCC = "00";

	/** 商户结算状态 01核心返回异常；02核心处理成功；03 核心返回失败 **/
	public static final String SETTLE_EXCEPTION = "01";
	public static final String SETTLE_SUCCESS = "02";
	public static final String SETTLE_FAILURE = "03";

	/** 结算操作类型：SETTLE_APPLY 结算申请，SETTLE_REVOKE 结算申请撤销,SETTLE_VERIFIED 结算核销 **/
	public static final String SETTLE_APPLY = "SETTLE_APPLY";
	public static final String SETTLE_REVOKE = "SETTLE_REVOKE";
	public static final String SETTLE_VERIFIED = "SETTLE_VERIFIED";
	
	/**付款方式 pay_type ，0：付到银行卡（默认）；1：付到其代付账户 */
	public static final String PAY_TO_CARD  = "0";
	public static final String PAY_TO_AGENTPAY_ACCT  = "1";
	/**
	 * 01：取消；02：提交核心处理；03：核心返回失败；04：核心返回成功；05：提交银联处理 ；06：银联返回失败；07：银联返回成功；00
	 * 撤销成功
	 **/
	public static final String PAYMENT_REVOKE_CANCEL = "99";
	public static final String PAYMENT_REVOKE_INIT = "01";
	public static final String PAYMENT_REVOKE_CORE_DEAL = "02";
	public static final String PAYMENT_REVOKE_CORE_FAIL = "03";
	public static final String PAYMENT_REVOKE_CORE_SUCC = "04";
	public static final String PAYMENT_REVOKE_YL_DEAL = "05";
	public static final String PAYMENT_REVOKE_YL_FAIL = "06";
	public static final String PAYMENT_REVOKE_YL_SUCC = "07";
	public static final String PAYMENT_REVOKE_SUCCESS = "00";

	public static final String STATE_SUCCESS = "00";

	public static final String STATE_CANCEL = "99";

	/**
	 * 密码修改 00 成功 01失败
	 */
	public static final String PASSWORD_MODIFY_SUCCESS = "00";

	public static final String PASSWORD_MODIFY_FAIL = "01";

	/**
	 * 账户冻结/可用
	 */
	public static final String ACCOUNT_FREEZE = "FREEZE";

	public static final String ACCOUNT_NORMAL = "NORMAL";

	/**
	 * 账户修改状态
	 */
	public static final String ACCOUNT_EDIT_CORE_SUCC = "00";
	public static final String ACCOUNT_EDIT_INIT = "01";
	public static final String ACCOUNT_EDIT_CORE_DEAL = "02";
	public static final String ACCOUNT_EDIT_CORE_FAIL = "03";
	public static final String ACCOUNT_EDIT_CANCEL = "99";

	/** 数据字典 - 银联内部商户号 */
	public static final String UNION_PAY_MER_CODE = "UNION_PAY_MER_CODE";

	/** 数据字典 - 客户证件审核不通过短信通知 */
	public static final String CERTIFICATE_AUDIT_MESSAGE = "certificate.audit.message";

	/** 数据字典 - 银联内部商户号 */
	public static final String QFQ_MARKET_CODE = "QFQ_MARKET_CODE";

	/** 数据字典 - 批量发送短信配置信息 */
	public static final String SEND_BATCH_MESSAGE = "send_batch_message";

	/*** RED_PACKET ****/

	/** 红包入账 **/
	public static final String IN_ORDER_SATATUS_SUCCESS = "00";

	/** 认证 ***/
	public static final String IDENTITYVERIFICATION_STATUS_FAILURE = "0";

	public static final String IDENTITYVERIFICATION_STATUS_SUCCESS = "1";

	public static final String IDENTITYVERIFICATION_STATUS_NONE = "2";

	/** 1 认证完成 **/
	public static final String IDENTITYVERIFICATION_QUERY_STATUS_SUCCESS = "1";

	/** 验证状态：01 待验证；02 验证不通过；00 验证通过 **/
	public static final String VALIDATE_STATUS_SUCCESS = "00";

	public static final String VALIDATE_STATUS_FAILURE = "02";

	public static final String REQUEST_FILE_TYPE = "REQ";

	public static final String RESPONSE_FILE_TYPE = "RES";
	
	public static final String UPLOAD_FILE_STATUS_SUCCESS = "UPLOAD_OVER";
	public static final String UPLOAD_FILE_STATUS_INVALID = "INVALID";
	
	public static final String COMBINED_TYPE_PAY = "1";
	
	public static final String COMBINED_TYPE_REFUND = "2";
	
	public static final String FIX_CONTENT_PATH="FIX_CONTENT";
	
	public static final String AGENTMERCHANTRATE="agent_Merchant_Rate";
	
	public static final String AGENT_MERCHANT="2";
	
	public static final String NORMAL_MERCHANT="1";
	
	/**
	 * 商户角色 0线上 1线下 2线上线下
	 */
	
	public static final String ON_LINE = "0";
	
	public static final String OFF_LINE = "1";
	
	public static final String ON_AND_OFF = "2";
	/**
	 * 商户报备状态 01 未报备，00已报备，02修改待报备
	 */
	public static final String NO_FILING = "01";
	
	public static final String YES_FILING = "00";
	
	public static final String WATING_FILING = "02";
	
	/**
	 * 渠道 
	 */
	public static final String HUARUN = "iCr";
	
	public static final String HELIPAY = "heliPay";
	
	public static final String KFTPAY = "kftPay";
	
	/**
	 * 快付通进件商户类型 0 ： 个人 ,1 商户
	 */
	public static final String KFT_PERSON = "1";
	
	public static final String KFT_MERCHANT = "2";
	
	/**
	 * 快付通文件商户类型 1 ： 个人 ,3 商户
	 */
	public static final String KFT_FILE_PERSON = "1";
	
	public static final String KFT_FILE_MERCHANT = "3";
	
	/**
	 * O 身份证
	 * Y 营业执照（三证合一）
	 */
	public static final String KFT_PERSON_CARD = "O";
	
	public static final String KFT_BUSINESS = "Y";
	
	/**
	 * 快付通 文件类别
	 * 		10	身份证正面照	
	 *		11	身份证背面照	
	 *		12	手持身份证正面照	
	 *		13	银行卡正面照	
	 *		14	银行卡反面照	
	 *		16	店面门头照	
	 *		21	营业执照（三证合一）	
	 *		25	法人身份证正面照	
	 *		26	法人身份证反面照	
	 *		27	开户许可证
	 *		29	店面门头照	
	 */
	public static final String KFT_FILE_TYPE_PER_CARD_BEFOR ="10";
	
	public static final String KFT_FILE_TYPE_PER_CARD_BACK ="11";
	
	public static final String KFT_FILE_TYPE_PER_HAND ="12";
	
	public static final String KFT_FILE_TYPE_PER_BANKCARD_BEFOR ="13";
	
	public static final String KFT_FILE_TYPE_PER_BANKCARD_BACK ="14";
	
	public static final String KFT_FILE_TYPE_PER_DOOR ="16";
	
	public static final String KFT_FILE_TYPE_MER_BUSINESS ="21";
	
	public static final String KFT_FILE_TYPE_MER_CARD_BEFOR ="25";
	
	public static final String KFT_FILE_TYPE_MER_CARD_BACK ="26";
	
	public static final String KFT_FILE_TYPE_MER_LICENCE ="27";
	
	public static final String KFT_FILE_TYPE_MER_DOOR ="29";
	
	/**
	 * 快付通 产品编号
	 * 	010101	微信扫码
		010102	微信公众号/H5
		010201	支付宝扫码
	 */
	public static final String KFT_PRO_WX_H5_PUB = "010102";
	
	public static final String KFT_PRO_AIPAY_QR = "010201";
	
	public static final String KFT_PRO_WX_QR = "010101";
}
