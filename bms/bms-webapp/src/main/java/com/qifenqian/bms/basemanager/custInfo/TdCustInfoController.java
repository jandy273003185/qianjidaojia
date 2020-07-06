package com.qifenqian.bms.basemanager.custInfo;

/**
 * 消费者管理
 */
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.custInfo.bean.TbPasswordModifyLog;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfoExcel;
import com.qifenqian.bms.basemanager.custInfo.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TbPasswordModifyLogMapper;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.privilegeControl.service.PrivilegeService;
import com.qifenqian.bms.basemanager.trade.service.DownLoadUtil;
import com.qifenqian.bms.basemanager.trade.service.TradeBillService;
import com.qifenqian.bms.basemanager.utils.DatetimeUtils;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.basemanager.utils.MD5Security;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.invoke.transaction.querybankcard.bean.BankCard;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageBean;
import com.sevenpay.plugin.message.bean.MessageColumnValues;

@Controller
@RequestMapping(TdCustInfoPath.BASE)
public class TdCustInfoController {

	private Logger logger = LoggerFactory.getLogger(TdCustInfoController.class);

	@Autowired
	private TdCustInfoService custInfoService;

	@Autowired
	private PrivilegeService privilegeService;
	
	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	SevenpayCoreServiceInterface sevenpayCoreServiceInterface;
	
	@Autowired
	private TradeBillService tradeBillService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private TbPasswordModifyLogMapper tbPasswordModifyLogMapper;
	
	/**
	 * 显示消费者列表
	 * 
	 * @param ad
	 * @return
	 */
	@RequestMapping(TdCustInfoPath.LIST)
	public ModelAndView list(TdCustInfo custInfo) {
		ModelAndView mv = new ModelAndView(TdCustInfoPath.BASE + TdCustInfoPath.LIST);
		List<TdCustInfo> list = custInfoService.selectCustInfos(custInfo);
		mv.addObject("custInfos", JSONObject.toJSON(list));
		mv.addObject("custBean", custInfo);
		mv.addObject("custStates", custInfoService.getCustStates());
		return mv;
	}
	
	/**
	 * 导出用户列表信息
	 * 
	 * @param requestBean
	 * @param request
	 * @param response
	 */
	@RequestMapping(TdCustInfoPath.EXPORTUSERINFO)
	public void exportExcel(TdCustInfo custInfo, HttpServletRequest request, HttpServletResponse response) {
		logger.info("导出用户列表信息");

		try {
			List<TdCustInfoExcel> excels = custInfoService.exportCustInfos(custInfo);

			String[] headers = { "客户编号", "客户名称", "客户邮箱","手机号码", "实名认证等级", "状态","创建时间","七分宝账号","七分钱账号","证件名称","证件号码","地址","备注","权限创建时间","权限编号"};
			String fileName = DatetimeUtils.dateSecond() + "_用户列表信息.xls";
			Map<String, String> fileInfo = tradeBillService.exportExcel(excels, headers, fileName, "消费者列表", request);
			DownLoadUtil.downLoadFile(fileInfo.get("filePath"), response, fileInfo.get("fileName"), "xls");
			logger.info("导出excel消费者列表成功");
		} catch (Exception e) {
			logger.error("导出excel消费者列表异常", e);
			throw new RuntimeException(e);
		}
	}
	

	/**
	 * 修改消费者信息
	 * 
	 * @param custInfo
	 * @return
	 */
	@RequestMapping(TdCustInfoPath.EDIT)
	@ResponseBody
	@Transactional
	public String updateCustInfo(TdCustInfo custInfo) {
		logger.info("修改客户信息");
		JSONObject jsonObject = new JSONObject();
		User user = WebUtils.getUserInfo();
		try {
			custInfo.setModifyId(String.valueOf(user.getUserId()));
			custInfoService.editTdCustInfo(custInfo);
			jsonObject.put("result", "success");
			logger.info("客户修改完成！");
		} catch (Exception e) {
			logger.error("修改客户未成功", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();

	}
	
	/**
	 * 重置支付密码
	 * @param custId
	 * @return
	 */
	@RequestMapping(TdCustInfoPath.PAYPASSWORDEDIT)
	@ResponseBody
	public String payPasswordEdit(String custId,String email,String mobile,String type) {
		logger.info("重置支付密码");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("email", email);
		jsonObject.put("mobile", mobile);
		jsonObject.put("type", type);
		TbPasswordModifyLog log = new TbPasswordModifyLog();
		try {
			TdCustInfo custInfo = custInfoService.selectById(custId);
			String tradeCode = GenSN.getRandomNum(6);
			String tradeCodeMD5= MD5Security.getMD5String(custId+tradeCode+custInfo.getAttachStr());
			custInfo.setTradePwd(tradeCodeMD5);
			
			/**客户密码修改记录*/	
			User user = WebUtils.getUserInfo();
			String id  = GenSN.getSN();
			log.setId(id);
			log.setCustId(custInfo.getCustId());
			log.setCreateId(String.valueOf(user.getUserId()));
			log.setStatus(Constant.PASSWORD_MODIFY_FAIL);
			tbPasswordModifyLogMapper.insert(log);
			
			int updateInt = custInfoService.updateInfo(custInfo);
			
			if(updateInt!=1){
				jsonObject.put("result", "fail");
				jsonObject.put("message", "重置支付密码失败");
				log.setStatus(Constant.PASSWORD_MODIFY_FAIL);
			}else{
				jsonObject.put("result", "success");
				jsonObject.put("message", "重置支付密码成功");
				jsonObject.put("tradeCode", tradeCode);
				log.setStatus(Constant.PASSWORD_MODIFY_SUCCESS);
			}
			tbPasswordModifyLogMapper.updateByPrimaryKey(log);
			
		} catch (Exception e) {
			logger.error("重置支付密码异常", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
	/**给客户发送短信**/
	@RequestMapping(TdCustInfoPath.SEND_MESSAGE_ASYN)
	public void sendMessageAsyn(String custId ,String tradeCode,String email, String mobil, String type) {
		TdCustInfo custInfo = custInfoService.selectById(custId);
		TdLoginUserInfo loginInfo = custInfoService.selectTdLoginInfo(custId);
		
		
		IPlugin plugin = commonService.getIPlugin();
		MessageBean messageBean = new MessageBean();
		
		if(null != loginInfo ){
			try {
				if("bindingEmail".equals(type)){
					
					if(StringUtils.isEmpty(loginInfo.getEmail())){
						throw new IllegalArgumentException("该用户未绑定邮箱");
					}
					messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
					messageBean.setBusType(MessageColumnValues.busType.RESET_TRADE_PWD);
					messageBean.setContent("亲爱的七分钱用户：" + custInfo.getCustName() + ":你好,你的七分钱支付密码已重置为:"+tradeCode+",请及时登陆七分钱更改密码,保障信息安全！");
					String[] tos = new String[]{loginInfo.getEmail()};
					messageBean.setTos(tos);
					plugin.sendMessage(MessageColumnValues.MsgType.EMAIL, messageBean);
					
				}
				if("byUser".equals(type)){
					if(!StringUtils.isEmpty(email)){
						messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
						messageBean.setBusType(MessageColumnValues.busType.RESET_TRADE_PWD);
						messageBean.setContent("亲爱的七分钱用户：" + custInfo.getCustName() + ":你好,你的七分钱支付密码已重置为:"+tradeCode+",请及时登陆七分钱更改密码,保障信息安全！");
						String[] tos = new String[]{email};
						messageBean.setTos(tos);
						plugin.sendMessage(MessageColumnValues.MsgType.EMAIL, messageBean);
					}
					
					if(!StringUtils.isEmpty(mobil)){
						messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
						messageBean.setBusType(MessageColumnValues.busType.RESET_TRADE_PWD);
						messageBean.setContent("亲爱的七分钱用户：" + custInfo.getCustName() + ":你好,你的七分钱支付密码已重置为:"+tradeCode+",请及时登陆七分钱更改密码,保障信息安全！");
						String[] tos = new String[]{mobil};
						messageBean.setTos(tos);
						plugin.sendMessage(MessageColumnValues.MsgType.SMS, messageBean);
					}
				}
			} catch (Exception e) {
				logger.error("重置密码信息通知异常",e);
			}
			

		
		}
	}
	
	/**
	 * 查询银行卡信息
	 * 
	 * @param custInfo
	 * @return
	 */
	@RequestMapping(TdCustInfoPath.BANKCARD)
	@ResponseBody
	public String queryAccountBankCard(String custId) {
		logger.info("查询客户信息");
		JSONObject jsonObject = new JSONObject();
		try {
			List<BankCard> bankCardList = custInfoService.queryBankCardList(custId);
			if (bankCardList.size()>0) {
				jsonObject.put("message", JSONObject.toJSON(bankCardList));
				jsonObject.put("result", "success");
			} else {
				jsonObject.put("result", "fail");
				jsonObject.put("message", "无银行卡信息");
			}
		} catch (Exception e) {
			logger.error("查询客户信息未成功", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	
	
	/**
	 * 查询余额
	 * 
	 * @param custInfo
	 * @return
	 */
	@RequestMapping(TdCustInfoPath.QUERYACCOUNT)
	@ResponseBody
	public String queryAccount(String custId) {
		logger.info("查询客户信息");
		JSONObject jsonObject = new JSONObject();
		try {
			TdCustInfo custInfo = custInfoService.queryAccount(custId);
			if (null != custInfo) {
				jsonObject.put("custInfo", custInfo);
				jsonObject.put("result", "success");
				logger.info("查询客户信息成功");
			} else {
				jsonObject.put("result", "fail");
				jsonObject.put("message", "未开通七分钱账户");
			}
		} catch (Exception e) {
			logger.error("查询客户信息未成功", e);
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
	/***
	 * 验证手机号码
	 * @param mobile
	 * @param custId
	 * @return
	 */
	@RequestMapping(TdCustInfoPath.VALIDATEMOBILE)
	@ResponseBody
	public String validateMobile(String mobile ,String custId) {
		JSONObject jsonObject = new JSONObject();
		try {
			TdLoginUserInfo loginCustInfo = custInfoService.validateCustMobile(mobile ,custId);
			if (null == loginCustInfo) {
				jsonObject.put("result", "SUCCESS");
				logger.info("查询客户信息成功");
			} else {
				jsonObject.put("result", "FAIL");
				jsonObject.put("message", "此手机号已被使用");
			}
		} catch (Exception e) {
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}
	
	
	
	
	/**
	 * 验证邮箱信息
	 * @param email
	 * @param custId
	 * @return
	 */
	@RequestMapping(TdCustInfoPath.VALIDATEEMAIL)
	@ResponseBody
	public String validateEmail(String email ,String custId) {
		JSONObject jsonObject = new JSONObject();
		try {
			TdLoginUserInfo loginCustInfo = custInfoService.validateEmail(email,custId);
			if (null == loginCustInfo) {
				jsonObject.put("result", "SUCCESS");
			} else {
				jsonObject.put("result", "FAIL");
				jsonObject.put("message", "此邮箱已被使用");
			}
		} catch (Exception e) {
			jsonObject.put("result", "fail");
			jsonObject.put("message", e.getMessage());
		}
		return jsonObject.toJSONString();
	}

	/**给客户发送信息**/
	@RequestMapping(TdCustInfoPath.SEND_MESSAGE)
	@ResponseBody
	public String sendMessage(String mobile ,String content) {

		logger.info("发送信息 {}",JSONObject.toJSONString(mobile,true));
		logger.info("发送信息内容 {}",JSONObject.toJSONString(content,true));
		JSONObject json = new JSONObject();
		if(StringUtils.isBlank(mobile)){
			json.put("result", "FAIL");
			json.put("message", "手机号码不可为空");
			return json.toJSONString();
		}
		if(StringUtils.isBlank(content)){
			json.put("result", "FAIL");
			json.put("message", "发送信息内容不可为空");
			return json.toJSONString();
		}
		IPlugin plugin = commonService.getIPlugin();
		MessageBean messageBean = new MessageBean();
		messageBean.setContent(content);
		messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
		messageBean.setBusType(MessageColumnValues.busType.MANUAL_HANDLING);
		String[] tos = new String[]{mobile};
		messageBean.setTos(tos);

		boolean falg = plugin.sendMessage(MessageColumnValues.MsgType.SMS, messageBean);
		if (falg) {
			json.put("result", "SUCCESS");
			logger.info("发送信息成功！");
		} else {
			logger.error("发送信息失败");
			json.put("result", "FAIL");
			json.put("message", "发送信息失败");
		}
		return json.toJSONString();
	}

}
