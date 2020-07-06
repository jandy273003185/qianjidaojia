package com.qifenqian.bms.basemanager.advertisement.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.advertisement.bean.Ad;
@MapperScan
public interface AdMapper {
	/**
	 * 广告信息展现
	 * @param Ad
	 * @return
	 */
	public List<Ad> selectAdList(Ad queryBean);
	
	/**
	 * 增加广告
	 * @param Ad
	 */
	public void insertAd(Ad ad);
	
	/**
	 * 更新广告信息
	 * @param Ad
	 */
	public void updateAd(Ad ad);
	
	/***
	 * 按ID查询
	 * @param adId
	 * @return
	 */
	public Ad selectAdById(String adId);
	
	
}
