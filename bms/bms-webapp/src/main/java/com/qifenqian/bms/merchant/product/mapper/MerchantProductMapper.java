package com.qifenqian.bms.merchant.product.mapper;

import com.qifenqian.bms.merchant.product.bean.MerchantProduct;
import com.qifenqian.bms.merchant.product.bean.Product;
import org.mybatis.spring.annotation.MapperScan;
import java.util.List;

/**
 * 商户产品信息持久层
 * 
 * @project qifenqian-bms
 * @fileName MerchantPorductMapper.java
 * @author wuzz
 * @date 2019年11月7日
 * @memo
 */
@MapperScan
public interface MerchantProductMapper {
		
	/**
	 * 查询商户产品信息列表
	 * 
	 * @return
	 */
	public List<MerchantProduct> selectMerchantProductList(MerchantProduct merchantProduct);
	
	/**
	 * 查询产品信息
	 * 
	 * @return
	 */
	public List<Product> selectProduct();
	
	/**
	 * 根据ID查询产品信息
	 * 
	 * @return
	 */
	public Product selectProductById(String productId);	
	
	
	/**
	 * 根据商户编号和产品编号查询
	 * 
	 * @return
	 */
	public MerchantProduct selectMerchantProductByCode(MerchantProduct merchantProduct);
	


	/**
	 * 新增商户产品信息
	 * 
	 * @param merchantProduct
	 * @return
	 */
	public int insertMerchantProductSingle(MerchantProduct merchantProduct);
	
	
	/**
	 * 修改商户产品信息
	 * 
	 * @param merchantProduct
	 * @return
	 */
	public int updateMerchantProduct(MerchantProduct merchantProduct);


	/**
	 * 删除商户产品信息
	 * @param merchantProduct
	 * @return
	 */
	public int deleteMerchantProduct(MerchantProduct merchantProduct);
	
	/**
	 * 删除商户设备信息
	 * @param merchantId
	 * @return
	 */
	public int deleteMerchantSign(String merchantId);
	
}
