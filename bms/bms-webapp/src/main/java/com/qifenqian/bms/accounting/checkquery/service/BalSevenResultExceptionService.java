package com.qifenqian.bms.accounting.checkquery.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.qifenqian.bms.accounting.checkquery.bean.BalSevenResultEqual;
import com.qifenqian.bms.accounting.checkquery.bean.BalSevenResultException;
import com.qifenqian.bms.accounting.checkquery.dao.BalSevenResultExceptionDao;

@Service
public class BalSevenResultExceptionService {
	@Resource
	private BalSevenResultExceptionDao dao;
	
	/**
	 * 交广科技对账差错报表
	 * @param resultException
	 * @return
	 */
	public List<BalSevenResultException> selectErrorList(BalSevenResultException resultException){
		return dao.selectErrorList(resultException);
	}
	/**
	 * 交广科技对账一致报表
	 * @param resultEqual
	 * @return
	 */
	public List<BalSevenResultEqual> selectFitList(BalSevenResultEqual resultEqual){
		return dao.selectFitList(resultEqual);
	}


}
