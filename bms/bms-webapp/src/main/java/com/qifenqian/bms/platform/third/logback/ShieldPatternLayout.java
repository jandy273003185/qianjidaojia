package com.qifenqian.bms.platform.third.logback;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * 增加屏蔽关键词屏蔽的日志布局模式
 * 
 * @project sevenpay-commons
 * @fileName ShieldPatternLayout.java
 * @author kunwang.li
 * @date 2015年10月13日
 * @memo
 */
public class ShieldPatternLayout extends PatternLayout {

	private String shieldPattern;

	public ShieldPatternLayout() {

	}

	public ShieldPatternLayout(String shieldPattern) {
		this.shieldPattern = shieldPattern;
	}

	@Override
	public String doLayout(ILoggingEvent event) {
		return super.doLayout(event).replaceAll(this.shieldPattern, "**be_masked_ sensitive_info**");
	}

}
