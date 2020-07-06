package com.qifenqian.bms.app.creditcard.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.app.creditcard.bean.CreditCardManageBean;
import com.qifenqian.bms.app.creditcard.mapper.CreditCardManageMapper;
import com.qifenqian.bms.platform.web.page.Page;
/**
 * 信用卡申请链接管理Dao层
 * @author hongjiagui
 *
 */


@Repository
public class CreditCardManageDao {

	@Autowired
	private CreditCardManageMapper creditCardManageMapper;
	/**
	 * 根据查询条件获取信用卡申请链接列表
	 * @param queryBean 查询条件
	 * @return CreditCardManageBean集合
	 */
	@Page
	public List<CreditCardManageBean> selectCreditCardManage(CreditCardManageBean queryBean){
		return creditCardManageMapper.selectCreditCardList(queryBean);
	}
	/**
	 * 查询信用卡数据总条数
	 */
	public int getCountOfCard() {
		return creditCardManageMapper.selectCreditCardList(null).size();
	}
	/**
	 * 插入单条信用卡信息
	 * @param bean
	 */
	public void saveCreditCard(CreditCardManageBean bean) {
		creditCardManageMapper.saveCreditCardManage(bean);	
	}
	/**
	 * 更新信用卡信息
	 */
	public void updateCreditCard(CreditCardManageBean creditCardManageBean){
		creditCardManageMapper.updateCreditCardManage(creditCardManageBean);
	}
	/**
	 * 查询信用卡的排序
	 */
	public int getSortByCardId(String cardId) {
		return creditCardManageMapper.getSortByCardId(cardId);
	}
	/**
	 * 根据排序范围查询信用卡列表
	 */
	public List<CreditCardManageBean> listCreditCardBySort(int oldSort, int newSort){
		return creditCardManageMapper.listCreditCardBySort(oldSort, newSort);
	}
	/**
	 * 信用卡排序
	 */
	public void updateSortByCardId(CreditCardManageBean bean){
		creditCardManageMapper.updateSortByCardId(bean);
	}
	/**
	 * 删除信用卡信息
	 */
	public void deleteCreditCardByCardId(String cardId) {
		creditCardManageMapper.deleteCreditCardByCardId(cardId);
	}
}
