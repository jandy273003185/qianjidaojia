package com.qifenqian.bms.basemanager.agency.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.agency.bean.AgenReport;
import com.qifenqian.bms.basemanager.agency.bean.AgentMerchantReport;
import com.qifenqian.bms.basemanager.agency.mapper.AgencyMapper;
import com.qifenqian.bms.basemanager.agency.mapper.AgencyReportMapper;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class AgencyDao {

	@Autowired
	private AgencyMapper agencyMapper;
	
	@Autowired
	private AgencyReportMapper agenReportMapper;
	
	/**
	 * 代理商报表
	 * @param bean
	 * @return
	 */
	@Page
	public List<AgenReport> getAgenReportList(AgenReport bean){
		return agenReportMapper.getAgenReportList(bean);
	}
	
	/**
	 * 代理商商户报表
	 * @param bean
	 * @return
	 */
	@Page
	public List<AgentMerchantReport> getAgentMerchantReport(AgentMerchantReport bean){
		return agenReportMapper.getAgentMerchantReport(bean);
	}
	
	/**
	 * 导出代理商报表
	 * @param bean
	 * @return
	 */
	public List<AgenReport> exportAgenReportList(AgenReport bean){
		return agenReportMapper.getAgenReportList(bean);
	}
	/**代理商列表查询 **/
	@Page
	public List<MerchantVo> agencyList(MerchantVo merchantVo) {
		return agencyMapper.agencyList(merchantVo);
	}
	
	/** 自己权限查询代理商列表  **/
	@Page
	public List<MerchantVo> myAgencyList(MerchantVo merchantVo){
		return agencyMapper.myAgencyList(merchantVo);
	}
	
	/** 代理商图片 */
	public String findPath(CustScan custScan){
		return agencyMapper.findPath(custScan);
	}
	
	
	/**
	 * 查询代理商
	 * @param merchant
	 * @return
	 */
	public List<Merchant> selectAgencyList(Merchant merchant){
		return agencyMapper.selectAgencyList(merchant);
	}
}
