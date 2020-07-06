package com.qifenqian.bms.basemanager.merchant.mapper;


import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.merchant.bean.MerchantExport;
import com.qifenqian.bms.basemanager.merchant.bean.MerchantVo;
import com.qifenqian.bms.basemanager.merchant.bean.TinyMerchantExport;

@MapperScan
public interface MerchantEnterMapper {

	/**
	 * 商户列表导出
	 * @param merchantVo
	 * @return
	 */
	public List<MerchantExport> exportlist(MerchantVo merchantVo);
	
	public List<MerchantExport> proExportlist(MerchantVo merchantVo);
	
	public List<MerchantExport> backExportlist(MerchantVo merchantVo);

	
	/** 导出微商户列表Excel */
	public List<TinyMerchantExport> exportTinyMerchantsList(MerchantVo merchantVo);
	
	/**
	 * 商户列表导出
	 * @param merchantVo
	 * @return
	 */
	public List<MerchantExport> newExportlist(MerchantVo merchantVo);



}
