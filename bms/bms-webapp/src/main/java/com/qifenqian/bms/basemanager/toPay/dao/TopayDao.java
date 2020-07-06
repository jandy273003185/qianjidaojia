package com.qifenqian.bms.basemanager.toPay.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.city.bean.CityNew;
import com.qifenqian.bms.basemanager.city.bean.ProvinceBean;
import com.qifenqian.bms.basemanager.toPay.bean.Creater;
import com.qifenqian.bms.basemanager.toPay.bean.DateBean;
import com.qifenqian.bms.basemanager.toPay.bean.ProfitBean;
import com.qifenqian.bms.basemanager.toPay.bean.TbBankCardBin;
import com.qifenqian.bms.basemanager.toPay.bean.TdPaymentBatDetailBean;
import com.qifenqian.bms.basemanager.toPay.bean.TdPaymentBatInfoBean;
import com.qifenqian.bms.basemanager.toPay.bean.ToPayChannelInfo;
import com.qifenqian.bms.basemanager.toPay.bean.TopayBatDetail;
import com.qifenqian.bms.basemanager.toPay.bean.TopaySingleDetail;
import com.qifenqian.bms.basemanager.toPay.bean.TyyBankInfo;
import com.qifenqian.bms.basemanager.toPay.mapper.TopayMapper;
import com.qifenqian.bms.platform.web.page.Page;
/**
 * 代付Dao
 * @author hongjiagui
 *
 */
@Repository
public class TopayDao {

	@Autowired
	private TopayMapper topayMapper;
	/**
	 * 获取Tyy渠道对应的银行编号信息
	 * @return
	 */
	public List<TyyBankInfo> listTyyBankCode(){
		return topayMapper.listTyyBankCode();
	}
	
	/**
	 * 批量插入单条代付信息
	 */
	public void saveSingleTopay(TopaySingleDetail singleDetail) {
		topayMapper.saveSingleTopay(singleDetail);
	}
	
	/**
	 * 插入代付批次信息
	 */
	public void saveBatTopay(TopayBatDetail bean) {
		topayMapper.saveBatTopay(bean);
	}
	
	/**
	 * 获取省份列表
	 */
	public List<ProvinceBean> listProvince(){
		return topayMapper.listProvince();
	}
	/**
	 * 根据省份code获取城市列表
	 */
	public List<CityNew> listCityByProvinceCode(String provinceCode){
		return topayMapper.listCityByProvinceCode(provinceCode);
	}

	/**
	 * 查询代付汇总信息
	 */
	@Page
	public List<TopayBatDetail> listBatRecord(TopayBatDetail bean) {
		return topayMapper.listBatRecord(bean);
	}

	/**
	 * 根据orderNo查询单笔代付信息
	 */
	public List<TopaySingleDetail> listSingleRecordByOrderNo(String orderNo){
		return topayMapper.listSingleRecordByOrderNo(orderNo);
	}
	
	/**
	 * 修改单笔支付状态信息
	 */
	public void updateSinglePayInfo(TopaySingleDetail bean) {
		topayMapper.updateSinglePayInfo(bean);
	}
	
	/**
	 * 修改交易汇总状态
	 */
	public void updateBatchPayInfo(TopayBatDetail bean) {
		topayMapper.updateBatchPayInfo(bean);
	}
	
	/**
	 * 获取系统用户id与名字
	 * @return
	 */
	public List<Creater> listCreater(){
		return topayMapper.listCreater();
	}


	/**
	 * 根据省份获取省份code
	 */
	public ProvinceBean selProvinceByName(String provinceName) {
		// TODO Auto-generated method stub
		return  topayMapper.selProvinceByName(provinceName);
	}
	
	/**
	 * 根据城市获取城市code
	 */
	public CityNew selCityByName(String cityName) {
		// TODO Auto-generated method stub
		return  topayMapper.selCityByName(cityName);
	}

	/**
	 * 根据银行名称获取银行Code
	 */
	public TyyBankInfo selBankCodeByName(String bankName) {
		// TODO Auto-generated method stub
		return  topayMapper.selBankCodeByName(bankName);
	}

	/**
	 * 查询商户号是否存在
	 * @param paerMerchantCode
	 * @return
	 */
	public String selInfoByMerchantCode(String paerMerchantCode) {
		// TODO Auto-generated method stub
		return topayMapper.selInfoByMerchantCode(paerMerchantCode);
	}

	public BigDecimal sumTransAmtInToday(String recAccountNo) {
		// TODO Auto-generated method stub
		return topayMapper.sumTransAmtInToday(recAccountNo);
	}
	/**
	 * 根据日期查询充值利润
	 */
	public ProfitBean getProfitOfRecharge(DateBean dateBean) {
		return topayMapper.getProfitOfRecharge(dateBean);
	}

	/**
	 * 根据日期查询手续费利润
	 */
	public ProfitBean getProfitOfPoundage(DateBean dateBean){
		return topayMapper.getProfitOfPoundage(dateBean);
	}
	
	/**
	 * 插入代付收益提现信息 
	 * @param bean
	 * @return
	 */
	public int insertBatInfo(TdPaymentBatInfoBean bean) {
		return topayMapper.insertBatInfo(bean);
	}
	
	/**
	 * 批量插入批量明细表
	 * @param selectBean
	 * @return
	 */
	public int insertPaymentDetail(TdPaymentBatDetailBean bean) {
		return topayMapper.insertPaymentDetail(bean);
	}
	
	/**
	 * 卡宾获取银行卡信息
	 */
	public TbBankCardBin getBankNameByBankNum(String bankNum) {
		return topayMapper.getBankNameByBankNum(bankNum);
	}
	/**
	 *	根据渠道名字获取渠道信息
	 */
	public ToPayChannelInfo getChnanelInfoByChannelName(String channelName) {
		return topayMapper.getChnanelInfoByChannelName(channelName);
	}
	/**
	 * 根据BatNo插入CORESN
	 */
	public void updateCoreSn(TdPaymentBatDetailBean bean) {
		 topayMapper.updateCoreSn(bean);
	}
}
