package com.qifenqian.bms.basemanager.toPay.mapper;

import java.math.BigDecimal;
import java.util.List;

import org.mybatis.spring.annotation.MapperScan;

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

@MapperScan
public interface TopayMapper {
	/**
	 * 获取Tyy渠道对应的银行编号信息
	 * @return
	 */
	public List<TyyBankInfo> listTyyBankCode();
	/**
	 * 获取省份列表
	 */
	public List<ProvinceBean> listProvince();
	/**
	 * 根据省份code获取城市列表
	 */
	public List<CityNew> listCityByProvinceCode(String provinceCode);
	
	/**
	 * 批量插入单条代付信息
	 */
	public void saveSingleTopay(TopaySingleDetail singleDetail);
	
	/**
	 * 插入代付批次信息
	 */
	public void saveBatTopay(TopayBatDetail bean);
	

	/**
	 * 查询代付汇总信息
	 */
	public List<TopayBatDetail> listBatRecord(TopayBatDetail bean);

	/**
	 * 根据orderNo查询单笔代付信息
	 */
	public List<TopaySingleDetail> listSingleRecordByOrderNo(String orderNo);
	/**
	 * 修改单笔支付状态信息
	 */
	public void updateSinglePayInfo(TopaySingleDetail bean);
	
	/**
	 * 修改交易汇总状态
	 */
	public void updateBatchPayInfo(TopayBatDetail bean);
	/**
	 * 获取系统用户id与名字
	 * @return
	 */
	public List<Creater> listCreater();

	/**
	 * 获取省份code
	 * @param provinceName
	 * @return
	 */
	public ProvinceBean selProvinceByName(String provinceName);
	
	/**
	 * 获取城市code
	 * @param provinceName
	 * @return
	 */
	public CityNew selCityByName(String cityName);
	
	/**
	 * 获取银行code
	 * @param bankName
	 * @return
	 */
	public TyyBankInfo selBankCodeByName(String bankName);
	
	/**
	 * 查询商户号是否存在
	 * @param paerMerchantCode
	 * @return
	 */
	public String selInfoByMerchantCode(String paerMerchantCode);
	
	public BigDecimal sumTransAmtInToday(String recAccountNo);

	/**
	 * 根据日期查询充值利润
	 */
	public ProfitBean getProfitOfRecharge(DateBean dateBean);

	/**
	 * 根据日期查询手续费利润
	 */
	public ProfitBean getProfitOfPoundage(DateBean dateBean);
	/**
	 * 插入代付收益提现信息 
	 * @param bean
	 * @return
	 */
	public int insertBatInfo(TdPaymentBatInfoBean bean);
	
	/**
	 * 批量插入批量明细表
	 * @param selectBean
	 * @return
	 */
	public int insertPaymentDetail(TdPaymentBatDetailBean bean);
	
	/**
	 * 卡宾获取银行卡信息
	 */
	public TbBankCardBin getBankNameByBankNum(String bankNum);
	/**
	 *	根据渠道名字获取渠道信息
	 */
	public ToPayChannelInfo getChnanelInfoByChannelName(String channelName);
	/**
	 * 根据BatNo插入CORESN
	 */
	public void updateCoreSn(TdPaymentBatDetailBean bean);
}
