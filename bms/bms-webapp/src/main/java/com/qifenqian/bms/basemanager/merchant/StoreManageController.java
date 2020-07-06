package com.qifenqian.bms.basemanager.merchant;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.jaood.qrcode.geneerate_qrcode.QRCode;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.StoreManage;
import com.qifenqian.bms.basemanager.merchant.mapper.CustScanMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.merchant.service.StoreManageService;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.platform.common.utils.DateUtils;

@Controller
@RequestMapping(StoreManagePath.BASE)
public class StoreManageController {

	private Logger logger = LoggerFactory.getLogger(StoreManageController.class);

	@Autowired
	private StoreManageService storeManageService;

	@Autowired
	private MerchantMapper merchantMapper;

	@Autowired
	private TdCustInfoMapper tdCustInfoMapper;

	@Autowired
	private CustScanMapper custScanMapper;
	
	@Value("${CERTIFY_FILE_SAVE_PATH}")
	private String CERTIFY_FILE_SAVE_PATH;

	/** 门店管理 */
	@RequestMapping(StoreManagePath.LIST)
	public ModelAndView list(StoreManage queryBean) {
		ModelAndView mv = new ModelAndView(StoreManagePath.BASE + StoreManagePath.LIST);

		mv.addObject("storeManageInfoList", JSONObject.toJSON(storeManageService.getStoreList(queryBean)));
		List<Merchant> merchantList = merchantMapper.selectMerchant();
		mv.addObject("queryBean", queryBean);
		mv.addObject("merchantList", merchantList);
		return mv;

	}

	// 添加门店
	@RequestMapping(StoreManagePath.ADD)
	@ResponseBody
	public String add(StoreManage storeManage) {
		logger.info("增加门店");
		JSONObject jsonObject = new JSONObject();
		storeManage.setShopNo("MD"+DateUtils.getDateTimeStrNo());
		if (storeManageService.repeats(storeManage) > 0) {
			jsonObject.put("result", "fail");
			jsonObject.put("message", "商户门店编号在系统中已存在");
			return jsonObject.toJSONString();
		} 
		try {
			storeManageService.insert(storeManage);
			jsonObject.put("result", "success");
		} catch (Exception e) {
			logger.error("增加门店：", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	// 编辑门店
	@RequestMapping(StoreManagePath.EDIT)
	@ResponseBody
	public String update(StoreManage storeManage) {
		logger.info("编辑门店");
		JSONObject jsonObject = new JSONObject();
		try {
			logger.info("++++++++++++++" + JSONObject.toJSONString(storeManage));
			storeManageService.update(storeManage);
			jsonObject.put("result", "success");
		} catch (Exception e) {
			logger.error("编辑门店：", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	// 删除门店
	@RequestMapping(StoreManagePath.DELETE)
	@ResponseBody
	public String delete(String shopId) {
		logger.info("删除门店");
		JSONObject jsonObject = new JSONObject();
		try {
			logger.info("++++++++++++++" + JSONObject.toJSONString(shopId));
			StoreManage storeManage = new StoreManage();
			storeManage.setShopId(shopId);
			storeManageService.delete(storeManage);
			jsonObject.put("result", "success");
		} catch (Exception e) {
			logger.error("删除门店：", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	@RequestMapping(StoreManagePath.VALIDATE)
	@ResponseBody
	public String verify(StoreManage storeManage) {
		logger.info("校验商户是否存在");
		JSONObject jsonObject = new JSONObject();
		try {
			if (storeManageService.exists(storeManage) < 1) {
				jsonObject.put("result", "fail");
				jsonObject.put("message", "商户在系统中不存在");
			} else {
				jsonObject.put("result", "success");
				jsonObject.put("message", "商户在系统中存在");
			}

		} catch (Exception e) {
			logger.error("商家账号：", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	@RequestMapping(StoreManagePath.REPEAT)
	@ResponseBody
	public String repeat(StoreManage storeManage) {
		logger.info("校验商户门店编号是否重复");
		JSONObject jsonObject = new JSONObject();
		
		try {
			if (storeManageService.repeats(storeManage) > 0) {
				jsonObject.put("result", "fail");
				jsonObject.put("message", "商户门店编号在系统中已存在");
			} else {
				jsonObject.put("result", "success");
				jsonObject.put("message", "商户在系统中存在");
			}

		} catch (Exception e) {
			logger.error("商家账号：", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

//	private static IPlugin iPlugin = (IPlugin)SpringBeans.getBean("pluginInvokerProxy");

	@RequestMapping(StoreManagePath.QRCODE)
	@ResponseBody
	public String getQRCode(HttpServletRequest request, HttpServletResponse response, StoreManage storeManage) {
		logger.info("开始查询商户信息===========客户号" + storeManage.getMchId() + "===============门店号" + storeManage.getShopNo());
		JSONObject object = new JSONObject();
//		String custId = request.getParameter("custId");
		Map<String, String> resultMap = new HashMap<String, String>();
		String src_url = "";
		String last_page = "/agent/merchantSuccess.jsp";
		String error_msg = "查询商户信息有误";
		try {
			// 获取商户编号
			TdCustInfo tdCustInfo = tdCustInfoMapper.selectById(storeManage.getMchId());
//			SevenpayResponse srp = BusinessFacadeHelper.handleService("EnterpriseService", "getAgentMerchantByPhone", custId);
//			@SuppressWarnings("unchecked")
//			List<TdCustInfo> list = (List<TdCustInfo>)srp.getResult();
//			String custCode = "";
//			TdCustInfo tdCustInfo=null;
//			if(list.size()>0){
//				custCode = tdCustInfo.getMerchantCode();
//				 tdCustInfo = list.get(0);
			if (null != storeManage.getMerchantCode() && !("".equals(storeManage.getMerchantCode()))) {
				// 判断生产环境 Or 测试环境
				//CommonData commonData = (CommonData) SpringBeans.getBean("CommonData");
				StringBuilder sb = new StringBuilder();
				sb.append("https://combinedpay.qifenqian.com/pub/merchantqr.do?mid=");
				sb.append(storeManage.getMerchantCode());
				sb.append("&sn=");
				sb.append(storeManage.getShopNo());

				String fileUploadPath = CERTIFY_FILE_SAVE_PATH;
				String filedName = "recode";
				String urlToImg = QRCode.urlToImg(fileUploadPath, sb.toString(), GenSN.getRandomNum(2) + filedName, 20);
				HttpSession session = request.getSession();
				String path = session.getServletContext().getRealPath("/WEB-INF/classes/static/static/images/pics_04.jpg");
				InputStream noePic = new FileInputStream(path);// 第一张背景图片可以写死
				InputStream twoPic = new FileInputStream(urlToImg);
				BufferedImage image = ImageIO.read(noePic);
				BufferedImage image2 = ImageIO.read(twoPic);
				Graphics g = image.getGraphics();
				g.drawImage(image2, image.getWidth() - image2.getWidth() + 21,
						image.getHeight() - image2.getHeight() - 38, image2.getWidth() - 120, image2.getHeight() - 120,
						null);
				File saveFile = new File(
						fileUploadPath + File.separator + "re" + File.separator + storeManage.getMchId());
				if (!saveFile.exists()) {
					saveFile.mkdirs();
				}
				OutputStream outImage = new FileOutputStream(fileUploadPath + File.separator + "re" + File.separator
						+ storeManage.getMchId() + File.separator + "recode.jpg");// 指定生成的图片路径
				//JPEGImageEncoder enc = JPEGCodec.createJPEGEncoder(outImage);
				//enc.encode(image);
				ImageIO.write(image, /*"GIF"*/ "jpg" /* format desired */ , outImage /* target */ );  
				noePic.close();
				twoPic.close();
				object.put("url", sb.toString());
				object.put("custId", storeManage.getMchId());
				// 保存
				CustScan custScan = new CustScan();
//					TdCustScanCopy scanCopyIdCard = new TdCustScanCopy();
				custScan.setCustId(storeManage.getMchId());
				custScan.setCertifyType("re");// 二维码

				String imagePath = custScanMapper.findPath(custScan);
//					SevenpayResponse srp3 = BusinessFacadeHelper.handleService("EnterpriseService", "findPath",scanCopyIdCard);
//					String imagePath =(String)srp3.getResult();
				if (imagePath != null) {// 已经插入
				} else {
					custScan.setCustId(tdCustInfo.getCustId());
					custScan.setCustName(tdCustInfo.getCustName());
					custScan.setScanCopyPath(fileUploadPath + File.separator + "re" + File.separator
							+ storeManage.getMchId() + File.separator + "recode.jpg");
					custScan.setCertifyNo(tdCustInfo.getCertifyNo());
					saveCustScanCopy(custScan);
				}

			} else {
				src_url = "/agent/QRCode/error.jsp";
				error_msg = "查询商户信息有误：商户编号为空";
				logger.error("查询商户信息有误：商户编号为空");
			}
			/*
			 * }else{ src_url = "/agent/QRCode/error.jsp"; error_msg =
			 * "查询商户信息有误：未查询出商户信息"; logger.error("查询商户信息有误：未查询出商户信息"); }
			 */

		} catch (Exception e) {
			src_url = "/agent/QRCode/error.jsp";
			resultMap.put("last_page", last_page);
			resultMap.put("errMag", error_msg);
			logger.error("查询商户信息有误===========" + e);
			e.printStackTrace();
		}

		return object.toString();

	}

	// 删除服务器图片
	public void delImagFile(String path) {
		File imgFile = new File(path);
		if (imgFile.exists()) {
			imgFile.delete();
		}
	}

	// 保存附件信息
	private void saveCustScanCopy(CustScan custScan) throws Exception {
		custScanMapper.insertCustScan(custScan);
		/*
		 * SevenpayResponse srp =
		 * BusinessFacadeHelper.handleService("EnterpriseService", "saveCustScanCopy",
		 * custScan); if(!srp.getResponseCode().equalsIgnoreCase("success")){ throw new
		 * AppException("保存附件表时出错"); }
		 */
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

	@RequestMapping(StoreManagePath.GETIMAGE)
	@ResponseBody
	public void getNewPic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String custId = request.getParameter("custId");
		String certifyType = request.getParameter("certifyType");
		CustScan custScan = new CustScan();
		custScan.setCustId(custId);
		custScan.setCertifyType(certifyType);
//		SevenpayResponse srp = BusinessFacadeHelper.handleService("EnterpriseService", "findPath", scanCopy);
//		String imagePath =(String)srp.getResult();
		String imagePath = custScanMapper.findPath(custScan);
		if (imagePath != null) {
			String paths = null;
			// 二维码类型
			if ("re".equals(certifyType)) {
				paths = imagePath;
			}

			File file = new File(imagePath);
			if (file.exists()) {
				FileInputStream fileInputStream = new FileInputStream(file);
				OutputStream os = response.getOutputStream();
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

	@RequestMapping(StoreManagePath.RECODE)
	@ResponseBody
	public void downNewPic(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String custId = request.getParameter("custId");
		String certifyType = request.getParameter("certifyType");
		CustScan custScan = new CustScan();
		custScan.setCustId(custId);
		custScan.setCertifyType(certifyType);
		String name = "recode.png";
		String imagePath = custScanMapper.findPath(custScan);
//		SevenpayResponse srp = BusinessFacadeHelper.handleService("EnterpriseService", "findPath", scanCopy);
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;fileName=" + name);
//		String imagePath =(String)srp.getResult();
		if (imagePath != null) {
			String paths = null;
			// 二维码类型
			if ("re".equals(certifyType)) {
				paths = imagePath;
			}

			File file = new File(imagePath);
			if (file.exists()) {
				FileInputStream fileInputStream = new FileInputStream(file);
				OutputStream os = response.getOutputStream();
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

}
