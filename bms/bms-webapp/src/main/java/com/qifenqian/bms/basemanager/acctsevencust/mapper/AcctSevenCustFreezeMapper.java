package com.qifenqian.bms.basemanager.acctsevencust.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.acctsevencust.bean.AcctSevenCustFreeze;

@MapperScan
public interface AcctSevenCustFreezeMapper {

	/**
	 * 保存
	 * 
	 * @param insertBean
	 * @return
	 */
	int insert(AcctSevenCustFreeze insertBean);

	/**
	 * 修改返回信息
	 * 
	 * @param insertBean
	 * @return
	 */
	int update(AcctSevenCustFreeze insertBean);

	/**
	 * 查询返回msgId
	 * 
	 * @param queryBean
	 * @return
	 */
	List<AcctSevenCustFreeze> selectMsgId(AcctSevenCustFreeze queryBean);

}
