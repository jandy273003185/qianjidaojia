package com.qifenqian.bms.app.creditcard.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qifenqian.bms.app.creditcard.bean.CreditCardManageBean;
import com.qifenqian.bms.app.creditcard.dao.CreditCardManageDao;
import com.qifenqian.bms.basemanager.utils.GenSN;

/**
 * 信用卡申请链接Service
 * @author hongjiagui
 *
 */
@Service
public class CreditCardManageService {

	private static Logger logger = LoggerFactory.getLogger(CreditCardManageService.class);

	@Autowired
	private CreditCardManageDao creditCardManageDao;
	/**
	 * 根据条件查询信用卡信息
	 * @param queryBean
	 * @return
	 */
	public List<CreditCardManageBean> selectCreditCardList(CreditCardManageBean queryBean){
		
		return creditCardManageDao.selectCreditCardManage(queryBean);
	}
	/**
	 * 查询信用卡列表总条数
	 */
	public int getCountOfCard() {
		return creditCardManageDao.getCountOfCard();
	}
	/**
	 * 保存信用卡信息
	 * @param bean
	 * @return
	 */
	public String saveCreditCard(CreditCardManageBean bean) {
		logger.info("增加信用卡信息");		
		try {
			bean.setCardId(GenSN.getSN());
			//新插入的信用卡信息排序默认为最后
			bean.setSort(creditCardManageDao.getCountOfCard()+1);
			//将状态设置为正常状态
			bean.setStatus("1");
			//将当前时间设为插入时间
			bean.setCreateTime(new Date());
			creditCardManageDao.saveCreditCard(bean);
			return "SUCCESS";
		}catch(Exception e) {
			logger.error("增加信用卡信息",e);
			e.printStackTrace();
			return "FALSE";
		}
		
	}
	/**
	 * 修改信用卡信息
	 * @param creditCard
	 * @return
	 */
	public String updateCreditCard(CreditCardManageBean creditCard) {
		logger.info("修改信用卡信息");
		int oldSort = creditCardManageDao.getSortByCardId(creditCard.getCardId());
		//一张卡的顺序改变，其他信用卡的顺序也会变化
		if(oldSort != creditCard.getSort()) {
			changeSort(oldSort, creditCard.getSort());
		}
		try {
			creditCardManageDao.updateCreditCard(creditCard);
			return "SUCCESS";
		}catch(Exception e) {
			logger.error("修改信用卡信息",e);
			e.printStackTrace();
			return "FALSE";
		}
		
	}
	/**
	 * 一张信用卡排序发生变化，某些信用卡的排序也会受到影响
	 */
	public void changeSort(int oldSort,int newSort) {
		//当排序的数字变大时，则oldSort+1至newSort范围内的信用卡排序都需要-1
		if(oldSort < newSort) {
			//取出变化范围内的List集合
			List<CreditCardManageBean> list = creditCardManageDao.listCreditCardBySort(oldSort+1, newSort);
			for (CreditCardManageBean creditCardManageBean : list) {
				creditCardManageBean.setSort(creditCardManageBean.getSort()-1);
				creditCardManageDao.updateSortByCardId(creditCardManageBean);
			}//当排序的数字减小时，则newSort至oldort-1范围内的信用卡排序都需要+1
		}else if(oldSort > newSort) {
			List<CreditCardManageBean> list = creditCardManageDao.listCreditCardBySort(newSort, oldSort-1);
			for (CreditCardManageBean creditCardManageBean : list) {
				creditCardManageBean.setSort(creditCardManageBean.getSort()+1);
				creditCardManageDao.updateSortByCardId(creditCardManageBean);
			}
		}
	}
	
	/**
	 * 根据信用卡ID删除信用卡信息
	 */
	public String deleteCreditCardByCardId(String cardId) {
		logger.info("删除信用卡信息,cardId为" + cardId);
		try {
			int maxCount = creditCardManageDao.getCountOfCard();
			int oldSort = creditCardManageDao.getSortByCardId(cardId);
			//删除信息，应该把后面的数据排序往前提一位
			changeSort(oldSort,maxCount);
			creditCardManageDao.deleteCreditCardByCardId(cardId);
			return "SUCCESS";
		}catch(Exception e) {
			logger.error("删除信用卡信息,cardId为" + cardId +"时出错，错误信息:"+e);
			return "FALSE";
		}
	}
}
