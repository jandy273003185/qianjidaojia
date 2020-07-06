package com.qifenqian.bms.merchant.product.dao;
import com.qifenqian.bms.merchant.product.bean.MerchantProduct;
import com.qifenqian.bms.merchant.product.mapper.MerchantProductMapper;
import com.qifenqian.bms.platform.web.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * dao层，一般分页需要
 * 
 * @project qifenqian-bms
 * @fileName MerchantProductDAO.java
 * @author wuzz
 * @date 2019年11月7日
 * @memo
 */
@Repository
public class MerchantProductDAO{

	@Autowired
	private MerchantProductMapper merchantPorductMapper;
	
	/**
	 * 分页查询商户产品列表
	 * @return
	 */
	@Page
	public List<MerchantProduct> selectMaterielListByPage(MerchantProduct merchantProduct) {
		return merchantPorductMapper.selectMerchantProductList(merchantProduct);
	}
}


