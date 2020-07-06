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
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.merchant.reported.bean.Bank;
import com.qifenqian.bms.merchant.reported.bean.ChannlInfo;
import com.qifenqian.bms.merchant.reported.bean.CrInComeBean;
import com.qifenqian.bms.merchant.reported.bean.Industry;
import com.qifenqian.bms.merchant.reported.bean.Province;
import com.qifenqian.bms.merchant.reported.bean.TbFmTradeInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.service.CrIncomeService;
import com.qifenqian.bms.merchant.reported.service.FmIncomeService;
import com.seven.micropay.base.domain.ChannelResult;
import com.seven.micropay.channel.domain.merchant.BestPayBankInfo;
import com.seven.micropay.channel.domain.merchant.BestUpdateMerChantInfo;
import com.seven.micropay.channel.enums.BestBankCode;
import com.seven.micropay.channel.enums.BestMerchantType;
import com.seven.micropay.channel.enums.ChannelMerRegist;
import com.seven.micropay.channel.enums.MerUpdateType;
import com.seven.micropay.channel.service.IMerChantIntoService;

@Controller
@RequestMapping(MerchantReportedPath.BASE)
public class bestPayMerchantReportsController {

   private Logger logger = LoggerFactory.getLogger(bestPayMerchantReportsController.class);
	
   @Autowired
   private FmIncomeService fmIncomeService;
   
   @Autowired
   private CrIncomeService crIncomeService;
   
   @Autowired
   private MerchantService merchantService;
   
   @Autowired
   private FmIncomeMapperDao fmIncomeMapperDao;
   
   @Autowired
   private IMerChantIntoService iMerChantIntoServic;

   

   /**
    * 选择渠道报备入口
    */
	@RequestMapping(MerchantReportedPath.SELMERCHANTREPORTS)
	public ModelAndView  selMerchantReported(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo detail){
		ModelAndView mv = new ModelAndView();
		
		/***查询渠道***/
		List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
		
		if(null!=channlInfoList && channlInfoList.size()>0){
			mv.addObject("channlInfoList", channlInfoList);
		}
		return mv;
	}	
	
	
   /**
    * 商户报备入口
    */
	@RequestMapping(MerchantReportedPath.BESTPAYMERCHANTREPORTS)
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
		if(null!=custInfo){
			mv.addObject("custInfo", custInfo);
		}
		mv.addObject("status",status);
		return mv;
	}	
	
	
	/**
	 * 提交报备
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(MerchantReportedPath.BESTPAYSUBMITREPORT)
	@ResponseBody
	public String list(HttpServletRequest request,HttpServletResponse response,CrInComeBean cr){
		JSONObject object = new JSONObject();
		JSONObject bestResult = new JSONObject();
		request.setAttribute("merchantCode", cr.getMerchantCode().trim());
		if("BEST_PAY".equals(cr.getChannelNo().trim())){//翼支付
			//查询该商户是否已报备
			TdMerchantDetailInfo td = fmIncomeService.getTdMerchantReport(cr);

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
					tdInfo.setBestMerchantType("01");
					fmIncomeService.UpdateMerReportAndMerDetailInfo(tdInfo,"99");
					
					//获取图片路径
					TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(cr.getMerchantCode());
					
					//获取门头照路径
					String doorImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "08");
					String[] pathDoorImage = null;
					pathDoorImage = doorImagePath.split(";");
					if(pathDoorImage.length >1){
						cr.setStoreSignBoardPic(pathDoorImage[0]);
						cr.setStoreInteriorPic(pathDoorImage[1]);
					}else{
						cr.setStoreSignBoardPic(doorImagePath);
						//内景
						cr.setStoreInteriorPic(doorImagePath);
					}
					
					
					//获取身份证正反面
					String  identityImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "04");
					String[] paths = null;
					paths = identityImagePath.split(";");
					//正面
					String identityImagePath0 = paths[0];
					cr.setIdentityCardFrontPic(identityImagePath0);
					//反面
					String identityImagePath1 = paths[1];
					cr.setIdentityCardReversePic(identityImagePath1);
					
					//获取营业执照
					String imagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "02");
					cr.setLicensePic(imagePath);
					
					
					logger.info(imagePath);
					//调用翼支付报备接口
					bestResult = fmIncomeService.bestReported(cr);
					
					if("SUCCESS".equals(bestResult.get("result"))){
						object.put("result", "SUCCESS");
						object.put("message", "报备成功");
					}else{
						object.put("result", "FAILURE");
						if(bestResult.get("message") == "" && bestResult.get("message") == null){
							object.put("message", "翼支付进件明确失败");
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
				info.setProvCode(cr.getProvince());
				info.setCityCode(cr.getCity());
				info.setContryCode(cr.getCountry());
				info.setBankCode(cr.getBankCode());
				info.setBranchBankCode(cr.getInterBank());
				info.setBranchBankName(cr.getInterBankName());
				info.setMobileNo(cr.getMobileNo());
				info.setBestMerchantType("01");
				fmIncomeService.insertTdMerchantReport(info);
				info.setReportStatus("99");
				fmIncomeService.inserTdMerchantDetailInfo(info);
				//判断支行是否正确
				BestPayBankInfo bankInfo = new BestPayBankInfo();
				bankInfo.setRequestSeqId(""+System.currentTimeMillis());
				bankInfo.setBankName(cr.getInterBankName());
				bankInfo.setBankCode(BestBankCode.getBestBankCodeByBankCode(cr.getBankCode()));
				Map<String, Object> req = new HashMap<String, Object>();
				req.put("merList", bankInfo);
				req.put("channelType", ChannelMerRegist.BEST_PAY);
				ChannelResult channelResult = iMerChantIntoServic.queryBankInfo(req);
				if("SUCCESS".equals(channelResult.getStatus()) && "00".equals(channelResult.getChannelCode())){
					//获取图片路径
					TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(cr.getMerchantCode());
					
					//获取门头照路径
					String doorImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "08");
					String[] pathDoorImage = null;
					pathDoorImage = doorImagePath.split(";");
					if(pathDoorImage.length >1){
						cr.setStoreSignBoardPic(pathDoorImage[0]);
						cr.setStoreInteriorPic(pathDoorImage[1]);
					}else{
						cr.setStoreSignBoardPic(doorImagePath);
						//内景
						cr.setStoreInteriorPic(doorImagePath);
					}
					
					
					//获取身份证正反面
					String  identityImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "04");
					String[] paths = null;
					paths = identityImagePath.split(";");
					//正面
					String identityImagePath0 = paths[0];
					cr.setIdentityCardFrontPic(identityImagePath0);
					//反面
					String identityImagePath1 = paths[1];
					cr.setIdentityCardReversePic(identityImagePath1);
					
					//获取营业执照
					String imagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "02");
					cr.setLicensePic(imagePath);
					
					//调用翼支付报备接口
					bestResult =fmIncomeService.bestReported(cr);
					
					if("SUCCESS".equals(bestResult.get("result"))){
						object.put("result", "SUCCESS");
						object.put("message", "报备成功");
					}else{
						object.put("result", "FAILURE");
						if(bestResult.get("message") == "" && bestResult.get("message") == null){
							object.put("message", "翼支付进件明确失败");
						}else {
							object.put("message", bestResult.get("message"));
						}
						
					}
				}else{
					object.put("result", "FAILURE");
					if( null != channelResult.getReMsg()){
						object.put("message", channelResult.getReMsg());
					}else{
						object.put("message", "支行信息错误");
					}
				}
			}
			
		
		}
		return object.toString();
	}
	
	
	/**
	 * 更新报备
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(MerchantReportedPath.UPDATESUBMITREPORT)
	@ResponseBody
	public String updateList(HttpServletRequest request,HttpServletResponse response,CrInComeBean cr,MerUpdateType updateType,String qualificationType){
		JSONObject object = new JSONObject();
		
		
		String merchantCode = cr.getMerchantCode().trim();
		//查询商户报备信息
		TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
		BestUpdateMerChantInfo info = new BestUpdateMerChantInfo();
		info.setRequestSeqId(System.currentTimeMillis() + "UP");
		info.setMerchantType(BestMerchantType.getBestMerchantTypeByName(cr.getBestMerchantType()));
		info.setMerchantNo(cr.getMerchantCode());
		info.setUpdateType(updateType);
		if(MerUpdateType.BASE_INFO.equals(updateType)){
			//更新基本信息
			info.setUpdateType(MerUpdateType.BASE_INFO);//更新基本信息
			if(BestBankCode.getBestBankCodeByBankCode(cr.getBankCode())!=null){
				info.setBankCode(BestBankCode.getBestBankCodeByBankCode(cr.getBankCode()));
			}else{
				object.put("message", "开户行信息错误");
				object.put("result", "FAILURE");
				return object.toString();
			}
			info.setBusiLicenseNo(custInfo.getBusinessLicense());
			info.setBusinessAddress(custInfo.getCustAdd());
			info.setBusinessScope("线下");
			if("forever".equals(custInfo.getBusinessTermEnd())){
				info.setBusinessTerm("2199-12-31");
			}else if(custInfo.getBusinessTermEnd() !="" && custInfo.getBusinessTermEnd() != null){
				info.setBusinessTerm(custInfo.getBusinessTermEnd());
			}else{
				info.setBusinessTerm(custInfo.getBusinessTerm());
			}
			info.setContactPhone(custInfo.getMobile());
			info.setMccCode(cr.getIndustryCode());
			 
			info.setMerchantName(custInfo.getCustName());
			info.setMerchantNameShort(custInfo.getCustName());
			info.setMerchantTxnRate(cr.getRate());
			info.setMerchantTxnSettlePeriod("1");
			info.setSettleBankcardNo(cr.getBankCardNo());
			info.setSettleBankName(cr.getInterBankName());
			info.setSettleBankNo("");
		}
		if(MerUpdateType.FILE_INFO.equals(updateType)){
			//更新纸质类型

			info.setUpdateType(MerUpdateType.FILE_INFO);//更新纸质类型
//			QualificationType资质类型：
//			IDCard-身份证正面照，
//			IDCardBack-身份证反面照，
//			businessLicense：营业执照，
//			integrateLicense：三证合一照，
//			storeInterior：店铺内景，
//			signBoard：店铺招牌照
			info.setQualificationType(qualificationType);
			if("IDCard".equals(qualificationType)){
				//更新身份证正面
				String  identityImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "04");
				String[] paths = null;
				paths = identityImagePath.split(";");
				String identityImagePath0 = paths[0];
				info.setFilePath(identityImagePath0);
			}else if("IDCardBack".equals(qualificationType)){
				//更新身份证反面
				String  identityImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "04");
				String[] paths = null;
				paths = identityImagePath.split(";");
				String identityImagePath1 = paths[1];
				info.setFilePath(identityImagePath1);
			}else if("businessLicense".equals(qualificationType)){
				//获取营业执照
				String imagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "02");
				info.setFilePath(imagePath);
			}else if("signBoard".equals(qualificationType)){
				//获取门头照路径
				String doorImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "08");
				String[] pathDoorImage = null;
				pathDoorImage = doorImagePath.split(";");
				if(pathDoorImage.length >1){
					info.setFilePath(pathDoorImage[0]);
				}else{
					info.setFilePath(doorImagePath);
				}
			}else if("storeInterior".equals(qualificationType)){
				String doorImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "18");
				if(null == doorImagePath || "".equals(doorImagePath)){
					doorImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "08");
					String[] pathDoorImage = null;
					pathDoorImage = doorImagePath.split(";");
					if(pathDoorImage.length >1){
						info.setFilePath(pathDoorImage[1]);
					}else{
						info.setFilePath(doorImagePath);
					}
					
				}else{
					info.setFilePath(doorImagePath);
				}
			}
		}
		if(MerUpdateType.SIGN_INFO.equals(updateType)){
			//签约数据更新
			info.setUpdateType(updateType);
			if(null == cr.getMchName() || "".equals(cr.getMchName())){
				info.setMerchantNameShort(custInfo.getCustName());
			}else{
				info.setMerchantNameShort(cr.getMchName());
			}
		}

		Map<String, Object> req = new HashMap<String, Object>();
		req.put("merList", info);
		req.put("channelType",ChannelMerRegist.BEST_PAY);
		ChannelResult result = iMerChantIntoServic.updataMerchatAdd(req);
		if(null ==result && "".equals(result)){
			object.put("result", "FAIL");
			object.put("message", "翼支付异常，更新资料失败");
			return object.toString();
		}
		if("SUCCESS".equals(result.getReCode())){
			object.put("result", "SUCCESS");
			object.put("message", "商户更新资料成功");
		}else {
			object.put("result", "FAIL");
			if(null == result.getData().get("errorMsg")){
				object.put("message", "商户更新资料失败");
			}else{
				object.put("message", result.getData().get("errorMsg"));
			}
		}
		
		return object.toString();
			
	}
}
