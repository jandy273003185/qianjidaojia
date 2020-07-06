package com.qifenqian.bms.upgrade.product;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.ServletException;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.merchant.auding.service.audingService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.platform.utils.HttpUtil;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.qifenqian.bms.upgrade.bean.ProductAuding;
import com.qifenqian.bms.upgrade.bean.ProductExport;
import com.qifenqian.bms.upgrade.bean.TdAuditRecodeInfo;
import com.qifenqian.bms.upgrade.service.ProductService;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageBean;
import com.sevenpay.plugin.message.bean.MessageColumnValues;

/**
 * 
 * @author 微商户审核控制器 2017年6月23日 上午11:28:22
 */
@Controller
@RequestMapping(ProductAudingPath.BASE)
public class ProductAudingCtroller {
	private Logger logger = LoggerFactory.getLogger(ProductAudingCtroller.class);
	ExecutorService smsExecutors = Executors.newFixedThreadPool(10);

	@Autowired
	private ProductService productService;
	@Autowired
	private audingService audingService;
	@Autowired
	private TradeBillService tradeBillService;
	@Autowired
	private IPlugin iPlugin;
	@Autowired
	private CommonService commonService;

	/** 产品列表 
	 * @throws ParseException */
	@RequestMapping(ProductAudingPath.PRODUCTAUDITLIST)
	public ModelAndView wechatAuditList(ProductAuding product) throws ParseException{
		//获取列表
		ModelAndView mv=new ModelAndView(ProductAudingPath.BASE + ProductAudingPath.PRODUCTAUDITLIST);
		List<ProductAuding> productList = productService.getProductAudingList(product);
		mv.addObject("productList", JSONObject.toJSON(productList));
		mv.addObject("product", product);
		return mv;

	}
	
	// 获取单个微商对象
	@RequestMapping(ProductAudingPath.SELDOORSCAN)
	@ResponseBody
	public String selDoorScan(String merchantCode) {
		JSONObject ob = new JSONObject();
		logger.info("开始查询商户{}信息", merchantCode);
		
		try {
			// 查询商户门头照路径
			String scanPath = productService.findScanPath(merchantCode, "08");// 查询门头照的个数
			//查询低价费率
			List<ProductAuding> list = productService.getProductByMerchantCode(merchantCode);
			for(int i = 0 ; i < list.size() ; i++){
				if("SCAN_PAY".equals(list.get(i).getProductCode())){
					//扫码支付
					ob.put("SCAN_PAY", list.get(i).getSignRate());
				}else if("APP_PAY".equals(list.get(i).getProductCode())){
					//APP支付
					ob.put("APP_PAY", list.get(i).getSignRate());
				}else if("OA_PAY".equals(list.get(i).getProductCode())){
					//公众号支付
					ob.put("OA_PAY", list.get(i).getSignRate());
				}else if("H5_PAY".equals(list.get(i).getProductCode())){
					//H5支付
					ob.put("H5_PAY", list.get(i).getSignRate());
				}else if("MINI_PAY".equals(list.get(i).getProductCode())){
					//小程序支付
					ob.put("MINI_PAY", list.get(i).getSignRate());
				}else if("BLUESEA_PAY".equals(list.get(i).getProductCode())){
					//蓝海计划
					ob.put("BLUESEA_PAY", list.get(i).getSignRate());
				}
			}
			
			//查询小票路径
			String receiptsScanPath = productService.findScanPath(merchantCode, "17");
			String invoiceScanPath = productService.findScanPath(merchantCode, "12");
			String publicAccountScanPath = productService.findScanPath(merchantCode, "16");
			String h5DomainNameScanPath = productService.findScanPath(merchantCode, "13");
			String h5DomainCertificateScanPath = productService.findScanPath(merchantCode, "14");
			String h5TextSnippetsScanPath = productService.findScanPath(merchantCode, "15");
			String checkstandPath = productService.findScanPath(merchantCode, "18");
			String storeEnviromentPath = productService.findScanPath(merchantCode, "19");
			String platformPath = productService.findScanPath(merchantCode, "20");
			ob.put("scanPath", scanPath);
			ob.put("receiptsScanPath", receiptsScanPath);
			ob.put("invoiceScanPath", invoiceScanPath);
			ob.put("publicAccountScanPath", publicAccountScanPath);
			ob.put("h5DomainNameScanPath", h5DomainNameScanPath);
			ob.put("h5DomainCertificateScanPath", h5DomainCertificateScanPath);
			ob.put("h5TextSnippetsScanPath", h5TextSnippetsScanPath);
			ob.put("checkstandPath", checkstandPath);
			ob.put("storeEnviromentPath", storeEnviromentPath);
			ob.put("platformPath", platformPath);
			ob.put("result", "SUCCESS");
		} catch (Exception e) {
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
			logger.error("门头照信息查询异常", e);
		}
		return ob.toJSONString();

	}

	// 读取服务器图片
	@RequestMapping(ProductAudingPath.IMAGE)
	protected void image(HttpServletRequest request, HttpServletResponse response,String scanPath)
			throws ServletException, IOException {
		String custId = request.getParameter("custId");
		String certifyType = request.getParameter("certifyType");
		String front = request.getParameter("front");
		if(scanPath==null){
			scanPath = audingService.findScanPath(custId, certifyType);
		}
		
		if (scanPath != null) {
			String path[] = null;
			if (certifyType.equals(Constant.CERTIFY_TYPE_MERCHANT_IDCARD)) {
				if (!StringUtils.isEmpty(front)) {
					path = scanPath.split(";");
					if (front.equals("0")) {//正面
						scanPath = path[0];
					} else {//反面
						scanPath = path[1];
					}
				}
			}
			if(certifyType.equals(Constant.CERTIFY_TYPE_BUSINESS)) {
				scanPath = scanPath.split(";")[0];
			}
			
			OutputStream os = response.getOutputStream();
			File file = new File(scanPath);
			if (file.exists()) {
				FileInputStream fips = new FileInputStream(file);
				byte[] btImg = readStream(fips);
				os.write(btImg);
				os.flush();
				if (null != fips) {
					fips.close();
				}
				if (null != os) {
					os.close();
				}
			}

		}

	}

	/**
	 * 读取管道中的流数据
	 */
	public byte[] readStream(InputStream inStream) {
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
	 * 导出报表
	 */

	@RequestMapping(ProductAudingPath.EXPORTMERCHANPRODUCT)
	public void backExportExcel(ProductAuding product, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出后台产品审核列表信息");
		try {
			List<ProductExport> excels = productService.getProductAudingListExpect(product);
			String[] headers = { "商户编号", "商户名称", "产品类型", "期望费率", "提交时间", "注册方式"};
			String fileName = DatetimeUtils.dateSecond() + "_产品审核列表信息.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "产品审核列表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel产品审核列表成功");
		} catch (Exception e) {
			logger.error("导出excel产品审核列表异常", e);
			throw new RuntimeException(e);
		}
	}
	
	// 产品审核不通过
	@RequestMapping(ProductAudingPath.AUDITNOTPASS)
	@ResponseBody
	public String notPass(ProductAuding product,String message) {
		JSONObject ob = new JSONObject();
		logger.info("产品审核通不过{}", message);
		
		try {
			product.setSignStatus("04");
			// 修改产品信息表为审核不通过
			productService.updateProduct(product);
			
			TdAuditRecodeInfo audit = new TdAuditRecodeInfo();
			//String id = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())+RandomStringUtils.randomNumeric(5);
			audit.setId(GenSN.getSN());
			audit.setAuditInfo(message);
			audit.setAuditTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			if("SCAN_PAY".equals(product.getProductCode())){
				audit.setAuditType("02");
	     	}else if("APP_PAY".equals(product.getProductCode())){
	     		audit.setAuditType("04");
	     	}else if("OA_PAY".equals(product.getProductCode())){
	     		audit.setAuditType("05");
	     	}else if("H5_PAY".equals(product.getProductCode())){
	     		audit.setAuditType("03");
	     	}else if("MINI_PAY".equals(product.getProductCode())){
	     		audit.setAuditType("06");
	     	}else if("BLUESEA_PAY".equals(product.getProductCode())){
	     		audit.setAuditType("07");
	     	}
			audit.setMerchantCode(product.getMerchantCode());
			audit.setAuditStatus("99");
			audit.setUserId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			//添加审核信息表为审核不通过
			productService.insertAuditRecode(audit);
			
			//查询商户信息
			TdCustInfo cust = productService.selCustInfoByMerchantCode(product.getMerchantCode());
			//发送短信
			final IPlugin plugin = commonService.getIPlugin();
			final MessageBean messageBean = new MessageBean();
			String flag = iPlugin.findDictByPath("IS_TEST");
			
			String url = iPlugin.findDictByPath("AGENT_MERCHANT_REGISTER_IP");
			String[] tos = null;
			
			if ("0".equals(flag)) {// 生产
				if(cust.getMobile() != null && !"".equals(cust.getMobile())) {
					tos = new String[] { cust.getMobile() };
					messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
				}else {
					tos = new String[] { cust.getEmail() };
					messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
				}
				
				//String shortUrl = this.getShortUrl(allUrl);
				
				if("SCAN_PAY".equals(product.getProductCode())){
					messageBean.setContent("【七分钱支付】您的扫码支付未能通过审核，原因是：" + message
							+ "，请前往商户网站修改产品信息，如有任何问题，请拨打400-633-0707。");
		     	}else if("APP_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的APP支付未能通过审核，原因是：" + message
							+ "，请前往商户网站修改产品信息，如有任何问题，请拨打400-633-0707。");
		     	}else if("OA_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的公众号支付未能通过审核，原因是：" + message
							+ "，请前往商户网站修改产品信息，如有任何问题，请拨打400-633-0707。");
		     	}else if("H5_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的H5支付未能通过审核，原因是：" + message
							+ "，请前往商户网站修改产品信息，如有任何问题，请拨打400-633-0707。");
		     	}else if("MINI_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的小程序未能通过审核，原因是：" + message
							+ "，请前往商户网站修改产品信息，如有任何问题，请拨打400-633-0707。");
		     	}else if("BLUESEA_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的蓝海计划未能通过审核，原因是：" + message
							+ "，请前往商户网站修改产品信息，如有任何问题，请拨打400-633-0707。");
		     	}
				
			} else {
				if(cust.getMobile() != null && !"".equals(cust.getMobile())) {
					tos = new String[] { cust.getMobile() };
					messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
				}else {
					tos = new String[] { cust.getEmail() };
					messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
				}				
				
				if("SCAN_PAY".equals(product.getProductCode())){
					messageBean.setContent("【七分钱支付】您的扫码支付未能通过审核，原因是：" + message
							+ "，请前往商户网站修改产品信息，如有任何问题，请拨打400-633-0707。");
		     	}else if("APP_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的APP支付未能通过审核，原因是：" + message
							+ "，请前往商户网站修改产品信息，如有任何问题，请拨打400-633-0707。");
		     	}else if("OA_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的公众号支付未能通过审核，原因是：" + message
							+ "，请前往商户网站修改产品信息，如有任何问题，请拨打400-633-0707。");
		     	}else if("H5_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的H5支付未能通过审核，原因是：" + message
							+ "，请前往商户网站修改产品信息，如有任何问题，请拨打400-633-0707。");
		     	}else if("MINI_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的小程序未能通过审核，原因是：" + message
							+ "，请前往商户网站修改产品信息，如有任何问题，请拨打400-633-0707。");
		     	}else if("BLUESEA_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的蓝海计划未能通过审核，原因是：" + message
							+ "，请前往商户网站修改产品信息，如有任何问题，请拨打400-633-0707。");
		     	}
			}

			messageBean.setSubject("【七分钱支付】产品审核未通过");// 标题
			messageBean.setTos(tos);
			messageBean.setBusType(MessageColumnValues.busType.REGISTER_VERIFY);
			
			smsExecutors.execute(new Runnable() {
				@Override
				public void run() {
					logger.debug("发送邮件");
					if (messageBean.getMsgType().equals(MessageColumnValues.MsgType.SMS)) {
						plugin.sendMessage(MessageColumnValues.MsgType.SMS, messageBean); // 电话SMS
					} else {
						plugin.sendMessage(MessageColumnValues.MsgType.EMAIL, messageBean); // 邮件EMAIL
					}
				}
			});
			ob.put("result", "SUCCESS");
		} catch (Exception e) {
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
			logger.error("产品审核异常", e);
		}
		return ob.toJSONString();

	}
	
	// 产品审核通过
	@RequestMapping(ProductAudingPath.AUDITPASS)
	@ResponseBody
	public String pass(ProductAuding product) {
		JSONObject ob = new JSONObject();
		logger.info("产品审核通通过{}", product);
		
		try {
			product.setSignStatus("00");
			// 修改产品信息表为审核通过
			productService.updateProduct(product);
			
			TdAuditRecodeInfo audit = new TdAuditRecodeInfo();
			//String id = new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())+RandomStringUtils.randomNumeric(5);
			audit.setId(GenSN.getSN());
			audit.setAuditInfo("审核通过");
			audit.setAuditTime(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
			if("SCAN_PAY".equals(product.getProductCode())){
				audit.setAuditType("02");
	     	}else if("APP_PAY".equals(product.getProductCode())){
	     		audit.setAuditType("04");
	     	}else if("OA_PAY".equals(product.getProductCode())){
	     		audit.setAuditType("05");
	     	}else if("H5_PAY".equals(product.getProductCode())){
	     		audit.setAuditType("03");
	     	}else if("MINI_PAY".equals(product.getProductCode())){
	     		audit.setAuditType("06");
	     	}else if("BLUESEA_PAY".equals(product.getProductCode())){
	     		audit.setAuditType("07");
	     	}
			audit.setMerchantCode(product.getMerchantCode());
			audit.setAuditStatus("00");
			audit.setUserId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			//添加审核信息表为审核通过
			productService.insertAuditRecode(audit);
			
			//查询商户信息
			TdCustInfo cust = productService.selCustInfoByMerchantCode(product.getMerchantCode());
			//发送短信
			final IPlugin plugin = commonService.getIPlugin();
			final MessageBean messageBean = new MessageBean();
			String flag = iPlugin.findDictByPath("IS_TEST");
			
			String url = iPlugin.findDictByPath("AGENT_MERCHANT_REGISTER_IP");
			String[] tos = null;
			
			if ("0".equals(flag)) {// 生产
				if(cust.getMobile() != null && !"".equals(cust.getMobile())) {
					tos = new String[] { cust.getMobile() };
					messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
				}else {
					tos = new String[] { cust.getEmail() };
					messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
				}
				//String shortUrl = this.getShortUrl(allUrl);
				messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
				if("SCAN_PAY".equals(product.getProductCode())){
					messageBean.setContent("【七分钱支付】您的扫码支付审核已通过,如有任何问题，请拨打400-633-0707。");
		     	}else if("APP_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的APP支付审核已通过,如有任何问题，请拨打400-633-0707。");
		     	}else if("OA_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的公众号支付审核已通过,如有任何问题，请拨打400-633-0707。");
		     	}else if("H5_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的H5支付审核已通过,如有任何问题，请拨打400-633-0707。");
		     	}else if("MINI_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的小程序审核已通过,如有任何问题，请拨打400-633-0707。");
		     	}else if("BLUESEA_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的蓝海计划审核已通过,如有任何问题，请拨打400-633-0707。");
		     	}
				
			} else {
				if(cust.getMobile() != null && !"".equals(cust.getMobile())) {
					tos = new String[] { cust.getMobile() };
					messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
				}else {
					tos = new String[] { cust.getEmail() };
					messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
				}
				//String shortUrl = this.getShortUrl(allUrl);
				messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
				if("SCAN_PAY".equals(product.getProductCode())){
					messageBean.setContent("【七分钱支付】您的扫码支付审核已通过,如有任何问题，请拨打400-633-0707。");
		     	}else if("APP_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的APP支付审核已通过,如有任何问题，请拨打400-633-0707。");
		     	}else if("OA_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的公众号支付审核已通过,如有任何问题，请拨打400-633-0707。");
		     	}else if("H5_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的H5支付审核已通过,如有任何问题，请拨打400-633-0707。");
		     	}else if("MINI_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的小程序审核已通过,如有任何问题，请拨打400-633-0707。");
		     	}else if("BLUESEA_PAY".equals(product.getProductCode())){
		     		messageBean.setContent("【七分钱支付】您的蓝海计划审核已通过,如有任何问题，请拨打400-633-0707。");
		     	}
			}

			messageBean.setSubject("【七分钱支付】产品审核通过");// 标题
			messageBean.setTos(tos);
			messageBean.setBusType(MessageColumnValues.busType.REGISTER_VERIFY);
			
			smsExecutors.execute(new Runnable() {
				@Override
				public void run() {
					logger.debug("发送邮件");
					if (messageBean.getMsgType().equals(MessageColumnValues.MsgType.SMS)) {
						plugin.sendMessage(MessageColumnValues.MsgType.SMS, messageBean); // 电话SMS
					} else {
						plugin.sendMessage(MessageColumnValues.MsgType.EMAIL, messageBean); // 邮件EMAIL
					}
				}
			});
			ob.put("result", "SUCCESS");
		} catch (Exception e) {
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
			logger.error("产品审核异常", e);
		}
		return ob.toJSONString();

	}
	
	
	/**
	 * 调用新浪短连接接口
	 * 
	 * @param longUrl
	 * @return
	 */
	public String getShortUrl(String longUrl) {
		String codeUrl = "";
		try {
			codeUrl = URLEncoder.encode(longUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String requestUrl = "http://api.t.sina.com.cn/short_url/shorten.json?source=2815391962&url_long=" + codeUrl;
		String url = HttpUtil.invoker(requestUrl);
		JSONArray jsonArray = JSONObject.parseArray(url);
		JSONObject jsonObject = jsonArray.getJSONObject(0);
		String shortUrl = (String) jsonObject.get("url_short");
		shortUrl = shortUrl.replaceAll("(http://|https://)", "");
		return shortUrl;
	}
	
	
	
}
