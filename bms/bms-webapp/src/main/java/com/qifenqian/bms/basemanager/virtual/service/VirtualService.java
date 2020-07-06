package com.qifenqian.bms.basemanager.virtual.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.virtual.bean.AgentInfo;
import com.qifenqian.bms.basemanager.virtual.bean.MerchantInfo;
import com.qifenqian.bms.basemanager.virtual.bean.MerchantTradeInfo;
import com.qifenqian.bms.basemanager.virtual.dao.VirtualDAO;

@Service
public class VirtualService {

	@Autowired
	private VirtualDAO virtualDAO;
	
	public List<MerchantTradeInfo> getTradeInfos(MerchantTradeInfo info){
		
		return virtualDAO.getTradeInfos(info);
	}
	
	public String getTotalAmt(MerchantTradeInfo info) {
		return virtualDAO.getTotalAmt(info);
	}
	
	public BigDecimal getTotal(List<MerchantTradeInfo> tradeBills) {
		BigDecimal allSum=new BigDecimal(0.00);
		for (MerchantTradeInfo merchantTradeInfo : tradeBills) {
			if(merchantTradeInfo.getTradeAmt()!=null&&!"".equals(merchantTradeInfo.getTradeAmt())){
				allSum = allSum.add(new BigDecimal(merchantTradeInfo.getTradeAmt()));
			}
		}
		return allSum;
	}
	
	public List<AgentInfo> getAgentInfos(AgentInfo info){
		
		return virtualDAO.getAgentInfos(info);
	}
	
	public List<MerchantInfo> getInfos(MerchantInfo info){
		
		return virtualDAO.getInfos(info);
	}
	
	public void updateInfos(MerchantInfo info){
		virtualDAO.updateInfos(info);
	}
}
