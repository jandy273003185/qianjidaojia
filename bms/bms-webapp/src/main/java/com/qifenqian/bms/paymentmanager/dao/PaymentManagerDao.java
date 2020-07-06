package com.qifenqian.bms.paymentmanager.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.bean.OrderInfoBean;
import com.qifenqian.bms.basemanager.aggregatepayment.orderinfo.mapper.OrderMapperMaster;
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
import com.qifenqian.bms.paymentmanager.mapper.PaymentManagerMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class PaymentManagerDao {

	@Autowired
	private PaymentManagerMapper mapper;
	
	@Autowired
	private OrderMapperMaster orderMapperMaster;

	public int insertPaymentList(List<TdPaymentBatDetail> bean) {
		
		return mapper.insertPaymentList(bean);
	}

	/**
	 * 查询银行号
	 * 
	 * @param selectBean
	 * @return
	 */
	public List<TdBankCardBin> selectCardBinList() {
		return mapper.selectCardBinList();
	}

	public int upPaymentBatDetail(TdPaymentBatDetail updateBean) {
		return mapper.upPaymentBatDetail(updateBean);
	}
	
	public int insertBatInfo(TdPaymentBatInfo bean){
		return mapper.insertBatInfo(bean);
	}
	
	@Page
	public List<TdPaymentBatDetail> selecPaymentList(String batNo){
		return mapper.selecPaymentList(batNo);
	}
	@Page
	public List<AcctSevenBussAgentpay> queryAcctSevenToPayBuss(AcctSevenBussAgentpay queryBean){
		return mapper.queryAcctSevenToPayBuss(queryBean);
	}
	
	/**
	 * 查询批次商户信息
	 * 
	 * @param batNo
	 * @return
	 */
	@Page
	public List<TdCustInfo> selToPayCustList(TdCustInfo td){
		return mapper.selToPayCustList(td);
	}
	
	
	public List<TdCustInfo> selRechargeCustList(TdCustInfo td){
		return mapper.selToPayCustList(td);
	}
	
	/**
	 * 查询商户信息
	 * 
	 * @param batNo
	 * @return
	 */
	public TdCustInfo selToPayCust(String custId){
		return mapper.selToPayCust(custId);
	}
	
	/**
	 * 更新代付账户信息
	 * 
	 * @param batNo
	 * @return
	 */
	public void updateToPayCust(TdCustInfo cust){
		mapper.updateToPayCust(cust);
	}
	
	/**
	 * 添加代付账户费用信息
	 * 
	 * @param batNo
	 * @return
	 */
	public void insertTdPaymentFeeInfo(TdPaymentFeeInfo fee){
		mapper.insertTdPaymentFeeInfo(fee);
	}
	
	/**
	 * 查询商户代付账户费用信息
	 * 
	 * @param batNo
	 * @return
	 */
	public TdPaymentFeeInfo selTdPaymentFeeInfo(TdPaymentFeeInfo fee){
		return mapper.selTdPaymentFeeInfo(fee);
	}
	
	/**
	 * 查询默认代付账户费用信息
	 * 
	 * @param batNo
	 * @return
	 */
	public TdPayInInfo selDefaultFeeInfo(TdPayInInfo fee){
		return mapper.getDefaultFeeInfo(fee);
	}
	
	
	
	/**
	 * 查询代付账户审核信息
	 * 
	 * @param batNo
	 * @return
	 */
	public List<TdPaymentAccountAuditInfo> getToPayHistory(TdPaymentAccountAuditInfo acctinfo){
		return mapper.getToPayHistory(acctinfo);
	}
	
	/**
	 * 添加代付账户开通审核信息
	 * 
	 * @param batNo
	 * @return
	 */
	public void insertPaymentAccountAuditInfo(TdPaymentAccountAuditInfo info){
		mapper.insertPaymentAccountAuditInfo(info);
	}
	
	/**
	 * 查询单笔代付账户信息
	 * 
	 * @param batNo
	 * @return
	 */
	public TdPaymentBatInfo getSingleToPayInfo(TdPaymentBatInfo info) {
		return mapper.getSingleToPayInfo(info);
	}
	
	public List<TdRecodeExport>  getPaymentRecodeQueryExcel(TdPaymentBatInfo batInfo){
		return mapper.getPaymentRecodeQueryExcel(batInfo);
	}

	
	@Page
	public List<TdPaymentBatInfo> getAudingPaymentBatList(TdPaymentBatInfo queryBean){
		return mapper.getAudingPaymentBatList(queryBean);
	}
	
	
	public List<TdAuditReportExport> exportPayment(TdPaymentBatInfo queryBean){
		return mapper.exportPayment(queryBean);
	}
	
	
	
	
	@Page
	public List<TdPayCreditInfo> rechargePaymentList(TdPayCreditInfo queryBean){
		return mapper.rechargePaymentList(queryBean);
	}
	
	
	
	public List<TdPayCreditInfo> selRechargeInfo(String id){
		return mapper.selRechargeInfo(id);
	}
	
	@Page
	public List<TdPaymentBatInfo> getPaymentRecodeQuery(TdPaymentBatInfo queryBean){
		return mapper.getPaymentRecodeQuery(queryBean);
	}
	
	@Page
	public List<TdPaymentBatInfo> getPaymentReportList(TdPaymentBatInfo queryBean){
		return mapper.getPaymentReportList(queryBean);
	}
	
	
	
	public TdPaymentBatInfo   getBatchAuditPayment(String batNo){
		
		return mapper.getBatchAuditPayment(batNo);
	}
	
	@Page
   public List<TdPaymentBatDetail> getBatchReportPaymentList(TdPaymentBatDetail batDetail){
		
		return  mapper.getBatchAuditPaymentList(batDetail);
	}

	public List<TdPaymentBatDetail> getBatchAuditPaymentList(TdPaymentBatDetail batDetail){
		
		return  mapper.getBatchAuditPaymentList(batDetail);
	}
	
    public List<TdPaymentBatInfo> getSingleInfo(TdPaymentBatInfo batInfo){
		
		return  mapper.getSingleInfo(batInfo);
	}
    
  @Page	
 public List<TdReportExport> getReportExport(TdPaymentBatInfo batInfo){
		
		return  mapper.getReportExport(batInfo);
	}
    
	public   TdBankCardBin selectCardName(TdBankCardBin bankCardBin){
		return mapper.selectCardName(bankCardBin);
	}

	public void InsertTdPaymentAuditRecode(TdPaymentAuditRecode recode){
		mapper.InsertTdPaymentAuditRecode(recode);
		
	}
	public void updateTdPaymentBatInfo(TdPaymentBatInfo batInfo){
		mapper.updateTdPaymentBatInfo(batInfo);
	}
	
	public void updateTdPaymentSingleInfo(TdPaymentBatInfo batInfo){
		mapper.updateTdPaymentSingleInfo(batInfo);
	}
	public void InsertTdPayCreditAuditInfo(TdPayCreditAuditInfo auditInfo){
		mapper.InsertTdPayCreditAuditInfo(auditInfo);
	}
	
	public void updateTdPayCreditInfo(TdPayCreditInfo creditInfo){
		mapper.updateTdPayCreditInfo(creditInfo);
	}
	public List<TdPayCreditAuditInfo> getRechargeCheckHistory(String creditId){
		return mapper.getRechargeCheckHistory(creditId);
	}
	
	public void updateTdPaymentBatDetail(TdPaymentBatDetail batDetail){
		mapper.updateTdPaymentBatDetail(batDetail);
	}
	
	public List<TdPaymentAuditRecode> getPaymentCheckHistory(String batNo){
		return  mapper.getPaymentCheckHistory(batNo);
	}
	
	public List<TdPaymentAuditRecode> getSinglePaymentCheckHistory(String batNo){
		return  mapper.getSinglePaymentCheckHistory(batNo);
	}
	
	public TdAgentPaymentChildRecord getTdAgentPaymentChildRecord(String batNo){
		return  mapper.getTdAgentPaymentChildRecord(batNo);
	}

	public TdAgentPaymentChildRecord selTdAgentPaymentChildRecord(String batNo){
		return  mapper.selTdAgentPaymentChildRecord(batNo);
	}
	
	
	public void addTdAgentPaymentChildRecord(TdAgentPaymentChildRecord child){
		mapper.addTdAgentPaymentChildRecord(child);
		
	}
	
	public TdPaymentBatInfo selectBatRecode(TdPaymentBatInfo td){
		return mapper.selectBatRecode(td);
	}
	
	public String findBankScanPath(TdPayCreditInfo scan){
		return mapper.getBankScanPath(scan);
	}
	
	public String getBankScanPathById(TdPayCreditInfo scan){
		return mapper.getBankScanPathById(scan);
	}
	public void updateTdPayCreditAuditInfo(TdPayCreditAuditInfo creditAuditInfo){
		mapper.updateTdPayCreditAuditInfo(creditAuditInfo);
	}
	
	public TdPaymentBatInfo getBatchToPayInfo(TdPaymentBatInfo info){
	  return mapper.getBatchToPayInfo(info);
	}

	public void updateToTdPayCreditAuditInfo(TdPayCreditInfo info) {
		// TODO Auto-generated method stub
		mapper.updateToTdPayCreditAuditInfo(info);
	}

	public String selectRechargeRate(String merchantCode) {
		// TODO Auto-generated method stub
		return mapper.selectRechargeRate(merchantCode);
	}

	public OrderInfoBean getOrderInfoByCreditId(String id) {
		// TODO Auto-generated method stub
		return mapper.getOrderInfoByCreditId(id);
	}

	public void updateOrderInfo(OrderInfoBean order) {
		// TODO Auto-generated method stub
		orderMapperMaster.updateOrderInfo(order);
	}
	
	/**
	 * 查询商户签约产品
	 * @param merNo
	 * @param prodCode
	 * @return
	 */
	public Map<String,Object> selectMerProduct(String merchantCode, String prodCode){
		return mapper.selectMerProduct(merchantCode,prodCode);
	}

	public TdCustInfo selCustInfoByEmail(String email) {
		// TODO Auto-generated method stub
		return mapper.selCustInfoByEmail(email);
	}
	public TdChannelInfo getChannelInfoByMerchantCode(String merchantCode) {
		return mapper.getChannelInfoByMerchantCode(merchantCode);
	}
	@Page
	public List<TdChannelInfo> selMerchantChannelList(TdChannelInfo queryBean) {
		// TODO Auto-generated method stub
		return mapper.selMerchantChannelList(queryBean);
	}
}
