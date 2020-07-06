package com.qifenqian.bms.basemanager.custInfo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.basemanager.custInfo.mapper.TdCustInfoMapper;
import com.qifenqian.bms.platform.web.page.Page;

/**
 * dao层，一般分页需要
 * 
 * @project sevenpay-bms-web
 * @fileName TdCustInfoDao.java
 * @memo 
 */
@Repository
public class TdCustInfoDao{

	@Autowired
	private TdCustInfoMapper custInfoMapper;
	
	/**
	 * 分页查询消费者列表
	 * @return
	 */
	@Page
	public List<TdCustInfo> selectCustInfos(TdCustInfo custInfo) {
		return custInfoMapper.selectCustInfos(custInfo);
	}
	
	/**
	 * 是否有权限查看所有订单
	 * @param userId
	 * @return
	 */
	public String isAllList(String userId){
		return custInfoMapper.isAllList(userId);
	}

	public String isAddMerchant(String userId) {
		return custInfoMapper.isAddMerchant(userId);
	}

	public TdCustInfo selectByMerchantCode(String merchantCode) {
		return custInfoMapper.selectByMerchantCode(merchantCode);
	}
}


