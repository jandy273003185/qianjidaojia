package com.qifenqian.bms.merchant.subAccount.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.merchant.subAccount.bean.MerchantSubAccouontBean;
import com.qifenqian.bms.merchant.subAccount.dao.MerchantSubAccountDao;
import com.qifenqian.jellyfish.api.merchantsettle.IAlipayMerchantSettleService;
import com.qifenqian.jellyfish.api.merchantsettle.WxpayMerchantSettleService;
import com.qifenqian.jellyfish.bean.enums.BusinessStatus;
import com.qifenqian.jellyfish.bean.merchantsettle.alipay.AlipayRoyaltyRelationBindReq;
import com.qifenqian.jellyfish.bean.merchantsettle.alipay.AlipayRoyaltyRelationBindRes;
import com.qifenqian.jellyfish.bean.merchantsettle.alipay.AlipayRoyaltyRelationUnbindReq;
import com.qifenqian.jellyfish.bean.merchantsettle.alipay.AlipayRoyaltyRelationUnbindRes;
import com.qifenqian.jellyfish.bean.merchantsettle.alipay.RoyaltyEntity;
import com.qifenqian.jellyfish.bean.merchantsettle.weixin.Receiver;
import com.qifenqian.jellyfish.bean.merchantsettle.weixin.WxAddReceiverReq;
import com.qifenqian.jellyfish.bean.merchantsettle.weixin.WxAddReceiverResp;
import com.qifenqian.jellyfish.bean.merchantsettle.weixin.WxRemoveReceiverReq;
import com.qifenqian.jellyfish.bean.merchantsettle.weixin.WxRemoveReceiverResp;


@Service
public class MerchantSubAccountService {
	
	private Logger logger = LoggerFactory.getLogger(MerchantSubAccountService.class);

	@Autowired
	private MerchantSubAccountDao merchantSubAccountDao;
	
	@Reference(version="${dubbo.provider.jellyfish.version}")
	private WxpayMerchantSettleService wxpayMerchantSettleService;
	
	@Reference(version="${dubbo.provider.jellyfish.version}")
	private IAlipayMerchantSettleService iAlipayMerchantSettleService;
	
	/**
	 * 查询商户分账
	 * @param merchantSubAccouontBean
	 * @return
	 */
	public List<MerchantSubAccouontBean> selectSubAccountList(MerchantSubAccouontBean merchantSubAccouontBean) {
		//merchantSubAccouontBean.setStatus("00");
		return merchantSubAccountDao.selectSubAccountList(merchantSubAccouontBean);
	}
	
	/**
	 * 停用商户分账方
	 * @param merchantSubAccouont
	 * @return
	 */
	public Map<String, String> disableSubAccount(MerchantSubAccouontBean merchantSubAccouont) {
		Map<String, String> result = new HashMap<String, String>();
		MerchantSubAccouontBean disableBean = merchantSubAccountDao.getSubAccountById(merchantSubAccouont.getRelationId());
		try {
			String resultCode = null;
			if ("WX".equals(disableBean.getChannelCode())) {
				WxRemoveReceiverResp removeReceiver = removeReceiver(disableBean);
				resultCode = BusinessStatus.SUCCESS.equals(removeReceiver.getSubCode()) ? "SUCCESS" : "FAIL";
			}
			else if ("ALIPAY".equals(disableBean.getChannelCode())) {
				AlipayRoyaltyRelationUnbindRes royaltyRelationUnbind = royaltyRelationUnbind(disableBean);
				resultCode = royaltyRelationUnbind.getResultCode();
			}
			//渠道操作成功
			if ("SUCCESS".equals(resultCode)) {
				disableBean.setStatus("0");
				merchantSubAccountDao.updateSubAccount(disableBean);
				result.put("result", "SUCCESS");
				result.put("message", "停用成功");
			} else {
				result.put("result", "FAILURE");
				result.put("message", "停用失败");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("停用商户分账方异常信息：{}", e.getMessage());
			result.put("result", "FAILURE");
			result.put("message", "停用失败");
		}
		return result;
	}

	/**
	 * 商户分账新增
	 * @return
	 */
	public Map<String, String> insterSubAccount(MerchantSubAccouontBean merchantSubAccouont) {
		Map<String, String> result = new HashMap<String, String>();
		//提交待审核
		merchantSubAccouont.setReportStatus("99");
		merchantSubAccouont.setStatus("0");
		String relationId = GenSN.getSN();
		merchantSubAccouont.setRelationId(relationId);
		//外部请求号
		String outRequestNo = GenSN.getSN();
		merchantSubAccouont.setOutRequestNo(outRequestNo);
		int i = merchantSubAccountDao.insterSubAccount(merchantSubAccouont);
		if (i > 0) {
			//更新表数据
			MerchantSubAccouontBean updateAccouontBean = new MerchantSubAccouontBean();
			updateAccouontBean.setRelationId(relationId);
			try {
				String resultCode = null;
				if ("WX".equals(merchantSubAccouont.getChannelCode())) {
					WxAddReceiverResp addReceiver = addReceiver(merchantSubAccouont);
					resultCode = BusinessStatus.SUCCESS.equals(addReceiver.getSubCode()) ? "SUCCESS" : "FAIL";
				}
				else if ("ALIPAY".equals(merchantSubAccouont.getChannelCode())) {
					AlipayRoyaltyRelationBindRes royaltyRelationBind = royaltyRelationBind(merchantSubAccouont);
					resultCode = royaltyRelationBind.getResultCode();
				}
				
				if ("SUCCESS".equals(resultCode)) {
					updateAccouontBean.setReportStatus("1");
					updateAccouontBean.setStatus("1");
				} else {
					updateAccouontBean.setReportStatus("0");
					updateAccouontBean.setStatus("0");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				logger.error("新增商户分账方异常信息：{}", e.getMessage());
				//渠道新增失败
				updateAccouontBean.setReportStatus("0");
				updateAccouontBean.setStatus("0");
			}
			merchantSubAccountDao.updateSubAccount(updateAccouontBean);
			result.put("result", "SUCCESS");
			result.put("message", "新增成功");
		} else {
			result.put("result", "FAILURE");
			result.put("message", "新增失败");
		}
		return result;
	}
	
	/**
	 * 微信添加分账接收方
	 * @param merchantSubAccouont
	 * @return
	 * @throws Exception
	 */
	private WxAddReceiverResp addReceiver(MerchantSubAccouontBean merchantSubAccouont) throws Exception {
		WxAddReceiverReq req = new WxAddReceiverReq();
		//微信分配的子商户号
		req.setSubMchId(merchantSubAccouont.getOutMerchantCode());
		Receiver receiver = new Receiver();
		//分账接收方类型
		receiver.setType(merchantSubAccouont.getSubAccountType());
		//分账接收方帐号
		receiver.setAccount(merchantSubAccouont.getAccount());
		//分账接收方全称
		if (StringUtils.isNotBlank(merchantSubAccouont.getAccountName())) {
			receiver.setName(merchantSubAccouont.getAccountName());
		}
		//与分账方的关系类型
		receiver.setRelationType(merchantSubAccouont.getRelationType());
		//自定义的分账关系
		if (StringUtils.isNotBlank(merchantSubAccouont.getCustomRelation())) {
			receiver.setCustomRelation(merchantSubAccouont.getCustomRelation());
		}
		req.setReceiver(receiver);
		logger.info("微信添加商户分账方请求报文：{}", JSONObject.toJSONString(req));
		WxAddReceiverResp addReceiver = wxpayMerchantSettleService.addReceiver(req);
		logger.info("微信添加商户分账方响应报文：{}", JSONObject.toJSONString(addReceiver));
		return addReceiver;
	};
	
	/**
	 * 微信删除分账接收方
	 * @return
	 * @throws Exception 
	 */
	private WxRemoveReceiverResp removeReceiver(MerchantSubAccouontBean merchantSubAccouont) throws Exception {
		WxRemoveReceiverReq req = new WxRemoveReceiverReq();
		req.setSubMchId(merchantSubAccouont.getOutMerchantCode());
		Map<String, String> receiver = new HashMap<String, String>();
		//分账接收方类型
		receiver.put("type", merchantSubAccouont.getSubAccountType());
		//分账接收方帐号
		receiver.put("account", merchantSubAccouont.getAccount());
		req.setReceiver(JSONObject.toJSONString(receiver));
		logger.info("微信删除商户分账方请求报文：{}", JSONObject.toJSONString(req));
		WxRemoveReceiverResp removeReceiver = wxpayMerchantSettleService.removeReceiver(req);
		logger.info("微信删除商户分账方响应报文：{}", JSONObject.toJSONString(removeReceiver));
		return removeReceiver;
	}
	
	/**
	 * 支付宝分账关系绑定
	 * @param merchantSubAccouont
	 * @return
	 * @throws Exception
	 */
	private AlipayRoyaltyRelationBindRes royaltyRelationBind(MerchantSubAccouontBean merchantSubAccouont) throws Exception {
		AlipayRoyaltyRelationBindReq request = new AlipayRoyaltyRelationBindReq();
		request.setOutRequestNo(merchantSubAccouont.getOutRequestNo());
		List<RoyaltyEntity> receiverList = new ArrayList<RoyaltyEntity>();
		//分账接收方
		RoyaltyEntity royaltyEntity = new RoyaltyEntity();
		royaltyEntity.setType(merchantSubAccouont.getSubAccountType());
		royaltyEntity.setAccount(merchantSubAccouont.getAccount());
		royaltyEntity.setName(merchantSubAccouont.getAccountName());
		if (StringUtils.isNotBlank(merchantSubAccouont.getDesc())) {
			royaltyEntity.setMemo(merchantSubAccouont.getDesc());
		}
		receiverList.add(royaltyEntity);
		request.setReceiverList(receiverList);
		logger.info("支付宝分账关系绑定请求报文：{}", JSONObject.toJSONString(request));
		AlipayRoyaltyRelationBindRes royaltyRelationBind = iAlipayMerchantSettleService.royaltyRelationBind(request);
		logger.info("支付宝分账关系绑定响应报文：{}", JSONObject.toJSONString(royaltyRelationBind));
		return royaltyRelationBind;
	}
	
	/**
	 * 支付宝分账关系解绑
	 * @param merchantSubAccouont
	 * @return
	 */
	private AlipayRoyaltyRelationUnbindRes royaltyRelationUnbind(MerchantSubAccouontBean merchantSubAccouont) throws Exception {
		AlipayRoyaltyRelationUnbindReq request = new AlipayRoyaltyRelationUnbindReq();
		List<RoyaltyEntity> receiverList = new ArrayList<RoyaltyEntity>();
		RoyaltyEntity reEntity= new RoyaltyEntity();
		reEntity.setAccount(merchantSubAccouont.getAccount());
		reEntity.setType(merchantSubAccouont.getSubAccountType());
		reEntity.setName(merchantSubAccouont.getAccountName());
		receiverList.add(reEntity);
		request.setReceiverList(receiverList);
		request.setOutRequestNo(merchantSubAccouont.getOutRequestNo());
		logger.info("支付宝分账关系解绑请求报文：{}", JSONObject.toJSONString(request));
		AlipayRoyaltyRelationUnbindRes royaltyRelationUnbind = iAlipayMerchantSettleService.royaltyRelationUnbind(request);
		logger.info("支付宝分账关系解绑响应报文：{}", JSONObject.toJSONString(royaltyRelationUnbind));
		return royaltyRelationUnbind;
	}

}
