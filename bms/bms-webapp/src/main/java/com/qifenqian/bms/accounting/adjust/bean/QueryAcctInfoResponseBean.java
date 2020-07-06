/**
 * 
 */
package com.qifenqian.bms.accounting.adjust.bean;

import com.sevenpay.invoke.transaction.queryacctseven.bean.AcctSeven;

/**
 * 账户信息查询响应信息bean
 * 
 * @project sevenpay-bms-web
 * @fileName QueryAcctInfoResponseBean.java
 * @author kunwang.li
 * @date 2015年9月16日
 * @memo
 */
public class QueryAcctInfoResponseBean extends CommonResponseBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AcctSeven acctSeven = null;

	public AcctSeven getAcctSeven() {
		return acctSeven;
	}

	public void setAcctSeven(AcctSeven acctSeven) {
		this.acctSeven = acctSeven;
	}

}
