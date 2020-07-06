package com.qifenqian.bms.basemanager.advertisement.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.basemanager.advertisement.bean.Ad;
import com.qifenqian.bms.basemanager.advertisement.dao.AdDao;
import com.qifenqian.bms.basemanager.advertisement.mapper.AdMapper;
import com.qifenqian.bms.platform.web.admin.user.bean.User;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;

@Service
public class AdService {
	@Autowired
	private AdMapper adMapper;
	
	@Autowired
	private AdDao adDao;
	
	/**
	 * 查询所有广告信息
	 * @return
	 */
	public List<Ad> selectAdList(Ad queryBean){
		return adDao.selectAdList(queryBean);
	}
	
	/**
	 * 增加广告
	 * @param Ad
	 */
	public void addAd(Ad insertBean){
		
		if(null == insertBean){
			throw new IllegalArgumentException("广告对象为空");
		}
		String adId = java.util.UUID.randomUUID().toString();
		insertBean.setAdId(adId);
		User user = WebUtils.getUserInfo();
		insertBean.setCreateId(String.valueOf(user.getUserId()));
		insertBean.setIsValid("1");
		adMapper.insertAd(insertBean);
	}
	
	/**
	 * 更新广告
	 * @param Ad
	 */
	public void updateAd(Ad ad){
		
		if(null == ad){
			throw new IllegalArgumentException("广告对象为空");
		}
		User user = WebUtils.getUserInfo();
		ad.setModifyId(String.valueOf(user.getUserId()));
		
		adMapper.updateAd(ad);
	}
	
	
	public Ad selectAdById(String adId){
		if(StringUtils.isEmpty(adId)){
			throw new IllegalArgumentException("广告编号称为空");			
		}
		return adMapper.selectAdById(adId);
	}
}
