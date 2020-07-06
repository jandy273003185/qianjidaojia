package com.qifenqian.bms.merchant.settle.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.merchant.settle.bean.MerchantChildSettle;
import com.qifenqian.bms.merchant.settle.bean.MerchantSettle;
import com.qifenqian.bms.merchant.settle.bean.MerchantSettleExport;

/**
 * @project sevenpay-bms-web
 * @fileName MerchantSettleMapper.java
 * @author huiquan.ma
 * @date 2015年10月10日
 * @memo
 */
@MapperScan
public interface MerchantSettleMapper {

	List<MerchantSettle> selectList(MerchantSettle selectBean);
	
	List<MerchantSettle> selectAgencyList(MerchantSettle selectBean);

	int updateById(MerchantSettle updateBean);

	MerchantSettle selectSingle(String id);

	int updateToSettle(MerchantSettle updateBean);

	List<MerchantSettleExport> selectExportList(MerchantSettle selectBean);
	
	List<MerchantSettleExport> selectAgencyExportList(MerchantSettle selectBean);

	List<MerchantSettle> selectListByIds(String[] ids);

	int insertSettleDetailById(MerchantSettle insertBean);

	int deleteSettleById(String id);

	int insertSettle(MerchantSettle insertBean);

	int insertSettleByTogetherBean(MerchantSettle togetherBean);

	int updateSettleDetailByTogetherId(String togetherId);

	List<MerchantSettle> selectDetailListByTogetherId(String togetherId);

	int insertChildSettle(MerchantChildSettle insertBean);

	int updateChildSettle(MerchantChildSettle insertBean);

	String selectCronSnByApplyCordId(String applyCoreId);
	
	String selectTdloginUserInfoByMouble(@Param("custId")String custId);
	
}
