package com.qifenqian.bms.basemanager.advertisement.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.advertisement.bean.Ad;
import com.qifenqian.bms.basemanager.advertisement.mapper.AdMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class AdDao {
	@Autowired
	private AdMapper adMapper;
	
	@Page
	public List<Ad> selectAdList(Ad queryBean){
		return adMapper.selectAdList(queryBean);
	}
}
