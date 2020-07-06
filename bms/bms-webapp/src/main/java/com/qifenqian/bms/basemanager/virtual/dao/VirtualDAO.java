package com.qifenqian.bms.basemanager.virtual.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.virtual.bean.AgentInfo;
import com.qifenqian.bms.basemanager.virtual.bean.MerchantInfo;
import com.qifenqian.bms.basemanager.virtual.bean.MerchantTradeInfo;
import com.qifenqian.bms.basemanager.virtual.mapper.MerchantInfoMapper;
import com.qifenqian.bms.basemanager.virtual.mapper.MerchantTradeInfoMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class VirtualDAO {

	@Autowired
	private MerchantTradeInfoMapper merchantTradeInfoMapper;
	
	@Autowired
	private MerchantInfoMapper merchantInfoMapper;
	
	@Page
	public List<MerchantInfo> getInfos(MerchantInfo info){
		
		return merchantInfoMapper.getInfos(info);
	}
	
	@Page
	public List<AgentInfo> getAgentInfos(AgentInfo info){
		
		return merchantInfoMapper.getAgentInfo(info);
	}
	
	@Page
	public List<MerchantTradeInfo> getTradeInfos(MerchantTradeInfo info){
		
		return merchantTradeInfoMapper.getTradeInfos(info);
	}
	
	public String getTotalAmt(MerchantTradeInfo info) {
		return merchantTradeInfoMapper.getTotalAmt(info);
	}
	
	public void updateInfos(MerchantInfo info){
		merchantInfoMapper.updateInfos(info);
	}
}
