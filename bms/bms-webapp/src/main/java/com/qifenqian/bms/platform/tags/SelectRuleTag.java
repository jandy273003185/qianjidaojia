package com.qifenqian.bms.platform.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.stereotype.Component;

import com.qifenqian.bms.basemanager.rule.bean.Rule;

/**
 * 选择费率下拉列表标签
 * @author PC
 *
 */
@Component
public class SelectRuleTag extends SimpleTagSupport {

	private String id;
	
	private String name;
	
	private String clasS;
	
	private List<Rule> rules ;
	
	
	
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
		
		for(Rule rule : rules){
			jspWriter.write("<option value = '"+rule.getFeeCode()+"'>"+rule.getFeeName()+"</option>"  );
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

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}


}
