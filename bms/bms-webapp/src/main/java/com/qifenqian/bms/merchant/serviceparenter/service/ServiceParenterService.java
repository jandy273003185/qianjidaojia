package com.qifenqian.bms.merchant.serviceparenter.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.merchant.serviceparenter.dao.ServiceParenterDao;

@Service
public class ServiceParenterService {
	
	
	@Autowired
	private ServiceParenterDao serviceDao;
	
	/**代理商列表查询 **/
	public List<MerchantVo> selectServices(MerchantVo merchantVo){
		return serviceDao.serviceList(merchantVo);
	}

	public List<MerchantVo> myServicesList(MerchantVo merchantVo) {
		// TODO Auto-generated method stub
		return serviceDao.myServicesList(merchantVo);
	}
	
	/**新  查询代理商列表  zhanggc**/
	public List<MerchantVo> selectNewServices(MerchantVo merchantVo){
		return serviceDao.serviceNewList(merchantVo);
	}
	/**
	 * 新  查询代理商列表   zhanggc
	 */
	public List<MerchantVo> myServicesNewList(MerchantVo merchantVo) {
		// TODO Auto-generated method stub
		return serviceDao.myServicesNewList(merchantVo);
	}
	
	/**
	 * @param fals 标识  yes 审核通过  ; no 服务商审核不通过
	 *  zhanggc 服务商审核状态
	 */
	public String updateState(String custId , String fals) {
		
		int i =0;//标识
		int j =0;//标识
		int z =0;
		//yes 审核通过
		if ("yes".equals(fals))
		{
			//加事务  下面代码干掉
			 i = serviceDao.updateTdCustInfoState(custId, "00","01");// 客户信息表 修改  00 有效；01 待审核；02 注销；03 冻结；04 审核不通过;05 待完善',
			 j = serviceDao.updateTdCertificateAuthState(custId, "0" ,"1");// 代理商审核表  0 审核通过  1 待审核  2 审核不通过
			 z = serviceDao.updateTdLoginUserInfoAuthState(custId, "00", "04");// 登录用户信息表 ：00 正常；01 停用；02 登录账户冻结 ; 03 注册待激活；04 商户审核中； 05 前台 商户协议待录入； 06 后台商户协议待录入;07 审核不通过',	 

		}
		//服务商审核不通过
		if ("no".equals(fals))
		{
			//加事屋  下面代码干掉  
			 i = serviceDao.updateTdCustInfoState(custId, "04","01");//00 有效；01 待审核；02 注销；03 冻结；04 审核不通过;05 待完善',
			 j = serviceDao.updateTdCertificateAuthState(custId, "2","1");//0 审核通过  1 待审核  2 审核不通过
			 z = serviceDao.updateTdLoginUserInfoAuthState(custId, "07", "04");// 登录用户信息表 ：00 正常；01 停用；02 登录账户冻结 ; 03 注册待激活；04 商户审核中； 05 前台 商户协议待录入； 06 后台商户协议待录入;07 审核不通过',
		}
		//服务商修改后改成更新状态
		if ("update".equals(fals)) {
			 serviceDao.updateTdCustInfoState(custId, "01",null);//00 有效；01 待审核；02 注销；03 冻结；04 审核不通过;05 待完善',
			 serviceDao.updateTdCertificateAuthState(custId, "1",null);//0 审核通过  1 待审核  2 审核不通过
			 serviceDao.updateTdLoginUserInfoAuthState(custId, "04", null);// 登录用户信息表 ：00 正常；01 停用；02 登录账户冻结 ; 03 注册待激活；04 商户审核中； 05 前台 商户协议待录入； 06 后台商户协议待录入;07 审核不通过',
			 serviceDao.updateTdLoginUserPassWordNull(custId);//修改后等待审核吧密码还原为Null
		}
		return "SUCCESS";
	}
	
}
