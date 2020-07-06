package com.qifenqian.bms.common.tags;

import java.io.IOException;
import java.util.ArrayList;
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
public class SelectRulePackageTag extends SimpleTagSupport {

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

		rule.setFeeType("Package");
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
		jspWriter.write("<th style='text-align: center;'>交易量(元)</th>");
		jspWriter.write("<th style='text-align: center;'>费率</th>");
		jspWriter.write("<th style='text-align: center;'>结算周期</th>");

		if (null == feeCode) {
			jspWriter.write("<th>选择</th>");
		}

		jspWriter.write("</tr>");

		// 解析出交易量 解析出费率
		for (Rule rulealis : rules) {
			String ruleDesc = rulealis.getRuleDesc();

			String ruleDescs[] = ruleDesc.split(";");

			List<String> y = new ArrayList<String>();
			for (String s : ruleDescs) {
				String x[] = s.split(",");
				String h = "";
				for (int i = 0; i < x.length; i++) {
					String w[] = x[i].split(":");

					if (i == 1) {
						h = h + w[1];
					} else {
						h = h + w[1] + "----";
					}
				}
				y.add(h);
			}

			String ruleValues = rulealis.getRuleValues();

			String ruleValue[] = ruleValues.split(",");

			List<String> valus = new ArrayList<String>();
			for (String ru : ruleValue) {
				String rv[] = ru.split(":");
				valus.add(rv[1]); 
			}

			for (int i = 0; i < y.size(); i++) {
				if (i == 0) {
					jspWriter.write("<tr>");
					jspWriter.write("<td rowspan=\"4\">" + rulealis.getFeeName() + "</td>");
					jspWriter.write("<td >" + y.get(i) + "</td>");
					jspWriter.write("<td >" + valus.get(i) + "</td>");
					jspWriter.write("<td >T+1</td>");

					if (null == feeCode) {
						jspWriter.write("<td rowspan=\"4\">");
						jspWriter.write("<input type=\"radio\" ");
						jspWriter.write("value ='" + rulealis.getFeeCode() + "' ");
						if (null != name) {
							jspWriter.write("name = '" + name + "' ");
						}
						jspWriter.write("class = '" + rulealis.getFeeType() + "' ");
						jspWriter.write("/>");
						jspWriter.write("</td>");
					}
					
					
					jspWriter.write("</tr>");
				} else {
					jspWriter.write("<tr>");
					jspWriter.write("<td >" + y.get(i) + "</td>");
					jspWriter.write("<td >" + valus.get(i) + "</td>");
					jspWriter.write("<td >T+1</td>");
					jspWriter.write("</tr>");
				}
			}
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
