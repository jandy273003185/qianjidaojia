package com.qifenqian.bms.accounting.reconciliationSummary.bean;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.seven.micropay.channel.enums.PaychannelType;


public class Summary {
	
	//支付渠道
	private PaychannelType paychannelType;
	
	//成功笔数
	private String success_total;
	
	//成功总金额
	private BigDecimal success_amt;
	
	//丢单笔数
	private String lack_total;
	
	//丢单金额
	private BigDecimal lack_amt;
	
	//掉单笔数
	private String lose_total;
	
	//掉单金额
	private BigDecimal lose_amt;
	
	//差错成功笔数
	private String diff_total;
	
	//差错成功金额
	private BigDecimal diff_amt;
	
	//创建时间
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date create_time;
	
	

	public String getDiff_total() {
		return diff_total;
	}

	public void setDiff_total(String diff_total) {
		this.diff_total = diff_total;
	}

	public BigDecimal getDiff_amt() {
		return diff_amt;
	}

	public void setDiff_amt(BigDecimal diff_amt) {
		this.diff_amt = diff_amt;
	}

	public PaychannelType getPaychannelType() {
		return paychannelType;
	}

	public void setPaychannelType(PaychannelType paychannelType) {
		this.paychannelType = paychannelType;
	}

	public String getSuccess_total() {
		return success_total;
	}

	public void setSuccess_total(String success_total) {
		this.success_total = success_total;
	}

	

	public String getLack_total() {
		return lack_total;
	}

	public void setLack_total(String lack_total) {
		this.lack_total = lack_total;
	}

	

	public String getLose_total() {
		return lose_total;
	}

	public void setLose_total(String lose_total) {
		this.lose_total = lose_total;
	}

	



	public BigDecimal getSuccess_amt() {
		return success_amt;
	}

	public void setSuccess_amt(BigDecimal success_amt) {
		this.success_amt = success_amt;
	}

	public BigDecimal getLack_amt() {
		return lack_amt;
	}

	public void setLack_amt(BigDecimal lack_amt) {
		this.lack_amt = lack_amt;
	}

	public BigDecimal getLose_amt() {
		return lose_amt;
	}

	public void setLose_amt(BigDecimal lose_amt) {
		this.lose_amt = lose_amt;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}
