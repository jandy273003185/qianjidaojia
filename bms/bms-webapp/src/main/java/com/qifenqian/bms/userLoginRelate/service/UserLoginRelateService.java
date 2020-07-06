package com.qifenqian.bms.userLoginRelate.service;
import com.qifenqian.bms.userLoginRelate.bean.UserLoginRelate;
import com.qifenqian.bms.userLoginRelate.dao.UserLoginRelateDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 商户产品服务层
 * 
 * @project qifenqian-bms
 * @fileName MerchantProductService.java
 * @author wuzz
 * @date 2019年11月7日
 * @memo
 */
@Service
public class UserLoginRelateService {
	
	@Autowired
	private UserLoginRelateDAO userLoginRelateDAO;

	
	/**
	 * 查询商户产品列表-分页
	 * 
	 * @return
	 */
	public List<UserLoginRelate> selectUserLoginRelateListByPage(UserLoginRelate userLoginRelate) {
		
		return userLoginRelateDAO.selectUserLoginRelateList(userLoginRelate);
	}

	/**
	 * 新增商户产品信息
	 * 
	 * @param materiel
	 * @return
	 */
	public int insertUserLoginRelate(UserLoginRelate userLoginRelate) {
		
		  if (null == userLoginRelate) {
			  throw new  IllegalArgumentException("商户产品对象为空"); 
		  }
		return userLoginRelateDAO.insertUserLoginRelate(userLoginRelate);
	}

	/**
	 * 修改商户产品信息
	 * 
	 * @param merchantProduct
	 * @return
	 */
	public void updateUserLoginRelate(UserLoginRelate userLoginRelate) {
		
		userLoginRelateDAO.updateUserLoginRelate(userLoginRelate);
				
	}
	
	/**
	 * 根据商户代码和产品编号查询
	 * @param merchantProduct
	 * @return
	 */
	public UserLoginRelate selectUserLoginRelateByCode(UserLoginRelate userLoginRelate) {					
		return userLoginRelateDAO.selectUserLoginRelateById(userLoginRelate);
	}

	
}
