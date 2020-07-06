package com.qifenqian.bms.userLoginRelate.dao;
import com.qifenqian.bms.platform.web.page.Page;
import com.qifenqian.bms.userLoginRelate.bean.UserLoginRelate;
import com.qifenqian.bms.userLoginRelate.mapper.UserLoginRelateMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * dao层，一般分页需要
 * 
 * @project qifenqian-bms
 * @fileName MerchantProductDAO.java
 * @author wuzz
 * @date 2019年11月7日
 * @memo
 */
@Repository
public class UserLoginRelateDAO{

	@Autowired
	private UserLoginRelateMapper userLoginRelateMapper;
	
	/**
	 * 分页查询商户产品列表
	 * @return
	 */
	@Page
	public List<UserLoginRelate> selectUserLoginRelateList(UserLoginRelate userLoginRelate) {
		return userLoginRelateMapper.selectUserLoginRelateList(userLoginRelate);
	}
	/**
	 * 根据ID查询信息
	 * 
	 * @return
	 */
	public UserLoginRelate selectUserLoginRelateById(UserLoginRelate userLoginRelate) {
		return userLoginRelateMapper.selectUserLoginRelateById(userLoginRelate);
	}
	
	/**
	 * 新增商户产品信息
	 * 
	 * @param merchantProduct
	 * @return
	 */
	public int insertUserLoginRelate(UserLoginRelate userLoginRelate) {
		return userLoginRelateMapper.insertUserLoginRelate(userLoginRelate);
	}
	
	/**
	 * 修改商户产品信息
	 * 
	 * @param merchantProduct
	 * @return
	 */
	public int updateUserLoginRelate(UserLoginRelate userLoginRelate) {
		return	userLoginRelateMapper.updateUserLoginRelate(userLoginRelate);
	}
	
}


