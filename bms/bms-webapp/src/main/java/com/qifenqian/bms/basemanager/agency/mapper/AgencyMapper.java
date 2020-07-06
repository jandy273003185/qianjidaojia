package com.qifenqian.bms.basemanager.agency.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.agency.bean.AgencyExport;
import com.qifenqian.bms.basemanager.merchant.bean.CustScan;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;

@MapperScan
public interface AgencyMapper {

	/**代理商列表查询  **/
	public List<MerchantVo> agencyList(MerchantVo merchantVo); 
	
	/** 自己权限查询代理商列表  **/
	public List<MerchantVo> myAgencyList(MerchantVo merchantVo); 
	
	/** 导出代理商列表Excel */
	public List<AgencyExport> exportAgencysList(MerchantVo merchantVo);
	
	//修改代理商
	public void updateMerchant(MerchantVo merchantVo);
	
	/** 图片 */
	public String findPath(CustScan custScan);
	
	/**
	 * 查询代理商
	 * @param merchant
	 * @return
	 */
	public List<Merchant> selectAgencyList(Merchant merchant);
}
