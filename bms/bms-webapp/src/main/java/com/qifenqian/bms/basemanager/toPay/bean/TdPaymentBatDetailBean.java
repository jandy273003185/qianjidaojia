package com.qifenqian.bms.basemanager.toPay.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * 批量代付明细实体类
 *
 */

public class TdPaymentBatDetailBean  implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3953786833042996926L;
	/**
	 * 批次号
	 */
	private String batNo = null;
	/**
	 * 序号
	 */
	private String rowNo = null;
	/**
	 * 收款人
	 */
	private String recAccountName = null;
	/**
	 * 收款人账号
	 */
	private String recAccountNo = null;
	/**
	 * 收款人联行号
	 */
	private String recBankCode = null;
	/**
	 * 收款人联连号
	 */
	private String recBankCnaps = null;
	/**
	 * 收款金额
	 */
	private String transAmt = null;
	/**
	 * 代付状态
	 */
	private String tradeStatus = null;
	/**
	 * 处理情况摘要
	 */
	private String procMemo = null;
	/**
	 * 创建人
	 */
	private String createId = null;
	/**
	 * 创建时间
	 */
	private String createTime = null;
	/**
	 * 修改人
	 */
	private String modifyId = null;
	/**
	 * 修改时间
	 */
	private String modifyTime = null;
	/**
	 * 结束时间
	 */
	private String endCreateTime;

	/**
	 * 收款人银行名称
	 */
	private String payeeAcctBankName = null;
	/**
	 * 代付类型
	 */
	private String toPayType=null;
	
	/**
	 * 
	 * 手机号码
	 */
	private String phone;
	
	/**
	 * 
	 * 邮箱
	 */
	private String email;
	
	/**
	 * 
	 * 用来标识单笔还是批量
	 */
	private String  type;
	/**
	 * 
	 * 起始时间
	 */
	private String startCreateTime;
	
	/**
	 * 处理状态
	 */
	private String status = null;
	
	/**
	 * 
	 * 付款总额
	 */
	private String sumAmt = null;
	
	
	private String sumCount=null;
	
	private BigDecimal transAmtCount = null;//批量的总金额
	
	private BigDecimal channelFeeamt; //付给渠道的手续费
	private String custId;
	
	/**
	 *服务费
	 */
	private String serviceFee;

	/***
	 * 核心流水号
	 */
	private String coreSn;
	
	/***
	 * 核心返回码
	 */
	private String coreReturnCode;
	
	/***
	 * 核心返回信息
	 */
	private String coreReturnInfo;
	
	/***
	 * 核心返回时间
	 */
	private Date coreReturnTime;
	
	
	private String  toPayCount;
	
	private String   totalMoney;
    
    private MultipartFile backPic;
    
    private String useBalance;
    
    private String recCardName;
    
    private String  bankPaymentNo;
    
    //开户行省份 
    private String provinceName;
    //开户行城市
    private String cityName;
    //开户行省份代码
    private String provinceCode;
    //开户行城市代码
    private String cityCode;
    
    private String orderNo;
    
    private String orderId;//交易订单id（关联订单表）
    
    //商户号
    private String merchantCode;
    
    private String singleFeeAmt;
    //通道订单号
    private String channelOrderId;
    
   
	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSingleFeeAmt() {
		return singleFeeAmt;
	}

	public void setSingleFeeAmt(String singleFeeAmt) {
		this.singleFeeAmt = singleFeeAmt;
	}

	public String getMerchantCode() {
		return merchantCode;
	}

	public void setMerchantCode(String merchantCode) {
		this.merchantCode = merchantCode;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCoreSn() {
		return coreSn;
	}

	public void setCoreSn(String coreSn) {
		this.coreSn = coreSn;
	}

	public String getCoreReturnCode() {
		return coreReturnCode;
	}

	public void setCoreReturnCode(String coreReturnCode) {
		this.coreReturnCode = coreReturnCode;
	}

	public String getCoreReturnInfo() {
		return coreReturnInfo;
	}

	public void setCoreReturnInfo(String coreReturnInfo) {
		this.coreReturnInfo = coreReturnInfo;
	}

	public Date getCoreReturnTime() {
		return coreReturnTime;
	}

	public void setCoreReturnTime(Date coreReturnTime) {
		this.coreReturnTime = coreReturnTime;
	}


	public String getBatNo() {
		return batNo;
	}

	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}

	public String getRowNo() {
		return rowNo;
	}

	public void setRowNo(String rowNo) {
		this.rowNo = rowNo;
	}

	public String getRecAccountName() {
		return recAccountName;
	}

	public void setRecAccountName(String recAccountName) {
		this.recAccountName = recAccountName;
	}

	public String getRecAccountNo() {
		return recAccountNo;
	}

	public void setRecAccountNo(String recAccountNo) {
		this.recAccountNo = recAccountNo;
	}

	public String getRecBankCode() {
		return recBankCode;
	}

	public void setRecBankCode(String recBankCode) {
		this.recBankCode = recBankCode;
	}

	public String getRecBankCnaps() {
		return recBankCnaps;
	}

	public void setRecBankCnaps(String recBankCnaps) {
		this.recBankCnaps = recBankCnaps;
	}

	public String getTransAmt() {
		return transAmt;
	}

	public void setTransAmt(String transAmt) {
		this.transAmt = transAmt;
	}

	public String getProcMemo() {
		return procMemo;
	}

	public void setProcMemo(String procMemo) {
		this.procMemo = procMemo;
	}

	public String getCreateId() {
		return createId;
	}

	public void setCreateId(String createId) {
		this.createId = createId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyId() {
		return modifyId;
	}

	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getEndCreateTime() {
		return endCreateTime;
	}

	public void setEndCreateTime(String endCreateTime) {
		this.endCreateTime = endCreateTime;
	}

	public String getPayeeAcctBankName() {
		return payeeAcctBankName;
	}

	public void setPayeeAcctBankName(String payeeAcctBankName) {
		this.payeeAcctBankName = payeeAcctBankName;
	}

	public String getTradeStatus() {
		return tradeStatus;
	}

	public void setTradeStatus(String tradeStatus) {
		this.tradeStatus = tradeStatus;
	}

	public String getToPayType() {
		return toPayType;
	}

	public void setToPayType(String toPayType) {
		this.toPayType = toPayType;
	}
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	public String getStartCreateTime() {
		return startCreateTime;
	}

	public void setStartCreateTime(String startCreateTime) {
		this.startCreateTime = startCreateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSumAmt() {
		return sumAmt;
	}

	public void setSumAmt(String sumAmt) {
		this.sumAmt = sumAmt;
	}

	public String getSumCount() {
		return sumCount;
	}
	public void setSumCount(String sumCount) {
		this.sumCount = sumCount;
	}

	public BigDecimal getTransAmtCount() {
		return transAmtCount;
	}

	public void setTransAmtCount(BigDecimal transAmtCount) {
		this.transAmtCount = transAmtCount;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	
	}

	public String getServiceFee() {
		return serviceFee;
	}

	public void setServiceFee(String serviceFee) {
		this.serviceFee = serviceFee;
	}

	public String getToPayCount() {
		return toPayCount;
	}

	public void setToPayCount(String toPayCount) {
		this.toPayCount = toPayCount;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public MultipartFile getBackPic() {
		return backPic;
	}

	public void setBackPic(MultipartFile backPic) {
		this.backPic = backPic;
	}

	public String getUseBalance() {
		return useBalance;
	}

	public void setUseBalance(String useBalance) {
		this.useBalance = useBalance;
	}

	public String getRecCardName() {
		return recCardName;
	}

	public void setRecCardName(String recCardName) {
		this.recCardName = recCardName;
	}

	public String getBankPaymentNo() {
		return bankPaymentNo;
	}

	public void setBankPaymentNo(String bankPaymentNo) {
		this.bankPaymentNo = bankPaymentNo;
	}

	public String getChannelOrderId() {
		return channelOrderId;
	}

	public void setChannelOrderId(String channelOrderId) {
		this.channelOrderId = channelOrderId;
	}

	public BigDecimal getChannelFeeamt() {
		return channelFeeamt;
	}

	public void setChannelFeeamt(BigDecimal channelFeeamt) {
		this.channelFeeamt = channelFeeamt;
	}
	
}
