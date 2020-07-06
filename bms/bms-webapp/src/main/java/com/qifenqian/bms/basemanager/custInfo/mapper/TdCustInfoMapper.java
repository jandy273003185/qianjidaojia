package com.qifenqian.bms.basemanager.custInfo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfoExcel;
import com.qifenqian.bms.basemanager.custInfo.bean.TdLoginUserInfo;

/**
 * 消费者信息管理
 * 
 * @author pc
 *
 */
@MapperScan
public interface TdCustInfoMapper {

	/**
	 * 消费者信息列表
	 * 
	 * @param custInfo
	 */
	public List<TdCustInfo> selectCustInfos(TdCustInfo custInfo);

	/**
	 * 根据ID查找消费者信息
	 * 
	 * @param id
	 * @return
	 */
	public TdCustInfo selectById(@Param("id") String id);
	
	/**
	 * 导出用户列表信息
	 * @param custInfo
	 * @return
	 */
	public List<TdCustInfoExcel> exportCustInfos(TdCustInfo custInfo);
	

	/**
	 * 更新消费者状态
	 * 
	 * @param custInfo
	 */
	public void updateCustInfo(TdCustInfo custInfo);


	public TdCustInfo validateMobile(@Param("mobile") String mobile,
			@Param("custId") String custId);
	
	/**
	 * 查找用户信息和用户登录信息
	 * @param custId
	 * @return
	 */
	public TdCustInfo selectLoginAndcustInfo(@Param("custId") String custId);

	public int updateCustLoginInfo(TdCustInfo updateBean);

	public int updateInfo(TdCustInfo tdCustInfo);
	
	public TdLoginUserInfo selectLoginInfo(@Param("custId") String custId);

	public TdLoginUserInfo validateCustMobile(@Param("mobile") String mobile,@Param("custId") String custId);

	public TdLoginUserInfo validateEmail(@Param("email") String email,@Param("custId") String custId);
	
	public TdCustInfo validateMerchantName(@Param("name") String name);
	
	public TdCustInfo selectByBean(TdCustInfo info);
	
	public String isAllList(@Param("userId")String userId);
	
	public int updateTdCustInfo(TdCustInfo tdCustInfo);

	String isAddMerchant(@Param("userId")String userId);

	TdCustInfo selectByMerchantCode(@Param("merchantCode")String merchantCode);
	
	List<TdCustInfo> selectByMerchantFlag(@Param("merchantFlag")String merchantFlag);
}
