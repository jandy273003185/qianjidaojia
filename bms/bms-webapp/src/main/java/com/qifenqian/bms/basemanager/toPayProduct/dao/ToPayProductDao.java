package com.qifenqian.bms.basemanager.toPayProduct.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.toPayProduct.bean.ToPayProduct;
import com.qifenqian.bms.basemanager.toPayProduct.mapper.ToPayProductMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class ToPayProductDao {

	@Autowired
	private ToPayProductMapper toPayProductMapper;
	
	@Page
	public List<ToPayProduct> listProduct(ToPayProduct bean){
		return toPayProductMapper.listProduct(bean);
		
	}

	public void updateRate(ToPayProduct bean) {
		toPayProductMapper.updateRate(bean);
		
	}

	public void saveProductRate(ToPayProduct bean) {
		toPayProductMapper.saveProductRate(bean);
		
	};

}
