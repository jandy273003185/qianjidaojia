package com.qifenqian.bms.expresspay.mpper;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.expresspay.cardholderInfo.bean.Cardholder;

@MapperScan
public interface CommonMapper {

	String getCustId(Cardholder queryBean);

}
