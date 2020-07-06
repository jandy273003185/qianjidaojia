package com.qifenqian.bms.basemanager.merchantincontrol.service;

import com.qifenqian.bms.basemanager.merchantincontrol.bean.MerchantInControl;
import com.qifenqian.bms.basemanager.merchantincontrol.dao.TdMerchantInControlDao;
import com.qifenqian.bms.basemanager.merchantincontrol.mapper.TdMerchantInControlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TdMerchantInControlService {
	@Autowired
	private TdMerchantInControlDao dao;
	
	@Autowired
	private TdMerchantInControlMapper mpper;
	
	public List<MerchantInControl> selectMerchantInControlList(MerchantInControl merchantInControl){
		return dao.selectMerchantInControlList(merchantInControl);
	}
	
	public void insert(MerchantInControl merchantInControl){
		//merchantInControl.setCreater(String.valueOf(WebUtils.getUserInfo().getUserId()));
		mpper.insert(merchantInControl);
	}
	
	public void update(MerchantInControl merchantInControl){
		//merchantInControl.setModified(String.valueOf(WebUtils.getUserInfo().getUserId()));
		mpper.update(merchantInControl);
	}
	
	public void delete(MerchantInControl merchantInControl){
		mpper.delete(merchantInControl);
	}
	
	public Integer verify(MerchantInControl merchantInControl){
		return mpper.verify(merchantInControl);
	}
	public Integer exists(MerchantInControl merchantInControl){
		return mpper.exists(merchantInControl);
	}
}
