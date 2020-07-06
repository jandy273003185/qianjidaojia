package com.qifenqian.bms.basemanager.dictData.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.dictData.bean.DictData;
import com.qifenqian.bms.basemanager.dictData.dao.DictDataDAO;
import com.qifenqian.bms.basemanager.dictData.mapper.DictDataMapper;

@Service
public class DictDataService {
	
	@Autowired
	private DictDataDAO  dictDataDao;
	
	@Autowired
	private DictDataMapper dictDataMapper;
	/**
	 * 查询数据字典列表
	 * @param data
	 * @return
	 */
	public List<DictData> selectDictDataList(DictData data){
		return dictDataDao.selectDictDataList(data);
	}
	
	/**
	 * 新增数据字典
	 * @param data
	 */
	public void insertDict(DictData data){
		dictDataMapper.insertDict(data);
	}
	
	/**
	 * 修改数据字典
	 * @param dictData
	 */
	public void updateDict(DictData dictData) {
		dictDataMapper.updateDict(dictData);
		
	}
	
	/**
	 * 删除数据字典
	 * @param id
	 */
	public void deleteDictData(DictData dictData) {
		dictDataMapper.delDict(dictData);
		
	}

	public List<DictData> selectDictionaryBeanByRestrict(DictData queryBean) {
		return  dictDataDao.selectDictionaryBeanByRestrict(queryBean);
	}
	
	/**
	 * 根据字典路径查询字典信息
	 * @param dataPath
	 * @return
	 */
	public DictData selectByDataPath(String dataPath){
		return dictDataMapper.selectByDataPath(dataPath);
	}
}
