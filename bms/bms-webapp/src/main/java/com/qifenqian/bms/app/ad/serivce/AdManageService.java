package com.qifenqian.bms.app.ad.serivce;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.app.ad.bean.AdManageBean;
import com.qifenqian.bms.app.ad.dao.AdManageDao;
import com.qifenqian.bms.basemanager.utils.GenSN;
import com.qifenqian.bms.platform.web.admin.utils.WebUtils;
/**
 * APP登录页广告管理Service
 * @author hongjiagui
 *
 */
@Service
public class AdManageService {
	@Autowired
	private AdManageDao adManageDao;
	/**
	 * 根据查询条件获取APP登录页广告列表
	 */
	public List<AdManageBean> listAdManageInfo(AdManageBean queryBean){
		return adManageDao.listAdManageInfo(queryBean);
	}
	/**
	 * 保存APP登录页广告信息
	 */
	public String saveAdManageInfo(AdManageBean adManageInfo) {
		//设置ID为UUID生成的32位随机数
		adManageInfo.setAdId(GenSN.getSN());
		//设置当前时间为创建时间
		adManageInfo.setCreateTime(new Date());
		//首次创建的广告信息状态均默认为1 （1-正常，0-无效）
		adManageInfo.setStatus("1");
		//将当前登录用户的ID设置放入创建者的字段中
		adManageInfo.setCreateUser(String.valueOf(WebUtils.getUserInfo().getUserId()));
		try {
			adManageDao.saveAdManageInfo(adManageInfo);
			return "SUCCESS";
		}catch(Exception e) {
			e.printStackTrace();
			return "FALSE";
		}
	}
	/**
	 * 修改APP登录页广告信息
	 */
	public String updateAdManageInfo(AdManageBean adManageInfo) {
		try {
			adManageDao.updateAdManageInfo(adManageInfo);
			return "SUCCESS";
		}catch(Exception e) {
			e.printStackTrace();
			return "FALSE";
		}
	}
	/**
	 * 删除APP登录页广告信息
	 */
	public String deleteAdManageInfoByAdId(String adId) {
		try {
			adManageDao.deleteAdManageInfoByAdId(adId);
			return "SUCCESS";
		}catch(Exception e) {
			e.printStackTrace();
			return "FALSE";
		}
	}
}
