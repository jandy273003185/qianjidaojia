package com.qifenqian.bms.merchant.reported;
import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.qifenqian.bms.merchant.reported.bean.Bank;
import com.qifenqian.bms.merchant.reported.bean.ChannlInfo;
import com.qifenqian.bms.merchant.reported.bean.City;
import com.qifenqian.bms.merchant.reported.bean.CrInComeBean;
import com.qifenqian.bms.merchant.reported.bean.Industry;
import com.qifenqian.bms.merchant.reported.bean.MerchantCity;
import com.qifenqian.bms.merchant.reported.bean.Province;
import com.qifenqian.bms.merchant.reported.bean.SuiXingBean;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.service.CrIncomeService;
import com.qifenqian.bms.merchant.reported.service.FmIncomeService;
import com.seven.micropay.base.domain.ChannelResult;
import com.seven.micropay.channel.domain.merchant.suixinpayInfo.SxPayUploadFileInfo;
import com.seven.micropay.channel.enums.ChannelMerRegist;
import com.seven.micropay.channel.enums.suixinpay.SuixinBankType;
import com.seven.micropay.channel.service.IMerChantIntoService;
import com.seven.micropay.commons.util.DateUtil;

@Controller
@RequestMapping(MerchantReportedPath.BASE)
public class SuiXingPayMerchantReportsController {

   private Logger logger = LoggerFactory.getLogger(SuiXingPayMerchantReportsController.class);
	
   @Autowired
   private FmIncomeService fmIncomeService;
   
   @Autowired
   private CrIncomeService crIncomeService;
   
   @Autowired
   private FmIncomeMapperDao fmIncomeMapperDao;
   
   @Autowired
   private IMerChantIntoService iMerChantIntoService;
   
   @Autowired
   private MerchantEnterService merchantEnterService;

   @Value("${SX_FILE_SAVE_PATH}")
   private String SX_FILE_SAVE_PATH;
	
	
   /**
    * 随行付商户报备入口
    */
	@RequestMapping(MerchantReportedPath.SUIXINGPAYMERCHANTREPORTS)
	public ModelAndView  viewMerchantReported(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo detail,String merchantCode,String status){
		ModelAndView mv = new ModelAndView();
		String channlCode = "SUIXING_PAY";
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
		/***查询随行付银行地区信息***/
		List<Province> proviceList = fmIncomeService.getSuiXingProvinceList();
		/***查询银行信息***/
		List<Bank> bankList = fmIncomeService.getBankList();
		/***查询随行付商户行业信息***/
		List<Industry> industryList = fmIncomeService.getSuiXingIndustryList();
		/***查询随行付商户注册地区信息***/
		String areaType ="2";
		List<MerchantCity> merchantProvinceList = fmIncomeService.getSuiXingMerchantCityList(areaType);
		merchantCode = detail.getMerchantCode();
		TdCustInfo custInfo = new TdCustInfo();
		if(null != merchantCode){
			custInfo = fmIncomeMapperDao.getInComeInfo(merchantCode);
		}
		if(null!=reportedList && reportedList.size()>0){
			mv.addObject("reportedList", reportedList);
			String remark =  reportedList.get(0).getRemark();
			mv.addObject("remark", remark);
		}
		//获取图片路径
		MerchantVo merchantVo = new MerchantVo();
		merchantVo.setAuthId(custInfo.getAuthId());
		merchantVo.setCustId(custInfo.getCustId());
		PicturePath picturePath = merchantEnterService.getPicPath(merchantVo);
		mv.addObject("picturePathVo", picturePath); 
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
		if(null!=custInfo){
			mv.addObject("custInfo", custInfo);
		}
		mv.addObject("status",status);
		return mv;
	}	
	
	/**
	 * 查询银行所在地区
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(MerchantReportedPath.SELSUIXINGCITY)
	@ResponseBody
	public String selCity(HttpServletRequest request,HttpServletResponse response){
		JSONObject object = new JSONObject();
		String province = request.getParameter("province");
		if(!StringUtils.isBlank(province)){
			List<City> cityList = fmIncomeService.getSuiXingCityList(province);
			if(null!=cityList &&cityList.size()>0){
				object.put("result", "SUCCESS");
				object.put("cityList", cityList);
			}else{
				object.put("result", "FAIL");
				object.put("message", "省份编号为空");
			}
		}
		return object.toString();
	}
	
	/**
	 * 查询商户所在地区
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(MerchantReportedPath.SELSUIXINGAREA)
	@ResponseBody
	public String selArea(HttpServletRequest request,HttpServletResponse response){
		JSONObject object = new JSONObject();
		String superiorAreaCode = request.getParameter("superiorAreaCode");
		if(!StringUtils.isBlank(superiorAreaCode)){
			List<MerchantCity> areaList = fmIncomeService.getSuiXingAreaList(superiorAreaCode);
			if(null!=areaList &&areaList.size()>0){
				object.put("result", "SUCCESS");
				object.put("areaList", areaList);
			}else{
				object.put("result", "FAIL");
				object.put("message", "区域编号为空");
			}
		}
		return object.toString();
	}
	

	/**
	 * 随行付上传文件
	 */
	@RequestMapping(MerchantReportedPath.SELSUIXINGFILEUPLOAD)
	@ResponseBody
	public String  fileUpload(HttpServletRequest request,HttpServletResponse response,String merchantCode,String status,String patchNo) {
		JSONObject object = new JSONObject();
		SuiXingBean cr = new SuiXingBean();
		cr.setMerchantCode(merchantCode);
		TdMerchantDetailInfo detail = new TdMerchantDetailInfo();
		detail.setMerchantCode(merchantCode);
		detail.setChannelNo("SUIXING_PAY");
		detail.setPatchNo(patchNo);
		TdMerchantDetailInfo detailInfo = fmIncomeMapperDao.selMerchantDetailInfo(detail);
		//不为空即资料已提交
		if(null != detailInfo && detailInfo.getReportStatus() != "1" && detailInfo.getReportStatus() != "21") {
			if(StringUtils.isNotBlank(detailInfo.getRemark()) && StringUtils.isNotBlank(detailInfo.getOutMerchantCode())){
				cr.setTaskCode(detailInfo.getRemark());
			}
		}
		
		try {
			//文件更名压缩并上传服务器
			logger.info("文件更名压缩并上传服务器" +  "------------------------------");
			Map<String, String> fileResult = fmIncomeService.download(request, response, cr);
			if("SUCCESS".equals(fileResult.get("result"))){
				
				//文件上传至随行付
//				File zipFile = new File(cr.getMerchantCode() +".zip") ;	// 定义压缩文件名称
				
//				String path =p.getProperty("SX_FILE_SAVE_PATH") + File.separator  + File.separator  +zipFile;
//				String filePath = cr.getMerchantCode() + DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS);
				//本地path
//				String path = "D:"+  File.separator +  p.getProperty("SX_FILE_SAVE_PATH") + File.separator + fileResult.get("filePath") + File.separator + cr.getMerchantCode() +".zip";
				//服务器地址
				String path = SX_FILE_SAVE_PATH + File.separator + cr.getMerchantCode() +".zip";
				logger.info("+++++++++++" + path + "------------------------------");
				
				SxPayUploadFileInfo uploadFileInfo = new SxPayUploadFileInfo();
				
				uploadFileInfo.setReqId(DateUtil.format(new Date(), DateUtil.YYYYMMDDHHMMSS));
				uploadFileInfo.setFilePath(path);
				Map<String, Object> req = new HashMap<>();
				ChannelResult result = new ChannelResult();
				if(null != cr.getTaskCode() && StringUtils.isNotBlank(detailInfo.getOutMerchantCode())){
					uploadFileInfo.setTaskCode(cr.getTaskCode());
					req.put("merList", uploadFileInfo);
					req.put("channelType", ChannelMerRegist.SUIXING_PAY);
					logger.info("文件更新至随行付" + "------------------------------");
					result = iMerChantIntoService.updataMerchatAdd(req);
					logger.info("文件更新至随行付返回信息"+ result.getChannelCode() + "------------------------------" +result.getReMsg());
				}else {
					req.put("merList", uploadFileInfo);
					req.put("channelType", ChannelMerRegist.SUIXING_PAY);
					
					logger.info("文件上传至随行付" + "------------------------------");
					result = iMerChantIntoService.fileUpload(req);
					logger.info("文件上传随行付返回信息" + result.getChannelCode() + "------------------------------");
				}
				
				if("00".equals(result.getChannelCode())){
				    String taskCode = (String) result.getData().get("taskCode");
					if(null != detailInfo && detailInfo.getReportStatus() != "1" && detailInfo.getReportStatus() != "21"){
						CrInComeBean cc =new CrInComeBean();
						cc.setMerchantCode(cr.getMerchantCode());
						cc.setChannelNo("SUIXING_PAY");
						cc.setPatchNo(patchNo);
						TdMerchantDetailInfo td = fmIncomeService.getTdMerchantReport(cc);
						TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
						tdInfo.setRemark(taskCode);
						tdInfo.setChannelNo("SUIXING_PAY");
						tdInfo.setMerchantCode(merchantCode);
						tdInfo.setPatchNo(td.getPatchNo());
						tdInfo.setReportStatus("Y");
						tdInfo.setFileStatus("Y");
						tdInfo.setDetailStatus("0");
						String mchntStatus = "0";
						fmIncomeService.UpdateMerReportAndMerDetailInfo(tdInfo, mchntStatus);
//						fmIncomeMapper.updateTdMerchantDetailInfo(tdInfo);	
					}
					object.put("result", "SUCCESS");
					object.put("message", taskCode);
					logger.info("上传文件结束-------------------" + taskCode);
				}else {
					object.put("result", "FAIL");
					object.put("message", result.getReMsg());
					logger.info("上传文件结束-------------------" + result.getReMsg());
					return object.toJSONString();
				}
			}else{
				object.put("result", "FAIL");
				object.put("message", fileResult.get("message"));
				logger.info("上传文件结束-------------------" + fileResult.get("message"));
				return object.toJSONString();
			}
		} catch (Exception e) {
			logger.error("随行付上传文件失败",e);
			object.put("result", "FAILURE");
			object.put("message", e);
			return object.toJSONString();
		}
		return object.toJSONString();
	}
	
	
	/**
	 * 随行付提交报备
	 */
	
	@RequestMapping(MerchantReportedPath.SUXINGPAYSUBMITREPORT)
	@ResponseBody
	public String list(HttpServletRequest request,HttpServletResponse response,SuiXingBean cr){
		logger.info("商户随行付开始信息提交进件："+ "--------------------");
		JSONObject object = new JSONObject();
		JSONObject bestResult = new JSONObject();
		request.setAttribute("merchantCode", cr.getMerchantCode().trim());
		SuixinBankType suiXing = getSuixinBankCodeByBankCode(cr.getSuiXinBank());
		cr.setSuiXinBank(suiXing.getName());
		try {
			//查询商户报备表
			CrInComeBean cc =new CrInComeBean();
			cc.setMerchantCode(cr.getMerchantCode());
			cc.setChannelNo(cr.getChannelNo());
			TdMerchantDetailInfo td = fmIncomeService.getTdMerchantReport(cc);
			//该商户已报备
			if(null!= td && !("O".equals(td.getReportStatus())) ){
				//该商户已报备成功
				if("Y".equals(td.getReportStatus())){
				object.put("result", "FAILURE");
					object.put("message", "商户已经报备，请勿重新提交");
					return object.toString();
				}else{//商户报备失败
					TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
					tdInfo.setMerchantCode(td.getMerchantCode());
					tdInfo.setChannelNo(td.getChannelNo());
					tdInfo.setReportStatus("E");
					tdInfo.setDetailStatus("99");
					tdInfo.setRemark(cr.getTaskCode());
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
				info.setDetailStatus("99");
				info.setProvCode(cr.getMerchantProvince());
				info.setCityCode(cr.getMerchantCity());
				info.setContryCode(cr.getMerchantArea());
				info.setBankCode(SuixinBankType.valueOf(cr.getSuiXinBank()).getCode());
				info.setBranchBankName(cr.getInterBankName());
				info.setMobileNo(cr.getMobileNo());
				info.setRemark(cr.getTaskCode());
				info.setRate(cr.getRate());
				fmIncomeService.insertTdMerchantReport(info);
				info.setReportStatus("99");
				fmIncomeService.inserTdMerchantDetailInfo(info);
			}
			
			cr.setLbnkNo(cr.getInterBankName());
			//商户随行付进件
			logger.info("商户随行付开始进件："+ "--------------------");
			bestResult = fmIncomeService.suiXingReported(cr);
			if("SUCCESS".equals(bestResult.get("result"))){
				object.put("result", "SUCCESS");
				object.put("message", "报备成功");
				logger.info("商户随行付进件成功："+ "--------------------");
			}else{
				object.put("result", "FAILURE");
				if(bestResult.get("message") == "" && bestResult.get("message") == null){
					object.put("message", "随行付进件明确失败");
					logger.info("商户随行付进件失败："+ "--------------------");
					return object.toString();
				}else {
					object.put("message", bestResult.get("message"));
					logger.info("商户随行付进件失败--------------------" + bestResult.get("message"));
					return object.toString();
				}
			}
			
		} catch (Exception e) {
			logger.error("随行付进件失败",e);
			object.put("result", "FAILURE");
			object.put("message", e);
			return object.toString();
		}
		return object.toString();
	}

	public static SuixinBankType getSuixinBankCodeByBankCode(String bankCode){
		for(SuixinBankType b:SuixinBankType.values()){
			if(b.getCode().equals(bankCode) || b.getCode() == bankCode){
				return b;
			}
		}
		return null;
	}
}
