package com.qifenqian.bms.platform.tags;

import java.io.IOException;
import java.util.List;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.qifenqian.bms.basemanager.bank.bean.Bank;

/**
 * 选择银行下拉列表标签
 * @author Dayet
 *
 */
public class SelectBankTag extends SimpleTagSupport {

private String id;
	
	private String name;
	
	private String clasS;
	
	private List<Bank> banks;
	
	@Override
	public void doTag() throws JspException, IOException {
		
		JspContext jspContext = this.getJspContext();
		
		JspWriter jspWriter = jspContext.getOut();
		
		jspWriter.write("<select ");
		
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

	public List<Bank> getBanks() {
		return banks;
	}

	public void setBanks(List<Bank> banks) {
		this.banks = banks;
	}


}
