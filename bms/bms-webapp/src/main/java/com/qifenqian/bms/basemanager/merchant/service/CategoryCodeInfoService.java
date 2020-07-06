package com.qifenqian.bms.basemanager.merchant.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.merchant.bean.CategoryCodeInfo;
import com.qifenqian.bms.basemanager.merchant.dao.CategoryCodeInfoDAO;

@Service
public class CategoryCodeInfoService {

	@Autowired
	private CategoryCodeInfoDAO categoryCodeInfoDAO;
	
	public List<CategoryCodeInfo> getCategoryCodeInfoList(String categoryTypeId,String channleCode){
		
		//华润通道
		if("iCr".equals(channleCode)){
			return categoryCodeInfoDAO.getCategoryCodeInfoList(categoryTypeId);
		}else if ("helipay".equals(channleCode)){//合利宝
			return categoryCodeInfoDAO.getHelipayCategoryCodeInfoList(categoryTypeId);
		}else {
			return null;
		}
	}

	public List<CategoryCodeInfo> getCategoryCodeInfoListById(String categoryId) {
		return categoryCodeInfoDAO.getCategoryCodeInfoListById(categoryId);
	}
}
