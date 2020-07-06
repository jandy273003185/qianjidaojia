package com.qifenqian.bms.accounting.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.dictData.mapper.DictDataMapper;



@Service
public class DictionaryUtils {

	@Autowired
	private DictDataMapper dictDataMapper;

	public String getDataValueByPath(String dictPath) {
		return dictDataMapper.getDataValueByPath(dictPath);
	}

}
