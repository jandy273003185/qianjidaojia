package com.qifenqian.bms.app.creditcard.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.app.creditcard.bean.CreditCardManageBean;


/**
 * 信用卡申请链接管理Mapper
 * @author hongjiagui
 *
 */
@MapperScan
public interface CreditCardManageMapper {

	/**
	 * 根据查询条件查询信用卡申请链接信息
	 * @param queryBean 查询信息
	 * @return CreditCardManageBean实体的集合
	 */
	public List<CreditCardManageBean> selectCreditCardList(CreditCardManageBean queryBean);
	
	/**
	 * 插入一条信用卡申请链接信息
	 */
	public void saveCreditCardManage(CreditCardManageBean bean);
	/**
	 * 修改信用卡信息
	 */
	public void updateCreditCardManage(CreditCardManageBean creditCardManageBean);
	/**
	 * 查询信用卡的排序
	 */
	public int getSortByCardId(@Param("cardId")String cardId);
	/**
	 * 根据排序查询信用卡列表 
	 */
	public List<CreditCardManageBean> listCreditCardBySort(@Param("oldSort")int oldSort,@Param("newSort")int newSort);
	/**
	 * 根据cardId改变信用卡的排序
	 */
	public void updateSortByCardId(CreditCardManageBean creditCardManageBean);
	/**
	 * 根据cardId删除信用卡信息
	 */
	public void deleteCreditCardByCardId(@Param("cardId")String cardId);

}
