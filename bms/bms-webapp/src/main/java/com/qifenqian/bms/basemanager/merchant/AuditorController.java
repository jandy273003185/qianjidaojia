package com.qifenqian.bms.basemanager.merchant;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.service.BankService;
import com.qifenqian.bms.basemanager.city.bean.City;
import com.qifenqian.bms.basemanager.city.service.CityService;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.service.AuditorService;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.merchant.service.MerchantWorkFlowAuditService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.basemanager.utils.MD5Security;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.myworkspace.WorkFlowHelper;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.user.service.UserService;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.common.message.request.RequestMessage;
import com.sevenpay.invoke.common.message.response.ResponseMessage;
import com.sevenpay.invoke.common.type.RequestColumnValues;
import com.sevenpay.invoke.common.type.RequestColumnValues.MsgType;
import com.sevenpay.invoke.transaction.bindbankcard.BindBankCardRequest;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageBean;
import com.sevenpay.plugin.message.bean.MessageColumnValues;

@Controller
@RequestMapping(AuditorPath.BASE)
public class AuditorController {
	private Logger logger = LoggerFactory.getLogger(MerchantController.class);
	@Autowired
	private MerchantService merchantService;
	@Autowired
	private AuditorService auditorService;
	@Autowired
	private UserService userService;
	@Autowired
	private CityService cityService;

	@Autowired
	private WorkFlowHelper workFlowHelper;
	@Autowired
	SevenpayCoreServiceInterface sevenpayCoreServiceInterface;
	@Autowired
	private CommonService commonService;
	@Autowired
	private MerchantWorkFlowAuditService merchantWorkFlowAuditService;

	@Autowired
	private BankService bankService;
	
	@Autowired
	private TdCustInfoService tdCustInfoService;
	
	@Value("${IMAGEIP}")
	private String IMAGEIP;
	
	@RequestMapping(AuditorPath.LIST)
	public ModelAndView list(MerchantVo merchantVo) {
		ModelAndView mv = new ModelAndView(AuditorPath.BASE + AuditorPath.LIST);
		List<MerchantVo> list = auditorService.selectMerchants(merchantVo);
		mv.addObject("merchantList", JSONObject.toJSON(list));
		return mv;
	}

	// 读取服务器图片
	@RequestMapping(AuditorPath.IMAGE)
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String custId = request.getParameter("custId");
		String certifyType = request.getParameter("certifyType");
		String front = request.getParameter("front");
		String authId = request.getParameter("authId");
		String scanPath = "";
		if("08".equals(certifyType)){
			scanPath = authId;
		}else{
			scanPath = auditorService.findScanPath(custId, certifyType,authId);
		}
		 
		if (scanPath != null) {
			String path[] = null;
			if (certifyType.equals(Constant.CERTIFY_TYPE_PERSON_IDCARD)) {
				scanPath =scanPath.split(";")[0] ;
			}
			if(certifyType.equals(Constant.CERTIFY_TYPE_BUSINESS)){
				scanPath = scanPath.split(";")[0];
			}
			/*if(certifyType.equals(Constant.CERTIFY_TYPE_MERCHANT_DOORID)){
				scanPath = scanPath.split(";")[0];
			}*/
			if (!StringUtils.isEmpty(front)) {
				path = scanPath.split(";");
				if (front.equals("0")) {
					scanPath = path[0];
				} else {
					scanPath = path[1];
				}
			}

			OutputStream os = response.getOutputStream();
			// scanPath=scanPath.replaceAll("\\\\","\\\\\\\\");
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

	// 审核商户不通过
	@RequestMapping(AuditorPath.NOPASSAUDITOR)
	@ResponseBody
	public String noPassAuditor(MerchantVo merchantVo, HttpServletRequest request) {
		logger.info("发送邮件信息");
		JSONObject object = new JSONObject();
		String createId = merchantVo.getCreateId();
		User user = userService.selectUserSingleById(Integer.parseInt(createId));
		String email = user.getWorkEmail();
		String ip = IMAGEIP;
		String content = "<html><body><div style=\"width:700px;margin:0 auto;\">" + "<div style=\"margin-bottom:10px;\">" + "<img src=\"https://"+ip+"/images/account-img/account-logo.png\" width=\"256px\" height=\"42px\">" + "</div><div style=\"border-top: 1px solid #ccc; margin-top: 20px;\"></div>" + "<div style=\"padding:20px 10px 60px;\"><div style=\"line-height:1.5;color:#4d4d4d;\">" + "<h3 style=\"font-weight:normal;font-size:16px;\">尊敬的" + createId + "：</h3>" + "<p style=\"font-size:14px;margin-top:15px;\">您好！你提供资料不完善，审核不通过 ,赶紧跟商户核对，完善资料</br>" + "，请尽快修改好商户资料。</p></div></div>	<div style=\"border-bottom: 1px dashed #d8d8d8\"></div>" + "<div style=\"width:700px;margin:0 auto;margin-top:10px;color:#8a8a8a;\">" + "<p>此为系统邮件，请勿回复；Copyright ©2015-2016七分钱（国银证保旗下支付平台）  版权所有</p></div></div></body></html>";
		IPlugin plugin = commonService.getIPlugin();
		MessageBean messageBean = new MessageBean();
		String[] tos = new String[]{email};
		messageBean.setSubject("七分钱--亲爱的" + createId + "你的七分钱商户账号注册不成功！");
		messageBean.setContent(content);
		messageBean.setTos(tos);
		messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
		messageBean.setBusType(MessageColumnValues.busType.REGISTER_VERIFY);
		boolean falg = plugin.sendMessage(MessageColumnValues.MsgType.EMAIL, messageBean);
		
		if (falg) {
			object.put("result", "success");
			logger.info("发送邮件成功！");
		} else {
			logger.error("发送邮件失败");
			object.put("result", "fail");
		}
		// 流程实例驳回
		Map<String, Object> var = new LinkedHashMap<String, Object>();
		var.put("result", "nopass");
		workFlowHelper.endTask(request.getParameter("taskId"), var);
		try {
			/**
			 * 写入商户注册审核状态
			 */
			auditorService.failPassAndUpdateStatus(merchantVo.getCustId());
			object.put("result", "success");
		} catch (Exception e) {
			object.put("result", "fail");
			object.put("message", e.getMessage());
		}
		return object.toJSONString();
	}

	// 审核商户
	@RequestMapping(AuditorPath.SUREAUDITOR)
	@ResponseBody
	public String sureAuditor(MerchantVo merchantVo, HttpServletRequest request) {

		String taskId = request.getParameter("taskId");
		logger.info("发送邮件信息");
		JSONObject object = new JSONObject();
		String smsVerifyCode = GenSN.getRandomNum(6);
		String custName = merchantVo.getCustName();
		String email = merchantVo.getEmail();
		String custId = merchantVo.getCustId();

		String attachStr = GenSN.getRandomNum(5);
		String loginPwd_02 = MD5Security.getMD5String(custId + smsVerifyCode + attachStr);
		merchantVo.setAttachStr(attachStr);

		String content = "<html><body><div style=\"width:700px;margin:0 auto;\">" + "<div style=\"margin-bottom:10px;\">" + "</div><div style=\"border-top: 1px solid #ccc; margin-top: 20px;\"></div>" + "<div style=\"padding:20px 10px 60px;\"><div style=\"line-height:1.5;color:#4d4d4d;\">" + "<h3 style=\"font-weight:normal;font-size:16px;\">尊敬的" + custName + "：您好！</h3>" + "<p style=\"font-size:14px;margin-top:15px;\">你的七分钱商户账号已注册成功！  </br>" + "<b style=\"font-size:18px;color:#ff9900\">账号为" + email + "</b>" + "<b style=\"font-size:18px;color:#ff9900\">初始密码为" + smsVerifyCode + "</b>" + "，为了方便使用七分钱请点击" + "<a href=\"https://www.qifenqian.com\">www.qifenqian.com</a>" + "尽快修改你的初始密码。" + "</p>" + "<p style=\"font-size:14px;margin-top:15px;\">如有疑问，请联系我们</p>" + "<p style=\"font-size:14px;margin-top:15px;\">电话：0755-83026070</p>" + "<p style=\"font-size:14px;margin-top:15px;\">七分钱因您而努力</p>" + "</div></div>	<div style=\"border-bottom: 1px dashed #d8d8d8\"></div>"
				+ "<div style=\"width:700px;margin:0 auto;margin-top:10px;color:#8a8a8a;\">" + "<p>此为系统邮件，请勿回复；Copyright ©2015-2016七分钱（国银证保旗下支付平台）  版权所有</p></div></div></body></html>";

		ResponseMessage<com.sevenpay.invoke.transaction.bindbankcard.BindBankCardResponse> response = this.requestBindBank(merchantVo);
		// System.out.println(response.getResponse().getRtnInfo());
		if (RequestColumnValues.RtnResult.SUCCESS == response.getRtnResult()) {
			try {
				this.saveAndUpdate(merchantVo, loginPwd_02);

				object.put("result", "success");
				object.put("message", "商户审核成功");

				// 完成流程实例任务
				Map<String, Object> var = new LinkedHashMap<String, Object>();
				var.put("result", "pass");
				workFlowHelper.endTask(taskId, var);

				IPlugin plugin = commonService.getIPlugin();
				MessageBean messageBean = new MessageBean();
				String[] tos = new String[]{email};
				messageBean.setSubject("七分钱--亲爱的" + custName + "你的七分钱商户账号已注册成功！");
				messageBean.setContent(content);
				messageBean.setTos(tos);
				messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
				messageBean.setBusType(MessageColumnValues.busType.REGISTER_VERIFY);

				plugin.sendMessage(MessageColumnValues.MsgType.EMAIL, messageBean);
				
			} catch (Exception e) {
				logger.error("商户审核失败：" + e);
				object.put("result", "fail");
				object.put("message", e.getMessage());
			}

		} else {
			logger.error("商户审核绑定银行卡失败！");
			object.put("result", "fail");
			object.put("message", "商户审核绑定银行卡失败:(" + response.getRtnInfo() + ")");

		}

		return object.toJSONString();
	}

	// 插入加密登陆密码
	// 修改客户状态
	public void saveAndUpdate(MerchantVo merchantVo, String loginPwd_02) {
		auditorService.saveAndUpdate(merchantVo, loginPwd_02);
	}
	
	// 根据id查找商户信息 businessRegAddr
	@RequestMapping(AuditorPath.SELECTIDAUDITORINFO)
	@ResponseBody
	public String findByIdMerchantInfo(String custId) {
		logger.info("查找商户信息");
		JSONObject jsonObject = new JSONObject();
		MerchantVo merchantVo = merchantService.findMerchantInfo(custId);
		if (merchantVo.getCompAcctBank() != null) {
			Bank bank = bankService.selectBankByBankCode(merchantVo.getCompAcctBank());
			merchantVo.setBankName(bank.getBankName());
		}
		String businessRegAddr = merchantVo.getBusinessRegAddr();
		Integer province = 0;
		Integer city1 = 0;

		if (businessRegAddr != null) {
			province = Integer.valueOf(businessRegAddr.split(",")[0]);
			city1 = Integer.valueOf(businessRegAddr.split(",")[1]);
			if (province <= 4) {
				businessRegAddr = cityService.findProvineNameById(province);
				merchantVo.setBusinessRegAddr(businessRegAddr);
			} else {
				City city = new City();
				city.setCityId(city1);
				city.setProvinceId(province);
				businessRegAddr = cityService.findProvineNameById(province) + cityService.findCityName(city);
				merchantVo.setBusinessRegAddr(businessRegAddr);
			}
		}

		jsonObject.put("merchantVo", merchantVo);
		return jsonObject.toJSONString();
	}

	@SuppressWarnings("finally")
	public ResponseMessage<com.sevenpay.invoke.transaction.bindbankcard.BindBankCardResponse> requestBindBank(MerchantVo vo) {
		ResponseMessage<com.sevenpay.invoke.transaction.bindbankcard.BindBankCardResponse> response = null;
		try {
			// InterfaceService service = this.getTranCoreIntfService();

			RequestMessage<BindBankCardRequest> requestMessage = new RequestMessage<BindBankCardRequest>();

			requestMessage.setMsgType(MsgType.BANK_CARD_BIND);
			requestMessage.setReqSysId(RequestColumnValues.ReqSysId.OPER);
			requestMessage.setReqSerialId(DatetimeUtils.datetime());
			requestMessage.setReqMsgNum(1);
			requestMessage.setReqDatetime(new Date());

			BindBankCardRequest request = new BindBankCardRequest();
			Bank bank = null;
			if (vo.getCompAcctBank() != null) {
				bank = bankService.selectBankByBankCode(vo.getCompAcctBank());
				if (bank != null) {
					logger.info("商户使用的银行为：" + bank.getBankName());
				} else {
					logger.error("商户使用的银行系统中不存在：" + vo.getCompAcctBank());
				}

			}
			request.setOperateType(RequestColumnValues.BindOperateType.BIND);
			request.setCustType(RequestColumnValues.CustType.BUSINESS);
			request.setCustId(vo.getCustId());
			request.setAcctType(RequestColumnValues.AcctType.BANK_DEBIT);//
			request.setBankCardNo(vo.getCompMainAcct());
			request.setBankCardName(vo.getCustName());// 必填
			request.setBusinessType(RequestColumnValues.BusinessType.WITHDRAW);//
			request.setPhone(vo.getRepresentativeMobile());
			request.setBankCode12(vo.getCompAcctBank());
			if (bank != null) {
				request.setBankName(bank.getBankName());
			}

			// request.setIdType(RequestColumnValues.IdType.IDENTITY);
			// request.setIdNo("个人身份证");//
			request.setBrief("提现卡");

			// requestMessage.setMsgType(RequestColumnValues.MsgType.BND_CAD_UN);
			// requestMessage.setReqMsgNum(1);
			// requestMessage.setReqSerialId(GenSN.getSN());
			// BindBankCardRequest bankCardRequest=new BindBankCardRequest();
			// bankCardRequest.setBindType(RequestColumnValues.BindOperateType.BIND);
			// bankCardRequest.setCustId(vo.getCustId());
			// bankCardRequest.setCustName(vo.getCustName());
			// bankCardRequest.setCustType(RequestColumnValues.CustType.CUSTOMER);
			// bankCardRequest.setMsgTypeUse(RequestColumnValues.MsgType.SEV_CASH_O);
			// bankCardRequest.setAcctType(RequestColumnValues.AcctType.BANK_CUS_0);
			// bankCardRequest.setAcctNo(vo.getCompMainAcct());
			// bankCardRequest.setAcctName(vo.getCustName());
			// bankCardRequest.setIdType(RequestColumnValues.IdType.IDENTITY);
			// bankCardRequest.setIdNo("个人身份证");
			// bankCardRequest.setQuickPass(RequestColumnValues.QuickPass.Y);
			//
			// bankCardRequest.setBankCode12(vo.getCompAcctBank());

			// bankCardRequest.setBankName(bankCard.getBankName());
			// bankCardRequest.setPhone(vo.getRepresentativeMobile());

			/*
			 * for(IdType rate:IdType.values()){
			 * System.out.println(rate.getCode());
			 * 
			 * //bankCardRequest.setIdType(rate); System.out.println(rate); }
			 */
			// String idType =vo.getCertifyType();
			// if(idType.equals(RequestColumnValues.IdType.))
			// bankCardRequest.setIdType(RequestColumnValues.IdType);
			// requestMessage.setReqDatetime(new Date());
			// requestMessage.setRequest(bankCardRequest);
			//
			// requestMessage.setReqSysId(RequestColumnValues.ReqSysId.BMS);
			requestMessage.setRequest(request);
			response = sevenpayCoreServiceInterface.bindBankCard(requestMessage);
		} catch (Exception e) {
			logger.error("创建七分钱账户异常", e);
		} finally {
			return response;
		}

	}
}
