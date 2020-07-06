package com.qifenqian.bms.merchant.channel.bean;

import java.io.IOException;
import java.lang.reflect.Type;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;

public enum ChannelStatus implements JSONSerializable {

	NORMAL("00", "正常"), PENDING("09", "准备"), UNKNOE("","未知");

	private String code;
	private String describe;

	private ChannelStatus(String code, String describe) {
		this.code = code;
		this.describe = describe;
	}

	public String getCode() {
		return code;
	}

	public String getDescribe() {
		return describe;
	}

	public static ChannelStatus value(String code) {
		if ("00".equals(code)) {
			return NORMAL;
		} else if ("09".equals(code)) {
			return PENDING;
		} else {
			return UNKNOE;
		}
	}

	/*
	 * FastJson 转换 json
	 * 
	 * @see
	 * com.alibaba.fastjson.serializer.JSONSerializable#write(com.alibaba.fastjson.
	 * serializer.JSONSerializer, java.lang.Object, java.lang.reflect.Type, int)
	 */

	@Override
	public void write(JSONSerializer serializer, Object fieldName, Type fieldType, int features) throws IOException {

		JSONObject object = new JSONObject();
		object.put("name", this.name());
		object.put("code", code);
		object.put("describe", describe);
		serializer.write(object);

	}

}
