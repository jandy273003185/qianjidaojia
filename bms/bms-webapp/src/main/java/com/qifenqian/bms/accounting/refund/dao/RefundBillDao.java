package com.qifenqian.bms.accounting.refund.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.qifenqian.bms.accounting.refund.bean.RefundBill;
import com.qifenqian.bms.accounting.refund.mapper.RefundBillMapper;
import com.qifenqian.bms.platform.web.page.Page;

@Repository
public class RefundBillDao {
	@Autowired
	private RefundBillMapper refundBillMapper;

	@Page
	public List<RefundBill> select(RefundBill queryBean) {
		return refundBillMapper.select(queryBean);
	}
}
