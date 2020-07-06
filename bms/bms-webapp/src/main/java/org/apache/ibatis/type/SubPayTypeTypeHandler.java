package org.apache.ibatis.type;

import com.seven.micropay.channel.enums.PayType;

/**
 * 通道产品 MyBatis 处理类, MyBatis 默认的行为
 * 
 * @author Sun Xun
 *
 */

public class SubPayTypeTypeHandler extends org.apache.ibatis.type.EnumTypeHandler<PayType> {

	public SubPayTypeTypeHandler(Class<PayType> type) {
		super(type);
	}

}
