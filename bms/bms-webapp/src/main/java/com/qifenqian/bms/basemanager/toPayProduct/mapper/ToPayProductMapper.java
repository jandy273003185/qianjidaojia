package com.qifenqian.bms.basemanager.toPayProduct.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.toPayProduct.bean.ToPayProduct;


@MapperScan
public interface ToPayProductMapper {

	/**
	 * 代付产品
	 * @param bean 
	 * @return
	 */
	public List<ToPayProduct> listProduct(ToPayProduct bean);

	public void updateRate(ToPayProduct bean);

	public void saveProductRate(ToPayProduct bean);


}
