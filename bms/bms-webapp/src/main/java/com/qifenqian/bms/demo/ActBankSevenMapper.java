package com.qifenqian.bms.demo;

import java.util.List;

import com.qifenqian.bms.common.annotation.MapperScanCore;

/**
 * @project sevenpay-bms-web
 * @fileName ActBankSevenMapper.java
 * @author huiquan.ma
 * @date 2015年7月22日
 * @memo 
 */
@MapperScanCore
public interface ActBankSevenMapper {

	List<ActBankSevenFlow> selectBankList();
}


