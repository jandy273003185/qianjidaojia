package com.qifenqian.bms.basemanager.market.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JsonDateValueProcessor implements JsonValueProcessor {

	private final String DEFAULT_FORMAT ="yyyy-MM-dd";
	private String format = "";
	public JsonDateValueProcessor(String format){
		this.format = StringUtils.isNotEmpty(format) ? format : DEFAULT_FORMAT;
	}
    /**
     * 将java.util.Date转换为字符串
     * @param value
     * @return
     */
    private Object process(Object value){           
        if(value instanceof Date){   
            SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.UK);   
            return sdf.format(value);   
        }   
        return value == null ? "" : value.toString();   
    }
    
	@Override
	public Object processArrayValue(Object value, JsonConfig config) {
		// TODO Auto-generated method stub
		return process(value);
	}

	@Override
	public Object processObjectValue(String key, Object value, JsonConfig config) {
		// TODO Auto-generated method stub
		return process(value);
	}

}
