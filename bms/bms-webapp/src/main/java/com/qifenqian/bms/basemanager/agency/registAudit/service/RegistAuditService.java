package com.qifenqian.bms.basemanager.agency.registAudit.service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.agency.registAudit.dao.RegistAuditDao;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.auding.bean.TbBankCode;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantExport;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.expresspay.CommonService;
import com.qifenqian.bms.upgrade.merchant.bean.MerchantRegisterInfo;
import com.qifenqian.bms.upgrade.merchant.bean.TdAuditRecodeInfo;
import com.sevenpay.plugin.IPlugin;
import com.sevenpay.plugin.message.bean.MessageBean;
import com.sevenpay.plugin.message.bean.MessageColumnValues;

@Service
public class RegistAuditService {

	@Autowired
	private RegistAuditDao registAuditDao;
	
	@Autowired
	private CommonService commonService;
	
	ExecutorService smsExecutors = Executors.newFixedThreadPool(10);
	private Logger logger = LoggerFactory.getLogger(RegistAuditService.class);
	/**
	 * 查询商户列表
	 * @param queryBean  查询条件
	 * @return
	 */
	public List<MerchantVo> listMerchant(MerchantVo queryBean){
		return registAuditDao.listMerchant(queryBean);
	}
	
	/**
	 * 查询商户状态
	 * @param merchantCode
	 * @return
	 */
	public String selectStateOfMerchant(String merchantCode) {
		return registAuditDao.selectStateOfMerchant(merchantCode);
	}
	/**
	 * 查询商户信息
	 * @param merchantCode 商户编号
	 * @return
	 */
	public List<MerchantRegisterInfo> queryMerchantInfo(String merchantCode){
		return registAuditDao.queryMerchantInfo(merchantCode);
	}
	/**
	 * 导出商户信息列表表格
	 * @param queryBean
	 * @return
	 */
	public List<MerchantExport> exportlist(MerchantVo queryBean){
		return registAuditDao.exportlist(queryBean);
	}
	/**
	 * 审核结果
	 * @param result 审核结果，pass通过   noPass不通过
	 * @param merchantId 商户编号
	 * @param AuditInfo	审核信息
	 * @return
	 */
	public String updateResultOfAudit(String result,String merchantId,TdAuditRecodeInfo AuditInfo) {
		String applyStatus = null;
		String custStatus = null;
		String userStatus = null;
		String[] tos = null;
		String content = null;
//		String subject = null;
		final IPlugin plugin = commonService.getIPlugin();
		final MessageBean messageBean = new MessageBean();
		

		//获取custId
		List<MerchantRegisterInfo> list = queryMerchantInfo(merchantId);
		MerchantRegisterInfo merchantInfo = null;
		merchantInfo = list.get(0);
		String custId = merchantInfo.getCustId();
		//获取登陆表信息
		TdLoginUserInfo userInfo = registAuditDao.getUserInfoByCustId(custId);
		String userEmail = userInfo.getEmail();
		String userMobile = userInfo.getMobile();
	
		//获取商户信息
		TdCustInfo custInfo = registAuditDao.getCustInfoByCustId(custId);
		String custName = custInfo.getCustName();
		
		
		if("noPass".equals(result)) {
			applyStatus = "99";
			custStatus = "04";
			userStatus = "07";	
			if(userEmail==null||("").equals(userEmail)) {
				//审核不通过，手机短信通知
				tos = new String[] { userMobile };
				messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
				messageBean.setSubject("【七分钱支付】服务商审核未通过");// 标题
				messageBean.setContent("【七分钱支付】您的服务商注册未能通过审核，原因是：" + AuditInfo.getAuditInfo() +"请前往服务商网站修改服务商信息，如有任何问题，请拨打400-633-0707。");
			
			}else {
				//审核不通过，邮件通知
				tos = new String[] {userEmail};
				content = "<html><body><div style=\"width:700px;margin:0 auto;\">"
						+ "<div style=\"margin-bottom:10px;\">"
						+ "</div><div style=\"border-top: 1px solid #ccc; margin-top: 20px;\"></div>"
						+ "<div style=\"padding:20px 10px 60px;\"><div style=\"line-height:1.5;color:#4d4d4d;\">"
						+ "<h3 style=\"font-weight:normal;font-size:16px;\">尊敬的" + custInfo.getCustName() + "：您好！</h3>"
						+ "<b style=\"font-size:18px;color:#ff9900\">您的账号为" + userInfo.getEmail() + "</b>"
						+ "审核不通过，具体原因为:"+AuditInfo.getAuditInfo()+"您可以通过 "
						+ "<a href=\"https://www.qifenqian.com\">www.qifenqian.com</a>" + "登录系统，重新提交资料。" + "</p>"
						+ "<p style=\"font-size:14px;margin-top:15px;\">如有疑问，请联系我们</p>"
						+ "<p style=\"font-size:14px;margin-top:15px;\">电话：0755-83026070</p>"
						+ "<p style=\"font-size:14px;margin-top:15px;\">七分钱因您而努力</p>"
						+ "</div></div>	<div style=\"border-bottom: 1px dashed #d8d8d8\"></div>"
						+ "<div style=\"width:700px;margin:0 auto;margin-top:10px;color:#8a8a8a;\">"
						+ "<p>此为系统邮件，请勿回复；Copyright ©2015-2016七分钱（国银证保旗下支付平台）  版权所有</p></div></div></body></html>";
				messageBean.setContent(content);
				messageBean.setSubject("【七分钱支付】服务商审核未通过");// 标题
				messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
			}
		}else if("pass".equals(result)) {
			applyStatus = "00";
			custStatus = "00";
			userStatus = "00";
			
			if(userEmail==null||("").equals(userEmail)) {
				//审核通过，手机短信通知
				tos = new String[] { userMobile };
				messageBean.setMsgType(MessageColumnValues.MsgType.SMS);
				messageBean.setSubject("【七分钱支付】服务商审核已通过");// 标题
				messageBean.setContent("【七分钱支付】您的服务商注册已通过审核，如有任何问题，请拨打400-633-0707。");
				
//				logger.debug("发送短信验证" + messageBean);
			}else {
				tos = new String[] { userEmail };
				content = "<html><body><div style=\"width:700px;margin:0 auto;\">"
						+ "<div style=\"margin-bottom:10px;\">"
						+ "</div><div style=\"border-top: 1px solid #ccc; margin-top: 20px;\"></div>"
						+ "<div style=\"padding:20px 10px 60px;\"><div style=\"line-height:1.5;color:#4d4d4d;\">"
						+ "<h3 style=\"font-weight:normal;font-size:16px;\">尊敬的" + custInfo.getCustName() + "：您好！</h3>"
						+ "<b style=\"font-size:18px;color:#ff9900\">您的账号为" + userInfo.getEmail() + "</b>"
						+ "已经审核通过,您可以通过 "
						+ "<a href=\"https://www.qifenqian.com\">www.qifenqian.com</a>" + " 登录系统。" + "</p>"
						+ "<p style=\"font-size:14px;margin-top:15px;\">如有疑问，请联系我们</p>"
						+ "<p style=\"font-size:14px;margin-top:15px;\">电话：0755-83026070</p>"
						+ "<p style=\"font-size:14px;margin-top:15px;\">七分钱因您而努力</p>"
						+ "</div></div>	<div style=\"border-bottom: 1px dashed #d8d8d8\"></div>"
						+ "<div style=\"width:700px;margin:0 auto;margin-top:10px;color:#8a8a8a;\">"
						+ "<p>此为系统邮件，请勿回复；Copyright ©2015-2016七分钱（国银证保旗下支付平台）  版权所有</p></div></div></body></html>";
				messageBean.setContent(content);
				messageBean.setSubject("七分钱--亲爱的" + custInfo.getCustName() + "，你的七分钱服务商账号已经审核通过，欢迎登录！");// 标题
				messageBean.setMsgType(MessageColumnValues.MsgType.EMAIL);
				logger.debug("发送邮箱验证");
			}
		}
		registAuditDao.updateStatusForAuditResult(applyStatus, custStatus, userStatus, merchantId);
		registAuditDao.deleteAuditInfo(AuditInfo);
		registAuditDao.insertAuditInfo(AuditInfo);
		
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
		return "success";
	}
	
	
	public TbBankCode selBankByBankCode(String bankCode) {	
		TbBankCode bank = null;
		try {
			bank = registAuditDao.selectBankByBankCode(bankCode);
		} catch (Exception e) {
			logger.error("查询银行信息异常：" + e.getMessage(), e);		
		}	
		return bank;
	}

	/**
	 * 商户信息-查询授权产品
	 * @param merchantCode
	 * @return
	 */
	public List<MerchantRegisterInfo> queryMerchantProduct(String merchantCode) {
		
		return registAuditDao.queryMerchantProduct(merchantCode);
	}
}
