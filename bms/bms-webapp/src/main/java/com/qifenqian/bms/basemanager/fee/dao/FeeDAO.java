package com.qifenqian.bms.basemanager.fee.dao;

import java.util.List;

import javax.annotation.Resource;

import org.activiti.engine.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.fee.bean.Fee;
import com.qifenqian.bms.basemanager.fee.mapper.FeeMapper;
import com.qifenqian.bms.platform.web.page.Page;

/**
 * dao层，一般分页需要
 * 
 * @project sevenpay-bms-web
 * @fileName CityDAO.java
 * @author Dayet
 * @date 2015年5月18日
 * @memo 
 */
@Repository
public class FeeDAO{

	@Autowired
	private FeeMapper feeMapper;
	
	@Resource
	private TaskService taskService;
	
	
	/**
	 * 分页查询手续列表
	 * @return
	 */
	@Page
	public List<Fee> selectFees(Fee fee) {
		return feeMapper.selectFees(fee);
	}
	
}


