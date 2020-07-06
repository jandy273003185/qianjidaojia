package com.qifenqian.bms.basemanager.dictData.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.dictData.bean.DictData;
@MapperScan
public interface DictDataMapper {
	
	/***
	 * 查询字典数据
	 * @param dict
	 * @return
	 */
	public List<DictData> selectListDict(DictData dict);
	/**
	 * 新增字典
	 * @param dict
	 */
	public void insertDict(DictData dict);
	
	/**
	 * 更新字典
	 * @param dict
	 */
	public void updateDict(DictData dict);
	
	/**
	 * 删除字典
	 * @param dict
	 */
	public void delDict(DictData dict);
	
	
	List<DictData> selectDictionaryBeanByRestrict(DictData queryBean);
	
	DictData selectByDataPath(@Param("dataPath") String dataPath);
	
	public String getDataValueByPath(String dictPath);
}
