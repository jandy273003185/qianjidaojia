package com.qifenqian.bms.platform.common.message.interceptor.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import com.qifenqian.bms.platform.common.message.interceptor.MessageInterceptorInterface;

/**
 * 在BigDecimal属性被设置后,金额缩小100倍
 * 
 * @project gyzb-platform-common
 * @fileName BigDecimalReduce100TimesInterceptor.java
 * @author huiquan.ma
 * @date 2015年11月24日
 * @memo
 */
@Repository
public class BigDecimalReduce100TimesInterceptor implements MessageInterceptorInterface{

	/**
	 * 默认浮点数的标度是2
	 */
	private static final int scaleSize = 2;

	@Override
	public Object deal(Object fieldValue) {
		// 校验
		if (fieldValue == null) {
			return null;
		}

		// 必须是BigDecimal
		if (!(fieldValue instanceof BigDecimal)) {
			throw new IllegalArgumentException("本拦截器支持的金额字段必须是BigDecimal类型");
		}

		// 转成BigDecimal类型
		BigDecimal bdValue = (BigDecimal) fieldValue;

		// 确保此时BigDecimal没有标度,即没有小数部分
		if (bdValue.scale() != 0) {
			throw new IllegalArgumentException("本拦截器支持的BigDecimal的标度必须是0,但是传入的值为" + bdValue.toPlainString() + ",其标度为" + bdValue.scale());
		}

		// 小数点向左移动scaleSize位,10的scaleSize次幂倍
		bdValue = bdValue.movePointLeft(scaleSize);

		// 返回转换后的数值
		return bdValue;
	}
}


