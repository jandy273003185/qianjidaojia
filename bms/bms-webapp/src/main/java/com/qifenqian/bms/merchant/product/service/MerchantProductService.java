package com.qifenqian.bms.merchant.product.service;
import com.qifenqian.bms.merchant.product.bean.MerchantProduct;
import com.qifenqian.bms.merchant.product.dao.MerchantProductDAO;
import com.qifenqian.bms.merchant.product.mapper.MerchantProductMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;



/**
 * 商户产品服务层
 * 
 * @project qifenqian-bms
 * @fileName MerchantProductService.java
 * @author wuzz
 * @date 2019年11月7日
 * @memo
 */
@Service
public class MerchantProductService {
	
	@Autowired
	private MerchantProductDAO merchantProductDAO;
	@Autowired
	private MerchantProductMapper merchantProductMapper;
	
	/**
	 * 查询商户产品列表-分页
	 * 
	 * @return
	 */
	public List<MerchantProduct> selectMaterielListByPage(MerchantProduct merchantProduct) {
		
		return merchantProductDAO.selectMaterielListByPage(merchantProduct);
	}

	/**
	 * 新增商户产品信息
	 * 
	 * @param materiel
	 * @return
	 */
	public int insertMaterielSingle(MerchantProduct merchantProduct) {
		
		if (null == merchantProduct) {
			throw new IllegalArgumentException("商户产品对象为空");
		}
		if (StringUtils.isEmpty(merchantProduct.getMerchantCode())) {
			throw new IllegalArgumentException("商户编号为空");
		}
		if (StringUtils.isEmpty(merchantProduct.getProductId())) {
			throw new IllegalArgumentException("产品编号为空");
		}
		return merchantProductMapper.insertMerchantProductSingle(merchantProduct);
		
	}


	/**
	 * 修改商户产品信息
	 * 
	 * @param merchantProduct
	 * @return
	 */
	public void updateMerchantProduct(MerchantProduct merchantProduct) {
		
		if (!StringUtils.isEmpty(merchantProduct.getProductRateStr())) {
			merchantProduct.setProductRate(new BigDecimal(merchantProduct.getProductRateStr()));
		}
		if (!StringUtils.isEmpty(merchantProduct.getRechargeRateStr())) {
			merchantProduct.setRechargeRate(new BigDecimal(merchantProduct.getRechargeRateStr()));
		}
				
		merchantProductMapper.updateMerchantProduct(merchantProduct);
				
				
	}
	
	
	/**
	 * 根据商户代码和产品ID删除商户产品信息
	 * 
	 * @param merchantProduct
	 * @return
	 */
	public void deleteMerchantProduct(MerchantProduct merchantProduct) {
		if (null == merchantProduct) {
			throw new IllegalArgumentException("商户产品信息为空");
		}
		if (StringUtils.isEmpty(merchantProduct.getMerchantCode())) {
			throw new IllegalArgumentException("商户代码为空");
		}
		if (StringUtils.isEmpty(merchantProduct.getProductId())) {
			throw new IllegalArgumentException("产品编号为空");
		}

		merchantProductMapper.deleteMerchantProduct(merchantProduct);		
		
	}
	
	/**
	 * 根据商户代码和产品编号查询
	 * 
	 * @param merchantProduct
	 * @return
	 */
	public MerchantProduct selectMerchantProductByCode(MerchantProduct merchantProduct) {
							
		if (StringUtils.isEmpty(merchantProduct.getMerchantCode())) {
			throw new IllegalArgumentException("商户代码为空");
		}
		if (StringUtils.isEmpty(merchantProduct.getProductId())) {
			throw new IllegalArgumentException("产品ID为空");
		}
		return merchantProductMapper.selectMerchantProductByCode(merchantProduct);
	}

	
}
