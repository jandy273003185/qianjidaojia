package com.qifenqian.bms.basemanager.agency.controller;

/**
 * @desc			代理商注册相关路径
 * @date			2017年6月9日上午11:48:52
 * @version			v1.0
 */
public class AgentRegisterPath {
	/** 代理商注册相关 **/
	/** 窄化请求 */ 				public final static String BASE = 						"/basemanager/agency";  
	/** 注册页面 */					public final static String REGISTPAGE =					"/registPage"; 
	/** 注册 */					public final static String REGISTER = 					"/register"; 
	/** 注册 */					public final static String REGISTERENTER = 					"/registerEnter";
	/** 图片上传 */					public final static String FILEUPLOAD=					"/fileUpload";
	/** 校验邮件地址是否被占用 */		public final static String VALIDATEEMAIL = 			    "/validateemail"; 
	/** 预览代理商图片信息 */			public final static String PREVIEWAGENTIMAGE = 	        "/previewAgentImage"; 
	/** 校验身份证是否被占用 */		    public final static String VALIDATECARDID =             "/validateCardID";
	/** 校验手机号是否被占用 */		    public final static String VALIDATEMOBILE =             "/validateMobile";
	/** 校验营业执照是否被占用 */		public final static String VALIDATELICENSE =             "/validateLicense";
	/** 优图 */					public final static String YOUTU = "/youTu";
}
