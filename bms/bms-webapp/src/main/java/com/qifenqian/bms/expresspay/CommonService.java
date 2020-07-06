package com.qifenqian.bms.expresspay;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.mapper.TdLoginUserInfoMapper;
import com.qifenqian.bms.expresspay.cardholderInfo.bean.Cardholder;
import com.qifenqian.bms.expresspay.mpper.CommonMapper;
import com.qifenqian.bms.expresspay.mpper.JgkjCardMapper;
import com.qifenqian.bms.platform.common.utils.SpringUtils;
import com.sevenpay.gateway.k3cloud.IK3Cloud;
import com.sevenpay.invoke.SevenpayCoreServiceInterface;
import com.sevenpay.plugin.IPlugin;
import com.stc.gateway.unionpay.IUnionPay;

@Service
public class CommonService {
	public static Logger logger = LoggerFactory.getLogger(CommonService.class);
	@Autowired
	private TdLoginUserInfoMapper tdLoginUserInfoMapper;

	@Autowired
	private CommonMapper commonMapper;

	@Autowired
	private JgkjCardMapper jgkjCardMapper;

	/**
	 * 用户登陆信息
	 * 
	 * @param email
	 * @return
	 */
	public TdLoginUserInfo getTdLoginUserInfo(String phone) {

		if (StringUtils.isEmpty(phone)) {

			throw new IllegalArgumentException("用户账号为空");
		}

		return tdLoginUserInfoMapper.selectByPhone(phone,null);
	}

	/**
	 * 调用核心
	 * 
	 * @return
	 */
	public SevenpayCoreServiceInterface getSevenpayCoreServiceInterface() {
		SevenpayCoreServiceInterface interfaceService = SpringUtils
				.getBean(SevenpayCoreServiceInterface.class);
		return interfaceService;
	}

	/**
	 * 调用银联接口
	 * 
	 * @return
	 */
	public IUnionPay getIUnionPay() {
		IUnionPay interfaceService = (IUnionPay) SpringUtils.getBean("httpInvokerProxyGatewayUnionPay");
		return interfaceService;
	}

	/**
	 * 调用金蝶接口
	 * 
	 * @return
	 */
	public IK3Cloud getIKingdeePay() {
		IK3Cloud interfaceService = (IK3Cloud) SpringUtils.getBean("httpInvokerProxyGatewayKingdeePay");
		return interfaceService;
	}

	/***
	 * 调用调度接口
	 * @return
	 */
	public IPlugin getIPlugin() {
		IPlugin interfaceService = (IPlugin) SpringUtils.getBean("pluginInvokerProxy");
		return interfaceService;
	}

	/**
	 * 根据客户号查询交广科技卡号
	 * 
	 * @param custId
	 * @return
	 */
	public String selectCardNo(String custId) {
		return jgkjCardMapper.selectCardNo(custId);
	}

	/**
	 * 
	 * @param mobile
	 * @return
	 */
	public String getCustId(Cardholder queryBean) {
		return commonMapper.getCustId(queryBean);
	}
}
