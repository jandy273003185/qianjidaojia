package com.qifenqian.bms.platform.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.stereotype.Component;

import com.qifenqian.bms.basemanager.agency.dao.AgencyDao;
import com.qifenqian.bms.basemanager.merchant.bean.Merchant;
import com.qifenqian.bms.platform.common.utils.SpringUtils;

/**
 * 代理商信息
 * 
 * @author PC
 *
 */
@Component
public class SelectAgentMerchantTag extends SimpleTagSupport {

	private String id;

	private String name;

	private String clasS;

	private String defaultValue;

	@Override
	public void doTag() throws JspException, IOException {

		JspContext jspContext = this.getJspContext();

		JspWriter jspWriter = jspContext.getOut();

		AgencyDao agencyDao = SpringUtils.getBean(AgencyDao.class);

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
		Merchant merchant = new Merchant();
		merchant.setMerchantFlag("3");
		List<Merchant> merchants = agencyDao.selectAgencyList(merchant);
		
		jspWriter.write(">");
		jspWriter.write("<option value=''>  --请选择--  </option>");
		if (null != merchants && !merchants.isEmpty()) {
			for (Merchant merchan : merchants) {
				if (merchan.getCustId().equals(defaultValue)) {
					jspWriter.write("<option selected='selected' value='" + merchan.getCustId() + "'>" + merchan.getCustName()+ "</option>");
				} else {
					jspWriter.write("<option value='" + merchan.getCustId() + "'>" + merchan.getCustName() + "</option>");
				}
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
