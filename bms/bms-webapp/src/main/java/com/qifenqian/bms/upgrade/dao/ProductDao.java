package com.qifenqian.bms.upgrade.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.platform.web.page.Page;
import com.qifenqian.bms.upgrade.bean.ProductAuding;
import com.qifenqian.bms.upgrade.bean.ProductExport;
import com.qifenqian.bms.upgrade.bean.TdAuditRecodeInfo;
import com.qifenqian.bms.upgrade.mapper.ProductMapper;

@Repository
public class ProductDao {
	@Autowired
	private ProductMapper productMapper;
     //获取代理商审核列表
	@Page
	public List<ProductAuding> getAgencyAudingList(ProductAuding product){
		return productMapper.getProductAuditList(product);
	}
	
	//获取代理商审核列表
	@Page
	public List<ProductExport> getAgencyAudingListExport(ProductAuding product){
		return productMapper.getAgencyAudingListExport(product);
	}
	
	public String findScanPath(String merchantCode,String type){
		return productMapper.findScanPath(merchantCode,type);
	}
	
	//通过商户编号获取代理商审核列表
	public List<ProductAuding> findProductByMerchantCode(String merchantCode) {
		// TODO Auto-generated method stub
		return productMapper.ProductByMerchantCode(merchantCode);
	}

	public void updateProduct(ProductAuding product) {
		// TODO Auto-generated method stub
		productMapper.updateProduct(product);
	}

	public void insertAuditRecode(TdAuditRecodeInfo audit) {
		// TODO Auto-generated method stub
		productMapper.insertAuditRecode(audit);
	}

	public TdCustInfo selCustInfoByMerchantCode(String merchantCode) {
		// TODO Auto-generated method stub
		return productMapper.selCustInfoByMerchantCode(merchantCode);
	}
	
	
	
}
