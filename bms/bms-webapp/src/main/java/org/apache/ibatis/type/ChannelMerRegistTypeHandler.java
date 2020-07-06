package org.apache.ibatis.type;

import com.seven.micropay.channel.enums.ChannelMerRegist;

/**
 * 通道名称 MyBatis 处理类, MyBatis 默认的行为
 * 
 * @author Sun Xun
 *
 */
public class ChannelMerRegistTypeHandler extends org.apache.ibatis.type.EnumTypeHandler<ChannelMerRegist> {

	public ChannelMerRegistTypeHandler(Class<ChannelMerRegist> type) {
		super(type);
	}

}
