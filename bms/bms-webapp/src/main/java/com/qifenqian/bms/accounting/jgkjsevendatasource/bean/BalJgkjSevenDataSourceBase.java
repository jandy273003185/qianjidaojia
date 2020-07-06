package com.qifenqian.bms.accounting.jgkjsevendatasource.bean;

import java.io.Serializable;

import com.sevenpay.invoke.common.type.RequestColumnValues;

public class BalJgkjSevenDataSourceBase implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 6953232439138962092L;
	
    private RequestColumnValues.BusinessType businessType;
    
	public RequestColumnValues.BusinessType getBusinessType() {
		return businessType;
	}

	public void setBusinessType(RequestColumnValues.BusinessType businessType) {
		this.businessType = businessType;
	}
}
