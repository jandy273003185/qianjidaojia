package com.qifenqian.bms.merchant.merchantReported;
import java.math.BigDecimal;
import java.util.ArrayList;
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
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.toPayProduct.bean.ToPayProduct;
import com.qifenqian.bms.basemanager.toPayProduct.mapper.ToPayProductMapper;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.merchant.merchantReported.bean.KFTCoBean;
import com.qifenqian.bms.merchant.merchantReported.bean.KFTMccBean;
import com.qifenqian.bms.merchant.merchantReported.service.KFTIncomeService;
import com.qifenqian.bms.merchant.reported.bean.Bank;
import com.qifenqian.bms.merchant.reported.bean.CrInComeBean;
import com.qifenqian.bms.merchant.reported.bean.MerchantCity;
import com.qifenqian.bms.merchant.reported.bean.Province;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.service.FmIncomeService;
import com.seven.micropay.channel.domain.merchant.KftProductFeeInfo;


@Controller
@RequestMapping(MerchantEnterReportedPath.BASE)
public class KftMerchantReportedController {

   private Logger logger = LoggerFactory.getLogger(KftMerchantReportedController.class);
	
   @Autowired
   private FmIncomeService fmIncomeService;
   
   @Autowired
   private KFTIncomeService kftIncomeService;
   
   @Autowired
   private ToPayProductMapper toPayProductMapper;
   
   @Autowired
   private FmIncomeMapperDao fmIncomeMapperDao;
   
   @Autowired
   private MerchantService merchantService;
   
   @Autowired
   private MerchantEnterService merchantEnterService;
	/**
	 * 快付通提交报备
	 */
	
	@RequestMapping(MerchantEnterReportedPath.KFTSUBMITREPORT)
	@ResponseBody
	public String list(HttpServletRequest request,HttpServletResponse response,KFTCoBean cr){
		JSONObject object = new JSONObject();
		JSONObject kftResult = new JSONObject();
		
		try {
			//查询商户报备表
			CrInComeBean cc =new CrInComeBean();
			cc.setMerchantCode(cr.getMerchantCode());
			cc.setChannelNo(cr.getChannelNo());
			TdMerchantDetailInfo td = fmIncomeService.getTdMerchantReport(cc);
			
			//该商户已报备
			if(td!=null){
				//该商户已报备成功
				if("Y".equals(td.getReportStatus())||"O".equals(td.getReportStatus())){
				object.put("result", "FAILURE");
					object.put("message", "商户已经报备，请勿重新提交");
					return object.toString();
				}else{//商户报备失败
					TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
					cr.setPatchNo(td.getPatchNo());
					tdInfo.setMerchantCode(td.getMerchantCode());
					tdInfo.setChannelNo(td.getChannelNo());
					tdInfo.setReportStatus("E");
					fmIncomeService.UpdateMerReportAndMerDetailInfo(tdInfo,"99");
				}
			}else{
				//添加商户报备详情表（td_merchant_detail_info）和商户报备表（td_merchant_report）
				TdMerchantDetailInfo info = new TdMerchantDetailInfo();
				info.setId(GenSN.getSN());
				info.setPatchNo(GenSN.getSN());
				info.setMerchantCode(cr.getMerchantCode().trim());
				info.setChannelNo(cr.getChannelNo());
				info.setReportStatus("E");
				info.setProvCode(cr.getProvince());
				info.setCityCode(cr.getCity());
				info.setContryCode(cr.getCountry());
				info.setBankCode(cr.getBankCode());
				info.setBranchBankName(cr.getInterBankName());
				info.setMobileNo(cr.getMobile());
				fmIncomeService.insertTdMerchantReport(info);
				info.setReportStatus("99");
				fmIncomeService.inserTdMerchantDetailInfo(info);
				cr.setPatchNo(info.getPatchNo());
			}
			//转换为目标对象list
			List<KftProductFeeInfo> kftProductFeeInfo = new ArrayList<KftProductFeeInfo>();
			//产品表
			ToPayProduct toPayProduct = new ToPayProduct();
			
			for(int i=0;i<cr.getFeeList().size();i++){
				KftProductFeeInfo prod = new KftProductFeeInfo();
				
				prod.setFeeOfAttach((String)cr.getFeeList().get(i).get("feeOfAttach"));
				prod.setFeeOfRate((String)cr.getFeeList().get(i).get("feeOfRate"));
				prod.setFeeType((String)cr.getFeeList().get(i).get("feeType"));
				prod.setProductId((String)cr.getFeeList().get(i).get("productId"));
				
				kftProductFeeInfo.add(prod);
			
				toPayProduct.setMerchantCode(cr.getMerchantCode());
				toPayProduct.setProductId(prod.getProductId());
				toPayProduct.setId(prod.getFeeType());
				
				BigDecimal productRate=new BigDecimal(prod.getFeeOfRate());
				toPayProduct.setProductRate(productRate);
				
				BigDecimal rechargeRate=new BigDecimal(prod.getFeeOfAttach());
				toPayProduct.setRechargeRate(rechargeRate);
				//报备成功后修改状态
				toPayProduct.setProductStatus("09");
				toPayProductMapper.saveProductRate(toPayProduct);
				
			}
			//调用快付通报备接口
			kftResult =kftIncomeService.kftReported(cr,kftProductFeeInfo);
			if("SUCCESS".equals(kftResult.get("result"))){
				object.put("result", "SUCCESS");
				object.put("message", "报备成功");
			}else{
				object.put("result", "FAILURE");
				if(kftResult.get("message") == "" && kftResult.get("message") == null){
					object.put("message", "快付通进件明确失败");
				}else {
					object.put("message", kftResult.get("message"));
				}
			
			}
		} catch (Exception e) {
			logger.error("快付通进件失败",e);
			object.put("result", "FAILURE");
			object.put("message", e);
			return object.toString();
		}
		
		return object.toString();
	}
	
	/**
	 * 报备查询 SELECTMERCHANTREPORTSTATUS
	 */
	
	@RequestMapping(MerchantEnterReportedPath.MERQUERYREPORT)
	@ResponseBody
	public String merQuery(HttpServletRequest request,HttpServletResponse response,KFTCoBean cr){
		JSONObject object = new JSONObject();
		JSONObject kftResult = new JSONObject();
		//调用快付通报备接口
		kftResult =kftIncomeService.merQuery(cr);
		if("SUCCESS".equals(kftResult.get("result"))){
			object.put("result", "SUCCESS");
			object.put("message", "报备成功");
		}else if("PROCESSING".equals((kftResult).get("result"))){
			object.put("result", "PROCESSING");
			object.put("message", "快付通进件审核中");
		
		}else{
			object.put("result", "FAILURE");
			object.put("message", kftResult.get("message"));
		}
		return object.toString();
		
		
	}
	
	@RequestMapping(MerchantEnterReportedPath.MERCHANTREPORTINFO)
	@ResponseBody
	public ModelAndView reportInfo(HttpServletRequest request,HttpServletResponse response,String merchantCode,String channlCode,String patchNo){
	
		ModelAndView mv = new ModelAndView();
		TdMerchantDetailInfo detailInfo = new TdMerchantDetailInfo();
		// 查询商户报备信息
	    TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
	    
	    detailInfo.setChannelCode(channlCode);
		detailInfo.setMerchantCode(merchantCode);
		detailInfo.setPatchNo(patchNo);
//		MerchantVo merchant = merchantService.getMerchantInfo(detailInfo);
		MerchantVo merchant = merchantService.findMerchantInfo(custInfo.getCustId());
		/***报备明细***/
		TdMerchantDetailInfo tdMerchantDetailInfo= merchantService.findMerchantDetailInfo(detailInfo);
		/***查询随行付银行地区信息***/
		List<Province> proviceList = fmIncomeService.getSuiXingProvinceList();
		/***查询银行信息***/
		List<Bank> bankList = fmIncomeService.getBankList();
		/***查询快付通行业信息***/
		List<KFTMccBean> industryList = kftIncomeService.getKftIndustryList();
		/***查询随行付商户注册地区信息***/
		String areaType ="2";
		List<MerchantCity> merchantProvinceList = fmIncomeService.getSuiXingMerchantCityList(areaType);
		
		mv.addObject("tdMerchantDetailInfo",tdMerchantDetailInfo);
		mv.addObject("merchantVo", merchant);
		mv.addObject("channlCode", channlCode);
		mv.addObject("custInfo", custInfo);
		
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
		//获取图片路径
		PicturePath picturePath = merchantEnterService.getPicPath(merchant);
		mv.addObject("picturePathVo", picturePath); 
		return mv;
	}
}
