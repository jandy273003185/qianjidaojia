package com.qifenqian.bms.basemanager.agency.controller;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import com.qifenqian.bms.basemanager.agency.bean.AgencyExport;
import com.qifenqian.bms.basemanager.agency.service.AgencyService;
import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.mapper.BankMapper;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

/***
 * 代理商列表
 * 
 * @param queryBean
 * @return
 */
@Controller
@RequestMapping(AgencyPath.BASE)
public class AgencyController {
	
	private Logger logger = LoggerFactory.getLogger(AgencyController.class);
	@Autowired
	private TdCustInfoService tdCustInfoService;
	@Autowired
	private AgencyService agencyService;
	@Autowired
	private BankMapper bankMapper;
	@Autowired
	private TradeBillService tradeBillService;
	@Autowired
	private MerchantService merchantService;

	@RequestMapping(AgencyPath.LIST)
	public ModelAndView list(MerchantVo merchantVo,HttpServletRequest request){
		Bank bank = new Bank();
		ModelAndView mv = new ModelAndView(AgencyPath.BASE + AgencyPath.LIST);
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
			list = agencyService.selectAgencys(merchantVo);
		}else{
			merchantVo.setUserId(userId);
			merchantVo.setUserName(WebUtils.getUserInfo().getUserName());
			list = agencyService.myAgencyList(merchantVo);
		}
		
		mv.addObject("isFirst","No");
		mv.addObject("banklist", bankMapper.selectBanks(bank));
		mv.addObject("agencyList", JSONObject.toJSON(list));
		mv.addObject("queryBean", merchantVo);
		return mv;
	}
	
	/** 导出代理商列表 **/
	@RequestMapping(AgencyPath.EXPORTAGENCYINFO)
	public void exportExcel(MerchantVo merchantVo, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出代理商列表信息");
		try {
			/** 手工处理get方式请求中文乱码问题 **/
//			String custName = getEncoding(merchantVo.getCustName());
//			String agentName = getEncoding(merchantVo.getAgentName());
//			merchantVo.setCustName(custName);
//			merchantVo.setAgentName(agentName);
			/** 根据条件查询需要导出的数据 **/
			List<AgencyExport> exportAgencysInfo = agencyService.exportAgencysInfo(merchantVo);
			/** 设置excel头 **/
			String[] excelHeaders = {"代理商编号" , "账号" , "商户名称" ,//
					"代理商费率" , "开户名" , "开户账号", "开户行", "客户经理" ,//
					"协议状态" , "注册时间" , "商户状态" };
			/** 设置文件名 **/
			String fileName = DatetimeUtils.dateSecond() + "_代理商列表信息.xls";
			/**  **/
			Map<String, String> exportExcel = tradeBillService.exportExcel(exportAgencysInfo, excelHeaders, fileName, "代理商列表", request);
			/**  **/
			DownLoadUtil.downLoadFile(exportExcel.get("filePath"), response, exportExcel.get("fileName"), "xls");
			logger.info("导出excel代理商列表成功");
		} catch (Exception e) {
			logger.error("导出excel代理商列表异常", e);
			throw new RuntimeException(e);
		}
	}
	
	/** 修改代理商更新文件上传 
	 * @throws IOException **/
	@RequestMapping(AgencyPath.UPDATEFILEUPLOAD) 
	@ResponseBody
	public String updateFileUpload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		JSONObject object = new JSONObject();
		String custId = request.getParameter("custId");
		Map<String, String> result = merchantService.compressUpload(request, custId);
		object.putAll(result);
		return object.toJSONString();
	}
	
	/** 修改代理商信息 **/
	@RequestMapping(AgencyPath.UPDATEAGENCYINFO)  
	@ResponseBody
	public String updateAgencyInfo(MerchantVo merchantVo, HttpServletRequest request) {
		logger.info("修改代理商信息");
		JSONObject object = new JSONObject();
		Map<String, String> filePath = new HashMap<String, String>();
		filePath.put("businessType", request.getParameter("businessType"));
		filePath.put("idCardType_1", request.getParameter("idCardType_1"));
		filePath.put("idCardType_2", request.getParameter("idCardType_2"));
		filePath.put("settlEmentCard", request.getParameter("settlEmentCard"));
		try {
			agencyService.updateAgencyInfo(merchantVo, filePath);
			object.put("result", "SUCCESS");
			object.put("message", "修改商户信息成功");
		} catch (Exception e) {
			logger.error("修改商户信息未成功", e);
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}
	
	/** 预览代理商图片信息 **/
	@RequestMapping(AgencyPath.PREVIEWAGENCYIMAGE)
	@ResponseBody
	public void findAgencyImageById(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String custId = request.getParameter("custId");
		String certifyType = request.getParameter("certifyType");
		String front = request.getParameter("front");
		String imagePath = agencyService.findAgencyImagePathById(custId, certifyType);
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
	
	/** Get请求编码转换 **/
	private String getEncoding(String param) throws UnsupportedEncodingException {
		// 用IOS回退
		byte[] bytes = param.getBytes("iso-8859-1");
		// 用UTF-8重新编码
		param = new String(bytes, "utf-8");
		return param;
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
}
