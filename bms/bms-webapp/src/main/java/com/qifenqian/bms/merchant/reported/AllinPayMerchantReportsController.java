package com.qifenqian.bms.merchant.reported;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.PicturePath;
import com.qifenqian.bms.basemanager.merchant.service.MerchantEnterService;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.merchant.reported.bean.AllinPayBean;
import com.qifenqian.bms.merchant.reported.bean.Bank;
import com.qifenqian.bms.merchant.reported.bean.Industry;
import com.qifenqian.bms.merchant.reported.bean.Province;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoAllinPay;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantReportInfo;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.service.AllinPayService;
import com.qifenqian.jellyfish.bean.merregist.allinpay.AllinpayMerchantQueryRes;

@Controller
@RequestMapping("/merchant")
public class AllinPayMerchantReportsController {
	
	 private Logger logger = LoggerFactory.getLogger(AllinPayMerchantReportsController.class);
	 
	 @Autowired
	 private FmIncomeMapperDao fmIncomeMapperDao;
	 
	 @Autowired
	 private MerchantEnterService merchantEnterService;
	 
	 @Autowired
	 private AllinPayService allinPayService;
	 
	/**
	 * 通联商户报备入口
	*/
	@RequestMapping("/merchantReported/allinPayMerchantReport")
	public ModelAndView  viewMerchantReported(HttpServletRequest request,HttpServletResponse response,String channlCode,String merchantCode,String status){
		
		ModelAndView mv = new ModelAndView();
		MerchantVo merchantVo = new MerchantVo();
		TdMerchantDetailInfo detail = new TdMerchantDetailInfo();
		detail.setMerchantCode(merchantCode);
		detail.setChannelNo(channlCode);

		/***查询客户信息***/
		TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
		/***查询商户行业信息***/
		List<Industry> industryList = allinPayService.getAllinPayIndustryList();
		/***查询省份***/
		List<Province> allinPayAreaInfoList = allinPayService.getProvinceName();
		/***查询银行***/
		List<Bank>  bankList =  allinPayService.getBankInfo();
		/***获取图片路径***/
		merchantVo.setCustId(custInfo.getCustId());
		merchantVo.setAuthId(custInfo.getAuthId());
		PicturePath picturePath = merchantEnterService.getPicPath(merchantVo);
		
		mv.addObject("custInfo",custInfo);
		mv.addObject("industryList", industryList);
		mv.addObject("allinPayAreaInfoList",allinPayAreaInfoList);
		mv.addObject("bankList",bankList);
		mv.addObject("picturePathVo", picturePath); 
		
		return mv;
	}
	
	/**
	 * 通联商户信息修改入口
	*/
	@RequestMapping("/merchantReported/allinPayEditReported")
	public ModelAndView  viewAllinPayEditReported(String channlCode, String merchantCode, String patchNo){
		ModelAndView mv = new ModelAndView();
		TdMerchantDetailInfo detail = new TdMerchantDetailInfo();
		detail.setMerchantCode(merchantCode);
		detail.setChannelNo(channlCode);
		
		TdMerchantDetailInfoAllinPay merchantDetailInfo = allinPayService.getAllinPayTdMerchantDetail(patchNo);
		mv.addObject("merchantDetailInfo", merchantDetailInfo);
		/***查询客户信息***/
		//TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
		/***查询省份***/
		List<Province> allinPayAreaInfoList = allinPayService.getProvinceName();
		/***查询银行***/
		List<Bank>  bankList =  allinPayService.getBankInfo();
		
		//mv.addObject("custInfo",custInfo);
		mv.addObject("allinPayAreaInfoList",allinPayAreaInfoList);
		mv.addObject("bankList",bankList);
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/merchantReported/allinPayEditReportedSubmit")
	public Map<String, String> allinPayEditReportedSubmit(AllinPayBean cr){
		Map<String, String> allinPayEditReported = allinPayService.allinPayEditReported(cr);
		return allinPayEditReported;
	}
	
	

	/**
	 * 通联商户报备提交
	*/
	@RequestMapping("/merchantReported/allinPayMerchantReportSubmit")
	@ResponseBody
	public String reportUpgradeSubmit(AllinPayBean cr){
		JSONObject object = new JSONObject();
		Map<String, Object> allinPayResult = new HashMap<String, Object>();
		try {
			//查询该商户是否已报备
			TdMerchantReportInfo reportBean = new TdMerchantReportInfo();
			reportBean.setMerchantCode(cr.getMerchantCode());
			reportBean.setChannelNo(cr.getChannelNo());
			TdMerchantReportInfo reportInfo = allinPayService.getTdMerchantReport(reportBean);
			if(reportInfo != null){
				//该商户已报备
				if("Y".equals(reportInfo.getReportStatus()) || "O".equals(reportInfo.getReportStatus())){
					object.put("result", "FAIL");
					object.put("message", "商户已经报备，请勿重新提交");
					return object.toString();
				}else{
					reportInfo.setReportStatus("E");
					//修改商户报备表
					allinPayService.updateTdMerchantReport(reportInfo);
					//修改商户报备明细表和产品表
					allinPayService.updateTdMerchantInfoAllinPay(cr);
				}
			}else{
				//添加商户报备表（td_merchant_report）
				TdMerchantReportInfo info = new TdMerchantReportInfo();
				info.setId(GenSN.getSN());
				info.setPatchNo(GenSN.getSN());
				info.setMerchantCode(cr.getMerchantCode().trim());
				info.setChannelNo(cr.getChannelNo());
				info.setReportStatus("E");
				//添加商户报备表
				allinPayService.insertTdMerchantReport(info);
				//添加商户报备明细表和产品表
				cr.setPatchNo(info.getPatchNo());
				cr.setReportStatus("99");
				allinPayService.insertTdMerchantInfoAllinPay(cr);
			}
			//商户通联进件
			logger.info("商户通联开始进件："+ "--------------------" + cr);
			allinPayResult = allinPayService.allinPyaAppReported(cr);
			logger.info("商户通联进件结束："+ "--------------------" + allinPayResult);
			if("SUCCESS".equals(allinPayResult.get("result"))){
				object.put("result", "SUCCESS");
				object.put("message", "报备成功");
			}else{
				object.put("result", "FAILURE");
				if(allinPayResult.get("message") == "" && allinPayResult.get("message") == null){
					object.put("message", "通联进件明确失败");
					return object.toString();
				}else {
					object.put("message", allinPayResult.get("message"));
					return object.toString();
				}
			}
		
		} catch (Exception e) {
			logger.error("通联进件失败",e);
			object.put("result", "FAILURE");
			object.put("message", e);
			return object.toString();
		}
		return object.toString();
		
	}
	
	/**
	 * 通联进件成功商户信息查询
	*/
	@RequestMapping("/merchantReported/allinPayMerchantReportQuery")
	@ResponseBody
	public String reportQuery(HttpServletRequest request,HttpServletResponse response,AllinPayBean cr){
		JSONObject object = new JSONObject();
		//商户通联进件
		logger.info("商户进件成功商户信息查询："+ "--------------------");
		AllinpayMerchantQueryRes allinPayResult = allinPayService.allinPyaAppQuery(cr);
		logger.info("商户进件成功商户信息查询结束："+ "--------------------");
		object.put("allinPayResult", allinPayResult);
		return object.toString();
	
	
	}
	
	
	/**
	 * 无纸化进件电子协议URL接口查询
	*/
	@RequestMapping("/merchantReported/allinPayMerchantReportQueryElectUrl")
	@ResponseBody
	public String reportQueryElectUrl(HttpServletRequest request,HttpServletResponse response,AllinPayBean cr){
		JSONObject object = new JSONObject();
		//商户通联进件
		logger.info("商户进件成功商户信息查询："+ "--------------------");
		//获取MCHID
		AllinpayMerchantQueryRes allinPayResult = allinPayService.queryElect(cr);
		logger.info("商户进件成功商户信息查询结束："+ "--------------------");
		object.put("allinPayResult", allinPayResult);
		return object.toString();
	
	
	}
	
	/**
	 * 无纸化进件电子协议URL接口重发
	*/
	@RequestMapping("/merchantReported/allinPayMerchantReportQueryElectSign")
	@ResponseBody
	public String reportQueryElectSign(HttpServletRequest request,HttpServletResponse response,AllinPayBean cr){
		JSONObject object = new JSONObject();
		//商户通联进件
		logger.info("商户进件成功商户信息查询："+ "--------------------");
		//获取MCHID
		AllinpayMerchantQueryRes allinPayResult = allinPayService.queryElectSign(cr);
		logger.info("商户进件成功商户信息查询结束："+ "--------------------");
		object.put("allinPayResult", allinPayResult);
		return object.toString();
	
	
	}
	
}
