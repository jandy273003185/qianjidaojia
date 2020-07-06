package com.qifenqian.bms.common.tags;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.qifenqian.bms.basemanager.rule.bean.Rule;
import com.qifenqian.bms.basemanager.rule.mapper.RuleMapper;
import com.qifenqian.bms.platform.common.utils.SpringUtils;

/**
 * 选择套餐费率标签
 * 
 * @author PC
 *
 */
public class SelectRuleFixedTag extends SimpleTagSupport {

	private String id;

	private String name;

	private String clasS;

	private String style;

	private String feeCode;

	public String getFeeCode() {
		return feeCode;
	}

	public void setFeeCode(String feeCode) {
		this.feeCode = feeCode;
	}

	private List<Rule> rules;

	@Override
	public void doTag() throws JspException, IOException {

		JspContext jspContext = this.getJspContext();

		JspWriter jspWriter = jspContext.getOut();

		Rule rule = new Rule();

		rule.setFeeType("Fixed");

		if (null != feeCode) {
			rule.setFeeCode(feeCode);
		}
		RuleMapper ruleMapper = SpringUtils.getBean(RuleMapper.class);
		rule.setStatus("VALID");
		rules = ruleMapper.selectRules(rule);

		jspWriter.write("<table ");

		if (null != style) {
			jspWriter.write("style = '" + style + "' ");
		}
		jspWriter.write(">");

		jspWriter.write("<tr>");
		jspWriter.write("<th style='text-align: center;'>收费方式</th>");
		jspWriter.write("<th style='text-align: center;'>费率</th>");
		jspWriter.write("<th style='text-align: center;'>结算周期</th>");

		if (null == feeCode) {
			jspWriter.write("<th>选择</th>");
		}
		jspWriter.write("</tr>");

		for (Rule rulealis : rules) {

			jspWriter.write("<tr>");
			jspWriter.write("<td >" + rulealis.getFeeName() + "</td>");
			jspWriter.write("<td >" + rulealis.getRuleValues() + "</td>");
			jspWriter.write("<td >T+1</td>");

			if (null == feeCode) {
				jspWriter.write("<td >");
				jspWriter.write("<input type=\"radio\" ");
				jspWriter.write("value ='" + rulealis.getFeeCode() + "' ");
				if (null != name) {
					jspWriter.write("name = '" + name + "' ");
				}
				jspWriter.write("class = '" + rulealis.getFeeType() + "' ");
				if (null != id) {
					jspWriter.write("id = '" + id + "' ");
				}
				jspWriter.write("/>");
				jspWriter.write("</td>");
			}

			jspWriter.write("</tr>");
		}

		jspWriter.write("</table>");

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

	public List<Rule> getRules() {
		return rules;
	}

	public void setRules(List<Rule> rules) {
		this.rules = rules;
	}

	public String getClasS() {
		return clasS;
	}

	public void setClasS(String clasS) {
		this.clasS = clasS;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

}
