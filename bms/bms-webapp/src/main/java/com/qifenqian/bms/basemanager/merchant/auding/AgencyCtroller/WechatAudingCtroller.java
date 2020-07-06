package com.qifenqian.bms.basemanager.merchant.auding.AgencyCtroller;

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
import java.util.ArrayList;
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
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.merchant.auding.bean.AgencyAuding;
import com.qifenqian.bms.basemanager.merchant.auding.bean.CategoryCodeInfo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.MerchantCashier;
import com.qifenqian.bms.basemanager.merchant.auding.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbAreaInfo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbCity;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbProvince;
import com.qifenqian.bms.basemanager.merchant.auding.bean.WechatExport;
import com.qifenqian.bms.basemanager.merchant.auding.bean.bmsProtocolColumn;
import com.qifenqian.bms.basemanager.merchant.auding.dao.audingDao;
import com.qifenqian.bms.basemanager.merchant.auding.service.WechatAudingService;
import com.qifenqian.bms.basemanager.merchant.auding.service.audingService;
import com.qifenqian.bms.basemanager.merchant.bean.TdCertificateAuth;
import com.qifenqian.bms.basemanager.merchant.service.AuditorService;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.merchant.service.MerchantWorkFlowAuditService;
import com.qifenqian.bms.basemanager.protocolcontent.bean.ProtocolInfo;
import com.qifenqian.bms.basemanager.protocolcontent.mapper.ProtocolContentMapper;
import com.qifenqian.bms.basemanager.protocolcontent.service.ProtocolContentService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.platform.utils.HttpUtil;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageBean;
import com.sevenpay.plugin.message.bean.MessageColumnValues;

/**
 * 
 * @author 微商户审核控制器 2017年6月23日 上午11:28:22
 */
@Controller
@RequestMapping(WechatAudingPath.BASE)
public class WechatAudingCtroller {
	private Logger logger = LoggerFactory.getLogger(WechatAudingCtroller.class);
	ExecutorService smsExecutors = Executors.newFixedThreadPool(10);

	@Autowired
	private WechatAudingService wechatAudingService;
	@Autowired
	private AuditorService auditorService;
	@Autowired
	private MerchantWorkFlowAuditService merchantWorkFlowAuditService;

	@Autowired
	private TdCustInfoService tdCustInfoService;
	@Autowired
	private TradeBillService tradeBillService;
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private ProtocolContentMapper protocolContentMapper;

	@Autowired
	private audingDao applicationAudingDao;

	@Autowired
	private audingService audingService;

	@Autowired
	private CommonService commonService;

	@Autowired
	private ProtocolContentService protocolContentService;

	@Autowired
	private IPlugin iPlugin;

	/**
	 * 微商户列表
	 * 
	 * @throws ParseException
	 */
	@RequestMapping(WechatAudingPath.WECHATAUDITLIST)
	public ModelAndView wechatAuditList(MerchantVo merchantVo) throws ParseException {
		/* 去掉多余的空格 */
		if (!StringUtils.isBlank(merchantVo.getMerchantCode())) {
			merchantVo.setMerchantCode(merchantVo.getMerchantCode().trim());
		}
		if (!StringUtils.isBlank(merchantVo.getCustName())) {
			merchantVo.setCustName(merchantVo.getCustName().trim());
		}
		if (!StringUtils.isBlank(merchantVo.getEmail())) {
			merchantVo.setEmail(merchantVo.getEmail().trim());
		}
		if (!StringUtils.isBlank(merchantVo.getMobile())) {
			merchantVo.setMobile((merchantVo.getMobile().trim()));
		}
		if (!StringUtils.isBlank(merchantVo.getStartCreateTime())) {
			Date startCreateTime = new SimpleDateFormat("yyyy-MM-dd").parse(merchantVo.getStartCreateTime());
			merchantVo.setStartCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(startCreateTime));
		}
		if (!StringUtils.isBlank(merchantVo.getEndCreateTime())) {
			Date endCreateTime = new SimpleDateFormat("yyyy-MM-dd").parse(merchantVo.getEndCreateTime());
			merchantVo.setEndCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(endCreateTime));
		}
		// 获取列表
		ModelAndView mv = new ModelAndView(WechatAudingPath.BASE + WechatAudingPath.WECHATAUDITLIST);
		List<AgencyAuding> audingList = wechatAudingService.getWeChatAudingList(merchantVo);
		mv.addObject("audingList", audingList);
		mv.addObject("audingLists", JSONObject.toJSON(audingList));
		mv.addObject("queryBean", merchantVo);
		return mv;

	}

	// 获取单个微商对象
	@RequestMapping(WechatAudingPath.WECHATEDIT)
	@ResponseBody
	public MerchantVo wechatEdit(String custId) {
		MerchantVo selectmerchantVo = new MerchantVo();
		selectmerchantVo.setCustId(custId);
		selectmerchantVo.setAuditState("AUDIT");// AUDIT带审核
		logger.info("开始查询商户{}信息", custId);
		try {
			// 查询商户信息
			MerchantVo merchantVo = wechatAudingService.getSingleWeChatAuding(selectmerchantVo);
			String scanPath = audingService.findScanPath(custId, "08");// 查询门头照的个数
			if (!StringUtils.isEmpty(scanPath)) {

				List<String> doorPhoto = new ArrayList<>();
				String path[] = scanPath.split(";");
				for (String string : path)
					if (string.indexOf("doorPhoto") != -1) {// 门头照
						doorPhoto.add(string);

					}
				merchantVo.setDoorPhoto(doorPhoto);
			}
			if (null != merchantVo) {
				// 获取行业类名信息
				if (merchantVo.getCategoryType() != null) {
					CategoryCodeInfo categoryInfo = null;
					CategoryCodeInfo category = new CategoryCodeInfo();
					category.setCategoryId(merchantVo.getCategoryType());
					List<CategoryCodeInfo> categoryCodeInfoByType = wechatAudingService
							.getCategoryCodeInfoByType(category);
					if (categoryCodeInfoByType.size() > 0) {
						categoryInfo = categoryCodeInfoByType.get(0);
						merchantVo.setCategoryInfo(categoryInfo);
					}
					Integer cityInfo = Integer.parseInt(merchantVo.getCity());
					Integer country = Integer.parseInt(merchantVo.getCountry());
					// 获取省 市 区
					List<TbProvince> provList = wechatAudingService.selectProvinceList();
					TbCity tbCity = wechatAudingService.getCityInfoById(cityInfo);
					TbAreaInfo tbAreaInfo = wechatAudingService.getAreaInfoById(country);
					merchantVo.setTbCity(tbCity);
					merchantVo.setTbAreaInfo(tbAreaInfo);
					merchantVo.setProvList(provList);
				}
				// 查询收银员信息
				List<MerchantCashier> cashierList = wechatAudingService.getCashierList(custId);
				List<bmsProtocolColumn> bmsColumnList = wechatAudingService.getbmsColumnList(custId);
				if (cashierList.size() > 0 || bmsColumnList.size() > 0) {
					merchantVo.setBmsProtocolColumns(bmsColumnList);
					merchantVo.setCashierList(cashierList);
				}
				return merchantVo;
			}
		} catch (Exception e) {
			logger.error("审核异常", e);
		}
		return null;

	}

	// 读取服务器图片
	@RequestMapping(WechatAudingPath.IMAGE)
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String custId = request.getParameter("custId");
		String certifyType = request.getParameter("certifyType");
		String front = request.getParameter("front");
		String scanPath = null;
		scanPath = audingService.findScanPath(custId, certifyType);
		if (scanPath != null) {
			String path[] = null;
			if (certifyType.equals(Constant.CERTIFY_TYPE_MERCHANT_IDCARD)) {
				if (!StringUtils.isEmpty(front)) {
					path = scanPath.split(";");
					if (front.equals("0")) {// 正面
						scanPath = path[0];
					} else {// 反面
						scanPath = path[1];
					}
				}
			}
			if (certifyType.equals(Constant.CERTIFY_TYPE_BUSINESS)) {
				scanPath = scanPath.split(";")[0];
			}
			if (certifyType.equals(Constant.CERTIFY_TYPE_MERCHANT_DOORID)) {
				path = scanPath.split(";");
				if (front.equals("0")) {
					scanPath = path[0];
				} else if (front.equals("1")) {
					scanPath = path[1];
				} else if (front.equals("2")) {
					scanPath = path[2];
				} else if (front.equals("3")) {
					scanPath = path[3];
				} else if (front.equals("4")) {
					scanPath = path[4];
				}

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
	 * 审核不通过
	 */
	@RequestMapping(WechatAudingPath.SECONDNOTPASS)
	@ResponseBody
	public String secondNotPass(String custId, String message, HttpServletRequest request, String email, String mobile,
			String custName) {
		JSONObject ob = new JSONObject();
		logger.info("开始微商户{}审核不通过流程", custId);

		try {
			// 查询商户 协议信息
			ProtocolInfo info = protocolContentService.selectProtocolInfo(custId);

			TdCustInfo custInfo = tdCustInfoService.selectById(custId);
			wechatAudingService.secondAudit(custId, null, false, custInfo.getAuthId(), message, "35", "2", null, "");
			final IPlugin plugin = commonService.getIPlugin();
			final MessageBean messageBean = new MessageBean();
			String flag = iPlugin.findDictByPath("IS_TEST");
			if (info != null) {
				if ("H5_AGENT".equals(info.getInstUser())) {
					String url = iPlugin.findDictByPath("AGENT_MERCHANT_REGISTER_IP");
					String[] tos = null;
					String allUrl = url + "/agent/register/merchantUpdateRegister.do?agentId="
							+ info.getServiceProvider() + "&custId=" + custId;

					if ("0".equals(flag)) {// 生产
						tos = new String[] { mobile };
						String shortUrl = this.getShortUrl(allUrl);
						messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
						messageBean.setContent("您的商户\"" + custName + "\"未能通过审核，请点击链接 " + shortUrl + " 查看原因并修改商户资料。");
					} else {
						tos = new String[] { email };
						String shortUrl = this.getShortUrl(allUrl);
						messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
						messageBean.setContent("【七分钱支付】您的商户\"" + custName + "\"未能通过审核，原因是：" + message
								+ "，请点击下面链接修改商户信息，<br/><a href =\"" + allUrl + "\">" + allUrl
								+ "</a><br/>如有任何问题，请拨打400-633-0707。");
					}
					messageBean.setSubject("【七分钱支付】商户审核未通过");// 标题
					messageBean.setTos(tos);
					messageBean.setBusType(MessageColumnValues.busType.REGISTER_VERIFY);
				} else if ("H5_MANAGER".equals(info.getInstUser())) {
					String url = iPlugin.findDictByPath("AGENT_MERCHANT_REGISTER_IP");
					String[] tos = null;
					String allUrl = url + "/agent/register/custManagerUpdateRegister.do?custManager="
							+ info.getSelfPhone() + "&custId=" + custId;
					if ("0".equals(flag)) {// 生产
						tos = new String[] { mobile };
						String shortUrl = this.getShortUrl(allUrl);
						shortUrl.replace("http://", "");
						messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
						messageBean.setContent("您的商户\"" + custName + "\"未能通过审核，请点击链接 " + shortUrl + " 查看原因并修改商户资料。");
					} else {
						tos = new String[] { email };
						messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);// 电话SMS
						messageBean.setContent("【七分钱支付】您的商户\"" + custName + "\"未能通过审核，原因是：" + message
								+ "，请点击下面链接修改商户信息，<br/><a href =\"" + allUrl + "\">" + allUrl
								+ "</a><br/>如有任何问题，请拨打400-633-0707。");
					}
					messageBean.setSubject("【七分钱支付】商户审核未通过");// 标题
					messageBean.setTos(tos);
					messageBean.setBusType(MessageColumnValues.busType.REGISTER_VERIFY);
				} else {
					String[] tos = new String[] { email };// EMAIL
					messageBean.setSubject("【七分钱支付】商户审核未通过");// 标题
					messageBean.setContent("<html><body><div style=\"width:700px;margin:0 auto;\">"
							+ "<div style=\"margin-bottom:10px;\">您的商户" + custName + "未能通过审核,<br/>原因是：" + message
							+ "。<br/>如有任何问题,请拨打400-633-0707</div></body></html>");// 内容
					messageBean.setTos(tos);
					messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);// 邮件EMAIL
					if (!StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {// 邮箱为空发送短信
						tos = new String[] { mobile };// 电话
						messageBean.setContent(
								"【七分钱支付】您的商户" + custName + "未能通过审核,原因是：" + message + ",如有任何问题，请拨打400-633-0707");
						messageBean.setMsgType(MessageColumnValues.MsgType.SMS);// 电话SMS
					}
					messageBean.setBusType(MessageColumnValues.busType.REGISTER_VERIFY);
				}
			} else {
				// 发送邮件短信
				if (!StringUtils.isBlank(email)) {
					String[] tos = new String[] { email };// EMAIL
					messageBean.setSubject("【七分钱支付】商户审核未通过");// 标题
					messageBean.setContent("<html><body><div style=\"width:700px;margin:0 auto;\">"
							+ "<div style=\"margin-bottom:10px;\">您的商户" + custName + "未能通过审核<br/>  ,原因是：" + message
							+ "。<br/>如有任何问题,请拨打400-633-0707</div></body></html>");// 内容
					messageBean.setTos(tos);
					messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);// 邮件EMAIL
					if (!StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {// 邮箱为空发送短信
						tos = new String[] { mobile };// 电话
						messageBean.setContent(
								"【七分钱支付】您的商户" + custName + "未能通过审核,原因是：" + message + ",如有任何问题，请拨打400-633-0707");
						messageBean.setMsgType(MessageColumnValues.MsgType.SMS);// 电话SMS
					}
					messageBean.setBusType(MessageColumnValues.busType.REGISTER_VERIFY);
				}
			}
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
			logger.error("审核异常", e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	}

	/**
	 * 微商审核通过
	 * 
	 */
	@RequestMapping(WechatAudingPath.PASS)
	@ResponseBody
	public String Pass(String custId, String number, String message, String json_data, HttpServletRequest request,
			String authId) {
		logger.info("开始微商{}审核通过流程", custId);
		JSONObject ob = new JSONObject();
	
		@SuppressWarnings("rawtypes")
		Map parameterMap = request.getParameterMap();
		String isClear = request.getParameter("isClear");

		try {
			/**
			 * 启动流程完成任务
			 */
			authId = request.getParameter("authId");
			logger.info("启动流程完成任务");
			wechatAudingService.updateColumn(request, custId, parameterMap);

			wechatAudingService.startPass(custId, true, message, number, request, parameterMap, authId, isClear);

			// 查询商户 协议信息
			final ProtocolInfo info = protocolContentService.selectProtocolForEmail(custId);
			final MessageBean messageBean = new MessageBean();
			final IPlugin plugin = commonService.getIPlugin();
			String flag = iPlugin.findDictByPath("IS_TEST");
			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			final String custName = request.getParameter("custName");
			if (info != null) {
				if ("H5_AGENT".equals(info.getInstUser())) {
					String url = iPlugin.findDictByPath("AGENT_MERCHANT_REGISTER_IP");
					String[] tos = null;
					if ("0".equals(flag)) {// 生产
						tos = new String[] { mobile };
						messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
						String httpUrl = url + "/agent/register/payQRcode.do?merchantCode="
								+ request.getParameter("merchantCode");
						String shortUrl = this.getShortUrl(httpUrl);
						String content = "您的商户\"" + custName + "\"已通过审核，请查收您的收款码 " + shortUrl + " ,如有问题请联系服务商"
								+ info.getServiceProvider() + "。";
						messageBean.setContent(content);
					} else {
						tos = new String[] { email };
						messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
						messageBean.setContent(
								"您的商户\"" + custName + "\"已通过审核，请联系服务商" + info.getServiceProvider() + "接入吧。");
					}

					messageBean.setSubject("【七分钱支付】商户审核已通过");// 标题
					messageBean.setTos(tos);
					messageBean.setBusType(MessageColumnValues.busType.REGISTER_VERIFY);
				} else if ("H5_MANAGER".equals(info.getInstUser())) {
					String url = iPlugin.findDictByPath("AGENT_MERCHANT_REGISTER_IP");
					String[] tos = null;
					if ("0".equals(flag)) {// 生产
						tos = new String[] { mobile };
						messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
						String httpUrl = url + "/agent/register/payQRcode.do?merchantCode="
								+ request.getParameter("merchantCode");
						String shortUrl = this.getShortUrl(httpUrl);
						String content = "您的商户\"" + custName + "\"已通过审核，请查收您的收款码 " + shortUrl + " ,如有问题请联系客户经理"
								+ info.getCustManager() + "。";
						messageBean.setContent(content);
					} else {
						tos = new String[] { email };
						messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);// 电话SMS
						messageBean.setContent("您的商户\"" + custName + "\"已通过审核，联系客户经理" + info.getCustManager() + "接入吧。");
					}

					messageBean.setSubject("【七分钱支付】商户审核已通过");// 标题
					messageBean.setTos(tos);
					messageBean.setBusType(MessageColumnValues.busType.REGISTER_VERIFY);
				} else {
					String[] tos = new String[] { email };// EMAIL
					messageBean.setSubject("【七分钱支付】商户审核通过  ");// 标题
					messageBean.setContent("<html><body><div style=\"width:700px;margin:0 auto;\">"
							+ "<div style=\"margin-bottom:10px;\">恭喜您的商户" + custName
							+ "成功通过审核。<br/> 如有任何问题，请拨打400-633-0707</div></body></html>");// 内容
					messageBean.setTos(tos);
					messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);// 邮件EMAIL
					if (!StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {// 邮箱为空发送短信
						tos = new String[] { mobile };// 电话
						messageBean.setContent("【七分钱支付】恭喜您的商户" + custName + "成功通过审核。如有任何问题，请拨打400-633-0707");
						messageBean.setMsgType(MessageColumnValues.MsgType.SMS);// 电话SMS
					}
					messageBean.setBusType(MessageColumnValues.busType.REGISTER_VERIFY);
				}
			} else {
				// 发送邮件短信
				if (!StringUtils.isBlank(email)) {
					String[] tos = new String[] { email };// EMAIL
					messageBean.setSubject("【七分钱支付】商户审核通过  ");// 标题
					messageBean.setContent("<html><body><div style=\"width:700px;margin:0 auto;\">"
							+ "<div style=\"margin-bottom:10px;\">恭喜您的商户" + custName
							+ "成功通过审核。<br/> 如有任何问题，请拨打400-633-0707</div></body></html>");// 内容
					messageBean.setTos(tos);
					messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);// 邮件EMAIL
					if (!StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {// 邮箱为空发送短信
						tos = new String[] { mobile };// 电话
						messageBean.setContent("【七分钱支付】恭喜您的商户" + custName + "成功通过审核。如有任何问题，请拨打400-633-0707");
						messageBean.setMsgType(MessageColumnValues.MsgType.SMS);// 电话SMS
					}
					messageBean.setBusType(MessageColumnValues.busType.REGISTER_VERIFY);
				}
			}

			smsExecutors.execute(new Runnable() {
				@Override
				public void run() {
					logger.debug("发送邮件");
					// 给商户发送短信
					if (messageBean.getMsgType().equals(MessageColumnValues.MsgType.SMS)) {
						plugin.sendMessage(MessageColumnValues.MsgType.SMS, messageBean); // 电话SMS

					} else {
						plugin.sendMessage(MessageColumnValues.MsgType.EMAIL, messageBean); // 邮件EMAIL
					}

					// 邀请商户注册 后 会给邀请人发送短信
					if ("H5_AGENT".equals(info.getInstUser())) {
						messageBean.setTos(new String[] { info.getServicePhone() });// 服务商电话
						messageBean
								.setContent("商户\"" + custName + "\"已通过审核，请协助商户联系人\"" + info.getLinkMan() + "\"接入服务。");
						plugin.sendMessage(MessageColumnValues.MsgType.SMS, messageBean);
					} else if ("H5_MANAGER".equals(info.getInstUser())) {
						messageBean.setTos(new String[] { info.getSelfPhone() });// 客户经理电话
						messageBean
								.setContent("商户\"" + custName + "\"已通过审核，请协助商户联系人\"" + info.getLinkMan() + "\"接入服务。");
						plugin.sendMessage(MessageColumnValues.MsgType.SMS, messageBean);
					}

				}
			});

			ob.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("审核异常", e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	}

	/**
	 * 导出
	 */

	@RequestMapping(WechatAudingPath.TINYMERCHANTEXPORTMERCHAN)
	public void backExportExcel(MerchantVo merchantVo, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出后台微商户审核列表信息");
		try {
			List<WechatExport> excels = wechatAudingService.tinyMerchantExport(merchantVo);
			String[] headers = { "微商编号", "微商户名称", "手机", "开户名", "开户账号", "邮箱", "注册时间", "微商户状态", "收银员" };
			String fileName = DatetimeUtils.dateSecond() + "_微商户列表信息.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "微商户审核列表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel微商商户审核列表成功");
		} catch (Exception e) {
			logger.error("导出excel微商户审核列表异常", e);
			throw new RuntimeException(e);
		}
	}

	// 获取单个审核完毕商对象
	@RequestMapping(WechatAudingPath.SHOW)
	@ResponseBody
	public MerchantVo getCheckAgencyAuding(String custId) {
		logger.info("开始查询商户{}信息", custId);
		MerchantVo selectmerchantVo = new MerchantVo();
		selectmerchantVo.setCustId(custId);
		selectmerchantVo.setAuditState("VALID");// AUDIT带审核
		try {
			// 查询商户信息
			MerchantVo merchantVo = wechatAudingService.getSingleWeChatAuding(selectmerchantVo);
			String scanPath = audingService.findScanPath(custId, "08");// 查询门头照的个数

			if (!StringUtils.isEmpty(scanPath)) {
				List<String> doorPhoto = new ArrayList<>();
				String path[] = scanPath.split(";");
				for (String string : path) {
					if (string.indexOf("doorPhoto") != -1) {// 门头照
						doorPhoto.add(string);

					}
				}
				merchantVo.setDoorPhoto(doorPhoto);
			}

			if (null != merchantVo) {
				// 获取行业类名信息
				if (merchantVo.getCategoryType() != null) {
					CategoryCodeInfo categoryInfo = null;
					CategoryCodeInfo category = new CategoryCodeInfo();
					category.setCategoryId(merchantVo.getCategoryType());
					List<CategoryCodeInfo> categoryCodeInfoByType = wechatAudingService
							.getCategoryCodeInfoByType(category);
					if (categoryCodeInfoByType.size() > 0) {
						categoryInfo = categoryCodeInfoByType.get(0);
						merchantVo.setCategoryInfo(categoryInfo);
					}
					Integer cityInfo = Integer.parseInt(merchantVo.getCity());
					Integer country = Integer.parseInt(merchantVo.getCountry());
					// 获取省 市 区
					List<TbProvince> provList = wechatAudingService.selectProvinceList();
					TbCity tbCity = wechatAudingService.getCityInfoById(cityInfo);
					TbAreaInfo tbAreaInfo = wechatAudingService.getAreaInfoById(country);
					merchantVo.setTbCity(tbCity);
					merchantVo.setTbAreaInfo(tbAreaInfo);
					merchantVo.setProvList(provList);
				}
				// 查询收银员信息
				List<MerchantCashier> cashierList = wechatAudingService.getCashierList(custId);
				// 查询开通产品信息
				List<bmsProtocolColumn> bmsColumnList = wechatAudingService.getCheckbmsProtocolColumnList(custId);
				merchantVo.setBmsProtocolColumns(bmsColumnList);
				merchantVo.setCashierList(cashierList);
				return merchantVo;
			}
		} catch (Exception e) {
			logger.error("审核异常", e);
		}
		return null;
	}

	/**
	 * 获取微商审核历史记录
	 * 
	 */
	@RequestMapping(WechatAudingPath.GETHISTORY)
	@ResponseBody
	public String getHistory(String custId, HttpServletRequest request) {
		List<TdCertificateAuth> checkHistory = applicationAudingDao.getAgencyCheckHistory(custId);
		JSONObject ob = new JSONObject();
		try {
			if (null != checkHistory && checkHistory.size() > 0) {
				ob.put("checkHistory", checkHistory);
			} else {
				ob.put("result", "FAILE");
			}
		} catch (Exception e) {
			logger.error("获取历史记录异常", e);
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	}

	public static void main(String[] args) {
		String localUrl = "http://www.qifenqian.com/agent/register/custManagerUpdateRegister.do?custManager=18665857440&custId=57588824957049ed9c9d731c4840a25a";
		String codeUrl = "";
		try {
			codeUrl = URLEncoder.encode(localUrl, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String requestUrl = "http://api.t.sina.com.cn/short_url/shorten.json?source=2815391962&url_long=" + codeUrl;
		String url = HttpUtil.invoker(requestUrl);
		JSONArray jsonArray = JSONObject.parseArray(url);
		JSONObject jsonObject = jsonArray.getJSONObject(0);
		String j = (String) jsonObject.get("url_short");
		String x = j.replace("http://", "");
		System.out.println(x);
		String src = "https://api.t.sina";
		src = src.replaceAll("(http://|https://)", "");
		System.out.println(src);
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
