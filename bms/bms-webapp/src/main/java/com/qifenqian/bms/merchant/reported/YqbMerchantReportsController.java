package com.qifenqian.bms.merchant.reported;
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
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.merchant.reported.bean.Bank;
import com.qifenqian.bms.merchant.reported.bean.ChannlInfo;
import com.qifenqian.bms.merchant.reported.bean.CrInComeBean;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.bean.YQBArea;
import com.qifenqian.bms.merchant.reported.bean.YQBCoBean;
import com.qifenqian.bms.merchant.reported.bean.YQBIndustry;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.service.CrIncomeService;
import com.qifenqian.bms.merchant.reported.service.FmIncomeService;

@Controller
@RequestMapping(MerchantReportedPath.BASE)
public class YqbMerchantReportsController {

   private Logger logger = LoggerFactory.getLogger(YqbMerchantReportsController.class);
	
   @Autowired
   private FmIncomeService fmIncomeService;
   
   @Autowired
   private CrIncomeService crIncomeService;
   
   
   @Autowired
   private FmIncomeMapperDao fmIncomeMapperDao;
   
	
   /**
    * 平安付报备入口
    */
	@RequestMapping(MerchantReportedPath.YQBMERCHANTREPORTS)
	public ModelAndView  viewMerchantReported(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo detail,String merchantCode,String status){
		ModelAndView mv = new ModelAndView();
		String channlCode = "YQB";
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
		List<YQBArea> proviceList = fmIncomeService.getYQBProvinceList();
		/***查询行业信息***/
		List<YQBIndustry> industryList = fmIncomeService.getYQBIndustryList();
		/***查询银行信息***/
		Bank bank = new Bank();
		List<Bank> bankIdList = fmIncomeService.getYQBBankList(bank);
		
		merchantCode = detail.getMerchantCode();
		TdCustInfo custInfo = new TdCustInfo();
		if(null != merchantCode){
			custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
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
		if(null!=bankIdList && bankIdList.size()>0){
			mv.addObject("bankIdList", bankIdList);
		}
		if(null!=industryList && industryList.size()>0){
			mv.addObject("industryList", industryList);
		}
		if(null!=custInfo){
			mv.addObject("custInfo", custInfo);
		}
		mv.addObject("status",status);
		return mv;
	}	
	
//	/**查询平安付银行*/
//	@RequestMapping(value = MerchantReportedPath.YQBSELBANK, method = RequestMethod.POST)
//	@ResponseBody
//	public Bank selBank(@RequestBody String searchBank){
//		/***查询银行信息***/
//		//System.out.println("开始查询"+searchBank);
//		Bank bank = new Bank();
//		List<Bank> bankIdList = fmIncomeService.getYQBBankList(bank);
//		//System.out.println(bankIdList.size());
//		int len = bankIdList.size();
//		if (len > 0){
//			System.out.println(len+"进来了");
//			for(Bank bank1 : bankIdList) {
//				if(searchBank.equals(bank1.getBankName())) {
//					System.out.println("查询银行成功:"+bank1.getBankName());
//					return bank1;
//				}
//			}	
//		}
//		
//		return null;
//		
//	}
	
	/**查询平安付市*/
	@RequestMapping(MerchantReportedPath.YQBSELCITY)
	@ResponseBody
	public String selCity(HttpServletRequest request,HttpServletResponse response,YQBArea yabArea){
		JSONObject object = new JSONObject();
		List<YQBArea> yqbAreaList = fmIncomeService.getYQBCityList(yabArea);
		if(null!=yqbAreaList &&yqbAreaList.size()>0){
			object.put("result", "SUCCESS");
			object.put("yqbAreaList", yqbAreaList);
		}else{
			object.put("result", "FAIL");
			object.put("message", "省份编号为空");
		}
		return object.toString();
	}
	
	
	/**查询平安付地区*/
	@RequestMapping(MerchantReportedPath.YQBSELAREA)
	@ResponseBody
	public String selArea(HttpServletRequest request,HttpServletResponse response,YQBArea yabArea){
		JSONObject object = new JSONObject();
		List<YQBArea> yqbAreaList = fmIncomeService.getYQBAreaList(yabArea);
		if(null!=yqbAreaList &&yqbAreaList.size()>0){
			object.put("result", "SUCCESS");
			object.put("yqbAreaList", yqbAreaList);
		}else{
			object.put("result", "FAIL");
			object.put("message", "省份编号为空");
		}
		return object.toString();
	}

	/**查询平安付行业信息*/
	@RequestMapping(MerchantReportedPath.YQBSELINDUSTRY)
	@ResponseBody
	public String selIndustry(HttpServletRequest request,HttpServletResponse response,YQBIndustry yqbIndustry){
		JSONObject object = new JSONObject();
		List<YQBIndustry> yqbIndustryList = fmIncomeService.getYQBIndustryCodeList(yqbIndustry);
		if(null!=yqbIndustryList &&yqbIndustryList.size()>0){
			object.put("result", "SUCCESS");
			object.put("yqbIndustryList", yqbIndustryList);
		}else{
			object.put("result", "FAIL");
			object.put("message", "省份编号为空");
		}
		return object.toString();
	}
	
	
	
	/**
	 * 提交平安付资料报备
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(MerchantReportedPath.YQBINFOMERCHANTREPORT)
	@ResponseBody
	public String list(HttpServletRequest request,HttpServletResponse response,YQBCoBean cr){
		JSONObject object = new JSONObject();
		JSONObject bestResult = new JSONObject();
		request.setAttribute("merchantCode", cr.getMerchantCode().trim());
		if("YQB".equals(cr.getChannelNo().trim())){
			//查询该商户是否已报备
			CrInComeBean comeBean = new CrInComeBean();
			comeBean.setMerchantCode(cr.getMerchantCode());
			comeBean.setChannelNo(cr.getChannelNo());
			TdMerchantDetailInfo td = fmIncomeService.getTdMerchantReport(comeBean);

			if(td!=null){
				//该商户已报备
				if("Y".equals(td.getReportStatus())||"O".equals(td.getReportStatus())){
				object.put("result", "FAILURE");
					object.put("message", "商户已经报备，请勿重新提交");
					return object.toString();
				}else{//商户报备
					TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
					tdInfo.setMerchantCode(td.getMerchantCode());
					tdInfo.setChannelNo(td.getChannelNo());
					tdInfo.setReportStatus("E");
					tdInfo.setDetailStatus("99");
					fmIncomeService.UpdateMerReportAndMerDetailInfo(tdInfo,"99");
					Bank bank = new Bank();
					bank.setBankId(cr.getBank());
					List<Bank> bankIdList = fmIncomeService.getYQBBankList(bank);
					if(bankIdList.size() != 0){
						cr.setBankName(bankIdList.get(0).getBankName());
					}
					//调用平安付报备接口
					logger.info("-----------------调用平安付报备接口开始");
					bestResult = fmIncomeService.yqbReported(cr);
					logger.info("-----------------调用平安付报备接口开始"+bestResult.get("result") +  "----------------------");
					if("SUCCESS".equals(bestResult.get("result"))){
						object.put("result", "SUCCESS");
						object.put("message", "报备成功");
					}else{
						object.put("result", "FAILURE");
						if(bestResult.get("message") == "" && bestResult.get("message") == null){
							object.put("message", "平安付进件明确失败");
						}else {
							object.put("message", bestResult.get("message"));
						}
					}
				}
				
			}else{//查询为空，商户未报备
				//添加商户报备详情表（td_merchant_detail_info）和商户报备表（td_merchant_report）
				TdMerchantDetailInfo info = new TdMerchantDetailInfo();
				info.setId(GenSN.getSN());
				info.setPatchNo(GenSN.getSN());
				info.setMerchantCode(cr.getMerchantCode().trim());
				info.setChannelNo(cr.getChannelNo());
				info.setReportStatus("E");
				info.setDetailStatus("99");
				info.setProvCode(cr.getProvince());
				info.setCityCode(cr.getCity());
				info.setContryCode(cr.getArea());
				info.setBankCode(cr.getSumpayBankNmType());
//				info.setBranchBankCode(cr.getInterBank());
				info.setBranchBankName(cr.getInterBankName());
				info.setMobileNo(cr.getMobile());
//				info.setBestMerchantType("02");
				fmIncomeService.insertTdMerchantReport(info);
				info.setReportStatus("99");
				fmIncomeService.inserTdMerchantDetailInfo(info);
				
				Bank bank = new Bank();
				bank.setBankId(cr.getBank());
				List<Bank> bankIdList = fmIncomeService.getYQBBankList(bank);
				if(bankIdList.size() != 0){
					cr.setBankName(bankIdList.get(0).getBankName());
				}
				
				//调用平安付报备接口
				bestResult =fmIncomeService.yqbReported(cr);
				
				if("SUCCESS".equals(bestResult.get("result"))){
						object.put("result", "SUCCESS");
						object.put("message", "报备成功");
				}else{
					object.put("result", "FAILURE");
					if(bestResult.get("message") == "" && bestResult.get("message") == null){
						object.put("message", "平安付进件明确失败");
					}else {
						object.put("message", bestResult.get("message"));
					}
					
				}
				
			}
			
		
		}
		return object.toString();
	}
}
