package com.qifenqian.bms.basemanager.merchant.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.merchant.bean.CategoryCodeInfo;

/**
 * 
 * @author dayet
 * @date 2015.5.18
 */

@MapperScan
public interface CategoryCodeInfoMapper {
	
	
	public List<CategoryCodeInfo> getCategoryCodeInfoList(@Param("categoryTypeId") String categoryTypeId);

	public List<CategoryCodeInfo> getCategoryCodeInfoListById(String categoryId);

	public List<CategoryCodeInfo> getHelipayCategoryCodeInfoList(
			String categoryTypeId);
}
