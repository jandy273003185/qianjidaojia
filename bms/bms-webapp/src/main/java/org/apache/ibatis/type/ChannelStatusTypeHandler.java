package org.apache.ibatis.type;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.qifenqian.bms.merchant.channel.bean.ChannelStatus;
/**
 * 通道状态  MyBatis 处理类, 将数据库中的 00 09 转换为 枚举
 * @author Sun Xun
 *
 */
public class ChannelStatusTypeHandler implements TypeHandler<ChannelStatus> {

	@Override
	public void setParameter(PreparedStatement ps, int i, ChannelStatus parameter, JdbcType jdbcType)
			throws SQLException {
		ps.setString(i, parameter.getCode());
	}

	@Override
	public ChannelStatus getResult(ResultSet rs, String columnName) throws SQLException {
		return ChannelStatus.value(rs.getString(columnName));

	}

	@Override
	public ChannelStatus getResult(ResultSet rs, int columnIndex) throws SQLException {
		return ChannelStatus.value(rs.getString(columnIndex));
	}

	@Override
	public ChannelStatus getResult(CallableStatement cs, int columnIndex) throws SQLException {
		return ChannelStatus.value(cs.getString(columnIndex));
	}

}
