package com.qifenqian.bms.sms.message;

public class BaseMessagePath {

	public static final String BASE = "/sms/message";

	/**
	 * 列表
	 */
	public static final String LIST = "/list";
	
	/**
	 * 导入EXCEL
	 */
	public static final String IMPORT_EXCEL = "/importExcel";
	
	/**
	 * 批量发送
	 */
	public static final String BATCH_SEND = "/batchSend";
	
	/**
	 * 单个发送
	 */
	public static final String  SINGLE_SEND = "/singleSend";
	
	/**
	 * 删除
	 */
	public static final String  DELETE = "/delete";
	
	/**
	 * 批量删除
	 */
	public static final String  BATCH_DELETE = "/batchDelete";
	
	
}