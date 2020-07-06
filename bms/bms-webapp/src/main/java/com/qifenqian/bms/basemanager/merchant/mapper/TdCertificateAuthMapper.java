package com.qifenqian.bms.basemanager.merchant.mapper;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.merchant.bean.TdCertificateAuth;


@MapperScan
public interface TdCertificateAuthMapper {

    int deleteByPrimaryKey(Integer authId);

    int insert(TdCertificateAuth record);

    int insertSelective(TdCertificateAuth record);

    int updateByPrimaryKeySelective(TdCertificateAuth record);

    int updateByPrimaryKey(TdCertificateAuth record);

    TdCertificateAuth findTdCertificateAuthByCustId(String custId);
}
