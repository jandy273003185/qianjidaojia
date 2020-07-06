package com.qifenqian.bms.merchant.reported;

/**
 * @project sevenpay-bms-web
 * @fileName MerchantSettlePath.java
 * @author huiquan.ma
 * @date 2015年10月10日
 * @memo 
 */
public class MerchantReportedPath {

	public static final String BASE = "/merchant";

	/** 查询列表*/
	public static final String LIST = "/reported/reportedList";
	/** 导出报表*/
	public static final String EXPORT = "/reported/export";
	/**页面入口*/
	public static final String BMSREPORT="/reported/merchantReported";
	
	/** 查询户报备 *//*
	public final static String SELECTLIST = "/reported/merchantReported";*/
	
	/** 查询商户是否可报备*/
	public static final String CHECKMERCHANTREPORT="/reported/checkMerchantReport";
	
	/**查询商户渠道*/
	public static final String GETCHANNL="/reported/getChannl";
	
	/**提交报备*/
	public static final String GOTOREPORT="/reported/goToReport";
	
	public static final String QUERYCATEGORYINFO="/reported/queryCategoryInfo";
	
	
	
	
	
	/**********************************新商户报备*****************************************/
	/**页面入口*/
	public static final String BMSMERCHANTREPORT="/reported/merchantReporteds";
	/**查询市*/
	public static final String BMSSELCITY="/reported/bmsSelCity";
	/**查询区*/
	public static final String BMSSELAREA="/reported/bmsSelArea";
	/**查询开户支行号*/
	public static final String BMSSELINTERBANK="/reported/bmsSelInterBank";
	/**查询行目类别*/
	public static final String BMSSELFIRSTTYPE="/reported/bmsSelFirstType";
	/**提交报备*/
	public static final String SUBMITREPORT="/reported/submitReport";
	/**文件上传*/
	public static final String FILEUPLOAD="/reported/fileUpload";
	/**翼支付进件详情*/
	public static final String SELECTMERCHANTREPORTINFO="/reported/merchantReportInfo";
	/**翼支付更新进件*/
	public static final String UPDATESUBMITREPORT="/reported/updateSubmitReport";
	
	/**进件状态查询*/
	public static final String SELECTMERCHANTREPORTSTATUS="/reported/merchantReportStatus";
	
	
	/**********************************翼支付报备*****************************************/
	/**选择渠道入口*/
	public static final String SELMERCHANTREPORTS="/reported/selMerchantReporteds";
	
	/**翼支付页面入口*/
	public static final String BESTPAYMERCHANTREPORTS="/reported/bestPayMerchantReporteds";
	/**翼支付进件**/
	public static final String BESTPAYSUBMITREPORT = "/reported/bestPaySubmitReport";
	
	/**随行付页面入口*/
	public static final String SUIXINGPAYMERCHANTREPORTS="/reported/suiXingPayMerchantReporteds";
	/**查询随行付银行对应省市*/
	public static final String SELSUIXINGCITY="/reported/selSuiXingCity";
	/**查询随行付商户注册地区*/
	public static final String SELSUIXINGAREA="/reported/selSuiXingArea";
	
	/**随行付上传文件*/
	public static final String SELSUIXINGFILEUPLOAD="/reported/selSuiXingFileUpload";
	/**随行付进件**/
	public static final String SUXINGPAYSUBMITREPORT = "/reported/suiXingPaySubmitReport";
	
	/**翼支付企业页面入口*/
	public static final String BESTPAYCOMERCHANTREPORTS="/reported/bestPayCoMerchantReporteds";
	
	/**翼支付企业进件**/
	public static final String BESTPAYCOSUBMITREPORT = "/reported/bestPayCoSubmitReport";
	
	/**翼支付企业签约**/
	public static final String CONTRACTPRODUCT = "/reported/contractProduct";
	
	
	/**********************************商盟报备*****************************************/
	
	/**商盟页面入口*/
	public static final String SUMPAYMERCHANTREPORTS="/reported/sumpayMerchantReporteds";
	
	/**商盟信息提交*/
	public static final String SUMPAYINFOMERCHANTREPORT="/reported/sumpayInfoMerchantReporteds";
	
	/**查询商盟市*/
	public static final String SUMPAYSELCITY="/reported/sumPaySelCity";
	
	/**商盟纸质资料更新/merchant*/
	public static final String SUMPAYMERCHANTINFOREPORTS="/reported/sumpayMerchantInfoReporteds";
	
	/**商盟图片信息提交*/
	public static final String SUMPAYPHOTOINFOMERCHANTREPORT="/reported/sumpayPhotoInfoMerchantReporteds";
	
	/**商盟开通产品**/
	public static final String OPENPRODUCT = "/reported/openProduct";
	
	/**商盟支付宝产品查询**/
	public static final String SELZFBPRODUCT = "/reported/selZFBProduct";
	
	/**商盟支付宝产品MCC查询**/
	public static final String SELZFBPRODUCTMCC = "/reported/selZFBProductMcc";
	
	/**商盟微信产品查询**/
	public static final String SELWXPRODUCT = "/reported/selWXProduct";
	
	/**商盟微信产品MCC查询**/
	public static final String SELWXPRODUCTMCC = "/reported/selWXProductMcc";
	
	
	/**********************************商盟报备*****************************************/
	
	/**平安付页面入口*/
	public static final String YQBMERCHANTREPORTS="/reported/yqbMerchantReporteds";
	
	/**查询平安付市*/
	public static final String YQBSELCITY="/reported/yqbSelCity";
	
	/**查询平安付地区*/
	public static final String YQBSELAREA="/reported/yqbSelArea";
	
	/**查询平安付行业信息*/
	public static final String YQBSELINDUSTRY="/reported/yqbSelIndustry";
	
	/**平安付信息提交*/
	public static final String YQBINFOMERCHANTREPORT="/reported/yqbInfoMerchantReporteds";
	
	/** 查询平安付银行*/
	public static final String YQBSELBANK = "/reported/yqbSelBank";
}


