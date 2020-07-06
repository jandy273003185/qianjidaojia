package com.qifenqian.bms.basemanager.merchantincontrol.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.merchantincontrol.bean.MerchantInControl;

@MapperScan
public interface TdMerchantInControlMapper {
	
	List<MerchantInControl> selectMerchantInControlList(MerchantInControl merchantInControl);
	
	
	public void insert(MerchantInControl merchantInControl);
	
	public void update(MerchantInControl merchantInControl);
	
	public void delete(MerchantInControl merchantInControl);
	
	public Integer verify(MerchantInControl merchantInControl);


	public Integer exists(MerchantInControl merchantInControl);
	
	
}
