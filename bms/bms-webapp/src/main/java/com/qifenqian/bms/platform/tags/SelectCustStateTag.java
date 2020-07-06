package com.qifenqian.bms.platform.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.qifenqian.bms.basemanager.custInfo.bean.TdCustInfo;


/**
 * 客户状态
 * @author PC
 *
 */
public class SelectCustStateTag extends SimpleTagSupport {

	
private String id;
	
	private String name;
	
	private String clasS;
	
	private List<TdCustInfo> custStates;
	
	
	
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
		jspWriter.write("<option value=''>  - 请选择 -  </option>");
		for(TdCustInfo cust : custStates){
			jspWriter.write("<option value = '"+cust.getState()+"'>"+cust.getDesc()+"</option>"  );
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

	public List<TdCustInfo> getCustStates() {
		return custStates;
	}

	public void setCustStates(List<TdCustInfo> custStates) {
		this.custStates = custStates;
	}



}
