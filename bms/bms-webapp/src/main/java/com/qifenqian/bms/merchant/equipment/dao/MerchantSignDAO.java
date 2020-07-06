package com.qifenqian.bms.merchant.equipment.dao;
import com.qifenqian.bms.merchant.equipment.bean.DeviceLogin;
import com.qifenqian.bms.merchant.equipment.bean.MerchantSign;
import com.qifenqian.bms.merchant.equipment.mapper.MerchantSignMapper;
import com.qifenqian.bms.platform.web.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * dao层，一般分页需要
 * 
 * @project qifenqian-bms
 * @fileName MerchantSignDAO.java
 * @author wuzz
 * @date 2019年11月9日
 * @memo
 */
@Repository
public class MerchantSignDAO{

	@Autowired
	private MerchantSignMapper merchantSignMapper;
	
	/**
	 * 分页查询商户设备列表
	 * @return
	 */
	@Page
	public List<MerchantSign> selectMerchantSignListByPage(MerchantSign merchantSign) {
		return merchantSignMapper.selectMerchantSignList(merchantSign);
	}
	/**
	 * @param deviceLogin 
	 * @return  zhanggc 查询设备详情
	 */
	@Page
	public  List<DeviceLogin> selectDeviceLoginById(DeviceLogin deviceLogin) {
		return merchantSignMapper.selectDeviceLoginById(deviceLogin);
	}
}


