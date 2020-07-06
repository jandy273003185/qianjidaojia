package com.qifenqian.bms.merchant.merchantReported.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.merchant.service.MerchantService;
import com.qifenqian.bms.basemanager.toPayProduct.bean.ToPayProduct;
import com.qifenqian.bms.basemanager.toPayProduct.mapper.ToPayProductMapper;
import com.qifenqian.bms.merchant.merchantReported.bean.KFTArea;
import com.qifenqian.bms.merchant.merchantReported.bean.KFTCoBean;
import com.qifenqian.bms.merchant.merchantReported.bean.KFTMccBean;
import com.qifenqian.bms.merchant.merchantReported.dao.KftIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.dao.FmIncomeMapperDao;
import com.qifenqian.bms.merchant.reported.mapper.FmIncomeMapper;
import com.qifenqian.bms.merchant.reported.service.FmIncomeService;
import com.seven.micropay.base.domain.ChannelResult;
import com.seven.micropay.channel.domain.merchant.KftCertInfo;
import com.seven.micropay.channel.domain.merchant.KftMerChantDataReq;
import com.seven.micropay.channel.domain.merchant.KftProductFeeInfo;
import com.seven.micropay.channel.domain.merchant.KftSettleBankInfo;
import com.seven.micropay.channel.domain.merchant.KftUploadFileBean;
import com.seven.micropay.channel.enums.ChannelMerRegist;
import com.seven.micropay.channel.service.IMerChantIntoService;

@Service
public class KFTIncomeService {

	private Logger logger = LoggerFactory.getLogger(KFTIncomeService.class);

	@Autowired
	private MerchantService merchantService;
	
	@Autowired
	private FmIncomeMapperDao fmIncomeMapperDao;
	
	@Autowired
	private IMerChantIntoService iMerChantIntoService;
	
	@Autowired
	private FmIncomeMapper fmIncomeMapper;
	
	@Autowired 
	private  FmIncomeService fmIncomeService;
	
	@Autowired
	private KftIncomeMapperDao fktIncomeMapperDao;
	
	@Autowired
	private ToPayProductMapper toPayProductMapper;
	
	public JSONObject merQuery(KFTCoBean cr){
		JSONObject object = new JSONObject();
		Map<String, Object> req = new HashMap<String, Object>();
		
		req.put("merchantId",cr.getOutMerchantCode());
		req.put("merchantProperty",cr.getMerchantProperty());
		if("1".equals(cr.getMerchantProperty())){
			req.put("certNo",cr.getCertifyNo());
		}else{
			req.put("merchantId",cr.getBusinessLicense());
		}
		
		logger.info("快付通查询状态请求报文：" + JSONObject.toJSONString(req));
		ChannelResult channelFileResult = iMerChantIntoService.merQuery(req);
	    logger.info("快付通查询状态响应报文：" + JSONObject.toJSONString(channelFileResult));
	    if("SUCCESS".equals(channelFileResult.getStatus()) && "00".equals(channelFileResult.getReCode())){
	    	//修改报备状态
	    	TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
	    	tdInfo.setMerchantCode(cr.getMerchantCode().trim());
	        tdInfo.setChannelNo(cr.getChannelNo());
	        tdInfo.setReportStatus("1");
	        // 查询商户报备信息
	        TdMerchantDetailInfo tdInfo_ = fmIncomeMapper.selTdMerchantDetailInfo(tdInfo);
	        tdInfo.setPatchNo(tdInfo_.getPatchNo());
	    	fmIncomeMapper.updateTdMerchantDetailInfo(tdInfo);
	    	object.put("result", "SUCCESS");
	    	object.put("message", "快付通进件成功");
	    }else if("PROCESSING".equals(channelFileResult.getStatus())&& "00".equals(channelFileResult.getReCode())){
	    	object.put("result", "PROCESSING");
	    	object.put("message", "快付通进件审核中");
	    }else{
	    	TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
	    	tdInfo.setMerchantCode(cr.getMerchantCode().trim());
	        tdInfo.setChannelNo(cr.getChannelNo());
	        tdInfo.setReportStatus("2");
	        // 查询商户报备信息
	        TdMerchantDetailInfo tdInfo_ = fmIncomeMapper.selTdMerchantDetailInfo(tdInfo);
	        tdInfo.setPatchNo(tdInfo_.getPatchNo());
	    	fmIncomeMapper.updateTdMerchantDetailInfo(tdInfo);
	    	object.put("result", "FAIL");
	    	if(null == channelFileResult.getReMsg()){
	    		object.put("message", "快付通进件失败");
	    	}else{
	    		object.put("message", channelFileResult.getReMsg());
	    	}
	    	
	    }
	    
		return object;
		
	}

	public JSONObject kftReported(KFTCoBean cr,List<KftProductFeeInfo> kftProductFeeInfo) {
		JSONObject object = new JSONObject();
		KftUploadFileBean kftId0Bean = new KftUploadFileBean();
		KftUploadFileBean kftId1Bean = new KftUploadFileBean();
		KftUploadFileBean kftId2Bean = new KftUploadFileBean();
		KftUploadFileBean kftBankCardBean = new KftUploadFileBean();
		KftUploadFileBean kftBankCardBackBean = new KftUploadFileBean();
		KftUploadFileBean kftDoorBean = new KftUploadFileBean();
		KftUploadFileBean kftLicenseBean = new KftUploadFileBean();
		KftUploadFileBean kftOpenBean = new KftUploadFileBean();
		KftUploadFileBean kftCooperateBean = new KftUploadFileBean();
		TdCustInfo custInfo = fmIncomeMapperDao.getInComeInfo(cr.getMerchantCode());
		List<KftUploadFileBean> list = new ArrayList<KftUploadFileBean>();
		
		if("1".equals(cr.getMerchantProperty())){
			
			//获取身份证正反面
			String  identityImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "04");
			String[] paths = null;
			//正面
			String identityImagePath0 = null;
			//反面
			String identityImagePath1 = null;
			if(null != identityImagePath){
				paths = identityImagePath.split(";");
				identityImagePath0 = paths[0];
				identityImagePath1 = paths[1];
			}
			
			/**个人身份证正面照**/
			if(null ==cr.getIdentityImagePath0()){
				kftId0Bean.setFilePath(identityImagePath0);
			}else{
				kftId0Bean.setFilePath(cr.getIdentityImagePath0());
			}
			kftId0Bean.setCertNo("10");
			kftId0Bean.setMerchantType("1");
			kftId0Bean.setNo("1");
			
			/**个人身份证反面照**/
			if(null == cr.getIdentityImagePath1()){
				kftId1Bean.setFilePath(identityImagePath1);
			}else{
				kftId1Bean.setFilePath(cr.getIdentityImagePath1());
			}
			kftId1Bean.setCertNo("11");
			kftId1Bean.setMerchantType("1");
			kftId1Bean.setNo("2");
			
			/**手持身份证正面照**/
			String  identityHandImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "13");
			if(null ==cr.getIdentityHandImagePath()){
				kftId2Bean.setFilePath(identityHandImagePath);
			}else{
				kftId2Bean.setFilePath(cr.getIdentityImagePath0());
			}
			kftId2Bean.setCertNo("12");
			kftId2Bean.setMerchantType("1");
			kftId2Bean.setNo("3");
			
			/**银行卡正面照**/
			String bankCardPath =merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "07");
			if(null ==cr.getBankCardPath()){
				kftBankCardBean.setFilePath(bankCardPath);
			}else{
				kftBankCardBean.setFilePath(cr.getBankCardPath());
			}
			kftBankCardBean.setCertNo("13");
			kftBankCardBean.setMerchantType("1");
			kftBankCardBean.setNo("4");
			
			/**银行卡反面照**/
			String bankCardBackPath =merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "14");
			if(null ==cr.getBankCardBackPath()){
				kftBankCardBackBean.setFilePath(bankCardBackPath);
			}else{
				kftBankCardBackBean.setFilePath(cr.getBankCardBackPath());
			}
			kftBankCardBackBean.setCertNo("14");
			kftBankCardBackBean.setMerchantType("1");
			kftBankCardBackBean.setNo("5");
			
			/**门头照**/
			String doorPath =merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "08");
			if(null ==cr.getDoorPhotoPath()){
				kftDoorBean.setFilePath(doorPath);
			}else{
				kftDoorBean.setFilePath(cr.getDoorPhotoPath());
			}
			kftDoorBean.setCertNo("14");
			kftDoorBean.setMerchantType("1");
			kftDoorBean.setNo("6");
			
			/**bean添加到list中**/
			list.add(kftId0Bean);
			list.add(kftId1Bean);
			list.add(kftId2Bean);
			list.add(kftBankCardBean);
			list.add(kftBankCardBackBean);
			list.add(kftDoorBean);
			
		}else if("2".equals(cr.getMerchantProperty()) ||"4".equals(cr.getMerchantProperty()) ){
			//获取三证合一营业执照路径
			String licensePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "02");
			if(null == cr.getLicensePath()){
				kftLicenseBean.setFilePath(licensePath);
			}else{
				kftLicenseBean.setFilePath(cr.getLicensePath());
			}
			kftLicenseBean.setCertNo("21");
			kftLicenseBean.setMerchantType("2");
			kftLicenseBean.setNo("1");
			
			//获取身份证正反面
			String  identityImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "04");
			String[] paths = null;
			//正面
			String identityImagePath0 = null;
			//反面
			String identityImagePath1 = null;
			if(null != identityImagePath){
				paths = identityImagePath.split(";");
				identityImagePath0 = paths[0];
				identityImagePath1 = paths[1];
			}
			
			/**个人身份证正面照**/
			if(null ==cr.getIdentityImagePath0()){
				kftId0Bean.setFilePath(identityImagePath0);
			}else{
				kftId0Bean.setFilePath(cr.getIdentityImagePath0());
			}
			kftId0Bean.setCertNo("25");
			kftId0Bean.setMerchantType("2");
			kftId0Bean.setNo("2");
			
			/**个人身份证反面照**/
			if(null == cr.getIdentityImagePath1()){
				kftId1Bean.setFilePath(identityImagePath1);
			}else{
				kftId1Bean.setFilePath(cr.getIdentityImagePath1());
			}
			kftId1Bean.setCertNo("26");
			kftId1Bean.setMerchantType("2");
			kftId1Bean.setNo("3");
			
			//获取开户许可证
			String openPath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "03");
			if(null == cr.getOpenPath()){
				kftOpenBean.setFilePath(openPath);
			}else{
				kftOpenBean.setFilePath(cr.getOpenPath());
			}
			kftOpenBean.setCertNo("27");
			kftOpenBean.setMerchantType("2");
			kftOpenBean.setNo("4");
			
			//获取合作证明函
			String cooperatePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "15");
			if(null == cr.getOpenPath()){
				kftCooperateBean.setFilePath(cooperatePath);
			}else{
				kftCooperateBean.setFilePath(cr.getOpenPath());
			}
			kftCooperateBean.setCertNo("28");
			kftCooperateBean.setMerchantType("2");
			kftCooperateBean.setNo("5");
			
			/**门头照**/
			String doorPath =merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "08");
			if(null ==cr.getDoorPhotoPath()){
				kftDoorBean.setFilePath(doorPath);
			}else{
				kftDoorBean.setFilePath(cr.getDoorPhotoPath());
			}
			kftDoorBean.setCertNo("29");
			kftDoorBean.setMerchantType("2");
			kftDoorBean.setNo("6");
			
			/**bean添加到list中**/
			list.add(kftId0Bean);
			list.add(kftId1Bean);
			list.add(kftLicenseBean);
			list.add(kftOpenBean);
			list.add(kftDoorBean);
			list.add(kftCooperateBean);
			
			
		}else{
			//获取三证合一营业执照路径
			String licensePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "02");
			if(null == cr.getLicensePath()){
				kftLicenseBean.setFilePath(licensePath);
			}else{
				kftLicenseBean.setFilePath(cr.getLicensePath());
			}
			kftLicenseBean.setCertNo("30");
			kftLicenseBean.setMerchantType("3");
			kftLicenseBean.setNo("1");
			
			//获取身份证正反面
			String  identityImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "04");
			String[] paths = null;
			//正面
			String identityImagePath0 = null;
			//反面
			String identityImagePath1 = null;
			if(null != identityImagePath){
				paths = identityImagePath.split(";");
				identityImagePath0 = paths[0];
				identityImagePath1 = paths[1];
			}
			
			/**个人身份证正面照**/
			if(null ==cr.getIdentityImagePath0()){
				kftId0Bean.setFilePath(identityImagePath0);
			}else{
				kftId0Bean.setFilePath(cr.getIdentityImagePath0());
			}
			kftId0Bean.setCertNo("32");
			kftId0Bean.setMerchantType("3");
			kftId0Bean.setNo("2");
			
			/**个人身份证反面照**/
			if(null == cr.getIdentityImagePath1()){
				kftId1Bean.setFilePath(identityImagePath1);
			}else{
				kftId1Bean.setFilePath(cr.getIdentityImagePath1());
			}
			kftId1Bean.setCertNo("33");
			kftId1Bean.setMerchantType("3");
			kftId1Bean.setNo("3");
			

			/**银行卡正面照**/
			String bankCardPath =merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "07");
			if(null ==cr.getBankCardPath()){
				kftBankCardBean.setFilePath(bankCardPath);
			}else{
				kftBankCardBean.setFilePath(cr.getBankCardPath());
			}
			kftBankCardBean.setCertNo("34");
			kftBankCardBean.setMerchantType("3");
			kftBankCardBean.setNo("4");
			
			/**银行卡反面照**/
			String bankCardBackPath =merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "14");
			if(null ==cr.getBankCardBackPath()){
				kftBankCardBackBean.setFilePath(bankCardBackPath);
			}else{
				kftBankCardBackBean.setFilePath(cr.getBankCardBackPath());
			}
			kftBankCardBackBean.setCertNo("35");
			kftBankCardBackBean.setMerchantType("3");
			kftBankCardBackBean.setNo("5");
			
			/**手持身份证正面照**/
			String  identityHandImagePath = merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "13");
			if(null ==cr.getIdentityHandImagePath()){
				kftId2Bean.setFilePath(identityHandImagePath);
			}else{
				kftId2Bean.setFilePath(cr.getIdentityImagePath0());
			}
			kftId2Bean.setCertNo("36");
			kftId2Bean.setMerchantType("3");
			kftId2Bean.setNo("6");
			
			
			/**门头照**/
			String doorPath =merchantService.findTinyMerchantImagePathById(custInfo.getCustId(), "08");
			if(null ==cr.getDoorPhotoPath()){
				kftDoorBean.setFilePath(doorPath);
			}else{
				kftDoorBean.setFilePath(cr.getDoorPhotoPath());
			}
			kftDoorBean.setCertNo("37");
			kftDoorBean.setMerchantType("3");
			kftDoorBean.setNo("7");
			
			/**bean添加到list中**/
			list.add(kftLicenseBean);
			list.add(kftId0Bean);
			list.add(kftId1Bean);
			list.add(kftId2Bean);
			list.add(kftBankCardBean);
			list.add(kftBankCardBackBean);
			list.add(kftDoorBean);
		}
		
		Map<String, Object> fileList = new HashMap<String, Object>();
		fileList.put("filePatch", list);
		fileList.put("merCustId", cr.getMerchantCode());
		fileList.put("outMerchantCode", cr.getMerchantCode());
//		fileList.put("merCustId", Property.getProperty("kft.outMerchantCode"));
//		fileList.put("orderNo", "C2018030700123001");
		fileList.put("patchNo", cr.getPatchNo());
		fileList.put("channelType",ChannelMerRegist.KFT_PAY);
		
		logger.info("快付通上传文件请求报文：" + JSONObject.toJSONString(fileList));
		ChannelResult channelFileResult = iMerChantIntoService.fileUpload(fileList);
	    logger.info("快付通上传文件响应报文：" + JSONObject.toJSONString(channelFileResult));
	    
	    Map<String, Object> res = channelFileResult.getData();
		logger.info("上传文件响应返回：{}",JSONObject.toJSONString(res));
		
	    if("00".equals(channelFileResult.getReCode()) && "SUCCESS".equals(channelFileResult.getStatus().name())){
	    	/**
			 * 快付通进件信息
			 */
		    KftMerChantDataReq paramReq = new KftMerChantDataReq();
//		    	paramReq.setOrderNo("C2018030700123001");
				paramReq.setPatchNo(cr.getPatchNo());
				paramReq.setMerCustId(cr.getMerchantCode());
				/** 商户名称 
				 * 企业、事业单位：要求与营业执照上的名称一致。
				 * 个体户、个人：符合以下规则之一：
				 * 1.以营业执照名称命名；
				 * 2.以法人代表（或个人姓名）名称+销售商品命名；
				 * 3.以实体店点名命名
				 */
				paramReq.setSignName(cr.getCustName());
				/**展示名 - 收银台展示 */
				paramReq.setShowName(cr.getShortName());
				/**注册地址区县 */
				paramReq.setDistrict(cr.getCountry());
				/*** 通讯地址 */
				paramReq.setAddress(cr.getCprRegAddr());
				/**商户属性**/
				paramReq.setMerchantAttribute(cr.getMerchantAttribute());
				/*** 商户类型 */
//				paramReq.setMerchantType(cr.getMerchantProperty());
				/*** 经营类别    0100400186、软件/建站/技术开发-0080100020 */
				paramReq.setCategory(cr.getCategory());//美食1
				/*** 法人名字 */
				paramReq.setLegalPerson(cr.getRepresentativeName());
				/*** 联系人 */
				paramReq.setLinkman(cr.getAttentionName());
				/*** 联系电话 */
				paramReq.setLinkPhone(cr.getAttentionMobile());
				/*** 联系邮箱 */
				paramReq.setEmail(cr.getAttentionEmail());
				/**业务场景说明**/
				paramReq.setBusinessScene(cr.getBusinessScene());
				/**备注说明**/
//				paramReq.setRemark(cr.getRemark());
				/*paramReq.setIcpRecord("123124564");
				paramReq.setServiceIp("192.168.156.220");
				paramReq.setCompanyWebUrl("http://sj.qq.com/myapp/detail.htm?apkName=io.qianyi.mingming");
				*/
				if(null!=res){
					paramReq.setCertPath(res.get("certPath").toString());
					paramReq.setCertDigest(res.get("certDigest").toString());
				}
				/*paramReq.setCertPath("20180122164851838.zip");
				paramReq.setCertDigest("FlGXKVL1QM/e4ijP+QzXzA==");*/
			//证件信息
			List<KftCertInfo> corpCertInfo = new ArrayList<KftCertInfo>();
			KftCertInfo cert = new KftCertInfo();
				//身份证
				cert.setCertNo(cr.getCertifyNo());
				cert.setCertType("0");
				cert.setCertValidDate(cr.getCertifyTermEnd());
				//非个人时需营业执照
				if(!("1".equals(cr.getCustType()))){
					KftCertInfo cert2 = new KftCertInfo();
					cert2.setCertNo(cr.getBusinessLicense());
					cert2.setCertType("Y");
					cert2.setCertValidDate(cr.getBusinessTermEnd());
					corpCertInfo.add(cert2);
				}
				corpCertInfo.add(cert);
				
			
//			List<KftSettleBankInfo> settleBankAccount = new ArrayList<KftSettleBankInfo>();
				
			//清算银行信息
			KftSettleBankInfo settleBankAccount = new KftSettleBankInfo();
			//客户账户借记贷记类型 1借记 2贷记
			settleBankAccount.setSettleAccountCreditOrDebit("1");
			//结算账户账号
			settleBankAccount.setSettleBankAccountNo(cr.getAccountNo());
			//客户银行账户类型 1个人 2企业
			settleBankAccount.setSettleBankAcctType(cr.getSettleBankAcctType());
			//结算账户所属银行
			settleBankAccount.setSettleBankNo(cr.getSettleBankNo());
			//结算银行账户户名
			settleBankAccount.setSettleName(cr.getAccountNm());
//			settleBankAccount.add(settle);
			
			//开通的产品及费率
			List<KftProductFeeInfo> productFees = new ArrayList<KftProductFeeInfo>();
			KftProductFeeInfo prod = new KftProductFeeInfo();
			
			for(int i=0;i<kftProductFeeInfo.size();i++){
				
				//附加费率
				prod.setFeeOfAttach(kftProductFeeInfo.get(i).getFeeOfAttach());
				//百分比费率
				prod.setFeeOfRate(kftProductFeeInfo.get(i).getFeeOfRate());
				//费用类型 1：S0, 2: T0, 3: T1
				prod.setFeeType(kftProductFeeInfo.get(i).getFeeType());
				//产品Id
				prod.setProductId(kftProductFeeInfo.get(i).getProductId());//微信扫码
				
				productFees.add(prod);
			}
			
			paramReq.setCorpCertInfo(corpCertInfo);
			paramReq.setSettleBankAccount(settleBankAccount);
			paramReq.setProductFees(productFees);
			paramReq.setMerchantType(cr.getMerchantProperty());
			Map<String, Object> req = new HashMap<String, Object>();
			req.put("merList", paramReq);
			req.put("channelType",ChannelMerRegist.KFT_PAY);
		    
		    logger.info("快付通进件请求报文：" + JSONObject.toJSONString(req));
		    ChannelResult channelResult = iMerChantIntoService.merchatAdd(req);
		    logger.info("快付通进件响应报文：" + JSONObject.toJSONString(channelResult));
		    
		    
		    if(channelResult != null && "00".equals(channelResult.getReCode().toString())) {
//		        Map<String, Object> data = channelResult.getData();
		        String reMessage = StringUtils.isBlank(channelResult.getReMsg()) ? "请联系客服": channelResult.getReMsg();
		        if ("00".equals(channelResult.getReCode())) {
		        	  TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
		        	  /*String outMerchantCode = StringUtils.isBlank((String)channelResult.getData().get("mchntId")) ? "" :(String)channelResult.getData().get("mchntId");
			          tdInfo.setOutMerchantCode(outMerchantCode);*/
		        	  tdInfo.setMerchantCode(cr.getMerchantCode().trim());
			          tdInfo.setChannelNo(cr.getChannelNo());
			          tdInfo.setReportStatus("O");
			          // 查询商户报备信息
			          TdMerchantDetailInfo tdInfo_ = fmIncomeMapper.selTdMerchantDetailInfo(tdInfo);
			          tdInfo.setPatchNo(tdInfo_.getPatchNo());
			          String mchntStatus = "00";
			          tdInfo.setFileStatus("Y");
			          fmIncomeService.UpdateMerReportAndMerDetailInfo(tdInfo, mchntStatus);
			          //产品表状态修改
			          ToPayProduct toPayProduct = new ToPayProduct();
			          for(int i=0;i<kftProductFeeInfo.size();i++){
			        	  toPayProduct.setProductId(kftProductFeeInfo.get(i).getProductId());
			        	  toPayProduct.setMerchantCode(cr.getMerchantCode());
			        	  List<ToPayProduct> toPayProductList = toPayProductMapper.listProduct(toPayProduct);
			        	  if(!("00".equals(toPayProductList.get(0).getProductStatus()))){
			        		  toPayProduct.setProductStatus("00");
			        		  toPayProductMapper.updateRate(toPayProduct);
			        	  }
			          }
		        	  object.put("message", "快付通进件信息报备成功");
		        	  object.put("result", "SUCCESS");
		        }else {
		          logger.error("快付通进件明确失败:" + reMessage);
		          object.put("message", reMessage);
		          object.put("result", "FAILURE");
		        }
		    }else{
		        logger.error("快付通进件明确失败:" + channelResult.getReMsg());
		        object.put("result", "FAILURE");
		        object.put("message", channelResult.getReMsg());
		    }
	    }else{
	    	 logger.error("快付通上传文件明确失败:" + channelFileResult.getReMsg());
		     object.put("result", "FAILURE");
		     object.put("message", channelFileResult.getReMsg());
	    }
		
		return object;
	}

	public List<KFTArea> getKftProvinceList() {
		// TODO Auto-generated method stub
		return fktIncomeMapperDao.selKftProvinceList();
	}

	public List<KFTMccBean> getKftIndustryList() {
		// TODO Auto-generated method stub
		return fktIncomeMapperDao.selKftIndustryList();
	}

	public List<KFTMccBean> getKftIndustryTwoList(KFTMccBean kFTMccBean) {
		// TODO Auto-generated method stub
		return fktIncomeMapperDao.selKftIndustryTwoList(kFTMccBean);
	}

	public List<KFTMccBean> getKftIndustryThreeList(KFTMccBean kFTMccBean) {
		// TODO Auto-generated method stub
		return fktIncomeMapperDao.selKftIndustryThreeList(kFTMccBean);
	}

	public List<KFTArea> getKftCityList(KFTArea kftArea) {
		// TODO Auto-generated method stub
		return fktIncomeMapperDao.selKftCityList(kftArea);
	}
	
	public List<KFTArea> getKftAreaList(KFTArea kftArea) {
		// TODO Auto-generated method stub
		return fktIncomeMapperDao.selKftAreaList(kftArea);
	}

	public TdMerchantDetailInfo getInfo(TdMerchantDetailInfo detailInfo){
		
		return fktIncomeMapperDao.selInfo(detailInfo);
		
	}
	
}
