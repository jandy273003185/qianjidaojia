package com.qifenqian.bms.basemanager.merchant.auding.AgencyCtroller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.merchant.auding.bean.AgencyAuding;
import com.qifenqian.bms.basemanager.merchant.auding.bean.AgencyAudingPath;
import com.qifenqian.bms.basemanager.merchant.auding.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.bmsProtocolColumn;
import com.qifenqian.bms.basemanager.merchant.auding.bean.bmsProtocolContent;
import com.qifenqian.bms.basemanager.merchant.auding.service.WechatAudingService;
import com.qifenqian.bms.basemanager.merchant.auding.service.audingService;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantExport;
import com.qifenqian.bms.basemanager.merchant.bean.TdCertificateAuth;
import com.qifenqian.bms.basemanager.merchant.service.MerchantWorkFlowAuditService;
import com.qifenqian.bms.basemanager.protocolcontent.mapper.ProtocolContentMapper;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.expresspay.CommonService;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageBean;
import com.sevenpay.plugin.message.bean.MessageColumnValues;

/**
 * 
 * @author 代理商审核控制器 2017年6月23日 上午11:28:22
 */
@Controller
@RequestMapping(AgencyAudingPath.BASE)
public class AgencyCtrollers {
	private Logger logger = LoggerFactory.getLogger(AgencyCtrollers.class);

	ExecutorService smsExecutors = Executors.newFixedThreadPool(10);

	@Autowired
	private audingService audingService;

	@Autowired
	private MerchantWorkFlowAuditService merchantWorkFlowAuditService;

	@Autowired
	private TdCustInfoService tdCustInfoService;
	@Autowired
	private TradeBillService tradeBillService;

	@Autowired
	private ProtocolContentMapper protocolContentMapper;

	@Autowired
	private WechatAudingService wechatAudingService;

	@Autowired
	private CommonService commonService;

	@RequestMapping(AgencyAudingPath.LIST)
	public ModelAndView list(MerchantVo merchantVo) {
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
		if (!StringUtils.isBlank(merchantVo.getCustManagerName())) {
			merchantVo.setCustManagerName((merchantVo.getCustManagerName().trim()));
			;
		}
		// 获取列表
		ModelAndView mv = new ModelAndView(AgencyAudingPath.BASE + AgencyAudingPath.LIST);
		List<AgencyAuding> audingList = audingService.getAgencyAudingList(merchantVo);
		mv.addObject("audingList", audingList);
		mv.addObject("audingLists", JSONObject.toJSON(audingList));
		mv.addObject("queryBean", merchantVo);
		return mv;

	}

	// 获取待审核代理商对象
	@RequestMapping(AgencyAudingPath.AGENCY)
	@ResponseBody
	public MerchantVo getAgencyAuding(String custId) {
		MerchantVo selectmerchantVo = new MerchantVo();
		selectmerchantVo.setCustId(custId);
		selectmerchantVo.setAuditState("AUDIT");// AUDIT带审核
		MerchantVo merchantVo = audingService.getSingleAgencyAuding(selectmerchantVo);
		// 获取代理商协议 栏位
		if (null != merchantVo) {
			List<bmsProtocolColumn> protocolColumnList = audingService.getbmsProtocolColumnList(custId);
			bmsProtocolContent protocolContent = audingService.getbmsPbmsProtocolContent(custId);
			if (null != protocolColumnList && protocolColumnList.size() > 0) {
				merchantVo.setBmsProtocolColumns(protocolColumnList);
				merchantVo.setProtocolContent(protocolContent);
				return merchantVo;
			} else {
				return merchantVo;
			}
		}
		return null;
	}

	/** 获取审核完毕的单个代理商对象 */
	@RequestMapping(AgencyAudingPath.SHOW)
	@ResponseBody
	public MerchantVo getCheckAgencyAuding(String custId) {
		MerchantVo selectmerchantVo = new MerchantVo();
		selectmerchantVo.setCustId(custId);
		selectmerchantVo.setAuditState("VALID");// 生效VALID
		MerchantVo merchantVo = audingService.getSingleAgencyAuding(selectmerchantVo);
		/** 获取代理商协议 */
		if (null != merchantVo) {
			List<bmsProtocolColumn> protocolColumnList = audingService.getCheckbmsProtocolColumnList(custId);
			if (null != protocolColumnList && protocolColumnList.size() > 0) {
				merchantVo.setBmsProtocolColumns(protocolColumnList);
				return merchantVo;
			} else {
				return merchantVo;
			}
		}
		return null;

	}

	/**
	 * 审核不通过
	 */
	@RequestMapping(AgencyAudingPath.SECONDNOTPASS)
	@ResponseBody
	public String secondNotPass(String custId, String messages, String email, String mobile, String authId) {
		logger.info("开始代理商{}审核不通过流程", custId);
		JSONObject ob = new JSONObject();
		try {
			TdCustInfo custInfo = tdCustInfoService.selectById(custId);

			// 2：审核不通过
			audingService.secondAudit(custId, null, false, custInfo.getAuthId(), messages, "35", "2", null, "");

			// 发送邮件短信
			if (!StringUtils.isBlank(email)) {
				final IPlugin plugin = commonService.getIPlugin();
				final MessageBean messageBean = new MessageBean();
				String[] tos = new String[] { email };// EMAIL
				messageBean.setSubject("【七分钱支付】服务商审核未通过");// 标题
				messageBean.setContent(
						"<html><body><div style=\"width:700px;margin:0 auto;\"><div style=\"margin-bottom:10px;\">您未能通过七分钱服务商审核,<br/>原因是："
								+ messages + "<br/>如有任何问题,请拨打400-633-0707</div></body></html>");
				messageBean.setTos(tos);
				messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);// 邮件EMAIL
				if (!StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {// 邮箱为空发送短信
					tos = new String[] { mobile };// 电话
					messageBean.setContent("【七分钱支付】您未能通过七分钱服务商审核,原因是:" + messages + "如有任何问题,请拨打400-633-0707");
					messageBean.setMsgType(MessageColumnValues.MsgType.SMS);// 电话SMS
				}
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
			}

			ob.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("审核异常", e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());
		}
		return ob.toJSONString();
	}

	/**
	 * 代理商审核通过
	 * 
	 */
	@RequestMapping(AgencyAudingPath.PASS)
	@ResponseBody
	public String Pass(String custId, String number, String message, String json_data, HttpServletRequest request) {
		logger.info("开始代理商{}审核通过流程", custId);

		JSONObject ob = new JSONObject();
		@SuppressWarnings("rawtypes")
		Map  parameterMap = request.getParameterMap();
		try {
			/**
			 * 启动流程完成任务
			 */
			logger.info("启动流程完成任务");
			String authId = request.getParameter("authId");
			audingService.updateColumn(request, custId, parameterMap);
			audingService.startPass(custId, true, message, number, authId);

			String email = request.getParameter("email");
			String mobile = request.getParameter("mobile");
			// 发送邮件短信
			if (!StringUtils.isBlank(email)) {
				final IPlugin plugin = commonService.getIPlugin();
				final MessageBean messageBean = new MessageBean();
				String[] tos = new String[] { email };// 电话或者 EMAIL
				messageBean.setSubject("【七分钱支付】服务商审核通过");// 标题
				messageBean.setContent("<html><body><div style=\"width:700px;margin:0 auto;\">"
						+ "<div style=\"margin-bottom:10px;\">恭喜您成功通过服务商审核，您可以继续添加商户信息。<br/>如有任何问题，请拨打400-633-0707</div></body></html>");// 内容
				messageBean.setTos(tos);
				messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);// 邮件EMAIL
				if (!StringUtils.isBlank(mobile) && StringUtils.isBlank(email)) {// 邮箱为空发送短信
					tos = new String[] { mobile };// 电话
					messageBean.setContent("【七分钱支付】恭喜您成功通过服务商审核,您可以继续添加商户信息。如有任何问题，请拨打400-633-0707");
					messageBean.setMsgType(MessageColumnValues.MsgType.SMS);// 电话SMS
				}
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
			}
			ob.put("result", "SUCCESS");
		} catch (Exception e) {
			logger.error("审核异常", e);
			ob.put("result", "FAILE");
			ob.put("message", e.getMessage());

		}
		return ob.toJSONString();
	}

	/**
	 * 获取代理商审核历史记录
	 * 
	 */
	@RequestMapping(AgencyAudingPath.GETHISTORY)
	@ResponseBody
	public String getHistory(String custId, HttpServletRequest request) {
		JSONObject ob = new JSONObject();
		List<TdCertificateAuth> checkHistory = audingService.getAgencyCheckHistory(custId);
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

	/**
	 * 导出后台商户审核列表信息
	 * 
	 * @param requestBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(AgencyAudingPath.AGENTEXPORTMERCHANTINFO)
	public void backExportExcel(MerchantVo merchantVo, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出后台商户审核列表信息");
		try {
			List<MerchantExport> excels = audingService.agentExportMerchantInfo(merchantVo);
			String[] headers = { "代理商编号", "代理商名称", "手机", "开户名", "开户账号", "邮箱", "客户经理", "注册时间", "商户状态" };
			String fileName = DatetimeUtils.dateSecond() + "_代理商户列表信息.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "代理商户审核列表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel代理商商户审核列表成功");
		} catch (Exception e) {
			logger.error("导出excel代理商户审核列表异常", e);
			throw new RuntimeException(e);
		}
	}

	// 读取服务器图片
	@RequestMapping(AgencyAudingPath.GETIMG)
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String custId = request.getParameter("custId");
		String certifyType = request.getParameter("certifyType");
		String front = request.getParameter("front");
		String scanPath = audingService.findScanPath(custId, certifyType);
		if (scanPath != null) {
			String path[] = null;
			if (certifyType.equals(Constant.CERTIFY_TYPE_MERCHANT_IDCARD)) {
				if (!StringUtils.isEmpty(front)) {
					path = scanPath.split(";");
					if (front.equals("0")) {
						scanPath = path[0];
					} else {
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

}
