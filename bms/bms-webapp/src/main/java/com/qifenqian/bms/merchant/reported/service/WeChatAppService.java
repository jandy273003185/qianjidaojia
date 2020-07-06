package com.qifenqian.bms.merchant.reported.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.merchant.reported.bean.City;
import com.qifenqian.bms.merchant.reported.bean.CrInComeBean;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantBankInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoWeChat;
import com.qifenqian.bms.merchant.reported.bean.WeChatAppAreaInfo;
import com.qifenqian.bms.merchant.reported.bean.WeChatAppBean;
import com.qifenqian.bms.merchant.reported.bean.WeChatAppModifyBean;
import com.qifenqian.bms.merchant.reported.dao.WeChatAppMapperDao;
import com.qifenqian.bms.merchant.reported.mapper.WeChatAppMapper;
import com.qifenqian.jellyfish.api.merregist.WxpayAgentMerRegistService;
import com.qifenqian.jellyfish.bean.enums.BusinessStatus;
import com.qifenqian.jellyfish.bean.enums.GetwayStatus;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgentMerRegistReq;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgentMerRegistResp;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgentMerRegistUpgradeReq;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgentMerRegistUpgradeResp;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgrntMerRegistQueryReq;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgrntMerRegistQueryResp;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgrntMerRegistUpgradeQueryReq;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinAgrntMerRegistUpgradeQueryResp;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinModifyContactinfoReq;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinModifyContactinfoResp;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinModifySettlementCardReq;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinModifySettlementCardResp;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinReintroduceWithdrawalsReq;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinReintroduceWithdrawalsResp;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinWithdrawalStateQueryReq;
import com.qifenqian.jellyfish.bean.merregist.weixin.WeiXinWithdrawalStateQueryResp;
import com.seven.micropay.channel.domain.UpgradeApplicatioMerchanelReq;

@Service
public class WeChatAppService {
	
	private Logger logger = LoggerFactory.getLogger(WeChatAppService.class);
	
	@Autowired
	private WeChatAppMapperDao weChatAppMapperDao;
	
	@Autowired
	private WeChatAppMapper weChatAppMapper;
	
	@Autowired
	private FmIncomeService fmIncomeService;
	
	@Reference(version="${dubbo.provider.jellyfish.version}")
	private WxpayAgentMerRegistService wxpayAgentMerRegistService;
	
	
	/**
	 * 微信报备
	 * @param cr
	 * @return
	 * @throws Exception 
	 */
	public Map<String, Object> weChatAppReported(WeChatAppBean cr) throws Exception {
		logger.info("微信进件报备start..");
		Map<String, Object> result = new HashMap<String, Object>();
		//添加商户报备详情表（td_merchant_detail_info）和商户报备表（td_merchant_report）
		TdMerchantDetailInfo info = new TdMerchantDetailInfo();
		info.setId(GenSN.getSN());
		String patchNo = GenSN.getSN();
		info.setPatchNo(patchNo);
		info.setMerchantCode(cr.getMerchantCode().trim());
		info.setChannelNo(cr.getChannelNo());
		info.setReportStatus("E");
		info.setDetailStatus("99");
		//info.setProvCode(cr.getMerchantProvince());
		//info.setCityCode(cr.getMerchantCity());
		//info.setContryCode(cr.getMerchantArea());
		//info.setBankCode(cr.getBank());
		//结算账户
		//info.setAccountNumber(cr.getAccountNo());
		//info.setBranchBankName(cr.getInterBankName());
		//info.setMobileNo(cr.getMobileNo());
		logger.debug("插入td_merchant_report表数据：{}", JSONObject.toJSONString(info));
		fmIncomeService.insertTdMerchantReport(info);
		//微信报备明细
		TdMerchantDetailInfoWeChat detailInfoWeChat = new TdMerchantDetailInfoWeChat();
		BeanUtils.copyProperties(cr, detailInfoWeChat);
		detailInfoWeChat.setPatchNo(patchNo);
		detailInfoWeChat.setReportStatus("99");
		logger.debug("插入td_merchant_detail_info_wechat表数据：{}", JSONObject.toJSONString(detailInfoWeChat));
		weChatAppMapper.insertTdMerchantDetailInfoWechat(detailInfoWeChat);
		
		
		//微信报备
		WeiXinAgentMerRegistReq req = new WeiXinAgentMerRegistReq();
		//业务申请编号
		req.setBusinessCode(patchNo);
		// 身份证人像面照片
		req.setIdCardCopy(cr.getLegalCertAttribute1Path());
		// 身份证国徽面
		req.setIdCardNational(cr.getLegalCertAttribute2Path());
		// 门店门口照片
		req.setStoreEntrancePic(cr.getDoorPhotoPath());
		// 店内环境照片
		req.setIndoorPic(cr.getShopInteriorPath());
		// 身份证姓名
		req.setIdCardName(cr.getRepresentativeName());
		// 身份证号码
		req.setIdCardNumber(cr.getRepresentativeCertNo());
		// 身份证有效期限
		String idCardValidTime = "[\"" + cr.getIdentityEffDate() + "\",\"" + ("2099-12-31".equals(cr.getIdentityValDate()) ? "长期" : cr.getIdentityValDate()) + "\"]";
		req.setIdCardValidTime(idCardValidTime);
		// 开户名称
		req.setAccountName(cr.getAccountNm());
		// 开户银行
		req.setAccountBank(cr.getBank());
		// 开户银行省市编码
		req.setBankAddressCode(cr.getBankCity());
		// 银行账号
		req.setAccountNumber(cr.getAccountNo());
		// 门店名称
		req.setStoreName(cr.getStoreName());
		// 门店省市编码
		req.setStoreAddressCode(cr.getMerchantArea());
		// 门店街道名称
		req.setStoreStreet(cr.getCprRegAddr());
		// 商户简称
		req.setMerchantShortname(cr.getCustName());
		// 客服电话
		req.setServicePhone(cr.getContactPhone());
		// 售卖商品/提供服务描述
		req.setProductDesc(cr.getIndustryCode());
		// 费率
		req.setRate(cr.getRate());
		// 超级管理员姓名
		req.setContact(cr.getRepresentativeName());
		// 手机号码
		req.setContactPhone(cr.getMobileNo());
		// 管理员邮箱
		req.setContactEmail(cr.getEmail());
		
		logger.info("-----------------微信进件请求报文：" + JSONObject.toJSONString(req));
		WeiXinAgentMerRegistResp wxregResp = wxpayAgentMerRegistService.microMerRegist(req);
		logger.info("-----------------微信进件响应报文：" + JSONObject.toJSONString(wxregResp));
	    if(BusinessStatus.SUCCESS.equals(wxregResp.getSubCode())) {
    		info.setReportStatus("O");
    		info.setDetailStatus("00");
    		detailInfoWeChat.setReportStatus("00");
            //info.setFileStatus("Y");
    		detailInfoWeChat.setApplymentId(wxregResp.getApplymentId());
            result.put("data", wxregResp);
			result.put("message", "报备成功");
			result.put("result", "SUCCESS");
	    } else {
           logger.error("微信进件明确失败：{}", wxregResp.getSubMsg());
           info.setResultMsg(wxregResp.getSubMsg());
           info.setDetailStatus("99");
           detailInfoWeChat.setResultMsg(wxregResp.getSubMsg());
		   detailInfoWeChat.setReportStatus("99");
		   result.put("message", wxregResp.getSubMsg());
		   result.put("result", "FAIL");
	    }
	    logger.debug("更新td_merchant_report和td_merchant_detail_info表数据：{}", JSONObject.toJSONString(info));
	    weChatAppMapper.updateTdMerchantReport(info);
	    weChatAppMapper.updateTdMerchantDetailInfoWechat(detailInfoWeChat);
	    //UpdateMerReportAndMerDetailInfo(info, reportState);
	    logger.info("微信进件报备end..");
		return result; 
		
	}
	
	
	
	public WeiXinAgrntMerRegistQueryResp microMerRegistQuery(String applymentId) throws Exception {
		WeiXinAgrntMerRegistQueryReq registQueryReq = new WeiXinAgrntMerRegistQueryReq();
		registQueryReq.setApplymentId(applymentId);
		registQueryReq.setBusinessCode("123");
		logger.info("查询微信进件申请单入参：{}", registQueryReq);
		WeiXinAgrntMerRegistQueryResp microMerRegistQuery = wxpayAgentMerRegistService.microMerRegistQuery(registQueryReq);
		logger.info("查询微信进件申请单返回值：{}", microMerRegistQuery);
		return microMerRegistQuery;
	}
	
	public TdMerchantDetailInfo selTdMerchantReport(CrInComeBean crInComeBean) {
		return weChatAppMapper.selTdMerchantReport(crInComeBean);
	}

	/**
	 * 查询微信省份
	 * @return
	 */
	public List<WeChatAppAreaInfo> getProvinceName() {
		return weChatAppMapperDao.getProvinceName();
	}
	
	/**
	 * 查微信区域信息
	 * @param areaName
	 * @return
	 */
	public WeChatAppAreaInfo selectWxAreaInfo(String areaName) {
		return weChatAppMapperDao.selectWxAreaInfo(areaName);
	}
	
	/**
	 * 查询商户银行账号信息
	 * @param custId
	 * @return
	 */
	public TdMerchantBankInfo getMerchantBankInfo(String custId) {
		return weChatAppMapperDao.getMerchantBankInfo(custId);
	}
	
	public City getTbSpCity(String cityId) {
		return weChatAppMapperDao.getTbSpCity(cityId);
	}

	/**
	 * 微信升级报备
	 * @param cr
	 * @return
	 */
	public Map<String, Object> weChatAppUpgradeReported(WeChatAppBean cr) {
		
		logger.info("-----------------微信升级进件开始-----------------");
		
		Map<String, Object> result = new HashMap<String, Object>();
		WeiXinAgentMerRegistUpgradeReq req = new WeiXinAgentMerRegistUpgradeReq();
		TdMerchantDetailInfo info = new TdMerchantDetailInfo();
		try {
			//添加商户报备详情表（td_merchant_detail_info）和商户报备表（td_merchant_report）
			info.setId(GenSN.getSN()); 
			String patchNo = GenSN.getSN();
			info.setPatchNo(patchNo); 
			info.setMerchantCode(cr.getMerchantCode().trim());
			info.setChannelNo(cr.getChannelNo()); 
			info.setReportStatus("E");
			info.setDetailStatus("99");
			//info.setProvCode(cr.getMerchantProvince());
			//info.setCityCode(cr.getMerchantCity());
			//info.setContryCode(cr.getMerchantArea());
			//info.setBranchBankName(cr.getInterBankName());
			//info.setMobileNo(cr.getMobileNo());
			logger.debug("插入td_merchant_report表数据：{}", JSONObject.toJSONString(info));
			fmIncomeService.insertTdMerchantReport(info); 
			//微信报备明细
			TdMerchantDetailInfoWeChat detailInfoWeChat = new TdMerchantDetailInfoWeChat();
			BeanUtils.copyProperties(cr, detailInfoWeChat);
			detailInfoWeChat.setEmail(cr.getMerchantEmail());
			detailInfoWeChat.setAccountNm(cr.getActNm());
			detailInfoWeChat.setAccountNo(cr.getBankCardNo());
			detailInfoWeChat.setBank(cr.getWeChatBank());
			
			detailInfoWeChat.setReportStatus("99");
			detailInfoWeChat.setRemark("weChatUpgrade");//备注值为weChatUpgrade则是微信升级信息
			logger.debug("插入td_merchant_detail_info_wechat表数据：{}", JSONObject.toJSONString(info));
			weChatAppMapper.insertTdMerchantDetailInfoWechat(detailInfoWeChat);
			 
			String businessTermTime = "[\"" + cr.getBusinessEffectiveTerm() + "\",\"" + ("2099-12-31".equals(cr.getBusinessTerm()) ? "长期" : cr.getBusinessTerm()) + "\"]";
			String businessSceneList = "[" +cr.getBusinessScene()+ "]";
			req.setSubMchId(cr.getOutMerchantCode());       		         //小微商户的商户号
			req.setOrganizationType(cr.getMerchantProperty());               //主体类型
			req.setBusinessLicenseCopy(cr.getBusinessPhotoPath());		     //营业执照扫描件
			req.setBusinessLicenseNumber(cr.getBusinessLicense());           //营业执照注册号
			req.setMerchantName(cr.getCustName());                           //商户名称(营业执照上的商户名称)
			req.setCompanyAddress(cr.getCprRegAddr());                       //注册地址
			req.setLegalPerson(cr.getRepresentativeName());                  //经营者姓名/法定代表人(营业执照上的经营者/法人姓名)
			req.setBusinessTime(businessTermTime);                           //营业期限
			req.setBusinessLicenceType("1762");                              //营业执照类型
			req.setAccountName(cr.getAccountNm());                           //开户名称
			req.setAccountBank(cr.getBank());                                //开户银行
			req.setBankAddressCode(cr.getBankCity());                        //开户银行省市编码
			req.setBankName(cr.getInterBankName());                          //开户银行全称（含支行）
			req.setAccountNumber(cr.getAccountNo());                         //银行卡号
			req.setMerchantShortname(cr.getShortName());                     //商户简称
			req.setBusiness(cr.getRateId());                                 //费率结算规则ID
			req.setBusinessScene(businessSceneList);                         //经营场景
			req.setContactEmail(cr.getAttentionEmail());                     //联系邮箱
			req.setMpAppid(cr.getMpAppid());                                 //公众号APPID
			req.setMpAppScreenShots(cr.getMpAppScreenShotsPath());           //公众号页面截图
			req.setMiniprogramAppid(cr.getMiniprogramAppid());               //小程序APPID
			req.setMiniprogramScreenShots(cr.getMiniprogramAppidPath());     //小程序页面截图
			req.setAppAppid(cr.getAppAppid());                               //应用APPID
			req.setAppScreenShots(cr.getAppAppidPath());                     //APP截图
			req.setAppDownloadUrl(cr.getAppDownloadUrl());                   //APP下载衔接
			req.setWebUrl(cr.getWebUrl());                                   //PC网站域名
			req.setWebAuthoriationLetter(cr.getWebUrlPath());                //网站授权函
			req.setWebAppid(cr.getWebAppid());                               //PC网站对应的公众号APPID
			
			logger.info("-----------------微信升级进件请求报文：" + JSONObject.toJSONString(req));
			WeiXinAgentMerRegistUpgradeResp wxregUpgradeResp = wxpayAgentMerRegistService.microMerRegistUpgrade(req);
			logger.info("-----------------微信升级进件响应报文：" + JSONObject.toJSONString(wxregUpgradeResp));
			
		    if(BusinessStatus.SUCCESS.equals(wxregUpgradeResp.getSubCode())) {
	    		info.setReportStatus("O");
	    		info.setDetailStatus("00");
	    		detailInfoWeChat.setReportStatus("00");
	            //info.setFileStatus("Y");
	            result.put("data", wxregUpgradeResp);
				result.put("message", "报备成功");
				result.put("result", "SUCCESS");
		    } else {
	           logger.error("微信进件明确失败：{}", wxregUpgradeResp.getSubMsg());
	           info.setResultMsg(wxregUpgradeResp.getSubMsg());
	           info.setDetailStatus("99");
	           detailInfoWeChat.setResultMsg(wxregUpgradeResp.getSubMsg());
			   detailInfoWeChat.setReportStatus("99");
			   result.put("message", wxregUpgradeResp.getSubMsg());
			   result.put("result", "FAIL");
		    }
		    logger.debug("更新td_merchant_report和td_merchant_detail_info表数据：{}", JSONObject.toJSONString(info));
		    weChatAppMapper.updateTdMerchantReport(info);
		    weChatAppMapper.updateTdMerchantDetailInfoWechat(detailInfoWeChat);
		    //UpdateMerReportAndMerDetailInfo(info, reportState);
		    logger.info("-----------------微信升级进件结束-----------------");
		} catch (Exception e) {
			result.put("message", e);
			result.put("result", "FAIL");
		}
		return result;
	}
	
	/**
	 * 升级查询
	 * @param applymentId
	 * @return
	 * @throws Exception
	 */
	public WeiXinAgrntMerRegistUpgradeQueryResp microMerRegistUpgradeQuery(String subMchId) throws Exception {
		WeiXinAgrntMerRegistUpgradeQueryReq registUpgradeQueryReq = new WeiXinAgrntMerRegistUpgradeQueryReq();
		registUpgradeQueryReq.setSubMchId(subMchId);
		logger.info("查询微信进件申请单入参：{}", registUpgradeQueryReq);
		WeiXinAgrntMerRegistUpgradeQueryResp microMerRegistQuery = wxpayAgentMerRegistService.microMerRegistUpgradeQuery(registUpgradeQueryReq);
		logger.info("查询微信进件申请单返回值：{}", microMerRegistQuery);
		return microMerRegistQuery;
	}
	/**
	 * 微信查询结算账户
	 * @param cr
	 * @return
	 */
	public JSONObject querySettlement(WeChatAppBean cr) {
		JSONObject object = new JSONObject();
		UpgradeApplicatioMerchanelReq req = new UpgradeApplicatioMerchanelReq();
		try {
			req.setMch_id("");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return object;
	}
	
	/**
	 * 微信修改结算账户
	 * @param cr
	 * @return
	 * @throws Exception 
	 */
	public Map<String, Object> modifySettlement(WeChatAppModifyBean modifyBean) {
		Map<String, Object> result = new HashMap<String, Object>();
		WeiXinModifySettlementCardReq req = new WeiXinModifySettlementCardReq();
		req.setSubMchId(modifyBean.getSubMchId());
		if (StringUtils.isNotBlank(modifyBean.getAccountNo())) {
			req.setAccountNumber(modifyBean.getAccountNo());
		}
		if (StringUtils.isNotBlank(modifyBean.getInterBankName())) {
			req.setBankName(modifyBean.getInterBankName());
		}
		if (StringUtils.isNotBlank(modifyBean.getBank())) {
			req.setAccountBank(modifyBean.getBank());
		}
		req.setBankAddressCode(modifyBean.getBankCity());
		
		try {
			logger.info("微信修改结算信息请求信息：{}", JSONObject.toJSONString(req));
			WeiXinModifySettlementCardResp modifySettlementCard = wxpayAgentMerRegistService.modifySettlementCard(req);
			logger.info("微信修改结算信息响应信息：{}", JSONObject.toJSONString(modifySettlementCard));
			if (BusinessStatus.SUCCESS.equals(modifySettlementCard.getSubCode())) {
				//更新
				TdMerchantDetailInfo merchantDetailInfo = new TdMerchantDetailInfo();
				merchantDetailInfo.setPatchNo(modifyBean.getPatchNo());
				merchantDetailInfo.setBankCode(modifyBean.getBank());
				merchantDetailInfo.setAccountNumber(modifyBean.getAccountNo());
				merchantDetailInfo.setBranchBankName(modifyBean.getInterBankName());
				weChatAppMapper.updateTdMerchantDetailInfo(merchantDetailInfo);
				
				result.put("code", "SUCCESS");
				result.put("message", "修改结算信息成功");
			} else {
				result.put("code", "FAIL");
				result.put("message", "修改结算信息失败：" + modifySettlementCard.getSubMsg());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("修改结算信息异常：{}", e.getMessage());
			result.put("code", "FAIL");
			result.put("message", "修改结算信息异常");
		}
		
		return result;
	}
	
	/**
	 * 修改微信联系信息
	 * @param modifyBean
	 * @return
	 */
	public Map<String, Object> modifyContact(WeChatAppModifyBean modifyBean) {
		Map<String, Object> result = new HashMap<String, Object>();
		WeiXinModifyContactinfoReq req = new WeiXinModifyContactinfoReq();
		req.setSubMchId(modifyBean.getSubMchId());
		if (StringUtils.isNotBlank(modifyBean.getMobileNo())) {
			req.setMobilePhone(modifyBean.getMobileNo());
		}
		if (StringUtils.isNotBlank(modifyBean.getEmail())) {
			req.setEmail(modifyBean.getEmail());
		}
		if (StringUtils.isNotBlank(modifyBean.getShortName())) {
			req.setMerchantName(modifyBean.getShortName());
		}
		try {
			logger.info("微信修改联系信息请求体：{}", JSONObject.toJSONString(req));
			WeiXinModifyContactinfoResp modifyContactinfoResp = wxpayAgentMerRegistService.modifyContactinfo(req);
			logger.info("微信修改联系信息响应体：{}", JSONObject.toJSONString(modifyContactinfoResp));
			if (BusinessStatus.SUCCESS.equals(modifyContactinfoResp.getSubCode())) {
				TdMerchantDetailInfo merchantDetailInfo = new TdMerchantDetailInfo();
				merchantDetailInfo.setPatchNo(modifyBean.getPatchNo());
				merchantDetailInfo.setMobileNo(modifyBean.getMobileNo());
				weChatAppMapper.updateTdMerchantDetailInfo(merchantDetailInfo);
				
				result.put("code", "SUCCESS");
				result.put("message", "修改联系信息成功");
			} else {
				result.put("code", "FAIL");
				result.put("message", "修改联系信息失败：" + modifyContactinfoResp.getSubMsg());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error("修改联系信息异常：{}", e.getMessage());
			result.put("code", "FAIL");
			result.put("message", "修改联系信息异常");
		}
		return result;
	}
	
	/**
	 * 查询提现状态
	 * @param weChatBean
	 * @return
	 */
	public Map<String, Object> merchantWithdrawalStateQuery(WeChatAppBean weChatBean) {
		Map<String, Object> weChatResult = new HashMap<String, Object>();
		WeiXinWithdrawalStateQueryReq req = new WeiXinWithdrawalStateQueryReq();
		req.setDate(weChatBean.getDate());
		req.setSubMchId(weChatBean.getOutMerchantCode());
		try {
			logger.info("查询微信提现状态：{}", req);
			WeiXinWithdrawalStateQueryResp weiXinWithdrawalStateQueryResp = wxpayAgentMerRegistService.merchantWithdrawalStateQuery(req);
			logger.info("查询微信提现状态：{}", weiXinWithdrawalStateQueryResp);
			if(GetwayStatus.SUCCESS.equals(weiXinWithdrawalStateQueryResp.getCode())){
				if (BusinessStatus.SUCCESS.equals(weiXinWithdrawalStateQueryResp.getSubCode())) {
					weChatResult.put("message",weiXinWithdrawalStateQueryResp);
					weChatResult.put("result", "SUCCESS");
				}else if(BusinessStatus.FAIL.equals(weiXinWithdrawalStateQueryResp.getSubCode())) {
					weChatResult.put("message",weiXinWithdrawalStateQueryResp.getSubMsg());
					weChatResult.put("result", "FAIL");
				}
			}else if(GetwayStatus.FAIL.equals(weiXinWithdrawalStateQueryResp.getCode())){
				weChatResult.put("message",weiXinWithdrawalStateQueryResp.getMsg());
				weChatResult.put("result", "FAIL");
			}
			
		} catch (Exception e) {
			weChatResult.put("message",e);
			weChatResult.put("result", "FAIL");
		}
		return weChatResult;
	}

	/**
	 * 重新发起提现
	 * @param weChatBean
	 * @return
	 */
	public Map<String, Object> reintroduceWithdrawals(WeChatAppBean weChatBean) {
		Map<String, Object> weChatResult = new HashMap<String, Object>();
		WeiXinReintroduceWithdrawalsReq req = new WeiXinReintroduceWithdrawalsReq();
		req.setDate(weChatBean.getDate());
		req.setSubMchId(weChatBean.getOutMerchantCode());
		try {
			logger.info("重新发起提现状态：{}", req);
			WeiXinReintroduceWithdrawalsResp resp = wxpayAgentMerRegistService.reintroduceWithdrawals(req);
			logger.info("重新发起提现状态：{}", resp);
			if(GetwayStatus.SUCCESS.equals(resp.getCode())){
				if (BusinessStatus.SUCCESS.equals(resp.getSubCode())) {
					weChatResult.put("message",resp);
					weChatResult.put("result", "SUCCESS");
				}else if(BusinessStatus.FAIL.equals(resp.getSubCode())) {
					weChatResult.put("message",resp.getSubMsg());
					weChatResult.put("result", "FAIL");
				}
			}else if(GetwayStatus.FAIL.equals(resp.getCode())){
				weChatResult.put("message",resp.getMsg());
				weChatResult.put("result", "FAIL");
			}
		} catch (Exception e) {
			weChatResult.put("message",e);
			weChatResult.put("result", "FAIL");
		}
		return weChatResult;
	}
	
}
