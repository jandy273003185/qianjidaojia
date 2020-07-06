package com.qifenqian.bms.merchant.merchantReported;
import java.util.List;

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
import com.qifenqian.bms.merchant.merchantReported.bean.KFTArea;
import com.qifenqian.bms.merchant.merchantReported.bean.KFTMccBean;
import com.qifenqian.bms.merchant.merchantReported.mapper.KftIncomeMapper;
import com.qifenqian.bms.merchant.merchantReported.service.KFTIncomeService;
import com.qifenqian.bms.merchant.merchantReported.service.MerchantEnterReportService;
import com.qifenqian.bms.merchant.reported.bean.Bank;
import com.qifenqian.bms.merchant.reported.bean.ChannlInfo;
import com.qifenqian.bms.merchant.reported.bean.Industry;
import com.qifenqian.bms.merchant.reported.bean.MerchantCity;
import com.qifenqian.bms.merchant.reported.bean.Province;
import com.qifenqian.bms.merchant.reported.bean.TbFmTradeInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.bean.YQBArea;
import com.qifenqian.bms.merchant.reported.bean.YQBIndustry;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.mapper.FmIncomeMapper;
import com.qifenqian.bms.merchant.reported.service.CrIncomeService;
import com.qifenqian.bms.merchant.reported.service.FmIncomeService;


@Controller
@RequestMapping(MerchantEnterReportedPath.BASE)
public class MerchantEnterReportedController {

   private Logger logger = LoggerFactory.getLogger(MerchantEnterReportedController.class);
	
   @Autowired
   private KFTIncomeService kFTIncomeService;
	
   @Autowired
   private CrIncomeService crIncomeService;
   
   @Autowired
   private FmIncomeService fmIncomeService;
   
   @Autowired
   private FmIncomeMapperDao fmIncomeMapperDao;
   
   @Autowired
   private KftIncomeMapper kftIncomeMapper;
   
   @Autowired
   private MerchantEnterService merchantEnterService;
   
   @Autowired
   private MerchantEnterReportService merchantEnterReportService;
   
   @Autowired
   private FmIncomeMapper fmIncomeMapper;
   
	
   /**
            *    商户报备列表
    * @param request
    * @param response
    * @param detail
    * @return
    */
 	@RequestMapping(MerchantEnterReportedPath.LIST)
 	public ModelAndView  reportedList(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo detail){
 		ModelAndView mv = new ModelAndView();
 		/***查询渠道信息***/
 		List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
 		/***查询报备信息***/
 		List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
 		//分表合并list查询报备信息
// 		List<TdMerchantDetailInfo> reportedList = merchantEnterReportService.getMerchantDetailInfoList(detail);
 		/***查询商户信息***/
 		TdCustInfo custInfo = new TdCustInfo();
 		/***参数回显***/
 		mv.addObject("queryBean", detail);
 		
 		if(null != detail.getMerchantCode()){
 			custInfo = fmIncomeMapperDao.getInComeInfo(detail.getMerchantCode());
 		}
 		if(null!=reportedList && reportedList.size()>0){
 			mv.addObject("reportedList", reportedList);
 		}
 		if(null!=channlInfoList && channlInfoList.size()>0){
 			mv.addObject("infoList", channlInfoList);
 		}
 		if(null!=custInfo){
 			mv.addObject("custInfo", custInfo);
 		}
 		return mv;
 	}	   
 	
   /**
    * 商户报备查询
    */
	@RequestMapping(MerchantEnterReportedPath.QUERYSTATUS)
	@ResponseBody
	public String  queryStatus(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo detail){
		JSONObject object = new JSONObject();
		logger.info("----------------------七分钱报备查询-----------------------");
		/***查询渠道***/
//		List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
		/***查询报备信息***/
		TdMerchantDetailInfo detailInfo = fmIncomeMapper.selTdMerchantDetailInfo(detail);
		List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
		logger.info("reportedList:"+reportedList);
		TdCustInfo custInfo = new TdCustInfo();
		if(null != detail.getMerchantCode()){
			custInfo = fmIncomeMapperDao.getInComeInfo(detail.getMerchantCode());
			if(null != custInfo){
				object.put("custInfo", custInfo);
				//审核通过再判断是否已经报备
//				if(null!=reportedList && reportedList.size()>0){
//					for(int i=0;i<reportedList.size();i++){
//						if("1".equals(reportedList.get(i).getReportStatus()) ||"0".equals(reportedList.get(i).getReportStatus()) ||"00".equals(reportedList.get(i).getReportStatus())){
//							object.put("result", "SUCCESS");
//							return object.toString();
//						}
//					}
//					object.put("result", "FAIL");
//				}else{
//					object.put("result", "FAIL");
//				}
				//审核通过再判断是否已经报备
				if(null!=detailInfo) {
					if("Y".equals(detailInfo.getReportStatus())) {
						object.put("result", "SUCCESS");
						return object.toString();
					}else {
						object.put("result", "FAIL");
					}
				}else {
					object.put("result", "FAIL");
				}
			}else{
				logger.info("--------------商户未通过审核，请查看商户状态----------------");
				custInfo = kftIncomeMapper.selInComeInfo(detail.getMerchantCode());
				object.put("custId", custInfo.getCustId());
				object.put("status","01");
				object.put("result", "FAIL");
			}
		}
		
		return object.toString();
	}	
	
   /**
    * 随行付报备资料页面
    */
	@RequestMapping(MerchantEnterReportedPath.SUIXINGMERCHANTREPORT)
	public ModelAndView  viewSuiXingMerchantReported(HttpServletRequest request,HttpServletResponse response,String merchantCode,String channlCode,String status){
		ModelAndView mv = new ModelAndView();
		/*if(null == detail || null == detail.getMerchantCode() ){
			detail.setMerchantCode(merchantCode);
		}
		if(null ==detail || null == detail.getChannelNo()){
			detail.setChannelNo(channlCode);
		}*/
		/***查询渠道***/
		List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
		/***查询报备信息***/
//		List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
		/***查询随行付银行地区信息***/
		List<Province> proviceList = fmIncomeService.getSuiXingProvinceList();
		/***查询银行信息***/
		List<Bank> bankList = fmIncomeService.getBankList();
		/***查询随行付商户行业信息***/
		List<Industry> industryList = fmIncomeService.getSuiXingIndustryList();
		/***查询随行付商户注册地区信息***/
		String areaType ="2";
		List<MerchantCity> merchantProvinceList = fmIncomeService.getSuiXingMerchantCityList(areaType);
//		merchantCode = detail.getMerchantCode();
		TdCustInfo custInfo = new TdCustInfo();
		if(null != merchantCode){
			custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
			if(null != custInfo){
				mv.addObject("custInfo", custInfo);
			}else{
				logger.info("--------------商户未通过审核，请查看商户状态----------------");
				status = "商户未通过审核，请查看商户状态";
				mv.addObject("status",status);
				return mv;
			}
		}
		//获取图片路径
		MerchantVo merchantVo = new MerchantVo();
		merchantVo.setAuthId(custInfo.getAuthId());
		merchantVo.setCustId(custInfo.getCustId());
		PicturePath picturePath = merchantEnterService.getPicPath(merchantVo);
		mv.addObject("picturePathVo", picturePath);
		/*if(null!=reportedList && reportedList.size()>0){
			mv.addObject("reportedList", reportedList);
			String remark =  reportedList.get(0).getRemark();
			mv.addObject("remark", remark);
		}*/
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
		
		mv.addObject("status",status);
		return mv;
	}

	
	/**
    * 平安付报备入口
    */
	@RequestMapping(MerchantEnterReportedPath.YQBMERCHANTREPORT)
	public ModelAndView  viewYQBMerchantReported(HttpServletRequest request,HttpServletResponse response,String channlCode,String merchantCode,String status){
		ModelAndView mv = new ModelAndView();
//		String channlCode = "YQB";
		/*if(null == detail || null == detail.getMerchantCode() ){
			detail.setMerchantCode(merchantCode);
		}
		if(null ==detail || null == detail.getChannelNo()){
			detail.setChannelNo(channlCode);
		}*/
		/***查询渠道***/
		List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
		/***查询报备信息***/
//			List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
		/***查询省份信息***/
		List<YQBArea> proviceList = fmIncomeService.getYQBProvinceList();
		/***查询行业信息***/
		List<YQBIndustry> industryList = fmIncomeService.getYQBIndustryList();
		/***查询银行信息***/
		Bank bank = new Bank();
		List<Bank> bankIdList = fmIncomeService.getYQBBankList(bank);
		
//		merchantCode = detail.getMerchantCode();
		TdCustInfo custInfo = new TdCustInfo();
		if(null != merchantCode){
			custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
			if(null != custInfo){
				mv.addObject("custInfo", custInfo);
			}else{
				logger.info("--------------商户未通过审核，请查看商户状态----------------");
				status = "商户未通过审核，请查看商户状态";
				mv.addObject("status",status);
				return mv;
			}
		}
		/*if(null!=reportedList && reportedList.size()>0){
			mv.addObject("reportedList", reportedList);
		}*/
		if(null!=channlInfoList && channlInfoList.size()>0){
			mv.addObject("infoList", channlInfoList);
		}
		if(null!=proviceList && proviceList.size()>0){
			mv.addObject("provinceList", proviceList);
		}
		if(null!=bankIdList && bankIdList.size()>0){
			mv.addObject("bankIdList", bankIdList);
		}
		if(null!=industryList && industryList.size()>0){
			mv.addObject("industryList", industryList);
		}
		
		mv.addObject("status",status);
		return mv;
	}	
		
	
	/**
    * 快付通报备入口
    */
	@RequestMapping(MerchantEnterReportedPath.KFTMERCHANTREPORT)
	public ModelAndView  viewKFTMerchantReported(HttpServletRequest request,HttpServletResponse response,String channlCode,String merchantCode,String status){
		ModelAndView mv = new ModelAndView();
		/*if(null == detail || null == detail.getMerchantCode() ){
			detail.setMerchantCode(merchantCode);
		}
		if(null ==detail || null == detail.getChannelNo()){
			detail.setChannelNo(channlCode);
		}*/
		/***查询渠道***/
		List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
		/***查询报备信息***/
//		List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
		/***查询快付通地区信息***/
		List<KFTArea> provinceList = kFTIncomeService.getKftProvinceList();
		/***查询银行信息***/
		List<Bank> bankList = fmIncomeService.getBankList();
		/***查询快付通行业信息***/
		List<KFTMccBean> industryList = kFTIncomeService.getKftIndustryList();
//		merchantCode = detail.getMerchantCode();
		TdCustInfo custInfo = new TdCustInfo();
		if(null != merchantCode){
			custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
			if(null != custInfo){
				mv.addObject("custInfo", custInfo);
			}else{
				logger.info("--------------商户未通过审核，请查看商户状态----------------");
				status = "商户未通过审核，请查看商户状态";
				mv.addObject("status",status);
				return mv;
			}
		}
		/*if(null!=reportedList && reportedList.size()>0){
			mv.addObject("reportedList", reportedList);
			String remark =  reportedList.get(0).getRemark();
			mv.addObject("remark", remark);
		}*/
		if(null!=channlInfoList && channlInfoList.size()>0){
			mv.addObject("infoList", channlInfoList);
		}
		if(null!=provinceList && provinceList.size()>0){
			mv.addObject("provinceList", provinceList);
		}
		if(null!=bankList && bankList.size()>0){
			mv.addObject("bankList", bankList);
		}
		if(null!=industryList && industryList.size()>0){
			mv.addObject("industryList", industryList);
		}
		mv.addObject("status",status);
		return mv;
	}
	
	
	/**查询快付通二级行业信息*/
	@RequestMapping(MerchantEnterReportedPath.KFTSELINDUSTRYTWO)
	@ResponseBody
	public String selIndustryTwo(HttpServletRequest request,HttpServletResponse response,KFTMccBean kFTMccBean){
		JSONObject object = new JSONObject();
		List<KFTMccBean> kftIndustryTwoList = kFTIncomeService.getKftIndustryTwoList(kFTMccBean);
		if(null!=kftIndustryTwoList &&kftIndustryTwoList.size()>0){
			object.put("result", "SUCCESS");
			object.put("kftIndustryTwoList", kftIndustryTwoList);
		}else{
			object.put("result", "FAIL");
			object.put("message", "一级行业类目为空");
		}
		return object.toString();
	}
	
	/**查询快付通三级行业信息*/
	@RequestMapping(MerchantEnterReportedPath.KFTSELINDUSTRYTHREE)
	@ResponseBody
	public String selIndustryThree(HttpServletRequest request,HttpServletResponse response,KFTMccBean kFTMccBean){
		JSONObject object = new JSONObject();
		List<KFTMccBean> kftIndustryThreeList = kFTIncomeService.getKftIndustryThreeList(kFTMccBean);
		if(null!=kftIndustryThreeList &&kftIndustryThreeList.size()>0){
			object.put("result", "SUCCESS");
			object.put("kftIndustryThreeList", kftIndustryThreeList);
		}else{
			object.put("result", "FAIL");
			object.put("message", "二级行业类目为空");
		}
		return object.toString();
	}
	
	
	/**查询快付通市*/
	@RequestMapping(MerchantEnterReportedPath.SELKFTCITY)
	@ResponseBody
	public String selCity(HttpServletRequest request,HttpServletResponse response,KFTArea kftArea){
		JSONObject object = new JSONObject();
		List<KFTArea> kftCityList = kFTIncomeService.getKftCityList(kftArea);
		if(null!=kftCityList &&kftCityList.size()>0){
			object.put("result", "SUCCESS");
			object.put("kftCityList", kftCityList);
		}else{
			object.put("result", "FAIL");
			object.put("message", "省份编号为空");
		}
		return object.toString();
	}
	
	
	/**查询快付通地区*/
	@RequestMapping(MerchantEnterReportedPath.SELKFTAREA)
	@ResponseBody
	public String selArea(HttpServletRequest request,HttpServletResponse response,KFTArea kftArea){
		JSONObject object = new JSONObject();
		List<KFTArea> kftAreaList = kFTIncomeService.getKftAreaList(kftArea);
		if(null!=kftAreaList &&kftAreaList.size()>0){
			object.put("result", "SUCCESS");
			object.put("kftAreaList", kftAreaList);
		}else{
			object.put("result", "FAIL");
			object.put("message", "省份编号为空");
		}
		return object.toString();
	}

	
	

   /**
    * 翼支付商户报备入口
    */
	@RequestMapping(MerchantEnterReportedPath.BESTPAYMERCHANTREPORT)
	public ModelAndView  viewMerchantReported(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo detail,String merchantCode,String channlCode,String status){
		ModelAndView mv = new ModelAndView();
		if(null == detail || null == detail.getMerchantCode() ){
			detail.setMerchantCode(merchantCode);
		}
		if(null ==detail || null == detail.getChannelNo()){
			detail.setChannelNo(channlCode);
		}
		/***查询渠道***/
		List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
		/***查询报备信息***/
		List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
		/***查询省份信息***/
		List<Province> proviceList = fmIncomeService.getprovinceList();
		/***查询银行信息***/
		List<Bank> bankList = fmIncomeService.getBankList();
		/***查询支付功能Id***/
		List<TbFmTradeInfo> powerIdList = fmIncomeService.getPowerIdList();
		/***查询翼支付商户行业信息***/
		List<Industry> industryList = fmIncomeService.getIndustryList();
		merchantCode = detail.getMerchantCode();
		TdCustInfo custInfo = new TdCustInfo();
		if(null != merchantCode){
			custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
			if(null != custInfo){
				mv.addObject("custInfo", custInfo);
			}else{
				logger.info("--------------商户未通过审核，请查看商户状态----------------");
				status = "商户未通过审核，请查看商户状态";
				mv.addObject("status",status);
				return mv;
			}
		}
		if(null!=reportedList && reportedList.size()>0){
			mv.addObject("reportedList", reportedList);
		}
		if(null!=channlInfoList && channlInfoList.size()>0){
			mv.addObject("infoList", channlInfoList);
		}
		if(null!=proviceList && proviceList.size()>0){
			mv.addObject("provinceList", proviceList);
		}
		if(null!=bankList && bankList.size()>0){
			mv.addObject("bankList", bankList);
		}
		if(null!=powerIdList && powerIdList.size()>0){
			mv.addObject("powerIdList", powerIdList);
		}
		if(null!=industryList && industryList.size()>0){
			mv.addObject("industryList", industryList);
		}
		mv.addObject("status",status);
			return mv;
	}	
	
	
	
	 /**
    * 翼支付商户更新报备入口
    */
	@RequestMapping(MerchantEnterReportedPath.UPDATEBESTPAYREPORT)
	public ModelAndView  updateMerchantReported(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo detail,String merchantCode,String channlCode,String status){
		ModelAndView mv = new ModelAndView();
		if(null == detail || null == detail.getMerchantCode() ){
			detail.setMerchantCode(merchantCode);
		}
		if(null ==detail || null == detail.getChannelNo()){
			detail.setChannelNo(channlCode);
		}
		/***查询渠道***/
		List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
		/***查询报备信息***/
		List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
		/***查询省份信息***/
		List<Province> proviceList = fmIncomeService.getprovinceList();
		/***查询银行信息***/
		List<Bank> bankList = fmIncomeService.getBankList();
		/***查询支付功能Id***/
		List<TbFmTradeInfo> powerIdList = fmIncomeService.getPowerIdList();
		/***查询翼支付商户行业信息***/
		List<Industry> industryList = fmIncomeService.getIndustryList();
		merchantCode = detail.getMerchantCode();
		TdCustInfo custInfo = new TdCustInfo();
		if(null != merchantCode){
			custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
			if(null != custInfo){
				mv.addObject("custInfo", custInfo);
			}else{
				logger.info("--------------商户未通过审核，请查看商户状态----------------");
				status = "商户未通过审核，请查看商户状态";
				mv.addObject("status",status);
				return mv;
			}
		}
		if(null!=reportedList && reportedList.size()>0){
			mv.addObject("reportedList", reportedList);
		}
		if(null!=channlInfoList && channlInfoList.size()>0){
			mv.addObject("infoList", channlInfoList);
		}
		if(null!=proviceList && proviceList.size()>0){
			mv.addObject("provinceList", proviceList);
		}
		if(null!=bankList && bankList.size()>0){
			mv.addObject("bankList", bankList);
		}
		if(null!=powerIdList && powerIdList.size()>0){
			mv.addObject("powerIdList", powerIdList);
		}
		if(null!=industryList && industryList.size()>0){
			mv.addObject("industryList", industryList);
		}
		mv.addObject("status",status);
			return mv;
	}	
		
	
   /**
    * 随行付更新报备
    */
	@RequestMapping(MerchantEnterReportedPath.UPDATESUNXINGREPORT)
	public ModelAndView  updateSuiXingMerchantReported(HttpServletRequest request,HttpServletResponse response,String merchantCode,String channlCode,String patchNo,String reportStatus,String status){
		ModelAndView mv = new ModelAndView();
		/***查询渠道***/
		List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
		/***查询报备信息***/
//		List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
		/***查询随行付银行地区信息***/
		List<Province> proviceList = fmIncomeService.getSuiXingProvinceList();
		/***查询银行信息***/
		List<Bank> bankList = fmIncomeService.getBankList();
		/***查询随行付商户行业信息***/
		List<Industry> industryList = fmIncomeService.getSuiXingIndustryList();
		/***查询随行付商户注册地区信息***/
		String areaType ="2";
		List<MerchantCity> merchantProvinceList = fmIncomeService.getSuiXingMerchantCityList(areaType);
		TdCustInfo custInfo = new TdCustInfo();
		if(null != merchantCode){
			custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
			if(null != custInfo){
				mv.addObject("custInfo", custInfo);
			}else{
				logger.info("--------------商户未通过审核，请查看商户状态----------------");
				status = "商户未通过审核，请查看商户状态";
				mv.addObject("status",status);
				return mv;
			}
		}
		//获取图片路径
		MerchantVo merchantVo = new MerchantVo();
		merchantVo.setAuthId(custInfo.getAuthId());
		merchantVo.setCustId(custInfo.getCustId());
		PicturePath picturePath = merchantEnterService.getPicPath(merchantVo);
		mv.addObject("picturePathVo", picturePath);
		/*if(null!=reportedList && reportedList.size()>0){
			mv.addObject("reportedList", reportedList);
			String remark =  reportedList.get(0).getRemark();
			mv.addObject("remark", remark);
		}*/
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
		TdMerchantDetailInfo tdMerchantDetailInfo = new TdMerchantDetailInfo();
		tdMerchantDetailInfo.setMerchantCode(merchantCode);
		tdMerchantDetailInfo.setPatchNo(patchNo);
		tdMerchantDetailInfo.setChannelNo(channlCode);
		tdMerchantDetailInfo = fmIncomeMapperDao.selMerchantDetailInfo(tdMerchantDetailInfo);
		mv.addObject("patchNo",patchNo);
		mv.addObject("remark",tdMerchantDetailInfo.getRemark());
		mv.addObject("reportStatus",reportStatus);
		mv.addObject("status",status);
		return mv;
	}

	
	/**
    * 平安付更新报备入口
    */
	@RequestMapping(MerchantEnterReportedPath.UPDATEYQBREPORT)
	public ModelAndView  updateYQBMerchantReported(HttpServletRequest request,HttpServletResponse response,String channlCode,String merchantCode,String status){
		ModelAndView mv = new ModelAndView();
//		String channlCode = "YQB";
		/*if(null == detail || null == detail.getMerchantCode() ){
			detail.setMerchantCode(merchantCode);
		}
		if(null ==detail || null == detail.getChannelNo()){
			detail.setChannelNo(channlCode);
		}*/
		/***查询渠道***/
		List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
		/***查询报备信息***/
//		List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
		/***查询省份信息***/
		List<YQBArea> proviceList = fmIncomeService.getYQBProvinceList();
		/***查询行业信息***/
		List<YQBIndustry> industryList = fmIncomeService.getYQBIndustryList();
		/***查询银行信息***/
		Bank bank = new Bank();
		List<Bank> bankIdList = fmIncomeService.getYQBBankList(bank);
		
		TdCustInfo custInfo = new TdCustInfo();
		if(null != merchantCode){
			custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
			if(null != custInfo){
				mv.addObject("custInfo", custInfo);
			}else{
				logger.info("--------------商户未通过审核，请查看商户状态----------------");
				status = "商户未通过审核，请查看商户状态";
				mv.addObject("status",status);
				return mv;
			}
		}
		/*if(null!=reportedList && reportedList.size()>0){
			mv.addObject("reportedList", reportedList);
		}*/
		if(null!=channlInfoList && channlInfoList.size()>0){
			mv.addObject("infoList", channlInfoList);
		}
		if(null!=proviceList && proviceList.size()>0){
			mv.addObject("provinceList", proviceList);
		}
		if(null!=bankIdList && bankIdList.size()>0){
			mv.addObject("bankIdList", bankIdList);
		}
		if(null!=industryList && industryList.size()>0){
			mv.addObject("industryList", industryList);
		}
		
		mv.addObject("status",status);
		return mv;
	}	
		
	
	/**
    * 快付通更新报备入口
    */
	@RequestMapping(MerchantEnterReportedPath.UPDATEKFTREPORT)
	public ModelAndView  updateKFTMerchantReported(HttpServletRequest request,HttpServletResponse response,String channlCode,String merchantCode,String status){
		ModelAndView mv = new ModelAndView();
		/*if(null == detail || null == detail.getMerchantCode() ){
			detail.setMerchantCode(merchantCode);
		}
		if(null ==detail || null == detail.getChannelNo()){
			detail.setChannelNo(channlCode);
		}*/
		/***查询渠道***/
		List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
		/***查询报备信息***/
//				List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
		/***查询快付通地区信息***/
		List<KFTArea> provinceList = kFTIncomeService.getKftProvinceList();
		/***查询银行信息***/
		List<Bank> bankList = fmIncomeService.getBankList();
		/***查询快付通行业信息***/
		List<KFTMccBean> industryList = kFTIncomeService.getKftIndustryList();
//				merchantCode = detail.getMerchantCode();
		TdCustInfo custInfo = new TdCustInfo();
		if(null != merchantCode){
			custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
			if(null != custInfo){
				mv.addObject("custInfo", custInfo);
			}else{
				logger.info("--------------商户未通过审核，请查看商户状态----------------");
				status = "商户未通过审核，请查看商户状态";
				mv.addObject("status",status);
				return mv;
			}
		}
		/*if(null!=reportedList && reportedList.size()>0){
			mv.addObject("reportedList", reportedList);
			String remark =  reportedList.get(0).getRemark();
			mv.addObject("remark", remark);
		}*/
		if(null!=channlInfoList && channlInfoList.size()>0){
			mv.addObject("infoList", channlInfoList);
		}
		if(null!=provinceList && provinceList.size()>0){
			mv.addObject("provinceList", provinceList);
		}
		if(null!=bankList && bankList.size()>0){
			mv.addObject("bankList", bankList);
		}
		if(null!=industryList && industryList.size()>0){
			mv.addObject("industryList", industryList);
		}
		mv.addObject("status",status);
		return mv;
	}
			
	
}
