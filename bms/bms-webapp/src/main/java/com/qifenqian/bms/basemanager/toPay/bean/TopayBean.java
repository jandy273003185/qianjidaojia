package com.qifenqian.bms.basemanager.toPay.bean;

import java.io.Serializable;
import java.util.List;

/**
 * 代付辅助类，用以接收前台批量发送过来的代付信息
 * @author dengtongbai
 *
 */
public class TopayBean implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1580819664446224852L;

	/**付款商户编号  （即MerchantCode")**/
	private String paerMerchantCode;
	
	/**单笔手续费**/
	private int singleFeeAmt;
	
	private List<TopaySingleDetail> singleDetail;

	public String getPaerMerchantCode() {
		return paerMerchantCode;
	}

	public void setPaerMerchantCode(String paerMerchantCode) {
		this.paerMerchantCode = paerMerchantCode;
	}

	public int getSingleFeeAmt() {
		return singleFeeAmt;
	}

	public void setSingleFeeAmt(int singleFeeAmt) {
		this.singleFeeAmt = singleFeeAmt;
	}

	public List<TopaySingleDetail> getSingleDetail() {
		return singleDetail;
	}

	public void setSingleDetail(List<TopaySingleDetail> singleDetail) {
		this.singleDetail = singleDetail;
	}
	
	
	
}
