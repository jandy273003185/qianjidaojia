package com.qifenqian.bms.basemanager.admanage.mapper;

import com.qifenqian.bms.basemanager.admanage.bean.AdAllCustInfoVO;
import com.qifenqian.bms.basemanager.admanage.bean.AdCustInfoVO;
import com.qifenqian.bms.basemanager.admanage.bean.MchShopDO;
import com.qifenqian.bms.basemanager.admanage.bean.ShopAd;
import com.qifenqian.bms.basemanager.admanage.bean.TdMachineAdvert;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

@MapperScan
public interface ShopAdMapper {

    int batchSaveShopAd(@Param("list") List<ShopAd> list);

    int batchDeleteShopAd(@Param("mchShopDOList") List<MchShopDO> mchShopDOList);

    List<AdCustInfoVO> selectCustInfoOnDistributionAd(@Param("customName") String customName);

    List<AdAllCustInfoVO> selectAllCustInfoOnDistributionAd(@Param("customName") String customName, @Param("shopName") String shopName);

	List<String> selectTerminalNoByCustId(String custId);

	String selectPicturePathByAdId(String adId);

	int saveTdMachineAdvert(TdMachineAdvert tdMachineAdvert);

	int updateTdMachineAdvert(TdMachineAdvert tdMachineAdvert);
	
	String selectPictureUrlByAdId(String adId);
}
