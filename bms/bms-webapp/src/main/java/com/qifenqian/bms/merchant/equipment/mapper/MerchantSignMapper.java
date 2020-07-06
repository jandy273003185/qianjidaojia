package com.qifenqian.bms.merchant.equipment.mapper;

import com.qifenqian.bms.merchant.equipment.bean.DeviceLogin;
import com.qifenqian.bms.merchant.equipment.bean.MerchantSign;
import org.mybatis.spring.annotation.MapperScan;
import java.util.List;

/**
 * 商户设备信息持久层
 * 
 * @project qifenqian-bms
 * @fileName MerchantSignMapper.java
 * @author wuzz
 * @date 2019年11月9日
 * @memo
 */
@MapperScan
public interface MerchantSignMapper {
		
	/**
	 * 查询商户产品信息列表
	 * 
	 * @return
	 */
	public List<MerchantSign> selectMerchantSignList(MerchantSign merchantSign);
		
	
	/**
	 * 根据商户ID查询商户设备信息
	 * 
	 * @return
	 */
	public MerchantSign selectMerchantSignByMerIdAndTerNo(MerchantSign merchantSign);		
	
	
	/**
	 * 根据ID查询商户设备信息
	 * 
	 * @return
	 */
	public MerchantSign selectMerchantSignById(int id);
	
	
	
	/**
	 * 新增商户设备信息
	 * 
	 * @param merchantSign
	 * @return
	 */
	public int insertMerchantSign(MerchantSign merchantSign);
	

	/**
	 * 修改商户设备信息
	 * 
	 * @param merchantSign
	 * @return
	 */
	public int updateMerchantSign(MerchantSign merchantSign);
	
	
	/**
	 * 删除商户设备信息
	 * @param merchantId
	 * @return
	 */
	public int deleteMerchantSign(int id);
	
	/**
	 * @param deviceLogin 
	 * @return  zhanggc 查询设备详情
	 */
	public  List<DeviceLogin>  selectDeviceLoginById(DeviceLogin deviceLogin);
	
}
