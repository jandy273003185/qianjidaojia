package com.qifenqian.bms.basemanager.custInfo.mapper;

import java.util.List;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.common.annotation.MapperScanCore;
import com.sevenpay.invoke.transaction.querybankcard.bean.BankCard;

/**
 * 消费者信息管理
 * 
 * @author pc
 *
 */
@MapperScanCore
public interface QueryAccountMapper {

	/**
	 * 查询七分钱
	 * 
	 * @param custInfo
	 */
	public List<TdCustInfo> getActSevenCustList(TdCustInfo custInfo);
	
	public List<BankCard> queryBankCardList(String custId);
	
	public int updateAcctNameByCustName(TdCustInfo updateBean);
	
}
