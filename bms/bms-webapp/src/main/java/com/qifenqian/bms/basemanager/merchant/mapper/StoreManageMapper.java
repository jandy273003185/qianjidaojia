package com.qifenqian.bms.basemanager.merchant.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.merchant.bean.StoreManage;



@MapperScan
public interface StoreManageMapper {
	
	public List<StoreManage> getStoreList(StoreManage storeManage);
	
	public void insert(StoreManage storeManage);
	
	public void update(StoreManage storeManage);
	
	public void delete(StoreManage storeManage);

	public Integer exists(StoreManage storeManage);
	
	public Integer repeats(StoreManage storeManage);

}
