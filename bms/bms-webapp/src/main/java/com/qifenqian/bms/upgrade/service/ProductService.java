package com.qifenqian.bms.upgrade.service;

import java.util.List;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.upgrade.bean.ProductAuding;
import com.qifenqian.bms.upgrade.bean.ProductExport;
import com.qifenqian.bms.upgrade.bean.TdAuditRecodeInfo;

public interface ProductService {
	 //获取产品审核列表
	public List<ProductAuding> getProductAudingList(ProductAuding product);
	//获取产品审核列表
	public List<ProductExport> getProductAudingListExpect(ProductAuding product);
	//通过商户编号获取产品审核列表
	public List<ProductAuding> getProductByMerchantCode(String merchantCode);
	//查询图片路径
	public String findScanPath(String merchantCode,String type);
	//更新商户产品表
	public void updateProduct(ProductAuding product);
	//更新商户产品表
	public void insertAuditRecode(TdAuditRecodeInfo audit);
	//查询商户信息
	public TdCustInfo selCustInfoByMerchantCode(String merchantCode);
	
	
	
	
	
	
}
