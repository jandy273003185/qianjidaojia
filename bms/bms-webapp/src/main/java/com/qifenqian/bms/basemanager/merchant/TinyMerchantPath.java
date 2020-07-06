package com.qifenqian.bms.basemanager.merchant;

/**
 * @desc			微商注册相关路径
 * @author 			tanchuan
 * @date			2017年3月10日上午11:48:52
 * @version			v1.0
 */
public class TinyMerchantPath {
	/** 微商户注册相关 **/
	/** 窄化请求 */ 					public final static String BASE = 						"/tinymerchant/regist";  
	/** 注册页面 */						public final static String REGISTPAGE =					"/registPage"; 
	/** 注册 */						public final static String REGISTER = 					"/register"; 
	/** 图片上传 */						public final static String FILEUPLOAD=					"/fileUpload";
	/** 微商户列表 */					public final static String LIST = 						"/list"; 
	/** 校验商户二维码编号 */				public final static String VALIDATEMERCHANTCODE = 		"/validateMerchantCode"; 
	/** 校验邮件地址是否被占用 */			public final static String VALIDATEEMAIL = 				"/validateemail"; 
	/** 验证营业执照注册号是否被占用 */		public final static String VALIDATELICENSE = 			"/validateLicense"; 
	
	/** 验证手机号码是否被占用 */		public final static String VALIDATEMOBILE = 			"/validateMobile"; 
	
	/** 微商户列表相关 **/
	/** 修改邮箱地址 */ 					public final static String UPDATEEMAIL = 				"/updateEmail"; 
	/** 修改商户信息 */					public final static String UPDATEMERCHANTINFO = 		"/updateTinyMerchantInfo"; 
	/** 导出微商户列表 */					public final static String EXPORTMERCHANTINFO = 		"/exportTinyMerchantInfo"; 
	/** 根据ID查找微商户信息 */			public final static String FINDTINYMERCHANTINFO = 		"/findTinyMerchantInfo";  
	/** 修改微商户信息 */					public final static String UPDATETINYMERCHANTINFO = 	"/updateTinyMerchantInfo"; 
	/** 更新微商户图片信息 */				public final static String UPDATEFILEUPLOAD = 			"/updateFileupload"; 
	/** 预览微商户图片信息 */				public final static String PREVIEWTINYMERCHANTIMAGE = 	"/previewTinyMerchantImage"; 
	/** 获取行业类目 */ 					public final static String GETCATEGORYTYPE =  "/getCategoryType";
	/** 获取行业类目 */ 					public final static String GETCATEGORYTYPEBYID =  "/getCategoryTypeById";
	
	/** 获取地区信息 */ 					public final static String AREAINFO =  "/areaInfo";
	/** 根据编号获取地区信息*/ 			public final static String GETAREAINFOBYID =  "/getAreaInfoById";
	/** 根据编号获取图片信息*/            public final static String GETMERCHANTIMG =  "/getMerchantImg";
}
