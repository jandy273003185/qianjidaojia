package com.qifenqian.bms.upgrade.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.upgrade.bean.ProductAuding;
import com.qifenqian.bms.upgrade.bean.ProductExport;
import com.qifenqian.bms.upgrade.bean.TdAuditRecodeInfo;
import com.qifenqian.bms.upgrade.dao.ProductDao;
import com.qifenqian.bms.upgrade.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
    private ProductDao productDao;
	public static Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Override
	public List<ProductAuding> getProductAudingList(ProductAuding product) {
		return productDao.getAgencyAudingList(product);
	}
	
	@Override
	public List<ProductExport> getProductAudingListExpect(ProductAuding product) {
		// TODO Auto-generated method stub
		return productDao.getAgencyAudingListExport(product);
	}

	@Override
	public String findScanPath(String merchantCode,String type) {
		// TODO Auto-generated method stub
		return productDao.findScanPath(merchantCode,type);
	}

	@Override
	public List<ProductAuding> getProductByMerchantCode(String merchantCode) {
		// TODO Auto-generated method stub
		return productDao.findProductByMerchantCode(merchantCode);
	}

	@Override
	@Transactional
	public void updateProduct(ProductAuding product) {
		productDao.updateProduct(product);
		
	}

	@Override
	@Transactional
	public void insertAuditRecode(TdAuditRecodeInfo audit) {
		// TODO Auto-generated method stub
		productDao.insertAuditRecode(audit);
	}

	@Override
	public TdCustInfo selCustInfoByMerchantCode(String merchantCode) {
		// TODO Auto-generated method stub
		return productDao.selCustInfoByMerchantCode(merchantCode);
	}

	
	
	

	
     
}
