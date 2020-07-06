package com.qifenqian.bms.platform.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.qifenqian.bms.basemanager.bank.bean.Bank;
import com.qifenqian.bms.basemanager.bank.mapper.BankMapper;
import com.qifenqian.bms.platform.common.utils.SpringUtils;

/**
 * 选择银行下拉列表标签
 * @author Dayet
 *
 */
public class SelectAllBankTag extends SimpleTagSupport {

	private String id;
	
	private String name;
	
	private String clasS;
	
	private String style;
	
	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	@Override
	public void doTag() throws JspException, IOException {
		
		JspContext jspContext = this.getJspContext();
		
		JspWriter jspWriter = jspContext.getOut();
		
		jspWriter.write("<select ");
		
		BankMapper bankMapper = SpringUtils.getBean(BankMapper.class);
		if(null != id){
			jspWriter.write("id = '"+id+"' ");
		}
		
		if(null != name){
			jspWriter.write("name = '"+name+"' ");
		}
		
		if(null !=clasS){
			jspWriter.write("class = '"+clasS+"' ");
		}
		jspWriter.write(">");
		jspWriter.write("<option value=''>  --请选择--  </option>");
		Bank bankinfo = new Bank();
		List<Bank> banks  = bankMapper.selectBanks(bankinfo);
		for(Bank bank : banks){
			jspWriter.write("<option value = '"+bank.getBankCode()+"'>"+bank.getBankName()+"</option>"  );
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

}
