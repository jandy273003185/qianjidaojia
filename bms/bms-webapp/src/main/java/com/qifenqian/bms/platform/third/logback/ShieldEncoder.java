package com.qifenqian.bms.platform.third.logback;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;

/**
 * 给予关键词屏蔽的logEncoder
 * 
 * @project sevenpay-commons
 * @fileName ShieldEncoder.java
 * @author kunwang.li
 * @date 2015年10月13日
 * @memo
 */
public class ShieldEncoder extends PatternLayoutEncoder {

	/**
	 * 支持正则表达式， 需要屏蔽的关键词
	 */
	private String shieldPattern;

	@Override
	public void start() {
		super.start();
		PatternLayout patternLayout = new ShieldPatternLayout(this.shieldPattern);
		patternLayout.setContext(context);
		patternLayout.setPattern(getPattern());
		patternLayout.setOutputPatternAsHeader(outputPatternAsHeader);
		patternLayout.start();
		this.layout = patternLayout;
	}

	public String getShieldPattern() {
		return shieldPattern;
	}

	public void setShieldPattern(String shieldPattern) {
		this.shieldPattern = shieldPattern;
	}

}
