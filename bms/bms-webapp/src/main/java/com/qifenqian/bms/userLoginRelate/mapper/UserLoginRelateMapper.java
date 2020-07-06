package com.qifenqian.bms.userLoginRelate.mapper;

import com.qifenqian.bms.userLoginRelate.bean.UserLoginRelate;
import org.mybatis.spring.annotation.MapperScan;
import java.util.List;

/**
 * 商户产品信息持久层
 * 
 * @project qifenqian-bms
 * @fileName MerchantPorductMapper.java
 * @author wuzz
 * @date 2019年11月7日
 * @memo
 */
@MapperScan
public interface UserLoginRelateMapper {
		
	/**
	 * 查询信息列表
	 * 
	 * @return
	 */
	public List<UserLoginRelate> selectUserLoginRelateList(UserLoginRelate userLoginRelate);
	
	/**
	 * 根据ID查询信息
	 * 
	 * @return
	 */
	public UserLoginRelate selectUserLoginRelateById(UserLoginRelate userLoginRelate);
	
	/**
	 * 新增商户产品信息
	 * 
	 * @param merchantProduct
	 * @return
	 */
	public int insertUserLoginRelate(UserLoginRelate userLoginRelate);
	
	/**
	 * 修改商户产品信息
	 * 
	 * @param merchantProduct
	 * @return
	 */
	public int updateUserLoginRelate(UserLoginRelate userLoginRelate);

}
