package com.qifenqian.bms.accounting.unnamedProcessing.clearJgkjregister.mapper;

import java.util.List;

import com.qifenqian.bms.accounting.unnamedProcessing.clearJgkjregister.bean.ClearJgkjRegister;
import com.qifenqian.bms.common.annotation.MapperScanCore;

@MapperScanCore
public interface ClearJgkjRegisterMapper {
	
	List<ClearJgkjRegister> selectClearJgkjRegisterByList(ClearJgkjRegister clearJgkjRegister);
}
