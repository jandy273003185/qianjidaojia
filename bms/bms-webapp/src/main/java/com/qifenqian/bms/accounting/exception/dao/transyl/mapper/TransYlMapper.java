package com.qifenqian.bms.accounting.exception.dao.transyl.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.accounting.exception.dao.transyl.bean.TransYl;

/**
 * @project sevenpay-bms-web
 * @fileName TransYlMapper.java
 * @author huiquan.ma
 * @date 2015年11月3日
 * @memo 
 */
@MapperScan
public interface TransYlMapper {

	/**
	 * 查询银联交易列表
	 * @param transId
	 * @return
	 */
	List<TransYl> selectBySn(String transSn);
	/***
	 * 修改银联交易新
	 * @param updateBean
	 * @return
	 */
	int updateTransYl(TransYl updateBean);
	/**
	 * 新增银联交易
	 * @param insertBean
	 * @return
	 */
	int insertTransYl(TransYl insertBean);
	
	/**
	 * 根据transId查询
	 * @param transId
	 * @return
	 */
	TransYl selectTransYlByTransId(String  transId);

	/**
	 * 根据transSnow查询
	 * @param transSn
	 * @return
	 */
	TransYl selectTransYlByTransSn(String transSn);
}


