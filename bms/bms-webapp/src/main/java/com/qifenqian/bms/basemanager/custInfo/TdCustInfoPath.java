package com.qifenqian.bms.basemanager.custInfo;

/**
 * 消费者信息
 * @author pc
 *
 */
public final class TdCustInfoPath {

	/** 消费者信息*/
	public final static String BASE = "/basemanager/custInfo";
	
	/** 消费者信息列表*/
	public final static String LIST = "/list";
	/** 修改客户信息*/
	
	public final static String EDIT = "/update";
	
	/**
	 * 重置支付密码
	 */
	public final static String PAYPASSWORDEDIT = "/paypasswordEdit";
	
	/** 查看七分钱、七分宝余额*/
	public final static String QUERYACCOUNT ="/queryAccount";
	
	/** 查看消费者卡绑定信息*/
	public final static String BANKCARD ="/bankCard";
	
	/** 导出用户信息*/
	public final static String EXPORTUSERINFO ="/exportUserinfo";
	
	public final static String  VALIDATEMOBILE="/validateMobile";
	
	public final static String  VALIDATEEMAIL="/validateEmail";
	
	public final static String SEND_MESSAGE ="/sendMessage";
	
	public final static String SEND_MESSAGE_ASYN ="/sendMessageAsyn";
}


