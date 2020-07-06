package com.qifenqian.bms.merchant.merchantReported;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.PicturePath;
import com.qifenqian.bms.basemanager.merchant.service.MerchantEnterService;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.common.controller.CommonInfoService;
import com.qifenqian.bms.merchant.merchantReported.bean.MerchantDetailInfoAliPay;
import com.qifenqian.bms.merchant.merchantReported.bean.MerchantDetailInfoShuiXingPay;
import com.qifenqian.bms.merchant.merchantReported.bean.MerchantDetailInfoWechat;
import com.qifenqian.bms.merchant.merchantReported.service.MerchantDetailInfoAliPayService;
import com.qifenqian.bms.merchant.merchantReported.service.MerchantDetailInfoShuiXingPayService;
import com.qifenqian.bms.merchant.merchantReported.service.MerchantDetailInfoWechatService;
import com.qifenqian.bms.merchant.reported.bean.Bank;
import com.qifenqian.bms.merchant.reported.bean.ChannlInfo;
import com.qifenqian.bms.merchant.reported.bean.CommonIndustry;
import com.qifenqian.bms.merchant.reported.bean.Industry;
import com.qifenqian.bms.merchant.reported.bean.MerchantCity;
import com.qifenqian.bms.merchant.reported.bean.Province;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.service.CrIncomeService;
import com.qifenqian.bms.merchant.reported.service.FmIncomeService;
import com.qifenqian.bms.merchant.reported.service.WeChatAppService;

@Controller
@RequestMapping("/merchant")
public class MerchanEnterController {
	
	   @Autowired
	   private CrIncomeService crIncomeService;
	   
	   @Autowired
	   private FmIncomeService fmIncomeService;

	   @Autowired
	   private MerchantEnterService merchantEnterService;
	   
	   @Autowired
	   private MerchantService merchantService;
	   
	   @Autowired
	   private MerchantDetailInfoShuiXingPayService merchantDetailInfoShuiXingPayService;
	   
	   @Autowired
	   private FmIncomeMapperDao fmIncomeMapperDao;
	   
	   @Autowired
	   private CommonInfoService commonInfoService;
	   
	   @Autowired
	   private MerchantDetailInfoAliPayService merchantDetailInfoAliPayService;
	   
	   @Autowired
	   private WeChatAppService weChatAppService;
	   @Autowired
	   private MerchantDetailInfoWechatService merchantDetailInfoWechatService;
	/**
	 * @param request
	 * @param response
	 * @param merchantCode   zhanggc 随行付报备信息显示
	 * @param channlCode
	 * @param patchNo
	 * @param custId
	 * @return
	 */
	@RequestMapping("/merchantReported/suiXingMerchantReportShow")
	public ModelAndView showShuiXingPay(HttpServletRequest request,HttpServletResponse response,
			String merchantCode,String channlCode,String patchNo,String custId){
		ModelAndView mv = new ModelAndView();
		/***查询渠道***/
		List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
		/***查询随行付银行地区信息***/
		List<Province> proviceList = fmIncomeService.getSuiXingProvinceList();
		/***查询银行信息***/
		List<Bank> bankList = fmIncomeService.getBankList();
		/***查询随行付商户行业信息***/
		List<Industry> industryList = fmIncomeService.getSuiXingIndustryList();
		/***查询随行付商户注册地区信息***/
		String areaType ="2";
		List<MerchantCity> merchantProvinceList = fmIncomeService.getSuiXingMerchantCityList(areaType);
		
		if(null!=channlInfoList && channlInfoList.size()>0){
			mv.addObject("infoList", channlInfoList);
		}
		if(null!=proviceList && proviceList.size()>0){
			mv.addObject("provinceList", proviceList);
		}
		if(null!=bankList && bankList.size()>0){
			mv.addObject("bankList", bankList);
		}
		if(null!=merchantProvinceList && merchantProvinceList.size()>0){
			mv.addObject("merchantProvinceList", merchantProvinceList);
		}
		if(null!=industryList && industryList.size()>0){
			mv.addObject("industryList", industryList);
		}
		//	mv.addObject("status",status);
		MerchantDetailInfoShuiXingPay detailInfo = new MerchantDetailInfoShuiXingPay();
		detailInfo.setPatchNo(patchNo);
		MerchantDetailInfoShuiXingPay merchantDetailInfoShuiXing= merchantDetailInfoShuiXingPayService.getMerchantDetailInfoShuiXingPay(detailInfo);
		mv.addObject("merchantDetailInfo",merchantDetailInfoShuiXing);
		//获取图片路径
		MerchantVo merchantVo = new MerchantVo();
		merchantVo.setCustId(custId);
		PicturePath picturePath = merchantEnterService.getPicPath(merchantVo);
		mv.addObject("picturePathVo", picturePath);
		
		return mv;
	}
	
	/**
	 * @param request
	 * @param response    
	 * @param merchantCode
	 * @param channlCode   zhanggc 支付宝报备信息显示
	 * @param patchNo
	 * @param custId
	 * @return
	 */
	@RequestMapping("/merchantReported/aliPayMerchantReportShow")
	public ModelAndView merchantDetailInfoAliPayShow(HttpServletRequest request,HttpServletResponse response,
			String merchantCode,String channlCode,String patchNo,String custId){
		
		ModelAndView mv = new ModelAndView();
		/***查询行业信息***/
		List<CommonIndustry> industryList = commonInfoService.selectCommonIndustrys(channlCode, null, null);
		
		TdCustInfo  custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode); 
		mv.addObject("custInfo", custInfo);
		
		if(null!=industryList && industryList.size()>0){
			mv.addObject("industryList", industryList);
		}
		
		//获取图片路径
		MerchantVo merchantVo = new MerchantVo();
		merchantVo.setCustId(custId);
		PicturePath picturePath = merchantEnterService.getPicPath(merchantVo);
		mv.addObject("picturePathVo", picturePath);
		//回显信息表
		MerchantDetailInfoAliPay detail = new MerchantDetailInfoAliPay();
		detail.setPatchNo(patchNo);
		MerchantDetailInfoAliPay merchantDetailInfoAliPay = merchantDetailInfoAliPayService.getMerchantDetailInfoAliPay(detail);
		mv.addObject("merchantDetailInfoAliPay", merchantDetailInfoAliPay);
		return mv;
	}
	
	/**
	 * @param request
	 * @param response
	 * @param merchantCode  zhanggc 微信报备信息显示
	 * @param channlCode 
	 * @param patchNo
	 * @param custId
	 * @return
	 */
	@RequestMapping("/merchantReported/weChatAppMerchantReportShow")
	public ModelAndView merchantDetailInfoWechatShow(HttpServletRequest request,HttpServletResponse response,
			String merchantCode,String channlCode,String patchNo,String custId ){
		ModelAndView mv = new ModelAndView();
		/***查询省份***/
		//查询微信报备信息 
		MerchantDetailInfoWechat Wechat = new MerchantDetailInfoWechat();
		Wechat.setPatchNo(patchNo);
		MerchantDetailInfoWechat merchantDetailInfoWechat = merchantDetailInfoWechatService.getMerchantDetailInfoWechat(Wechat);
		mv.addObject("merchantDetailInfoWechat",merchantDetailInfoWechat);
		return mv;
	}
	
}
