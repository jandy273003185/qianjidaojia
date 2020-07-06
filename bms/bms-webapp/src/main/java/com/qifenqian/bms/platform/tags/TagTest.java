package com.qifenqian.bms.platform.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class TagTest extends SimpleTagSupport{

	@Override
	public void doTag() throws JspException, IOException {
		JspFragment js = this.getJspBody();
		
		StringWriter sw = new StringWriter();
		js.invoke(sw);
		
		String content = sw.toString();
		content = content.toUpperCase()+"shabi";
		
		for(int i= 0;i<5;i++){
			this.getJspContext().getOut().write(content);
		}
			
		 
		throw new javax.servlet.jsp.SkipPageException();
	}

	
}
