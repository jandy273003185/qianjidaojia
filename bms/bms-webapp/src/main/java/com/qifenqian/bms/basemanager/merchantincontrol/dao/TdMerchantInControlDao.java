package com.qifenqian.bms.basemanager.merchantincontrol.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.merchantincontrol.bean.MerchantInControl;
import com.qifenqian.bms.basemanager.merchantincontrol.mapper.TdMerchantInControlMapper;
import com.qifenqian.bms.platform.web.page.Page;
@Repository
public class TdMerchantInControlDao {
	@Autowired
	private TdMerchantInControlMapper mapper;
	@Page
	public List<MerchantInControl> selectMerchantInControlList(MerchantInControl merchantInControl){
		return mapper.selectMerchantInControlList(merchantInControl);
	}
}
