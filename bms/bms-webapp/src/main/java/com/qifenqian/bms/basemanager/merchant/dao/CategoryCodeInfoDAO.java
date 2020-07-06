package com.qifenqian.bms.basemanager.merchant.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.merchant.bean.CategoryCodeInfo;
import com.qifenqian.bms.basemanager.merchant.mapper.CategoryCodeInfoMapper;

@Repository
public class CategoryCodeInfoDAO {

	@Autowired
	private CategoryCodeInfoMapper categoryCodeInfoMapper;
	
	public List<CategoryCodeInfo> getCategoryCodeInfoList(String categoryTypeId){
		
		return categoryCodeInfoMapper.getCategoryCodeInfoList(categoryTypeId);
	}

	public List<CategoryCodeInfo> getCategoryCodeInfoListById(String categoryId) {
		// TODO Auto-generated method stub
		return categoryCodeInfoMapper.getCategoryCodeInfoListById(categoryId);
	}
	
	public List<CategoryCodeInfo> getHelipayCategoryCodeInfoList(String categoryTypeId){
		
		return categoryCodeInfoMapper.getHelipayCategoryCodeInfoList(categoryTypeId);
	}
}
