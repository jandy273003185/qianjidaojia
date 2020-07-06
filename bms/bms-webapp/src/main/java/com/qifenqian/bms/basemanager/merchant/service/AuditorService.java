package com.qifenqian.bms.basemanager.merchant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.Constant;
import com.qifenqian.bms.basemanager.bank.service.BankService;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.basemanager.custInfo.service.TdCustInfoService;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.TdCertificateAuth;
import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;
import com.qifenqian.bms.basemanager.merchant.dao.MerchantDao;
import com.qifenqian.bms.basemanager.merchant.mapper.CustScanMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.MerchantMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.TdCertificateAuthMapper;
import com.qifenqian.bms.basemanager.merchant.mapper.TdLoginUserInfoMapper;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Service
public class AuditorService {
	@Autowired
	private MerchantDao dao;
	
	@Autowired
	private CustScanMapper scanMapper;
	
	@Autowired 
	private TdLoginUserInfoMapper loginUserInfoMapper;
	@Autowired 
	private MerchantMapper merchantMapper;
	@Autowired
	private BankService bankService;
	
	@Autowired
	private TdCustInfoService tdCustInfoService;
	
	@Autowired
	private MerchantWorkFlowAuditService merchantWorkFlowAuditService;
	
	@Autowired
	private TdCertificateAuthMapper tdCertificateAuthMapper;
	
	@Autowired
	private TdCustInfoMapper tdCustInfoMapper;
	
	public List<MerchantVo> selectMerchants(MerchantVo merchantVo){
		return dao.listAuditor(merchantVo);
	}
	
	public String findScanPath(String custId,String certifyType,String authId){
		CustScan custScan=new CustScan();
		custScan.setCustId(custId);
		custScan.setCertifyType(certifyType);	
		custScan.setAuthId(authId);
		return scanMapper.findPath(custScan);
	}
	
	/**
	 * 后台商户注册审核不通过
	 * @param custId
	 */
	public void failPassAndUpdateStatus(String custId){
		
		TdCustInfo custInfo = tdCustInfoService.selectById(custId);
		if(custInfo ==null){
			throw new IllegalArgumentException(custId+"商户不存在");
		}
		
		try {
			TdCertificateAuth tdCertificateAuth = new TdCertificateAuth();
			tdCertificateAuth.setAuthId(Integer.valueOf(custInfo.getAuthId()));
			tdCertificateAuth.setCertifyUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
			tdCertificateAuth.setCertificateState("2");// 审核不通过
			tdCertificateAuthMapper.updateByPrimaryKeySelective(tdCertificateAuth);
			
			TdCustInfo tdcustInfo = new TdCustInfo();
			tdcustInfo.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
			tdcustInfo.setCustId(custId);
			tdcustInfo.setTrustCertifyAuditState("35");//认证不通过
			tdCustInfoMapper.updateInfo(tdcustInfo);
		} catch (Exception e) {
			throw e;
		}
	
	}
	
	/**
	 * 后台商户审核通过
	 * @param merchantVo
	 * @param loginPwd_02
	 */
	public void saveAndUpdate(MerchantVo merchantVo,String loginPwd_02) {
		
			try {
				
				TdLoginUserInfo userInfo=new TdLoginUserInfo();
				userInfo.setCustId(merchantVo.getCustId());
				userInfo.setLoginPwd(loginPwd_02);
				userInfo.setAttachStr(merchantVo.getAttachStr());
				userInfo.setState(Constant.LOGIN_STATE_AGREEMENTING);//后台商户协议待录入
				loginUserInfoMapper.updatePwd(userInfo);				
				merchantMapper.updateUserState(merchantVo.getCustId());
				
				TdCustInfo custInfo = tdCustInfoService.selectById(merchantVo.getCustId());
				if(custInfo ==null){
					throw new IllegalArgumentException(merchantVo.getCustId()+"商户不存在");
				}
				
				TdCertificateAuth tdCertificateAuth = new TdCertificateAuth();
				tdCertificateAuth.setAuthId(Integer.valueOf(custInfo.getAuthId()));
				tdCertificateAuth.setCertifyUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
				tdCertificateAuth.setCertificateState("0");// 审核通过
				tdCertificateAuthMapper.updateByPrimaryKeySelective(tdCertificateAuth);
				
				TdCustInfo tdcustInfo = new TdCustInfo();
				tdcustInfo.setModifyId(String.valueOf(WebUtils.getUserInfo().getUserId()));
				tdcustInfo.setCustId(merchantVo.getCustId());
				tdcustInfo.setTrustCertifyAuditState("30");//认证通过
				tdCustInfoMapper.updateInfo(tdcustInfo);
			} catch (Exception e) {
				throw e;
			}
				
		
	}

	public List<String> findByidScanPath(String custId) {
		// TODO Auto-generated method stub
		return scanMapper.findByidScanPath(custId);
	}
	
	/*@Transactional
	public void updateStatue(String custId,String auditStatus,String certifyStatus,String message){
		TdCustInfo custInfo = tdCustInfoService.selectById(custId);
		merchantWorkFlowAuditService.updateAuditStatus(custInfo.getAuthId(), message, auditStatus);
		merchantWorkFlowAuditService.updateCertifiyStatus(custId, certifyStatus);// 30  认证通过
	}*/
}
