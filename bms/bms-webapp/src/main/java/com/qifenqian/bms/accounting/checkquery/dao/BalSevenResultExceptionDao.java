package com.qifenqian.bms.accounting.checkquery.dao;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.checkquery.bean.BalSevenResultEqual;
import com.qifenqian.bms.accounting.checkquery.bean.BalSevenResultException;
import com.qifenqian.bms.accounting.checkquery.mapper.BalSevenResultEqualMapper;
import com.qifenqian.bms.accounting.checkquery.mapper.BalSevenResultExceptionMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class BalSevenResultExceptionDao {
	@Resource
	private BalSevenResultExceptionMapper mapper;
	@Resource
	private BalSevenResultEqualMapper equalMapper;
	
	@Page
	public List<BalSevenResultException> selectErrorList(BalSevenResultException resultException){
		return mapper.selectErrorList(resultException);
	}
	
	@Page
	public List<BalSevenResultEqual> selectFitList(BalSevenResultEqual resultEqual){
		return equalMapper.selectFitList(resultEqual);
	}
	
}
