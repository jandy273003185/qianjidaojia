package com.qifenqian.bms.app.ad.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.app.ad.bean.AdManageBean;

@MapperScan
public interface AdManageMapper {
	/**
	 * 根据查询条件获取APP登录页广告列表
	 */
	public List<AdManageBean> listAdManageInfo(AdManageBean queryBean);
	/**
	 * 保存APP登录页广告信息
	 */
	public void saveAdManageInfo(AdManageBean adManageInfo);
	/**
	 * 修改APP登录页广告信息
	 */
	public void updateAdManageInfo(AdManageBean adManageInfo);
	/**
	 * 删除APP登录页广告信息
	 */
	public void deleteAdManageInfoByAdId(String adId);
}
