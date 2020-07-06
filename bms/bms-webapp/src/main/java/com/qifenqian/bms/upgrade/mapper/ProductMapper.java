package com.qifenqian.bms.upgrade.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.upgrade.bean.ProductAuding;
import com.qifenqian.bms.upgrade.bean.ProductExport;
import com.qifenqian.bms.upgrade.bean.TdAuditRecodeInfo;
@MapperScan
public interface ProductMapper {
	 //获取代理商审核列表
	public List<ProductAuding> getProductAuditList(ProductAuding product);
	 //获取图片路径
	public String findScanPath(@Param("merchantCode")String merchantCode,@Param("type")String type);
	//通过商户编号获取代理商审核列表
	public List<ProductAuding> ProductByMerchantCode(@Param("merchantCode")String merchantCode);
	//导出报表
	public List<ProductExport> getAgencyAudingListExport(ProductAuding product);
	//更新产品信息表
	public void updateProduct(ProductAuding product);
	//添加审核信息表
	public void insertAuditRecode(TdAuditRecodeInfo audit);
	//查询商户信息
	public TdCustInfo selCustInfoByMerchantCode(@Param("merchantCode")String merchantCode);
	
	
	
}
