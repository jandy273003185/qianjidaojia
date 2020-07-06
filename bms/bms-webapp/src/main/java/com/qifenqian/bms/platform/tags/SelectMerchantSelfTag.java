package com.qifenqian.bms.platform.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.basemanager.merchant.dao.MerchantDao;
import com.qifenqian.bms.platform.common.utils.SpringUtils;

/**
 * 商户标签
 * 
 * @author Dayet
 * 
 */
public class SelectMerchantSelfTag extends SimpleTagSupport {


	private String id;

	private String name;

	private String clasS;
	
	private String defaultValue;

	@Override
	public void doTag() throws JspException, IOException {

		JspContext jspContext = this.getJspContext();

		JspWriter jspWriter = jspContext.getOut();
		
		MerchantDao merchantDao = SpringUtils.getBean(MerchantDao.class);

		jspWriter.write("<select ");

		if (null != id) {
			jspWriter.write("id = '" + id + "' ");
		}

		if (null != name) {
			jspWriter.write("name = '" + name + "' ");
		}

		if (null != clasS) {
			jspWriter.write("class = '" + clasS + "' ");
		}
		List<Merchant> merchants = merchantDao.getAllMerchant();
	
		jspWriter.write(">");
		jspWriter.write("<option value=''>  --请选择--  </option>");

		for (Merchant merchant : merchants) {
			if (merchant.getCustId().equals(defaultValue)) {
				jspWriter.write("<option selected='selected' value='" + merchant.getCustId() + "'>" + merchant.getCustName()+ "</option>");
			} else {
				jspWriter.write("<option value='" + merchant.getCustId() + "'>" + merchant.getCustName() + "</option>");
			}
		}

		jspWriter.write("</select>");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getClasS() {
		return clasS;
	}

	public void setClasS(String clasS) {
		this.clasS = clasS;
	}
	
	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

}
