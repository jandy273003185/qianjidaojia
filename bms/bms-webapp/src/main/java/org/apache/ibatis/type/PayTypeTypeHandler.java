package org.apache.ibatis.type;

import com.seven.micropay.channel.enums.ChannelCode;

/**
 * 支付类型 MyBatis 处理类, MyBatis 默认的行为
 * 
 * @author Sun Xun
 *
 */
public class PayTypeTypeHandler extends org.apache.ibatis.type.EnumTypeHandler<ChannelCode> {

	public PayTypeTypeHandler(Class<ChannelCode> type) {
		super(type);
	}

}
