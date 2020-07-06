package com.qifenqian.bms.basemanager.merchant;

/**
 * 商户注册相关路径
 * 
 * @project sevenpay-bms-web
 * @fileName MerchantPath.java
 * @author Dayet
 * @date 2015年5月12日
 * @memo 
 */
public final class MerchantPath {

	/** 商户注册*/
	public final static String BASE = "/merchant/regist";
	
	public final static String LIST = "/list";
	
	public final static String NEWLIST ="/newList";
	public final static String BACKLIST = "/backList";
	
	
	public final static String PROLIST = "/proList";
	
	/** 注册页面 */
	public final static String REGISTPAGE ="/registPage";
	
	/** 商户注册 */
	public final static String REGIST ="/regist";
	
	/** 商户信息导出 */
	public final static String EXPORTMERCHANTINFO ="/exportMerchantInfo";
	
	
	/** 前台商户审核信息导出 */
	public final static String PROEXPORTMERCHANTINFO ="/proExportMerchantInfo";
	
	/** 后台商户审核信息导出 */
	public final static String BACKEXPORTMERCHANTINFO ="/backExportMerchantInfo";
	
	/** 邮箱验证*/
	public final static String VALIDATE="/validate";
	
	/** 邮箱验证*/
	public final static String VALIDATEMOBILE="/validateMobile";
	
	/** 邮箱商户名称*/
	public final static String VALIDATEMERCHANTNAME="/validateMerchantName";
	
	/** 营业执照注册号验证*/
	public final static String VALIDATELICENSE="/validateLicense";
	
	/** 组织机构代码验证*/
	public final static String VALIDATEORGINSTCODE="/validateOrgInstCode";
	
	/** 文件上传*/
	public final static String FILEUPLOAD="/fileUpload";

	//  修改 邮箱
	public final static String UPDATEEMAIL="/updateEmail";
	
	// 发送邮件
	public final static String SENDEMAIL="/sendEmail";
	
	//  根据ID查找商户信息
	public final static String FINDMERCHANTINFO="/findMerchantInfo";
	
	//  修改商户注册信息
	public final static String UPDATEMERCHANTINFO="/updateMerchantInfo";
	
	//  修改商户文件上传
	public final static String UPDATEFILEUPLOAD="/updateFileUpload";
	
	//  预览
	public final static String PREMERCHANTINFO="/preMerchantInfo";
	
	/** 一级审核通过 */
	public final static String FIRSTPASS="/firstPass";
	
	/** 一级审核不通过 */
	public final static String FIRSTNOTPASS="/firstNotPass";
	
	/** 二级级审核通过 */
	public final static String SECONDPASS="/secondPass";
	
	/** 二级级审核不通过 */
	public final static String SECONDNOTPASS="/secondNotPass";
	
	/** 省市查询 */
	public final static String GRTCITYLIST="/getCityList";
	
	/** 商户信息导出 */
	public final static String NEWEXPORTMERCHANTINFO ="/newExportMerchantInfo";
}


