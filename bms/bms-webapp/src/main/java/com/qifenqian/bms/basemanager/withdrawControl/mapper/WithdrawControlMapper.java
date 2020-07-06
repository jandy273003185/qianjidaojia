package com.qifenqian.bms.basemanager.withdrawControl.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.withdrawControl.bean.WithdrawControl;

@MapperScan
public interface WithdrawControlMapper {

	public List<WithdrawControl> selectAll(WithdrawControl withdrawControl);
	
	public void addWithdrawControl(WithdrawControl withdrawControl);
	
	public WithdrawControl selectWithdrawControl(WithdrawControl tradeControl);
	
	public void editTradeControl(WithdrawControl tradeControl);
	
	public void deleteTradeControl(WithdrawControl tradeControl);

	public WithdrawControl selectCustIdByMobile(@Param("mobile") String mobile);
}
