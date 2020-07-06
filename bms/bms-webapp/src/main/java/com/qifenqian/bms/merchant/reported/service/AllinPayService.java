package com.qifenqian.bms.merchant.reported.service;

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
import com.qifenqian.bms.merchant.reported.bean.AllinPayBean;
import com.qifenqian.bms.merchant.reported.bean.AllinPayProductInfo;
import com.qifenqian.bms.merchant.reported.bean.Bank;
import com.qifenqian.bms.merchant.reported.bean.Industry;
import com.qifenqian.bms.merchant.reported.bean.Province;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfo;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantDetailInfoAllinPay;
import com.qifenqian.bms.merchant.reported.bean.TdMerchantReportInfo;
import com.qifenqian.bms.merchant.reported.dao.AllinPayMapperDao;
import com.qifenqian.bms.merchant.reported.mapper.AllinPayMapper;
import com.qifenqian.bms.merchant.reported.mapper.FmIncomeMapper;
import com.qifenqian.jellyfish.api.merregist.IAllinpayAgentMerRegistService;
import com.qifenqian.jellyfish.bean.merregist.allinpay.AllinpayMerchantAddReq;
import com.qifenqian.jellyfish.bean.merregist.allinpay.AllinpayMerchantAddRes;
import com.qifenqian.jellyfish.bean.merregist.allinpay.AllinpayMerchantEditReq;
import com.qifenqian.jellyfish.bean.merregist.allinpay.AllinpayMerchantEditRes;
import com.qifenqian.jellyfish.bean.merregist.allinpay.AllinpayMerchantQueryReq;
import com.qifenqian.jellyfish.bean.merregist.allinpay.AllinpayMerchantQueryRes;
import com.qifenqian.jellyfish.bean.merregist.allinpay.AllinpayMerchantQueryStatusReq;
import com.qifenqian.jellyfish.bean.merregist.allinpay.AllinpayMerchantQueryStatusRes;
import com.qifenqian.jellyfish.bean.merregist.allinpay.Prod;
import com.qifenqian.jellyfish.bean.merregist.allinpay.ProdInfo;

@Service
public class AllinPayService {
	
	private Logger logger = LoggerFactory.getLogger(AllinPayService.class);
	
	@Autowired
	private AllinPayMapperDao allinPayMapperDao;
	@Autowired
	private FmIncomeMapper fmIncomeMapper;
	@Autowired
	private AllinPayMapper allinPayMapper;
	@Reference(version="${dubbo.provider.jellyfish.version}")
	private IAllinpayAgentMerRegistService allinAgentMerRegistService;
	
	
	
	/**
	 * 商户行业信息
	 * @return
	 */
	public List<Industry> getAllinPayIndustryList(){
		return allinPayMapperDao.getAllinPayIndustryList();
		
	}
	
	/**
	 * 获取通联商户报备明细
	 * @return
	 */
	public TdMerchantDetailInfoAllinPay getAllinPayTdMerchantDetail(String patchNo) {
		return allinPayMapperDao.getAllinPayTdMerchantDetail(patchNo);
	}

	/**
	 * 商户省份
	 * @return
	 */
	public List<Province> getProvinceName() {
		return allinPayMapperDao.getProvinceName();
	}

	/**
	 * 商户银行信息
	 * @return
	 */
	public List<Bank> getBankInfo() {
		return allinPayMapperDao.getBankInfo();
	}
	
	public Map<String, String> allinPayEditReported(AllinPayBean cr) {
		Map<String, String> result = new HashMap<String, String>();
		AllinpayMerchantEditReq  request= new AllinpayMerchantEditReq();
		request.setMchid(cr.getMchId());
		//request.setMerchantid(cr.getMerchantCode());
		//request.setMerchantname(cr.getCustName());
		request.setShortname(cr.getShortName());
		request.setProvince(cr.getMerchantProvince());
		request.setCity(cr.getMerchantCity());
		request.setAddress(cr.getCprRegAddr());
		request.setServicephone(cr.getContactPhone());
		//行业
		//request.setMccid(cr.getIndustry());
		request.setComproperty(cr.getMecTypeFlag());
		request.setCorpbusname(cr.getCprRegNmCn());
		request.setCreditcode(cr.getBusinessLicense());
		request.setCreditcodeexpire(cr.getBusinessTerm());
		request.setLegal(cr.getRepresentativeName());
		request.setLegalidtype(cr.getRepresentativeCertType());
		request.setLegalidno(cr.getRepresentativeCertNo());
		request.setLegalidexpire(cr.getIdTermEnd());
		request.setContactphone(cr.getAttentionMobile());
		request.setContactperson(cr.getAttentionName());
		request.setClearmode(cr.getClearMode());
		request.setAcctid(cr.getAccountNo());
		request.setAcctname(cr.getAccountNm());
		request.setAccttype(cr.getPerEntFlag());
		request.setAccttp(cr.getAccttp());
		request.setBankcode(cr.getBranchBank());   
		request.setCnapsno(cr.getInterBankCode());
		request.setContractdate(cr.getContractDate());
		request.setOfflag(cr.getOfflag());
		request.setExpanduser(cr.getExpanduser());
		//结算人身份证号
		//request.setSettidno(cr.getCertifyNo());
		request.setPubacctinfo(cr.getAccountNo()+"#"+cr.getBankProvince()+"#"+cr.getBankCity()+"#"+cr.getBranchBank()+"#"+cr.getInterBankCode());
		//无纸化标识
		//request.setAgreetype(cr.getAgreeType());
		//进件成功 回调地址
		request.setNotifyurl("https://combinedpay.qifenqian.com/allinpay/callbak.do");
		//交易类型List
		List<Prod>  list = new ArrayList<Prod>();
		
		Prod prod = new Prod();
		for(int i=0;i<cr.getProdInfoList().size();i++){
			prod.setPid((String) cr.getProdInfoList().get(i).get("pid"));
			prod.setMtrxcode((String) cr.getProdInfoList().get(i).get("mtrxcode"));
			prod.setFeerate((String) cr.getProdInfoList().get(i).get("feerate"));
			if(StringUtils.isNotBlank(cr.getProdInfoList().get(i).get("creditrate").toString())) {
				prod.setCreditrate((String) cr.getProdInfoList().get(i).get("creditrate"));
			}
			
			list.add(prod);
		}
		request.setProdlist(list);
		
		logger.info("通联商户信息修改接口请求报文：{}", JSONObject.toJSONString(request));
		AllinpayMerchantEditRes editRes = allinAgentMerRegistService.edit(request);
		logger.info("通联商户信息修改接口响应报文：{}", JSONObject.toJSONString(editRes));
		
		if ("SUCCESS".equals(editRes.getRetcode())) {
			result.put("result", "SUCCESS");
			String message = null;
			if ("SUCCESS".equals(editRes.getAuditstatus())) {
				message = "审核通过";
			}
			else if ("AUDITING".equals(editRes.getAuditstatus())) {
				message = "审核中";
			}
			else if ("ACCEPT".equals(editRes.getAuditstatus())) {
				message = "受理成功待提交审核";
			}
			else if ("ACCEPTFAIL".equals(editRes.getAuditstatus())) {
				message = "提交审核失败";
			}
			else if ("FAIL".equals(editRes.getAuditstatus())) {
				message = "审核失败";
			}
			else {
				message = "未知状态";
			}
			result.put("message", message);
			cr.setPatchNo(GenSN.getSN());
			cr.setReportStatus("00");
			cr.setFlagStatus("1");
			insertTdMerchantInfoAllinPay(cr);
		} else {
			result.put("result", "FAIL");
			result.put("message", "调用修改接口失败");
		}
		
		return result;
	}

	/**
	 * 通联进件
	 * @param cr
	 * @return
	 */
	public JSONObject allinPyaAppReported(AllinPayBean cr) {
		JSONObject object = new JSONObject();
		TdMerchantDetailInfo tdInfo = new TdMerchantDetailInfo();
		AllinpayMerchantAddReq  request= new AllinpayMerchantAddReq();
		
		try {
			request.setMerchantid(cr.getMerchantCode());
			request.setMerchantname(cr.getCustName());
			request.setShortname(cr.getShortName());
			request.setProvince(cr.getMerchantProvince());
			request.setCity(cr.getMerchantCity());
			request.setAddress(cr.getCprRegAddr());
			request.setServicephone(cr.getContactPhone());
			request.setMccid(cr.getIndustry());
			request.setComproperty(cr.getMecTypeFlag());
			request.setCorpbusname(cr.getCprRegNmCn());
			request.setCreditcode(cr.getBusinessLicense());
			request.setCreditcodeexpire(cr.getBusinessTerm());
			request.setLegal(cr.getRepresentativeName());
			request.setLegalidtype(cr.getRepresentativeCertType());
			request.setLegalidno(cr.getRepresentativeCertNo());
			request.setLegalidexpire(cr.getIdTermEnd());
			request.setContactphone(cr.getAttentionMobile());
			request.setContactperson(cr.getAttentionName());
			request.setClearmode(cr.getClearMode());
			request.setAcctid(cr.getAccountNo());
			request.setAcctname(cr.getAccountNm());
			request.setAccttype(cr.getPerEntFlag());
			request.setAccttp(cr.getAccttp());
			request.setBankcode(cr.getBranchBank());   
			request.setCnapsno(cr.getInterBankCode());
			request.setContractdate(cr.getContractDate());
			request.setOfflag(cr.getOfflag());
			request.setExpanduser(cr.getExpanduser());
			request.setSettidno(cr.getCertifyNo());
			request.setPubacctinfo(cr.getAccountNo()+"#"+cr.getBankProvince()+"#"+cr.getBankCity()+"#"+cr.getBranchBank()+"#"+cr.getInterBankCode());
			request.setAgreetype(cr.getAgreeType());
			//进件成功 回调地址
			request.setNotifyurl("https://combinedpay.qifenqian.com/allinpay/callbak.do");
			//法人身份证正面照片
			request.setLegalidpicfront(cr.getLegalCertAttribute1Path());
			//法人身份证反面照片
			request.setLegalidpicback(cr.getLegalCertAttribute2Path());
			//商户门头照片
			request.setStorepic(cr.getDoorPhotoPath());
			//手持身份证照片
			request.setLegalpic(cr.getHandIdCardPath());
			//经营场所证明文件
			request.setBizplacepic(cr.getBusinessPlacePath());
			//经营内景照片
			request.setStoreinnerpic(cr.getShopInteriorPath());
			//交易类型List
			List<ProdInfo>  list = new ArrayList<ProdInfo>();
			
			ProdInfo prod = new ProdInfo();
			for(int i=0;i<cr.getProdInfoList().size();i++){
				prod.setPid((String) cr.getProdInfoList().get(i).get("pid"));
				prod.setMtrxcode((String) cr.getProdInfoList().get(i).get("mtrxcode"));
				prod.setFeerate((String) cr.getProdInfoList().get(i).get("feerate"));
				if(StringUtils.isNotBlank(cr.getProdInfoList().get(i).get("creditrate").toString())) {
					prod.setCreditrate((String) cr.getProdInfoList().get(i).get("creditrate"));
				}
				if(StringUtils.isNotBlank(cr.getProdInfoList().get(i).get("lowlimit").toString())) {
					prod.setLowlimit((String) cr.getProdInfoList().get(i).get("lowlimit"));
				}
				if(StringUtils.isNotBlank(cr.getProdInfoList().get(i).get("toplimit").toString())) {
					prod.setToplimit((String) cr.getProdInfoList().get(i).get("toplimit"));
				}
				
				list.add(prod);
			}
			request.setProdlist(list);
			
			logger.info("进件通联开始调用接口："+ "--------------------" + request);
			AllinpayMerchantAddRes res = allinAgentMerRegistService.add(request);
			logger.info("进件通联调用接口结束："+ "--------------------" + res);
			
			object.put("retcode",res.getRetcode());
			
			tdInfo.setMerchantCode(cr.getMerchantCode().trim());
	        tdInfo.setChannelNo(cr.getChannelNo());
	        TdMerchantDetailInfo tdInfo_ = fmIncomeMapper.selTdMerchantDetailInfo(tdInfo);
	        TdMerchantReportInfo reportInfo = new TdMerchantReportInfo();
	        reportInfo.setMerchantCode(cr.getMerchantCode());
	        reportInfo.setChannelNo(cr.getChannelNo());
	        reportInfo.setPatchNo(tdInfo_.getPatchNo());
	        
	        //报备完毕更改报备表状态
			if("SUCCESS".equals(res.getRetcode())) {
				if("ACCEPT".equals(res.getAuditstatus()) || "AUDITING".equals(res.getAuditstatus())) {
					reportInfo.setReportStatus("Y");
					cr.setReportStatus("00");
					object.put("result","SUCCESS");
				}else if("FAIL".equals(res.getAuditstatus())) {
					object.put("result","FAILURE");
					reportInfo.setReportStatus("F");
				}else if("ACCEPTFAIL".equals(res.getAuditstatus())) {
					object.put("result","FAILURE");
					reportInfo.setReportStatus("E");
				}else if("SUCCESS".equals(res.getAuditstatus())) {
					reportInfo.setReportStatus("O");
					cr.setReportStatus("1");
					object.put("result","SUCCESS");
				}
				object.put("message",res.getRetmsg());
				object.put("auditstatus",res.getAuditstatus());
				object.put("merchantid",res.getMerchantid());
				object.put("sign",res.getSign());
				cr.setResultMsg(res.getRetmsg());
			}else {
				reportInfo.setReportStatus("F");
				object.put("result","FAILURE");
				object.put("message",res.getErrmsg());
				cr.setResultMsg(res.getRetmsg());
			}
			
			logger.debug("更新td_merchant_report和td_merchant_detail_info_allin_pay表数据：{}", JSONObject.toJSONString(reportInfo));
			allinPayMapper.updateTdMerchantReport(reportInfo);
			if(null != res.getMerchantid()) {
				cr.setOutMerchantCode(res.getMerchantid());
				allinPayMapper.updateTdMerchantDetailInfoAllinPay(cr);
			}
		    logger.info("通联进件报备end.......................");
			
		}catch (Exception e) {
			object.put("result","FAILURE");
			object.put("message",e);
			
		}
		return object;
	}

	/**
	 * 通联查询进件状态
	 * @param selMerchantDetailInfo
	 * @return
	 */
	public AllinpayMerchantQueryStatusRes queryStatus(TdMerchantDetailInfo selMerchantDetailInfo) {
		AllinpayMerchantQueryStatusReq  request= new AllinpayMerchantQueryStatusReq();
		
		logger.debug("查询通联报备申请单状态，报备事务编号：{}", selMerchantDetailInfo);
		request.setMerchantid(selMerchantDetailInfo.getMerchantCode());
		AllinpayMerchantQueryStatusRes res = allinAgentMerRegistService.queryStatus(request);
		logger.debug("查询通联报备申请单状态，返回值：{}", JSONObject.toJSONString(res));
		
		return res;
		
	}

	/**
	 * 通联商户进件成功商户信息查询
	 * @param cr
	 * @return
	 */
	public AllinpayMerchantQueryRes allinPyaAppQuery(AllinPayBean cr) {
		AllinpayMerchantQueryReq req = new AllinpayMerchantQueryReq();
		
		logger.debug("通联商户进件成功商户信息查询：{}", cr);
		req.setMchid(cr.getMchId());
		AllinpayMerchantQueryRes res  = allinAgentMerRegistService.query(req);
		logger.debug("通联商户进件成功商户信息查询：{}", JSONObject.toJSONString(res));
		
		return res;
	}

	public TdMerchantReportInfo getTdMerchantReport(TdMerchantReportInfo reportBean) {
		TdMerchantReportInfo reportInfo =  allinPayMapperDao.getTdMerchantReport(reportBean);
		return reportInfo;
	}

	public void updateTdMerchantReport(TdMerchantReportInfo tdInfo) {
		allinPayMapper.updateTdMerchantReport(tdInfo);
		
	}

	public void insertTdMerchantReport(TdMerchantReportInfo info) {
		allinPayMapperDao.insertTdMerchantReport(info);
		
	}

	public void insertTdMerchantInfoAllinPay(AllinPayBean cr) {
		//添加通联报备明细表
		allinPayMapperDao.insertTdMerchantDetailInfoAllinPay(cr);
		//添加通联报备产品明细表
		AllinPayProductInfo prod = new AllinPayProductInfo();
		for(int i=0;i<cr.getProdInfoList().size();i++){
			prod.setId(GenSN.getSN());
			prod.setMerchantCode(cr.getMerchantCode());
			prod.setProductId((String) cr.getProdInfoList().get(i).get("pid"));
			prod.setMtrxCode((String) cr.getProdInfoList().get(i).get("mtrxcode"));
			prod.setFeeRate((String) cr.getProdInfoList().get(i).get("feerate"));
			prod.setCreditRate((String) cr.getProdInfoList().get(i).get("creditrate"));
			prod.setLowLimit((String) cr.getProdInfoList().get(i).get("lowlimit"));
			prod.setTopLimit((String) cr.getProdInfoList().get(i).get("toplimit"));
			allinPayMapperDao.insertTdMerchantProductInfoAllinPay(prod);
		}
		
		
		
	}

	public void updateTdMerchantInfoAllinPay(AllinPayBean cr) {
		//修改通联报备明细表
		allinPayMapperDao.updateTdMerchantDetailInfoAllinPay(cr);
		//修改通联报备产品明细表
		AllinPayProductInfo prod = new AllinPayProductInfo();
		for(int i=0;i<cr.getProdInfoList().size();i++){
			prod.setId(GenSN.getSN());
			prod.setMerchantCode(cr.getMerchantCode());
			prod.setProductId((String) cr.getProdInfoList().get(i).get("pid"));
			prod.setMtrxCode((String) cr.getProdInfoList().get(i).get("mtrxcode"));
			prod.setFeeRate((String) cr.getProdInfoList().get(i).get("feerate"));
			prod.setCreditRate((String) cr.getProdInfoList().get(i).get("creditrate"));
			prod.setLowLimit((String) cr.getProdInfoList().get(i).get("lowlimit"));
			prod.setTopLimit((String) cr.getProdInfoList().get(i).get("toplimit"));
			allinPayMapperDao.updateTdMerchantProductInfoAllinPay(prod);
		}
		
	}

	//无纸化进件电子协议URL接口查询
	public AllinpayMerchantQueryRes queryElect(AllinPayBean cr) {
		AllinpayMerchantQueryReq req = new AllinpayMerchantQueryReq();
		
		logger.debug("通联商户无纸化进件电子协议URL接口查询：{}", cr);
		req.setMchid(cr.getMchId());
		AllinpayMerchantQueryRes res  = allinAgentMerRegistService.query(req);
		logger.debug("通联商户无纸化进件电子协议URL接口查询：{}", JSONObject.toJSONString(res));
		
		return res;
	}

	//无纸化进件电子协议URL接口重发
	public AllinpayMerchantQueryRes queryElectSign(AllinPayBean cr) {
		AllinpayMerchantQueryReq req = new AllinpayMerchantQueryReq();
		
		logger.debug("通联商户无纸化进件电子协议URL接口查询：{}", cr);
		req.setMchid(cr.getMchId());
		AllinpayMerchantQueryRes res  = allinAgentMerRegistService.query(req);
		logger.debug("通联商户无纸化进件电子协议URL接口查询：{}", JSONObject.toJSONString(res));
		
		return res;
	}
	
	
	
}
