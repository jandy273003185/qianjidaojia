package com.qifenqian.bms.merchant.serviceparenter.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class ServiceParenterDao {
	
	@Autowired
	private ServiceParenterMapper serviceParenterMapper;
	/**
	 * 查询代理商列表
	 * @param merchantVo
	 * @return
	 */
	@Page
	public List<MerchantVo> serviceList(MerchantVo merchantVo) {
		return serviceParenterMapper.serviceList(merchantVo);
	}
	/**
	 * 查询自己权限下代理商列表
	 * @param merchantVo
	 * @return
	 */
	@Page
	public List<MerchantVo> myServicesList(MerchantVo merchantVo) {
		return serviceParenterMapper.myServicesList(merchantVo);
	}
	
	/**
	 * 新  查询代理商列表  //增加待审核状态
	 * @param merchantVo zhanggc
	 * @return
	 */
	@Page
	public List<MerchantVo> serviceNewList(MerchantVo merchantVo) {
		return serviceParenterMapper.serviceNewList(merchantVo);
	}
	/**
	 * 新  	zhanggc 查询代理商列表 显示 增加权限
	 * @param merchantVo 
	 * @return
	 */
	@Page
	public List<MerchantVo> myServicesNewList(MerchantVo merchantVo) {
		return serviceParenterMapper.myServicesNewList(merchantVo);
	}
	/**
	 * zhanggc 表 td_cust_info 修改审核状态
	 * @param newState 修改的新状态
	 * @param state 原有状态
	 */
	public int updateTdCustInfoState(String custId,String newState, String state) {
		return serviceParenterMapper.updateTdCustInfoState(custId,newState, state);
	}
	
	/**
	 * zhanggc 表td_certificate_auth 修改审核状态
	 */
	public int updateTdCertificateAuthState(String custId,String newState, String state) {
		return serviceParenterMapper.updateTdCertificateAuthState(custId,newState, state);
	}
	/**
	 * zhanggc 表td_certificate_auth 修改审核状态
	 */
	public int updateTdLoginUserInfoAuthState(String custId,String newState, String state) {
		return serviceParenterMapper.updateTdLoginUserInfoAuthState(custId,newState, state);
	}
	/**
	 * zhanggc 更新完还原密码
	 */
	public int updateTdLoginUserPassWordNull(String custId) {
		return serviceParenterMapper.updateTdLoginUserPassWordNull(custId);
	}
	
}
