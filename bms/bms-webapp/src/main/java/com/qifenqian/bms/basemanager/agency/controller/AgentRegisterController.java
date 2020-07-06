package com.qifenqian.bms.basemanager.agency.controller;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.agency.service.AgentRegisterService;
import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.mapper.BankMapper;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.rule.mapper.RuleMapper;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.basemanager.utils.YouTuUtils;

@Controller
@RequestMapping(AgentRegisterPath.BASE)
public class AgentRegisterController {

	private Logger logger = LoggerFactory.getLogger(AgentRegisterController.class);

	@Autowired
	private BankMapper bankMapper;

	@Autowired
	private RuleMapper ruleMapper;

	@Autowired
	private AgentRegisterService agentRegisterService;

	@Autowired
	private TradeBillService tradeBillService;
	
	@Autowired
	private TdCustInfoService tdCustInfoService;
	
	@Autowired
	private MerchantService merchantService;
	
	/** 跳转到注册页面 */
	@RequestMapping(AgentRegisterPath.REGISTPAGE)
	public ModelAndView toRegistPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView(AgentRegisterPath.BASE + AgentRegisterPath.REGISTPAGE);
		Bank bank = new Bank();
		mv.addObject("banklist", bankMapper.selectBanks(bank));
		return mv;
	}

	/** 代理商注册 */
	@SuppressWarnings("unchecked")
	@RequestMapping(AgentRegisterPath.REGISTER)
	@ResponseBody
	public String register(HttpServletRequest request, Merchant merchant) {
		logger.info("================= 代理商注册开始 =================");
		JSONObject object = new JSONObject();
		try {
			String email = request.getParameter("email"); 	// 邮箱
			//判断邮箱是否已被注册
			TdLoginUserInfo loginInfo = agentRegisterService.selectLoginUserInfoByEmail(email, "","agent");
			if (null != loginInfo) {
				object.put("result", "FAIL");
				object.put("message", "邮箱已被注册，请重新输入");
				return object.toJSONString();
			} 
			
			String mobile = merchant.getRepresentativeMobile(); 	// 邮箱
			//判断手机号是否已被注册
			TdLoginUserInfo loginInfo1 = agentRegisterService.selectLoginUserInfoByMobile(mobile,"agent");
			if (null != loginInfo1) {
				object.put("result", "FAIL");
				object.put("message", "手机号已被注册，请重新输入");
				return object.toJSONString();
			} 
			
			merchant.setRoleId("agent");
			
		/*	int num = agentRegisterService.selectLoginUserInfoByCardId(merchant.getCertifyNo(), merchant.getRoleId());
			if (num > 0 ) {
				object.put("result", "FAIL");
				object.put("message", "身份证已被注册，请重新输入");
				return object.toJSONString();
			} */
			if(merchant.getBusinessLicense()==null||"".equals(merchant.getBusinessLicense())){
				
			}else{
				//判断营业执照是否被注册
				TdCustInfo cust =  agentRegisterService.selectCustInfoByLicense(merchant.getBusinessLicense(),"agent");
				if (null != cust ) {
					object.put("result", "FAIL");
					object.put("message", "营业执照已被注册，请重新输入");
					return object.toJSONString();
				} 
			}
			
			if("1".equals(merchant.getCustType())){
				merchant.setMerchantCode( "P" + GenSN.getRandomNum(15));
			}else{
				merchant.setMerchantCode("M" + GenSN.getRandomNum(15));
			}
			//merchant.setMerchantCode("M"+GenSN.getRandomNum(15));//生成商户编号：M***************
			Map<String, String> custScanMap = (Map<String, String>) request.getSession().getAttribute("custScanMap");
			merchant.setFcontactunitNumber("1000073");//往来单位编号
			agentRegisterService.saveAgentRegist(email, merchant.getCustId(),merchant,custScanMap);
			object.put("result", "SUCCESS");
			object.put("message", "注册成功");
			logger.info("================= 代理商注册成功 =================");
		} catch (Exception e) {
			logger.error("注册失败", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}

	/** 代理商注册 */
	@SuppressWarnings("unchecked")
	@RequestMapping(AgentRegisterPath.REGISTERENTER)
	@ResponseBody
	public String registerEnter(HttpServletRequest request, Merchant merchant) {
		logger.info("================= 代理商注册开始 =================");
		JSONObject object = new JSONObject();
		try {
			String email = request.getParameter("email"); 	// 邮箱
			//判断邮箱是否已被注册
			TdLoginUserInfo loginInfo = agentRegisterService.selectLoginUserInfoByEmail(email, "","agent");
			if (null != loginInfo) {
				object.put("result", "FAIL");
				object.put("message", "邮箱已被注册，请重新输入");
				return object.toJSONString();
			}

			String mobile = merchant.getRepresentativeMobile(); 	// 邮箱
			//判断手机号是否已被注册
			TdLoginUserInfo loginInfo1 = agentRegisterService.selectLoginUserInfoByMobile(mobile,"agent");
			if (null != loginInfo1) {
				object.put("result", "FAIL");
				object.put("message", "手机号已被注册，请重新输入");
				return object.toJSONString();
			}

			merchant.setRoleId("agent");

		/*	int num = agentRegisterService.selectLoginUserInfoByCardId(merchant.getCertifyNo(), merchant.getRoleId());
			if (num > 0 ) {
				object.put("result", "FAIL");
				object.put("message", "身份证已被注册，请重新输入");
				return object.toJSONString();
			} */
			if(merchant.getBusinessLicense()==null||"".equals(merchant.getBusinessLicense())){

			}else{
				//判断营业执照是否被注册
				TdCustInfo cust =  agentRegisterService.selectCustInfoByLicense(merchant.getBusinessLicense(),"agent");
				if (null != cust ) {
					object.put("result", "FAIL");
					object.put("message", "营业执照已被注册，请重新输入");
					return object.toJSONString();
				}
			}

			if("1".equals(merchant.getCustType())){
				merchant.setMerchantCode( "C" + GenSN.getRandomNum(15));
			}else{
				merchant.setMerchantCode( "P" + GenSN.getRandomNum(15));
			}
			//merchant.setMerchantCode("M"+GenSN.getRandomNum(15));//生成商户编号：M***************
			Map<String, String> custScanMap = (Map<String, String>) request.getSession().getAttribute("custScanMap");
			merchant.setFcontactunitNumber("1000073");//往来单位编号
			agentRegisterService.saveAgentRegist(email, merchant.getCustId(),merchant,custScanMap);
			object.put("result", "SUCCESS");
			object.put("message", "注册成功");
			logger.info("================= 代理商注册成功 =================");
		} catch (Exception e) {
			logger.error("注册失败", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}

	/** 文件上传 */
	@RequestMapping(AgentRegisterPath.FILEUPLOAD)
	@ResponseBody
	public String fileUpload(HttpServletRequest request, HttpServletResponse response) {
		JSONObject object = new JSONObject();
		try {
			String picDataString = request.getParameter("imgData");
			String custId = GenSN.getSN();
			Map<String,String> result =merchantService.compressUpload(request,custId);
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
	@RequestMapping(AgentRegisterPath.VALIDATEEMAIL)
	@ResponseBody
	public String validateEmail(String email, String custId) {
		logger.info("********************校验邮箱********************");
		JSONObject object = new JSONObject();
		try {
			TdLoginUserInfo loginInfo = agentRegisterService.selectLoginUserInfoByEmail(email, custId,"agent");
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
	
	/** ----------------------------------------校验相关方法start---------------------------------------- **/
	/** 校验身份证是否已经存在 */
	@RequestMapping(AgentRegisterPath.VALIDATECARDID)
	@ResponseBody
	public String validateCardID(String cardId, String roleId) {
		logger.info("********************校验身份证********************");
		JSONObject object = new JSONObject();
		try {
			
			int num = agentRegisterService.selectLoginUserInfoByCardId(cardId, "agent");
			if (num > 0 ) {
				object.put("result", "FAIL");
				
			} else {
				object.put("result", "SUCCESS");
			}
		} catch (Exception e) {
			logger.error("邮箱匹配出现问题" + e);
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}
	
	/** ----------------------------------------校验相关方法start---------------------------------------- **/
	/** 校验手机号是否已经存在 */
	@RequestMapping(AgentRegisterPath.VALIDATEMOBILE)
	@ResponseBody
	public String validateMobile(String mobile) {
		logger.info("********************校验身份证********************");
		JSONObject object = new JSONObject();
		try {
			TdLoginUserInfo loginInfo = agentRegisterService.selectLoginUserInfoByMobile(mobile,"agent");
			
			if (loginInfo != null) {
				object.put("result", "FAIL");
			} else {
				object.put("result", "SUCCESS");
			}
		} catch (Exception e) {
			logger.error("手机号匹配出问题" + e);
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}
	
	
	/** ----------------------------------------校验相关方法start---------------------------------------- **/
	/** 校验营业执照是否已经存在 */
	@RequestMapping(AgentRegisterPath.VALIDATELICENSE)
	@ResponseBody
	public String validateLicense(String License, String CustId) {
		logger.info("********************校验营业执照********************");
		JSONObject object = new JSONObject();
		try {
			
			TdCustInfo cust =  agentRegisterService.selectCustInfoByLicense(License,"agent");
			if (null == cust ) {
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
	
	
	
	/** ----------------------------------------校验相关方法end---------------------------------------- **/
	/** Get请求编码转换 **/
	private String getEncoding(String param) throws UnsupportedEncodingException {
		// 用IOS回退
		byte[] bytes = param.getBytes("iso-8859-1");
		// 用UTF-8重新编码
		param = new String(bytes, "utf-8");
		return param;
	}

	/** 预览代理商图片信息 **/
	@RequestMapping(AgentRegisterPath.PREVIEWAGENTIMAGE)
	@ResponseBody
	public void findTinyMerchantImageById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String custId = request.getParameter("custId");
		String certifyType = request.getParameter("certifyType");
		String front = request.getParameter("front");
		String imagePath = agentRegisterService.findTinyMerchantImagePathById(custId, certifyType);
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
	
	/**
	 * 优图
	 */
	/** 预览代理商图片信息 **/
	@RequestMapping(AgentRegisterPath.YOUTU)
	@ResponseBody
	public String youTu(HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("********************获取图片内容********************");
		JSONObject object = new JSONObject();
		try {
			request.setCharacterEncoding("UTF-8");
			StringBuilder sb = new StringBuilder();
			try(BufferedReader reader = request.getReader()) {
			char[] buff = new char[1024];
			int len;
			 while((len = reader.read(buff)) != -1) {
			    sb.append(buff,0, len);
			    }
			}catch (IOException e) {
			     e.printStackTrace();
			}
			
			JSONObject jobject = JSONObject.parseObject(sb.toString());
			String str = jobject.getString("str");
			String flag = jobject.getString("flag");
			YouTuUtils youto = new YouTuUtils();
			object = youto.youTu(str, flag);
			
		} catch (Exception e) {
			logger.error("解析图片出现问题" + e);
			object.put("result", "FAIL");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}
	

}
