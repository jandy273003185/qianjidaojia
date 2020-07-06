package com.qifenqian.bms.basemanager.merchant.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.merchant.bean.TdLoginUserInfo;

@MapperScan
public interface TdLoginUserInfoMapper {

	/**
	 * 保存用户登陆信息
	 * @param userInfo
	 */
	public void saveLoginUserInfo(TdLoginUserInfo userInfo);
	
	/**
	 * 根据邮箱查找信息
	 */
	public TdLoginUserInfo selectByEmail(@Param("email") String email,@Param("custId") String custId,@Param("roleId") String roleId);
	
	/**
	 * 根据身份证查找信息
	 */
	public int selectByCardId(@Param("cardId") String cardId,@Param("roleId") String roleId);
	
	/**
	 * 根据营业执照查找信息
	 */
	public TdCustInfo selectByLicense(@Param("license") String license,@Param("roleId") String roleId);

	/**
	 * 根据手机查询
	 * @param mobile
	 * @return
	 */
	public TdLoginUserInfo selectByPhone(@Param("mobile")String mobile,@Param("roleId")String roleId);

	public void updatePwd(TdLoginUserInfo userInfo);
	
	/**
	 * 修改商户邮箱
	 * @param custId
	 * @param email
	 */
	public void updateEmail(@Param("custId") String custId,@Param("email") String email);
	
	/**
	 * 修改登录信息
	 * @param userInfo
	 */
	public void updateLoginUserInfo(TdLoginUserInfo userInfo);
	
	public TdLoginUserInfo selectLoginUserInfo(@Param("custId") String custId);

	TdLoginUserInfo selectByPhoneEnter(String merchantAccount);
}
