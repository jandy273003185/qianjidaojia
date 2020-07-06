package com.qifenqian.bms.paymentmanager.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoBean;
import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;
import com.qifenqian.bms.paymentmanager.bean.AcctSevenBussAgentpay;
import com.qifenqian.bms.paymentmanager.bean.TdAgentPaymentChildRecord;
import com.qifenqian.bms.paymentmanager.bean.TdAuditReportExport;
import com.qifenqian.bms.paymentmanager.bean.TdBankCardBin;
import com.qifenqian.bms.paymentmanager.bean.TdChannelInfo;
import com.qifenqian.bms.paymentmanager.bean.TdPayCreditAuditInfo;
import com.qifenqian.bms.paymentmanager.bean.TdPayCreditInfo;
import com.qifenqian.bms.paymentmanager.bean.TdPayInInfo;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentAccountAuditInfo;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentAuditRecode;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatDetail;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentBatInfo;
import com.qifenqian.bms.paymentmanager.bean.TdPaymentFeeInfo;
import com.qifenqian.bms.paymentmanager.bean.TdRecodeExport;
import com.qifenqian.bms.paymentmanager.bean.TdReportExport;

/**
 * @project sevenpay-bms-web
 * @fileName MerchantSettleMapper.java
 * @author huiquan.ma
 * @date 2015年10月10日
 * @memo
 */
@MapperScan
public interface PaymentManagerMapper {

	/**
	 * 查询代付明细
	 * @param selectBean
	 * @return
	 */
	List<TdPaymentBatDetail> selecPaymentList(String batNo);
	
	List<AcctSevenBussAgentpay> queryAcctSevenToPayBuss(AcctSevenBussAgentpay sevenBussAgentpay);
	/**
	 * 批量插入
	 * @param selectBean
	 * @return
	 */
	int insertPaymentList(List<TdPaymentBatDetail> bean);
	
	/**
	 * 查询银行号
	 * @param selectBean
	 * @return
	 */
	List<TdBankCardBin> selectCardBinList();

	int upPaymentBatDetail(TdPaymentBatDetail updateBean);

	int insertBatInfo(TdPaymentBatInfo bean);
	
	int upPaymentBatInfo(TdPaymentBatInfo bean);
	
	int updateDetailBatch(List<TdPaymentBatDetail> bean);
	

	/**
	 * 查询未审核代付商户
	 * @param selectBean
	 * @return
	 */
	List<TdCustInfo> selToPayCustList(TdCustInfo td);
	
	/**
	 * 获取代付账户
	 */
	List<TdCustInfo> selRechargeCustList(TdCustInfo td);
	
	
	/**
	 * 查询代付商户信息
	 * @param selectBean
	 * @return
	 */
	TdCustInfo selToPayCust(String custId);
	
	/**
	 * 查询代付商户信息
	 * @param selectBean
	 * @return
	 */
	void updateToPayCust(TdCustInfo cust);
	
	/**
	 * 添加代付商户费用信息
	 * @param selectBean
	 * @return
	 */
	void insertTdPaymentFeeInfo(TdPaymentFeeInfo fee);
	
	/**
	 * 查询代付商户费用信息
	 * @param selectBean
	 * @return
	 */
	TdPaymentFeeInfo selTdPaymentFeeInfo(TdPaymentFeeInfo fee);
	
	
	
	/**
	 * 查询代付默认费用信息
	 * @param selectBean
	 * @return
	 */
	TdPayInInfo getDefaultFeeInfo(TdPayInInfo fee);
	
	
	/**
	 * 查询代付商户审核信息
	 * @param selectBean
	 * @return
	 */
	List<TdPaymentAccountAuditInfo> getToPayHistory(TdPaymentAccountAuditInfo fee);
	
	
	/**
	 * 添加代付账户开通审核信息表
	 * @param selectBean
	 * @return
	 */
	void insertPaymentAccountAuditInfo(TdPaymentAccountAuditInfo info);
	
	/**
	 * 查询单笔商户代付商户信息
	 * @param selectBean
	 * @return
	 */
	TdPaymentBatInfo getSingleToPayInfo(TdPaymentBatInfo info);
	
	TdPaymentBatInfo getBatchToPayInfo(TdPaymentBatInfo info);
	
	public List<TdPaymentBatInfo>  getAudingPaymentBatList(TdPaymentBatInfo batInfo);
	
	public List<TdAuditReportExport>  exportPayment(TdPaymentBatInfo batInfo);
	
	
	
	public List<TdPayCreditInfo> rechargePaymentList(TdPayCreditInfo queryBean);
	
	public List<TdPayCreditInfo> selRechargeInfo(@Param("id")String id);
	
	public List<TdPaymentBatInfo>  getPaymentRecodeQuery(TdPaymentBatInfo batInfo);
	
	public List<TdRecodeExport>  getPaymentRecodeQueryExcel(TdPaymentBatInfo batInfo);
	
	
	
	public List<TdPaymentBatInfo>  getPaymentReportList(TdPaymentBatInfo batInfo);
	
	public TdPaymentBatInfo  getBatchAuditPayment(@Param("batNo")String batNo);
	
	public List<TdPaymentBatDetail> getBatchAuditPaymentList(TdPaymentBatDetail detail);
	
	public List<TdPaymentBatInfo> getSingleInfo(TdPaymentBatInfo batInfo);
	
	public   List<TdReportExport> getReportExport(TdPaymentBatInfo batInfo);
	
	public   TdBankCardBin selectCardName(TdBankCardBin bankCardBin);
    
	
	
   public   TdBankCardBin getReportExport(TdBankCardBin bankCardBin);
	
	public void InsertTdPaymentAuditRecode(TdPaymentAuditRecode recode);
	
	public void InsertTdPayCreditAuditInfo(TdPayCreditAuditInfo auditInfo);
	
	public void updateTdPayCreditAuditInfo(TdPayCreditAuditInfo creditAuditInfo);
	
	public void updateTdPayCreditInfo(TdPayCreditInfo creditInfo);
	
	public void updateTdPaymentBatInfo(TdPaymentBatInfo batInfo);
	
	public void updateTdPaymentSingleInfo(TdPaymentBatInfo batInfo);
	
	public void updateTdPaymentBatDetail(TdPaymentBatDetail batDetail);
	
	public List<TdPaymentAuditRecode> getPaymentCheckHistory(@Param("batNo")String batNo);
	
	
	public List<TdPayCreditAuditInfo> getRechargeCheckHistory(@Param("creditId")String creditId);
	
	public List<TdPaymentAuditRecode> getSinglePaymentCheckHistory(@Param("batNo")String batNo);
	
	public TdAgentPaymentChildRecord getTdAgentPaymentChildRecord(@Param("batNo")String batNo);
	
	public TdAgentPaymentChildRecord selTdAgentPaymentChildRecord(@Param("batNo")String batNo);
	
	public void addTdAgentPaymentChildRecord(TdAgentPaymentChildRecord child);
	
	public TdPaymentBatInfo selectBatRecode(TdPaymentBatInfo td);
	
	public String getBankScanPath(TdPayCreditInfo scan);
	
	
	public String getBankScanPathById(TdPayCreditInfo scan);

	public void updateToTdPayCreditAuditInfo(TdPayCreditInfo info);

	public String selectRechargeRate(String merchantCode);

	public OrderInfoBean getOrderInfoByCreditId(String id);

	public Map<String,Object> selectMerProduct(@Param("merchantCode")String merchantCode, @Param("prodCode")String prodCode);

	public TdCustInfo selCustInfoByEmail(String email);
	
	/**
	 * 根据merchantCode查询商户签约的渠道信息
	 * @param merchantCode
	 * @return
	 */
	public TdChannelInfo getChannelInfoByMerchantCode(@Param("merchantCode")String merchantCode);

	public List<TdChannelInfo> selMerchantChannelList(TdChannelInfo queryBean);

}
