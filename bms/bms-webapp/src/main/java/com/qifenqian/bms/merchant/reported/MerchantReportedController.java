package com.qifenqian.bms.merchant.reported;
import java.net.URLEncoder;
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

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.merchant.bean.CategoryCodeInfo;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.merchant.reported.bean.Bank;
import com.qifenqian.bms.merchant.reported.bean.ChannlInfo;
import com.qifenqian.bms.merchant.reported.bean.CrInComeBean;
import com.qifenqian.bms.merchant.reported.bean.Industry;
import com.qifenqian.bms.merchant.reported.bean.MerchantFilingInfo;
import com.qifenqian.bms.merchant.reported.bean.Province;
import com.qifenqian.bms.merchant.reported.bean.SumpayMcc;
import com.qifenqian.bms.merchant.reported.bean.TbFmTradeInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.service.CrIncomeService;
import com.qifenqian.bms.merchant.reported.service.FmIncomeService;


@Controller
@RequestMapping(MerchantReportedPath.BASE)
public class MerchantReportedController {

   private Logger logger = LoggerFactory.getLogger(MerchantReportedController.class);
	
   @Autowired
   private TradeBillService tradeBillService;
	
   @Autowired
   private CrIncomeService crIncomeService;
   
   @Autowired
   private FmIncomeService fmIncomeService;
   
   @Autowired
   private FmIncomeMapperDao fmIncomeMapperDao;
	 
	/**
	 * 提交报备
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(MerchantReportedPath.GOTOREPORT)
	@ResponseBody
	public String list(HttpServletRequest request,HttpServletResponse response,String merchantCode,String channl){
		JSONObject object = new JSONObject();
		request.setAttribute("merchantCode", merchantCode.trim());
		
		String result = "";
		//查询商户报备信息
		if(channl.equals("iCr")){//华润银行
			//查询该商户角色是否报备
			MerchantFilingInfo info = new MerchantFilingInfo();
			String role = (String)request.getParameter("role");
			info.setMchRole(role);
			info.setMerchantCode(merchantCode);
			info.setChannelNo(channl);
			List<MerchantFilingInfo> list = crIncomeService.verifyFiling(info);
			if(list != null && list.size() > 0){
				if("00".equals(role)){
					if(list.size()>=2){
						object.put("result", "FAILURE");
						object.put("message", "商户已经报备，请勿重新提交");
						return object.toString();
					}
				}else{
					object.put("result", "FAILURE");
					object.put("message", "商户已经报备，请勿重新提交");
					return object.toString();
				}
				
			}
			//调用华润银行
			 result = crIncomeService.doReported(request, response);
		  }
		//合利宝报备
		if(channl.equals("helipay")){
			
			//查询该商户角色是否报备
			MerchantFilingInfo info = new MerchantFilingInfo();
			info.setMerchantCode(merchantCode);
			info.setChannelNo(channl);
			List<MerchantFilingInfo> list = crIncomeService.verifyFiling(info);
			if(list != null && list.size() >0){
				object.put("result", "FAILURE");
				object.put("message", "商户已经报备，请勿重新提交");
				return object.toString();
			}
			result =  crIncomeService.helipayReport(request);
		}
		if(result.equals("SUCCESS")){
			object.put("result", "SUCCESS");
			object.put("message", "处理成功");
		}else {
		  object.put("result", "FAILURE");
		  object.put("message", "处理异常");
		}
		return object.toString();
	}
	/**
	 * 
	 检查商户是否可以报备
	 */
	@RequestMapping(MerchantReportedPath.CHECKMERCHANTREPORT)
	@ResponseBody
	public String checkMerchantStatus(HttpServletRequest request,HttpServletResponse response){
		JSONObject object = new JSONObject();
		String merchantCode = request.getParameter("merchantCode");
		if(!StringUtils.isBlank(merchantCode)){
			List<CrInComeBean> inComeInfo = crIncomeService.getInComeInfo(merchantCode);
			if(null!=inComeInfo &&inComeInfo.size()>0){
				object.put("result", "SUCCESS");
			}else{
				object.put("result", "FAIL");
			}
		}
		return object.toString();
	}

	  /**
	    * 商户报备入口
	    */
		@RequestMapping(MerchantReportedPath.BMSREPORT)
		public ModelAndView  showReported(HttpServletRequest request,HttpServletResponse response,TdMerchantDetailInfo detail){
			ModelAndView mv = new ModelAndView();
			/***查询渠道***/
			List<ChannlInfo> channlInfoList = crIncomeService.getChannlInfoList();
			/***查询报备信息***/
			List<TdMerchantDetailInfo> reportedList = fmIncomeService.getMerchantDetailInfoList(detail);
			/***查询省份信息***/
			List<Province> proviceList = fmIncomeService.getprovinceList();
			/***查询省份信息***/
			List<Bank> bankList = fmIncomeService.getBankList();
			/***查询支付功能Id***/
			List<TbFmTradeInfo> powerIdList = fmIncomeService.getPowerIdList();
			/***查询翼支付商户行业信息***/
			List<Industry> industryList = fmIncomeService.getIndustryList();
			/***查询商盟支付宝产品行业信息***/
			List<SumpayMcc> sumpayMccList = fmIncomeService.getSumpayMccList();
			/***查询商盟微信产品行业信息***/
			List<SumpayMcc> sumpayMccWXList = fmIncomeService.getSumpayMccWXList();
			/***查询商盟微信产品行业信息***/
			List<SumpayMcc> sumpayMccZFBList = fmIncomeService.getSumpayMccZFBList();
			
			
			String merchantCode = detail.getMerchantCode();
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
			if(null!=sumpayMccList){
				mv.addObject("sumpayMccList", sumpayMccList);
			}
			if(null!=sumpayMccWXList){
				mv.addObject("sumpayMccWXList", sumpayMccWXList);
			}
			if(null!=sumpayMccZFBList){
				mv.addObject("sumpayMccZFBList", sumpayMccZFBList);
			}
			return mv;
		}	   
		
		
		@RequestMapping(MerchantReportedPath.LIST)
		public ModelAndView list(MerchantFilingInfo requestBean) {
			// 返回视图
			ModelAndView mv = new ModelAndView(MerchantReportedPath.BASE + MerchantReportedPath.LIST);
			//查询报备列表
			List<MerchantFilingInfo> list = crIncomeService.selectReportList(requestBean);
			// 列表
			mv.addObject("reportedList", list);
			mv.addObject("queryBean", requestBean);
			return mv;
		}
		
		
		/**
		 * 导出商户结算
		 * @param requestBean
		 * @param request
		 * @param response
		 */
		@RequestMapping(MerchantReportedPath.EXPORT)
		public void exportExcel(MerchantFilingInfo requestBean, HttpServletRequest request, HttpServletResponse response) {
			logger.info("导出excel商户结算表");

			try {
				List<MerchantFilingInfo> excels = crIncomeService.selectReportList(requestBean);
				
				String[] headers = {"报备编号","商户编号","商户角色(0 线上商户|1 线下商户)","报备批次号","报备渠道(iCr 华润银行)","报备状态(01 未提交报备|00已提交报备)","报备文件上传状态","报备审核状态","商户进件状态","进件描述","创建时间",
						"修改时间"};
				String fileName = DatetimeUtils.dateSecond() + "_商户报备信息.xls";
				fileName = URLEncoder.encode(fileName,"UTF8");  
				Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "商户报备信息表", request);
				DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
				logger.info("导出excel商户报备信息表成功");
			} catch (Exception e) {
				logger.error("导出excel商户报备信息表异常", e);
				e.printStackTrace();
			}

		}
		
		/**
		 * 查询商户行业信息
		 * @param request
		 * @return
		 */
		@RequestMapping(MerchantReportedPath.QUERYCATEGORYINFO)
		@ResponseBody
		public String queryMerchantCategoryInfo(HttpServletRequest request){
			String merchantId = request.getParameter("merchantId");
			logger.debug("查询商户行业信息[{}]",merchantId);
			JSONObject object = new JSONObject();
			try {
				CategoryCodeInfo info = crIncomeService.getCategoryTypeInfo(merchantId);
				if(info!= null){
					object.put("result", "SUCCESS");
					object.put("info", info);
				}else{
					object.put("result", "FAIL");
				}
				
			} catch (Exception e) {
				logger.error("查询商户行业信息异常",e);
				object.put("result", "FAIL");
			}
			return object.toJSONString();
		}
}
