package com.qifenqian.bms.merchant.reported;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.merchant.reported.bean.Bank;
import com.qifenqian.bms.merchant.reported.bean.ChannlInfo;
import com.qifenqian.bms.merchant.reported.bean.CrInComeBean;
import com.qifenqian.bms.merchant.reported.bean.Industry;
import com.qifenqian.bms.merchant.reported.bean.PicPath;
import com.qifenqian.bms.merchant.reported.bean.SumPayArea;
import com.qifenqian.bms.merchant.reported.bean.SumPayCoBean;
import com.qifenqian.bms.merchant.reported.bean.SumpayMcc;
import com.qifenqian.bms.merchant.reported.bean.TbFmTradeInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.mapper.FmIncomeMapper;
import com.qifenqian.bms.merchant.reported.service.CrIncomeService;
import com.qifenqian.bms.merchant.reported.service.FmIncomeService;
import com.qifenqian.bms.platform.utils.FormTokenUtil;
import com.seven.micropay.base.domain.ChannelResult;
import com.seven.micropay.base.enums.ReStatus;
import com.seven.micropay.channel.domain.merchant.SumpayOpenProduInfo;
import com.seven.micropay.channel.enums.ChannelMerRegist;
import com.seven.micropay.channel.service.IMerChantIntoService;

@Controller
@RequestMapping(MerchantReportedPath.BASE)
public class SumPayMerchantReportsController {

   private Logger logger = LoggerFactory.getLogger(SumPayMerchantReportsController.class);
	
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

   @Autowired
   private FmIncomeMapper fmIncomeMapper;
	
	
   /**
    * 商盟支付报备信息入口
    */
	@RequestMapping(MerchantReportedPath.SUMPAYMERCHANTREPORTS)
	public ModelAndView  viewMerchantReported(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo detail,String merchantCode,String merchantType,String status){
		ModelAndView mv = new ModelAndView();
		String channlCode = "SUM_PAY";
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
		String superiorAreaCode = "1";
		List<SumPayArea> proviceList = fmIncomeService.getSumPayProvinceList(superiorAreaCode);
		/***查询银行信息***/
		List<Bank> bankCdList = fmIncomeService.getSumPayBankList();
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
		if(null!=bankCdList && bankCdList.size()>0){
			mv.addObject("bankCdList", bankCdList);
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
	 * 提交商盟资料报备
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(MerchantReportedPath.SUMPAYINFOMERCHANTREPORT)
	@ResponseBody
	public String list(HttpServletRequest request,HttpServletResponse response,SumPayCoBean cr){
		JSONObject object = new JSONObject();
		JSONObject bestResult = new JSONObject();
		request.setAttribute("merchantCode", cr.getMerchantCode().trim());
		if("SUM_PAY".equals(cr.getChannelNo().trim())){//翼支付
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
//					tdInfo.setBestMerchantType("02");
					fmIncomeService.UpdateMerReportAndMerDetailInfo(tdInfo,"99");
					
					//调用商盟报备接口
					bestResult = fmIncomeService.sumpayReported(cr);
					
					if("SUCCESS".equals(bestResult.get("result"))){
						/*cr.setOutMerchantCode((String)bestResult.get("outMerchantCode"));
						sumpayResult = fmIncomeService.sumpayFileUpload(cr);
						if("SUCCESS".equals(sumpayResult.get("result"))){
							object.put("result", "SUCCESS");
							object.put("message", "报备成功");
						}else{
							sumpayResult.put("result", "FAILURE");
							if(sumpayResult.get("message") == "" && sumpayResult.get("message") == null){
								object.put("message", "商盟进件明确失败");
							}else {
								object.put("message", sumpayResult.get("message"));
							}
						}*/
						object.put("result", "SUCCESS");
						object.put("message", "报备成功");
					}else{
						object.put("result", "FAILURE");
						if(bestResult.get("message") == "" && bestResult.get("message") == null){
							object.put("message", "商盟进件明确失败");
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
				info.setBankCode(cr.getSumpayBankNmType());
//				info.setBranchBankCode(cr.getInterBank());
				info.setBranchBankName(cr.getInterBankName());
				info.setMobileNo(cr.getMobile());
//				info.setBestMerchantType("02");
				fmIncomeService.insertTdMerchantReport(info);
				info.setReportStatus("99");
				fmIncomeService.inserTdMerchantDetailInfo(info);
				
				//调用商盟报备接口
				bestResult =fmIncomeService.sumpayReported(cr);
				
				if("SUCCESS".equals(bestResult.get("result"))){
					/*sumpayResult = fmIncomeService.sumpayFileUpload(cr);
					if("SUCCESS".equals(sumpayResult.get("result"))){
						object.put("result", "SUCCESS");
						object.put("message", "报备成功");
					}else{
						sumpayResult.put("result", "FAILURE");
						if(sumpayResult.get("message") == "" && sumpayResult.get("message") == null){
							object.put("message", "商盟进件明确失败");
						}else {
							object.put("message", sumpayResult.get("message"));
						}
					}*/
					object.put("result", "SUCCESS");
					object.put("message", "报备成功");
				}else{
					object.put("result", "FAILURE");
					if(bestResult.get("message") == "" && bestResult.get("message") == null){
						object.put("message", "商盟进件明确失败");
					}else {
						object.put("message", bestResult.get("message"));
					}
					
				}
				
			}
			
		
		}
		return object.toString();
	}
	
	/**查询商盟市*/
	@RequestMapping(MerchantReportedPath.SUMPAYSELCITY)
	@ResponseBody
	public String selCity(HttpServletRequest request,HttpServletResponse response){
		JSONObject object = new JSONObject();
		String area = request.getParameter("area");
		if(!StringUtils.isBlank(area)){
			List<SumPayArea> sumPayAreaList = fmIncomeService.getSumPayProvinceList(area);
			if(null!=sumPayAreaList &&sumPayAreaList.size()>0){
				object.put("result", "SUCCESS");
				object.put("sumPayAreaList", sumPayAreaList);
			}else{
				object.put("result", "FAIL");
				object.put("message", "省份编号为空");
			}
		}
		return object.toString();
	}
	
	/**
	 * 开通产品
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(MerchantReportedPath.OPENPRODUCT)
	@ResponseBody
	public String openProduct(HttpServletRequest request,HttpServletResponse response,SumpayMcc sumpayMcc,String merchantCode,String outMerchantCode){
		JSONObject object = new JSONObject();
		if(!FormTokenUtil.validateToken(request)){
			object.put("result", "FAILURE");
			object.put("message", "已经确认,请不要重复提交");
			return object.toJSONString();
		}
		TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
		tdInfo.setMerchantCode(merchantCode);
		tdInfo.setChannelNo("SUM_PAY");
		TdMerchantDetailInfo tdInfo_ = fmIncomeMapper.selMerchantDetailInfo(tdInfo);
		List<SumpayMcc> sumPayMccList = fmIncomeService.getSumPayMccThreeList(sumpayMcc);
		//支付宝类目code改为支付宝品类ID
		sumpayMcc.setAlipayMchntType(sumpayMcc.getMchntType());
		sumpayMcc.setMcc(sumPayMccList.get(0).getMcc());
		Map<String, Object> req = new HashMap<>();
		SumpayOpenProduInfo openProduInfo = new SumpayOpenProduInfo();
		openProduInfo.setMchntId(outMerchantCode);
		openProduInfo.setMchntShortName(tdInfo_.getCustName());
		openProduInfo.setAlipayMchntType(sumpayMcc.getAlipayMchntType());
		openProduInfo.setMcc(sumpayMcc.getMcc());
		openProduInfo.setWechatMchntType(sumpayMcc.getWechatMchntType());
		openProduInfo.setRate(sumpayMcc.getRate());
		
		req.put("merList", openProduInfo);
		req.put("channelType", ChannelMerRegist.SUM_PAY);
		
		ChannelResult result = iMerChantIntoServic.openProduct(req);
		logger.info(JSON.toJSONString(result));
		if(ReStatus.SUCCESS == result.getStatus()){
			tdInfo.setReportStatus("10");
			tdInfo.setPatchNo(tdInfo_.getPatchNo());
			tdInfo.setFileStatus("Y");
			fmIncomeMapper.updateTdMerchantDetailInfo(tdInfo);
		}else{
			object.put("result", "FAIL");
			String message = StringUtils.isBlank(result.getReMsg())?"请联系客服" : result.getReMsg();
			object.put("message", message);
			return object.toString();
		}
		
		//开通微信配置
		req.put("mchntId", outMerchantCode);
		req.put("channelType", ChannelMerRegist.SUM_PAY);
		logger.info("开通产品>>>{}",JSONObject.toJSONString(req));
		result = iMerChantIntoServic.wxConfig(req);
		logger.info(JSON.toJSONString(result));
		if(ReStatus.SUCCESS == result.getStatus() && "00".equals(result.getReCode())){
			object.put("result", "SUCCESS");
			object.put("message", "产品开通成功");
		}else{
			object.put("result", "FAIL");
			String message = StringUtils.isBlank(result.getReMsg())?"请联系客服" : result.getReMsg();
			object.put("message", message);
		}
		return object.toString();
	}
	
	
	 /**
	    * 商盟支付报备图片入口
	    */
		@RequestMapping(MerchantReportedPath.SUMPAYMERCHANTINFOREPORTS)
		public ModelAndView  viewMerchantInfoReported(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo detail,String outMerchantCode,String merchantCode,String merchantType,String status){
			ModelAndView mv = new ModelAndView();
			String channlCode = "SUM_PAY";
			if(null == detail || null == detail.getMerchantCode() ){
				detail.setMerchantCode(merchantCode);
			}
			if(null ==detail || null == detail.getChannelNo()){
				detail.setChannelNo(channlCode);
			}
			if(null ==detail || null == detail.getOutMerchantCode()){
				detail.setOutMerchantCode(outMerchantCode);
			}
			/***查询渠道***/
			List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
			/***查询报备信息***/
			List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
			/***查询省份信息***/
			String superiorAreaCode = "1";
			List<SumPayArea> proviceList = fmIncomeService.getSumPayProvinceList(superiorAreaCode);
			/***查询银行信息***/
			List<Bank> bankCdList = fmIncomeService.getSumPayBankList();
			/***查询支付功能Id***/
			List<TbFmTradeInfo> powerIdList = fmIncomeService.getPowerIdList();
			/***查询翼支付商户行业信息***/
			List<Industry> industryList = fmIncomeService.getIndustryList();
			
			if(null!=reportedList && reportedList.size()>0){
				mv.addObject("reportedList", reportedList);
			}
			if(null!=channlInfoList && channlInfoList.size()>0){
				mv.addObject("infoList", channlInfoList);
			}
			if(null!=proviceList && proviceList.size()>0){
				mv.addObject("provinceList", proviceList);
			}
			if(null!=bankCdList && bankCdList.size()>0){
				mv.addObject("bankCdList", bankCdList);
			}
			if(null!=powerIdList && powerIdList.size()>0){
				mv.addObject("powerIdList", powerIdList);
			}
			if(null!=industryList && industryList.size()>0){
				mv.addObject("industryList", industryList);
			}
			
			
			
			merchantCode = detail.getMerchantCode();
			TdCustInfo custInfo = new TdCustInfo();
			if(null != merchantCode){
				custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
			} 
			PicPath picPath = this.getPicPath(custInfo);
			
			mv.addObject("picPath", picPath);
			if(null!=custInfo){
				mv.addObject("custInfo", custInfo);
			}
			mv.addObject("detail", detail);
			mv.addObject("status",status);
			return mv;
		}	
		
		
		/**
		 * 提交商盟图片资料报备
		 * @param request
		 * @param response
		 * @return
		 */
		@RequestMapping(MerchantReportedPath.SUMPAYPHOTOINFOMERCHANTREPORT)
		@ResponseBody
		public String photoList(HttpServletRequest request,HttpServletResponse response,SumPayCoBean cr){
			JSONObject object = new JSONObject();
			JSONObject sumpayResult = new JSONObject();
			request.setAttribute("merchantCode", cr.getMerchantCode().trim());
			if(null == cr.getOutMerchantCode()){
				object.put("result", "FAILURE");
				object.put("message", "外部商户号为空");
				return object.toString();
			}
			sumpayResult = fmIncomeService.sumpayFileUpload(cr);
			if("SUCCESS".equals(sumpayResult.get("result"))){
				object.put("result", "SUCCESS");
				object.put("message", "报备成功");
			}else{
				sumpayResult.put("result", "FAILURE");
				if(sumpayResult.get("message") == "" && sumpayResult.get("message") == null){
					object.put("message", "商盟进件明确失败");
				}else {
					object.put("message", sumpayResult.get("message"));
				}
			}
			return object.toString();
		}
		
		/**查询商盟支付宝产品类目*/
		@RequestMapping(MerchantReportedPath.SELZFBPRODUCT)
		@ResponseBody
		public String selZFBProduct(HttpServletRequest request,HttpServletResponse response){
			JSONObject object = new JSONObject();
			String one = request.getParameter("one");
			if(!StringUtils.isBlank(one)){
				List<SumpayMcc> sumPayMccList = fmIncomeService.getSumPayMccTwoList(one);
				if(null!=sumPayMccList &&sumPayMccList.size()>0){
					object.put("result", "SUCCESS");
					object.put("sumPayMccList", sumPayMccList);
				}else{
					object.put("result", "FAIL");
					object.put("message", "一级类目为空");
				}
			}
			return object.toString();
		}
		
		/**查询商盟支付宝产品类目*/
		@RequestMapping(MerchantReportedPath.SELZFBPRODUCTMCC)
		@ResponseBody
		public String selZFBProductMcc(HttpServletRequest request,HttpServletResponse response,SumpayMcc sumpayMcc){
			JSONObject object = new JSONObject();
//			String one = request.getParameter("one");
//			String two = request.getParameter("two");
			/*if(!StringUtils.isBlank(one)){*/
				List<SumpayMcc> sumPayMccList = fmIncomeService.getSumPayMccThreeList(sumpayMcc);
				if(null!=sumPayMccList &&sumPayMccList.size()>0){
					object.put("result", "SUCCESS");
					object.put("sumPayMccList", sumPayMccList);
				}else{
					object.put("result", "FAIL");
					object.put("message", "一级类目为空");
				}
			/*}*/
			return object.toString();
		}
		
		/**查询商盟微信产品类目*/
		@RequestMapping(MerchantReportedPath.SELWXPRODUCT)
		@ResponseBody
		public String selWXProduct(HttpServletRequest request,HttpServletResponse response){
			JSONObject object = new JSONObject();
			String one = request.getParameter("one");
			if(!StringUtils.isBlank(one)){
				List<SumpayMcc> sumPayMccList = fmIncomeService.getSumPayMccTwoWXList(one);
				if(null!=sumPayMccList &&sumPayMccList.size()>0){
					object.put("result", "SUCCESS");
					object.put("sumPayMccList", sumPayMccList);
				}else{
					object.put("result", "FAIL");
					object.put("message", "一级类目为空");
				}
			}
			return object.toString();
		}
		
		/**查询商盟微信产品类目*/
		@RequestMapping(MerchantReportedPath.SELWXPRODUCTMCC)
		@ResponseBody
		public String selWXProductMcc(HttpServletRequest request,HttpServletResponse response,SumpayMcc sumpayMcc){
			JSONObject object = new JSONObject();
			/*String one = request.getParameter("one");
			String two = request.getParameter("two");
			if(!StringUtils.isBlank(one)){*/
				List<SumpayMcc> sumPayMccList = fmIncomeService.getSumPayMccThreeWXList(sumpayMcc);
				if(null!=sumPayMccList &&sumPayMccList.size()>0){
					object.put("result", "SUCCESS");
					object.put("sumPayMccList", sumPayMccList);
				}else{
					object.put("result", "FAIL");
					object.put("message", "一级类目为空");
				}
			/*}*/
			return object.toString();
		}
		
		
		
		
		public PicPath getPicPath(TdCustInfo custInfo){
			PicPath picPath = new PicPath();
			//获取开户许可证路径
			String openPath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "03");
			if(null != openPath){
				picPath.setOpenPath(openPath);
			}
			//获取身份证正反面
			String  identityImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "04");
			String[] paths = null;
			paths = identityImagePath.split(";");
			//正面
			String identityImagePath0 = paths[0];
			//反面
			String identityImagePath1 = paths[1];
			if(null != identityImagePath0){
				picPath.setIdentityImagePath0(identityImagePath0);
			}
			if(null != identityImagePath1){
				picPath.setIdentityImagePath1(identityImagePath1);
			}
			//获取营业执照
			String licensePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "02");
			if(null != licensePath){
				picPath.setLicensePath(licensePath);
			}
			//获取银行卡照
			String bankCardPath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "07");
			if(null != bankCardPath){
				picPath.setBankCardPath(bankCardPath);
			}
			//获取门头照
			String doorPath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "08");
			if(null != doorPath){
				picPath.setDoorPath(doorPath);
			}
			//获取店内照
			String shopInteriorPath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "18");
			if(null == shopInteriorPath && null != doorPath){
				String[] pathDoorImage = null;
				pathDoorImage = doorPath.split(";");
				if(pathDoorImage.length >1){
					shopInteriorPath = pathDoorImage[1];
				}else{
					//内景
					shopInteriorPath = doorPath;
				}
				picPath.setShopInteriorPath(shopInteriorPath);
			}else {
				picPath.setShopInteriorPath(shopInteriorPath);
			}
			
			return picPath;
			
		}
}
