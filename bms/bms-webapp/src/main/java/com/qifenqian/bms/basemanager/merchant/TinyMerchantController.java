package com.qifenqian.bms.basemanager.merchant;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.mapper.BankMapper;
import com.qifenqian.bms.basemanager.city.bean.RegionsBean;
import com.qifenqian.bms.basemanager.city.service.CityService;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.merchant.auding.service.audingService;
import com.qifenqian.bms.basemanager.merchant.bean.CategoryCodeInfo;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.bean.TinyMerchantExport;
import com.qifenqian.bms.basemanager.merchant.service.CategoryCodeInfoService;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.platform.utils.SequenceUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Controller
@RequestMapping(TinyMerchantPath.BASE)
public class TinyMerchantController {

	private Logger logger = LoggerFactory.getLogger(TinyMerchantController.class);

	@Autowired
	private BankMapper bankMapper;

	@Autowired
	private MerchantService merchantService;

	@Autowired
	private TradeBillService tradeBillService;
	
	@Autowired
	private TdCustInfoService tdCustInfoService;
	
	@Autowired
	private CategoryCodeInfoService categoryCodeInfoService;
	
	@Autowired
	private CityService cityService;
	@Autowired
	private audingService audingService;
	
	
	/** 跳转到注册页面 */
	@RequestMapping(TinyMerchantPath.REGISTPAGE)
	public ModelAndView toRegistPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(TinyMerchantPath.BASE + TinyMerchantPath.REGISTPAGE);
		Bank bank = new Bank();
		//获取省份信息
		List<RegionsBean> provinceList = cityService.selectSpProvince();
		mv.addObject("banklist", bankMapper.selectBanks(bank));
		mv.addObject("provinceList", provinceList);
		return mv;
	}

	/** 微商户注册 */
	@SuppressWarnings("unchecked")
	@RequestMapping(TinyMerchantPath.REGISTER)
	@ResponseBody
	public String register(HttpServletRequest request, Merchant merchant) {
		logger.info("================= 微商户注册开始 =================");
		JSONObject object = new JSONObject();
		String merchantCode = request.getParameter("merchantCode");
		try {
			if(StringUtils.isEmpty(merchantCode)){
				if("1".equals(merchant.getCustType())){
					merchant.setMerchantCode(SequenceUtils.getMerchantSeqNo("C"));
				}else{
					merchant.setMerchantCode(SequenceUtils.getMerchantSeqNo("P"));
				}
			}else{
				merchant.setMerchantCode(merchantCode);
			}
			merchant.setRoleId("ent");

			String email = request.getParameter("email"); 					// 邮箱
			merchant.setFcontactunitNumber("1000073");//往来单位编号
			Map<String, String> custScanMap = (Map<String, String>) request.getSession().getAttribute("custScanMap");
			merchantService.saveTinyMerchantRegist(email, merchant.getCustId(),merchant,custScanMap);
			object.put("result", "SUCCESS");
			object.put("message", "注册成功");
			logger.info("================= 微商户注册成功 =================");
		} catch (Exception e) {
			logger.error("注册失败", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}
	
	/**
	 * 获取行业类目数据
	 * @return
	 */
	@RequestMapping(TinyMerchantPath.GETCATEGORYTYPE)
	@ResponseBody
	public String getCategoryType(String categoryType,String channlCode){
		logger.debug("获取行业类目数据");
		//获取行业类目编号对应的数据列
		JSONObject jon = new JSONObject();
		try {
			List<CategoryCodeInfo>  categoryList  = categoryCodeInfoService.getCategoryCodeInfoList(categoryType,channlCode);
			jon.put("categoryList", categoryList);
			jon.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("获取行业类目异常{}", e);
			jon.put("result", "FAIL");
		}
		
		return jon.toJSONString();
	}
	/**
	 * 根据行目ID获取列表信息
	 * @param categoryId
	 * @return
	 */
	@RequestMapping(TinyMerchantPath.GETCATEGORYTYPEBYID)
	@ResponseBody
	public String getCategoryTypeDataById(String categoryId){
		JSONObject jon = new JSONObject();
		List<CategoryCodeInfo> categoryList = categoryCodeInfoService.getCategoryCodeInfoListById(categoryId);
		jon.put("categoryList", categoryList);
		if(categoryList != null && categoryList.size()>0){
			jon.put("categoryTypeId", categoryList.get(0).getCategoryTypeId());
		}
		
		jon.put("result", "SUCCESS");
		return jon.toJSONString();
	}
	/** 文件上传 */
	@RequestMapping(TinyMerchantPath.FILEUPLOAD)
	@ResponseBody
	public String fileUpload(HttpServletRequest request, HttpServletResponse response) {
		JSONObject object = new JSONObject();
		try {
			String custId = GenSN.getSN();
			Map<String, String> result = merchantService.compressUpload(request, custId);
			object.put("result", result.get("result"));
			object.put("message", result.get("message"));
			object.put("custId", custId);
			request.getSession().setAttribute("custScanMap", result);
		} catch (Exception e) {
			logger.error("文件上传失败",e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}

	/** ----------------------------------------校验相关方法start---------------------------------------- **/
	/** 校验邮箱是否已经存在 */
	@RequestMapping(TinyMerchantPath.VALIDATEEMAIL)
	@ResponseBody
	public String validateEmail(String email, String custId) {
		logger.info("********************校验邮箱********************");
		JSONObject object = new JSONObject();
		try {
			TdLoginUserInfo loginInfo = merchantService.selectLoginUserInfoByEmail(email, custId,"ent");
			if (null == loginInfo) {
				object.put("result", "SUCCESS");
			} else {
				object.put("result", "FAIL");
			}
		} catch (Exception e) {
			logger.error("邮箱匹配出现问题" + e);
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}
	
	/** 验证微商户二维码编号是否已存在 **/
	@RequestMapping(TinyMerchantPath.VALIDATEMERCHANTCODE)
	@ResponseBody
	public String validateMerchantCode(String merchantCode){
		logger.info("***********校验微商户二维码编号***********");
		JSONObject object = new JSONObject();
		try {
			int result = merchantService.validateTinyMerchantCode(merchantCode);
			if (result <= 0) {
				object.put("result", "SUCCESS");
			} else {
				object.put("result", "FAIL");
			}
		} catch (Exception e) {
			logger.error("微商户二维码编号已经被占用" + e);
			object.put("result", "FAIL");
			// object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}
	
	/** 验证手机号码是否已注册 **/
	@RequestMapping(TinyMerchantPath.VALIDATEMOBILE)
	@ResponseBody
	public String validateMobile(String mobile){
		logger.info("校验手机号码是否已经存在");
		JSONObject object = new JSONObject();
		try {
			int result = merchantService.validateMobile(mobile);
			if (result <= 0) {
				object.put("result", "SUCCESS");
			} else {
				object.put("result", "FAIL");
			}
		} catch (Exception e) {
			logger.error("校验手机号码出现问题" + e);
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}
	
	/** 验证营业执照注册号是否被占用 **/
	@RequestMapping(TinyMerchantPath.VALIDATELICENSE)
	@ResponseBody
	public String validateBusinessLicense(String businessLicense){
		logger.info("校验营业执照注册号是否已经存在");
		JSONObject object = new JSONObject();
		try {
			int result = merchantService.validateBusinessLicense(businessLicense,"ent");
			if (result <= 0) {
				object.put("result", "SUCCESS");
			} else {
				object.put("result", "FAIL");
			}
		} catch (Exception e) {
			logger.error("校验营业执照注册号出现问题" + e);
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}
	
	/** ----------------------------------------校验相关方法end---------------------------------------- **/

	/** ----------------------------------------商户列表start---------------------------------------- **/
	/** 微商户列表 */
	@RequestMapping(TinyMerchantPath.LIST)
	public ModelAndView auditList(MerchantVo merchantVo) {
		Bank bank = new Bank();
		// Rule rule = new Rule();
		ModelAndView mv = new ModelAndView(TinyMerchantPath.BASE + TinyMerchantPath.LIST);
		String userId  = String.valueOf(WebUtils.getUserInfo().getUserId());
		//是否有权限查看所有订单
		boolean isAllList = tdCustInfoService.isAllList(userId);
		List<MerchantVo> list = null;
		/** 去掉多余的空格 */
		if (!StringUtils.isBlank(merchantVo.getMerchantCode())) {
			merchantVo.setMerchantCode(merchantVo.getMerchantCode().trim());
		}
		if (!StringUtils.isBlank(merchantVo.getCustName())) {
			merchantVo.setCustName(merchantVo.getCustName().trim());
		}
		if (!StringUtils.isBlank(merchantVo.getEmail())) {
			merchantVo.setEmail(merchantVo.getEmail().trim());
		}
		if(isAllList){
			list = merchantService.selectTinyMerchants(merchantVo);
		}else{
			merchantVo.setUserId(userId);
			merchantVo.setUserName(WebUtils.getUserInfo().getUserName());
			list = merchantService.tinyMyMerchantsList(merchantVo);
		}
		
		mv.addObject("banklist", bankMapper.selectBanks(bank));
		mv.addObject("merchantList", JSONObject.toJSON(list));
		 
		mv.addObject("queryBean", merchantVo);
		return mv;
	}
	
	/** Get请求编码转换 **/
	private String getEncoding(String param) throws UnsupportedEncodingException {
		// 用IOS回退
		byte[] bytes = param.getBytes("iso-8859-1");
		// 用UTF-8重新编码
		param = new String(bytes, "utf-8");
		return param;
	}

	/** 导出微商户列表 **/
	@RequestMapping(TinyMerchantPath.EXPORTMERCHANTINFO)
	public void exportExcel(MerchantVo merchantVo, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出微商户列表信息");
		try {
			// System.out.println(">>>>>>>>>>>>" + merchantVo.getCustName());
			/** 手工处理get方式请求中文乱码问题 **/
			String custName = getEncoding(merchantVo.getCustName());
			merchantVo.setCustName(custName);
			// System.out.println(">>>>>>>>>>>>" + merchantVo.getCustName());
			/** 根据条件查询需要导出的数据 **/
			List<TinyMerchantExport> exportTinyMerchantInfo = merchantService.exportTinyMerchantInfo(merchantVo);
			/** 设置excel头 **/
			String[] excelHeaders = {"商户二维码编号" , "客户名称" , "证件号" ,//
					"客户类型" , "商户标志" , "营业执照注册号", "客户状态", "七分钱账户ID" ,//
					"创建人" , "创建时间" , "修改人" , "修改时间" , "手机号码" , "往来单位编号" , //
					"电子邮件" , "登录状态"};
			/** 设置文件名 **/
			String fileName = DatetimeUtils.dateSecond() + "_微商户列表信息.xls";
			/**  **/
			Map<String, String> exportExcel = tradeBillService.exportExcel(exportTinyMerchantInfo, excelHeaders, fileName, "微商户列表", request);
			/**  **/
			DownLoadUtil.downLoadFile(exportExcel.get("filePath"), response, exportExcel.get("fileName"), "xls");
			logger.info("导出excel商户列表成功");
		} catch (Exception e) {
			logger.error("导出excel商户列表异常", e);
			throw new RuntimeException(e);
		}
	}

	/** 根据id查找微商户信息 **/
	@RequestMapping(TinyMerchantPath.FINDTINYMERCHANTINFO)
	@ResponseBody
	public String findTinyMerchantInfoById(String custId) {
		logger.info("查找微商户信息");
		JSONObject jsonObject = new JSONObject();
		MerchantVo merchantVo = merchantService.findTinyMerchantInfo(custId);
		// List<BmsProtocolContent> contents = merchantService.selectContentByCustId(custId); // 查找商户协议
		// if (null != contents && contents.size() > 0) {
		// jsonObject.put("bmsProtocolContent", contents.get(0));
		// }
		jsonObject.put("merchantVo", merchantVo);
		return jsonObject.toJSONString();
	}

	/** 预览微商户图片信息 **/
	@RequestMapping(TinyMerchantPath.PREVIEWTINYMERCHANTIMAGE)
	@ResponseBody
	public void findTinyMerchantImageById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String custId = request.getParameter("custId");
		String certifyType = request.getParameter("certifyType");
		String front = request.getParameter("front");
		String imagePath = merchantService.findTinyMerchantImagePathById(custId, certifyType);
		if (imagePath != null) {
			String[] paths = null;
			// 如果是身份证，因为有正反面，所以需要利用前端传递的参数来区分正反面
			if (certifyType.equals(Constant.CERTIFY_TYPE_MERCHANT_IDCARD)) {
				paths = imagePath.split(";");
				if (front.equals("0")) {
					imagePath = paths[0];
				} else {
					imagePath = paths[1];
				}
			}
//			if (certifyType.equals(Constant.CERTIFY_TYPE_BUSINESS)) {
//				imagePath = imagePath.split(";")[0];
//			}
			
			if(certifyType.equals(Constant.CERTIFY_TYPE_MERCHANT_DOORID)){
				paths = imagePath.split(";");
				if(front.equals("0")){
					imagePath = paths[0];
				}else if(front.equals("1")){
					imagePath = paths[1];
				}else if(front.equals("2")){
					imagePath = paths[2];
				}else if(front.equals("3")){
					imagePath = paths[3];
				}else if(front.equals("4")){
					imagePath = paths[4];
				}
				
			}
			
			OutputStream os = response.getOutputStream();
			File file = new File(imagePath);
			if (file.exists()) {
				FileInputStream fileInputStream = new FileInputStream(file);
				byte[] btImg = readStream(fileInputStream);
				os.write(btImg);
				os.flush();
				if (null != fileInputStream) {
					fileInputStream.close();
				}
				if (null != os) {
					os.close();
				}
			}
		}
	}

	/** 读取管道中的流数据 */
	private byte[] readStream(InputStream inStream) {
		ByteArrayOutputStream bops = new ByteArrayOutputStream();
		int data = -1;
		try {
			while ((data = inStream.read()) != -1) {
				bops.write(data);
			}
			return bops.toByteArray();
		} catch (Exception e) {
			return null;
		} finally {
			if (null != bops) {
				try {
					bops.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/** 修改微商户更新文件上传 **/
	@RequestMapping(TinyMerchantPath.UPDATEFILEUPLOAD) 
	@ResponseBody
	public String updateFileUpload(HttpServletRequest request, HttpServletResponse response) {
		JSONObject object = new JSONObject();
		String custId = request.getParameter("custId");
		StringBuffer doorPhotoPath = new StringBuffer();
		Map<String, String> result = merchantService.tinyMerchantFileUpload(request, custId);
		 for(String key:result.keySet()){
			 if(key.indexOf("doorPhoto")!=-1){    //门头照(doorPhoto)
				doorPhotoPath.append(result.get(key));
				doorPhotoPath.append(";");
			 }
		 }
		result.put("doorPhotoPath", doorPhotoPath.toString());
		object.putAll(result);
		return object.toJSONString();
	}

	/** 修改微商户信息 **/
	@RequestMapping(TinyMerchantPath.UPDATETINYMERCHANTINFO)  
	@ResponseBody
	public String updateTinyMerchantInfo(MerchantVo merchantVo, HttpServletRequest request) {
		logger.info("修改微商户信息");
		JSONObject object = new JSONObject();
		Map<String, String> filePath = new HashMap<String, String>();
		filePath.put("businessType", request.getParameter("businessType"));
		filePath.put("idCardType_1", request.getParameter("idCardType_1"));
		filePath.put("idCardType_2", request.getParameter("idCardType_2"));
		filePath.put("doorPhotoPath", request.getParameter("doorPhotoPath"));
		filePath.put("netWorkPhoto", request.getParameter("netWorkPhoto"));
		filePath.put("openAccount", request.getParameter("openAccount"));
		filePath.put("bankCardPhoto", request.getParameter("bankCardPhoto"));
		
		try {
			if("YES".equals(request.getParameter("isFiling"))){
				merchantVo.setFilingAuditStatus("02");
			}
			merchantService.updateTinyMerchantInfo(merchantVo, filePath);
			object.put("result", "SUCCESS");
			object.put("message", "修改商户信息成功");
		} catch (Exception e) {
			logger.error("修改商户信息未成功", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}

	/** ----------------------------------------商户列表end---------------------------------------- **/
	
	/**
	 * 根据编号获取地区信息
	 * @param request
	 * @return
	 */
	@RequestMapping(TinyMerchantPath.GETAREAINFOBYID)
	@ResponseBody
	public String getAreaInfoById(HttpServletRequest request){
		JSONObject jo = new JSONObject();
		
		String province = 	request.getParameter("province");
		String city		=	request.getParameter("city");
		try {
			List<RegionsBean> provinceList = cityService.selectSpProvince();
			List<RegionsBean> cityList = cityService.getSpCityByProvinceId(province);
			List<RegionsBean> areaList = cityService.getSpAreaByCityId(city);
			
			jo.put("provinceList", provinceList);
			jo.put("cityList", cityList);
			jo.put("areaList", areaList);
			jo.put("result", "SUCCESS");
		} catch (Exception e) {
			jo.put("result", "FAIL");
		}
		
		return jo.toJSONString();
	}
	
	//省份地区信息
	@RequestMapping(TinyMerchantPath.AREAINFO)
	@ResponseBody
	public String getAreaInfo(HttpServletRequest request,
			HttpServletResponse response) {
		JSONObject jo = new JSONObject();
		try {
			if (request.getMethod().equalsIgnoreCase("POST")) {
				String choiceType = request.getParameter("choiceType");

				if ("city".equals(choiceType)) {
					getCityInfo(request, jo);
				} else if ("area".equals(choiceType)) {
					getAreaInfo(request, jo);
				} 
			} 
		}catch (Exception e) {
			jo.put("result", "FAIL");
			jo.put("returnMsg", "网络繁忙，请稍候重试！");
		} 
		
		return jo.toJSONString();
	}
	
	/**
	 * 获取区域信息
	 * @param request
	 * @param map
	 */
	@SuppressWarnings("unchecked")
	private void getAreaInfo(HttpServletRequest request, Map<String, Object> map) {
		String cityId = request.getParameter("city");
		try {
			List<RegionsBean> areaList = cityService.getSpAreaByCityId(cityId);
			map.put("areaList", areaList);
			map.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("查询区域信息异常{}",e);
			throw new IllegalArgumentException("查询区域信息异常！");
		}
		
	}

/**
 * 获取城市信息
 * @param map
 */
@SuppressWarnings("unchecked")
private void getCityInfo(HttpServletRequest request, Map<String, Object> map) {
		String provinceId = request.getParameter("province");
		try {
			List<RegionsBean> cityList = cityService.getSpCityByProvinceId(provinceId);
			map.put("cityList", cityList);
			map.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("查询城市信息异常{}",e);
			throw new IllegalArgumentException("查询城市信息异常！");
		}
	
	}


//省份地区信息
	@RequestMapping(TinyMerchantPath.GETMERCHANTIMG)
	@ResponseBody
	public String getMerchantImg(HttpServletRequest request,HttpServletResponse response,String custId) {
		JSONObject jo = new JSONObject();
		try {
			
			String scanPath =audingService.findScanPath(custId, "08");//查询门头照的个数
			List<String> doorPhoto=new ArrayList<>();
			String path[]=scanPath.split(";");
			for (String string : path)
				if(string.indexOf("doorPhoto")!=-1){//门头照
					doorPhoto.add(string);
					
			}
		jo.put("doorPhoto", doorPhoto);
		jo.put("result", "SUCCESS");
		}catch (Exception e) {
			jo.put("result", "FAIL");
			jo.put("returnMsg", "网络繁忙，请稍候重试！");
		} 
		
		return jo.toJSONString();
	}

}
