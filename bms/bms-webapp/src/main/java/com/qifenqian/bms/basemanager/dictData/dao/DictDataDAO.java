package com.qifenqian.bms.basemanager.dictData.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.dictData.bean.DictData;
import com.qifenqian.bms.basemanager.dictData.mapper.DictDataMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class DictDataDAO {
	
	@Autowired
	private DictDataMapper dictDataMapper;
	
	@Page
	public List<DictData> selectDictDataList(DictData data){
		return dictDataMapper.selectListDict(data);
	}
	
	@Page
	public List<DictData> selectDictionaryBeanByRestrict(DictData queryBean) {
		return dictDataMapper.selectDictionaryBeanByRestrict(queryBean);
	}
}
